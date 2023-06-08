<?php
include "../database/db_config.php";

$response = array();

// if (isset($_POST['login'])) {
if (isset($_POST['username']) && isset($_POST['username'])) {
  $username = $_POST['username'];
  $password = $_POST['password'];

  $sql = "select * from users_21410100050 where username= '$username' and password='$password'";
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
    $response["message"] = "Failed to login, invalid username or password";
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
    <label for="username">Username : </label><br>
    <input type="text" name="username" id="username"><br>
    <label for="password">Password : </label><br>
    <input type="text" name="password" id="password"><br>
    <input type="submit" value="login" name="login">
  </form>
</body>

</html>