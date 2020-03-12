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

        #bloc
        {
            margin-bottom: 100px;
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
    </style>
</head>
<body>
<%@ include file="./_include/header.jsp"%>
<div id="bloc" style="display: flex;justify-content:center;z-index: 3">
    <div id="carouselExampleIndicators" class="carousel slide"
         data-ride="carousel" style="width: 100%;">
        <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
        </ol>
        <div class="carousel-inner" style="height: 100%">
            <div class="carousel-item active" style="width: 100%;height: 100%;">
                <img class="d-block w-100" src="./image/fond_1.jfif"
                     alt="1 slide">
            </div>
            <div class="carousel-item" style="width: 100%;height: 100%;">
                <img style="opacity: 0.9;" class="d-block w-100" src="./image/fond_2.jfif" alt="2 slide">
            </div>
        </div>
    </div>
</div>
<div style="text-align: left;padding :5px;height:300px;width: 400px;position: absolute;top:150px;left: 250px;color: white">
    <h2 style="font-weight: bold;text-shadow: 1px 1px #000000;">Bienvenue sur le site de l'alegia !</h2>
    <h4 style="text-shadow: 1px 1px #000000;">Ce que nous proposons</h4>
    <p style="font-size: 0.9em;font-style: italic;text-shadow: 1px 1px #000000;">Ce site propose un catalogue d'articles à l'effigie de l'alegia.
    </p>
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
