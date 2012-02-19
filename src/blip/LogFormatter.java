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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 *
 * @author seed419
 */

public class LogFormatter extends Formatter{

    @Override
    public String format(LogRecord record) {     
        StringBuilder builder = new StringBuilder();
        Throwable ex = record.getThrown();        
        SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss");
        builder.append(date.format(record.getMillis()));
        builder.append(" [");
        if (record.getLevel().equals(Level.FINE)) {
            builder.append("DEBUG");
        } else {
            builder.append(record.getLevel().getLocalizedName().toUpperCase());
        }
        builder.append("] ");
        builder.append(record.getMessage());
        builder.append('\n');

        if (ex != null) {
            StringWriter writer = new StringWriter();
            ex.printStackTrace(new PrintWriter(writer));
            builder.append(writer);
        }

        return builder.toString();
    }
}
    

