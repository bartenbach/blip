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

/**
 *
 * @author seed419
 */
public class ConnectionTester extends Thread {
    
    private boolean connection;

    @Override
    public void run() {
        while (true) {
            try {
                connection = testConnection();
                if(!connection) {
                    Log.info("Not connected.");
                    BlipUI.setProgressLabel("Not connected");
                    BlipUI.setProgressBar(false);
                    BlipUI.enableDisconnect(false);
                    BlipUI.enableConnection(true);
                    BlipUI.setProgressValue(0);
                } else {
                    Log.info("Connected.");
                    BlipUI.enableConnection(false);
                    BlipUI.enableDisconnect(true);
                    if(BlipUI.getEssid() != null) {
                        BlipUI.setProgressLabel("Connected to " + BlipUI.getEssid());
                    } else {
                        BlipUI.setProgressLabel("Connected");
                    }
                    BlipUI.setProgressValue(100);
                }
                Thread.sleep(10000);                
            } catch (InterruptedException ex) {
                Log.warning("Connection test thread interrupted");
            }
        }
    }
    
    public boolean testConnection() {
        int status = Executor.execute(Command.ping());
        if(status != 0) {
            return false;
        }
        return true;
    }
    
//    public void sleep(int milli) {
//        this.sleep(milli);
//    }
    
}
