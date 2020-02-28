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
        <label class="textTop">Sélectionnez votre mode de paiement</label>
        <div class="col-12 container border shadow p-3 mb-5 bg-white rounded" id="cadreArticle">
            <div style="display: flex;justify-content: space-between">
                <div style="text-align: center">
                    <p><i class="fab fa-paypal"></i>  Paypal</p>
                    <s:a action="authorize_payment" class="btn btn-dark" style="font-size: 0.8em;">
                        <s:param name="commandeId" value="commande.id"/>
                        Sélectionner</s:a>
                </div>
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
