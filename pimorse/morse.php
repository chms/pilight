<?php

// Load socket and logfile configuration
require_once("config.php");

// Morse code transformation array
$morsetransform = array( " " => "  ", 
                          "A" => ".- ",
                          "B" => "-... ",
                          "C" => "-.-. ",
                          "D" => "-.. ",
                          "E" => ". ",
                          "F" => "..-. ",
                          "G" => "--. ",
                          "H" => ".... ",
                          "I" => ".. ",
                          "J" => ".--- ",
                          "K" => "-.- ",
                          "L" => ".-.. ",
                          "M" => "-- ",
                          "N" => "-. ",
                          "O" => "--- ",
                          "P" => ".--. ",
                          "Q" => "--.- ",
                          "R" => ".-. ",
                          "S" => "... ",
                          "T" => "- ",
                          "U" => "..- ",
                          "V" => "...- ",
                          "W" => ".-- ",
                          "X" => "-..- ",
                          "Y" => "-.-- ",
                          "Z" => "--.. ",
                          
                          "1" => ".---- ",
                          "2" => "..--- ",
                          "3" => "...-- ",
                          "4" => "....- ",
                          "5" => "..... ",
                          "6" => "-.... ",
                          "7" => "--... ",
                          "8" => "---.. ",
                          "9" => "----. ",
                          "0" => "----- ");

// Reformat plaintext and convert to Morse message
$plaintext = $_GET["msg"];
$textmsg   = substr($plaintext, 0, MAX_MSG_LENGTH);
$textmsg   = strtoupper($textmsg);
$textmsg   = preg_replace("/[^A-Z 0-9\-]/", "", $textmsg);
$morsemsg  = strtr($textmsg, $morsetransform);
$morsemsgn = $morsemsg . "\n";

// Connect to pilight server
$socket = socket_create(AF_INET, SOCK_STREAM, SOL_TCP);
socket_connect($socket, PIMORSE_SERVER_ADDRESS, PIMORSE_SERVER_PORT);
socket_write($socket, $morsemsgn, strlen($morsemsgn));
socket_close($socket);

// Log morse message as reformated plaintext
$sqlLog = "INSERT INTO log (timestamp, msg) VALUES (DATETIME(), \"" . $textmsg ."\")";
$db = new SQLite3(LOGDB);
$db->exec($sqlLog);
$db->close();

// Return reformated plaintext and Morse message
$result = "<div class=\"textmsg\">You just sent</br>\"" . $textmsg . "\"</div>\n";
$result = $result . "<div class=\"morsemsg\">" . str_replace(" ", "&nbsp;", $morsemsg) . "</div>\n";
echo $result;

?>
