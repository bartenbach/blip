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
