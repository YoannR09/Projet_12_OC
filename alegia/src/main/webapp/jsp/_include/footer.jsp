<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div id="bas" class="row" style="height: 100%;box-shadow: 0px -5px 50px -3px rgba(0,0,0,0.49);background: url(../../image/black.png);margin: auto;justify-content: center">
    <div style="width: 25%;text-align: center;margin: 25px;">
        <div style="color: lightgray;width: 100%;text-align: center;margin-bottom: 17px"><em style="font-size: 0.6em;font-weight: bold"> Réseaux sociaux </em> </div>
        <a href="https://www.facebook.com/Alegia-1428352727440625/?epa=SEARCH_BOX" class="text-warning" style="margin: 20px;font-size: 1.2em">
            <i class="fab fa-facebook">
            </i></a>
        <a href="https://www.instagram.com/alegia.sanary/?hl=fr" class="text-warning" style="margin: 20px;font-size: 1.2em">
            <i class="fab fa-instagram">
            </i></a>
        <br/>
        <a href="https://www.tripadvisor.fr/Restaurant_Review-g651751-d10507269-Reviews-Alegia-Sanary_sur_Mer_Var_Provence_Alpes_Cote_d_Azur.html" class="text-warning" style="margin: 20px;font-size: 1.2em">
            <i class="fab fa-tripadvisor">
            </i></a>
    </div>
    <div style="width: 25%;text-align: center;margin: 25px;">
        <div style="color: lightgray;margin-bottom: 17px"><em style="font-size: 0.6em;font-weight: bold"> Adresse </em> </div>
        <em class="text-warning" style="font-size: 0.6em;color: whitesmoke"> 5 Rue Louis Blanc <br/>
            83110 Sanary-sur-Mer</em>
    </div>
    <div style="width: 25%;text-align: center;margin: 25px;">
        <div style="color: lightgray;margin-bottom: 17px"><em style="font-size: 0.6em;font-weight: bold"> Contact </em> </div>
        <div class="text-warning">
            <i class="fas fa-envelope"></i> <em style="font-size: 0.6em">
             alegia.shop@gmail.com
        </em>
        </div>
    </div>
    <div style="width: 25%;text-align: center;margin: 25px;">
        <div style="color: lightgray;width: 100%;text-align: center;margin-bottom: 17px;font-weight: bold"><em style="font-size: 0.6em"> Aide </em> </div>
        <div class="text-warning"><em style="font-size: 0.6em"><s:a action="" class="text-warning">Conditions genérales de ventes</s:a>  </em> </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
    $(function() {
        var h = $('#footer').height();
        $('#bas').height(h);
    });
    $( window ).resize(function() {
        var h = $('#footer').height();
        $('#bas').height(h);
    });
</script>
