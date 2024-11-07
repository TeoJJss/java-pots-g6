
import java.util.Arrays;
import java.util.List;

public class POTS_G6 {

    public static void main(String args[]){
        /* List of user */
        User [] userList = User.getUserList();
        for (User user : userList){
            System.out.println(user.getUserId() + ", " + user.getUsername() + ", "+ user.getRole());
        }
        
        /* Add user*/
        User newUser = new User("jj", "jj123", "PM");
        newUser.addUser();

        /* Edit user*/
        try{
            userList[1].editUser("max", "newpassword");
        } catch (Exception e) {
            System.out.println(e);
        }
        
        /* Delete user */
        try{
            userList[1].deleteUser();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
        
        /* List of Items (View), including supplier info */
        // Show information in table
        Item [] items = Item.getActiveItemList();
        for (Item i : items){
            if (i == null){
                continue;
            }
            String [] itemInfo = i.getItemInfo();
            Supplier supplierItem = i.getSupplier();
            String [] supplierInfo = supplierItem.getCurrentSupplier();
            
            System.out.println(itemInfo[0] + ", " + itemInfo[1]+ ", " +itemInfo[2]+ ", " +itemInfo[3] + ", " + itemInfo[4]);
            System.out.println(supplierInfo[0] + ", " + supplierInfo[1] + ", " +supplierInfo[2] + ", " + supplierInfo[3]);
            System.out.println("\n");
        }
        
        /* Daily Sales Entry */
        // Require user to select an item id first
        // Item item = new Item();
        try{
            int itemTableInd = 12; // Get index from the clicked row in the table
            items[24].submitItemSales(itemTableInd);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        
        /* Sales Report */
        // Require user to select start date and end date
        Item [] itemList = ItemSales.getItemList();
        ItemSales itemsales = new ItemSales();
        try{
            for (Item i : itemList){
                if (i == null){
                    continue;
                }
                itemsales.setItemById(i.getItemId());
                System.out.println(Arrays.toString(i.getItemInfo()));
                int sales = itemsales.getSalesByItem("21/05/2024", "23/11/2024");
                System.out.println(sales);
                System.out.println("\n");
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        
        /* List of items below reorder level */
        // Display in table
        try{
            List <Item> lowStockItems = Item.getLowItemList();
            System.out.println("Items with quantity below reorder level:");
            for (Item lowStockItem : lowStockItems) {
                if (lowStockItem == null){
                    continue;
                }
                System.out.println("Item ID: " + lowStockItem.getItemId() + ", Name: " + lowStockItem.getItemName()
                        + ", Quantity: " + lowStockItem.getQuantity()
                        + ", Reorder Level: " + lowStockItem.getReorderLevel());
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        
        /* Add item with existing supplier */
        Item item = new Item("test item", 25, 14);
        Supplier supplier = new Supplier();
        supplier.setCurrentSupplier("S2", "Living Sdn Bhd", "living@sdn.com", "active");
        item.setSupplier(supplier);
        
        item.addItem();
         
 
        /* Edit item*/
        try {
            Item itemTest = itemList[2]; // pass the row ind of item in the table
            itemTest.editItem("desk", 31, 30);
        } catch (Exception e) {
            System.out.println(e);
        }
         
        
        /* Delete item */
        try {
            Item itemTest = itemList[3]; // pass the row ind of item in the table
            itemTest.deleteItem();
        } catch (Exception e) {
            System.out.println(e);
        }
         
        
        /* Submit item new stock */
        try{
            Item itemTest = itemList[7]; // pass the row ind of item in the table
            itemTest.submitNewStock(10);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        /* Create PR from item list */        
        PR pr1 = new PR("30/11/2024 12:00");
        
        // set reorder amount for each selected item
        itemList[0].setReorderAmt(10);
        itemList[1].setReorderAmt(20);
        
        Item [] itemsPR = {itemList[0], itemList[1]};
        User user = User.getUserById("U1");
        pr1.setUser(user); // pass user object here 
        pr1.setItems(itemsPR); // pass array of item objects 
     
        pr1.createPR();
        
        
        /* List of PR */
        PR [] prList = new PR().getPRList();
        
        for (PR pr : prList){
            String [] prInfo = pr.getCurrentPR();
            System.out.println(Arrays.toString(prInfo));
            
            String [] userInfo = pr.getUser().getCurrentUserInfo();
            System.out.println("User: " +Arrays.toString(userInfo));
            
            Item [] PRItems = pr.getItems();
            for (Item PRItem : PRItems){
                System.out.println(Arrays.toString(PRItem.getItemInfo()) + " "+ PRItem.getReorderAmt());
            }
            System.out.println("\n");
        }
        
        /* Approve or Reject PR */
        try{
            PR pr = new PR().getPRList()[2];
            pr.updatePRStatus("approved"); //approved or rejected
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
 
        
        
        
        /* List of suppliers (View) including items */
        Supplier [] suppliers = Supplier.getActiveSupplierList();
        for (Supplier supp : suppliers){
            String [] supplierInfo = supp.getCurrentSupplier();
            System.out.println(supplierInfo[0] + ", " + supplierInfo[1] + ", " +supplierInfo[2] + ", " + supplierInfo[3]);
            
            Item [] itemsOfSupplier = supp.getCurrentSupplierItems();
            for (Item itemSupp : itemsOfSupplier){
                if (itemSupp != null){
                    String [] itemInfo = itemSupp.getItemInfo();
                    System.out.println(itemInfo[0] + ", " + itemInfo[1]+ ", " +itemInfo[2]+ ", " +itemInfo[3] + ", " + itemInfo[4]);
                }
                
            }
            System.out.println("\n");
        }
        
        /* Add new supplier (must add new item for new supplier) */
        Supplier newSupplier = new Supplier("Fast Sdn Bhd", "fast@sdn.com");
        Item [] newItems = new Item [2];
        newItems[0] = new Item("carpet", 30, 20);
        newItems[1] = new Item("whiteboard", 35, 18);
        newSupplier.setItems(newItems);
        newSupplier.addSupplier();
        
        /* edit supplier */
        try{
            suppliers[3].editSupplier("Boost House Supp", "boosthousesupp@g.com");
        } catch (Exception e) {
            System.out.println(e);
        }
         
 
        /* Delete supplier */
        try{
            suppliers[1].deleteSupplier();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        /* Get Payment History of Supplier*/
        Supplier supp = suppliers[2];
        PO [] paymentHistory = supp.getSupplierPaymentHistory();
        
        System.out.println(Arrays.toString(supp.getCurrentSupplier()));
        String supplierid = supp.getSupplierId();
        for (PO po : paymentHistory){
            if (po == null){
                continue;
            }
            System.out.println(po.getTimestamp());
            System.out.print(po.getPoId() + " -> ");
            Item [] poItems = po.getPOItems();
            for (Item poItem : poItems){
                if (poItem == null){
                    break;
                }
                String poSupplierId = poItem.getSupplier().getSupplierId();
                if (poSupplierId.equals(supplierid)){
                    System.out.print(poItem.getItemId() + ", ");
                }
            }
            System.out.println(po.getStatus() + "\n");
        }
        
        
        /* List of PO (View) */
        PO [] poList = new PO().getPOList();
        for (PO po : poList){
            System.out.println(po.getPoId());
        }
        
        /* Generate PO */
        // Require user to select a PR
        // Allow user to change the reorder amount after select PR
        try{
            PR [] PRList = new PR().getPRList();
            PR pr = PRList[0];
            
            Item [] prItems = pr.getItems(); // list of items in the PR
            pr.editReorderAmt(prItems[1], 13); // optional to editreorderamount
            
            User pruser = User.getUserById("U2");

            PO po = new PO();
            po.setPR(pr);
            po.setUser(pruser);
            po.createPO();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }  
        
    }
}
