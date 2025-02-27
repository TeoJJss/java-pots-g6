
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
public class PM_PrItems extends javax.swing.JFrame {
    private DefaultTableModel tableModel;
    private Item [] prItems;
    private PR targetPR;
    int row = -1;
    private final User authenticatedUser;
    /**
     * Creates new form PM_PrItems
     * @param pr
     * @param authenticatedUser
     */
    public PM_PrItems(PR pr, User authenticatedUser) {
        this.targetPR = pr;
        this.authenticatedUser = authenticatedUser;
        setTitle("Items in PR - " + authenticatedUser.getUsername());
        
        initComponents();
        setTable();
        prIdLabel.setText(targetPR.getPRId());
    }
    
    private void setTable(){
        tableModel = new DefaultTableModel();
        
        Item [] itemList = targetPR.getPRItems();
        this.prItems = itemList;
        
        String[] columnNames = {"Item ID", "Item Name", "Current Stock / Reorder Level", "Reorder Amount"};
        tableModel.setColumnIdentifiers(columnNames);
        
        for (Item item : itemList){
            if(item != null){
                String [] itemInfo = {item.getItemId(), item.getItemName(), item.getQuantity() + " / " + item.getReorderLevel(), item.getReorderAmt()+""};
                tableModel.addRow(itemInfo);
            }
        }
        
        itemTable.setModel(tableModel);
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
        prIdLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        itemTable = new javax.swing.JTable();
        backBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        reorderAmtField = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Items in the PR:");

        prIdLabel.setText("pr-id");

        itemTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        itemTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                itemTableMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(itemTable);

        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        editBtn.setText("Edit Reorder Amount");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(prIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editBtn)
                    .addComponent(reorderAmtField, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(prIdLabel))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reorderAmtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(editBtn)
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        PM_PrList pm_prList = new PM_PrList(authenticatedUser);
        pm_prList.setVisible(true);
        dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        // TODO add your handling code here:
        int reorderAmt = Integer.parseInt(reorderAmtField.getValue().toString());
        
        Item targetPRItem = prItems[row];
        try{
            if (reorderAmt < 1){
                throw new Exception("Invalid reorder amount");
            }
            targetPR.editReorderAmt(targetPRItem, reorderAmt);
            dispose();
            PM_PrList pM_PrList = new PM_PrList(authenticatedUser);
            pM_PrList.refresh(targetPR.getPRId());
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "Fail to edit PO: \n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_editBtnActionPerformed

    private void itemTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemTableMouseReleased
        // TODO add your handling code here:
        int row = itemTable.getSelectedRow();
        this.row = row;
    }//GEN-LAST:event_itemTableMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JTable itemTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel prIdLabel;
    private javax.swing.JSpinner reorderAmtField;
    // End of variables declaration//GEN-END:variables
}
