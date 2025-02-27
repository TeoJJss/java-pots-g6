
public class FM_Dashboard extends javax.swing.JFrame {
    private User authenticatedUser;
    /**
     * Creates new form FM_Dashboard
     * @param authenticatedUser
     */
    public FM_Dashboard(User authenticatedUser) {
        setTitle("FM Dashboard - " + authenticatedUser.getUsername());
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

        welcome_message = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JButton();
        welcomeMsgLabel = new javax.swing.JLabel();
        managePOButton = new javax.swing.JButton();
        managePaymentButton = new javax.swing.JButton();
        adminBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        logoutBtn.setText("Logout");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        welcomeMsgLabel.setText("Hello");

        managePOButton.setText("Manage Purchase Order");
        managePOButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                managePOButtonActionPerformed(evt);
            }
        });

        managePaymentButton.setText("View Supplier Payment");
        managePaymentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                managePaymentButtonActionPerformed(evt);
            }
        });

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(welcome_message, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(welcomeMsgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(adminBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(251, 251, 251)
                        .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(managePOButton)
                        .addGap(79, 79, 79)
                        .addComponent(managePaymentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(welcome_message))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(welcomeMsgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(100, 100, 100)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(managePaymentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(managePOButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logoutBtn)
                    .addComponent(adminBtn))
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        // TODO add your handling code here:
        All_Login loginPage = new All_Login();
        loginPage.setVisible(true);
        dispose();
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void managePOButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_managePOButtonActionPerformed
        // TODO add your handling code here:
        FM_ViewPO FMViewPo = new FM_ViewPO(this.authenticatedUser);
        FMViewPo.setVisible(true);
        dispose();
    }//GEN-LAST:event_managePOButtonActionPerformed

    private void managePaymentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_managePaymentButtonActionPerformed
        // TODO add your handling code here:
        FM_SupplierList FMSupplierList = new FM_SupplierList(authenticatedUser);
        FMSupplierList.setVisible(true);
        dispose();
    }//GEN-LAST:event_managePaymentButtonActionPerformed

    private void adminBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminBtnActionPerformed
        // TODO add your handling code here:
        AM_Dashboard am_dash = new AM_Dashboard(authenticatedUser);
        am_dash.setVisible(true);
        dispose();
    }//GEN-LAST:event_adminBtnActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adminBtn;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JButton managePOButton;
    private javax.swing.JButton managePaymentButton;
    private javax.swing.JLabel welcomeMsgLabel;
    private javax.swing.JLabel welcome_message;
    // End of variables declaration//GEN-END:variables
}
