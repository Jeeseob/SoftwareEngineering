<?php
    $con = mysqli_connect("localhost", "root", "2017","campingplus");
    mysqli_query($con,'SET NAMES utf8');

    $reservationNum = $_POST["reservationNum"];;
    
    
    $statement = mysqli_prepare($con, "SELECT * FROM reservationdata WHERE num = ?");
    mysqli_stmt_bind_param($statement, "s", $reservationNum);
    mysqli_stmt_execute($statement);


    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $reservationNum, $userNum, $userName, $campNum, $hostNum, $hostPhoneNum, $userPhoneNum,$campName, $date, $campAddress, $accountNum, $campExtraUse, $campCost, $kakao, $accept);

    $response = array();
    $response["success"] = false;
    
    while(mysqli_stmt_fetch($statement)) {
        $response["success"] = true;
        $response["reservationNum"] = $reservationNum;
        $response["userNum"] = $userNum;
        $response["userName"] = $userName;
        $response["campNum"] = $campNum;
        $response["hostNum"] = $hostNum;
        $response["hostPhoneNum"] = $hostPhoneNum;
        $response["userPhoneNum"] = $userPhoneNum;
        $response["campName"] = $campName;
        $response["date"] = $date;
        $response["campAddress"] = $campAddress;
        $response["accountNum"] = $accountNum;
        $response["campExtraUse"] = $campExtraUse;
        $response["campCost"] = $campCost;
        $response["kakao"] = $kakao;
        $response["accept"] = $accept;
        
    }

    echo json_encode($response);

?>