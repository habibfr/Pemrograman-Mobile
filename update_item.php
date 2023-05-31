<?php

include "database/db_config.php";

$response = array();

$kode_item = $_GET['id'];
// echo $kode_barang;

$sql = "select * from myOrder_21410100050 where id= '$kode_item'";
$result = mysqli_query($conn, $sql);
$row  = mysqli_num_rows($result);
// echo $row;

if ($row > 0) {
  $data = mysqli_fetch_assoc($result);
  $item = $data['item'];
}

if (isset($_POST['ubah'])) {
  // $id = $_GET['id'];
  // $item = $_POST['item'];

  // if (empty($kode_barang) || empty($nama_barang) || empty($satuan) || empty($harga_beli) || empty($harga_jual)) {
  //   echo "Data tidak boleh kosong";
  //   exit();
  // }

  $item = $_POST['item'];

  $sql = "UPDATE `myorder_21410100050` SET `item` = '$item' WHERE `myorder_21410100050`.`id` = $kode_item";

  $result = mysqli_query($conn, $sql);

  if ($result) {
    $response["success"] = 1;
    $response["message"] = "succesfully update";
  } else {
    $response["success"] = 0;
    $response["message"] = "failed to update";
    // echo "gagal hapus data";
  }
  echo json_encode($response);
}

?>

<!DOCTYPE html>
<html lang="en">

</html>

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>

<body>
  <form action="" method="post">
    <input type="text" name="item" id="item" value="<?php echo $item; ?>">
    <input type="submit" value="ubah" name="ubah">
  </form>
</body>

</html>