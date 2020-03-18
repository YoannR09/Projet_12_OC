<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../_include/head.jsp"%>
    <style type="text/css">
        td
        {
            text-align: center;
            font-size: 0.8em;
            font-weight: bold;
        }
        th
        {
            text-align: center;
            font-size: 0.8em;
        }
        #cadreArticle,#cadreAutreAdresse
        {
            width: 100%;
            margin: 10px;
        }
        span
        {
            font-size: 0.8em;
        }
        .textTop
        {
            font-size: 1.3em;
            margin: 10px;
        }
        #blocCenter
        {
            display: flex;
            justify-content: center;
            margin-top: 75px;
        }

        label,input
        {
            font-size: 0.7em;
        }
        divInput
        {
            display: flex;
            justify-content: space-around;
        }
        em
        {
            font-size: 0.7em;
        }
        .lab
        {
            font-size: 0.7em;
        }

    </style>
</head>
<body>
<%@ include file="../_include/header.jsp"%>
<div id="blocCenter" style="display: flex;justify-content: center;">
    <div class="col-9" style="max-width: 600px">
        <label class="textTop">Votre commande</label>
        <div class="col-12 container border border-secondary shadow p-3 mb-5 bgTran rounded" id="cadreArticle">
            <div style="display: flex;justify-content: center">
                <div>
                    <table class="table table-bordered table-hover">
                        <thead class="thead">
                        <tr style="max-height: 10px">
                            <th scope="col">ARTICLE</th>
                            <th scope="col">PRIX TTC</th>
                            <th scope="col">MONTANT TTC</th>
                            <th scope="col">QUANTITE</th>
                            <th scope="col">TAILLE</th>
                        </tr>
                        </thead>
                        <tbody>
                        <s:iterator value="commande.ligneDeCommandeList">
                            <tr>
                                <td><s:property value="designation"/></td>
                                <td class="numberFont"><s:property value="prixHt"/> €</td>
                                <td class="numberFont"><s:property value="montantHt"/> €</td>
                                <td class="numberFont"><s:property value="quantite"/></td>
                                <td><s:property value="taille"/></td>
                            </tr>
                        </s:iterator>
                        <td>Montant HT<br/><label class="numberFont" style="font-size: 1em">
                            <s:property value="commande.prixTotal"/> €</label></td>
                        <td>TVA à 10%<br/><label class="numberFont" style="font-size: 1em">
                            <s:property value="commande.tva"/> €</label></td>
                        <td>Livraison<br/><label class="numberFont" style="font-size: 1em">
                            <s:property value="livraison"/> €</label></td>
                        <td>TOTAL TTC<br/><label class="numberFont" style="font-size: 0.9em">
                            <s:property value="commande.totalPayer"/> €</label></td>
                        </tbody>
                    </table>
                    <div style="width: 100%">
                        <em class="lab">ADRESSE DE LIVRAISON</em><br/>
                        <div style="margin-bottom: 10px">
                            <span class="numberFont" style="font-weight: bold"><s:property value="commande.adresse.codePostal"/></span>
                            <span style="font-weight: bold">  -  </span>
                            <span style="font-weight: bold"><s:property value="commande.adresse.ville"/></span>
                            <span style="font-weight: bold">  -  </span>
                            <span class="numberFont" style="font-weight: bold"><s:property value="commande.adresse.numero"/></span>
                            <span style="font-weight: bold"><s:property value="commande.adresse.rue"/></span>
                        </div>
                    </div>
                </div>
            </div>
            <div style="width: 100%;text-align: center">
                <s:form action="execute">
                    <input type="hidden" name="paymentId" value="<s:property value="paymentId"/>" />
                    <input type="hidden" name="PayerID" value="<s:property value="PayerID"/>" />
                    <input type="hidden" name="commandeId" value="<s:property value="commande.id"/>" />
                    <button type="submit" style="font-size: 0.7em" class="btn btn-dark">Confirmer le paiement</button>
                </s:form>
            </div>
        </div>
    </div>
</div>
<footer id="footer">
    <%@ include file="../_include/footer.jsp"%>
</footer>

</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
    $(function() {

    });
</script>
</html>
