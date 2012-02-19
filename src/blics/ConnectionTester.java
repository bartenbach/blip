/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blics;

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
                    BLICSGUI.setProgressLabel("Connection lost");
                    BLICSGUI.setProgressBar(false);
                } else {
                    BLICSGUI.setProgressLabel("Connected");
                    BLICSGUI.setProgressValue(100);
                }
                Thread.sleep(5000);                
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
