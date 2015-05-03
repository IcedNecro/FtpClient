package org.lyuda.ftp.core;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;
import org.lyuda.ftp.gui.MainFrame;

/**
 * 
 * Thread for downloading file from ftp-server
 */
public class DownloadThread extends Thread {

    private static Logger logger = Logger.getLogger("Download");
    private InputStream stream;
    private FileOutputStream output;
    private FTPClient client;
    private Long status = new Long(0);
    
    /**
     * 
     * @param stream - input
     * @param output - where to output
     * @param client 
     */
    public DownloadThread(InputStream stream, FileOutputStream output, FTPClient client) {
        this.client = client;
        this.stream = stream;
        this.output = output;
    }
    
    @Override
    public void start() {
        super.start();
        logger.log(Level.INFO, "Started download");
    }
    
    public Long getStatus() {
        return this.status;
    }
    
    @Override 
    public void run() {
        try {

            MainFrame.out.writeLogEvent("INFO", "Server status "+this.client.getReplyCode());
            while(stream.available()!=0) {
                status++;
                this.output.write(this.stream.read());
            }
            this.client.completePendingCommand();
            
            this.stream.close();
            this.output.close();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
}
