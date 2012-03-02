/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.SwingWorker;

/**
 *
 * @author seed419
 */
public class Reader extends SwingWorker<String, String> {
    
    
    private Process proc;
    private BufferedReader br;
    private String command;
    

    public Reader(String command) {
        this.command = command;
    }
    
    @Override
    protected String doInBackground() throws Exception {
        return read(command);
    }
    
    private String read(String command) {
        String line = null;
        try {
            proc = Runtime.getRuntime().exec(command);
            br = new BufferedReader(new InputStreamReader(proc.getInputStream()));        
        } catch (IOException ex) {
            Log.severe("Couldn't connect to shell!");
        }
        try {
            proc.waitFor();
        } catch (InterruptedException ex) {
            Log.warning("Process Interrupted");
        }
        try {
            if (br.ready()) {
                line = br.readLine();
            }
        } catch (IOException e) {
            Log.warning("Couldn't read from the shell");
        }
        finally{
            try {
            br.close();
            } catch (IOException e) {
                Log.warning("Couldn't close BufferedReader");
            }
        }
        return line;
    } 

}
