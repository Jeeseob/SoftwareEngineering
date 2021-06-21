<?php
  $con = mysqli_connect("localhost","root", "2017","campingplus");
  mysqli_set_charset($con,"utf8");
  
  $res = mysqli_query($con,"select distinct * from userdata");
  $result = array();
  
  while($row = mysqli_fetch_array($res)){
    array_push($result,
      array("num" => $row[0],"name"=> $row[1], "id" =>$row[2],"password"=>$row[3], "email"=>$row[4], "phone"=>$row[5], "host"=>$row[6]));
  }
  echo json_encode(array("campingplus"=> $result), JSON_UNESCAPED_UNICODE);
  mysqli_close($con);
 ?>