/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blip;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author seed419
 */
public class ConfigurationFile {
    
    private File settings;
    
    public ConfigurationFile() {
        settings = new File("/etc/blip.conf");
        if (!settings.exists()) {
            try {
                settings.createNewFile();
                Log.info("Created empty settings file in /etc/blip.conf");
            } catch (IOException ex) {
                Log.severe("Could not create settings file!");
            }
        } else {
            loadSettings();
        }
   }
    private void loadSettings() {
        
    }
    
    public void saveSettings() {
        
    }
    
}
