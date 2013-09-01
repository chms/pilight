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

package com.chschmid.pilight.server.handler;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.chschmid.pilight.server.PILight;
import com.chschmid.pilight.server.PiLightInterface;

/**
 * The PiLight Server handler
 * @author Christian M. Schmid
 */
@Sharable
public class LightServerHandler extends SimpleChannelInboundHandler<String> {
	private PiLightInterface light;
    private static final Logger logger = Logger.getLogger(LightServerHandler.class.getName());
    
    public LightServerHandler(PiLightInterface light){
    	this.light = light;
    }

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // Send greeting for a new connection.
    }

    public void channelRead0(ChannelHandlerContext ctx, String request) throws Exception {
        boolean close = false;
        if (PILight.DEBUG) System.out.println("PiLight Server: " + request);
        if (request.equals("01t"))      light.toggleLight(PiLightInterface.LIGHT1);
        else if (request.equals("02t")) light.toggleLight(PiLightInterface.LIGHT2);
        else if (request.equals("03t")) light.toggleLight(PiLightInterface.LIGHT3);
        
        else if (request.equals("00m")) light.disableMorse(); 
        else if (request.equals("01m")) light.enableMorse(PiLightInterface.LIGHT1);
        else if (request.equals("02m")) light.enableMorse(PiLightInterface.LIGHT2);
        else if (request.equals("03m")) light.enableMorse(PiLightInterface.LIGHT3);
        
        else if (request.equals("bye")) close = true;

        if (close) ctx.close().addListener(ChannelFutureListener.CLOSE);
    }

    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.log(
                Level.WARNING,
                "Unexpected exception from downstream.", cause);
        ctx.close();
    }
}
