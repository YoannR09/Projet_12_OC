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
            margin-top: 100px;
        }

        body
        {
            margin:0;
            padding:0;
        }

    </style>
</head>
<body>
<%@ include file="../_include/header.jsp"%>
<div id="blocCenter" style="display: flex;justify-content: center">
    <div>
        <div id="list-example" class="list-group border border-secondary shadow">
            <a class="list-group-item list-group-item-action" href="#list-item-1">Une famille</a>
            <a class="list-group-item list-group-item-action" href="#list-item-2">De la musique</a>
            <a class="list-group-item list-group-item-action" href="#list-item-3">Un lieu</a>
            <a class="list-group-item list-group-item-action" href="#list-item-4">Une marque</a>
        </div>
        <div data-spy="scroll" data-target="#list-example" data-offset="0" class="scrollspy-example">
            <h4 id="list-item-1">Une famille</h4>
            <p>...</p>
            <h4 id="list-item-2">De la musique</h4>
            <p>...</p>
            <h4 id="list-item-3">Un lieu</h4>
            <p>...</p>
            <h4 id="list-item-4">Une marque</h4>
            <p>...</p>
        </div>
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
