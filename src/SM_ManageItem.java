
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Louis
 */
public class SM_ManageItem extends javax.swing.JFrame {
    private User authenticatedUser;
    private DefaultTableModel tableModel;
    /**
     * Creates new form SM_ManageItem
     */
    public SM_ManageItem(User authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        initComponents();
        setTable();
    }
    private void setTable(){
        tableModel = new DefaultTableModel();
        String[] columnNames = {"Item ID","Sales","Date"};
        tableModel.setColumnIdentifiers(columnNames);
        ItemSales[] itemSalesRecords = new ItemSales().getSalesRecordsList();
            if(itemSalesRecords != null){
                for (ItemSales item : itemSalesRecords){
                    if(item != null){
                        tableModel.addRow(item.getItemSalesInfo());
                    }
            }
        }
        
        itemTable.setModel(tableModel);
        itemTable.clearSelection();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        itemTable = new javax.swing.JTable();
        backBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        itemSalesField = new javax.swing.JTextField();
        itemIDField = new javax.swing.JTextField();
        dateField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("ItemID");

        jLabel2.setText("ItemSales");

        jLabel3.setText("Date");

        itemSalesField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSalesFieldActionPerformed(evt);
            }
        });

        itemIDField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemIDFieldActionPerformed(evt);
            }
        });

        dateField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(updateBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backBtn)
                .addGap(24, 24, 24))
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(itemIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(itemSalesField, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(itemIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(itemSalesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backBtn)
                    .addComponent(updateBtn))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemTableMouseReleased
        // TODO add your handling code here:
    int row = itemTable.getSelectedRow();      
    String itemId = tableModel.getValueAt(row, 0).toString();
    String itemSales = tableModel.getValueAt(row, 1).toString();
    String date = tableModel.getValueAt(row, 2).toString();
    
    itemIDField.setText(itemId);
    itemSalesField.setText(itemSales);
    dateField.setText(date);
    }//GEN-LAST:event_itemTableMouseReleased

    private void dateFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateFieldActionPerformed

    private void itemIDFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemIDFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemIDFieldActionPerformed

    private void itemSalesFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSalesFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemSalesFieldActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        // TODO add your handling code here:    
        try {
            String itemId = itemIDField.getText();
            int sales = Integer.parseInt(itemSalesField.getText());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String currentDate = LocalDate.now().format(formatter);

            if (itemId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Item ID");
                return;
            }

            ItemSales newSales = new ItemSales();
            newSales.setItemById(itemId); 
            newSales.setItemSales(sales);
            dateField.setText(currentDate);
            newSales.addSalesRecord();

            setTable();
            JOptionPane.showMessageDialog(this, "Sales record updated successfully.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error updating sales record: ");
        }        
    }//GEN-LAST:event_updateBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        SM_Dashboard sm_dash = new SM_Dashboard(authenticatedUser);
        sm_dash.setVisible(true);
        dispose();
    }//GEN-LAST:event_backBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JTextField dateField;
    private javax.swing.JTextField itemIDField;
    private javax.swing.JTextField itemSalesField;
    private javax.swing.JTable itemTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
