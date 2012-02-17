/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author seed419
 */
public class Executor {
    
    
    private static Process proc;
    private static BufferedReader br;
    
    
    public Executor() {
        proc = null;
        br = null;   
    }
    
    public static int execute(String command) {
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
        }finally{
            try {
            br.close();
            } catch (IOException e) {
                Log.warning("Couldn't close BufferedReader");
            }
        }
        return proc.exitValue();
    }
    
    public static String read(String command) {
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
