<%--
  Created by IntelliJ IDEA.
  User: El-ra
  Date: 28/06/2019
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
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
        *
        {
            font-family: "Microsoft YaHei UI Light";
        }

        em
        {
            color : gray;
            text-align: left;
        }
        #cadreLogin
        {
            margin-top: 50px;
            margin-bottom: 20px;
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
        #cadreLog
        {
            width: 100%;
            border: darkgray 1px solid;
        }
        label
        {
            text-align: right;
            margin-left: 15px;
            font-weight: 600;
        }
        #message
        {
            float: right;
            font-size: 1.1em;
            margin-top: 15px;
        }



    </style>
</head>
<body>
<header>
    <%@ include file="../_include/header.jsp"%>
</header>

<div id="page">
    <div id="bottom" >
        <div id="cadreLogin" class="col-xl-4 col-lg-5 col-md-6 col-sm-7">
            <label class="form-check-label" id="labelRecherche"> Inscription </label>
            <div class="col-12 container bg-light" id="cadreLog">
                <em id="message" class="text-info"><s:actionmessage/></em>
                <s:form action="doInscription">
                    <div class="form-group" style="margin: 20px;">
                        <label for="inputPseudo">Pseudo</label>
                        <input name="pseudo" type="pseudo" class="form-control" id="inputPseudo" required value="<s:property value="pseudo"/>">
                    </div>
                    <div class="form-group " style="margin: 20px">
                        <label for="inputPassword">Mot de passe</label>
                        <input name="newMdp" type="password" class="form-control" id="inputPassword" required >
                    </div>
                    <div class="form-group " style="margin: 20px">
                        <label for="inputPasswordVerif">Confirmation</label>
                        <input name="newMdpVerif" type="password" class="form-control" id="inputPasswordVerif" required>
                    </div>
                    <div class="form-group " style="margin: 20px">
                        <label for="inputNom">Nom</label>
                        <input name="nom" type="text" class="form-control" id="inputNom" required value="<s:property value="nom"/>">
                    </div>
                    <div class="form-group " style="margin: 20px">
                        <label for="inputPrenom">Prénom</label>
                        <input name="prenom" type="text" class="form-control" id="inputPrenom" required value="<s:property value="prenom"/>">
                    </div>
                    <div class="form-group " style="margin: 20px">
                        <label for="inputEmail">Adresse électronique</label>
                        <input name="newEmail" type="text" class="form-control" id="inputEmail" required value="<s:property value="newEmail"/>">
                    </div>
                    <div class="form-group " style="margin: 20px">
                        <label for="inputNumero">Numéro de télèphone</label>
                        <input name="numero" type="text" class="form-control" id="inputNumero" required value="<s:property value="numero"/>">
                    </div>
                    <div style=" margin: 20px" class="form-group">
                        <label for="selectBibliotheque">Bibliothèque favorite</label>
                        <select id="selectBibliotheque" name="bibliotheque" class="form-control">
                            <s:iterator value="bibliothequeList" status="list">
                                <option value="<s:property value="id"/>"><s:property value="nom"/></option>
                            </s:iterator>
                        </select>
                    </div>
                    <div style="text-align: right;margin: 10px;">
                        <button type="submit" class="btn btn-outline-info">Valider</button>
                    </div>
                </s:form>
            </div>
        </div>
    </div>
</div>

</body>
</html>