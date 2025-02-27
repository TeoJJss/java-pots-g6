
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FM_PoItems extends javax.swing.JFrame {
    private User authenticatedUser;
    private Item [] poItems;
    /**
     * Creates new form FM_PoItems
     * @param authenticatedUser
     * @param targetPO
     */
    public FM_PoItems(User authenticatedUser, PO targetPO) {
        setTitle("View PO Items - FM");
        initComponents();
        this.authenticatedUser = authenticatedUser;
        Item [] poItems = targetPO.getPOItems();
        this.poItems = poItems;
        String poId = targetPO.getPoId();
        this.poIdLabel.setText(poId);
        
        // Set creator Info
        User poCreator = targetPO.getUser();
        String creatorId = poCreator.getUserId();
        String creatorName = poCreator.getUsername();
        String creatorRole = poCreator.getRole();
        this.creatorInfoLabel.setText(creatorId + " " + creatorName + " (" + creatorRole + ")");
        
        setTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        poIdLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        itemsTable = new javax.swing.JTable();
        creatorInfoLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Item list for PO: ");

        poIdLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        poIdLabel.setText("po id");

        itemsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        itemsTable.setEnabled(false);
        jScrollPane1.setViewportView(itemsTable);

        creatorInfoLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        creatorInfoLabel.setText("jLabel3");

        jLabel3.setText("This PO is raised by: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(poIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(creatorInfoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 933, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(462, 462, 462))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(poIdLabel)
                    .addComponent(creatorInfoLabel)
                    .addComponent(jLabel3))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(backBtn)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        FM_ViewPO poView = new FM_ViewPO(authenticatedUser);
        poView.setVisible(true);
        dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    
    private void setTable() {
        // Set column names for the table
        DefaultTableModel tableModel = new DefaultTableModel();
        String[] columnNames = {"Item ID", "Item Name", "Current Stock / Reorder Level", "Reorder Amount", "Supplier"};
        tableModel.setColumnIdentifiers(columnNames);
        
        for (Item item : poItems){
            String [] itemInfo = new String [5];
            Supplier itemSupplier = item.getSupplier();
            
            // Item info
            int itemQuantity = item.getQuantity(); 
            int reorderLevel = item.getReorderLevel();
            String stockStatus = "";
            if (itemQuantity < reorderLevel){
                // Insufficient
                stockStatus = "Insufficient";
            }else{
                stockStatus = "Sufficient";
            }
            itemInfo[0] = item.getItemId();
            itemInfo[1] = item.getItemName();
            itemInfo[2] = itemQuantity + " / " + reorderLevel + " (" + stockStatus + ")";
            itemInfo[3] = item.getReorderAmt() + "";
            itemInfo[4] = itemSupplier.toString();
            tableModel.addRow(itemInfo);
        }
        
        itemsTable.setModel(tableModel);
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JLabel creatorInfoLabel;
    private javax.swing.JTable itemsTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel poIdLabel;
    // End of variables declaration//GEN-END:variables
}
