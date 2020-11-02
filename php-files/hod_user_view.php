<?php
	include "conn.php";

	$view_query = "SELECT fullname, lab_no, department, post FROM users;";

	$view_result = mysqli_query($conn, $view_query);
	while($row = mysqli_fetch_assoc($view_result)){
		$raw_result[] = $row;
	}

	$result["users"] = $raw_result;

	$jsondata = json_encode($result);

	echo $jsondata;

?>