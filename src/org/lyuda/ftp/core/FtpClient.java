package org.lyuda.ftp.core;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import com.sun.xml.internal.messaging.saaj.util.CharWriter;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.lyuda.ftp.config.Config;
import org.lyuda.ftp.gui.MainFrame;

public class FtpClient implements FileNavigator {
    
    private static Logger logger = Logger.getLogger("FTP client");
    private static BufferedOutputStream stream = new BufferedOutputStream(new ByteOutputStream());
    
    private static ExecutorService service = Executors.newFixedThreadPool(1);
    
    private static String TEST_HOST = "node100.net2ftp.ru";
    private static String USERNAME = "romanstatkevich@gmail.com";
    private static String PASSWORD =  "3b05809e25ee";
   
    static {
    }
    
   /* private static String TEST_HOST = "ftp.asu.kpi.ua";
    private static String USERNAME = "ip3219";
    private static String PASSWORD =  "081996";
    */
    private FTPClient client;
    private String pwd="/";
    
    @Override
    public void createDirrectory(String name) throws IOException  {
        this.client.makeDirectory(name);
    }
    
    public FtpClient() {
        this.client = new FTPClient();
    }

    public void connect(Config.BookMark bookMark) throws IOException{
       this.client.connect(bookMark.getHost());
       this.client.login(bookMark.getLogin(), bookMark.getPassword());
    }
    
    public void connect(String host, String username, String password) throws IOException{
       
       MainFrame.out.writeLogEvent("INFO", "Trying to connect:"+host);
       this.client.connect(host);
       this.client.login(username, password);
       this.client.setControlEncoding("UTF-8");
       MainFrame.out.writeLogEvent("INFO", "Server status "+this.client.getReplyCode());

    }
  
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

    public List<FileNode<?>> goToDirectoryRelative(String dirName) throws IOException{

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
        System.out.println(this.pwd);
        
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

    public String currentPosition() {
        return this.pwd;
    }
    
    public void uploadGroupOfFiles(File[] files) throws IOException {
        for(File file : files)
            this.uploadFile(file);
    }
    
    public void downloadGroupOfFiles(FTPFile[] files, String path) throws IOException{
        for(FTPFile file : files)
            this.downloadFile(file, path);
    }
    
    public Thread uploadFile(File file) throws IOException {
        this.client.setFileType(FTP.BINARY_FILE_TYPE);
         OutputStream fos = this.client.appendFileStream(file.getName());
        InputStream fis = new FileInputStreamWithStatus(file);
        Thread t = new UploadThread(fos, fis, this.client);
        t.start();
        return t;
    }
    
    public Thread downloadFile(FTPFile file, String path) throws IOException {
        this.client.setFileType(FTP.BINARY_FILE_TYPE);
        this.client.enterLocalPassiveMode();
        FileOutputStream fos = new FileOutputStreamWithStatus(new File(path+""+file.getName()));
        System.out.println("to download "+this.pwd+file.getName());
        InputStream stream = this.client.retrieveFileStream(this.pwd + file.getName());
        
        Thread t = new DownloadThread(stream,fos, this.client);
        t.start();
        return t;
    }
    
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
    
    public static void main(String[] args) throws IOException, InterruptedException {
        FtpClient c = new FtpClient();
        
        c.connect(TEST_HOST, USERNAME, PASSWORD);
        System.out.println(c.getFilesAtCurrentDirrectory());
        System.out.println(c.goToDirectory("public_http"));
       
        //c.downloadFile(, USERNAME);
        c.logOut();
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
    
    private static class FileOutputStreamWithStatus extends FileOutputStream {
        long status = 0;

        public FileOutputStreamWithStatus(File file) throws FileNotFoundException {
            super(file);
        }
        
        @Override
        public void write(int i) throws IOException {
            super.write(i); 
            status++;
        }

        public long getStatus() {
            return status;
        }        
    }
    
    private static class FileInputStreamWithStatus extends FileInputStream {
        long status = 0;

        public FileInputStreamWithStatus(File file) throws FileNotFoundException {
            super(file);
        }

        @Override
        public int read() throws IOException {
            status++;
            return super.read(); 
        }
        
        public long getStatus() {
            return status;
        }        
    }
    
    public static OutputStream getOutput() {
        return stream;
    }
}

