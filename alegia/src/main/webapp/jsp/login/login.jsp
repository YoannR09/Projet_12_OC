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
            background: url(../../image/fond.png) repeat center fixed;
            -webkit-background-size: cover; /* pour anciens Chrome et Safari */
            background-size: cover; /* version standardisée */
        }
        #cadreLogin
        {
            margin-top: 100px;
            margin-bottom: 20px;
            border-radius: 10px;
            background-color: rgba(0, 0, 0, 0.40);
            width: 350px;
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
            color: white;
        }
        #cadreLog
        {
            width: 100%;
            padding: 20px ;
        }
        #message
        {
            float: right;
            font-size: 1.1em;
            margin-top: 15px;
            color: white;
        }
    </style>
</head>
<body>
<header>
    <%@ include file="../_include/header.jsp"%>
</header>
<div id="page">
    <div id="bottom" >
        <div id="cadreLogin" >
            <label class="form-check-label" id="labelRecherche"> Connectez-vous </label>
            <em id="message" ><s:actionmessage/></em>
            <div class="col-12 container" id="cadreLog">
                <s:form action="doLogin">
                    <div class="form-group" style="margin: 20px;">
                        <label for="inputPseudo" style="color: white;">Adresse éléctronique</label>
                        <input name="identifiant" type="text" class="form-control" id="inputPseudo" required>
                    </div>
                    <div class="form-group " style="margin: 20px">
                        <label for="inputPassword" style="color: white;">Mot de passe</label>
                        <input name="motDePasse" type="password" class="form-control" id="inputPassword" required>
                    </div>
                    <div style="display: flex;justify-content: space-between;padding-top: 25px">
                        <s:a action="formInscription" class="btn btn-outline-warning">S'inscrire</s:a>
                        <button type="submit" class="btn btn-outline-warning">Connexion</button>
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