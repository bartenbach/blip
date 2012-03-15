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

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author seed419
 */
public class Command {
    
    
    public static String setInterfaceUp(String inter) {
        return "ip link set " + inter + " up";
    }
    
    public static void setInterfaceDown(String inter) {
        Executor setInterDown = new Executor("ip link set " + inter + " down");
        setInterDown.execute();
    }
    
    public static String connectToESSID(String inter, String essid) {
        return "iwconfig " + inter + " essid " + essid;
    }
    
    public static String ping() {
        return "ping -c1 www.google.com";
    }
    
    public static String getUID() {
        return "id -u";
    }
    
    public static String startWpa_Supplicant(String inter) {
        return "wpa_supplicant -B -Dwext -i" + inter + " -c/etc/wpa_supplicant.conf";
    }
    
    public static void killWpa_Supplicant() {
        Executor killwpa_supp = new Executor("pkill wpa_supplicant");
        killwpa_supp.execute();
    }
    
    public static String dhcpcd(String inter) {
        return "dhcpcd " + inter;
    }
    
    public static void killdhcpcd() {
        Executor killDhcpcd = new Executor("pkill dhcpcd");
        killDhcpcd.execute();
    }
    
    public static List<String> getEssids(String inter) {
        Log.debug("Getting Essids");
        List<String> essidList = null;
        BigReader essids = new BigReader("iwlist " + inter + " scan");
        essids.execute();
        Log.debug("Executed essids");
        try {
            essidList = essids.get();
        } catch (InterruptedException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
        return essidList;
    }

}
