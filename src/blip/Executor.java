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

/**
 *
 * @author seed419
 */
public class Executor extends Thread {
    
    //TODO This absolutely needs to run in a separate thread.
    
    
    private static Process proc;
    private static BufferedReader br;
    private static String command;
    
    
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

    @Override
    public void run() {

    }
}
