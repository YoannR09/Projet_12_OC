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

    </style>
</head>
<body>
    <%@ include file="./_include/header.jsp"%>
    <div id="blocCenter" style="display: flex;justify-content: center">
    <div class="col-8 bg-dark" style="height: 200px;opacity: 0">
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