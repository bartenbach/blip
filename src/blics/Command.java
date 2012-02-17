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
