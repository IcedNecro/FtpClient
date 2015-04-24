/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lyuda.ftp.gui;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author roman
 */
public class CustomCellRenderer extends DefaultTreeCellRenderer{

    JCheckBox checkBox;

    @Override
    public Component getTreeCellRendererComponent(JTree jtree, Object o, boolean bln, boolean bln1, boolean bln2, int i, boolean bln3) {
        
        if((o instanceof FileNodeInTree) ){
            FileNodeInTree node = (FileNodeInTree) o;
            if(node.isLeaf()) {
                JCheckBox box = new JCheckBox(node.getName());
                box.setSelected(((FileNodeInTree)o).isIsSelected());
                return box;
            }
        }
        return super.getTreeCellRendererComponent(jtree, o, bln, bln1, bln2, i, bln3);
    }
}
