<?php

$hostname = 'localhost';
$username = "root";
$password = '';
$db = "orders_21410100050";

$conn = mysqli_connect($hostname, $username, $password, $db);
session_start();

if(!$conn){
  echo "gagal connect, " . mysqli_connect_error();
  die();
}


?>