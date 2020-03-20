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
        a.disabled {
            pointer-events: none;
            cursor: default;
        }

    </style>
</head>
<body>
<%@ include file="../_include/header.jsp"%>
<div id="blocCenter" style="display: flex;justify-content: center;">
    <div class="col-xs-5 col-7">
        <s:if test="articleList.size == 0">
            <label style="font-size: 0.8em;margin-bottom: 200px"><em>Cette catégorie ne contient pas d'article pour le moment.</em></label>
        </s:if>
        <section class="row" style="margin: auto;justify-content: space-around">
            <s:iterator value="articleList">
                <s:set var="articleId" value="id"/>
                <li class="nav-item hover border border-secondary shadow bgTran " style="text-align: center;width: 200px;margin :15px">
                    <div id="carouselExampleControls" class="carousel slide " data-ride="carousel" style=" margin-bottom: 5px">
                        <div class="carousel-inner " style="height: 100%">
                            <s:iterator value="imageList" status="imageList">
                                <s:if test="%{#imageList.count == 1}">
                                    <div class="carousel-item active " style="width: 100%;height: 100%;">
                                        <s:a action="doDetailArticle">
                                            <s:param name="articleId" value="articleId"/>
                                            <img class="d-block w-100" src="./image/<s:property value="url"/>"
                                                 alt="First slide">
                                        </s:a>
                                    </div>
                                </s:if>
                                <s:elseif test="%{#imageList.count > 1}">
                                    <div class="carousel-item border" style="width: 100%;height: 100%">
                                        <s:a action="doDetailArticle">
                                            <s:param name="articleId" value="articleId"/>
                                            <img class="d-block w-100 " src="./image/<s:property value="url"/>">
                                        </s:a>
                                    </div>
                                </s:elseif>
                            </s:iterator>
                        </div>
                    </div>
                    <p style="font-size: 0.8em;font-weight: bolder"> <s:property value="nom"/> </p>
                    <p class="numberFont" style="font-size: 0.8em;"> <s:property value="prixAffichage"/> €</p>
                </li>
            </s:iterator>
        </section>
        <s:if test="articleList.size != 0">
            <div style="display: flex;justify-content: center;margin: 35px;font-size: 0.7em">
                <div class="btn-group mr-2" role="group">
                <span class="numberFont" style="padding: auto;margin: 5px">
                <s:if test="page != 1">
                    <s:a action="doMoinsListArticle" class="text-dark" style="font-size :2em">
                        <s:param value="listSize" name="listSize"/>
                        <s:param value="categorieId" name="categorieId"/>
                        <s:param value="page" name="page"/>
                        <i class="fas fa-arrow-circle-left"></i>
                    </s:a>
                </s:if>
                <s:else>
                    <a class="disabled" style="font-size :2em">
                        <i class="fas fa-arrow-circle-left text-secondary"></i>
                    </a>
                </s:else>
                </span>
                    <span style="margin: 5px;font-weight: bold;padding-top: 6px"> Page : <s:property value="page"/> / <s:property value="countPage"/> </span>
                    <span class="numberFont" style="padding: auto;margin: 5px">
                <s:if test="!max || max == null">
                    <s:a action="doPlusListArticle" class="text-dark" style="font-size :2em">
                        <s:param value="listSize" name="listSize"/>
                        <s:param value="page" name="page"/>
                        <s:param value="categorieId" name="categorieId"/>
                        <i class="fas fa-arrow-circle-right"></i>
                    </s:a>
                </s:if>
                <s:else>
                    <a class="disabled" style="font-size :2em">
                        <i class="fas fa-arrow-circle-right text-secondary"></i>
                    </a>
                </s:else>
                </span>
                </div>
            </div>
        </s:if>
    </div>
</div>
<footer id="footer">
    <%@ include file="../_include/footer.jsp"%>
</footer>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
    $(function() {

    });
</script>
</html>
