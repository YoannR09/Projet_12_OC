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
    <div class="col-xs-5 col-8">
        <section class="row" style="margin: auto;justify-content: space-around">
            <s:iterator value="articleList">
                <s:set var="articleId" value="id"/>
                <li class="nav-item" style="text-align: center;width: 200px;;margin :15px">
                    <div id="carouselExampleControls" class="carousel slide" data-ride="carousel" style=" margin-bottom: 5px">
                        <div class="carousel-inner" style="height: 100%">
                            <s:iterator value="imageList" status="imageList">
                                <s:if test="%{#imageList.count == 1}">
                                    <div class="carousel-item active border" style="width: 100%;height: 100%;">
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
                    <p style="font-size: 0.8em;color: darkgray"> <s:property value="prixTtc"/> €</p>
                </li>
            </s:iterator>
        </section>
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