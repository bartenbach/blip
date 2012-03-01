/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blip;

import java.io.*;

/**
 *
 * @author seed419
 */
public class ConfigurationFile {
    
    private File settings;
    
    public ConfigurationFile() throws FileNotFoundException, IOException {
        settings = new File("/etc/blip.conf");
        if (!settings.exists()) {
            try {
                settings.createNewFile();
                Log.info("Created empty settings file in /etc/blip.conf");
            } catch (IOException ex) {
                Log.severe("Could not create settings file!");
            }
        } else {
            Log.debug("Configuration file found");
            loadSettings();
        }
   }
    private void loadSettings() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(settings));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            if (!line.startsWith("#")) {
                sb.append(line);
            }
        }
        String[] configLine = sb.toString().split(":");
        BlipUI.setInterface(configLine[0]);
        BlipUI.setEssid(configLine[1]);
        if (configLine[2].equalsIgnoreCase("true")) {
            BlipUI.setEncrypted(true);
            if (configLine[3].equalsIgnoreCase("WPA")) {
                BlipUI.setWPA(true);
            } else {
                BlipUI.setWEP(true);
            }
            BlipUI.setPrivateEncryptionKey(Integer.parseInt(configLine[4]));
        } else {
            BlipUI.setEncrypted(false);
        }
        Log.debug("Settings loaded.");
    }
    
    public void saveSettings() throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(settings)));
        out.println("#blip configuration file");
        out.println("#");
        out.println("#Saved Settings:");
        out.write(BlipUI.getInterface() + ":");
        out.write(BlipUI.getEssid() + ":");
        if (BlipUI.isEncrypted()) {
            out.write("true:" );
            if (BlipUI.isWPA()) {
                out.write("WPA:");
            } else {
                out.write("WEP:");
            }
            out.write(BlipUI.getEncryptionKey().length() + ":");
        } else {
            out.write("false:");
        }
        out.flush();
        out.close();
        Log.info("Settings saved.");
    }
    
}
