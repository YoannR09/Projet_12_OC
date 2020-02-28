<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../_include/head.jsp"%>
    <style type="text/css">
        td
        {
            text-align: center;
            font-size: 0.8em;
            font-weight: bold;
        }
        th
        {
            text-align: center;
            font-size: 0.8em;
        }
        #bottom
        {
            display: flex;
            justify-content: space-around;
            width: 100%;
        }
        #labelRecherche
        {
            left: 10px;
            font-size: 1.5em;
            margin: 10px;
            font-weight: bold;
        }
        #cadreArticle,#cadreAutreAdresse
        {
            width: 100%;
            margin: 10px;
        }
        #cadreBtn
        {
            display: flex;
            justify-content: space-around;
        }
        span
        {
            font-size: 0.8em;
        }
        .lab
        {
            font-size: 0.7em;
            color: black;
        }
        .textTop
        {
            font-size: 1.3em;
            margin: 10px;
        }
        .btnType
        {
            font-size: 0.7em;
            margin: 10px;
        }
        #blocCenter
        {
            display: flex;
            justify-content: center;
            margin-top: 75px;
        }
        .btnUnder
        {
            margin-top : 5px;
            margin-bottom : 5px;
            font-size: 0.8em;
            width: 100%;
        }
        #radio
        {
            display: flex;
            justify-content: space-around;
            width: 300px;
        }
        #btnRecherche
        {
            font-size: 0.8em;
            height: 32px;
            margin: auto;
        }
        #barreDeRecherche
        {
            padding: 15px;
        }
        label,input
        {
            font-size: 0.7em;
        }
        divInput
        {
            display: flex;
            justify-content: space-around;
        }
        em
        {
            font-size: 0.7em;
        }
        .spanAdresse
        {
            font-size: 0.8em;
            font-weight: bold;
        }

    </style>
</head>
<body>
<%@ include file="../_include/header.jsp"%>
<div id="blocCenter" style="display: flex;justify-content: center;">
    <div class="col-9" style="max-width: 500px">
        <label class="textTop">Votre commande</label>
        <div class="col-12 container border shadow p-3 mb-5 bg-white rounded" id="cadreArticle">
            <div style="display: flex;justify-content: space-between">
                <s:form action="execute">
                        <table>
                            <tr>
                                <td colspan="2"><b>Transaction Details:</b></td>
                                <td>
                                    <input type="hidden" name="paymentId" value="<s:property value="paymentId"/>" />
                                    <input type="hidden" name="PayerID" value="<s:property value="PayerID"/>" />
                                </td>
                            </tr>
                            <tr>
                                <td>Description:</td>
                                <td><s:property value="transaction.descrpition"/></td>
                            </tr>
                            <tr>
                                <td>Subtotal:</td>
                                <td><s:property value="transaction.amount.details.subtotal"/> USD</td>
                            </tr>
                            <tr>
                                <td>Shipping:</td>
                                <td><s:property value="transaction.amount.details.shipping"/> USD</td>
                            </tr>
                            <tr>
                                <td>Tax:</td>
                                <td><s:property value="transaction.amount.tax"/> USD</td>
                            </tr>
                            <tr>
                                <td>Total:</td>
                                <td><s:property value="transaction.amount.total"/> USD</td>
                            </tr>
                            <tr><td><br/></td></tr>
                            <tr>
                                <td colspan="2"><b>Payer Information:</b></td>
                            </tr>
                            <tr>
                                <td>First Name:</td>
                                <td><s:property value="payer.firstName"/></td>
                            </tr>
                            <tr>
                                <td>Last Name:</td>
                                <td><s:property value="payer.lastName"/></td>
                            </tr>
                            <tr>
                                <td>Email:</td>
                                <td><s:property value="payer.email"/></td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center">
                                    <input type="submit" value="Pay Now" />
                                </td>
                            </tr>
                        </table>
                </s:form>
            </div>
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
