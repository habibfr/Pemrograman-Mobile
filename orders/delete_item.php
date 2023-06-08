<?php

include "../database/db_config.php";

$response = array();

// echo $response;

if (isset($_GET["id"])) {
  $id = $_GET["id"];

  // $item = $_GET["item"];

  $result = mysqli_query($conn, "delete from  myOrder_21410100050 where id='$id'");

  if ($result) {
    // $_SESSION["alert"] = [
    //   'type' => 'deleted',
    //   'title' => 'Deleted',
    //   'message' => 'The employee data has been removed.',
    // ];


    // $response['success'] = 1;
    // $response['message'] = "success delete";
    // header("Location: read_all_order.php");

    $response["success"] = 1;
    $response["message"] = "succesfully delete";
  } else {
    $response["success"] = 0;
    $response["message"] = "failed to delete";
    // echo "gagal hapus data";
  }
  echo json_encode($response);
  // echo json_encode($response);
}
