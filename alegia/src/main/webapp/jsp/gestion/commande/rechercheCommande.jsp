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
            height: 30px;
            margin: auto;
        }
        #barreDeRecherche
        {
            padding: 15px;
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

    </style>
</head>
<body>
<%@ include file="../../_include/header.jsp"%>
<div id="blocCenter" style="display: flex;justify-content: center;">
    <div class="col-8" style="padding: auto">
        <s:form id="formulaire" action="doRechercheCommande" >
        <div id="barreDeRecherche" class="border shadow p-3 mb-5 bg-white rounded" style="padding: auto">
            <div style="display:flex;justify-content: space-around;">
                    <div class="form-group divInput" style="margin-right: 10px" >
                        <label for="inputNom" >Nom</label>
                        <input name="nom" class="form-control" id="inputNom">
                    </div>
                    <div class="form-group divInput" style="margin-right: 10px">
                        <label for="inputPrenom" >Prénom</label>
                        <input name="prenom" class="form-control" id="inputPrenom">
                    </div>
                    <div class="form-group divInput" style="margin-right: 10px">
                        <label for="inputEmail" >Adresse éléctronique</label>
                        <input name="email" class="form-control" id="inputEmail">
                    </div>
                    <button type="submit"id="btnRecherche" class="btn btn-dark">Rechercher</button>
            </div>
        </div>
        </s:form>
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

