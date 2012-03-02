/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.swing.SwingWorker;

/**
 *
 * @author seed419
 */
public class BigReader extends SwingWorker<List<String>, String> {
    
    
    private Process proc;
    private BufferedReader br;
    private String command;
    private List<String> shellList;
    

    public BigReader(String command) {
        this.command = command;
    }
    
    private List<String> read(String command) {
        String[] tA;
        String line;
        shellList = new LinkedList<String>();
        try {
            proc = Runtime.getRuntime().exec(command);
            br = new BufferedReader(new InputStreamReader(proc.getInputStream()));        
        } catch (Exception ex) {
            Log.severe("Couldn't connect to shell!", ex);
        }
        try {
            Log.debug("Waiting for BigReader");
            proc.waitFor();
            Log.debug("BigReader done.");
        } catch (Exception ex) {
            Log.warning("Process Interrupted", ex);
        }
        try {
            if (br.ready()) {
                Log.debug("BigReader reading.");
                line = br.readLine();
                while (line != null) {
                    String trimmed = line.trim();
                    if (trimmed.startsWith("ESSID")) {
                        tA = trimmed.split(":");
                        String removed = tA[1].replaceAll("\"", "");
                        if (removed.length() > 0) {
                            shellList.add(removed);
                        }
                    }
                    line = br.readLine();
                }
            }    
        } catch (Exception e) {
            Log.warning("Couldn't read from the shell", e);
        }
        finally{
            try {
            br.close();
            } catch (IOException e) {
                Log.warning("Couldn't close BufferedReader");
            }
        }
        Log.debug("BigReader Finished.");
        return Collections.unmodifiableList(shellList);
    }

    @Override
    protected List<String> doInBackground() throws Exception {
        return read(command);
    }

}