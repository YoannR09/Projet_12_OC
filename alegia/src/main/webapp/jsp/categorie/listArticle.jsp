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
    <div class="col-8">
        <section class="row">
        <s:iterator value="articleList">
            <li class="nav-item col-3" style="text-align: center;">
                <s:a action=""><img class="d-block w-100" src="../../image/t-shirt.jpg"><s:param name="articleId" value="id"/></s:a>
                <p style="font-size: 0.8em;font-weight: bolder"> <s:property value="nom"/> </p>
                <p style="font-size: 0.7em;color: darkgray"> <s:property value="prixTtc"/> €</p>
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