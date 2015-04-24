/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lyuda.ftp.gui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import org.lyuda.ftp.core.FileNode;

/**
 *
 * @author roman
 */
public class CustomTableCellModel extends DefaultTableModel {
    
    private ArrayList<FileNode<?>> nodes;
    private Boolean editable[] = {true,false,false,false,false};
    
    public CustomTableCellModel(JTable table){
        this.addColumn("");
        this.addColumn("");
        this.addColumn("File Name");
        this.addColumn("Date Modified");
        this.addColumn("Size");
        this.nodes = new ArrayList<>();

    }
    
    public void addFiles(List<FileNode<?>> nodes) {
        for(FileNode<?> node : nodes) {
            this.add(node);
        }        
    }
    
    public List<FileNode<?>> getSelectedNodes() {
        ArrayList<FileNode<?>> list = new ArrayList<>();
        for(int i=0; i<this.getRowCount(); i++)
            if(this.getValueAt(i, 0).equals(new Boolean(true)))
                list.add(this.nodes.get(i));
        return list;
    }
    
    public void add(FileNode<?> node) {
        ImageIcon icon;
        if(node.getType().equals(FileNode.FileType.DIRRECTORY))
            icon = new ImageIcon("folder.jpg");
        else
            icon = new ImageIcon("file.jpg");

        this.addRow(new Object[] {false, icon, node.getName(), node.getTime().getTime().toString(), node.getSize()});
        this.nodes.add(node);
    }
    
    public void clear() {
        while(this.getRowCount()!=0)
            this.removeRow(0);
        this.nodes.clear();
    }
    
    @Override
    public Class getColumnClass(int column)
    {
        if (column == 0) return Boolean.class; 
        if (column == 1) return ImageIcon.class; 
        return Object.class;
    }
    
    @Override 
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return this.editable[columnIndex];
    }
}
