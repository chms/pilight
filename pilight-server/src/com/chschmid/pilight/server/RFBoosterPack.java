/**
 * Copyright (C) 2013 Christian M. Schmid
 *
 * This file is part of the PILight.
 * 
 * PILight is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.chschmid.pilight.server;

import com.pi4j.wiringpi.Spi;

/**
 * Provides the direct interface to the CC110L RF Boosterpack, but already
 * targeted towards the 868 MHz command required by the
 * http://www.conrad.at/ce/de/product/620587/Funk-Lichtschalter-mit-Sender
 * @author Christian M. Schmid
 */
public class RFBoosterPack {
	public static short STATUS_0                  = 0;
	public static short STATUS_SPI_INITIALIZED    = 1;
	public static short STATUS_RF_INITIALIZED     = 1;
	public static short ERROR_SPI_NOT_INITIALIZED = 101;
	public static short ERROR_DATA_TOO_LONG       = 102;
	
	public static short SPI_COMMAND_SUCCESS       = 1;
	
	public static final int SPI_CHANNEL         = 0x0;
	public static final int SPEED               = 1000000;
	public static final int COMMAND_BUFFER_SIZE = 256;
	
	//********************* STROBE DEFINITIONS *********************
	public static short STROBE_RES    = 0x30; // Reset chip.
	public static short STROBE_FSTXON = 0x31; // Enable and calibrate frequency synthesizer (if MCSM0.FS_AUTOCAL=1). If in RX (with CCA):
	                                          // Go to a wait state where only the synthesizer is running (for quick RX / TX turnaround).
	public static short STROBE_XOFF   = 0x32; // Turn off crystal oscillator.
	public static short STROBE_CAL    = 0x33; // Calibrate frequency synthesizer and turn it off. SCAL can be strobed from IDLE mode without
	                                          // setting manual calibration mode (MCSM0.FS_AUTOCAL=0)
	public static short STROBE_RX     = 0x34; // In IDLE state: Enable RX. Perform calibration first if MCSM0.FS_AUTOCAL=1.
	public static short STROBE_TX     = 0x35; // In IDLE state: Enable TX. Perform calibration first if MCSM0.FS_AUTOCAL=1.
	                                          // If in RX state and CCA is enabled: Only go to TX if channel is clear.
	public static short STROBE_IDLE   = 0x36; // Enter IDLE state
                                      //0x37 - 0x38 Reserved
	public static short STROBE_PWD    = 0x39; // Enter power down mode when CSn goes high.
	public static short STROBE_FRX    = 0x3A; // Flush the RX FIFO buffer. Only issue SFRX in IDLE or RXFIFO_OVERFLOW states.
	public static short STROBE_FTX    = 0x3B; // Flush the TX FIFO buffer. Only issue SFTX in IDLE or TXFIFO_UNDERFLOW states.
	                                  //0x3C  // Reserved
	public static short STROBE_NOP    = 0x3D; // No operation. May be used to get access to the chip status byte.
	//**************************************************************
	
	//******************** REGISTER DEFINITIONS ********************
	public static short IOCFG2    = 0x00; // GDO2 output pin configuration
	public static short IOCFG1    = 0x01; // GDO1 output pin configuration
	public static short IOCFG0    = 0x02; // GDO0 output pin configuration
    public static short FIFOTHR   = 0x03; // RX FIFO and TX FIFO thresholds
    public static short SYNC1     = 0x04; // Sync word, high byte
	public static short SYNC0     = 0x05; // Sync word, low byte
	public static short PKTLEN    = 0x06; // Packet length
	public static short PKTCTRL1  = 0x07; // Packet automation control
	public static short PKTCTRL0  = 0x08; // Packet automation control
	public static short ADDR      = 0x09; // Device address
	public static short CHANNR    = 0x0A; // Channel number
	public static short FSCTRL1   = 0x0B; // Frequency synthesizer control
	public static short FSCTRL0   = 0x0C; // Frequency synthesizer control
	public static short FREQ2     = 0x0D; // Frequency control word, high byte
	public static short FREQ1     = 0x0E; // Frequency control word, middle byte
	public static short FREQ0     = 0x0F; // Frequency control word, low byte Yes 61
	public static short MDMCFG4   = 0x10; // Modem configuration
	public static short MDMCFG3   = 0x11; // Modem configuration
	public static short MDMCFG2   = 0x12; // Modem configuration
	public static short MDMCFG1   = 0x13; // Modem configuration
	public static short MDMCFG0   = 0x14; // Modem configuration
	public static short DEVIATN   = 0x15; // Modem deviation setting
	public static short MCSM2     = 0x16; // Main Radio Control State Machine configuration
	public static short MCSM1     = 0x17; // Main Radio Control State Machine configuration
	public static short MCSM0     = 0x18; // Main Radio Control State Machine configuration
	public static short FOCCFG    = 0x19; // Frequency Offset Compensation configuration
	public static short BSCFG     = 0x1A; // Bit Synchronization configuration
	public static short AGCTRL2   = 0x1B; // AGC control
	public static short AGCTRL1   = 0x1C; // AGC control
	public static short AGCTRL0   = 0x1D; // AGC control
	                             // 0x1E - 0x1F Not Used 0x20 RESERVED
	public static short FREND1    = 0x21; // Front end RX configuration
	public static short FREND0    = 0x22; // Front end TX configuration
	public static short FSCAL3    = 0x23; // Frequency synthesizer calibration
	public static short FSCAL2    = 0x24; // Frequency synthesizer calibration
	public static short FSCAL1    = 0x25; // Frequency synthesizer calibration
	public static short FSCAL0    = 0x26; // Frequency synthesizer calibration
	                             // 0x27 - 0x28 Not Used 0x29 - 0x2B RESERVED
	public static short TEST2     = 0x2C; // Various test settings
	public static short TEST1     = 0x2D; // Various test settings
	public static short TEST0     = 0x2E; // Various test settings
	public static short PARTNUM   = 0x30; // (0xF0) Part number for CC110L
    public static short VERSION   = 0x31; // (0xF1) Current version number
	public static short FREQEST   = 0x32; // (0xF2) Frequency Offset Estimate
	public static short CRC_REG   = 0x33; // (0xF3) CRC OK
	public static short RSSI      = 0x34; // (0xF4) Received signal strength indication
	public static short MARCSTATE = 0x35; // (0xF5) Control state machine state
	                             // 0x36 - 0x37 (0xF6 – 0xF7) Reserved
	public static short PKTSTATUS = 0x38; // (0xF8) urrent GDOx status and packet status
	                             // 0x39 (0xF9) Reserved
	public static short TXBYTES   = 0x3A; // (0xFA) Underflow and number of bytes in the TX FIFO
	public static short RXBYTES   = 0x3B; // (0xFB) Overflow and number of bytes in the RX FIFO 76
	//**************************************************************
	
