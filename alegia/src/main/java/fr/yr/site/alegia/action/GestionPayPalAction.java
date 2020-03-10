package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.paypal.api.payments.*;
import com.paypal.base.rest.PayPalRESTException;
import fr.yr.site.alegia.beans.*;
import fr.yr.site.alegia.configuration.Factory;
import fr.yr.site.alegia.configuration.GenerateMethod;
import fr.yr.site.alegia.paypal.OrderDetail;
import fr.yr.site.alegia.paypal.PaymentServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

/**
 * Classe qui gére le paiement avec PayPal
 * Fonctionne avec une API fournit par PayPal
 * Dans un premier temps on passe par une authentifaction de l'utilisateur et de la transaction
 * Ensuite on demande la confirmation à l'utilisateur.
 * Une fois que l'utilisateur confirme on effectue la transaction avec le service PayPal
 */
public class GestionPayPalAction extends ActionSupport implements SessionAware {

    // Microseervices
    @Autowired
    private Factory factory;

    private GenerateMethod gm = new GenerateMethod();

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
    private Map<String, Object> session;

    private static final Logger logger = LogManager.getLogger();
    final NumberFormat instance = NumberFormat.getNumberInstance();
    private static final long serialVersionUID = 1L;

    private         String              product;
    private         String              subtotal;
    private         String              shipping;
    private         String              tax;
    private         String              total;
    private         String              livraison;
    private         Integer             countPanier;
    private         Commande            commande;
    private         Integer             commandeId;
    private         Transaction         transaction;
    private         String              paymentId;
    private         Payer               payer;
    private         String              PayerID;
    private         PayerInfo           payerInfo;
    private         ShippingAddress     shippingAddress;
    private         Payment             payment;
    private         String              url;
    private         String              token;
    private         List<Categorie>     categorieList;


    /**
     * Méthode pour récupérer les informations de la commande.
     * Transmet toutes les informations de la commande à l'API PayPal
     * Affiche la page de connexion Paypal
     * @return
     */
    public String doPayPalAuth() {
        try {
            instance.setMinimumFractionDigits(2);
            instance.setMaximumFractionDigits(2);
            commande = factory.getCommandeProxy().getCommande(commandeId);
            float totalC = 0;
            int count = 0;
            gm.generateCommande(commande,count,totalC,factory);
            String tax = String.valueOf(commande.getTotal()*1.1-commande.getTotal());
            OrderDetail orderDetail = new OrderDetail(
                    commande.getNumero()
                    ,String.valueOf(commande.getTotal())
                    ,"10"
                    ,tax
                    ,String.valueOf(commande.getTotal()+10+Float.parseFloat(tax)));

            PaymentServices paymentServices = new PaymentServices();
            Compte compte = factory.getCompteProxy().findById(commande.getCompteId());
            String approvalLink = paymentServices.authorizePayment(orderDetail,compte);
            url = approvalLink;

            return ActionSupport.SUCCESS;

        } catch (PayPalRESTException ex) {
            ex.printStackTrace();
            return ActionSupport.ERROR;
        }
    }

    /**
     * Affiche un récapitulatif de la commande pour l'acheteur.
     * @return
     */
    public String doReview(){
        try {
            PaymentServices paymentServices = new PaymentServices();
            payment = paymentServices.getPaymentDetails(paymentId);
            payerInfo = payment.getPayer().getPayerInfo();
            commande = factory.getCommandeProxy().getCommandeByNumero(
                    payment.getTransactions().get(0).getDescription());
            transaction = payment.getTransactions().get(0);
            shippingAddress = transaction.getItemList().getShippingAddress();
            if (ActionContext.getContext().getSession().get("email") == null){
                Compte compte = factory.getCompteProxy().findById(commande.getCompteId());
                this.session.put("email", compte.getEmail());
                if (compte.getNiveauAccesId() == 2) {
                    this.session.put("admin", compte);
                } else {
                    this.session.put("user", compte);
                }
            }
            countPanier = gm.generateCountPanier(factory
                    ,(String) this.session.get("email"));
            livraison = instance.format(10);
            int count = 0;
            float total = 0;
            gm.generateCommande(commande,count,total, factory);
            categorieList = factory.getCategorieProxy().findByDispo(true);

            return ActionSupport.SUCCESS;
        } catch (PayPalRESTException ex) {
            ex.printStackTrace();
            return ActionSupport.ERROR;
        }
    }

    /**
     * L'utilisateur à confirmer la commande
     * Le paiement PayPal s'effectue
     * Le statut de la commande évolue à l'étape suivante.
     * @return
     * @throws PayPalRESTException
     */
    public String doExecute() throws PayPalRESTException {
        try {
            String email = (String) ActionContext.getContext().getSession().get("email");
            Compte compte = factory.getCompteProxy().findByEmail(email.toLowerCase());
            Panier panier = factory.getPanierProxy().getPanierByCompteId(compte.getId());
            List<Contenu> contenuList = factory.getContenuProxy().findByPanierId(panier.getId());
            for (Contenu contenu : contenuList) {
                factory.getContenuProxy().delete(contenu.getId());
            }
            PaymentServices paymentServices = new PaymentServices();
            commande = factory.getCommandeProxy().getCommande(commandeId);
            payment = paymentServices.executePayment(paymentId, PayerID);
            payerInfo = payment.getPayer().getPayerInfo();
            transaction = payment.getTransactions().get(0);
            int count = 0;
            float total = 0;
            gm.generateCommande(commande, count, total, factory);
            commande.setStatutId(2);
            factory.getCommandeProxy().update(commande);
            if (ActionContext.getContext().getSession().get("email") != null) {
                countPanier = gm.generateCountPanier(factory
                        , (String) ActionContext.getContext().getSession().get("email"));
            }
            categorieList = factory.getCategorieProxy().findByDispo(true);
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            return ActionSupport.ERROR;
        }
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Integer getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(Integer commandeId) {
        this.commandeId = commandeId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public String getPayerID() {
        return PayerID;
    }

    public void setPayerID(String payerID) {
        PayerID = payerID;
    }

    public PayerInfo getPayerInfo() {
        return payerInfo;
    }

    public void setPayerInfo(PayerInfo payerInfo) {
        this.payerInfo = payerInfo;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Integer getCountPanier() {
        return countPanier;
    }

    public void setCountPanier(Integer countPanier) {
        this.countPanier = countPanier;
    }

    public List<Categorie> getCategorieList() {
        return categorieList;
    }

    public void setCategorieList(List<Categorie> categorieList) {
        this.categorieList = categorieList;
    }

    public String getLivraison() {
        return livraison;
    }

    public void setLivraison(String livraison) {
        this.livraison = livraison;
    }
}
