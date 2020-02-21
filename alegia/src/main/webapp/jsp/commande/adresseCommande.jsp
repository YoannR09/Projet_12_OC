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
        <label class="textTop">Adresse de livraison</label>
        <div class="col-12 container border shadow p-3 mb-5 bg-white rounded" id="cadreArticle">
            <div style="display: flex;justify-content: space-between;width: 100%">
                    <span style="font-size:0.9em;font-weight: bold">
                Adresse de livraison lié à votre compte
                        </span>
                <button class="btn btn-dark" style="font-size: 0.7em" id="selectionAdresseCompte">
                    Sélectionner
                </button>
            </div>
            <div id="cadreAdresseCompte" style="margin: 10px">
                <em>Ville  :  </em>
                <span class="spanAdresse">  <s:property value="adresse.ville"/></span><br/>
                <em>Code postal  :  </em>
                <span class="spanAdresse">  <s:property value="adresse.codePostal"/></span><br/>
                <em>Numéro  :  </em>
                <span class="spanAdresse">  <s:property value="adresse.numero"/></span><br/>
                <em>Rue  :  </em>
                <span class="spanAdresse">  <s:property value="adresse.rue"/></span><br/>
                <em>Information  :  </em>
                <span class="spanAdresse">  <s:property value="adresse.info"/></span><br/>
                <span style="position: absolute;right: 10px;bottom: 10px">
                <s:a action="doAdresseCompteCommande" class="btn btn-dark" style="font-size: 0.8em;">
                    <s:param name="commandeId" value="commandeId"/>
                    Confirmer</s:a>
                    </span>
            </div>
        </div>
        <div class="col-12 container border shadow p-3 mb-5 bg-white rounded" id="cadreAutreAdresse">
            <div style="display: flex;justify-content: space-between;width: 100%">
                    <span style="font-size:0.9em;font-weight: bold">
                Nouvelle adresse de livraison
                        </span>
                <button class="btn btn-dark btn-small" style="font-size: 0.7em" id="selectionAdresseNouvelle">
                    Sélectionner
                </button>
            </div>
            <div style="margin: 10px" id="cadreNouvelleAdresse">
                <s:form id="doNewAdresseCommande" action="" >
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
<footer>
    <%@ include file="../_include/footer.jsp"%>
</footer>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
    $(function() {
        $('#cadreNouvelleAdresse').hide();
        $('#selectionAdresseCompte').hide();
        $('#commandeId').hide();
    });

    $(document).on('click', '#selectionAdresseNouvelle', function () {
        $('#selectionAdresseNouvelle').hide();
        $('#selectionAdresseCompte').show();
        $('#cadreAdresseCompte').hide();
        $('#cadreNouvelleAdresse').show();
    });

    $(document).on('click', '#selectionAdresseCompte', function () {
        $('#selectionAdresseCompte').hide();
        $('#selectionAdresseNouvelle').show();
        $('#cadreAdresseCompte').show();
        $('#cadreNouvelleAdresse').hide();
    });
</script>
</html>
