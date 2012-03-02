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
    private BlipUI blip;
    
    public ConfigurationFile(BlipUI blip) throws FileNotFoundException, IOException {
        this.blip = blip;
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
//        BufferedReader br = new BufferedReader(new FileReader(settings));
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = br.readLine()) != null) {
//            if (!line.startsWith("#")) {
//                sb.append(line);
//            }
//        }
//        if (sb.toString() != null) {
//            String[] configLine = sb.toString().split(":");
//            if (configLine[0].length() != 0) {
//                blip.setInterface(Integer.parseInt(configLine[0]));
//                //TODO Oh boy...
//                //blip.setEssid(configLine[1]);
//                if (configLine[2].equalsIgnoreCase("true")) {
//                    blip.setEncrypted(true);
//                    if (configLine[3].equalsIgnoreCase("WPA")) {
//                        blip.setWPA(true);
//                    } else {
//                        blip.setWEP(true);
//                    }
//                    blip.setPrivateEncryptionKey(Integer.parseInt(configLine[4]));
//                } else {
//                    blip.setEncrypted(false);
//                }
//            Log.debug("Settings loaded.");
//            }
//        } else {
//            Log.debug("Settings file empty.");
//        }
    }
    
    public void saveSettings() throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(settings)));
        out.println("#blip configuration file");
        out.println("#");
        out.println("#Saved Settings:");
        out.write(blip.getInterface() + ":");
        out.write(blip.getEssid() + ":");
        if (blip.isEncrypted()) {
            out.write("true:" );
            if (blip.isWPA()) {
                out.write("WPA:");
            } else {
                out.write("WEP:");
            }
            out.write(blip.getEncryptionKey().length() + ":");
        } else {
            out.write("false:");
        }
        out.flush();
        out.close();
        Log.info("Settings saved.");
    }
    
}
