<?php 

    error_reporting(E_ALL); 
    ini_set('display_errors',1); 

    include('dbcon.php');
    $android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");


    if( ($_SERVER['REQUEST_METHOD'] == 'POST') || $android)
    {
        
        $joinName=$_POST['name'];
        $joinId=$_POST['id'];
        $joinPassword=$_POST['password'];
        $joinEmail=$_POST['email'];
        $joinPhone=$_POST['phone'];
        $joinHost=$_POST['host'];
        

        if(empty($joinName)){
            $errMSG = "이름을 입력하세요.";
        }
        else if(empty($joinId)){
            $errMSG = "아이디를 입력하세요.";
        }
         else if(empty($joinPassword)){
            $errMSG = "비밀번호를 입력하세요.";
        }
         else if(empty($joinEmail)){
            $errMSG = "이메일을  입력하세요.";
        }
        else if(empty($joinPhone)){
            $errMSG = "전화번호를 입력하세요.";
        }
        else if(empty($joinHost)){
            $errMSG = "관리자여부를 입력하세요.";
        }

        if(!isset($errMSG))
        {
            try{
                $stmt = $con->prepare('INSERT INTO userdata(name,id, password, email, phone, admin) VALUES(:name, :id, :password, :email, :phone, :host)');
                $stmt->bindParam(':name', $joinName);
                $stmt->bindParam(':id', $joinId);
                $stmt->bindParam(':password', $joinPassword);
                $stmt->bindParam(':email', $joinEmail);
                $stmt->bindParam(':phone', $joinPhone);
                $stmt->bindParam(':host', $joinHost);

                if($stmt->execute())
                {
                    $successMSG = "새로운 사용자를 추가했습니다.";
                }
                else
                {
                    $errMSG = "사용자 추가 에러";
                }

            } catch(PDOException $e) {
                die("Database error: " . $e->getMessage()); 
            }
        }

    }
?>
<?php 
    if (isset($errMSG)) echo $errMSG;
    if (isset($successMSG)) echo $successMSG;

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
            name: <input type = "varchar" name = "name" />
            id: <input type = "varchar" name = "id" />
            password: <input type = "varchar" name = "password" />
            email: <input type = "varchar" name = "email" />
            phone: <input type = "varchar" name = "phone" />
            host: <input type = "varchar" name = "host" />
            
            <input type = "submit" name = "submit" />
        </form>
   
   </body>
</html>
<?php 
    }
?>