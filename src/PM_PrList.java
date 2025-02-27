
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author User
 */
public class PM_PrList extends javax.swing.JFrame {
    private DefaultTableModel tableModel;
    private User authenticatedUser;
    private PR [] prList;
    int row = -1;
    /**
     * Creates new form PM_PrList
     * @param authenticatedUser
     */
    public PM_PrList(User authenticatedUser) {
        initComponents();
        this.authenticatedUser = authenticatedUser;
        setTitle("Manage Purchase Requisition - " + authenticatedUser.getUsername());
        setTable();
    }

    private void setTable(){
        tableModel = new DefaultTableModel();
        
        String [] columnName = {"PR ID", "Creation timestamp", "Due date", "Status"};
        tableModel.setColumnIdentifiers(columnName);
        
        PR [] prList = new PR().getPRList();
        this.prList = prList;
        
        for (PR pr : prList){
            tableModel.addRow(pr.getCurrentPR());
        }
        PrTable.setModel(tableModel);
    }
    
    public void refresh(String prId){
        PR targetPR = new PR().getPRByID(prId);
        PM_PrItems pm_prItems = new PM_PrItems(targetPR, authenticatedUser);
        pm_prItems.setVisible(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        PrTable = new javax.swing.JTable();
        rejectBtn = new javax.swing.JButton();
        viewItemsBtn = new javax.swing.JButton();
        generatePOBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("List of Purchase Requisition (PR)");

        PrTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        PrTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                PrTableMouseReleased(evt);
            }
        });
        PrTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PrTableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(PrTable);

        rejectBtn.setText("Reject");
        rejectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rejectBtnActionPerformed(evt);
            }
        });

        viewItemsBtn.setText("View Items");
        viewItemsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewItemsBtnActionPerformed(evt);
            }
        });

        generatePOBtn.setText("Generate PO");
        generatePOBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generatePOBtnActionPerformed(evt);
            }
        });

        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(generatePOBtn)
                            .addGap(79, 79, 79)
                            .addComponent(rejectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(viewItemsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(67, 67, 67)
                            .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rejectBtn)
                    .addComponent(viewItemsBtn)
                    .addComponent(generatePOBtn)
                    .addComponent(backBtn))
                .addGap(59, 59, 59))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PrTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PrTableMouseReleased
        // TODO add your handling code here:
        int row = PrTable.getSelectedRow();
        
        this.row=row;
    }//GEN-LAST:event_PrTableMouseReleased

    private void PrTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PrTableKeyReleased
        // TODO add your handling code here:
        int row = PrTable.getSelectedRow();
        
        this.row=row;
    }//GEN-LAST:event_PrTableKeyReleased

    private void rejectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rejectBtnActionPerformed
        // TODO add your handling code here:
        try{
            PR targetPR = prList[row];
            
            if (targetPR.getStatus().equals("approved")){
                throw new Exception("Cannot reject a PR which is already approved");
            }

            targetPR.updatePRStatus("rejected"); //approved or rejected
            JOptionPane.showMessageDialog(this, "rejected PR");
            setTable();
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "Fail to reject PR: \n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_rejectBtnActionPerformed

    private void viewItemsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewItemsBtnActionPerformed
        // TODO add your handling code here:
        try{
            if (row == -1) {
                throw new Exception("Please select a row");
            }
            PR targetPR = prList[row];

            PM_PrItems pm_Pritems = new PM_PrItems(targetPR, authenticatedUser);
            pm_Pritems.setVisible(true);
            dispose();
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_viewItemsBtnActionPerformed

    private void generatePOBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generatePOBtnActionPerformed
        try{
            if (row == -1) {
                throw new Exception("Please select a row");
            }
            PR targetPR = prList[row];
            
            if (!"pending".equals(targetPR.getStatus())){
                throw new Exception("You can only generate PO for PR with 'pending' status");
            }

            PO newPO = new PO();
            newPO.setPR(targetPR);
            newPO.setUser(authenticatedUser);
            newPO.createPO();
            JOptionPane.showMessageDialog(this, "Generate PO successfully");
            setTable();
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_generatePOBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        PM_Dashboard pmDashboard = new PM_Dashboard(this.authenticatedUser);
        pmDashboard.setVisible(true);
        dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable PrTable;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton generatePOBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton rejectBtn;
    private javax.swing.JButton viewItemsBtn;
    // End of variables declaration//GEN-END:variables
}
