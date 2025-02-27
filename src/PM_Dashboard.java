/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author yifen
 */
public class PM_Dashboard extends javax.swing.JFrame {
     private User authenticatedUser;
    /**
     * Creates new form PM_Dashboard
     * @param authenticatedUser
     */
    public PM_Dashboard(User authenticatedUser) {
        setTitle("PM Dashboard - " + authenticatedUser.getUsername());
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

        welcomeMsgLabel = new javax.swing.JLabel();
        ManageItemsButton = new javax.swing.JButton();
        SupplierButton = new javax.swing.JButton();
        PRButton = new javax.swing.JButton();
        managePOButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        adminBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        welcomeMsgLabel.setText("Hello");

        ManageItemsButton.setText("Manage Items");
        ManageItemsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ManageItemsButtonActionPerformed(evt);
            }
        });

        SupplierButton.setText("View Suppliers");
        SupplierButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SupplierButtonActionPerformed(evt);
            }
        });

        PRButton.setText("Manage Purchase Requisition");
        PRButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PRButtonActionPerformed(evt);
            }
        });

        managePOButton.setText("View Purchase Order");
        managePOButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                managePOButtonActionPerformed(evt);
            }
        });

        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
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
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SupplierButton)
                    .addComponent(managePOButton)
                    .addComponent(PRButton)
                    .addComponent(ManageItemsButton))
                .addContainerGap(127, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(welcomeMsgLabel)
                .addGap(53, 53, 53))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(adminBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoutButton)
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(welcomeMsgLabel)
                .addGap(53, 53, 53)
                .addComponent(ManageItemsButton)
                .addGap(18, 18, 18)
                .addComponent(SupplierButton)
                .addGap(18, 18, 18)
                .addComponent(PRButton)
                .addGap(18, 18, 18)
                .addComponent(managePOButton)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logoutButton)
                    .addComponent(adminBtn))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PRButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PRButtonActionPerformed
        // TODO add your handling code here:
        PM_PrList PMPrList = new PM_PrList(this.authenticatedUser);
        PMPrList.setVisible(true);
        dispose();
    }//GEN-LAST:event_PRButtonActionPerformed

    private void managePOButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_managePOButtonActionPerformed
        // TODO add your handling code here:
        PM_ViewPO PMViewPo = new PM_ViewPO(this.authenticatedUser);
        PMViewPo.setVisible(true);
        dispose();
    }//GEN-LAST:event_managePOButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        // TODO add your handling code here: 
        All_Login loginPage = new All_Login();
        loginPage.setVisible(true);
        dispose();
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void SupplierButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupplierButtonActionPerformed
        // TODO add your handling code here:
        PM_SupplierList PMSupplierList = new PM_SupplierList(this.authenticatedUser);
        PMSupplierList.setVisible(true);
        dispose();
    }//GEN-LAST:event_SupplierButtonActionPerformed

    private void ManageItemsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ManageItemsButtonActionPerformed
        // TODO add your handling code here:
        PM_ItemList PMItemList = new PM_ItemList(this.authenticatedUser);
        PMItemList.setVisible(true);
        dispose();
    }//GEN-LAST:event_ManageItemsButtonActionPerformed

    private void adminBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminBtnActionPerformed
        // TODO add your handling code here:
        AM_Dashboard am_dash = new AM_Dashboard(authenticatedUser);
        am_dash.setVisible(true);
        dispose();
    }//GEN-LAST:event_adminBtnActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ManageItemsButton;
    private javax.swing.JButton PRButton;
    private javax.swing.JButton SupplierButton;
    private javax.swing.JButton adminBtn;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton managePOButton;
    private javax.swing.JLabel welcomeMsgLabel;
    // End of variables declaration//GEN-END:variables
}
