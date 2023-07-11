<?php

include "../database/db_config.php";

$response = array();

// echo $kode_barang;

if (isset($_POST['user_id'])) {
  $kode_user = $_POST['user_id'];
  $fullname = $_POST['fullname'];
  $email = $_POST['email'];
  $password = $_POST['password'];

  $sql = "UPDATE `users` SET `fullname` = '$fullname', `email` = '$email', `password` = '$password' WHERE `users`.`user_id` = $kode_user";

  $result = mysqli_query($conn, $sql);

  if ($result) {
    $response["success"] = 1;
    $response["message"] = "succesfully update";
  } else {
    $response["success"] = 0;
    $response["message"] = "failed to update";
  }
  echo json_encode($response);
  exit();
}
