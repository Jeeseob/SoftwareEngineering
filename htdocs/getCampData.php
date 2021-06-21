<?php
    $con = mysqli_connect("localhost", "root", "2017","campingplus");
    mysqli_query($con,'SET NAMES utf8');

    $campNum = $_POST["campNum"];;
    
    
    $statement = mysqli_prepare($con, "SELECT * FROM campdata WHERE num = ?");
    mysqli_stmt_bind_param($statement, "s", $campNum);
    mysqli_stmt_execute($statement);


    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $campNum, $hostNum, $campName, $campAddress, $campPhone, $campKakao, $campAccount,$campTime, $campExtra, $campPrice, $imagepath);

    $response = array();
    $response["success"] = false;
    
    while(mysqli_stmt_fetch($statement)) {
        $response["success"] = true;
        $response["campNum"] = $campNum;
        $response["hostNum"] = $hostNum;
        $response["campName"] = $campName;
        $response["campAddress"] = $campAddress;
        $response["campPhone"] = $campPhone;
        $response["campKakao"] = $campKakao;
        $response["campAccount"] = $campAccount;
        $response["campTime"] = $campTime;
        $response["campExtra"] = $campExtra;
        $response["campPrice"] = $campPrice;
        $response["imagepath"] = $imagepath;
    }

    echo json_encode($response);

?>