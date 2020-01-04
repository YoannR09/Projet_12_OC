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
            margin-top: 75px;
        }
        .btnUnder
        {
            margin-top : 5px;
            margin-bottom : 5px;
            font-size: 0.8em;
            width: 100%;
        }
        #radio
        {
            display: flex;
            justify-content: space-around;
            width: 300px;
            margin-bottom: 10px;
        }
        #btnRecherche
        {
            font-size: 0.8em;
        }
        #barreDeRecherche
        {
            text-align: center;
            padding: 15px;
            width: 400px;
        }

    </style>
</head>
<body>
<%@ include file="../../_include/header.jsp"%>
<div id="blocCenter" style="display: flex;justify-content: center;">
    <div class="col-8" style="padding: auto">
        <div id="barreDeRecherche" class="border" style="display:flex;justify-content: space-around;margin: auto">
            <s:form id="formulaire" action="gestionArticle" >
                <div class="input-group">
                    <div class="input-group">
                        <s:radio id="radio" name="radio" list="radioList" label="Afficher les articles " labelposition="top"
                                 style="color:gray"/>
                        <s:select name="categorieSelect" class="custom-select mb-2 mr-sm-2 mb-sm-0" id="selectCategorie" list="categorieList"
                                  listKey="nom" listValue="nom" labelposition="top" label="Dans la categorie ">
                        </s:select>
                        <s:submit type="submit" value="Rechercher" id="btnRecherche" class="btn btn-dark"></s:submit>
                    </div>
                </div>
            </s:form>
        </div>
        <section class="row" style="margin: auto;justify-content: space-around">
            <s:iterator value="articleList">
                <li class="nav-item border" style="text-align: center;margin: 15px;padding:10px;width:200px">
                    <p style="font-weight: bold;font-size: 1.1em;"><s:property value="nom"/></p>
                    <div id="carouselExampleControls" class="carousel slide" data-ride="carousel" style=" margin-bottom: 5px">
                        <div class="carousel-inner" style="height: 100%">
                            <s:iterator value="imageList" status="imageList">
                                <s:if test="%{#imageList.count == 1}">
                                    <div class="carousel-item active border" style="width: 100%;height: 100%;">
                                        <img class="d-block w-100" src="./image/<s:property value="url"/>"
                                             alt="First slide">
                                    </div>
                                </s:if>
                                <s:elseif test="%{#imageList.count > 1}">
                                    <div class="carousel-item border" style="width: 100%;height: 100%">
                                        <img class="d-block w-100 " src="./image/<s:property value="url"/>">
                                    </div>
                                </s:elseif>
                            </s:iterator>
                        </div>
                    </div>
                    <s:a action="formModifArticle" class="btn btn-dark btnUnder">
                        Modifier
                        <s:param name="articleId" value="id"/>
                    </s:a>
                    <s:if test="!disponible">
                        <s:a action="doArticleDisponible" class="btn btn-dark btnUnder">
                            Rendre disponible
                            <s:param name="articleId" value="id"/>
                            <s:param name="radio" value="radio"/>
                        </s:a>
                    </s:if>
                    <s:else>
                        <s:a action="doArticleIndosponible" class="btn btn-dark btnUnder">
                            Rendre indisponible
                            <s:param name="articleId" value="id"/>
                            <s:param name="radio" value="radio"/>
                        </s:a>
                    </s:else>
                    <s:if test="supprimable">
                        <s:a action="doArticleSupprimer" class="btn btn-danger btnUnder">
                            Supprimer
                            <s:param name="articleId" value="id"/>
                            <s:param name="radio" value="radio"/>
                        </s:a>
                    </s:if>
                    <s:else>
                        <button disabled class="btn btn-danger btnUnder" >Supprimer</button>
                    </s:else>
                </li>
            </s:iterator>
        </section>
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