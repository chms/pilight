<?php
// Load logfile configuration
require_once("config.php");

// Supports limited number of logs
if(isset($_GET["limit"])) {
    $myQuery = "SELECT * FROM log ORDER BY id DESC LIMIT " . $_GET["limit"];
} else {
    $myQuery = "SELECT * FROM log ORDER BY id DESC";
}

// Database access
$db = new SQLite3(LOGDB);
$results = $db->query($myQuery);

while ($row = $results->fetchArray()) {
    echo "<div class=\"pimorselog\">";
    echo "<div class= \"timestamp\">" . $row['timestamp'] . "</div>";
    echo "<div class= \"message\">" . $row['msg'] . "</div>";
    echo "</div>";
}

$db->close();
?>
