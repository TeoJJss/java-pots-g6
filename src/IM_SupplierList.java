
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
public class IM_SupplierList extends javax.swing.JFrame {
    private User authenticatedUser;
    private DefaultTableModel tableModel;
    /**
     * Creates new form IM_SupplierList
     * @param authenticatedUser
     */
    public IM_SupplierList(User authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        initComponents();
        
        setTitle("List of Suppliers - IM");
        setTable();
    }
    
    private void setTable() {
        tableModel = new DefaultTableModel();
        // Set column names for the table
        String[] columnNames = {"Supplier ID", "Supplier Name", "Supplier Email", "Status"};
        tableModel.setColumnIdentifiers(columnNames);
        
        Supplier [] supplierList = Supplier.getSupplierList();
        for (Supplier supp : supplierList){
            String [] suppInfo = supp.getCurrentSupplier();
            tableModel.addRow(suppInfo);
        }
        
        suppliersTable.setModel(tableModel);
        
    }
    
    private void clearFields(){
        supplierIdField.setText("");
        supplierNameField.setText("");
        supplierEmailField.setText("");
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
        suppliersTable = new javax.swing.JTable();
        supplierId = new javax.swing.JLabel();
        supplierName = new javax.swing.JLabel();
        supplierEmail = new javax.swing.JLabel();
        supplierIdField = new javax.swing.JTextField();
        supplierNameField = new javax.swing.JTextField();
        supplierEmailField = new javax.swing.JTextField();
        addBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        suppliersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        suppliersTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                suppliersTableMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(suppliersTable);

        supplierId.setText("Supplier ID");

        supplierName.setText("Supplier Name");

        supplierEmail.setText("Supplier Email");

        supplierIdField.setText("auto-generated");
        supplierIdField.setEnabled(false);

        addBtn.setText("Add");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        editBtn.setText("Edit");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Deleting a supplier will delete all items associated with the supplier");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(supplierId, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(supplierName, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(supplierEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(supplierIdField, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                    .addComponent(supplierNameField)
                                    .addComponent(supplierEmailField))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(editBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(supplierId)
                            .addComponent(supplierIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(addBtn)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(supplierName)
                            .addComponent(supplierNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(editBtn)))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(supplierEmail)
                    .addComponent(supplierEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(deleteBtn)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearBtn)
                    .addComponent(backBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(42, 42, 42))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        // TODO add your handling code here:
        clearFields();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        String supplierId = supplierIdField.getText().replace(",", "");
        Supplier targetSupplier = Supplier.getSupplierById(supplierId);
        
        try{
            if (supplierId.strip().isEmpty() || targetSupplier == null){
                throw new Exception();
            }
            targetSupplier.deleteSupplier();
            setTable();
            clearFields();
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "Please select a row.");
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        // TODO add your handling code here:
        String supplierId = supplierIdField.getText().replace(",", "");
        Supplier targetSupplier = Supplier.getSupplierById(supplierId);
        String supplierName = supplierNameField.getText().replace(",", "");
        String supplierEmail = supplierEmailField.getText().replace(",", "");
        try{
            if (supplierId.strip().isEmpty() || targetSupplier == null || supplierName.strip().isEmpty() || supplierEmail.strip().isEmpty()){
                throw new Exception();
            }
            targetSupplier.editSupplier(supplierName, supplierEmail);
            setTable();
            clearFields();
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "Please select a row.");
        }
    }//GEN-LAST:event_editBtnActionPerformed

    private void suppliersTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_suppliersTableMouseReleased
        // TODO add your handling code here:
        int row = suppliersTable.getSelectedRow();
        String supplierId = tableModel.getValueAt(row, 0).toString();
        String supplierName = tableModel.getValueAt(row, 1).toString();
        String supplierEmail = tableModel.getValueAt(row, 2).toString();
        
        supplierIdField.setText(supplierId);
        supplierNameField.setText(supplierName);
        supplierEmailField.setText(supplierEmail);
    }//GEN-LAST:event_suppliersTableMouseReleased

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // TODO add your handling code here:
        String supplierName = supplierNameField.getText().replace(",", "");
        String supplierEmail = supplierEmailField.getText().replace(",", "");
        
        try{
            if(supplierName.strip().isEmpty() || supplierEmail.strip().isEmpty() || !supplierEmail.contains("@")){
                throw new Exception();
            }
            Supplier supp = new Supplier(supplierName, supplierEmail);
            IM_ItemsSupplier im_itemsSupplier = new IM_ItemsSupplier(authenticatedUser, supp);
            im_itemsSupplier.setVisible(true);
            dispose();
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "Invalid input values.");
        }
        
        
    }//GEN-LAST:event_addBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        IM_Dashboard im_dash = new IM_Dashboard(authenticatedUser);
        im_dash.setVisible(true);
        dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel supplierEmail;
    private javax.swing.JTextField supplierEmailField;
    private javax.swing.JLabel supplierId;
    private javax.swing.JTextField supplierIdField;
    private javax.swing.JLabel supplierName;
    private javax.swing.JTextField supplierNameField;
    private javax.swing.JTable suppliersTable;
    // End of variables declaration//GEN-END:variables
}
