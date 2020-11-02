<?php

	include "conn.php";
	$username = $_POST["username"];
	$password = $_POST["password"];
	$fullname = $_POST["fullname"];
	$department = $_POST["department"];
	$lab_no = $_POST["lab_no"];

	$reg_query = "INSERT INTO users(fullname, department, username, password, lab_no) VALUES('$fullname', '$department', '$username', '$password', '$lab_no');";
	$reg_result = mysqli_query($conn, $reg_query);

	if($reg_result){
		echo "Registration Successful.";
	}
	else{
		echo "Registration Error!!";
	}

?>