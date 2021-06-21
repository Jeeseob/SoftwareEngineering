<?php

    $con = mysqli_connect("localhost", "root", "2017","campingplus");
    
    if (mysqli_connect_errno()){
    echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }
    $campNum = $_POST["campnum"];
    $campName = $_POST["campname"];
    $campAddress= $_POST["campaddress"];
    $campPhone = $_POST["campphone"];
    $campKakao = $_POST["campkakao"];
    $accountNum = $_POST["accountnum"];
    $campTime = $_POST["camptime"];
    $campCost = $_POST["campcost"];
    $campExtra = $_POST["campextra"];
    
    
    $query = "UPDATE campdata SET name ='$campName',address='$campAddress' 
    ,phone= '$campPhone',kakao='$campKakao'
    ,account='$accountNum',time='$campTime',extra='$campExtra',price='$campCost'
     WHERE num='$campNum'";
    mysqli_query($con,$query);

    
    mysqli_close($con);

 

?>