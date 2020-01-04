<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../_include/head.jsp"%>
    <style type="text/css">
        #page {
            display: flex;
            justify-content: space-around;
        }
        body
        {
            margin:0;
            padding:0;
        }
        #cadreLogin
        {
            color: black;
            margin-top: 100px;
            margin-bottom: 20px;
            border-radius: 10px;
            border: 1px lightgrey solid;
            width: 350px;
        }
        #bottom
        {
            display: flex;
            justify-content: space-around;
            width: 100%;
        }
        #label
        {
            left: 10px;
            font-size: 1.2em;
            margin-top: 10px;
            margin-left: 10px;
            font-weight: bold;
            color: black;
        }
        #cadreLog
        {
            width: 100%;
            padding: 10px ;
            margin-bottom: 10px;
        }
        #message
        {
            float: right;
            font-size: 1.1em;
            margin-top: 15px;
            color: white;
        }
        label
        {
            font-size: 0.8em;
        }
    </style>
</head>
<body>
<header>
    <%@ include file="../_include/header.jsp"%>
</header>
<div id="page">
    <div id="bottom" >
        <div id="cadreLogin">
            <label class="form-check-label" id="label"> S'inscrire </label>
            <em id="message" ><s:actionmessage/></em>
            <div class="col-12 container" id="cadreLog">
                <s:form action="doInscription">
                    <div class="form-group" style="margin: 20px;">
                        <label for="inputNom" style="color: black;">Nom</label>
                        <input name="nom" type="text" class="form-control" id="inputNom" required>
                    </div>
                    <div class="form-group" style="margin: 20px;">
                        <label for="inputPrenom" style="color: black;">Prénom</label>
                        <input name="prenom" type="text" class="form-control" id="inputPrenom" required>
                    </div>
                    <div class="form-group" style="margin: 20px;">
                        <label for="inputNumeroTelephone" style="color: black;">Numéro de téléphone</label>
                        <input name="numeroTelephone" type="text" class="form-control" id="inputNumeroTelephone" required>
                    </div>
                    <div class="form-group" style="margin: 20px;">
                        <label for="inputEmail" style="color: black;">Adresse éléctronique</label>
                        <input name="email" type="text" class="form-control" id="inputEmail" required>
                    </div>
                    <div class="form-group" style="margin: 20px;">
                        <label for="inputVerifEmail" style="color: black;">Confirmer l'adresse éléctronique</label>
                        <input type="text" class="form-control" id="inputVerifEmail" required>
                    </div>
                    <div class="form-group " style="margin: 20px">
                        <label for="inputPassword" style="color: black;">Mot de passe</label>
                        <input name="motDePasse" type="password" class="form-control" id="inputPassword" required>
                    </div>
                    <div class="form-group " style="margin: 20px">
                        <label for="inputPassword" style="color: black;">Confirmer le mot de passe</label>
                        <input type="password" class="form-control" id="inputVerifPassword" required>
                    </div>

                    Votre adresse
                    <div class="form-group " style="margin: 20px">
                        <label for="inputVille" style="color: black;">Ville</label>
                        <input name="ville" type="text" class="form-control" id="inputVille" required>
                    </div>
                    <div class="form-group " style="margin: 20px">
                        <label for="inputCodePostal" style="color: black;">Code postal</label>
                        <input name="codePostal" type="text" class="form-control" id="inputCodePostal" required>
                    </div>
                    <div class="form-group " style="margin: 20px">
                        <label for="inputNumero" style="color: black;">Numéro</label>
                        <input name="numero" type="text" class="form-control" id="inputNumero" required>
                    </div>
                    <div class="form-group " style="margin: 20px">
                        <label for="inputRue" style="color: black;">Rue</label>
                        <input name="rue" type="text" class="form-control" id="inputRue" required>
                    </div>
                    <div class="form-group " style="margin: 20px">
                        <label for="inputRue" style="color: black;">Informations supplémentaire</label>
                        <textarea name="info"  class="form-control"  rows="3" required></textarea>
                    </div>

                    <div style="display: flex;justify-content:space-around;padding-top: 25px">
                        <button type="submit" style="font-size: 0.7em" class="btn btn-dark">Confirmer</button>
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
</html>