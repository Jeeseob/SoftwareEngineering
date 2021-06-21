<?php
    $con = mysqli_connect("localhost", "root", "2017","campingplus");
    mysqli_query($con,'SET NAMES utf8');

    $hostNum = $_POST["hostNum"];;
    
    
    $statement = mysqli_prepare($con, "SELECT * FROM campdata WHERE hostnum = ?");
    mysqli_stmt_bind_param($statement, "s", $hostNum);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $campNum, $hostNum, $campName, $campAddress, $campPhone, $campKakao, $campAccount,$campTime, $campExtra, $campPrice, $imagepath);

    $response = array();
    $allresponse = array();
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
        $allresponse[] = $response
    }

    //$json = json_encode(array("Camping_v1"=>$response), JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
    //echo $json
    echo json_encode($allresponse);

?>