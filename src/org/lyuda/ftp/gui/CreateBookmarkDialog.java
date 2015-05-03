package org.lyuda.ftp.gui;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import org.lyuda.ftp.config.Config;

/**
 *
 * @author mila
 */
public class CreateBookmarkDialog extends javax.swing.JDialog {

    private JList list;
    
    /**
     * Creates new form CreateBookmarkDialog
     */
    public CreateBookmarkDialog(java.awt.Frame parent, boolean modal, JList list) {
        super(parent, modal);
        initComponents();
        this.list = list;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        uiNewBookmarkHost = new javax.swing.JFormattedTextField();
        uiNewBookmarkUserName = new javax.swing.JFormattedTextField();
        uiNewBookMarkPassword = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        uiConfirmNewBookMark = new javax.swing.JButton();
        uiCancelBookmarkCreation = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Host");

        jLabel2.setText("User Name");

        jLabel3.setText("Password");

        uiConfirmNewBookMark.setText("Ok");
        uiConfirmNewBookMark.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uiConfirmNewBookMarkActionPerformed(evt);
            }
        });

        uiCancelBookmarkCreation.setText("Cancel");
        uiCancelBookmarkCreation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uiCancelBookmarkCreationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(uiNewBookmarkHost, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(uiNewBookmarkUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(uiConfirmNewBookMark, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(uiCancelBookmarkCreation))
                            .addComponent(uiNewBookMarkPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uiNewBookmarkHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uiNewBookmarkUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uiNewBookMarkPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(uiCancelBookmarkCreation)
                    .addComponent(uiConfirmNewBookMark))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void uiConfirmNewBookMarkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiConfirmNewBookMarkActionPerformed
        if(!(   this.uiNewBookMarkPassword.getText().length()==0 || 
                this.uiNewBookmarkHost.getText().length()==0 ||
                this.uiNewBookmarkUserName.getText().length()==0)) {
            Config config = Config.getInstance();

            Config.BookMark bookmark = new Config.BookMark();
            bookmark.setHost(this.uiNewBookmarkHost.getText());
            bookmark.setLogin(this.uiNewBookmarkUserName.getText());
            bookmark.setPassword(this.uiNewBookMarkPassword.getText());
            
            config.addBookMark(bookmark);
            
            ((DefaultListModel)this.list.getModel()).addElement(bookmark);
        }
        this.setVisible(false);
            
    }//GEN-LAST:event_uiConfirmNewBookMarkActionPerformed

    private void uiCancelBookmarkCreationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uiCancelBookmarkCreationActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_uiCancelBookmarkCreationActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton uiCancelBookmarkCreation;
    private javax.swing.JButton uiConfirmNewBookMark;
    private javax.swing.JFormattedTextField uiNewBookMarkPassword;
    private javax.swing.JFormattedTextField uiNewBookmarkHost;
    private javax.swing.JFormattedTextField uiNewBookmarkUserName;
    // End of variables declaration//GEN-END:variables
}
