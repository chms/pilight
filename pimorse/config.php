<?php

define('PIMORSE_SERVER_ADDRESS', "192.168.1.223"); // IP address of your PI running the pilight server - should of course be reachable from your webserver ;)
define('PIMORSE_SERVER_PORT',    22042);           // Port of your pimorse interface
define('LOGDB',                  'pimorse.db');    // File for the sqlite3 database (=logfile)
define('MAX_MSG_LENGTH',         160);             // Maximal length to which incoming message will be truncated. Note that the pimorse server also has a limit that should always be 6-7 times higher!

?>
