<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div style="top: 20px;position: fixed;right: 10px;z-index: 1">
    <div>
        <li>
            <s:a action="login" class="nav-link btnNav" style="font-size:0.9em;"><i class="fas fa-user-circle" style="margin-right: 10px"></i>  Se connecter </s:a>
        </li>
        <li>
            <s:a action="" class="nav-link btnNav" style="font-size:0.9em;"><i class="fas fa-shopping-cart" style="margin-right: 10px"></i>  Mon panier </s:a>
        </li>
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
                            <s:a action="doListArticleByCategorieId" class="nav-link btnNav" style="font-size:1em;font-family: arial"><s:property value="nom"/><s:param name="categorieId" value="id"/></s:a>
                        </li>
                    </s:iterator>
                    <li class="nav-item" style="margin-top: 20px;'">
                        <s:a action="" class="nav-link btnNav" style="font-size:1em;font-family: arial"> LA MARQUE </s:a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
