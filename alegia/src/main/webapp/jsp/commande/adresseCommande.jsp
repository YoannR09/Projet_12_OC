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
        <div class="col-12 container border border-secondary shadow p-3 mb-5 bgTran rounded" id="cadreArticle">
              <span style="font-size:0.9em;font-weight: bold">
                Adresse de livraison lié au compte
                        </span>
            <div style="margin: 10px">
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
                    <s:param name="adresseId" value="adresse.id"/>
                    Sélectionner</s:a>
                    </span>
            </div>
        </div>

        <s:iterator value="adresseList">
        <div class="col-12 container border border-secondary shadow p-3 mb-5 bgTran rounded" id="cadreArticle">
        <div style="margin: 10px">
            <em>Ville  :  </em>
            <span class="spanAdresse">  <s:property value="ville"/></span><br/>
            <em>Code postal  :  </em>
            <span class="spanAdresse">  <s:property value="codePostal"/></span><br/>
            <em>Numéro  :  </em>
            <span class="spanAdresse">  <s:property value="numero"/></span><br/>
            <em>Rue  :  </em>
            <span class="spanAdresse">  <s:property value="rue"/></span><br/>
            <em>Information  :  </em>
            <span class="spanAdresse">  <s:property value="info"/></span><br/>
            <span style="position: absolute;right: 10px;bottom: 10px">
                <s:a action="doAdresseCommande" class="btn btn-dark btn-small" style="font-size: 0.8em;">
                    <s:param name="commandeId" value="commandeId"/>
                    <s:param name="adresseId" value="id"/>
                    <s:param name="livraisonId" value="adresseLivraisonId"/>
                    Sélectionner</s:a>
                    </span>
            <span style="position: absolute;right: 10px;top: 10px">
                <s:a action="doSupprimerAdresseLivraison" class="text-danger" style="font-size: 1em;">
                    <s:param name="commandeId" value="commandeId"/>
                    <s:param name="adresseId" value="id"/>
                    <s:param name="livraisonId" value="adresseLivraisonId"/>
                    <i class="fas fa-trash-alt"></i></s:a>
                    </span>
        </div>
        </div>
    </s:iterator>
    <div class="col-12 container border border-secondary shadow p-3 mb-5 bgTran rounded" id="cadreAutreAdresse">
        <div style="display: flex;justify-content: space-between;width: 100%">
                    <span style="font-size:0.9em;font-weight: bold">
                Nouvelle adresse de livraison
                        </span>
            <s:a action="doFormNewAdresseCommande" class="btn btn-dark btn-small" style="font-size: 0.8em;">
                <s:param name="commandeId" value="commandeId"/>
                <s:param name="adresseId" value="adresse.id"/>
                Créer</s:a>
        </div>
    </div>
</div>
</div>
<footer>
    <%@ include file="../_include/footer.jsp"%>
</footer>
</body>
</html>
