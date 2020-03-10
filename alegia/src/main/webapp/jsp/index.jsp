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
            margin-top: 100px;
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
    </style>
</head>
<body>
<%@ include file="./_include/header.jsp"%>
<div id="blocCenter" style="display: flex;justify-content: center;z-index: 3">
    <div class="nav-item" style="text-align: center;width: 550px;box-shadow: 0px 0px 30px -8px rgba(0,0,0,0.50);
margin-right: 10px">
        <div id="carouselExampleIndicators" class="carousel slide rounded" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
            </ol>
            <div class="carousel-inner rounded" style="height: 100%">
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
    <div style="text-align: left;padding :5px;height:200px;width: 350px" class="border border-secondary shadow p-3 mb-5 bgTran rounded">
        <h4 style="font-weight: bold">BIENVENUE SUR LE SITE DE L'ALEGIA</h4>
        <h6>Ce que nous proposons</h6>
        <p style="font-size: 0.8em;font-style: italic">Ce site propose un catalogue d'articles à l'effigie de l'alegia.
        </p>
        <span style="margin-left: 240px">
            <s:a action="" style="font-size:0.7em;">
            En savoir plus</s:a>
        </span>
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
