
public class SM_Dashboard extends javax.swing.JFrame {
    private User authenticatedUser;
    /**
     * Creates new form SM_Dashboard
     */
    public SM_Dashboard(User authenticatedUser) {
        setTitle("SM Dashboard - " + authenticatedUser.getUsername());
        this.authenticatedUser = authenticatedUser;
        initComponents();
        welcomeMsgLabel.setText("Hello " + authenticatedUser.getUsername() + ", " + authenticatedUser.getRole());
        
        if (authenticatedUser.getRole().equals("AM")){
            adminBtn.setVisible(true);
        }else{
            adminBtn.setVisible(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        manageItemBtn = new javax.swing.JButton();
        createPRBtn = new javax.swing.JButton();
        viewPOBtn = new javax.swing.JButton();
        logoutBtn = new javax.swing.JButton();
        welcomeMsgLabel = new javax.swing.JLabel();
        adminBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        manageItemBtn.setText("Manage Item Sales");
        manageItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageItemBtnActionPerformed(evt);
            }
        });

        createPRBtn.setText("Create PR");
        createPRBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createPRBtnActionPerformed(evt);
            }
        });

        viewPOBtn.setText("View PO");
        viewPOBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewPOBtnActionPerformed(evt);
            }
        });

        logoutBtn.setText("Logout");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        welcomeMsgLabel.setText("Hello");

        adminBtn.setText("Admin");
        adminBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(manageItemBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addComponent(createPRBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(welcomeMsgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(adminBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logoutBtn)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(viewPOBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(welcomeMsgLabel)
                .addGap(89, 89, 89)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(manageItemBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createPRBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(viewPOBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logoutBtn)
                    .addComponent(adminBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        // TODO add your handling code here:
        All_Login loginPage = new All_Login();
        loginPage.setVisible(true);
        dispose();
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void manageItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageItemBtnActionPerformed
        // TODO add your handling code here:
        SM_ManageItem SMManageItem = new SM_ManageItem(this.authenticatedUser);
        SMManageItem.setVisible(true);
        dispose();
    }//GEN-LAST:event_manageItemBtnActionPerformed

    private void createPRBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createPRBtnActionPerformed
        // TODO add your handling code here:
        SM_CreatePR SMCreatePR = new SM_CreatePR(this.authenticatedUser);
        SMCreatePR.setVisible(true);
        dispose();
    }//GEN-LAST:event_createPRBtnActionPerformed

    private void viewPOBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewPOBtnActionPerformed
        // TODO add your handling code here:
        SM_ViewPO SMViewPO = new SM_ViewPO(this.authenticatedUser);
        SMViewPO.setVisible(true);
        dispose();
    }//GEN-LAST:event_viewPOBtnActionPerformed

    private void adminBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminBtnActionPerformed
        // TODO add your handling code here:
        AM_Dashboard am_dash = new AM_Dashboard(authenticatedUser);
        am_dash.setVisible(true);
        dispose();
    }//GEN-LAST:event_adminBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adminBtn;
    private javax.swing.JButton createPRBtn;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JButton manageItemBtn;
    private javax.swing.JButton viewPOBtn;
    private javax.swing.JLabel welcomeMsgLabel;
    // End of variables declaration//GEN-END:variables
}
