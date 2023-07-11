<?php
include '../database/db_config.php';

if (isset($_POST['user_id'])) {
    $user = $_POST['user_id'];

    // cari transaksi hari ini
    $querySQL = "SELECT transaction_id, user_id, title, date, type, amount, COALESCE(additional_info, 'Kosong'), created_at FROM `transactions` WHERE user_id = 1 AND DATE_FORMAT(date, '%d') = DATE_FORMAT(CURRENT_DATE, '%d') ORDER BY date DESC";
    $stmt = $conn->prepare($querySQL);

    if ($stmt) {
        $stmt->bind_param('i', $user);
        $stmt->execute();

        $result = $stmt->get_result();

        if ($result->num_rows > 0) {
            $rows = array();
            while ($row = $result->fetch_assoc()) {
                $rows[] = $row;
            }

            $myObj = new stdClass();
            $myObj->status = 1;
            $myObj->message = "Get Transaksi Berhasil";
            $myObj->data = $rows;
        } else {
            $myObj = new stdClass();
            $myObj->status = 0;
            $myObj->message = "Tidak ada transaksi";
        }
    } else {
        $myObj = new stdClass();
        $myObj->status = 0;
        $myObj->message = "Error preparing statement for transaction: .$conn->error";
    }
} else {
    $myObj = new stdClass();
    $myObj->status = 0;
    $myObj->message = "Gagal mencari transaksi. Parameter yang dibutuhkan tidak ada";
}
echo json_encode($myObj);