	private short statusRF;
	private short statusSPI;
	
	private byte[] commandBuffer; // Command buffer
	
	public RFBoosterPack() {
		// Initialize variables
		commandBuffer = new byte[COMMAND_BUFFER_SIZE];
		statusRF      = STATUS_0;
		statusSPI     = STATUS_0;
		
		// Initialize SPI inferface and RF BoosterPack
		initSPI();
		initRFBoosterPack();
	}
	
	/**
	 * Reset RF Boosterpack by means of the reset strobe
	 * @return the status of the RF interface
	 */
	public short resetRFBoosterPack() {
		if (statusSPI != STATUS_SPI_INITIALIZED) {
			statusRF = ERROR_SPI_NOT_INITIALIZED;
			return statusRF;
		}
		
		// Reset strobe -> 5 ms delay -> Idle strobe -> Flush TX strobe
		writeStrobe(STROBE_RES);
		try { Thread.sleep(5); } catch (InterruptedException e) {}
		writeStrobe(STROBE_IDLE);
		writeStrobe(STROBE_FTX);
		return statusRF;
	}
	
	/**
	 * Configure RF Boosterpack and perform frequency calibration
	 * @return the status of the RF interface
	 */
	public short configureRFBoosterPack() {
		if (statusSPI != STATUS_SPI_INITIALIZED) {
			statusRF = ERROR_SPI_NOT_INITIALIZED;
			return statusRF;
		}
		
		configRegisters();
		configPATable();
		
		writeStrobe(STROBE_CAL);
		try { Thread.sleep(1); } catch (InterruptedException e) {}

		return statusRF;
	}
	
	/**
	 *  Configure RF power amplifier table for ASK
	 */
	public void configPATable(){
		commandBuffer[0] = (byte) 0x7E; // PA Table burst mode
		commandBuffer[1] = (byte) 0x00; // 0 =  0 dBm
		commandBuffer[2] = (byte) 0xC0; // 1 = 12 dBm
		readWriteSPI(commandBuffer, 3);
	}
	
	/**
	 * Configure RF register settings for
	 * 868.35 MHz, 1.6 Kbaud, ASK
	 */
	public void configRegisters(){
		writeRegister(FIFOTHR,  0x47);
		writeRegister(PKTLEN,   0x32);
		writeRegister(PKTCTRL0, 0x00);
		
		writeRegister(FREQ2, 0x20);
		writeRegister(FREQ1, 0x29);
		writeRegister(FREQ0, 0x3E);
		
		writeRegister(MDMCFG4, 0x85);
		writeRegister(MDMCFG3, 0xF1);
		writeRegister(MDMCFG2, 0x30);
		
		//writeRegister(MCSM0, 0x14);
		
		writeRegister(FREND0, 0x11);
		writeRegister(FSCAL3, 0xE9);
		writeRegister(FSCAL2, 0x2A);
		writeRegister(FSCAL1, 0x00);
		writeRegister(FSCAL0, 0x1F);
		writeRegister(TEST2,  0x81);
		writeRegister(TEST1,  0x35);
		writeRegister(TEST0,  0x09);
	}
	
