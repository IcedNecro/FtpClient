package org.lyuda.ftp.gui;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeModel;
import org.apache.commons.net.ftp.FTPFile;
import org.lyuda.ftp.core.FileNode;
import org.lyuda.ftp.core.FileSystemNavigator;
import org.lyuda.ftp.core.FtpClient;
import org.lyuda.ftp.core.UploadThread;


public class MainFrame extends javax.swing.JFrame {

    private FtpClient client;
    private FileSystemNavigator navigator;
    private FileNodeInTree rootInFileSystem;
    private CustomTableCellModel fileSystemModel;
    private CustomTableCellModel ftpSystemModel;
    
    public static LoggerOutputStream out = new LoggerOutputStream();
    
    public MainFrame() {
        initComponents();
            
        this.uiFtpTree.setCellEditor(new CustomCellEditor(this.uiFtpTree));
        this.uiFtpTree.setCellRenderer(new CustomCellRenderer());
        
        this.uiFileSystem.setCellEditor(new CustomCellEditor(this.uiFileSystem));
        this.uiFileSystem.setCellRenderer(new CustomCellRenderer());
        
        out.setOutputPane(this.logger);
        
        this.logger.setEditable(false);
        
        FileNode<Object> node = new FileNode<Object>(null,true);
        node.setName("/");
        FileNodeInTree root = new FileNodeInTree(node);
            
        ((DefaultTreeModel)this.uiFileSystem.getModel()).setRoot(root);
            
        client = new FtpClient();
        navigator = new FileSystemNavigator();
        
        
        
        fileSystemModel = new CustomTableCellModel(this.uiCurrentDirTable);
        ftpSystemModel = new CustomTableCellModel(this.uiFtpTable);
        CustomTreeExpansionListener l = new CustomTreeExpansionListener(
                uiFileSystem, root, navigator, this.fileSystemModel);
        this.uiFileSystem.addTreeExpansionListener(l);
        l.buildTree();
        this.uiCurrentDirTable.setModel(fileSystemModel);
        this.uiFtpTable.setModel(this.ftpSystemModel);
        this.uiCurrentDirTable.getColumnModel().getColumn(0).setMaxWidth(20);
        this.uiCurrentDirTable.getColumnModel().getColumn(1).setMaxWidth(20);
        this.uiFtpTable.getColumnModel().getColumn(0).setMaxWidth(20);
        this.uiFtpTable.getColumnModel().getColumn(1).setMaxWidth(20);
        //this.uiCurrentDirTable.setEnabled(false);
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        label1 = new java.awt.Label();
        uiHostInput = new javax.swing.JTextField();
        label3 = new java.awt.Label();
        uiUserNameInput = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        uiPasswordInput = new javax.swing.JPasswordField();
        uiConnectButton = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jScrollPane1 = new javax.swing.JScrollPane();
        uiFileSystem = new javax.swing.JTree();
        jScrollPane2 = new javax.swing.JScrollPane();
        uiFtpTree = new javax.swing.JTree();
        jScrollPane3 = new javax.swing.JScrollPane();
        logger = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        uiCurrentDirTable = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        uiFtpTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        uiDownloadButton = new javax.swing.JButton();
        uiIndicator = new javax.swing.JProgressBar();

        FormListener formListener = new FormListener();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseMotionListener(formListener);

        jToolBar1.setRollover(true);

        label1.setText("Host");
        jToolBar1.add(label1);
        label1.getAccessibleContext().setAccessibleName("uiHostLabel");

        uiHostInput.setMaximumSize(new java.awt.Dimension(100, 28));
        uiHostInput.setMinimumSize(new java.awt.Dimension(100, 28));
        uiHostInput.setName("uiHostInput"); // NOI18N
        uiHostInput.setPreferredSize(new java.awt.Dimension(100, 28));
        uiHostInput.addActionListener(formListener);
        jToolBar1.add(uiHostInput);

        label3.setText("UserName");
        jToolBar1.add(label3);
        label3.getAccessibleContext().setAccessibleName("uiLoginLabel");

        uiUserNameInput.setPreferredSize(new java.awt.Dimension(100, 28));
        uiUserNameInput.addActionListener(formListener);
        jToolBar1.add(uiUserNameInput);

        jLabel1.setText("Password");
        jToolBar1.add(jLabel1);

        uiPasswordInput.setPreferredSize(new java.awt.Dimension(100, 28));
        jToolBar1.add(uiPasswordInput);

        uiConnectButton.setText("Connect");
        uiConnectButton.setActionCommand("uiConnectButton");
        uiConnectButton.setFocusable(false);
        uiConnectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        uiConnectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        uiConnectButton.addActionListener(formListener);
        jToolBar1.add(uiConnectButton);
        jToolBar1.add(filler1);

        jScrollPane1.setViewportView(uiFileSystem);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        uiFtpTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane2.setViewportView(uiFtpTree);

        jScrollPane3.setViewportView(logger);

        uiCurrentDirTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "DateModified", "Size"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(uiCurrentDirTable);

        uiFtpTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(uiFtpTable);

        jButton1.setText("Upload");
        jButton1.addActionListener(formListener);

        uiDownloadButton.setText("Download");
        uiDownloadButton.addActionListener(formListener);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jToolBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 955, Short.MAX_VALUE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jScrollPane3)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(layout.createSequentialGroup()
                                .addContainerGap()
                                .add(uiIndicator, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 116, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 458, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 458, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jScrollPane2)
                            .add(jScrollPane5)
                            .add(layout.createSequentialGroup()
                                .add(uiDownloadButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 111, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jToolBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jScrollPane4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                    .add(jScrollPane5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(2, 2, 2)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jButton1)
                            .add(uiDownloadButton)))
                    .add(layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(uiIndicator, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 113, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener, java.awt.event.MouseMotionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == uiHostInput) {
                MainFrame.this.uiHostInputActionPerformed(evt);
            }
            else if (evt.getSource() == uiUserNameInput) {
                MainFrame.this.uiUserNameInputActionPerformed(evt);
            }
            else if (evt.getSource() == uiConnectButton) {
                MainFrame.this.uiConnectButtonActionPerformed(evt);
            }
            else if (evt.getSource() == jButton1) {
                MainFrame.this.jButton1ActionPerformed(evt);
            }
            else if (evt.getSource() == uiDownloadButton) {
                MainFrame.this.uiDownloadButtonActionPerformed(evt);
            }
        }

        public void mouseDragged(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == MainFrame.this) {
                MainFrame.this.formMouseDragged(evt);
            }
        }

