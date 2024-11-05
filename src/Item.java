import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Item implements Config {
    private String itemId, itemName, status;
    private int quantity, reorderLevel, reOrderAmt;
    private Supplier supplier;
    private static final String ITEM_FILE = BASE_DIR + "item.txt";
    private static File itemF = new File(ITEM_FILE);
    
    /* Default constructor */
    Item(){
        
    }
    
    /* Consctructor for new item (without ID) */
    Item(String itemName, int quantity, int reorderLevel){
        this.itemName = itemName;
        this.quantity = quantity;
        this.reorderLevel = reorderLevel;
    }
    
    /* Constructor for private use (with ID) */
    private Item(String itemId,String itemName, int quantity, int reorderLevel, String status){
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.reorderLevel = reorderLevel;
        this.status = status;
    }
    
    /* Set Supplier of the item */
    public void setSupplier(Supplier supp){
        this.supplier = supp;
    }
    
    public String getItemId(){
        return this.itemId;
    }
    
    public String getItemName() {
        return this.itemName;
    }

    /* Set reorder amount (for PR only) */
    public void setReorderAmt(int reorderAmt){
        this.reOrderAmt = reorderAmt;
    }

    /* Get reorder amount (for PR only) */
    public int getReorderAmt(){
        return this.reOrderAmt;
    }
    public Supplier getSupplier(){
        return this.supplier;
    }
    /*Get quantity for item*/
    public int getQuantity(){
        return this.quantity;
    }

    /*Get reorder level*/
    public int getReorderLevel(){
        return this.reorderLevel;
    }
    /* Get item info of current object */
    public String [] getItemInfo(){
        String [] itemInfo = {this.itemId, this.itemName, String.valueOf(this.quantity), String.valueOf(this.reorderLevel), this.status};
        
        return itemInfo;
    }
    /* Get item info by ID */
    public static Item getItemById(String itemId){
        try{
            Item [] items = getItemList();
            if (items == null){
                throw new Exception("Empty Item list");
            }
            
            for (Item item : items){
                if(item.getItemId().equals(itemId)){
                    return item;
                }
            }
            return null;
        }catch (Exception e){
            System.out.print(e);
            return null;
        }
    }

    /* Retrieve items with quantity below reorderLevel */
    public static List <Item> getLowItemList() {
        try {
            Item[] items = getItemList();
            if (items == null || items.length == 0) {
                throw new Exception("No items available");
            }

            List<Item> lowStockItems = new ArrayList<>();
            for (Item item : items) {
                if (item.quantity < item.reorderLevel) {
                    lowStockItems.add(item);
                }
            }

            if (lowStockItems.isEmpty()) {
                System.out.println("No items below reorder level.");
                return null;
            } else {
                return lowStockItems;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    /* set item obj by ID */
    public void setItemById(String itemId) throws Exception{
        Item item = getItemById(itemId);
        if (item == null){
            throw new Exception("Empty Item");
        }
        String [] itemInfo = item.getItemInfo();
        String itemName = itemInfo[1];
        int quantity = Integer.parseInt(itemInfo[2]);
        int reorderLevel = Integer.parseInt(itemInfo[3]);
        String status = itemInfo[4];
        
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.reorderLevel = reorderLevel;
        this.status = status;
        
        this.setSupplier(item.getSupplier());
    }
    
    /* Get number of items in file */
    private static int getNumberOfItems(){
        try{
            FileReader itemFr = new FileReader(itemF);
            BufferedReader itemBr = new BufferedReader(itemFr);
            int count = 0;
            while ((itemBr.readLine()) != null){
                count++;
            }
            itemBr.close();
            itemFr.close();
            return count;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }
    
    /* Generate unique itemID */
    @Override
    public String generateNewId() {
        int count = getNumberOfItems();
        count++;
        String newItemId = "I"+count;
        
        return newItemId;
    }

    /* Get all items along with supplier information */
    public static Item [] getItemList(){
        try{            
            // Count number of items
            int count = getNumberOfItems();
            if(count < 1){
                throw new Exception("No item data");
            }
            
            Item [] itemList = new Item[count];
            
            // Read items
            FileReader itemFr = new FileReader(itemF);
            BufferedReader itemBr = new BufferedReader(itemFr);
            String row;
            int ind = 0;
            while ((row = itemBr.readLine()) != null){
                String [] itemInfo = row.split(",");
                String itemId = itemInfo[0];
                String itemName = itemInfo[1];
                int quantity = Integer.parseInt(itemInfo[2]);
                int reorderLevel = Integer.parseInt(itemInfo[3]);
                String supplierId = itemInfo[4];
                String itemStatus = itemInfo[5];
                
                String [] supplierInfo = new Supplier().getSupplierInfoById(supplierId);
                String supplierName = supplierInfo[1];
                String supplierEmail = supplierInfo[2];
                String supplierStatus = supplierInfo[3];
                Supplier itemSupplier = new Supplier();
                itemSupplier.setCurrentSupplier(supplierId, supplierName, supplierEmail, supplierStatus);

                itemList[ind] = new Item(itemId, itemName, quantity, reorderLevel, itemStatus);
                itemList[ind].supplier = itemSupplier;
                ind++;
            }
            itemBr.close();
            itemFr.close();

            return itemList;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    /* Get all items along with supplier information */
    public static Item [] getActiveItemList(){
        try{            
            // Count number of items
            int count = getNumberOfItems();
            if(count < 1){
                throw new Exception("No item data");
            }
            
            Item [] itemList = new Item[count];
            
            // Read items
            FileReader itemFr = new FileReader(itemF);
            BufferedReader itemBr = new BufferedReader(itemFr);
            String row;
            int ind = 0;
            while ((row = itemBr.readLine()) != null){
                String [] itemInfo = row.split(",");
                String itemId = itemInfo[0];
                String itemName = itemInfo[1];
                int quantity = Integer.parseInt(itemInfo[2]);
                int reorderLevel = Integer.parseInt(itemInfo[3]);
                String supplierId = itemInfo[4];
                String itemStatus = itemInfo[5];

                // Skip deleted items
                if (itemStatus.equals("deleted")){
                    continue;
                }
                
                String [] supplierInfo = new Supplier().getSupplierInfoById(supplierId);
                String supplierName = supplierInfo[1];
                String supplierEmail = supplierInfo[2];
                String supplierStatus = supplierInfo[3];
                Supplier itemSupplier = new Supplier();
                itemSupplier.setCurrentSupplier(supplierId, supplierName, supplierEmail, supplierStatus);

                itemList[ind] = new Item(itemId, itemName, quantity, reorderLevel, itemStatus);
                itemList[ind].supplier = itemSupplier;
                ind++;
            }
            itemBr.close();
            itemFr.close();

            return itemList;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /* Get item list of a supplier */
    public Item[] getItemList(String supplierId) {
        try {
            // Count number of items
            int count = getNumberOfItems();
            if (count < 1) {
                throw new Exception("No item data");
            }

            Item[] itemList = new Item[count];

            // Read items and enter items into array of objects
            FileReader itemFr = new FileReader(itemF);
            BufferedReader itemBr = new BufferedReader(itemFr);
            String row;
            int ind = 0;
            while ((row = itemBr.readLine()) != null) {
                String[] itemInfo = row.split(",");

                // Skip the item if not the target
                if (!(itemInfo[4].equals(supplierId))) {
                    continue;
                }
                String itemId = itemInfo[0];
                String itemName = itemInfo[1];
                int quantity = Integer.parseInt(itemInfo[2]);
                int reorderLevel = Integer.parseInt(itemInfo[3]);
                String itemStatus = itemInfo[5];

                String[] supplierInfo = new Supplier().getSupplierInfoById(supplierId);
                String supplierName = supplierInfo[1];
                String supplierEmail = supplierInfo[2];
                String supplierStatus = supplierInfo[3];
                Supplier itemSupplier = new Supplier();
                itemSupplier.setCurrentSupplier(supplierId, supplierName, supplierEmail, supplierStatus);

                itemList[ind] = new Item(itemId, itemName, quantity, reorderLevel, itemStatus);
                itemList[ind].supplier = itemSupplier;
                ind++;
            }
            itemBr.close();
            itemFr.close();

            return itemList;
        } catch (Exception e) {
            return null;
        }
    }

    /* Add item to existing supplier */
    public void addItem() {
        try {
            if (this.itemName == null || this.supplier == null) {
                throw new NullValException();
            }
            if (this.supplier.validateSupplier()) {
                String supplierId = this.supplier.getCurrentSupplier()[0];
                String itemId = this.generateNewId();

                StringBuffer sb = new StringBuffer(itemId);
                sb.append(",").append(this.itemName);
                sb.append(",").append(this.quantity);
                sb.append(",").append(this.reorderLevel);
                sb.append(",").append(supplierId);
                sb.append(",").append("active");

                FileWriter itemFw = new FileWriter(itemF, true);
                BufferedWriter itemBw = new BufferedWriter(itemFw);
                itemBw.write(sb.append("\n").toString());
                itemBw.close();
                itemFw.close();
            } else {
                throw new Exception("Invalid supplier");
            }

        } catch (Exception e) {
            System.out.print(e);
        }
    }

    /* Edit item details */
    public void editItem(String newItemName, Integer newQuantity, Integer newReorderLevel) {
        try {
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(itemF));
            String line;
            boolean itemFound = false;
            
            if (this.itemId == null){
                reader.close();
                throw new NullValException();
            }

            while ((line = reader.readLine()) != null) {
                String[] itemInfo = line.split(",");

                // Check if the current line has the itemId we want to edit
                if (itemInfo[0].equals(this.itemId)) {
                    itemFound = true;

                    // Update item fields only if new values are provided
                    if (newItemName != null && !newItemName.isEmpty()) {
                        itemInfo[1] = newItemName;
                    }
                    if (newQuantity != null) {
                        itemInfo[2] = String.valueOf(newQuantity);
                    }
                    if (newReorderLevel != null) {
                        itemInfo[3] = String.valueOf(newReorderLevel);
                    }

                    // Recreate the modified line with updated item details
                    line = String.join(",", itemInfo);
                }
                lines.add(line);
            }
            reader.close();

            if (!itemFound) {
               throw new Exception("Invalid item ID");
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(itemF));
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
            writer.close();
            System.out.println("Item with ID " + this.itemId + " updated successfully.");

        } catch (Exception e) {
            System.out.println("Error editing item: " + e.getMessage());
        }
    }

    /* Change item status to deleted */
    public void deleteItem() {
        try {
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(itemF));
            String line;
            boolean itemFound = false;

            while ((line = reader.readLine()) != null) {
                String[] itemInfo = line.split(",");

                // Check if the itemId we want to delete is valid
                if (itemInfo[0].equals(itemId)) {
                    itemFound = true;

                    // Update the status to "deleted"
                    itemInfo[5] = "deleted";
                    line = String.join(",", itemInfo); // recreate the modified line
                }
                lines.add(line);
            }
            reader.close();

            if (!itemFound) {
                System.out.println("Item with ID " + itemId + " not found.");
                return;
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(itemF));
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
            writer.close();
            System.out.println("Item with ID " + itemId + " marked as deleted.");

        } catch (IOException e) {
            System.out.println("Error deleting item: " + e.getMessage());
        }

    }

    /* Update Item List */
    private void updateItems() {
        try {
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(itemF));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] itemInfo = line.split(",");

                // Check if the current line has the itemId we want to update
                if (itemInfo[0].equals(this.itemId)) {
                    itemInfo[2] = String.valueOf(this.quantity); // Update the quantity
                    line = String.join(",", itemInfo); // Recreate the modified line
                }
                lines.add(line);
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(itemF));
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
            writer.close();
            System.out.println("File updated successfully.");

        } catch (IOException e) {
            System.out.println("Error updating item file: " + e.getMessage());
        }
    }

    /* Method to submit item sales and decrease quantity */
    public void submitItemSales(int sales) throws Exception {
        
        if (this.itemId == null){
            throw new NullValException();
        }
        if (sales < 1) {
            throw new Exception("Sales must be >= 1");
        }
        
        if (sales > quantity) {
            throw new Exception("Insufficient stock to complete sale.");
        }
        this.quantity -= sales;
        System.out.println("Sales submitted. Updated quantity: " + this.quantity);

        // Update the file
        updateItems();
        
        // Add item sales record
        ItemSales itemsales = new ItemSales();
        itemsales.setItemById(this.itemId);
        itemsales.setItemSales(sales);
        itemsales.addSalesRecord();
    }

    // Method to submit new stock and increase quantity
    public void submitNewStock(int addAmt) throws Exception {
        if (addAmt < 1) {
            throw new Exception("Sales must be >= 1");
        }
        this.quantity += addAmt;
        System.out.println("Stock added. Updated quantity: " + this.quantity);

        // Update the file
        updateItems();
    }

}
