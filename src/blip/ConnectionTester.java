/*
 * blip
 * Attribute Only (Public) License
    Version 0.a5, Feb 07, 2012
    
Copyright (C) 2012 Blake Bartenbach <SeeD419@gmail.com> (@SeeD419)

Anyone is allowed to copy and distribute verbatim or modified 
copies of this license document and altering is allowed as long 
as you attribute the author(s) of this license document / files.
 */
package blip;

import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author seed419
 */
public class ConnectionTester extends Thread {
    
    
    private boolean connection;
    private static boolean lastConnect;
    private TrayHandler th;
    private int failedPings;
    private BlipUI blip;
    private boolean initialPing;
    
    
    public ConnectionTester(BlipUI blip, TrayHandler th) {
        this.blip = blip;
        this.th = th;
        failedPings = 2;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                Log.debug("Testing Connection...");
                connection = testConnection();
                if(!connection) {
                    if (lastConnect != connection) {
                    Log.info("Not connected.");
                    th.setIconDisconnected();
                    blip.setProgressLabel("Not connected");
                    blip.setProgressBar(false);
                    blip.enableDisconnect(false);
                    blip.enableConnection(true);
                    blip.setProgressBar(false);
                    blip.setProgressValue(0);
                    lastConnect = false;                    
                    }
                } else {
                    if (lastConnect != connection) {
                    Log.info("Connected.");
                    th.setIconConnected();
                    blip.enableConnection(false);
                    blip.enableDisconnect(true);
                    if(!blip.getEssid().equals("$$$$$$$$")) {
                        blip.setProgressLabel("Connected to " + blip.getEssid());
                    } else {
                        blip.setProgressLabel("Connected");
                    }
                    blip.setProgressBar(false);
                    blip.setProgressValue(100);
                    lastConnect = true;                     
                    }                   
                }
                Thread.sleep(5000);                
            } catch (Exception ex) {
                Log.warning("Connection test thread interrupted", ex);
            }
        }
    }
    
    public boolean testConnection() {
        Log.debug("Pinging...");
        Executor pinger = new Executor(Command.ping());
        pinger.execute();
        int status = 0;
        try {
            status = pinger.get();
        } catch (InterruptedException ex) {
            Logger.getLogger(ConnectionTester.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ConnectionTester.class.getName()).log(Level.SEVERE, null, ex);
        }
        Log.debug("Pinged");
        if(status != 0) {
            failedPings++;
            if (failedPings > 1) {
                Log.debug("Not connected");
                return false;
            } else {
                return true;
            }
        }
        failedPings = 0;
        Log.debug("Connected");
        return true;
    }
}
