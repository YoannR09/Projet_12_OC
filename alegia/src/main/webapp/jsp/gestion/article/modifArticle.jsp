<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
        #cadre
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
        .wwFormTable
        {
            width: 100%;
        }
    </style>
</head>
<body>
<header>
    <%@ include file="../../_include/header.jsp"%>
</header>
<div id="page">
    <div id="bottom" >
        <div id="cadre" class="shadow p-3 mb-5 bg-white rounded">
            <label class="form-check-label" id="label"> Modifier l'article </label>
            <div class="col-12 container" id="cadreLog">
                <div class="col-12">
                    <div id="carouselExampleControls" class="carousel slide" data-ride="carousel" style=" margin-bottom: 5px">
                        <div class="carousel-inner" style="height: 100%">
                            <s:iterator value="article.imageList" status="imageList">
                                <s:if test="%{#imageList.count == 1}">
                                    <div class="carousel-item active border" style="width: 100%;height: 100%;">
                                        <img class="d-block w-100" src="./image/<s:property value="url"/>"
                                             alt="First slide">
                                    </div>
                                </s:if>
                                <s:elseif test="%{#imageList.count > 1}">
                                    <div class="carousel-item border" style="width: 100%;height: 100%">
                                        <img class="d-block w-100 " src="./image/<s:property value="url"/>">
                                    </div>
                                </s:elseif>
                            </s:iterator>
                        </div>
                        <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                            <span style="border-radius: 25px;background-color: orange;" class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                            <span style="border-radius: 25px;background-color: orange;" class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </div>
                <div style="display:flex;justify-content: center;margin-top: 15px;width: 100%">
                    <button type="button" class="btn btn-dark" data-toggle="modal"
                            data-target="#exampleModalCenter" style="width: 200px">Ajouter une image</button>
                </div>
                <div style="display:flex;justify-content: center;margin-top: 15px;width: 100%">
                    <button type="button" class="btn btn-dark" data-toggle="modal"
                            data-target="#modalRetirer" style="width: 200px">Retirer une image</button>
                </div>
            </div>

                <div class="form-group" style="margin: 20px;">
                    <label for="inputNom" style="color: black;">Nom de l'article</label>
                    <input name="nom" type="text" class="form-control" id="inputNom" value="<s:property value="article.nom"/>" required>
                </div>
                <div class="form-group " style="margin: 20px">
                    <label for="inputDescription" style="color: black;">Description de l'article</label>
                    <textarea name="description" rows="3" class="form-control" id="inputDescription" required><s:property value="article.description"/></textarea>
                </div>
                <div class="form-group " style="margin: 20px">
                    <label for="prixHt" style="color: black;">Prix HT</label>
                    <input name="prixHt" class="form-control" value="<s:property value="article.prixHt"/>" id="prixHt">
                </div>
                <div class="form-group " style="margin: 20px">
                    <label for="prixTtc" style="color: black;">Prix TTC</label>
                    <input name="prixTtc" class="form-control" id="prixTtc" value="<s:property value="article.prixTtc"/>">
                </div>
                <div style="display: flex;justify-content: center;margin: 10px">
                    <button type="submit" style="font-size: 0.8em" class="btn btn-dark">Confirmer</button>
                </div>

        </div>
    </div>
</div>
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered " role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Ajouter une image</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" style="text-align: center;">
                <s:form action="doAddImage" method="post" enctype="multipart/form-data">
                    <input value="Description de l'image" style="width: 100%" name="descriptionImage"/>
                    <input id="idArticle" name="articleId" value="<s:property value="article.id"/>">
                    <input type = "file" name = "myFile" />
                    <s:submit value="Confirmer" />
                </s:form>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modalRetirer" tabindex="-1" role="dialog" aria-labelledby="modalRetirer" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered " role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" >Supprimer une image</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" style="text-align: center;">
                <s:iterator value="article.imageList" status="imageList">
                    <s:if test="!url.equals('indisponible.jpg')">
                        <div style="display: flex;justify-content: center;border-bottom: 1px darkslategrey solid">
                            <img src="./image/<s:property value="url"/>" class="rounded mx-auto d-block"
                                 alt="<s:property value="description"/>" style="width: 100px;height: 100px">
                            <s:a action="doDeleteImage" class="btn btn-red" style="font-size:1em;margin:auto">
                                <s:param name="imageId" value="id" />
                                <s:param name="articleId" value="article.id" />
                                <i class="fas fa-trash-alt"></i></s:a>
                        </div>
                    </s:if>
                    <s:else>
                        Aucune image disponible
                    </s:else>
                </s:iterator>
            </div>
        </div>
    </div>
</div>
</div>
<footer>
    <%@ include file="../../_include/footer.jsp"%>
</footer>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
    $(function() {
        $('#idArticle').hide();
    });
</script>
</html>
