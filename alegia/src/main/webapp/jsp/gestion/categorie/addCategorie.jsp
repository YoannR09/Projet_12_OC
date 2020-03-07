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
        body
        {
            margin:0;
            padding:0;
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
</div>
<footer>
    <%@ include file="../../_include/footer.jsp"%>
</footer>
</body>
</html>
