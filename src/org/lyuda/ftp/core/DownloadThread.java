/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lyuda.ftp.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author roman
 */
public class DownloadThread extends Thread {

    private static Logger logger = Logger.getLogger("Download");
    private InputStream stream;
    private FileOutputStream output;
    private FTPClient client;
    
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
    
    @Override 
    public void run() {
        try {
            logger.log(Level.INFO, "Lol");
            while(stream.available()!=0)
                this.output.write(this.stream.read());
            this.stream.close();
            this.output.close();
            this.client.completePendingCommand();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
}
