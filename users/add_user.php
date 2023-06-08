<?php

include "../database/db_config.php";

// if (isset($_POST["simpan"])) {
$response = array();

if (isset($_POST["nama"]) && isset($_POST["username"]) && isset($_POST["password"])) {
  $nama = $_POST["nama"];
  $username = $_POST["username"];
  $password = $_POST["password"];

  if (empty(trim($nama)) || empty(trim($username)) || empty(trim($password))) {
    echo "please isi field";
    exit();
  }


  $sql = "INSERT INTO `users_21410100050` (`id`, `nama`, `username`, `password`) VALUES (NULL, '$nama', '$username', '$password');";
  $result = mysqli_query($conn, $sql);

  if ($result > 0) {
    $response["success"] = 1;
    $response["message"] = "success add user!";
  } else {
    $response["success"] = 0;
    $response["message"] = "gagal add user!";
  }

  echo json_encode($response);
  exit();
}
// }
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
    <label for="nama">Nama : </label>
    <input type="text" name="nama" id="nama"><br>
    <label for="username">Username : </label>
    <input type="text" name="username" id="username"><br>
    <label for="password">Password : </label>
    <input type="text" name="password" id="password">
    <br>
    <input type="submit" value="simpan" name="simpan">
  </form>
</body>



</html>