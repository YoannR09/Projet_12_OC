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
            margin-top: 75px;
        }
        .btnType
        {
            font-size: 0.8em;
            width: 200px;
            margin: 10px;
        }
        .titre
        {
            padding-left: 10px;
            text-align: left;
            margin: 10px;
            font-weight: bold;
            border-left: orange solid 1px;
            font-size: 1.1em;
        }

    </style>
</head>
<body>
<%@ include file="../_include/header.jsp"%>
<div id="blocCenter" style="display: flex;justify-content: center;">
    <div class="col-5" style="max-width: 600px">
        <h5 class="titre"> Categorie </h5>
        <div style="display: flex;justify-content: space-around">
            <s:a action="gestionCategorie" class="btn btn-dark btnType">Gestion des catégories</s:a>
            <s:a action="formAddCategorie" class="btn btn-dark btnType">Créer une catégories</s:a>
        </div>
        <br/>
        <h5 class="titre"> Article </h5>
        <div style="display: flex;justify-content: space-around">
            <s:a action="gestionArticle" class="btn btn-dark btnType">Gestion des articles</s:a>
            <s:a action="formAddArticle" class="btn btn-dark btnType">Créer un article</s:a>
        </div>
        <br/>
        <h5 class="titre"> Commande </h5>
        <div style="display: flex;justify-content: space-around">
            <s:a action="doListCommande" class="btn btn-dark btnType">En préparation
                <s:param name="statut" value="2"/>
            </s:a>
            <s:a action="doListCommande" class="btn btn-dark btnType">En cours de livraison
                <s:param name="statut" value="3"/>
            </s:a>
        </div>
        <div style="display: flex;justify-content: space-around">
            <s:a action="doListCommande" class="btn btn-dark btnType">Achevées
                <s:param name="statut" value="4"/>
            </s:a>
            <s:a action="rechercheForm" class="btn btn-dark btnType">Rechercher</s:a>
        </div>
        <br/>
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
