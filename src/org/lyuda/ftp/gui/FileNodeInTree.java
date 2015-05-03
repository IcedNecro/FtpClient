package org.lyuda.ftp.gui;

import javax.swing.tree.DefaultMutableTreeNode;
import org.lyuda.ftp.core.FileNode;


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

    @Override
    public boolean equals(Object o) {
        if(o instanceof FileNodeInTree)
            return this.node.getName().equals(((FileNodeInTree)o).getName());
        return false;
    }
}
