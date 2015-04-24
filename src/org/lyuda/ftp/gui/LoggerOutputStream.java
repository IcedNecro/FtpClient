/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lyuda.ftp.gui;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;

/**
 *
 * @author roman
 */
public class LoggerOutputStream extends OutputStream {
    private Thread t;
    private OutputStream stream;
    private InputStream input;
    private JTextPane pane;
    
    public LoggerOutputStream() {
    }

    public void setOutputPane(JTextPane pane) {
        this.pane = pane;
    }
    
    @Override
    public void write(int i) throws IOException {
        this.pane.setText(this.pane.getText()+(char)i);
    }
    
    public void writeLogEvent(String level, String str) {
        try {
            Formatter format = new Formatter();
            String log = format.format("[%6s]\t%s\n", level, str).toString();
            
            byte[] b = log.getBytes();
            for(int i = 0; i<b.length;i++)
                this.write(b[i]);
                
        } catch (IOException ex) {
            Logger.getLogger(LoggerOutputStream.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
