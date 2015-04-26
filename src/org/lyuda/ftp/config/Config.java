package org.lyuda.ftp.config;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Config {
    private static Config instance;
    private String homeDirectory;

    public static final String HOME_DIR = "HOME_DIR";
    public static final String BOOK_MARKS = "BOOK_MARKS";
    public static final String HOST = "host";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";

    private ArrayList<BookMark> bookmarks = new ArrayList<BookMark>();	

    private Config() {}

    public String getHomeDirectory() {
        return homeDirectory;
    }

    public void setHomeDirectory(String homeDirectory) {
        this.homeDirectory = homeDirectory;
    }

    public static Config getInstance() {
        if (instance == null) {
            Config.instance = new Config();
            new ConfigFileParser();
        }
        return Config.instance;
    }

    public void addBookMark(BookMark bm){
        this.bookmarks.add(bm);
    }
    
    public List<BookMark> getBookMarks() {
        return this.bookmarks;
    }
    
    public void save() {
        new Thread(new SaveRunnable(this)).start();
    }
    
    public static class SaveRunnable implements Runnable {

        private Config config;
        
        public SaveRunnable(Config config) {
            this.config = config;
        }
        
        @Override
        public void run() {
            try {
                BufferedWriter bos = new BufferedWriter(new FileWriter(new File("props.prop")));
                
                bos.write(HOME_DIR+" : "+config.homeDirectory);
                
                StringBuffer bookmarks = new StringBuffer();
                
                
            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    public static class BookMark {
        private String host;
        private String login;
        private String password;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return this.host;
        }
    }
}
