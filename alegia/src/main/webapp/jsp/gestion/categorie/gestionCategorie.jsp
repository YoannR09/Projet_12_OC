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
            <s:form id="formulaire" action="gestionCategorie" >
                <div class="input-group">
                        <div class="input-group">
                            <s:radio id="radio" name="radio" list="radioList" style="color:gray" label="Afficher les catégories "
                            labelposition="top"/>
                            <s:submit type="submit" value="Rechercher" id="btnRecherche" class="btn btn-dark"></s:submit>
                        </div>
                </div>
            </s:form>
        </div>
        <section class="row" style="margin: auto;justify-content: space-around">
            <s:iterator value="categories">
                <li class="nav-item border" style="text-align: center;margin: 15px;padding:10px;width:200px">
                    <p style="font-weight: bold;font-size: 1.1em;"><s:property value="nom"/></p>
                    <em style="font-size: 0.7em">Nombre d'article(s) : </em><s:property value="countArticle"/>
                    <s:a action="" class="btn btn-dark btnUnder">
                        Rendre disponible
                        <s:param name="categorieId" value="id"/>
                    </s:a>
                    <s:a action="" class="btn btn-dark btnUnder">
                        Rendre indisponible
                        <s:param name="categorieId" value="id"/>
                    </s:a>
                    <s:if test="countArticle == 0">
                        <s:a action="" class="btn btn-danger btnUnder">
                            Supprimer
                            <s:param name="categorieId" value="id"/>
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