import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Item extends Supplier {
    private String itemId, itemName, status;
    private int quantity, reorderLevel;
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
    
    /* Get item info of current object */
    public String [] getItemInfo(){
        String [] itemInfo = {this.itemId, this.itemName, String.valueOf(this.quantity), String.valueOf(this.reorderLevel), this.status};
        
        return itemInfo;
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
    private String generateNewId(){
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
                
                String [] supplierInfo = super.getSupplierInfoById(supplierId);
                String supplierName = supplierInfo[1];
                String supplierEmail = supplierInfo[2];
                String supplierStatus = supplierInfo[3];
                
                itemList[ind] = new Item(itemId, itemName, quantity, reorderLevel, itemStatus);
                itemList[ind].setCurrentSupplier(supplierId, supplierName, supplierEmail, supplierStatus);
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
                String itemStatus = itemInfo[4];
                
                String [] supplierInfo = super.getSupplierInfoById(supplierId);
                String supplierName = supplierInfo[1];
                String supplierEmail = supplierInfo[2];
                
                itemList[ind] = new Item(itemId, itemName, quantity, reorderLevel, itemStatus);
                itemList[ind].setCurrentSupplier(supplierId, supplierName, supplierEmail);
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
    public void addItem(String supplierId){
        try{
            if(this.itemName == null || supplierId == null){
                throw new NullValException();
            }
            
            if (super.validateSupplierId(supplierId)){
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
                throw new Exception("Invalid supplier ID");
            }
            
        }catch (Exception e){
            System.out.print(e);
        }
    }   
    
}
