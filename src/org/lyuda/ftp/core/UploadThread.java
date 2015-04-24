/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lyuda.ftp.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;

public class UploadThread extends Thread {
    
    private OutputStream fos;
    private InputStream fis;
    private FTPClient client;
    private int status=0;
    
    UploadThread(OutputStream fos, InputStream fis, FTPClient client) {
        this.fis = fis;
        this.fos = fos;
        this.client = client;
    }
    
    @Override 
    public void run() {
        try {
            while(fis.available()!=0) {
                status++;
                fos.write(fis.read());
            }
            fis.close();
            fos.close();
            client.completePendingCommand();
        } catch (IOException ex) {
            Logger.getLogger(UploadThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public int getStatus() {
        return this.status;
    }
}