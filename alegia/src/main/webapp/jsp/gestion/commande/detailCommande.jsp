<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../../_include/head.jsp"%>
    <style type="text/css">
        #blocCenter
        {
            display: flex;
            justify-content: center;
            margin-top: 100px;
        }
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
        #bottom
        {
            display: flex;
            justify-content: space-around;
            width: 100%;
        }
        #labelRecherche
        {
            left: 10px;
            font-size: 1.5em;
            margin: 10px;
            font-weight: bold;
        }
        #cadreArticle
        {
            width: 100%;
            border-radius: 4px;
            margin: 10px;
        }
        #cadreBtn
        {
            display: flex;
            justify-content: space-around;
        }
        span
        {
            font-size: 0.8em;
        }
        .lab
        {
            font-size: 0.7em;
            color: black;
        }
        .textTop
        {
            font-size: 1.3em;
            margin: 10px;
        }
        .btnType
        {
            font-size: 0.7em;
            margin: 10px;
        }

    </style>
</head>
<body>
<%@ include file="../../_include/header.jsp"%>
<div id="blocCenter" style="display: flex;justify-content: center;">
    <div class="col-9">
        <s:if test="panierVide"><label class="textTop">Vous n'avez effectué aucune commande.</label></s:if>
        <s:else><label class="textTop">DETAILS DE LA COMMANDE</label></s:else>
        <div class="col-12" style="display: flex;justify-content: space-around">
            <div style="width: 800px">
                <div class="col-12 container border shadow bg-white rounded" id="cadreArticle">
                    <div style="display: flex;justify-content: space-around;text-align: center">
                        <div style="margin-left: 20px;">
                            <em class="lab"></em><br/>
                            <span style="font-weight: bold">N° DE COMMANDE : <s:property value="commande.numero"/></span>
                        </div>
                    </div>
                    <div class="divModal" style="padding: 10px;" id="commande<s:property value="id"/>">
                        <table class="table table-bordered table-hover">
                            <thead class="thead">
                            <tr style="max-height: 10px">
                                <th scope="col">ARTICLE</th>
                                <th scope="col">PRIX</th>
                                <th scope="col">MONTANT</th>
                                <th scope="col">QUANTITE</th>
                                <th scope="col">TAILLE</th>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="commande.ligneDeCommandeList">
                                <tr>
                                    <td><s:property value="designation"/></td>
                                    <td><s:property value="prixUnitTtc"/> €</td>
                                    <td><s:property value="montantTtc"/> €</td>
                                    <td><s:property value="quantite"/></td>
                                    <td><s:property value="taille"/></td>
                                </tr>
                            </s:iterator>
                            <td>TOTAL : <s:property value="commande.prixTotal"/>  €</td>
                            </tbody>

                        </table>
                    </div>
                    <div style="display: flex;justify-content: space-between">
                        <div>
                            <em class="lab">COMMANDE EFFECTUEE LE</em><br/>
                            <span style="font-weight: bold"><s:property value="commande.date"/></span>
                        </div>
                        <div>
                            <em class="lab">CLIENT</em><br/>
                            <span style="font-weight: bold"><s:property value="compte.nom"/></span><br/>
                            <span style="font-weight: bold"><s:property value="compte.prenom"/></span>
                        </div>
                    </div>
                    <em class="lab">STATUT DE LA COMMANDE</em><br/>
                    <s:if test="commande.statut.equals('EN ATTENTE DE VALIDATION')">
                        <div class="progress border">
                            <div class="progress-bar progress-bar-striped progress-bar-animated bg-info" style="width:25%">
                                <em style="font-size: 0.6em"><s:property value="commande.statut"/></em>
                            </div>
                            <div style="width:25%;font-size: 0.5em;font-weight: bold;text-align: center">
                                EN COURS DE PREPARATION
                            </div>
                            <div style="width:25%;font-size: 0.5em;font-weight: bold;text-align: center">
                                EXPEDIEE
                            </div>
                            <div style="width:25%;font-size: 0.5em;font-weight: bold;text-align: center">
                                LIVREE
                            </div>
                        </div>
                    </s:if>
                    <s:elseif test="commande.statut.equals('EN COURS DE PREPARATION')">
                        <div class="progress border">
                            <div class="progress-bar progress-bar-striped borderProgress progress-bar-animated bg-info" style="width:50%">
                                <em style="font-size: 0.6em"><s:property value="commande.statut"/></em>
                            </div>
                            <div class="progress-bar borderProgress bg-light" style="width:25%">
                                <em style="font-size: 0.6em;color: black">EXPEDIEE</em>
                            </div>
                            <div class="progress-bar bg-light" style="width:25%">
                                <em style="font-size: 0.6em;color: black">LIVREE</em>
                            </div>
                        </div>
                    </s:elseif>
                    <s:elseif test="commande.statut.equals('EXPEDIEE')">
                        <div class="progress border">
                            <div class="progress-bar progress-bar-striped borderProgress progress-bar-animated bg-info" style="width:75%">
                                <em style="font-size: 0.6em"><s:property value="commande.statut"/></em>
                            </div>
                            <div class="progress-bar bg-light" style="width:25%">
                                <em style="font-size: 0.6em;color: black">LIVREE</em>
                            </div>
                        </div>
                    </s:elseif>
                    <s:elseif test="commande.statut.equals('ACHEVEE')">
                        <div class="progress border">
                            <div class="progress-bar progress-bar-striped borderProgress progress-bar-animated bg-info" style="width:100%">
                                <em style="font-size: 0.6em"><s:property value="commande.statut"/></em>
                            </div>
                        </div>
                    </s:elseif>
                    <div style="display:flex;width: 100%;justify-content: space-around;margin: 10px">
                        <s:if test="!commande.statut.equals('ACHEVEE')">
                            <s:a class="btn btn-dark btnType" action="doChangeStatut">
                                <s:param value="commande.id" name="commandeId"/>
                                <s:param value="commande.statut" name="statut"/>
                                Passer la commande à l'étape suivante
                            </s:a>
                        </s:if>
                        <s:else>
                            <button class="btn btn-dark btnType" disabled>Passer la commande à l'étape suivante</button>
                        </s:else>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<footer>
    <%@ include file="../../_include/footer.jsp"%>
</footer>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
    $(function() {

    });
</script>
</html>
