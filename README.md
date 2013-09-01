## pilight

pilight consists of multiple software components. At it's core it is a Java server running on a Raspberry PI which interfaces TI's CC110L RF Booster Pack via SPI to send 868 MHz commands to a dimmer switch.

pilight consists of

* pilight-server: A Java server running on Raspberry PI which offers two different socket interfaces;
* pilight-android-client: Android software to tell the server to send dimmer switch commands;
* pimorse: PHP Scripts to be deployed on a public webserver, which allow everyone in the world to send Morse messages

Check out http://pilight.chschmid.com and http://pimorse.chschmid.com to learn more about my little weekend project



