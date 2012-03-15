/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blip;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author seed419
 */
public class UIHandler {
    
    private ConnectionTester conTest;
    private ConfigurationFile config;
    private TrayHandler th;
    private BlipUI blip;
    private int uid = 999;
    private LinkedList<String> interfaceList;
    
    
    public UIHandler(BlipUI blip) {
        this.blip = blip;
    }
    
    public void init() {
        if(!root()) {
            JOptionPane.showMessageDialog(null, "You must run blip as root", "blip", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } else {        
            Log.debug("Starting TrayHandler...");
            th = new TrayHandler(blip);
            th.initTray();
            Log.debug("Getting interfaces...");
            //getInterfaces();
            try {
                config = new ConfigurationFile(blip);
            } catch (FileNotFoundException ex) {
                Log.severe("Configuration file not found");
            } catch (Exception ex) {
                Log.severe("Couldn't read configuration file", ex);
            }
            Log.info("Starting ConnectionTester thread...");
            conTest = new ConnectionTester(blip, th);
            conTest.start();
            Log.debug("Connection Tester started");                
            blip.checkEncryptionBox();
        }      
    }
    
    public boolean root() {    
        Log.debug("Checking for root...");
        Reader readUID = new Reader(Command.getUID());
        readUID.execute();
        Log.debug("Returned from root check");
        try {
            uid = Integer.parseInt(readUID.get());
            Log.info("UID: " + uid);
        } catch (NumberFormatException n) {
            Log.severe("Recieved unrecognized uid!");
        } catch (Exception e) {
            Log.severe("Exception getting UID: ", e);
        }
        if(uid != 0) {
            JOptionPane.showMessageDialog(null, "You must run blip as root", "blip", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return true;
    }
    
    //TODO What is the deal with this?
//    public void getInterfaces() {
//        Log.debug("Getting network interfaces...");
//        Executor setUp = new Executor(Command.setInterfaceUp("wlan0"));
//        setUp.execute();
//        interfaceList = new LinkedList<String>();
//        Enumeration<NetworkInterface> interfaces = null;
//        try {
//            interfaces = NetworkInterface.getNetworkInterfaces();
//        } catch (SocketException ex) {
//            Log.severe("Couldn't get network interfaces!", ex);
//        }
//        while (interfaces.hasMoreElements()) {
//            NetworkInterface interf = interfaces.nextElement();
//            Log.debug("INTERFACE: " + interf.getName());
//            try {
//                if (!interf.isLoopback()) {
//                    blip.addToInterfaceBox(interf.getName());
//                    interfaceList.add(interf.getName());
////                    System.out.println("MAC: " + interf.getHardwareAddress().toString());
////                    Enumeration<InetAddress> addresses = interf.getInetAddresses();
////                    for (InetAddress x : Collections.list(addresses)) {
////                        Log.debug("Address: " + x);
////                    }
////                    List<InterfaceAddress> add = interf.getInterfaceAddresses();
////                    for (InterfaceAddress y : add) {
////                        Log.debug("Interface Address: " + y);
////                    }
//                }
//            } catch (SocketException ex) {
//                Logger.getLogger(BlipUI.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        for (String x : interfaceList) {
//            Log.info("Found interface: " + x);
//        }
//
//    }


    
}
