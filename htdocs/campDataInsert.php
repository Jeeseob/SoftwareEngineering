<?php 

    error_reporting(E_ALL); 
    ini_set('display_errors',1); 

    include('dbcon.php');
    $android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");


    if( ($_SERVER['REQUEST_METHOD'] == 'POST') || $android)
    {
        $userNum=$_POST['userNum'];
        $name=$_POST['name'];
        $address=$_POST['address'];
        $phone=$_POST['phone'];
        $kakao=$_POST['kakao'];
        $account=$_POST['account'];
        $time=$_POST['time'];
        $extra=$_POST['extra'];
        $price=$_POST['price'];

         if(!isset($errMSG))
        {
            try{
                $stmt = $con->prepare('INSERT INTO campdata(hostnum,name,address, phone, kakao, account, time, extra, price) VALUES(:hostnum,:name, :address, :phone, :kakao, :account, :time, :extra, :price)');
                $stmt->bindParam(':hostnum', $userNum);
                $stmt->bindParam(':name', $name);
                $stmt->bindParam(':address', $address);
                $stmt->bindParam(':phone', $phone);
                $stmt->bindParam(':kakao', $kakao);
                $stmt->bindParam(':account', $account);
                $stmt->bindParam(':time', $time);
                $stmt->bindParam(':extra', $extra);
                $stmt->bindParam(':price', $price);

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
            hostnum: <input type = "varchar" name = "hostnum" />
            name: <input type = "varchar" name = "name" />
            address: <input type = "varchar" name = "address" />
            phone: <input type = "varchar" name = "phone" />
            kakao: <input type = "varchar" name = "kakao" />
            account: <input type = "varchar" name = "account" />
            time: <input type = "varchar" name = "time" />
            extra: <input type = "varchar" name = "extra" />
            price: <input type = "varchar" name = "price" />
            
            <input type = "submit" name = "submit" />
        </form>
   
   </body>
</html>
<?php 
    }
?>