	/**
	 *  Writes data into the CC110L TX FIFO
	 * @param command the data to be written to the TX FIFO
	 * @param length the lenght of the data
	 * @return the status of the RF interface
	 */
	public short writeTXFIFO(byte[] command, int length){
		if (length > (COMMAND_BUFFER_SIZE-1)) return ERROR_DATA_TOO_LONG;
		System.arraycopy(command, 0, commandBuffer, 1, command.length);
		commandBuffer[0] = (byte) 0x7F;
		return readWriteSPI(commandBuffer, length+1); 
	}
	
	/**
	 * Writes a command to the SPI interface, keeps the command array
	 * unchanged and returns SPI_COMMAND_SUCCESS on success
	 * @param command the command to be written to the SPI interface
	 * @param length the length of the command
	 * @return the status of the RF interface
	 */
	public short writeCommand(byte[] command, int length) {
		if (length > COMMAND_BUFFER_SIZE) return ERROR_DATA_TOO_LONG;
		System.arraycopy(command, 0, commandBuffer, 0, command.length);
		return readWriteSPI(commandBuffer, length); 
	}
	
	/**
	 * Writes a command to the SPI interface, reads data back to the
	 * command array and returns SPI_COMMAND_SUCCESS on success
	 * @param command the command to be written to the SPI interface,
	 *        will be overwritten by the data read
	 * @param length the length of the command
	 * @return the status of the RF interface
	 */
	public short readWriteCommand(byte[] command, int length) {
		return readWriteSPI(command, length);
	}
	
	/**
	 * Writes a single strobe to the SPI interface and returns the status byte
	 * @param strobe the strobe to be sent
	 * @return the status of the RF interface
	 */
	public short writeStrobe(short strobe) {
		commandBuffer[0] = (byte) strobe;
		readWriteSPI(commandBuffer, 1);
		return (short) commandBuffer[0];
	}
	
	/**
	 * Writes a single RF Boosterpack register and returns the latest status byte
	 * @param register the register to be written
	 * @param data the data to be written
	 * @return the status of the RF interface
	 */
	public short writeRegister(short register, short data) {
		commandBuffer[0] = (byte) register;
		commandBuffer[1] = (byte) data;
		readWriteSPI(commandBuffer, 2);
		return (short) commandBuffer[2];
    }
	
	/**
	 * Writes a single RF Boosterpack register and returns the latest status byte
	 * @param register the register to be written
	 * @param data the data to be written
	 * @return the status of the RF interface
	 */
	public short writeRegister(int register, int data) {
		return writeRegister((short) register, (short) data);
	}
	
	/**
	 * Checks if SPI is available and inits SPI interface
	 * @return the status of the SPI interface
	 */
	private short initSPI(){
        if (Spi.wiringPiSPISetup(0, SPEED) <= -1) {
        	statusSPI = ERROR_SPI_NOT_INITIALIZED;
        	return statusSPI;
        }
        statusSPI = STATUS_SPI_INITIALIZED; 
        return statusSPI;
	}
	
	/**
	 * Resets RF and initialize RF register settings
	 * @return the status of the RF interface
	 */
	private short initRFBoosterPack() {
		if (statusSPI == STATUS_SPI_INITIALIZED) {
			resetRFBoosterPack();
	        configureRFBoosterPack();
			statusRF = STATUS_RF_INITIALIZED;
		} else {
			statusRF = ERROR_SPI_NOT_INITIALIZED;
		}
		
        return statusRF;
	}
	
	/**
	 * Writes buffer to SPI and stores read data to buffer
	 * @param buffer data to be written to the SPI interface
	 *               will be overwritten with the data read
	 * @param length length of the write/read data
	 * @return the status of the RF interface
	 */
	private short readWriteSPI(byte[] buffer, int length) {
		if (statusSPI == STATUS_SPI_INITIALIZED){
			if (PILight.DEBUG) System.out.println("SPI TX: " + bytesToHexString(buffer, length));
			Spi.wiringPiSPIDataRW(SPI_CHANNEL, buffer, length);
			if (PILight.DEBUG) System.out.println("SPI RX: " + bytesToHexString(buffer, length));			
			return SPI_COMMAND_SUCCESS;
		}
		return ERROR_SPI_NOT_INITIALIZED;
	}
	
	public int getRFStatus() { return statusRF; }
	public int getSPIStatus() { return statusSPI; }
	
	/**
	 * Converts an array of bytes to a human-readable string
	 * @param bytes the byte array to be converted to a string
	 * @param length lenght of the byte array
	 * @return a human readable representation of the byte array
	 */
    public static String bytesToHexString(byte[] bytes, int length) {
        final char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        char[] hexChars = new char[length * 5];
        int v;
        for ( int j = 0; j < length; j++ ) {
            v = bytes[j] & 0xFF;
            hexChars[5*j]     = '0';
            hexChars[5*j + 1] = 'x';
            hexChars[5*j + 2] = hexArray[v >>> 4];
            hexChars[5*j + 3] = hexArray[v & 0x0F];
            hexChars[5*j + 4] = ' ';
        }
        return new String(hexChars);
    }
}
