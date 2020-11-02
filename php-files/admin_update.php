<?php
	include "conn.php";

	$lab_no = $_POST["lab_no"];
	$pc_no = $_POST["pc_no"];
	$desc = $_POST["description"];
	$status = $_POST["status"];
	$admin_query = "UPDATE problems_table SET status = '$status' WHERE lab_no='$lab_no' AND pc_no = '$pc_no' AND description = '$desc';";

	$admin_result = mysqli_query($conn, $admin_query);
	if($admin_result){
		echo "Problem Updated.";
	}
	else{
		echo "Error while updating.";
	}
	


?>