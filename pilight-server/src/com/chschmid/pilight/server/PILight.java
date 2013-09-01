/**
 * PILight
 * 
 * Copyright (C) 2013 Christian M. Schmid
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

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import com.chschmid.pilight.server.handler.LightServerInitializer;
import com.chschmid.pilight.server.handler.MorseServerInitializer;

/**
 * Provides PILight's main function
 * @author Christian M. Schmid
 */
public class PILight {
	public static boolean DEBUG = false;
	public static boolean cli = false;
	
	public static final String PILIGHT   = "pilight";
	public static final String TITLE     = PILIGHT + ", Copyright (C) 2013 Christian M. Schmid";
	public static final String ERROR_SPI = PILIGHT + ": Could not initialize SPI, check if SPI is available and if access is granted";
	public static final String ERROR_RF  = PILIGHT + ": Could not initialize RF";
	public static final String ERROR_CLI = "Try '" + PILIGHT + " --help' for more information.";
	
	public static final int LIGHT_SERVER_PORT = 22041;
	public static final int MORSE_SERVER_PORT = 22042;
	
	public static void main(String args[])  throws Exception {
		// Application Title
        System.out.println(TITLE);
        
		// Apache CLI Parser
		Options options = getCLIOptions();
		CommandLineParser parser = new PosixParser();

        try {
            CommandLine line = parser.parse( options, args);
            if(line.hasOption("c")) cli = true;
            if(line.hasOption("d")) DEBUG = true;
            if(line.hasOption("h")) {
            	printHelp();
            	return;
            }
        }
        catch( ParseException exp ) {
            System.out.println(PILIGHT + ": " + exp.getMessage());
            System.out.println(ERROR_CLI);
        	return;
        }
        
        // Init BoosterPack and check if SPI and RF work properly
        RFBoosterPack rf = new RFBoosterPack();
        
        if (rf.getSPIStatus() != RFBoosterPack.STATUS_SPI_INITIALIZED) {
        	System.out.println(ERROR_SPI);
        	return;
        }
        if (rf.getRFStatus() != RFBoosterPack.STATUS_RF_INITIALIZED) {
        	System.out.println(ERROR_RF);
        	return;
        }
        
        // Initialize nicer interface
        PiLightInterface light = new PiLightInterface(rf);
        try { Thread.sleep(10); } catch (InterruptedException e) {}
        
        if (cli) simpleCommandLineInterface(light);
        else     startServers(light);
	}
	
	/**
	 * Initializes the options for the Apache CLI Parser
	 */
	public static Options getCLIOptions() {
		Options options = new Options();
		options.addOption("c", false, "start in command line mode");
		options.addOption("d", false, "enable debug output");
		options.addOption("h","help",false,"display this help and exit");
		return options;
	}
	
	/**
	 * Prints some help to the standard output
	 */
	public static void printHelp() {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp(PILIGHT, getCLIOptions());
	}
	
	/**
	 * Starts PiLight without any servers, but a simple CLIO
	 * @param  light the PiLightInterface to operate on.
	 */
	private static void simpleCommandLineInterface(PiLightInterface light) {
		light.start();
		char x = 'y';

        while (x != 'q') {
        	System.out.print("Enter light number (1-3) or 'q' for quit: ");
        	try {
				x = (char) System.in.read();
				System.in.skip(System.in.available());
			} catch (IOException e) { }
        	if (x == '1') light.toggleLight(PiLightInterface.LIGHT1);
        	if (x == '2') light.toggleLight(PiLightInterface.LIGHT2);
        	if (x == '3') light.toggleLight(PiLightInterface.LIGHT3);
        }
        light.stop();
	}
	
	/**
	 * Starts who servers for pimorse and the more direct piglight interface
	 * @param  light the PiLightInterface to operate on.
	 */
	private static void startServers(PiLightInterface light) throws Exception {
		if (DEBUG) System.out.println("Starting servers");
		light.start();
		
		EventLoopGroup bossGroupPiLight   = new NioEventLoopGroup();
        EventLoopGroup workerGroupPiLight = new NioEventLoopGroup();
        EventLoopGroup bossGroupPiMorse   = new NioEventLoopGroup();
        EventLoopGroup workerGroupPiMorse = new NioEventLoopGroup();
        
        try {
            ServerBootstrap pilight = new ServerBootstrap();
            ServerBootstrap pimorse = new ServerBootstrap();
            
            pilight.group(bossGroupPiLight, workerGroupPiLight)
             .channel(NioServerSocketChannel.class)
             .childHandler(new LightServerInitializer(light));
            
            pimorse.group(bossGroupPiMorse, workerGroupPiMorse)
            .channel(NioServerSocketChannel.class)
            .childHandler(new MorseServerInitializer(light));
            
            pilight.bind(LIGHT_SERVER_PORT).sync();
            pimorse.bind(MORSE_SERVER_PORT).sync().channel().closeFuture().sync();    
        } finally {
        	bossGroupPiLight.shutdownGracefully();
        	workerGroupPiLight.shutdownGracefully();
        	bossGroupPiMorse.shutdownGracefully();
        	workerGroupPiMorse.shutdownGracefully();
        }
        
        light.stop();
	}
}
