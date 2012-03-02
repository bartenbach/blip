/*
 * blip
 * Attribute Only (Public) License
    Version 0.a5, Feb 07, 2012
    
Copyright (C) 2012 Blake Bartenbach <SeeD419@gmail.com> (@SeeD419)

Anyone is allowed to copy and distribute verbatim or modified 
copies of this license document and altering is allowed as long 
as you attribute the author(s) of this license document / files.
*/
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
public class Executor extends SwingWorker<Integer, String> {
    
    
    private static Process proc;
    private static BufferedReader br;
    private String command;
    
    
    public Executor(String command) {
        proc = null;
        br = null;
        this.command = command;
    }
    
    @Override
    protected Integer doInBackground() throws Exception {
        return execute(command);
    }
    
    private Integer execute(String command) {
        try {
            Log.debug("Executing command");
            proc = Runtime.getRuntime().exec(command);
            Log.debug("Command executed");
            br = new BufferedReader(new InputStreamReader(proc.getInputStream()));        
        } catch (IOException ex) {
            Log.severe("Couldn't connect to shell!");
        }
        try {
            Log.debug("Watiing for process...");
            proc.waitFor();
            Log.debug("Process complete.");
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
    
//    @Override 
//    protected void done() {
//        this.command = null;
//    }
}
