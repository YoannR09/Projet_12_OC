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
        }
        th
        {
            text-align: center;
            font-size: 0.8em;
        }

        #cadreArticle
        {
            width: 100%;
            border-radius: 4px;
            margin-bottom: 30px;
        }
        #cadreBtn
        {
            display: flex;
            justify-content: space-around;
        }
        .lab
        {
            font-size: 0.7em;
            font-weight: bold;
        }
        .textTop
        {
            font-size: 1.3em;
            margin: 10px;
        }
        .disableLink {
            pointer-events: none;
            cursor: default;
        }
        .spanDonne
        {
            font-size: 0.8em;
        }

    </style>
</head>
<body>
<%@ include file="../_include/header.jsp"%>
<div id="blocCenter" style="display: flex;justify-content: center;">
    <div style="width: 800px">
        <div style="text-align: left">
            <label class="textTop" style="text-align: left">Mon panier</label>
        </div>
        <div class="col-12" style="display: flex;justify-content: space-between">
            <div style="width: 350px">
                <s:if test="contenuList.size == 0">
                    <label style="font-size: 0.8em"><em>Votre panier est vide pour le moment.</em></label>
                </s:if>
                <s:iterator value="contenuList">
                    <div class="col-12 container border border-secondary shadow bgTran rounded" id="cadreArticle">
                        <section class="row">
                            <div style="margin: 5px;text-align: center">
                                <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                                    <div class="carousel-inner border rounded" style="height: 100%">
                                        <s:iterator value="article.imageList" status="imageList">
                                            <s:if test="%{#imageList.count == 1}">
                                                <div class="carousel-item active border" style="width: 150px;height: 150px;">
                                                    <s:a action="doDetailArticle">
                                                        <s:param name="articleId" value="articleId"/>
                                                        <img class="d-block w-100" src="./image/<s:property value="url"/>"
                                                             alt="First slide">
                                                    </s:a>
                                                </div>
                                            </s:if>
                                        </s:iterator>
                                    </div>
                                </div>
                            </div>
                            <div class="col" style="padding: 7px;">
                                <s:a action="doDetailArticle"
                                     style="font-size:1em;font-weight: bold;margin-top:10px;">
                                    <s:param name="articleId" value="article.id"/>
                                    <s:property value="article.nom"/></s:a><br/>
                                <span class="lab">Taille :  </span>
                                <span class="spanDonne" style="font-weight: bold"><s:property value="taille.taille"/></span><br/>
                                <span class="lab">Prix :  </span>
                                <span class="spanDonne numberFont" style="font-weight: bold"><s:property value="article.prix"/> €</span><br/>
                                <span class="lab">Quantité :  </span>
                                <span class="spanDonne numberFont" style="font-weight: bold"><s:property value="quantite"/></span>
                                <div class="badge badge-dark" style="margin-left: 10px">
                                <s:a action="doPlusContenu" class="text-light" style="font-size:0.7em;margin-right:5px">
                                    <s:param name="contenuId" value="id"/>
                                    <i class="fas fa-plus"></i></s:a>
                                <s:if test="quantite == 1">
                                    <s:a class="text-light disableLink" action="doMoinsContenu" style="font-size:0.7em;">
                                        <s:param name="contenuId" value="id"/>
                                        <i class="fas fa-minus"></i></s:a>
                                </s:if>
                                <s:else>
                                    <s:a action="doMoinsContenu" class="text-light" style="font-size:0.7em;">
                                        <s:param name="contenuId" value="id"/>
                                        <i class="fas fa-minus"></i></s:a>
                                </s:else>
                                </div>
                                <br/>
                                <span style="position: absolute;right: 10px;bottom: 10px"><s:a action="doDeleteContenu" style="font-size:0.7em;">
                                    <s:param name="contenuId" value="id"/>Supprimer</s:a></span>
                            </div>
                        </section>
                    </div>
                </s:iterator>
            </div>
            <div class="border border-secondary shadow p-3 mb-5 bgTran rounded" style="width: 230px;height: 280px;
            padding:15px;text-align: center;">
                <p><span style="width: 100%;text-align: center;font-size: 1.1em;font-weight: bold">Votre commande</span></p>
                <span style="display:flex;justify-content: space-between">
                <span class="lab">Nombre d'article(s) : </span><label class="lab numberFont"><s:property value="countArticle"/></label>
                    </span>

                <span style="display:flex;justify-content: space-between">
                    <span class="lab">Montant HT : </span><label class="lab numberFont"><s:property value="totalPrix"/> €</label>
                </span>
                <span style="display:flex;justify-content: space-between">
                    <span class="lab">TVA à 10% : </span><label class="lab numberFont"><s:property value="tva"/> €</label>
                </span>
                <span style="display:flex;justify-content: space-between">
                    <span class="lab">Livraison : </span><label class="lab numberFont"><s:property value="livraison"/> €</label>
                </span><br/>

                <p> <span style="display:flex;justify-content: space-between">
                    <span class="lab" style="font-size: 0.9em;">Total à payer TTC : </span>
                    <label class="lab numberFont" style="font-size: 0.9em"><s:property value="totalPayer"/> €</label>
                </span></p>
                <s:if test="countArticle == 0">
                    <button class="btn btn-dark" style="font-size:0.8em;" disabled>Passer la commande</button>
                </s:if>
                <s:else>
                    <s:a class="btn btn-dark" action="doCommande" style="font-size:0.8em;">Passer la commande</s:a>
                </s:else>
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
        $("#disablePlus").prop("disabled",true);
    });
</script>
</html>
