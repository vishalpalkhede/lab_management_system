<?php
session_start();
include "conn.php";
	$username = $_POST["user_name"];
	$password = $_POST["password"];
	$log_query = "SELECT * FROM users WHERE username LIKE '$username' AND password LIKE '$password';";
	$log_result = mysqli_query($conn, $log_query);
	$log_result = mysqli_fetch_assoc($log_result);
	$_SESSION['login_session'] = array('username' => $log_result["username"], 'lab_no' => $log_result["lab_no"], 'post' => $log_result["post"]);
	
	if($log_result){
		echo $_SESSION['login_session']['lab_no'];
		
	}
?>