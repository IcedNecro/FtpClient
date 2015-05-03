package org.lyuda.ftp.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import org.lyuda.ftp.core.FileNavigator;
import org.lyuda.ftp.core.FileNode;


public class CustomTreeExpansionListener implements TreeExpansionListener{
    private JTree uiFtpTree;
    private FileNavigator client;
    public  FileNodeInTree current;
    private FileNodeInTree root;
    private CustomTableCellModel table;

    public CustomTreeExpansionListener(JTree tree, FileNodeInTree root, FileNavigator nav, CustomTableCellModel table) {
        this.uiFtpTree = tree;
        this.client = nav;
        this.root = root;
        this.current = root;
        this.table = table;
    }
    
    @Override
    public void treeExpanded(TreeExpansionEvent tee) {
        TreePath originalPath = tee.getPath();
        String path="/";
        for(int i=1; i<originalPath.getPathCount(); i++)
            path+=((FileNodeInTree)originalPath.getPathComponent(i)).getName()+"/";

        current = (FileNodeInTree)(originalPath.getLastPathComponent());
        try {
            client.goToDirectoryRelative(path);
            if(current.getChildCount()!=0)
                 return;
            else {
                buildTree();
                table.clear();
                table.addFiles(client.getFilesAtCurrentDirrectory());
            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void refresh() throws IOException {
        this.uiFtpTree.startEditingAtPath(new TreePath(current));
        current.removeAllChildren();

        buildTree();
        
        table.clear();
        table.addFiles(client.getFilesAtCurrentDirrectory());
    }
    
    @Override
    public void treeCollapsed(TreeExpansionEvent tee) {
        TreePath originalPath = tee.getPath();
        String path="";
        try {
            for(int i=0; i<originalPath.getPathCount(); i++)
                path+=((FileNodeInTree)originalPath.getPathComponent(i)).getName()+"/";
            current = (FileNodeInTree)(originalPath.getLastPathComponent());
            client.goToDirectoryRelative(path);
            table.clear();
            table.addFiles(client.getFilesAtCurrentDirrectory());
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void buildTree()  {
        
        try {
            for(FileNode<?> node : this.client.getFilesAtCurrentDirrectory()){
                FileNodeInTree toInsert = new FileNodeInTree(node);
                if(node.getType().equals(FileNode.FileType.DIRRECTORY))
                    ((DefaultTreeModel)this.uiFtpTree.getModel()).insertNodeInto(toInsert, this.current, 0);
            }
            ((DefaultTreeModel)this.uiFtpTree.getModel()).reload(current);
        
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    
