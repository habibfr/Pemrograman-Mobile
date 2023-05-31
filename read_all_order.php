<?php

include "database/db_config.php";

$sql = "SELECT * FROM myOrder_21410100050";

$result = mysqli_query($conn, $sql);


// if (isset($_SESSION['alert'])) {
// echo '
//   <div class="d-flex justify-content-center ' . $_SESSION['alert']['type'] . '">
//       <h3>' . $_SESSION['alert']['title'] . '</h3>
//       <div class="alert alert-success w-75" role="alert">
//           ' . $_SESSION['alert']['message'] . '
//       </div>
//   </div>
//   ';
// remove the session, for display this alert just one time.
// unset($_SESSION['alert']);
// }

echo "<h1>Daftar Item</h1>";
echo "<a href='take_order.php'><button>+</button></a>";
echo "<table border='1'>";
echo "<tr><th>ID</th><th>Item</th><th>Action</th></tr>";
echo "<tbody>";

if (mysqli_num_rows($result) > 0) {
  $response["orders"] = array();
  while ($row = mysqli_fetch_assoc($result)) {
    echo "<tr>";
    echo "<td>" . $row["id"] . "</td>";
    echo "<td>" . $row["item"] . "</td>";
    echo "<td>";
    echo "<a href='update_item.php?id=" . $row["id"] . "'><button>U</button></a>";
    echo "<div style='display:inline-block; width:10px;'></div>";
    echo "<a href='delete_item.php?id=" . $row["id"] . "'><button>-</button></a>";
    echo "</td>";
    echo "</tr>";
    $item = array();

    $item['id'] = $row['id'];
    $item['item'] = $row['item'];

    array_push($response["orders"], $item);
  }
  $response["success"] = 1;
} else {
  $response["success"] = 0;
  $response["message"] = "No items found";
}
// echo json_encode($response);
// echo "<div style='display:inline-block; width:10px;'></div>";
// echo "<a href='fetch_data.php'><button>fetch</button></a>";
