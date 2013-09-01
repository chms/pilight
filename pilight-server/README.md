## pilight-server

The pilight-server is Java software built to be running on a Raspberry PI. pilight-server can talk to TI's CC110L RF Boosterpack via SPI and offers two different socket interfaces.

See pilight.chschmid.com and pimorse.chschmid.com for more information.

### Requirements

Althoug pilight-server is Java software, it is designed to be running on a hard-float Raspberry PI, as it uses some native components via JNI, which are also part of the jar.

### PiLight Interface

The PiLight Interface (default port 22041) accepts the following commands

*01t
*02t
*03t
*00m
*01m
*02m
*03m
*bye

Each command has to be followed by a newline to be executed. '0*t' lets the Boosterpack send an 868 MHz command to toggle light x. '00m' disables the PiMorse function (the socket interface is still running, but no light will be toggled) while '0*m' sets the light used for sending out Morse code. 'bye' closes the connection.

### PiLight Interface

The PiMorse Interface (default port 22042) accepts Morse code containing the characters '.', '-' and ' '.

*'.' will keep the light on for one unit and off for one unit
*'-' will keep the light on for three units and off for one unit
*' ' will keep the light off for two units

Any Morse code message has to be terminated by a newline to be transmitted. E.g. when opening a connection to 22042 and writing '... --- ...\n' the pilight server will send 'SOS'.

### Installing PiLight Server

1. Install Java on your PI (e.g. on Raspbian via "sudo apt-get install openjdk-7-jre")
2. Make sure you have the module spi-bcm2708 loaded either by adding it to your /etc/modules or by “sudo modprobe spi-bcm2708″
3. Make sure your user has access to /dev/spidev0.0, e.g. by adapting your udev rules or by changing the owner of /dev/spidev0.0 manually
2. Copy the subdirectory "pilight-server" to your server and go to this directory in your terminal.
3. Run the installer script via 'sudo ./install.sh'

### Open Source Licenses

pilight-server uses the following Open Source libraries. The licenses can be found in the lib folder

* netty.io, http://netty.io/
* J4PI, http://pi4j.com/
* Apache-CLI, http://commons.apache.org/proper/commons-cli/

