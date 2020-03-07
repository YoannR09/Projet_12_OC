<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div style="top: 20px;position: fixed;right: 10px;z-index: 2;">
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
<div style="top: 20px;position: fixed;left: 10px;z-index: 1;">
    <nav class="navbar navbar-expand-sm">
        <!-- Toggler/collapsibe Button -->
        <button class="navbar-toggler menu" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
            <span class="fas fa-bars"></span>
        </button>
        <!-- Navbar links -->
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav flex-column">
                <s:a action="index" class="nav-link btnNav" style="background: transparent;margin-bottom:25px">
                    <img src="../../image/alegiaLogo.png" height="100" alt="submit" /></s:a>
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
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content text-center border shadow">
            <div class="card-body">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <i class="fas fa-info-circle"></i>  <s:property value="infoMessage"/>
            </div>
        </div>
    </div>
</div>
<s:if test="infoMessage != null">
    <button type="button" id="btnModal" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">

    </button>
</s:if>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
    $(function() {
        $('#btnModal').hide();
    $('#btnModal').click();
    });
</script>
