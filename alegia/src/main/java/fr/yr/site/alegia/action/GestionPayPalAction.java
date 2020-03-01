package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.paypal.api.payments.*;
import com.paypal.base.rest.PayPalRESTException;
import fr.yr.site.alegia.beans.Categorie;
import fr.yr.site.alegia.beans.Commande;
import fr.yr.site.alegia.configuration.Factory;
import fr.yr.site.alegia.configuration.GenerateMethod;
import fr.yr.site.alegia.paypal.OrderDetail;
import fr.yr.site.alegia.paypal.PaymentServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class GestionPayPalAction extends ActionSupport {

    // Microseervices
    @Autowired
    private Factory factory;

    private GenerateMethod gm = new GenerateMethod();

    private static final Logger logger = LogManager.getLogger();
    private static final long serialVersionUID = 1L;

    private         String              product;
    private         String              subtotal;
    private         String              shipping;
    private         String              tax;
    private         String              total;
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



    public String doPaypalPaiement(){
        commande = factory.getCommandeProxy().getCommande(commandeId);
        return ActionSupport.SUCCESS;
    }

    public String doPayPalAuth() {
        commande = factory.getCommandeProxy().getCommande(commandeId);
        float totalC = 0;
        int count = 0;
        gm.generateCommande(commande,count,totalC,factory);
        OrderDetail orderDetail = new OrderDetail(
                commande.getNumero()
                ,String.valueOf(commande.getTotal())
                ,"10"
                ,"10"
                ,String.valueOf(commande.getTotal()+20));
        try {
            PaymentServices paymentServices = new PaymentServices();
            String approvalLink = paymentServices.authorizePayment(orderDetail);
            url = approvalLink;

            return ActionSupport.SUCCESS;

        } catch (PayPalRESTException ex) {
            ex.printStackTrace();
            return ActionSupport.ERROR;
        }
    }

    public String doReview(){
        try {
            PaymentServices paymentServices = new PaymentServices();
            payment = paymentServices.getPaymentDetails(paymentId);

            payerInfo = payment.getPayer().getPayerInfo();
            commande = factory.getCommandeProxy().getCommandeByNumero(
                    payment.getTransactions().get(0).getDescription());
            transaction = payment.getTransactions().get(0);
            shippingAddress = transaction.getItemList().getShippingAddress();

            if (ActionContext.getContext().getSession().get("email") != null){
                countPanier = gm.generateCountPanier(factory
                        ,(String) ActionContext.getContext().getSession().get("email"));
            }
            int count = 0;
            float total = 0;
            gm.generateCommande(commande,count,total, factory);
            categorieList = factory.getCategorieProxy().findByDispo(true);

            return ActionSupport.SUCCESS;
        } catch (PayPalRESTException ex) {
            return ActionSupport.ERROR;
        }
    }

    public String doExecute() throws PayPalRESTException {

        PaymentServices paymentServices = new PaymentServices();
        commande = factory.getCommandeProxy().getCommande(commandeId);
        payment = paymentServices.executePayment(paymentId, PayerID);
        payerInfo = payment.getPayer().getPayerInfo();
        transaction = payment.getTransactions().get(0);
        int count = 0;
        float total = 0;
        gm.generateCommande(commande,count,total, factory);
        commande.setStatutId(2);
        factory.getCommandeProxy().update(commande);


        return ActionSupport.SUCCESS;
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
}
