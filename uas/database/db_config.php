<?php

$hostname = 'localhost';
$username = "root";
$password = '';
$db = "uas_pbm";

$conn = mysqli_connect($hostname, $username, $password, $db);
session_start();

if(!$conn){
  echo "gagal connect, " . mysqli_connect_error();
  die();
}


?>