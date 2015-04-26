/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lyuda.ftp.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author roman
 */
public class FileSystemNavigator implements FileNavigator {

    private Path path = Paths.get("/");
    
    @Override
    public void createDirrectory(String name) throws IOException {
        Files.createDirectory(path.resolve(name));
    }

    @Override
    public List<FileNode<?>> goToParent() throws IOException {
        this.path = this.path.getParent();
        return this.getFilesAtCurrentDirrectory();
    }

    @Override
    public List<FileNode<?>> goToDirectory(String dirName) throws IOException {
        this.path = this.path.resolve(dirName);
        return this.getFilesAtCurrentDirrectory();
    }

    @Override
    public List<FileNode<?>> getFilesAtCurrentDirrectory() throws IOException {
        DirectoryStream<Path> directory = Files.newDirectoryStream(this.path);
        
        List<FileNode<?>> list = new ArrayList<>();
        
        
        for(Path p : directory) {
            File f = p.toFile();
            FileNode<File> node = new FileNode<>(f, f.isDirectory());
            
            Calendar calendar = new GregorianCalendar();
            calendar.setTimeInMillis(f.lastModified());
            
            node.setName(f.getName());
            node.setSize(f.length());
            node.setTime(calendar);
            
            list.add(node);
        }
        
        return list;
    }
   
    @Override
    public void changePath(String str) {
        this.path = Paths.get(str);
    }
    
    @Override
    public List<FileNode<?>> goToDirectoryRelative(String dirName) throws IOException {
        this.path = Paths.get(dirName);
        return this.getFilesAtCurrentDirrectory();
    }
    
    @Override 
    public FileNode<?> remove(String path) throws IOException {
        Path p = Paths.get(path);
        FileNode<File> node = new FileNode<>(p.toFile(), p.toFile().isDirectory());
        p.toFile().delete();
        return node;
    }
    
    
    public String getPath() {
        return this.path.toString()+"/";
    }
}
