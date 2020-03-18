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
        #cadreArticle,#cadreAutreAdresse
        {
            width: 100%;
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
            height: 32px;
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
        em
        {
            font-size: 0.7em;
        }
        .spanAdresse
        {
            font-size: 0.8em;
            font-weight: bold;
        }

    </style>
</head>
<body>
<%@ include file="../_include/header.jsp"%>
<div id="blocCenter" style="display: flex;justify-content: center;">
    <div class="col-9" style="max-width: 500px">
        <label class="textTop">Nouvelle adresse de livraison</label>
        <div class="col-12 container border border-secondary shadow p-3 mb-5 bgTran rounded" id="cadreAutreAdresse">
            <div style="margin: 10px" id="cadreNouvelleAdresse">
                <s:form action="doNewAdresseCommande" >
                    <input name="commandeId" value="<s:property value="commandeId"/>" class="form-control" id="commandeId">
                    <div style="padding-bottom: 20px">
                        <div class="form-group divInput" style="margin-right: 20px;margin-left: 20px" >
                            <label for="inputVille" >Ville</label>
                            <input name="ville" class="form-control" id="inputVille">
                        </div>
                        <div class="form-group divInput" style="margin-right: 20px;margin-left: 20px">
                            <label for="inputCode" >Code postal</label>
                            <input name="codePostal" class="form-control" id="inputCode">
                        </div>
                        <div class="form-group divInput" style="margin-right: 20px;margin-left: 20px">
                            <label for="inputNumero" >Numero</label>
                            <input name="numero" class="form-control" id="inputNumero">
                        </div>
                        <div class="form-group divInput" style="margin-right: 20px;margin-left: 20px">
                            <label for="inputRue" >Rue</label>
                            <input name="rue" class="form-control" id="inputRue">
                        </div>
                        <div class="form-group divInput" style="margin-right: 20px;margin-left: 20px">
                            <label for="inputInfo" >Informations supplémentaire</label>
                            <textarea rows="3" name="info" class="form-control" id="inputInfo"></textarea>
                        </div>
                        <span style="position: absolute;right: 10px;bottom: 10px">
                                    <button type="submit"id="btnRecherche" class="btn btn-dark">Confirmer</button>
                        </span>
                    </div>
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
        $('#commandeId').hide();
    });
</script>
</html>
