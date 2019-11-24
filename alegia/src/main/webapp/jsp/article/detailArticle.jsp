<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../_include/head.jsp"%>
    <style type="text/css">
        #blocCenter
        {
            display: flex;
            justify-content: center;
            margin-top: 75px;
        }

    </style>
</head>
<body>
<%@ include file="../_include/header.jsp"%>
<div id="blocCenter" style="display: flex;justify-content: center;">
    <div class="col-xs-5 col-8" style="display: flex;justify-content: center">
        <div class="col-5">
            <div id="carouselExampleControls" class="carousel slide" data-ride="carousel" style=" margin-bottom: 5px">
                <div class="carousel-inner" style="height: 100%">
                    <s:iterator value="imageList" status="imageList">
                        <s:if test="%{#imageList.count == 1}">
                            <div class="carousel-item active border" style="width: 100%;height: 100%;">
                                <s:a action="">
                                    <s:param name="articleId" value="articleId"/>
                                    <img class="d-block w-100" src="./image/<s:property value="url"/>"
                                         alt="First slide">
                                </s:a>
                            </div>
                        </s:if>
                        <s:elseif test="%{#imageList.count > 1}">
                            <div class="carousel-item border" style="width: 100%;height: 100%">
                                <s:a action="">
                                    <s:param name="articleId" value="articleId"/>
                                    <img class="d-block w-100 " src="./image/<s:property value="url"/>">
                                </s:a>
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
        <div class="col-5">
            <s:form action="addPanier" id="formAddPanier">
                <p style="font-size: 1.1em;font-weight: bolder"><s:property value="article.nom"/></p>
                <p style="font-size: 0.9em;color: darkslategrey"> 100% Coton - 210g </p>
                <p style="font-size: 0.9em;color: darkslategrey"><s:property value="article.prixTtc"/> €</p>
                <label for="selectTaille" style="font-size: 0.7em;"><em>Sélectionner une taille</em></label>
                <br/>
                <select class="custom-select custom-select-sm" style="width: 100px;font-size: 0.8em;" id="selectTaille">
                    <s:iterator value="listTailles" status="list">
                        <option><s:property value="taille.taille"/></option>
                    </s:iterator>
                </select>
                <div style="margin-top: 20px">
                    <button type="submit" class="btn btn-light btn-sm border mb-2">Ajouter au panier</button>
                </div>
            </s:form>
        </div>
    </div>
</div>
<footer>
    <%@ include file="../_include/footer.jsp"%>
</footer>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
    $(function() {

    });
</script>
</html>