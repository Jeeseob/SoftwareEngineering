<?php 

    error_reporting(E_ALL); 
    ini_set('display_errors',1); 

    include('dbcon.php');
    $android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");


    if( ($_SERVER['REQUEST_METHOD'] == 'POST') || $android)
    {
        $userNum=$_POST['usernum'];
        $userName=$_POST['username'];
        $campNum=$_POST['campnum'];
        $hostNum=$_POST['hostnum'];
        $hostPhoneNum=$_POST['hostphonenum'];
        $userPhoneNum=$_POST['userphonenum'];
        $campName=$_POST['campname'];
        $date=$_POST['date'];
        $campAddress=$_POST['campaddress'];
        $accountNum=$_POST['accountnum'];
        $campExtraUse=$_POST['campextrause'];
        $campCost=$_POST['campcost'];
        $kakao=$_POST['kakao'];

         if(!isset($errMSG))
        {
            try{
                $stmt = $con->prepare('INSERT INTO reservationdata(usernum, username, campnum, hostnum, hostphonenum, userphonenum, campname, date, campaddress, accountnum, campextrause, campcost, kakao) VALUES(:usernum,:username, :campnum, :hostnum, :hostphonenum, :userphonenum, :campname, :date, :campaddress, :accountnum, :campextrause, :campcost, :kakao)');
                $stmt->bindParam(':usernum', $userNum);
                $stmt->bindParam(':username', $userName);
                $stmt->bindParam(':campnum', $campNum);
                $stmt->bindParam(':hostnum', $hostNum);
                $stmt->bindParam(':hostphonenum', $hostPhoneNum);
                $stmt->bindParam(':userphonenum', $userPhoneNum);
                $stmt->bindParam(':campname', $campName);
                $stmt->bindParam(':date', $date);
                $stmt->bindParam(':campaddress', $campAddress);
                $stmt->bindParam(':accountnum', $accountNum);
                $stmt->bindParam(':campextrause', $campExtraUse);
                $stmt->bindParam(':campcost', $campCost);
                $stmt->bindParam(':kakao', $kakao);


                if($stmt->execute())
                {
                    $successMSG = "데이터를 추가했습니다.";
                }
                else
                {
                    $errMSG = "데이터  추가 에러";
                }

            }
            catch(PDOException $e) {
                die("Database error: " . $e->getMessage()); 
            }
        }
    }
?>
<?php 


	$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");
   
    if( !$android ){
?>
<html>
   <head><meta http-equiv="Content-Type" content="text/html; charset=utf-8"></head>

   <body>
        <?php 
        if (isset($errMSG)) echo $errMSG;
        if (isset($successMSG)) echo $successMSG;
        ?>
        
        <form action="<?php $_PHP_SELF ?>" method="POST">
            userNum: <input type = "varchar" name = "usernum" />
            userName: <input type = "varchar" name = "username" />
            campNum: <input type = "varchar" name = "campnum" />
            hostNum: <input type = "varchar" name = "hostnum" />
            hostPhoneNum: <input type = "varchar" name = "hostphonenum" />
            userPhoneNum: <input type = "varchar" name = "userphonenum" />
            campName: <input type = "varchar" name = "campnum" />
            date: <input type = "varchar" name = "date" />
            campAddress: <input type = "varchar" name = "campaddress" />
            accountNum: <input type = "varchar" name = "accountnum" />
            campExtraUse: <input type = "varchar" name = "campextrause" />
            campCost: <input type = "varchar" name = "campcost" />
            kakao: <input type = "varchar" name = "kakao" />
            
            <input type = "submit" name = "submit" />
        </form>
   
   </body>
</html>
<?php 
    }
?>