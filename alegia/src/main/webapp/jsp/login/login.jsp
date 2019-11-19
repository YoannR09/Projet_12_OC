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
        em
        {
            color : white;
        }
        *
        {
            font-family: "Microsoft YaHei UI Light";
        }

        em
        {
            color : gray;
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
            font-size: 1.2em;
            margin: 10px;
            font-weight: bold;
        }
        #cadreLog
        {
            width: 100%;
            border: darkgray 1px solid;
            padding: 20px ;
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
        <div id="cadreLogin" class="col-5">
            <label class="form-check-label" id="labelRecherche"> Connectez-vous </label>
            <em id="message" class="text-info"><s:actionmessage/></em>
            <div class="col-12 container bg-light" id="cadreLog">
                <s:form action="doLogin">
                    <div class="form-group" style="margin: 20px;">
                        <label for="inputPseudo">Pseudo/Email</label>
                        <input name="identifiant" type="text" class="form-control" id="inputPseudo" required>
                    </div>
                    <div class="form-group " style="margin: 20px">
                        <label for="inputPassword">Mot de passe</label>
                        <input name="motDePasse" type="password" class="form-control" id="inputPassword" required>
                    </div>
                    <div style="display: flex;justify-content: space-between">
                        <s:a action="formInscription" class="btn btn-outline-info">S'inscrire</s:a>
                        <button type="submit" class="btn btn-outline-info">Connexion</button>
                    </div>

                </s:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>