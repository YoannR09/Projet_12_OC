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
        em
        {
            color : darkgray;
        }

    </style>
</head>
<body>
<%@ include file="../_include/header.jsp"%>
<div id="blocCenter" style="display: flex;justify-content: center;">
    <div class="col-12 col-sm-10 col-md-8 col-lg-6 col-xl-4" style="display: flex;justify-content: center">
        <div class="border border-secondary shadow" style="min-width: 400px">
            <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner" style="height: 100%">
                    <s:iterator value="imageList" status="imageList">
                        <s:if test="%{#imageList.count == 1}">
                            <div class="carousel-item active" style="width: 100%; height: 100%;">
                                <img class="d-block w-100" src="./image/<s:property value="url"/>"
                                     alt="First slide">
                            </div>
                        </s:if>
                        <s:elseif test="%{#imageList.count > 1}">
                            <div class="carousel-item border" style="width: 100%;height: 100%">
                                <img class="d-block w-100 " src="./image/<s:property value="url"/>">
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
        <div class="border border-secondary shadow p-3 bgTran rounded" style="height: 320px;min-width: 250px;margin-left: 20px">
            <s:form action="addPanier" id="formAddPanier">
                <input id="idArticle" name="articleId" class="form-control" type="number" value="<s:property value="article.id"/>" style="width: 100px;font-size: 0.8em;visibility: revert">
                <p style="font-size: 1.1em;font-weight: bolder"><s:property value="article.nom"/></p>
                <p style="font-size: 0.9em;"><s:property value="article.description"/></p>
                <p class="numberFont" style="font-size: 0.9em;"><s:property value="article.prixAffichage"/> €</p>
                <label for="selectTaille" style="font-size: 0.7em;"><em>Sélectionner une taille</em></label>
                <br/>
                <select class="custom-select custom-select-sm" name="taille" style="width: 100px;font-size: 0.8em;" id="selectTaille">
                    <s:iterator value="listTailles" status="list">
                        <option><s:property value="taille.taille"/></option>
                    </s:iterator>
                </select>
                <br/>
                <label for="inputQuantite" style="font-size: 0.7em;"><em>Quantité</em></label>
                <input name="quantite" class="form-control" type="number" value="1" id="inputQuantite" style="width: 100px;font-size: 0.8em">
                <s:if test="#session.user || #session.admin">
                <div style="margin-top: 20px">
                    <s:param value="article.id" name="articleId"/>
                    <button type="submit" class="btn btn-dark btn-sm border mb-2">Ajouter au panier</button>
                </div>
                </s:if>
                <s:else>
                <div style="margin-top: 20px">
                    <button data-toggle="tooltip" data-placement="right" title="Vous devez être connecté" type="submit"
                            class="btn btn-dark btn-sm border mb-2" disabled>Ajouter au panier</button>
                </div>
                </s:else>

            </s:form>
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
        $('#idArticle').hide();
    });
</script>
</html>
