<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div style="top: 20px;position: fixed;right: 10px;z-index: 1">
    <div>
        <s:if test="!#session.user && !#session.admin">
            <li>
                <s:a action="login" class="nav-link btnNav" style="font-size:0.8em;"><i class="fas fa-user-circle" style="margin-right: 10px"></i>  Se connecter </s:a>
            </li>
        </s:if>
        <s:if test="#session.user || #session.admin">
            <li style="display: flex;justify-content: space-around">
                <s:a action="doConsulterPanier" class="nav-link btnNav" style="font-size:0.8em;"><i class="fas fa-shopping-cart" style="margin-right: 10px"></i> Mon panier
                    <span class="badge badge-pill badge-warning" style="color: white;position: relative;top:-10px;right: -1px;font-size: 0.9em"><s:property value="countPanier"/></span></s:a>
                <s:a action="doConsulterCommande" class="nav-link btnNav" style="font-size:0.8em;"> <i class="fas fa-shopping-bag" style="margin-right: 10px"></i> Mes commandes </s:a>
                <s:a action="doProfil" class="nav-link btnNav" style="font-size:0.8em;"><i class="fas fa-user-circle" style="margin-right: 10px"></i> Mon profil</s:a>
                <s:a action="logout" class="nav-link btnNav" style="font-size:0.8em;"><i class="fas fa-sign-out-alt" style="margin-right: 10px"></i> Se déconnecter</s:a>
            </li>
        </s:if>
    </div>
</div>
<div class="container-fluid " style="top: 20px;position: fixed;left: 10px;">
    <nav class="navbar navbar-expand-sm">
        <!-- Toggler/collapsibe Button -->
        <button class="navbar-toggler menu" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
            <span class="fas fa-bars"></span>
        </button>
        <!-- Navbar links -->
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav flex-column">
                <s:a action="index" class="nav-link btnNav" style="background: transparent;margin-bottom:25px"> <img src="../../image/logo.jpg" width="100" height="100" alt="submit" /></s:a>
                <s:iterator value="categorieList">
                    <li class="nav-item">
                        <s:a action="doListArticleByCategorieId" class="nav-link btnNav" style="font-size:0.9em;font-family: arial"><s:property value="nom"/><s:param name="categorieId" value="id"/></s:a>
                    </li>
                </s:iterator>
                <s:if test="#session.admin">
                    <li class="nav-item" style="margin-top: 20px;'">
                        <s:a action="gestion" class="nav-link btnNav" style="font-size:0.9em;font-family: arial;color:blue"> GESTION </s:a>
                    </li>
                </s:if>
            </ul>
        </div>
    </nav>
</div>
<s:if test="infoMessage != null">
    <div id="cardInfo" class="card text-center border shadow" style="bottom: 200px;position: fixed;right: 40px;z-index: 1
    ;width: 20rem;">
        <div class="card-body">
            <i class="fas fa-info-circle"></i>  <s:property value="infoMessage"/>
        </div>
        <div class="card-footer">
            <button id="btnInfo" class="btn btn-dark" style="font-size: 0.6em;">Fermer</button>
        </div>
    </div>
</s:if>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
    $(function() {
        $(document).on('click', '#btnInfo', function () {
            $('#cardInfo').slideUp(500);
        });
    });
</script>
