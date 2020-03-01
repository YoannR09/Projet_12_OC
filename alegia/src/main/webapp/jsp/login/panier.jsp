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
            background-color: whitesmoke;
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
        }
        .textTop
        {
            font-size: 1.3em;
            margin: 10px;
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
            <s:if test="panierVide"><label class="textTop">Votre panier est vide pour le moment.</label></s:if>
            <s:else><label class="textTop" style="text-align: left">Mon panier</label></s:else>
        </div>
        <div class="col-12" style="display: flex;justify-content: space-between">
            <div style="width: 350px">
                <s:iterator value="contenuList">
                    <div class="col-12 container border shadow bg-white rounded" id="cadreArticle">
                        <section class="row">
                            <div style="margin: 5px;text-align: center">
                                <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                                    <div class="carousel-inner" style="height: 100%">
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
                                            <s:elseif test="%{#imageList.count > 1}">
                                                <div class="carousel-item border" style="width: 100%;height: 100%">
                                                    <s:a action="doDetailArticle">
                                                        <s:param name="articleId" value="article.id"/>
                                                        <img class="d-block w-100 " src="./image/<s:property value="url"/>">
                                                    </s:a>
                                                </div>
                                            </s:elseif>
                                        </s:iterator>
                                    </div>
                                </div>
                            </div>
                            <div class="col" style="padding: 7px;">
                                <s:a action="doListArticleByCategorieId"
                                     style="font-size:1em;font-weight: bold;margin-top:10px;">
                                    <s:param name="articleId" value="article.id"/>
                                    <s:property value="article.nom"/></s:a><br/>
                                <em class="lab">taille :  </em>
                                <span class="spanDonne" style="font-weight: bold"><s:property value="taille.taille"/></span><br/>
                                <em class="lab">prix :  </em>
                                <span class="spanDonne" style="font-weight: bold"><s:property value="article.prixTtc"/> €</span><br/>
                                <em class="lab">quantité :  </em>
                                <span class="spanDonne" style="font-weight: bold"><s:property value="quantite"/></span><br/>
                                <span style="position: absolute;right: 10px;bottom: 10px"><s:a action="doDeleteContenu" style="font-size:0.7em;">
                                    <s:param name="contenuId" value="id"/>Supprimer</s:a></span>
                            </div>
                        </section>
                    </div>
                </s:iterator>
            </div>
            <div class="border shadow p-3 mb-5 bg-white rounded" style="width: 200px;
            padding:15px;text-align: center;background-color: whitesmoke">
                <p><span style="width: 100%;text-align: center;font-size: 1.1em;font-weight: bold">Votre commande</span></p>
                <span style="display:flex;justify-content: space-between">
                <em class="lab">nombre d'article(s) : </em><label class="lab"><s:property value="countArticle"/></label>
                    </span>

                <span style="display:flex;justify-content: space-between">
                    <em class="lab">montant ht : </em><label class="lab"><s:property value="totalPrix"/> €</label>
                </span>
                <span style="display:flex;justify-content: space-between">
                    <em class="lab">TVA à 10% : </em><label class="lab"><s:property value="tva"/> €</label>
                </span>
                <span style="display:flex;justify-content: space-between">
                    <em class="lab">livraison : </em><label class="lab">10 €</label>
                </span><br/>

                <p> <span style="display:flex;justify-content: space-between">
                    <em class="lab" style="font-size: 0.9em;">Total à payer : </em><label class="lab"><s:property value="totalPayer"/> €</label>
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

    });
</script>
</html>
