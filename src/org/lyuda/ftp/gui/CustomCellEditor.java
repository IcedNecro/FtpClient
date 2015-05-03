package org.lyuda.ftp.gui;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;


public class CustomCellEditor extends DefaultTreeCellEditor {

    private JCheckBox checkbox;

    public CustomCellEditor(JTree tree) {
        super(tree,new DefaultTreeCellRenderer());
    }

    @Override
    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
        if((value instanceof FileNodeInTree)){
            final FileNodeInTree node = (FileNodeInTree)value;
            if(node.isLeaf()){
                JCheckBox cbox = new JCheckBox(node.getName());
                cbox.addChangeListener(new ChangeListener() {

                    @Override
                    public void stateChanged(ChangeEvent ce) {
                        node.setIsSelected(checkbox.isSelected());
                    }
                });

                return cbox;
            }
        }
        return super.getTreeCellEditorComponent(tree, value, isSelected, expanded, leaf, row);
    }
}
