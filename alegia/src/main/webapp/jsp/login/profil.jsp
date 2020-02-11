
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

        #bottom
        {
            display: flex;
            justify-content: space-around;
            width: 100%;
        }
        #label
        {
            left: 10px;
            margin-top: 10px;
            margin-left: 10px;
            font-weight: bold;
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
    </style>
</head>
<body>
<header>
    <%@ include file="../_include/header.jsp"%>
</header>
<div id="page">
    <div id="bottom" >
        <div id="cadreLogin" style="margin-top: 100px;width: 350px">
            <label class="form-check-label textTop"> Connectez-vous </label>
            <em id="message" ><s:actionmessage/></em>
            <div class="col-12 container border shadow p-3 mb-5 bg-white rounded" id="cadreLog">
                <div class="col-12" style="text-align: center; margin: 10px" ><span> Informations</span></div>
                <div style="width: 100% ;display: flex;justify-content: space-around;">
                    <div  style="padding: 10px;" class="col-10">
                        <p><em class="col-6" style="color: gray;"> Adresse électronique : </em> <label class="col-6"><s:property value="compte.email"/></label></p>
                        <p><button type="button" class="btn btn-outline-info" data-toggle="modal" data-target="#popUpEmail" style="font-size:0.6em; margin-left: 15px;">
                            Modifier l'adresse électronique
                        </button></p>
                        <p><em class="col-6" style="color: gray;"> Nom : </em> <label class="col-6"><s:property value="compte.nom"/></label></p>
                        <p><em class="col-6" style="color: gray;"> Prénom : </em> <label class="col-6"><s:property value="compte.prenom"/></label></p>
                        <p><em class="col-6" style="color: gray;"> Numéro de téléphone : </em> <label class="col-6"><s:property value="compte.numero"/></label></p>
                    </div>
                </div>
                <div class="col-12" style="text-align: center;  margin: 10px" ><span class="label label-info">Adresse</span></div>
                <div  style="width: 100%;display: flex;justify-content: space-around">
                    <div style="padding: 10px;" class="col-10">
                        <p><em class="col-6" style="color: gray;"> Numero : </em> <label class="col-6"><s:property value="compte.adresse.numero"/></label></p>
                        <p><em class="col-6" style="color: gray;"> Rue : </em> <label class="col-6"><s:property value="compte.adresse.rue"/></label></p>
                        <p><em class="col-6" style="color: gray;"> Code postal : </em> <label class="col-6"><s:property value="compte.adresse.codePostal"/></label></p>
                        <p><em class="col-6" style="color: gray;"> Ville : </em> <label class="col-6"><s:property value="compte.adresse.ville"/></label></p>
                    </div>
                </div>
                <div style="display: flex;justify-content: space-around;padding: 20px;">
                    <button type="button" class="btn btn-outline-info" data-toggle="modal" data-target="#popUpMdp" style="font-size:0.6em;">
                        Changer de mot de passe
                    </button>
                    <button type="button" class="btn btn-outline-info" data-toggle="modal" data-target="#popUpAdresse" style="font-size:0.6em;">
                        Changer votre adresse
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<footer>
    <%@ include file="../_include/footer.jsp"%>
</footer>

<!-- Modal -->
<div class="modal fade" id="popUpMdp" tabindex="-1" role="dialog" aria-labelledby="popUpMdp" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="popUpMdpTitle">Changer votre mot de passe</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <s:form action="doChangeMdp">
                    <div class="form-group" style="margin: 20px;">
                        <label for="inputMdp">Nouveau mot de passe</label>
                        <input name="newMdp" type="password" class="form-control" id="inputMdp" required>
                    </div>
                    <div class="form-group " style="margin: 20px">
                        <label for="inputMdpVerif">Confirmation du mot de passe</label>
                        <input name="newMdpVerif" type="password" class="form-control" id="inputMdpVerif" required>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">Annuler</button>
                        <button type="submit" class="btn btn-outline-info">Changer</button>
                    </div>
                </s:form>
            </div>

        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="popUpEmail" tabindex="-1" role="dialog" aria-labelledby="popUpEmail" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="popUpEmailTitle">Changer votre adresse électronique</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <s:form action="doChangeEmail">
                    <div class="form-group" style="margin: 20px;">
                        <label for="inputEmail">Nouvelle adresse électronique</label>
                        <input name="newEmail" type="text" class="form-control" id="inputEmail" required>
                    </div>
                    <div class="form-group " style="margin: 20px">
                        <label for="inputEmailVerif">Confirmation de l'adresse</label>
                        <input name="newEmailVerif" type="text" class="form-control" id="inputEmailVerif" required>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">Annuler</button>
                        <button type="submit" class="btn btn-outline-info">Changer</button>
                    </div>
                </s:form>
            </div>

        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="popUpAdresse" tabindex="-1" role="dialog" aria-labelledby="popUpAdresse" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="popUpAdresseTitle">Changer votre adresse</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <s:form action="doChangeAdresse">
                    <div class="form-group" style="margin: 20px;">
                        <label for="inputNumero">Numero</label>
                        <input name="numero" type="text" class="form-control" id="inputNumero" required value="<s:property value="abonne.adresse.numero"/>">
                    </div>
                    <div class="form-group" style="margin: 20px;">
                        <label for="inputRue">Rue</label>
                        <input name="rue" type="text" class="form-control" id="inputRue" required value="<s:property value="abonne.adresse.rue"/>">
                    </div>
                    <div class="form-group" style="margin: 20px;">
                        <label for="inputCodePostal">Code postal</label>
                        <input name="codePostal" type="text" class="form-control" id="inputCodePostal" required value="<s:property value="abonne.adresse.codePostal"/>">
                    </div>
                    <div class="form-group" style="margin: 20px;">
                        <label for="inputVille">Ville</label>
                        <input name="ville" type="text" class="form-control" id="inputVille" required value="<s:property value="abonne.adresse.ville"/>">
                    </div>
                    <div class="form-group" style="margin: 20px;">
                        <label for="inputPays">Pays</label>
                        <input name="pays" type="text" class="form-control" id="inputPays" required value="<s:property value="abonne.adresse.pays"/>">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">Annuler</button>
                        <button type="submit" class="btn btn-outline-info">Changer</button>
                    </div>
                </s:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>

