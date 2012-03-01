/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blip;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

/**
 *
 * @author seed419
 */
public class TrayHandler extends MouseAdapter {
    
    private TrayIcon trayIcon;
    private SystemTray tray;
    private boolean supported;
    private Image disconImage;
    private Image connImage;
    
    public void initTray() {
        if(SystemTray.isSupported()) {
            supported = true;
            trayIcon = null;
            Log.debug("System tray supported");
            try {
            tray = SystemTray.getSystemTray();
            } catch (Exception ex) {
                Log.warning("Couldn't get system tray!!");
                return;
            }
            URL connurl = getClass().getResource("/img/Connected.png");
            connImage = Toolkit.getDefaultToolkit().getImage(connurl);
            URL disconurl = getClass().getResource("/img/Disconnected.png");
            disconImage = Toolkit.getDefaultToolkit().getImage(disconurl);
            MouseListener mouseListener = new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse clicked!");                 
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse entered!");                 
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse exited!");                 
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse pressed!");                 
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse released!");                 
                }   
            };
            
            PopupMenu popup = new PopupMenu();
            MenuItem defaulti = new MenuItem("Test");
            popup.add(defaulti);
            trayIcon = new TrayIcon(disconImage, "blip", popup);
            trayIcon.setImageAutoSize(false);
            trayIcon.setPopupMenu(popup);
            trayIcon.addMouseListener(mouseListener);
            try {
                tray.add(trayIcon);
            } catch (AWTException ex) {
                Log.severe("Couldn't add trayicon to tray!");
            }
            Log.debug("System tray initialized.");
        } else {
            Log.warning("System tray not supported");
        }
    }
    
    public void setIconConnected() {
        if (supported) {
            trayIcon.setImage(connImage);
        }
    }
    
    public void setIconDisconnected() {
        if (supported) {
            trayIcon.setImage(disconImage);
        }    
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Tray Icon - Mouse clicked!");                 
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Tray Icon - Mouse entered!");                 
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Tray Icon - Mouse exited!");                 
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Tray Icon - Mouse pressed!");                 
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Tray Icon - Mouse released!");                 
    }
    
}
