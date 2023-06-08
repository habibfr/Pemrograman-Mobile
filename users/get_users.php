<?php

include "../database/db_config.php";

$response = array();

$sql = "SELECT * FROM users_21410100050";

$result = mysqli_query($conn, $sql);

if (mysqli_num_rows($result) > 0) {
  $response['users'] = array();
  while ($row = mysqli_fetch_array($result)) {

    $item = array();
    $item['id'] = $row['id'];
    $item['nama'] = $row['nama'];
    $item['username'] = $row['username'];
    $item['password'] = $row['password'];

    array_push($response["users"], $item);
  }

  $response["success"] = 1;
} else {
  $response["success"] = 0;
  $response["message"] = "No users found";
}

echo json_encode($response);
exit();
