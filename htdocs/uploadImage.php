<?php

	$HOST = "localhost";
	$USER = "root";
	$PASSWORD = "2017";
	$DB_NAME = "campingplus";
	

	$con = mysqli_connect($HOST, $USER, $PASSWORD, $DB_NAME);

	$encodedImage = $_POST['EN_IMAGE'];
	
	
	$query = "SELECT * FROM campdata";
  $data = mysqli_query($con, $query);
  $num = mysqli_num_rows($data);


	$imageTitle = "Image$num";
	$imageLocation = "campImage/$imageTitle.jpg";

	$sqlQuery = "UPDATE campdata SET imagepath = '$imageLocation' WHERE num = $num;";


	if(mysqli_query($con, $sqlQuery)){

		file_put_contents($imageLocation, base64_decode($encodedImage));

		$result["status"] = TRUE;
		$result["remarks"] = "Image Uploaded Successfully";

	}else{

		$result["status"] = FALSE;
		$result["remarks"] = "Image Uploading Failed";

	}

	mysqli_close($con);

	print(json_encode($result));

?>