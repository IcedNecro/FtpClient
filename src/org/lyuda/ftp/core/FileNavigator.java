/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lyuda.ftp.core;

import java.io.IOException;
import java.util.List;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author roman
 */
public interface FileNavigator {
    
    public void createDirrectory(String name) throws IOException;
    public void changePath(String str);
    public List<FileNode<?>> goToParent() throws IOException;
    public List<FileNode<?>> goToDirectory(String dirName) throws IOException;
    public List<FileNode<?>> getFilesAtCurrentDirrectory() throws IOException;
    public List<FileNode<?>> goToDirectoryRelative(String dirName) throws IOException;
}
