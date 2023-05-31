<?php

include "database/db_config.php";

$response = array();

$sql = "SELECT * FROM myOrder_21410100050";

$result = mysqli_query($conn, $sql);

if(mysqli_num_rows($result) > 0){
  $response['orders'] = array();
  while($res = mysqli_fetch_array($result)){
    // $response[] = $res;
    
    $item = array();
    $item["id"] = $res['id'];
    $item["item"] = $res["item"];
  
    array_push($response["orders"], $item);
  }
  
}

echo json_encode($response);