        public void mouseMoved(java.awt.event.MouseEvent evt) {
        }
    }// </editor-fold>//GEN-END:initComponents

    private void uiUserNameInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiUserNameInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uiUserNameInputActionPerformed

    private void uiHostInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiHostInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uiHostInputActionPerformed

    private void uiConnectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiConnectButtonActionPerformed
        String password = new String(this.uiPasswordInput.getPassword());
        String host = this.uiHostInput.getText();
        String username = this.uiUserNameInput.getText();
        
        try {
            client.connect(host, username, password);
            FileNode<Object> node = new FileNode<Object>(null,true);
            node.setName("/");
            FileNodeInTree root = new FileNodeInTree(node);
            
            ((DefaultTreeModel)this.uiFtpTree.getModel()).setRoot(root);
            CustomTreeExpansionListener l = new CustomTreeExpansionListener(
                    this.uiFtpTree, root, this.client, this.ftpSystemModel);
            this.uiFtpTree.addTreeExpansionListener(l);
           
            l.buildTree();
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_uiConnectButtonActionPerformed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        
    }//GEN-LAST:event_formMouseDragged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        for(FileNode<?> node : this.fileSystemModel.getSelectedNodes())
            try {
                this.uiIndicator.setMaximum(node.getSize().intValue());
                
                new Indicator((UploadThread)this.client.uploadFile((File)node.getFile()),node.getSize().intValue()).execute();
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void uiDownloadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiDownloadButtonActionPerformed
        for(FileNode<?> node : this.ftpSystemModel.getSelectedNodes())
            try {
                MainFrame.out.writeLogEvent("INFO", "Downloading file "+node.getName()+" to path "+this.navigator.getPath());
                this.client.downloadFile((FTPFile)node.getFile(),this.navigator.getPath());
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_uiDownloadButtonActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    private class Indicator extends SwingWorker<Void, Object> {

        private UploadThread thread;
        private int fileSize;
        
        Indicator(UploadThread thread, int fileSize){
            this.thread = thread;
        }
        @Override
        protected Void doInBackground() throws Exception {
            while(thread.getStatus()<fileSize)
                MainFrame.this.uiIndicator.setValue(thread.getStatus());
            return null;
        }
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.Box.Filler filler1;
    javax.swing.JButton jButton1;
    javax.swing.JLabel jLabel1;
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JScrollPane jScrollPane2;
    javax.swing.JScrollPane jScrollPane3;
    javax.swing.JScrollPane jScrollPane4;
    javax.swing.JScrollPane jScrollPane5;
    javax.swing.JToolBar jToolBar1;
    java.awt.Label label1;
    java.awt.Label label3;
    javax.swing.JTextPane logger;
    javax.swing.JButton uiConnectButton;
    javax.swing.JTable uiCurrentDirTable;
    javax.swing.JButton uiDownloadButton;
    javax.swing.JTree uiFileSystem;
    javax.swing.JTable uiFtpTable;
    javax.swing.JTree uiFtpTree;
    javax.swing.JTextField uiHostInput;
    javax.swing.JProgressBar uiIndicator;
    javax.swing.JPasswordField uiPasswordInput;
    javax.swing.JTextField uiUserNameInput;
    // End of variables declaration//GEN-END:variables
}
