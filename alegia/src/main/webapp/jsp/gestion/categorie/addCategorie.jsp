<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../../_include/head.jsp"%>
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
    <%@ include file="../../_include/header.jsp"%>
</header>
<div id="page">
    <div id="bottom" >
        <div id="cadreLogin">
            <label class="form-check-label" id="label"> Ajouter une catégorie </label>
            <em id="message" ><s:actionmessage/></em>
            <div class="col-12 container" id="cadreLog">
                <s:form action="addCategorie">
                    <div class="form-group" style="margin: 20px;">
                        <label for="inputNom" style="color: black;">Nom de la catégorie</label>
                        <input name="nom" type="text" class="form-control" id="inputNom" required>
                    </div>
                    <div class="form-group " style="margin: 20px">
                        <label for="inputLabelle" style="color: black;">Labelle de la catégorie</label>
                        <textarea name="labelle" rows="3" class="form-control" id="inputLabelle" required></textarea>
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
    <%@ include file="../../_include/footer.jsp"%>
</footer>
</body>
</html>