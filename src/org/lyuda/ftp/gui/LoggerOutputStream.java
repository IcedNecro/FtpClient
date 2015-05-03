package org.lyuda.ftp.gui;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;


public class LoggerOutputStream extends OutputStream {
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
