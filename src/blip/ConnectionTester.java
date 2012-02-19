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
                    BlipGUI.setProgressLabel("Connection lost");
                    BlipGUI.setProgressBar(false);
                } else {
                    Log.info("Connected.");
                    BlipGUI.setProgressLabel("Connected");
                    BlipGUI.setProgressValue(100);
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
    
}
