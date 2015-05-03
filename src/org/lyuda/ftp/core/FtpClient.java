package org.lyuda.ftp.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.lyuda.ftp.config.Config;
import org.lyuda.ftp.gui.MainFrame;


/**
 * FileNavigator implementation, supports CRUD operations over ftp-server
 */
public class FtpClient implements FileNavigator {
    
    private static Logger logger = Logger.getLogger("FTP client");
    
    private FTPClient client;
    private String pwd="/";
    
    @Override
    public void createDirrectory(String name) throws IOException  {
        this.client.makeDirectory(name);
    }
    
    public FtpClient() {
        this.client = new FTPClient();
    }
    /**
     * Connects to ftp server, specified by "host", using given "username" and "password"
     * @param host
     * @param username
     * @param password
     * @throws IOException 
     */    
    public void connect(String host, String username, String password) throws IOException{
       
       MainFrame.out.writeLogEvent("INFO", "Trying to connect:"+host);
       this.client.connect(host);
       this.client.login(username, password);
       this.client.setControlEncoding("UTF-8");
       MainFrame.out.writeLogEvent("INFO", "Server status "+this.client.getReplyCode());

    }
  
    /**
     * Closes user session
     * @throws IOException 
     */
    public void logOut() throws IOException{
        client.logout();
    }
    
    @Override
    public List<FileNode<?>> goToParent() throws IOException{

        this.pwd = this.pwd.substring(0, this.pwd.lastIndexOf("//"));
        this.client.changeToParentDirectory();

        List<FileNode<?>> files = new ArrayList<>();

        for(FTPFile file : this.client.listFiles())
            files.add(convertToNode(file));

        return files;
    }
    
    @Override
    public List<FileNode<?>> goToDirectoryAbsolute(String dirName) throws IOException{

        this.pwd = dirName;
        this.client.changeWorkingDirectory(this.pwd);
        List<FileNode<?>> files = new ArrayList<FileNode<?>>();
        for(FTPFile file:this.client.listFiles())
            files.add(convertToNode(file));
        return files;
    }

    
    @Override
    public List<FileNode<?>> goToDirectory(String dirName) throws IOException{

        this.pwd += dirName+"/";
        
        this.client.changeWorkingDirectory(this.pwd);
        List<FileNode<?>> files = new ArrayList<FileNode<?>>();
        for(FTPFile file:this.client.listFiles())
            files.add(convertToNode(file));
        return files;
    }
    
    @Override
    public List<FileNode<?>> getFilesAtCurrentDirrectory() throws IOException {
        FTPFile[] files = this.client.listFiles();
        
        ArrayList<FileNode<?>> fileList = new ArrayList<FileNode<?>>();
        
        for(FTPFile file: files) {
            fileList.add(convertToNode(file));
        }
        
        return fileList;
    }
    
    /**
     * Uploads file to the ftp-server
     * @param file - file you need to upload
     * @return - thread that uploads file
     * @throws IOException 
     */
    public Thread uploadFile(File file) throws IOException {
        this.client.setFileType(FTP.BINARY_FILE_TYPE);
         OutputStream fos = this.client.appendFileStream(file.getName());
        InputStream fis = new FileInputStream(file);
        Thread t = new UploadThread(fos, fis, this.client);
        t.start();
        return t;
    }
    
    /**
     * Downloads file to the ftp-server
     * @param file - file you need to upload
     * @return - thread that uploads file
     * @throws IOException 
     */
    public Thread downloadFile(FTPFile file, String path) throws IOException {
        this.client.setFileType(FTP.BINARY_FILE_TYPE);
        this.client.enterLocalPassiveMode();
        FileOutputStream fos = new FileOutputStream(new File(path+""+file.getName()));
        InputStream stream = this.client.retrieveFileStream(this.pwd + file.getName());
        
        Thread t = new DownloadThread(stream,fos, this.client);
        t.start();
        return t;
    }
    /**
     * Returns adapted filenode that wraps FTPFile
     * @param file - file to be wrapped
     * @return 
     */
    private static FileNode<FTPFile> convertToNode(FTPFile file) {
        String name = file.getName();
        Calendar date = file.getTimestamp();
        Long size = file.getSize();
        
        FileNode<FTPFile> node = new FileNode<>(file, file.isDirectory());
        
        node.setName(name);
        node.setTime(date);
        node.setSize(size);
        
        return node;
    }
    
    @Override
    public void changePath(String str) {
        this.pwd = str;
    }
    
    @Override
    public FileNode<?> remove(String filename) throws IOException {
        if(this.client.mlistFile(filename).isDirectory())
            this.client.removeDirectory(filename);
        else
            this.client.deleteFile(filename);
        return null;
    }

    @Override 
    public String getPath() {
        return this.pwd;
    }
}

