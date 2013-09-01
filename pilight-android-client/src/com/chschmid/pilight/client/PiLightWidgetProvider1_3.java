package com.chschmid.pilight.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.widget.RemoteViews;

public class PiLightWidgetProvider1_3 extends AppWidgetProvider {
	public static String CLICK_LIGHT1 = "com.chschmid.pilight.client.light1";
	public static String CLICK_LIGHT2 = "com.chschmid.pilight.client.light2";
	public static String CLICK_LIGHT3 = "com.chschmid.pilight.client.light3";
	
	private static HandlerThread sWorkerThread;
    private static Handler sWorkerQueue;

    public PiLightWidgetProvider1_3() {
        // Start the worker thread
        sWorkerThread = new HandlerThread("PiLightWidget1_3-worker");
        sWorkerThread.start();
        sWorkerQueue = new Handler(sWorkerThread.getLooper());
    }
	
    private RemoteViews buildLayout(Context context, int appWidgetId, boolean largeLayout) {
    	
        RemoteViews rv;
        rv = new RemoteViews(context.getPackageName(), R.layout.widget_layout1);
        
        final Intent onClickLight1Intent = new Intent(context, PiLightWidgetProvider1_3.class);
        onClickLight1Intent.setAction(PiLightWidgetProvider1_3.CLICK_LIGHT3);
        final PendingIntent onClickLight1PendingIntent = PendingIntent.getBroadcast(context, 0, onClickLight1Intent, PendingIntent.FLAG_UPDATE_CURRENT);
        rv.setOnClickPendingIntent(R.id.imageView1_1, onClickLight1PendingIntent);
        
        return rv;
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // Update each of the widgets with the remote adapter
        /*for (int i = 0; i < appWidgetIds.length; ++i) {
            RemoteViews layout = buildLayout(context, appWidgetIds[i], mIsLargeLayout);
            appWidgetManager.updateAppWidget(appWidgetIds[i], layout);
        }*/
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }
    
    @Override
    public void onReceive(Context ctx, Intent intent) {
        final String action = intent.getAction();
        if (action.equals(CLICK_LIGHT1)) {
            // BroadcastReceivers have a limited amount of time to do work, so for this sample, we
            // are triggering an update of the data on another thread.  In practice, this update
            // can be triggered from a background service, or perhaps as a result of user actions
            // inside the main application.
            sWorkerQueue.removeMessages(0);
            sWorkerQueue.post(new Runnable() {
                @Override
                public void run() {
                	toggleLight(1);
                }
            });

           
        } else if (action.equals(CLICK_LIGHT2)) {
            sWorkerQueue.removeMessages(0);
            sWorkerQueue.post(new Runnable() {
                @Override
                public void run() {
                	toggleLight(2);
                }
            });
        } else if (action.equals(CLICK_LIGHT3)) {
            sWorkerQueue.removeMessages(0);
            sWorkerQueue.post(new Runnable() {
                @Override
                public void run() {
                	toggleLight(3);
                }
            });
        }

        super.onReceive(ctx, intent);
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
    	RemoteViews layout;
        /*int minWidth = newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH);
        int maxWidth = newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_WIDTH);
        int minHeight = newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT);
        int maxHeight = newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_HEIGHT);

        
        if (minHeight < 100) {
            mIsLargeLayout = false;
        } else {
            mIsLargeLayout = true;
        }*/
        layout = buildLayout(context, appWidgetId, true);
        appWidgetManager.updateAppWidget(appWidgetId, layout);
    }
    
    private void toggleLight(int iD) {
    	Socket clientSocket = new Socket();
    	try {
    		clientSocket.connect(new InetSocketAddress("192.168.1.223", 22041), 3000);
    		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
    		if (iD == 1) out.println("01t");
    		if (iD == 2) out.println("02t");
    		if (iD == 3) out.println("03t");
    		out.println("bye");
    		clientSocket.close();
    		Log.d("pilight","sentmsg");
		} catch (IOException e) {
		}
    	Log.d("pilight","sentmsg");
    }
}
