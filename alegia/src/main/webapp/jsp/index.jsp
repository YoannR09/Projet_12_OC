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
    <%@ include file="./_include/head.jsp"%>
    <style type="text/css">
        #blocCenter
        {
            display: flex;
            justify-content: center;
            margin-top: 75px;
        }

        body
        {
            margin:0;
            padding:0;
        }
        .titreImage
        {
            color: darkorange;
            font-size: 1.4em;
            font-weight: bold;
            font-family: 'Odibee Sans' ;
        }
        .pImage
        {
            font-family: 'Raleway' ;
            font-size: 0.8em;
        }
        #actuArticle
        {
            border-top: orange 1px solid;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<%@ include file="./_include/header.jsp"%>
<div id="blocCenter" style="display: flex;justify-content: center;z-index: 3">
    <div class="nav-item" style="text-align: center;width: 660px;border-radius: 15px;
    box-shadow: 0px 0px 30px -8px rgba(0,0,0,0.50);">
        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel"
             style="border-radius: 15px;">
            <ol class="carousel-indicators">
                <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
            </ol>
            <div class="carousel-inner" style="height: 100%;border-radius: 15px;">
                <div class="carousel-item active" style="width: 100%;height: 100%;">
                    <img class="d-block w-100" src="./image/index2.jpg"
                         alt="1 slide">
                    <div class="carousel-caption d-none d-md-block">
                        <h5 class="titreImage">UNE FAMILLE</h5>
                        <p class="pImage">L'alegia c'est avant tout une famille</p>
                    </div>
                </div>
                <div class="carousel-item" style="width: 100%;height: 100%;">
                    <img style="opacity: 0.9;" class="d-block w-100" src="./image/index4.jpg" alt="2 slide">
                    <div class="carousel-caption d-none d-md-block">
                        <h5 class="titreImage">UN LIEU</h5>
                        <p class="pImage">C'est un lieu pour passer un bon moment</p>
                    </div>
                </div>
                <div class="carousel-item" style="width: 100%;height: 100%;">
                    <img style="opacity: 0.9;" class="d-block w-100" src="./image/index3.jpg" alt="3 slide">
                    <div class="carousel-caption d-none d-md-block">
                        <h5 class="titreImage">DE LA MUSIQUE</h5>
                        <p class="pImage">Une passion pour la musique</p>
                    </div>
                </div>
                <div class="carousel-item" style="width: 100%;height: 100%;">
                    <img class="d-block w-100" src="./image/index5.jpg" alt="4 slide">
                    <div class="carousel-caption d-none d-md-block">
                        <h5 class="titreImage">UNE MARQUE</h5>
                        <p class="pImage">Description de la marque</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<footer>
    <%@ include file="_include/footer.jsp"%>
</footer>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
    $(function() {

    });
</script>
</html>
