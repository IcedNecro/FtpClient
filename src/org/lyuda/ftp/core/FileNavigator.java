package org.lyuda.ftp.core;

import java.io.IOException;
import java.util.List;

/**
 * FileNavigator - interface that provides an API for simple CRUD operations with
 * file-systems
 * @author roman
 */
public interface FileNavigator {
    
    /**
     * Creates directory using name "name" in the current working directory
     * @param name - name of directory you want to create
     * @throws IOException 
     */
    public void createDirrectory(String name) throws IOException;
    
    /**
     * Switches current working directory (CWD) to the given path
     * @param str - path of new working directory. Can only be an absolute path.
     * If it is not, CWD switches to the root ("/") directory
     */
    public void changePath(String str);
    
    /**
     * Moves CWD to the parent directory
     * @return - list of files of parent directory 
     * @throws IOException 
     */
    public List<FileNode<?>> goToParent() throws IOException;
    
    /**
     * Switches CWD to directory specified by "dirName". 
     * @param dirName - directory name. Is relative path
     * @return - list of files of the new CWD
     * @throws IOException 
     */
    public List<FileNode<?>> goToDirectory(String dirName) throws IOException;

    /**
     * Returns a list of files int the CWD
     * @return
     * @throws IOException 
     */
    public List<FileNode<?>> getFilesAtCurrentDirrectory() throws IOException;
    
    /**
     * Is an analogue of changePath() method, but only returns a list of files
     * @param dirName - absolute path
     * @return
     * @throws IOException 
     */
    public List<FileNode<?>> goToDirectoryAbsolute(String dirName) throws IOException;
    
    /**
     * Removes file from file system
     * @param filename - name of file
     * @return
     * @throws IOException 
     */
    public FileNode<?> remove(String filename) throws IOException;
    
    /**
     * 
     * @return absolute path of CWD
     */
    public String getPath();
}
