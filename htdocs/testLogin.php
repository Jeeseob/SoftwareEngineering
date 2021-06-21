<?php
    $con = mysqli_connect("localhost", "root", "2017","campingplus");
    mysqli_query($con,'SET NAMES utf8');

    $userID = $_POST["UserId"];
    $userPwd = $_POST["UserPwd"];
    
    
    $statement = mysqli_prepare($con, "SELECT * FROM userdata WHERE id = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $userID, $userPwd);
    mysqli_stmt_execute($statement);


    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userNum, $userName, $userID, $userPwd, $userEmail, $userPhone, $host);

    $response = array();
    $response["success"] = false;
    
    while(mysqli_stmt_fetch($statement)) {
        $response["success"] = true;
        $response["UserName"] = $userName;
        $response["UserNum"] = $userNum;
        $response["UserId"] = $userID;
        $response["UserPwd"] = $userPwd;
        $response["UserEmail"] = $userEmail;
        $response["UserPhone"] = $userPhone;
        $response["host"] = $host;
    }

    echo json_encode($response);

?>