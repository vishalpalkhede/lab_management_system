<?php
	include "conn.php";

	$fullname = $_POST["fullname"];
	$dept = $_POST["department"];
	$lab_no = $_POST["lab_no"];
	$post = $_POST["post"];

	$post_query = "UPDATE users SET post = '$post' WHERE fullname = '$fullname' AND department = '$dept' AND lab_no = '$lab_no'; ";

	$post_result = mysqli_query($conn, $post_query);

	if($post_result){
		echo "Post changed.";
	}

	else{
		echo "Error!";
	}



?>