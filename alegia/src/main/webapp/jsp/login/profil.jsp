
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
        .titre
        {
            padding-left: 10px;
            text-align: left;
            margin: 10px;
            font-weight: bold;
            border-left: orange solid 1px;
            font-size: 1.1em;
        }
        #bottom
        {
            display: flex;
            justify-content: space-around;
            width: 100%;
        }

        #cadreLog
        {
            width: 100%;
        }
        #message
        {
            color: black;
        }
        label,input
        {
            font-size: 0.7em;
        }
        .textTop
        {
            font-size: 1.3em;
            margin: 10px;
        }
        .emDif
        {
            font-size: 0.7em;
            text-align: left;
        }
        .labelDif
        {
            text-align: right;
            font-size: 0.8em;
        }
        .pText
        {
            display: flex;
            justify-content: space-between;
        }
        #changeAdresse,#changeEmail,#changeMdp
        {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<header>
    <%@ include file="../_include/header.jsp"%>
</header>
<div id="page">
    <div id="bottom" >
        <div id="cadreLogin" style="margin-top: 100px;width: 450px">
            <label class="form-check-label textTop"> Mon profil </label>
            <em id="message" ><s:actionmessage/></em>
            <div class="col-12 container border shadow p-3 mb-5 bg-white rounded" id="cadreLog">
                <h5 class="titre"> Informations </h5>
                <div class="col-12" style="text-align: left;  margin: 10px" >
                    <span class="label label-info"></span>
                </div>
                <div style="width: 100% ;display: flex;justify-content: space-around;">
                    <div  style="padding: 10px;width: 100%">
                        <p class="pText">
                            <em class="emDif"> Adresse électronique : </em>
                            <label class="labelDif"><s:property value="compte.email"/></label></p>
                        <div style="display: flex;justify-content: space-around;">
                            <button type="button" class="btn btn-dark" style="font-size:0.6em;" id="btnEmail">
                                Modifier l'adresse électronique
                            </button>
                        </div>
                        <div id="changeEmail">
                            <s:form action="doChangeEmail">
                                <div class="form-group" style="margin: 20px;">
                                    <label for="inputEmail">Nouvelle adresse électronique</label>
                                    <input name="email" type="text" class="form-control" id="inputEmail" required>
                                </div>
                                <div class="form-group " style="margin: 20px">
                                    <label for="inputEmailVerif">Confirmation de l'adresse</label>
                                    <input name="verif" type="text"
                                           class="form-control" id="inputEmailVerif" required>
                                </div>
                                <div style="display: flex;justify-content: space-between">
                                    <button type="button" style="font-size: 0.6em"
                                            class="btn btn-secondary btnClose" id="btnCloseEmail">Fermer</button>
                                    <button type="submit" style="font-size: 0.6em"
                                            class="btn btn-success">Changer</button>
                                </div>
                            </s:form>
                        </div>
                        <p class="pText">
                            <em class="emDif"> Nom : </em>
                            <label class="labelDif"><s:property value="compte.nom"/></label>
                        </p>
                        <p class="pText">
                            <em class="emDif"> Prénom : </em>
                            <label class="labelDif"><s:property value="compte.prenom"/></label>
                        </p>
                        <p class="pText">
                            <em class="emDif"> Numéro de téléphone : </em>
                            <label class="labelDif"><s:property value="compte.numeroTelephone"/></label>
                        </p>
                    </div>
                </div>
                <div style="display: flex;justify-content: space-around;">
                    <button type="button" class="btn btn-dark"  style="font-size:0.6em;" id="btnMdp">
                        Changer de mot de passe
                    </button>
                </div>
                <div id="changeMdp">
                    <s:form action="doChangeMdp">
                        <div class="form-group" style="margin: 20px;">
                            <label for="inputMdp">Nouveau mot de passe</label>
                            <input name="motDePasse" type="password" class="form-control" id="inputMdp" required>
                        </div>
                        <div class="form-group " style="margin: 20px">
                            <label for="inputMdpVerif">Confirmation du mot de passe</label>
                            <input name="verif" type="password" class="form-control" id="inputMdpVerif" required>
                        </div>
                        <div style="display: flex;justify-content: space-between">
                            <button type="button" style="font-size: 0.6em" id="btnCloseMdp"
                                    class="btn btn-secondary btnClose" >Fermer</button>
                            <button type="submit" style="font-size: 0.6em"
                                    class="btn btn-success">Changer</button>
                        </div>
                    </s:form>
                </div>
                <h5 class="titre"> Adresse </h5>
                <div  style="width: 100%;display: flex;justify-content: space-around">
                    <div style="padding: 10px;width: 100%">
                        <p class="pText">
                            <em class="emDif"> Numero : </em>
                            <label class="labelDif">
                                <s:property value="compte.adresse.numero"/>
                            </label>
                        </p>
                        <p class="pText">
                            <em class="emDif"> Rue : </em>
                            <label class="labelDif"><s:property value="compte.adresse.rue"/></label>
                        </p>
                        <p class="pText">
                            <em class="emDif"> Code postal : </em>
                            <label class="labelDif"><s:property value="compte.adresse.codePostal"/></label>
                        </p>
                        <p class="pText">
                            <em class="emDif"> Ville : </em>
                            <label class="labelDif"><s:property value="compte.adresse.ville"/></label>
                        </p>
                    </div>
                </div>
                <div style="display: flex;justify-content: space-around;">
                    <button id="btnAdresse" type="button" class="btn btn-dark" style="font-size:0.6em;">
                        Changer votre adresse
                    </button>
                </div>
                <div  id="changeAdresse">
                    <s:form action="doChangeAdresse">
                        <div class="form-group" style="margin: 20px;">
                            <label for="inputNumero">Numero</label>
                            <input name="numero" type="text" class="form-control" id="inputNumero"
                                   required value="<s:property value="abonne.adresse.numero"/>">
                        </div>
                        <div class="form-group" style="margin: 20px;">
                            <label for="inputRue">Rue</label>
                            <input name="rue" type="text" class="form-control" id="inputRue"
                                   required value="<s:property value="abonne.adresse.rue"/>">
                        </div>
                        <div class="form-group" style="margin: 20px;">
                            <label for="inputCodePostal">Code postal</label>
                            <input name="codePostal" type="text" class="form-control" id="inputCodePostal"
                                   required value="<s:property value="abonne.adresse.codePostal"/>">
                        </div>
                        <div class="form-group" style="margin: 20px;">
                            <label for="inputVille">Ville</label>
                            <input name="ville" type="text" class="form-control" id="inputVille"
                                   required value="<s:property value="abonne.adresse.ville"/>">
                        </div>
                        <div class="form-group" style="margin: 20px;">
                            <label for="inputPays">Pays</label>
                            <input name="pays" type="text" class="form-control" id="inputPays"
                                   required value="<s:property value="abonne.adresse.pays"/>">
                        </div>
                        <div style="display: flex;justify-content: space-between">
                            <button type="button" style="font-size: 0.6em"
                                    class="btn btn-secondary btnClose" id="btnCloseAdresse">Fermer</button>
                            <button type="submit" style="font-size: 0.6em"
                                    class="btn btn-success">Changer</button>
                        </div>
                    </s:form>
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
        $('#changeAdresse').hide();
        $('#changeEmail').hide();
        $('#changeMdp').hide();
    });


    $(document).on('click', '#btnAdresse', function () {
        $('#changeAdresse').slideDown(1000);
        $('#changeEmail').slideUp(500);
        $('#changeMdp').slideUp(500);
    });
    $(document).on('click', '#btnEmail', function () {
        $('#changeEmail').slideDown(1000);
        $('#changeAdresse').slideUp(500);
        $('#changeMdp').slideUp(500);
    });
    $(document).on('click', '#btnMdp', function () {
        $('#changeMdp').slideDown(1000);
        $('#changeEmail').slideUp(500);
        $('#changeAdresse').slideUp(500);
    });
    $(document).on('click', '.btnClose', function () {
        $('#changeMdp').slideUp(500);
        $('#changeEmail').slideUp(500);
        $('#changeAdresse').slideUp(500);
    });
</script>
</html>

