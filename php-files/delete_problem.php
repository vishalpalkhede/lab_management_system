<?php

	require "conn.php";

	$pc_no = $_POST["pc_no"];
	$lab_no = $_POST["lab_no"];
	$desc = $_POST["description"];

	$del_query = "UPDATE problems_table SET deleted = 1 WHERE lab_no='$lab_no' AND pc_no = '$pc_no' AND description = '$desc';";

	$del_result = mysqli_query($conn, $del_query);

	if($del_result){
		echo "Deleted successfully.";
	}
	else{
		echo "Error while deleting";
	}
?>