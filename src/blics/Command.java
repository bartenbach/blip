/*
 * BLICS
 *Attribute Only (Public) License
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
package blics;

/**
 *
 * @author seed419
 */
public class Command {
    
    
    public static String setInterfaceUp(String inter) {
        return "ip link set " + inter + " up";
    }
    
    public static String setInterfaceDown(String inter) {
        return "ip link set "+ inter + " down";
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
    
    public static String killWpa_Supplicant() {
        return "pkill wpa_supplicant";
    }
    
    public static String dhcpcd(String inter) {
        return "dhcpcd " + inter;
    }
    
    public static String killdhcpcd() {
        return "pkill dhcpcd";
    }

}
