package org.lyuda.ftp.core;

import java.io.IOException;
import java.util.List;


public interface FileNavigator {
    
    public void createDirrectory(String name) throws IOException;
    public void changePath(String str);
    public List<FileNode<?>> goToParent() throws IOException;
    public List<FileNode<?>> goToDirectory(String dirName) throws IOException;
    public List<FileNode<?>> getFilesAtCurrentDirrectory() throws IOException;
    public List<FileNode<?>> goToDirectoryRelative(String dirName) throws IOException;
    public FileNode<?> remove(String filename) throws IOException;
    public String getPath();
}
