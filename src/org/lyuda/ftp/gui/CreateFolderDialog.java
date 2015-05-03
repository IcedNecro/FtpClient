package org.lyuda.ftp.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lyuda.ftp.core.FileNavigator;


public class CreateFolderDialog extends javax.swing.JDialog {

    private FileNavigator navigator;
    
    public CreateFolderDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public void setNavigator(FileNavigator nav) {
        this.navigator = nav;        
    }
    
    @SuppressWarnings("unchecked")
    private void initComponents() {

        uiNewFolderName = new javax.swing.JTextField();
        uiConfirmNewFolder = new javax.swing.JButton();
        uiCancelButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        uiConfirmNewFolder.setText("OK");
        uiConfirmNewFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uiConfirmNewFolderActionPerformed(evt);
            }
        });

        uiCancelButton.setText("Cancel");
        uiCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uiCancelButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Input new folder name:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(uiNewFolderName)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(uiConfirmNewFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(uiCancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                .addGap(58, 58, 58))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(uiNewFolderName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uiConfirmNewFolder)
                    .addComponent(uiCancelButton))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void uiConfirmNewFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiConfirmNewFolderActionPerformed
        String newFolder = this.uiNewFolderName.getText();
        try {
            this.navigator.createDirrectory(newFolder);
        } catch (IOException ex) {
            Logger.getLogger(CreateFolderDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
    }
    private void uiCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiCancelButtonActionPerformed
        this.setVisible(false);
    }

    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton uiCancelButton;
    private javax.swing.JButton uiConfirmNewFolder;
    private javax.swing.JTextField uiNewFolderName;
}
