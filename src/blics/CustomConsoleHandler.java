/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blics;

import java.util.logging.ConsoleHandler;

/**
 *
 * @author seed419
 */
public class CustomConsoleHandler extends ConsoleHandler{
    
    public CustomConsoleHandler(){
        super();
        this.setOutputStream(System.out);
    }
}
