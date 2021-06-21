<?php

    $con = mysqli_connect("localhost", "root", "2017","campingplus");
    
    if (mysqli_connect_errno()){
    echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }

    $userNum = $_POST["usernum"];
    $userId= $_POST["userid"];
    $userName = $_POST["username"];
    $userPwd = $_POST["userpwd"];
    $userPhoneNum = $_POST["userphonenum"];
    $userEmail = $_POST["useremail"];
    
    $query = "UPDATE userdata SET name ='$userName',password='$userPwd' ,phone= '$userPhoneNum',email='$userEmail' WHERE id='$userId'";
    mysqli_query($con,$query);

    
    mysqli_close($con);

 

?>