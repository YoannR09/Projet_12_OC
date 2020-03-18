<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../../_include/head.jsp"%>
    <style type="text/css">
        #blocCenter
        {
            display: flex;
            justify-content: center;
            margin-top: 100px;
        }
        .textTop
        {
            font-size: 1.3em;
            margin: 10px;
        }

        #cadreLog
        {
            width: 100%;
            padding: 10px ;
            margin-bottom: 10px;
        }
        label
        {
            font-size: 0.8em;
        }
        .wwFormTable
        {
            width: 100%;
        }
        tbody
        {
            width: 100%;
            text-align: center;
        }
    </style>
</head>
<body>
<header>
    <%@ include file="../../_include/header.jsp"%>
</header>

<div id="blocCenter" style="display: flex;justify-content: center;">
    <div class="col-9" style="max-width: 600px">
        <label class="textTop">Ajouter un article</label>
        <div class="col-12" style="display: flex;justify-content: space-around">
            <div style="width: 500px">
                <div class="col-12 container border border-secondary shadow bgTran" id="cadreLog">
                    <s:form action="doAddArticle" enctype="multipart/form-data" method="POST" namespace="/">
                        <div class="form-group" style="margin: 20px;">
                            <label for="inputNom" style="color: black;">Nom de l'article</label>
                            <input name="nom" type="text" class="form-control" id="inputNom" required>
                        </div>
                        <div class="form-group" style="margin: 20px;">
                            <label for="inputRef" style="color: black;">Réference</label>
                            <input name="reference" type="text" class="form-control" id="inputRef" required>
                        </div>
                        <div class="form-group " style="margin: 20px">
                            <label for="inputDescription" style="color: black;">Description de l'article</label>
                            <textarea name="description" rows="3" class="form-control" id="inputDescription" required></textarea>
                        </div>
                        <div class="form-group " style="margin: 20px">
                            <label for="prix" style="color: black;">Prix</label>
                            <input name="prix" class="form-control" id="prix">
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
                        <s:checkboxlist list="tailleList" name="tailleSelect" labelposition="top"
                                        listLabelKey="taille" listKey="taille" />

                        <div style="display: flex;justify-content:space-around;padding-top: 10px">
                            <s:submit value="Confimer" type="submit" style="font-size: 0.7em;margin:15px" class="btn btn-dark"></s:submit>
                        </div>
                    </s:form>
                </div>
            </div>
        </div>
    </div>
</div>
<footer id="footer">
    <%@ include file="../../_include/footer.jsp"%>
</footer>
</body>
</html>
