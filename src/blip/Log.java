/*
 * blip
 * Attribute Only (Public) License
    Version 0.a5, Feb 07, 2012
    
Copyright (C) 2012 Blake Bartenbach <SeeD419@gmail.com> (@SeeD419)

Anyone is allowed to copy and distribute verbatim or modified 
copies of this license document and altering is allowed as long 
as you attribute the author(s) of this license document / files.
*/
package blip;

/**
 *
 * @author seed419
 */

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {
    

    final public static Logger log = Logger.getLogger("blip");
    private static Handler ch;
    

    public Log() {
        log.setUseParentHandlers(false);
        ch = new CustomConsoleHandler();   
        ch.setFormatter(new LogFormatter());
        log.addHandler(ch);
    }
        
    public static void info(String message){
        log.log(Level.INFO, message);
    }
    
    public static void warning(String message){
        log.warning(message);
    }
    
    public static void warning(String message, Throwable t){
        log.log(Level.WARNING, message, t);
    }
    
    public static void severe(String message){
        log.severe(message);
    }
    
    public static void severe(String message, Throwable t){
        log.log(Level.SEVERE, message, t);
    }
    
    public static void debug(String message) {
        log.log(Level.FINE, message);
    }
    
    public static void setLevel(Level level) {
        log.setLevel(level);
        ch.setLevel(level);
    }
}
