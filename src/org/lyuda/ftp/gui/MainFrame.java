package org.lyuda.ftp.gui;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.ListModel;
import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;

import java.util.List;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeModel;
import org.apache.commons.net.ftp.FTPFile;
import org.lyuda.ftp.config.Config;
import org.lyuda.ftp.core.DownloadThread;
import org.lyuda.ftp.core.FileNode;
import org.lyuda.ftp.core.FileSystemNavigator;
import org.lyuda.ftp.core.FtpClient;
import org.lyuda.ftp.core.UploadThread;
import org.omg.CORBA.SystemException;


public class MainFrame extends javax.swing.JFrame {

    private Config config = Config.getInstance();
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
        DefaultListModel<Config.BookMark> model = new DefaultListModel<>();
        for(Config.BookMark mark : config.getBookMarks())
            model.addElement(mark);
        
        this.uiBookmarkList.setModel(model);
        
        this.uiBookmarkList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if(!lse.getValueIsAdjusting()) {
                    int index = lse.getFirstIndex();
                    Config.BookMark bookmark = (Config.BookMark)uiBookmarkList.getModel().getElementAt(index);
                    uiLabelBookmarkHost.setText(bookmark.getHost());
                    uiLabelBookmarkUser.setText(bookmark.getLogin());
                    uiLabelPassword.setText(bookmark.getPassword());
                    
                    uiPasswordInput.setText(bookmark.getPassword());
                    uiHostInput.setText(bookmark.getHost());
                    uiUserNameInput.setText(bookmark.getLogin());
                }
                    
            }
        });
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
        uiUploadButton = new javax.swing.JButton();
        uiDownloadButton = new javax.swing.JButton();
        uiIndicator = new javax.swing.JProgressBar();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        uiBookmarkList = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        uiLabelBookmarkUser = new javax.swing.JLabel();
        uiLabelBookmarkHost = new javax.swing.JLabel();
        uiLabelPassword = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

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

        uiUploadButton.setText("Upload");
        uiUploadButton.addActionListener(formListener);

        uiDownloadButton.setText("Download");
        uiDownloadButton.addActionListener(formListener);

        jScrollPane6.setViewportView(uiBookmarkList);

        jLabel2.setText("Bookmarks");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Bookmark info"));

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel3.setText("Host");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setText("Password");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel5.setText("User");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, uiLabelPassword, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(uiLabelBookmarkHost, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(uiLabelBookmarkUser, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel3)
                            .add(jLabel5)
                            .add(jLabel4))
                        .add(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(uiLabelBookmarkHost, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(uiLabelBookmarkUser, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(uiLabelPassword, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setText("Create new");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(69, Short.MAX_VALUE)
                .add(jLabel2)
                .add(94, 94, 94))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jButton2)
                .add(85, 85, 85))
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jLabel2)
                .add(1, 1, 1)
                .add(jScrollPane6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 248, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton2)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jButton3.setText("Delete");
        jButton3.addActionListener(formListener);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jToolBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jScrollPane3)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(uiIndicator, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 324, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(uiUploadButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 116, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 458, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 458, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(uiDownloadButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 111, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jButton3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 99, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(0, 0, Short.MAX_VALUE))
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(jScrollPane5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                                    .add(jScrollPane2))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jToolBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jScrollPane5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                            .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(26, 26, 26)
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(uiIndicator, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(uiUploadButton)
                        .add(uiDownloadButton)
                        .add(jButton3)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 113, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
            else if (evt.getSource() == uiUploadButton) {
                MainFrame.this.uiUploadButtonActionPerformed(evt);
            }
            else if (evt.getSource() == uiDownloadButton) {
                MainFrame.this.uiDownloadButtonActionPerformed(evt);
            }
            else if (evt.getSource() == jButton3) {
                MainFrame.this.jButton3ActionPerformed(evt);
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
    }//GEN-LAST:event_uiUserNameInputActionPerformed

    private void uiHostInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiHostInputActionPerformed
    }//GEN-LAST:event_uiHostInputActionPerformed

    private void uiConnectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiConnectButtonActionPerformed
        final String password = new String(this.uiPasswordInput.getPassword());
        final String host = this.uiHostInput.getText();
        final String username = this.uiUserNameInput.getText();
        
            new Thread(new Runnable(){
               @Override
               public void run() {
                   try {
                       client.connect(host, username, password);
                   } catch (IOException ex) {
                       Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                   }
                    FileNode<Object> node = new FileNode<Object>(null,true);
                    node.setName("/");
                    FileNodeInTree root = new FileNodeInTree(node);

                    ((DefaultTreeModel)uiFtpTree.getModel()).setRoot(root);
                    CustomTreeExpansionListener l = new CustomTreeExpansionListener(
                            uiFtpTree, root, client, ftpSystemModel);
                    uiFtpTree.addTreeExpansionListener(l);

                    l.buildTree();
               }
            }).start();

    }//GEN-LAST:event_uiConnectButtonActionPerformed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        
    }//GEN-LAST:event_formMouseDragged

    private void uiUploadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiUploadButtonActionPerformed
        
        new UploadIndicator(this.fileSystemModel.getSelectedNodes()).execute();
                
    }//GEN-LAST:event_uiUploadButtonActionPerformed

    private void uiDownloadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiDownloadButtonActionPerformed
        new DownloadIndicator(this.ftpSystemModel.getSelectedNodes()).execute();
    }//GEN-LAST:event_uiDownloadButtonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        for(FileNode<?> node : this.fileSystemModel.getSelectedNodes())
            try {
                MainFrame.out.writeLogEvent("INFO", "Deleting file "+node.getName()+" from path "+this.navigator.getPath());
                this.navigator.remove(node.getName());
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_jButton3ActionPerformed

    
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

    private class UploadIndicator extends SwingWorker<Void, Object> {

        private List<FileNode<?>> nodes;
        
        UploadIndicator(List<FileNode<?>> nodes){
            this.nodes = nodes;
        }
        
        @Override
        protected Void doInBackground() throws Exception {
            uiDownloadButton.setEnabled(false);
            
            for(FileNode<?> node : nodes) {
                UploadThread t = (UploadThread)client.uploadFile((File)node.getFile());
                uiIndicator.setMaximum(node.getSize().intValue());
                while(t.isAlive()) {
                    MainFrame.this.uiIndicator.setValue(t.getStatus());
                }
                uiIndicator.setValue(0);
            }
            uiDownloadButton.setEnabled(false);
            return null;
            
        }
        
    }
    
    private class DownloadIndicator extends SwingWorker<Void, Object> {

        private List<FileNode<?>> nodes;
        
        DownloadIndicator(List<FileNode<?>> nodes) {
            this.nodes = nodes;
        }
        
        @Override
        protected Void doInBackground() throws Exception {
            for(FileNode<?> node : nodes) {
                DownloadThread t = (DownloadThread)client.downloadFile((FTPFile)node.getFile(), navigator.getPath());
                uiIndicator.setMaximum(node.getSize().intValue());
                while(t.getStatus()<node.getSize()) {
                    MainFrame.this.uiIndicator.setValue(t.getStatus().intValue());
                }
                uiIndicator.setValue(0);
                MainFrame.out.writeLogEvent("INFO", "Download finished");
            }
            return null;
        }
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.Box.Filler filler1;
    javax.swing.JButton jButton2;
    javax.swing.JButton jButton3;
    javax.swing.JLabel jLabel1;
    javax.swing.JLabel jLabel2;
    javax.swing.JLabel jLabel3;
    javax.swing.JLabel jLabel4;
    javax.swing.JLabel jLabel5;
    javax.swing.JPanel jPanel1;
    javax.swing.JPanel jPanel2;
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JScrollPane jScrollPane2;
    javax.swing.JScrollPane jScrollPane3;
    javax.swing.JScrollPane jScrollPane4;
    javax.swing.JScrollPane jScrollPane5;
    javax.swing.JScrollPane jScrollPane6;
    javax.swing.JToolBar jToolBar1;
    java.awt.Label label1;
    java.awt.Label label3;
    javax.swing.JTextPane logger;
    javax.swing.JList uiBookmarkList;
    javax.swing.JButton uiConnectButton;
    javax.swing.JTable uiCurrentDirTable;
    javax.swing.JButton uiDownloadButton;
    javax.swing.JTree uiFileSystem;
    javax.swing.JTable uiFtpTable;
    javax.swing.JTree uiFtpTree;
    javax.swing.JTextField uiHostInput;
    javax.swing.JProgressBar uiIndicator;
    javax.swing.JLabel uiLabelBookmarkHost;
    javax.swing.JLabel uiLabelBookmarkUser;
    javax.swing.JLabel uiLabelPassword;
    javax.swing.JPasswordField uiPasswordInput;
    javax.swing.JButton uiUploadButton;
    javax.swing.JTextField uiUserNameInput;
    // End of variables declaration//GEN-END:variables
}
