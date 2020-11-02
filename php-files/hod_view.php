<?php
	include "conn.php";

	$view_query = "SELECT * FROM problems_table WHERE status = 'Not Solved' or deleted = 0;";

	$view_result = mysqli_query($conn, $view_query);
	while($row = mysqli_fetch_assoc($view_result)){
		$raw_result[] = $row;
	}

	$result["problems_data"] = $raw_result;

	$jsondata = json_encode($result);

	echo $jsondata;

?>