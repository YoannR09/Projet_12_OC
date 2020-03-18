<%@ taglib prefix="s" uri="/struts-tags" %>
<title>Alegia</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Odibee+Sans|Raleway&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet">
<script
        src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
        integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/9499ed6664.js" crossorigin="anonymous"></script>
<style type="text/css">
    ul{
        list-style:none;
        display: flex;
    }
    html
    {
        height: 100%;
    }
    html, body {
        width: 100%;
        margin: 0; padding: 0;
        position: relative;
    }
    li
    {
        list-style: none;
    }
    .btnNav
    {
        color: orange;
        letter-spacing: 1px;
        font-family: 'Roboto', sans-serif;
    }
    .btnNav:hover
    {
        color: #FFCA51;
        font-weight: bold;
    }
    *
    {
        font-family: 'Raleway' ;
    }
    .numberFont
    {
        font-family: 'Roboto', sans-serif;
    }
    body {
        width: 100%;
        background-image:url(/image/fondAlegia200.png);
        background-repeat: repeat;
        background-size: 50%;
        background-color: white;
        min-height: 100%;
        height: 100%;
        position: relative; display: table;
        min-width: 340px;

    }
    label
    {
        font-weight: bold;
    }
    .bgTran
    {
        background-color: rgba(255, 255, 255, 0.5);
    }
    footer {
        display: table-row;
        width: 100%;
        height: 100%;
        min-height: 100%;
        bottom: 0;
        position: relative;
        height: 100%;
        margin: auto;
        overflow: hidden;
    }
    #blocCenter,#page
    {
        margin-bottom: 200px;
    }
    .aNav:after {
        background: none repeat scroll 0 0 transparent;
        bottom: 0;
        content: "";
        display: block;
        height: 1px;
        left: 50%;
        position: relative;
        background: #FFCA51;
        transition: width 0.5s ease 0s, left 0.5s ease 0s;
        width: 0;
    }
    .aNav:hover:after {
        width: 100%;
        left: 0;
    }
    @media screen and (max-height: 300px) {
        ul {
            margin-top: 40px;
        }
    }
</style>
