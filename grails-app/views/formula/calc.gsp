<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="main"/>

<title>Calculator</title>
<link rel="stylesheet" href="${resource(dir: 'css', file: 'calc.css')}" type="text/css">

</head>
<body>

<div class="content">
    <div class="calculator">
        <div class="screen"></div>
        <input type="hidden" value="" class="outcome" />
        <ul class="buttons">
            <li class="clear"><a>C</a></li>
            <li><a href="-" class="val">&plusmn;</a></li>
            <li><a href="/" class="val">&divide;</a></li>
            <li><a href="*" class="val">&times;</a></li>    
            <li><a href="7" class="val">7</a></li>
            <li><a href="8" class="val">8</a></li>
            <li><a href="9" class="val">9</a></li>
            <li><a href="-" class="val">-</a></li>
            <li><a href="4" class="val">4</a></li>
            <li><a href="5" class="val">5</a></li>
            <li><a href="6" class="val">6</a></li>
            <li><a href="+" class="val">+</a></li>
            <li><a href="1" class="val">1</a></li>
            <li><a href="2" class="val">2</a></li>
            <li><a href="3" class="val">3</a></li>
            <li><a class="equal tall">=</a></li>
            <li><a href="0" class="val wide shift">0</a></li>
            <li><a href="." class="val shift">.</a></li>
        </ul>
    </div>
</div>
<script type="text/javascript">
$(function(){
    $(".val").click(function(e){
         e.preventDefault();
          var a = $(this).attr("href");
          $(".screen").append(a);
          $(".outcome").val($(".outcome").val() + a);
    });

     $(".equal").click(function(){
          $(".outcome").val(eval($(".outcome").val()));
          $(".screen").html(eval($(".outcome").val()));
     });

     $(".clear").click(function(){
          $(".outcome").val("");
          $(".screen").html("");
     });

     $(".min").click(function(){
         $(".cal").stop().animate({width: "0px", height: "0px", marginLeft: "700px", marginTop: "1000px"}, 500);
        setTimeout(function(){$(".cal").css("display", "none")}, 600);
     });

     $(".close").click(function(){
          $(".cal").css("display", "none");
     })
})

</script>
</body>



</html>