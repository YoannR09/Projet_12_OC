<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../../_include/head.jsp"%>
    <style type="text/css">
        #blocCenter
        {
            display: flex;
            justify-content: center;
            margin-top: 100px;
        }
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
        #cadreArticle
        {
            width: 100%;
            border-left: orange solid 1px;
            border-radius: 4px;
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

    </style>
</head>
<body>
<%@ include file="../../_include/header.jsp"%>
<div id="blocCenter" style="display: flex;justify-content: center;">
    <div class="col-9">
        <s:if test="panierVide"><label class="textTop">Vous n'avez effectué aucune commande.</label></s:if>
        <s:else><label class="textTop">Liste des commande "<em style="font-size: 0.8em"><s:property value="statutList" /></em> "</label></s:else>
        <div class="col-12 " style="display: flex;justify-content: space-around">
            <div style="width: 800px">
                <table class="table table-bordered table-hover border shadow p-3 mb-5 bg-white rounded">
                    <thead class="thead">
                    <tr style="max-height: 10px">
                        <th scope="col">COMMANDE EFFECTUEE LE</th>
                        <th scope="col">N° DE COMMANDE</th>
                        <th scope="col">TOTAL</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="commandeList">
                        <tr>
                            <td><s:property value="date"/></td>
                            <td><s:property value="numero"/></td>
                            <td><s:property value="totalPayer"/>  €</td>
                            <td><s:a action="doDetailCommande">
                                <s:param value="id" name="commandeId"/>
                                <i style="font-size: 1em" class="fas fa-caret-square-right"></i>
                            </s:a></td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<footer>
    <%@ include file="../../_include/footer.jsp"%>
</footer>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
    $(function() {
        $('.divModal').hide();
    });

    $(document).on('click', '.btModal', function () {
        var id = $(this).attr("id");
        $('.btModal').attr("disabled", false);
        $(this).attr("disabled", true);
        $('.divModal').slideUp(500);
        $('#commande'+id).slideDown(1000);
    });
</script>
</html>
