/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lyuda.ftp.gui;

import java.util.Enumeration;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import org.lyuda.ftp.core.FileNode;

/**
 *
 * @author roman
 */
public class FileNodeInTree extends DefaultMutableTreeNode{

    private FileNode<?> node;
    private boolean isSelected;
    
    FileNodeInTree(FileNode<?> node) {
        super(node.getName());
        this.node = node;
    }

    public boolean isIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
    
    public String getName() {
        return this.node.getName();
    }
    
    @Override
    public boolean isLeaf() {
        return !this.node.getType().equals(FileNode.FileType.DIRRECTORY);
    }

}
