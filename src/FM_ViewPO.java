import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FM_ViewPO extends javax.swing.JFrame {
    private User authenticatedUser;
    private DefaultTableModel tableModel = new DefaultTableModel();
    private javax.swing.JTable apptTable;
    private JButton approveButton, rejectButton, paidButton, backButton, viewItemsButton;

    /**
     * Creates new form FM_ViewPO
     * @param authenticatedUser
     */
    public FM_ViewPO(User authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        apptTable = new javax.swing.JTable();
        setTitle("View PO List - FM");
        initComponents();
        setTable();
        displayAppts();
    }

    private void initComponents() {
        // Initialize the JTable
        apptTable = new javax.swing.JTable(tableModel);

        // Set layout and add JScrollPane to contain the table
        JScrollPane scrollPane = new JScrollPane(apptTable);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Create buttons
        approveButton = new JButton("Approve");
        rejectButton = new JButton("Reject");
        paidButton = new JButton("Paid");
        backButton = new JButton("Back");
        viewItemsButton = new JButton("View Items");

        // Create left panel for "Approve", "Reject", "Paid" buttons
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));  // Vertical alignment
        leftPanel.add(approveButton);
        leftPanel.add(rejectButton);
        leftPanel.add(paidButton);

        // Create right panel for "Back" and "View Items" buttons
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));  // Vertical alignment
        rightPanel.add(backButton);
        rightPanel.add(viewItemsButton);

        // Add the left and right panels to the frame
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(leftPanel, BorderLayout.WEST);  // Add left panel (buttons for PO status)
        buttonPanel.add(rightPanel, BorderLayout.EAST); // Add right panel (buttons for other actions)

        // Add the button panel to the frame
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Set default close operation
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // Add action listeners to buttons
        approveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePOStatus("approved");
            }
        });

        rejectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePOStatus("rejected");
            }
        });

        paidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePOStatus("paid");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack();  // Method to handle back button action
            }
        });

        viewItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewItems();  
            }
        });

        // Layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
            .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }

    private void setTable() {
        // Set column names for the table
        String[] columnNames = {"PO ID", "Due Date", "Creator", "Created timestamp", "Status"};
        tableModel.setColumnIdentifiers(columnNames);
    }

    private void displayAppts() {
        PO[] poList = PO.getPOList();
        for (PO po : poList) {
            if (po != null) {
                PR poPr = po.getPR();
                User poCreator = po.getUser();
                String[] poInfo = new String[5];

                poInfo[0] = po.getPoId();           // PO ID
                poInfo[1] = poPr.getDueDate();      // Due Date
                poInfo[2] = poCreator.toString();   // PO Creator (User)
                poInfo[3] = po.getTimestamp();      // Timestamp
                poInfo[4] = po.getStatus();         // Status

                // Add row to the table
                tableModel.addRow(poInfo);
            }
        }
    }

    private void updatePOStatus(String newStatus) {
        int selectedRow = apptTable.getSelectedRow();
        
        // Ensure a row is selected
        if (selectedRow != -1) {
            String poId = tableModel.getValueAt(selectedRow, 0).toString();
            PO po = PO.getPOById(poId); 
            if (po != null) {
                try {
                    po.updatePOStatus(newStatus); // Update the status of the selected PO
                    tableModel.setValueAt(newStatus, selectedRow, 4);
                    JOptionPane.showMessageDialog(this, "PO status updated to: " + newStatus);
                    
                    // If new status is paid, display payment receipt
                    if(newStatus.equals("paid")){
                        FM_PaymentReport paymentReport = new FM_PaymentReport(authenticatedUser, po);
                        paymentReport.setVisible(true);
                        dispose();
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    System.err.println(e);
                }
                
            } else {
                JOptionPane.showMessageDialog(this, "PO not found!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row.");
        }
    }

    // Method to handle the back button action
    private void goBack() {
        // Close the current window
        this.dispose();
        
        FM_Dashboard fm_dash = new FM_Dashboard(authenticatedUser);
        fm_dash.setVisible(true);
    }

    // Method to open the "FM_PoItems" window when "View Items" is clicked
    private void viewItems() {
        int selectedRow = apptTable.getSelectedRow();
        if (selectedRow != -1) {
            String poId = tableModel.getValueAt(selectedRow, 0).toString();
            PO selectedPO = PO.getPOById(poId); 
            FM_PoItems fmPoItems = new FM_PoItems(authenticatedUser, selectedPO);
            fmPoItems.setVisible(true);
            dispose();
        }else{
            JOptionPane.showMessageDialog(this, "Please select a row to view PO Items.");
        }
    }

    // Variables declaration - do not modify
    // End of variables declaration
}
