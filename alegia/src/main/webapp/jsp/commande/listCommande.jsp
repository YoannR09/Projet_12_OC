<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../_include/head.jsp"%>
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
            font-size: 0.7em;
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
        .borderProgress
        {
            border-right: 1px solid lightgray;

        }


    </style>
</head>
<body>
<%@ include file="../_include/header.jsp"%>
<div id="blocCenter" style="display: flex;justify-content: center;">
    <div class="col-9" style="max-width: 900px">
        <label class="textTop">Mes commandes</label>
        <div class="col-12" style="display: flex;justify-content: space-around">
            <div style="width: 800px">
                <s:if test="commandeList.size == 0">
                    <label style="font-size: 0.8em"><em>Vous n'avez pas effectué de commande pour le moment.</em></label>
                </s:if>
                <s:iterator value="commandeList">
                    <div class="col-12 container border border-secondary shadow p-3 mb-5 bgTran rounded" id="cadreArticle">
                        <section class="row">
                            <div class="col" style="display:flex;justify-content: space-around;padding: 5px">
                                <div>
                                    <em class="lab">COMMANDE EFFECTUEE LE</em><br/>
                                    <s:if test="!statut.equals('EN ATTENTE DE VALIDATION')">
                                        <span style="font-weight: bold"><s:property value="date"/></span>
                                    </s:if>
                                    <s:else>
                                        <span style="font-weight: bold">En attente</span>
                                    </s:else>
                                </div>
                                <div>
                                    <em class="lab">N° DE COMMANDE</em><br/>
                                    <span style="font-weight: bold"><s:property value="numero"/></span>
                                </div>
                                <div>
                                    <em class="lab">TOTAL</em><br/>
                                    <span style="font-weight: bold"><s:property value="totalPayer"/>  €</span>
                                </div>
                                <di>
                                    <em class="lab">STATUT</em><br/>
                                    <span style="font-weight: bold"><s:property value="statut"/></span>
                                </di>
                            </div>
                            <button class="btn btn-small btModal"
                                    id="<s:property value="id"/>"><i class="fas fa-eye"></i></button>
                        </section>
                        <div class="divModal" style="padding: 10px;border-top: 1px solid darkgray" id="commande<s:property value="id"/>">
                            <table class="table table-bordered table-hover">
                                <thead class="thead">
                                <tr style="max-height: 10px">
                                    <th scope="col">ARTICLE</th>
                                    <th scope="col">PRIX HT</th>
                                    <th scope="col">MONTANT HT</th>
                                    <th scope="col">QUANTITE</th>
                                    <th scope="col">TAILLE</th>
                                </tr>
                                </thead>
                                <tbody>
                                <s:iterator value="ligneDeCommandeList">
                                    <tr>
                                        <td><s:property value="designation"/></td>
                                        <td><s:property value="prixHt"/> €</td>
                                        <td><s:property value="montantHt"/> €</td>
                                        <td><s:property value="quantite"/></td>
                                        <td><s:property value="taille"/></td>
                                    </tr>
                                </s:iterator>
                                <td><em class="lab">Montant ht : </em><label class="lab"><s:property value="prixTotal"/> €</label></td>
                                <td><em class="lab">TVA à 10% :  </em><label class="lab"><s:property value="tva"/> €</label></td>
                                <td><em class="lab">Livraison : </em><label class="lab"><s:property value="livraison"/> €</label></td>
                                <td><em class="lab">TOTAL TTC : </em><label class="lab"><s:property value="totalPayer"/> €</label></td>
                                </tbody>
                            </table>
                            <s:if test="!statut.equals('EN ATTENTE DE VALIDATION')">
                            <div style="width: 100%">
                                <em class="lab">ADRESSE DE LIVRAISON</em><br/>
                                <div style="margin-bottom: 10px">
                                    <span style="font-weight: bold"><s:property value="adresse.codePostal"/></span>
                                    <span style="font-weight: bold">  -  </span>
                                    <span style="font-weight: bold"><s:property value="adresse.ville"/></span>
                                    <span style="font-weight: bold">  -  </span>
                                    <span style="font-weight: bold"><s:property value="adresse.numero"/></span>
                                    <span style="font-weight: bold"><s:property value="adresse.rue"/></span>
                                </div>
                            </div>
                            </s:if>
                            <em class="lab">L'AVANCEE DE VOTRE COMMANDE</em>
                            <s:if test="statut.equals('EN ATTENTE DE VALIDATION')">
                                <div class="progress border">
                                    <div class="progress-bar progress-bar-striped progress-bar-animated bg-info" style="width:25%">
                                        <em style="font-size: 0.6em"><s:property value="statut"/></em>
                                    </div>
                                    <div class="progress-bar borderProgress bg-light" style="width:25%">
                                        <em style="font-size: 0.6em;color: black">EN COURS DE PREPARATION</em>
                                    </div>
                                    <div class="progress-bar borderProgress bg-light" style="width:25%">
                                        <em style="font-size: 0.6em;color: black">EXPEDIEE</em>
                                    </div>
                                    <div class="progress-bar borderProgress bg-light" style="width:25%">
                                        <em style="font-size: 0.6em;color: black">LIVREE</em>
                                    </div>
                                </div>
                            </s:if>
                            <s:elseif test="statut.equals('EN COURS DE PREPARATION')">
                                <div class="progress border">
                                    <div class="progress-bar progress-bar-striped borderProgress progress-bar-animated bg-info" style="width:50%">
                                        <em style="font-size: 0.6em"><s:property value="statut"/></em>
                                    </div>
                                    <div class="progress-bar borderProgress bg-light" style="width:25%">
                                        <em style="font-size: 0.6em;color: black">EXPEDIEE</em>
                                    </div>
                                    <div class="progress-bar bg-light" style="width:25%">
                                        <em style="font-size: 0.6em;color: black">LIVREE</em>
                                    </div>
                                </div>
                            </s:elseif>
                            <s:elseif test="statut.equals('EXPEDIEE')">
                                <div class="progress border">
                                    <div class="progress-bar progress-bar-striped borderProgress progress-bar-animated bg-info" style="width:75%">
                                        <em style="font-size: 0.6em"><s:property value="statut"/></em>
                                    </div>
                                    <div class="progress-bar bg-light" style="width:25%">
                                        <em style="font-size: 0.6em;color: black">LIVREE</em>
                                    </div>
                                </div>
                            </s:elseif>
                            <s:elseif test="statut.equals('ACHEVEE')">
                                <div class="progress border">
                                    <div class="progress-bar progress-bar-striped borderProgress progress-bar-animated bg-info" style="width:100%">
                                        <em style="font-size: 0.6em"><s:property value="statut"/></em>
                                    </div>
                                </div>
                            </s:elseif>
                        </div>
                    </div>
                </s:iterator>
            </div>
        </div>
    </div>
</div>
<footer>
    <%@ include file="../_include/footer.jsp"%>
</footer>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
    $(function() {
        $('.divModal').hide();
    });

    $(document).on('click', '.btModal', function () {
        var id = $(this).attr("id");
        $('.btModal').attr("disabled", false);
        $(this).attr("disabled", true);
        $('.divModal').slideUp(500);
        $('#commande'+id).slideDown(1000);
    });

</script>
</html>
