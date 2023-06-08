<?php

include "../database/db_config.php";

if (isset($_POST["item"])) {
  $response = array();

  $item = $_POST["item"];

  if (empty(trim($item))) {
    echo "please isi field";
    exit();
  }

  $sql = "INSERT INTO `myorder_21410100050` (`id`, `item`) VALUES (NULL, '$item')";
  $result = mysqli_query($conn, $sql);

  if ($result > 0) {
    $response["success"] = 1;
  } else {
    $response["success"] = 0;
  }

  echo json_encode($response);
  exit();

  // if($result){
  //   $_SESSION["alert"] = [
  //     'type' => 'insert',
  //     'title' => 'Insert',
  //     'message' => 'The data has been add.',
  //   ];

  //   header("Location: read_all_order.php");
  // }else{
  //   echo "gagal insert data";
  // }
}
?>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>

<body>
  <form action="" method="post">
    <input type="text" name="item" id="item">
    <input type="submit" value="simpan" name="simpan">
  </form>
</body>

</html>