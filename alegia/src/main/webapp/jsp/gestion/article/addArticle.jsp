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
            <label class="form-check-label" id="label"> Ajouter un article </label>
            <em id="message" ><s:actionmessage/></em>
            <div class="col-12 container border shadow bg-white" id="cadreLog">
                <s:form action="doAddArticle" enctype="multipart/form-data" method="POST" namespace="/">
                    <div class="form-group" style="margin: 20px;">
                        <label for="inputNom" style="color: black;">Nom de l'article</label>
                        <input name="nom" type="text" class="form-control" id="inputNom" required>
                    </div>
                    <div class="form-group " style="margin: 20px">
                        <label for="inputDescription" style="color: black;">Description de l'article</label>
                        <textarea name="description" rows="3" class="form-control" id="inputDescription" required></textarea>
                    </div>
                    <div class="form-group " style="margin: 20px">
                        <label for="prixHt" style="color: black;">Prix HT</label>
                        <input name="prixHt" class="form-control" type="number" value="0" id="prixHt">
                    </div>
                    <div class="form-group " style="margin: 20px">
                        <label for="prixTtc" style="color: black;">Prix TTC</label>
                        <input name="prixTtc" class="form-control" type="number" value="0" id="prixTtc">
                    </div>
                    <div class="form-group " style="margin: 20px">
                        <label for="selectCategorie" style="color: black;">Sélectionner la catégorie</label>
                        <select name="categorieSelect" class="custom-select mb-2 mr-sm-2 mb-sm-0" id="selectCategorie">
                            <s:iterator value="categorieList">
                                <option><s:property value="nom"/></option>
                            </s:iterator>
                        </select>
                    </div>
                    <label style="color: black;margin-left: 20px">Sélectionner les tailles</label>
                    <s:checkboxlist list="tailleList" name="tailleSelect"
                                    listLabelKey="taille" style="width:51%" listKey="taille"/>

                    <div style="display: flex;justify-content:space-around;padding-top: 25px">
                        <s:submit value="Confimer" type="submit" style="font-size: 0.7em" class="btn btn-dark"></s:submit>
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
