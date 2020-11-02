<?php
	include "conn.php";
	$lab_no = $_POST["lab_no"];
	$json_raw = array();
	$query = "SELECT * FROM problems_table WHERE lab_no = '$lab_no' AND deleted = 0;";
	$query_result = mysqli_query($conn, $query);
	while($row = mysqli_fetch_assoc($query_result)){
		$json_raw[] = $row;
	}

	$json_result["incharge_view"] = $json_raw;
	$json_result = json_encode($json_result);
	
		echo $json_result;
	
?>
	
	