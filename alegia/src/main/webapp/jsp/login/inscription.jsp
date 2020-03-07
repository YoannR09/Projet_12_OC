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
        #cadreLogin
        {
            color: black;
            margin-top: 100px;
            margin-bottom: 20px;
            width: 350px;
        }
        #bottom
        {
            display: flex;
            justify-content: space-around;
            width: 100%;
        }
        label,input
        {
            font-size: 0.7em;
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
        .textTop
        {
            font-size: 1.3em;
            margin: 10px;
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
            <label class="form-check-label textTop"> S'inscrire </label>
            <div class="col-12 container border border-secondary shadow p-3 mb-5 bgTran rounded" id="cadreLog">
                <s:form action="doInscription" id="form">
                    <div class="form-group" style="margin: 20px;">
                        <label for="inputNom" style="color: black;">Nom</label>
                        <input name="nom" type="text" class="form-control" id="inputNom" required>
                    </div>
                    <div class="form-group" style="margin: 20px;">
                        <label for="inputPrenom" style="color: black;">Prénom</label>
                        <input name="prenom" type="text" class="form-control" id="inputPrenom" required>
                    </div>
                    <div class="form-group" style="margin: 20px;">
                        <label for="numeroTelephone" style="color: black;">Numéro de téléphone</label>
                        <input name="numeroTelephone" type="tel" class="form-control"
                               id="numeroTelephone"  pattern="[0-9]{10}" required>
                    </div>
                    <div class="form-group" style="margin: 20px;">
                        <label for="email" style="color: black;">Adresse éléctronique</label>
                        <input name="email"  pattern="[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" size="30"
                               class="form-control" id="email" required>
                    </div>
                    <div class="form-group" style="margin: 20px;">
                        <label for="verifEmail" style="color: black;">Confirmer l'adresse éléctronique</label>
                        <input type="text" pattern="[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2, 4}$" size="30"
                               name="verifEmail" class="form-control"
                               id="verifEmail" required  autocomplete="new-email">
                    </div>
                    <div class="form-group " style="margin: 20px">
                        <label for="motDePasse" style="color: black;">Mot de passe</label>
                        <input name="motDePasse" pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*\d)^[a-zA-Z\d]{6,15}$"
                               type="password" class="form-control" id="motDePasse" required>
                        <em style="font-size: 0.6em">1 majuscule, 1 chiffre, 6-15 charactères</em>
                    </div>
                    <div class="form-group " style="margin: 20px">
                        <label for="verifMdp" style="color: black;">Confirmer le mot de passe</label>
                        <input autocomplete="off" type="password" name="verifMdp" class="form-control"
                               id="verifMdp" required>
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
                        <textarea name="info"  class="form-control"  rows="3"></textarea>
                    </div>

                    <div style="display: flex;justify-content:space-around;padding-top: 25px">
                        <button type="submit" style="font-size: 0.7em" class="btn btn-dark" id="btnSub">Confirmer</button>
                    </div>
                </s:form>
            </div>
        </div>
    </div>
</div>
<footer>
    <%@ include file="../_include/footer.jsp"%>
</footer>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
    $(document).ready(function () {
        $('#verifMdp').bind('paste', function (e) {
            e.preventDefault();
        });
        $('#verifEmail').bind('paste', function (e) {
            e.preventDefault();
        });
    });
    $(window).click(function() {
        $('#verifEmail').attr({
            'pattern': $('#email').val()
        });
        $('#verifMdp').attr({
            'pattern': $('#motDePasse').val()
        });
        if($('#motDePasse').val() != $('#verifMdp').val()){
            $('#verifMdp').removeClass('is-valid');
            $('#verifMdp').addClass('is-invalid');
        }else if ($('#motDePasse').val() == $('#verifMdp').val() && $('#motDePasse').val() != '') {
            $('#verifMdp').removeClass('is-invalid');
            $('#verifMdp').addClass('is-valid');
        }
        if($('#email').val() != $('#verifEmail').val()){
            $('#verifEmail').removeClass('is-valid');
            $('#verifEmail').addClass('is-invalid');
        }else if ($('#email').val() == $('#verifEmail').val() && $('#email').val() != '') {
            $('#verifEmail').removeClass('is-invalid');
            $('#verifEmail').addClass('is-valid');
        }
        var reg = new RegExp($('#email').attr("pattern"));
        if (reg.test($('#email').val())){
            $('#email').removeClass('is-invalid');
            $('#email').addClass('is-valid');
        }else if (!reg.test($('#email').val()) && $('#email').val() != ''){
            $('#email').removeClass('is-valid');
            $('#email').addClass('is-invalid');
        }

        var regPhone = new RegExp($('#numeroTelephone').attr("pattern"));
        if (regPhone.test($('#numeroTelephone').val())){
            $('#numeroTelephone').removeClass('is-invalid');
            $('#numeroTelephone').addClass('is-valid');
        }else if (!regPhone.test($('#numeroTelephone').val()) && $('#numeroTelephone').val() != ''){
            $('#numeroTelephone').removeClass('is-valid');
            $('#numeroTelephone').addClass('is-invalid');
        }

        var regMdp = new RegExp($('#motDePasse').attr("pattern"));
        if (regMdp.test($('#motDePasse').val())){
            $('#motDePasse').removeClass('is-invalid');
            $('#motDePasse').addClass('is-valid');
        }else if (!regMdp.test($('#motDePasse').val()) && $('#motDePasse').val() != ''){
            $('#motDePasse').removeClass('is-valid');
            $('#motDePasse').addClass('is-invalid');
        }
    });
</script>
</body>
</html>
