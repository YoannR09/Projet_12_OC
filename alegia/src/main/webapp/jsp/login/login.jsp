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
            <div class="col-12 container border border-secondary shadow p-3 mb-5 bgTran rounded" id="cadreLog">
                <s:form action="doLogin">
                    <div class="form-group" style="margin: 20px;">
                        <label for="inputEmail" >Adresse électronique</label>
                        <input name="email" type="text" class="form-control" id="inputEmail" required>
                    </div>
                    <div class="form-group " style="margin: 20px">
                        <label for="inputPassword" >Mot de passe</label>
                        <input name="motDePasse" type="password" class="form-control" id="inputPassword" required>
                    </div>
                    <div style="display: flex;justify-content: space-between;padding-top: 25px">
                        <s:a action="doInscription" style="font-size: 0.7em" class="btn btn-dark">S'inscrire</s:a>
                        <s:a action="doNouveauMdp" style="font-size: 0.7em;color:orange;">Mot de passe oublié</s:a>
                        <button type="submit" style="font-size: 0.7em" class="btn btn-dark">Connexion</button>
                    </div>
                </s:form>
            </div>
        </div>
    </div>
</div>
<footer id="footer">
    <%@ include file="../_include/footer.jsp"%>
</footer>
</body>
</html>
