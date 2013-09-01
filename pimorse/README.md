## pimorse-webserver

The pimorse-webserver is a collection of php script to be deployed on a webserver, which should have a way to connect to the pilight-server running on a Raspberry PI.

Visit http://pimorse.chschmid.com for a demo.

pimorse-webserver consists of

* config.php
* morse.php
* log.php
* pimorse.db

### config.php

For basic configurations

### morse.php

Accepts plaintext messages via a GET parameter 'msg' which is converted to Morse code after all special characters have been removed. The Morse code message is then passed to the pimorse interface of a pilight-server running on a PI. Every message is logged in an sqlite3 database.

### log.php

Interfaces the sqlite3 database 'pimorse.db' and return all messages that have been sent, or only the latest x messages when the GET parameter 'limit' is set to x.

### pimorse.db

The sqlite3 database file.

