import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Item implements Config {
    private String itemId, itemName, status;
    private int quantity, reorderLevel, reOrderAmt;
    private Supplier supplier;
    private final String ITEM_FILE = BASE_DIR + "item.txt";
    private File itemF = new File(ITEM_FILE);
    
    /* Default constructor */
    Item(){
        
    }
    
    /* Consctructor for new item (without ID) */
    Item(String itemName, int quanity, int reorderLevel){
        this.itemName = itemName;
        this.quantity = quanity;
        this.reorderLevel = reorderLevel;
    }
    
    /* Constructor for private use (with ID) */
    private Item(String itemId,String itemName, int quanity, int reorderLevel, String status){
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quanity;
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
    
    /* Get item info of current object */
    public String [] getItemInfo(){
        String [] itemInfo = {this.itemId, this.itemName, String.valueOf(this.quantity), String.valueOf(this.reorderLevel), this.status};
        
        return itemInfo;
    }
    
    /* Get item info by ID */
    public Item getItemById(String itemId){
        try{
            Item [] items = this.getItemList();
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
    
    /* set item obj by ID */
    public void setItemById(String itemId) throws Exception{
        Item item = this.getItemById(itemId);
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
    private int getNumberOfItems(){
        try{
            FileReader itemFr = new FileReader(this.itemF);
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
    public String generateNewId(){
        int count = this.getNumberOfItems();
        count++;
        String newItemId = "I"+count;
        
        return newItemId;
    }
    
    /* Get all items along with supplier information */
    public Item [] getItemList(){
        try{            
            // Count number of items
            int count = this.getNumberOfItems();
            if(count < 1){
                throw new Exception("No item data");
            }
            
            Item [] itemList = new Item[count];
            
            // Read items
            FileReader itemFr = new FileReader(this.itemF);
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
        }catch (Exception e){
            return null;
        } 
    }
    
    /* Get item list of a supplier */
    public Item [] getItemList(String supplierId){
        try{            
            // Count number of items
            int count = this.getNumberOfItems();
            if(count < 1){
                throw new Exception("No item data");
            }
            
            Item [] itemList = new Item[count];
            
            // Read items and enter items into array of objects
            FileReader itemFr = new FileReader(this.itemF);
            BufferedReader itemBr = new BufferedReader(itemFr);
            String row;
            int ind = 0;
            while ((row = itemBr.readLine()) != null){
                String [] itemInfo = row.split(",");
                
                // Skip the item if not the target
                if (!(itemInfo[4].equals(supplierId))){
                    continue;
                }
                String itemId = itemInfo[0];
                String itemName = itemInfo[1];
                int quantity = Integer.parseInt(itemInfo[2]);
                int reorderLevel = Integer.parseInt(itemInfo[3]);
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
        }catch (Exception e){
            return null;
        } 
    }
    
    /* Add item to existing supplier */
    public void addItem(){
        try{
            if(this.itemName == null || this.supplier == null){
                throw new NullValException();
            }
            if (this.supplier.validateSupplier()){
                String supplierId = this.supplier.getCurrentSupplier()[0];
                String itemId = this.generateNewId();
                
                StringBuffer sb = new StringBuffer(itemId);
                sb.append(",").append(this.itemName);
                sb.append(",").append(this.quantity);
                sb.append(",").append(this.reorderLevel);
                sb.append(",").append(supplierId);
                sb.append(",").append("active");
                
                FileWriter itemFw = new FileWriter(this.itemF, true);
                BufferedWriter itemBw = new BufferedWriter(itemFw);
                itemBw.write(sb.append("\n").toString());
                itemBw.close();
                itemFw.close();
            }else{
                throw new Exception("Invalid supplier");
            }
            
        }catch (Exception e){
            System.out.print(e);
        }
    }   
    
}
