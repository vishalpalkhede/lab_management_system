<?php
	include "conn.php";

	$desc = $_POST["description"];
	$lab_no = $_POST["lab_no"];
	$pc_no = $_POST["pc_no"];

	$add_query = "INSERT INTO problems_table(pc_no, status, lab_no, description) VALUES('$pc_no', 'Not Solved', '$lab_no', '$desc');";

	$add_result = mysqli_query($conn, $add_query);

	if($add_result){
		echo "Problem Added!";
	}

?>