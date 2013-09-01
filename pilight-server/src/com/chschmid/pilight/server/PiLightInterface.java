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


/**
 * Provides an easy to use interface for both PiLight and PiMorse
 * However - this class is still far from complete and heavily under construction
 * @author Christian M. Schmid
 */
public class PiLightInterface {
	// Bedroom light 1
	public static final byte[] codeL1 = {(byte) 0x2C, (byte) 0xB6, (byte) 0xC9, (byte) 0x64, (byte) 0x92,
			                             (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x59,
			                             (byte) 0x6D, (byte) 0x92, (byte) 0xC9, (byte) 0x24, (byte) 0x00,
			                             (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xB2, (byte) 0xDB,
			                             (byte) 0x25, (byte) 0x92, (byte) 0x48, (byte) 0x00, (byte) 0x00,
			                        	 (byte) 0x00, (byte) 0x01, (byte) 0x65, (byte) 0xB6, (byte) 0x4B,
			                        	 (byte) 0x24, (byte) 0x90, (byte) 0x00, (byte) 0x00, (byte) 0x00,
			                        	 (byte) 0x02, (byte) 0xCB, (byte) 0x6C, (byte) 0x96, (byte) 0x49,
			                        	 (byte) 0x20, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x05,
			                        	 (byte) 0x96, (byte) 0xD9, (byte) 0x2C, (byte) 0x92, (byte) 0x40,
			                        	 
			                        	 (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x0B, (byte) 0x2D,
			                        	 (byte) 0xB2, (byte) 0x59, (byte) 0x24, (byte) 0x80, (byte) 0x00,
			                        	 (byte) 0x00, (byte) 0x00, (byte) 0x16, (byte) 0x5B, (byte) 0x64,
			                        	 (byte) 0xB2, (byte) 0x49, (byte) 0x00, (byte) 0x00, (byte) 0x00,
			                        	 (byte) 0x00};
	// Bedroom light 2
	public static final byte[] codeL2 = {(byte) 0x25, (byte) 0xB2, (byte) 0x5B, (byte) 0x24, (byte) 0x92,
	                                     (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x4B, 
	                                     (byte) 0x64, (byte) 0xB6, (byte) 0x49, (byte) 0x24, (byte) 0x00,
	                                     (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x96, (byte) 0xC9,
	                                     (byte) 0x6C, (byte) 0x92, (byte) 0x48, (byte) 0x00, (byte) 0x00,
	                                     (byte) 0x00, (byte) 0x01, (byte) 0x2D, (byte) 0x92, (byte) 0xD9,
	                                     (byte) 0x24, (byte) 0x90, (byte) 0x00, (byte) 0x00, (byte) 0x00,
	                                     (byte) 0x02, (byte) 0x5B, (byte) 0x25, (byte) 0xB2, (byte) 0x49,
	                                     (byte) 0x20, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x04,
	                                     (byte) 0xB6, (byte) 0x4B, (byte) 0x64, (byte) 0x92, (byte) 0x40,
	                                     
	                                     (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x09, (byte) 0x6C,
	                                     (byte) 0x96, (byte) 0xC9, (byte) 0x24, (byte) 0x80, (byte) 0x00,
	                                     (byte) 0x00, (byte) 0x00, (byte) 0x12, (byte) 0xD9, (byte) 0x2D,
	                                     (byte) 0x92, (byte) 0x49, (byte) 0x00, (byte) 0x00, (byte) 0x00,
	                                     (byte) 0x00};
	
	// Guest room light
	public static final byte[] codeL3 = {(byte) 0x2C, (byte) 0xB2, (byte) 0x49, (byte) 0x64, (byte) 0x92,
		                                 (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x59,
		                                 (byte) 0x64, (byte) 0x92, (byte) 0xC9, (byte) 0x24, (byte) 0x00,
		                                 (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xB2, (byte) 0xC9,
		                                 (byte) 0x25, (byte) 0x92, (byte) 0x48, (byte) 0x00, (byte) 0x00,
		                                 (byte) 0x00, (byte) 0x01, (byte) 0x65, (byte) 0x92, (byte) 0x4B,
		                                 (byte) 0x24, (byte) 0x90, (byte) 0x00, (byte) 0x00, (byte) 0x00,
		                                 (byte) 0x02, (byte) 0xCB, (byte) 0x24, (byte) 0x96, (byte) 0x49,
		                                 (byte) 0x20, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x05,
		                                 (byte) 0x96, (byte) 0x49, (byte) 0x2C, (byte) 0x92, (byte) 0x40,
		                                 
		                                 (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x0B, (byte) 0x2C,
		                                 (byte) 0x92, (byte) 0x59, (byte) 0x24, (byte) 0x80, (byte) 0x00,
		                                 (byte) 0x00, (byte) 0x00, (byte) 0x16, (byte) 0x59, (byte) 0x24,
		                                 (byte) 0xB2, (byte) 0x49, (byte) 0x00, (byte) 0x00, (byte) 0x00,
		                                 (byte) 0x00};
	
	public static final short LIGHT1      = 1;
	public static final short LIGHT2      = 2;
	public static final short LIGHT3      = 3;
	
	public static final short STATUS_STARTUP     =     -100;
	public static final short STATUS_IDLE        =      0;
	public static final short STATUS_DIM         =      1;
	public static final short STATUS_MORSE       =      1;
	public static final short STATUS_MORSE_MUTED =     -1;	
	public static final short STATUS_STOP_DIM    =     -1;
	public static final short STATUS_EXIT        =     -3;

	public static final short LIGHT_MAX   = LIGHT3;
	
	public static final int MORSE_BUFFER_SIZE = 524288;
	public static final int MORSE_TIME_UNIT   =    350;
		
	private RFBoosterPack  rf;
	
	// Treads for dimming and for sending Morse code
	private DimmThread   dimmThread;
	private MorseThread  morseThread;
	
	//private short dimLightID;
	private short morseLightID = 3;
	
	
	private short statusPiLight; // Either STATUS_STARTUP, STATUS_IDLE, STATUS_DIM, STATUS_STOP_DIM, STATUS_EXIT
	private short statusPiMorse; // Either STATUS_STARTUP, STATUS_MORSE_MUTED, STATUS_MORSE, STATUS_EXIT
	
	private MorseBuffer morseBuffer;
	
	private Object lock = new Object();
	
	public PiLightInterface(RFBoosterPack rf) {
		this.rf   = rf;
		statusPiLight = STATUS_STARTUP;
		statusPiMorse = STATUS_STARTUP;
		morseBuffer = new MorseBuffer(MORSE_BUFFER_SIZE);
	}
	
	public void toggleLight(short lightID) {
		synchronized (lock) {
		  if (statusPiLight == STATUS_IDLE) {
			  rf.writeStrobe(RFBoosterPack.STROBE_IDLE);
			  rf.writeStrobe(RFBoosterPack.STROBE_FTX);
			  rf.writeStrobe(RFBoosterPack.STROBE_CAL);
			  rf.writeStrobe(RFBoosterPack.STROBE_FSTXON);
			  try { Thread.sleep(1); } catch (InterruptedException e) {}
			  
			  if      (lightID == 1) rf.writeTXFIFO(codeL1, 50);
			  else if (lightID == 2) rf.writeTXFIFO(codeL2, 50);
			  else if (lightID == 3) rf.writeTXFIFO(codeL3, 50);
			  
			  rf.writeStrobe(RFBoosterPack.STROBE_TX);
		  }
		}
	}
	
	synchronized public void startDimming(short lightNr) {
		// TODO: Dimming
		/*if (statusPiLight == STATUS_STARTUP || statusPiLight == STATUS_EXIT) return;
		synchronized (lock) {
			if (statusPiLight == STATUS_IDLE) {
				if (lightNr <= LIGHT_MAX) statusPiLight = lightNr;
				dimmThread.notify();
		    }
		}*/ 
	}
	
	synchronized public void stopDimming(short lightNr) {
		// TODO: Dimming
		/*if (statusPiLight == STATUS_STARTUP || statusPiLight == STATUS_EXIT) return;
		synchronized (lock) {
			if (statusPiLight == lightNr) {
				statusPiLight = STATUS_STOP;
				dimmThread.notify();
		    }
		}*/
	}
	
	public void start() {
		dimmThread = new DimmThread();
		dimmThread.start();
		
		morseThread = new MorseThread();
		morseThread.start();
		
		statusPiLight = STATUS_IDLE;
		statusPiMorse = STATUS_MORSE_MUTED;
	}
	
	public void stop() {
		/*synchronized (loopLight) {
				status = STATUS_EXIT;
				loopLight.notify();
		}*/
		
		synchronized (morseThread) {
			statusPiMorse = STATUS_EXIT;
			morseThread.notify();
		}
	}
	
	// ------------------ Interface for PiMorse ------------------
	public void enableMorse(short lightID) {
		morseLightID  = lightID;
		statusPiMorse = STATUS_MORSE;
	}
	
	public void disableMorse() {
		statusPiMorse = STATUS_MORSE_MUTED;
	}
	
	public void addMorseCode(String morseCode) {
		synchronized (morseThread) {
			if(morseBuffer.getAvailableBufferLength() > morseCode.length()) {
				for (int k1 = 0; k1 < morseCode.length(); k1++) morseBuffer.addLast(morseCode.charAt(k1));
				morseThread.notify();
			}
        }
	}
	// -----------------------------------------------------------
	
	private class DimmThread extends Thread {
		
		public void run() {
			// TODO: Implement function for dimming!
			
			/*byte [] buffer;
			//short fifoBytesAvailable = 0;
			//int bufferIndex;
			while (status != STATUS_EXIT) {
				
				if (status >= STATUS_L1 && status <= STATUS_L3) {
					rf.writeStrobe(RFBoosterPack.STROBE_IDLE);
					
					buffer = null;
					if (status == STATUS_L1) buffer = codeL1.clone();
					if (status == STATUS_L2) buffer = codeL2.clone();
					if (status == STATUS_L3) buffer = codeL3.clone();
					
					rf.writeStrobe(RFBoosterPack.STROBE_FTX);
					rf.writeStrobe(RFBoosterPack.STROBE_CAL);
					rf.writeStrobe(RFBoosterPack.STROBE_FSTXON);
					
					rf.writeTXFIFO(buffer, 50);
					//bufferIndex = 50;
					rf.writeStrobe(RFBoosterPack.STROBE_TX);
					
					while(status != STATUS_STOP && status != STATUS_EXIT) {
						synchronized (loopLight) {
							try { loopLight.wait(50);} catch ( InterruptedException e ) { }
						}
					}
				}
				
				synchronized (loopLight) {
					try {
						rf.writeStrobe(RFBoosterPack.STROBE_IDLE);
						rf.writeStrobe(RFBoosterPack.STROBE_FTX);
						status = STATUS_IDLE;
						if (status != STATUS_EXIT) loopLight.wait();
					} catch ( InterruptedException e ) { }
				}
			}*/
			//statusPiMorse = STATUS_IDLE;
		}
		
	}
	
	private class MorseThread extends Thread {
		public void run() {
			char c;
			while (statusPiMorse != STATUS_EXIT) {
				if (morseBuffer.getAvailableDataLength() > 0) {
					c = morseBuffer.readFirst();
					if (statusPiMorse == STATUS_MORSE){
						if (c == '.') dit();
						else if (c == '-') dah();
						else if (c == ' ') space();
					}
				} else {
					synchronized (morseThread) {
						try {
							if (morseBuffer.getAvailableDataLength() == 0) morseThread.wait();
						} catch ( InterruptedException e ) { }
					}
				}
			}
		}
		
		private void dit() {
			toggleLight(morseLightID);
			mySleep(MORSE_TIME_UNIT);
			toggleLight(morseLightID);
			mySleep(MORSE_TIME_UNIT);
		}
		
		private void dah() {
			toggleLight(morseLightID);
			mySleep(3*MORSE_TIME_UNIT);
			toggleLight(morseLightID);
			mySleep(MORSE_TIME_UNIT);
		}
		
		private void space() {
			mySleep(2*MORSE_TIME_UNIT);
		}
		
		private void mySleep(int time) {
			try {
				sleep(time);
			} catch (InterruptedException e) {}
		}
	}
	
	public static class MorseBuffer {
		public static final int DEFAULT_SIZE = 1024;
		
		private char[] buffer;
		private int size;
		private int start, end;
		
		int availableBufferSize;
		
		public MorseBuffer() {
			initBuffer(DEFAULT_SIZE);
		}
		
		public MorseBuffer(int size) {
			initBuffer(size);
		}
		
		private void initBuffer(int size) {
			start = 0;
			end   = 0;
			this.size = size;
			buffer = new char[size];
			availableBufferSize = size;
		}
		
		public void addLast(char c) {
			if (availableBufferSize == 0) return;
			buffer[end] = c;
			availableBufferSize = availableBufferSize - 1;
			end = normalizePosition(end + 1);
		}
		
		public void addFirst(char c) {
			if (availableBufferSize == 0) return;
			start = normalizePosition(start - 1);
			buffer[start] = c;
			availableBufferSize = availableBufferSize - 1;
		}
		
		public char readFirst() {
			char c = 0;
			if (availableBufferSize == size) return c;
			c = buffer[start];
			availableBufferSize = availableBufferSize + 1;
			start = normalizePosition(start + 1);
			return c;
		}
		
		public char readLast() {
			char c = 0;
			if (availableBufferSize == size) return c;
			end = normalizePosition(end - 1);
			c = buffer[end];
			availableBufferSize = availableBufferSize + 1;
			return c;
		}
		
		private int normalizePosition(int position) {
			if (position < 0)     position = size-position;
			if (position >= size) position = position - size;
			return position;
		}
		
		public int getAvailableDataLength() {return size - availableBufferSize;}
		public int getAvailableBufferLength() {return size;}
		public int getSize() {return size;}
	}
}
