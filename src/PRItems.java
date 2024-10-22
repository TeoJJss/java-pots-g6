import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

abstract class PRItems implements Config{
    private String prId;
    private Item [] items;
    private String prItemF = BASE_DIR + "pr_items.txt";
    
    public void setPRItems(String prId, Item [] items){
        this.prId = prId;
        this.items = items;
    }
    
    public void setPRId(String prId){
        this.prId = prId;
    }
    
    /* Get PR_Items count by current PR ID */
    private int getNumberOfPrItems(){
        try{
            FileReader prItemFr = new FileReader(this.prItemF);
            BufferedReader prItemBr = new BufferedReader(prItemFr);
            int count = 0;
            String row;
            while ((row=prItemBr.readLine()) != null){
                String prId = row.split(",")[0];
                if (prId.equals(this.prId)){
                    count++;
                }
            }
            prItemBr.close();
            prItemFr.close();
            
            return count;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }
    
    /* Get PR_Items by current PR ID */
    public Item [] getPrItems(){
        int count = this.getNumberOfPrItems();
        Item [] prItems = new Item[count];
        int ind = 0;
        
        try{
            FileReader prItemFr = new FileReader(this.prItemF);
            BufferedReader prItemBr = new BufferedReader(prItemFr);
            String row;
            while ((row=prItemBr.readLine()) != null){
                String [] prItemInfo = row.split(",");
                String prId = prItemInfo[0];
                
                if (!prId.equals(this.prId)){
                    continue;
                }
                
                String itemId = prItemInfo[1];
                int reorderAmt = Integer.parseInt(prItemInfo[2]);
                
                // Get Item by Id (also set reorderAmt), after that append to array
                Item item = new Item().getItemById(itemId);
                item.setReorderAmt(reorderAmt);
                prItems[ind] = item;
                
                ind++;
            }
            prItemBr.close();
            prItemFr.close();
            
            return prItems;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
    
    public void createPrItems() throws Exception{
        StringBuffer sb = new StringBuffer();
        for (Item item : this.items){
            String[]itemInfo = item.getItemInfo();
            String itemId = itemInfo[0];
            
            // Get reorder amount provided by user
            int reorderAmt = item.getReorderAmt();
            
            // if no reorder amount is set, attempt to use reorderLevel - stock
            if (reorderAmt == 0){
                reorderAmt = Integer.parseInt(itemInfo[3]) - Integer.parseInt(itemInfo[2]);
                if (reorderAmt < 1){
                    throw new Exception("Invalid reorder amount");
                }
            }
            sb.append(this.prId).append(",").append(itemId).append(",").append(reorderAmt).append("\n");
        }
        
        // Write to file
        FileWriter prFw = new FileWriter(this.prItemF, true);
        BufferedWriter prBw = new BufferedWriter(prFw);
        prBw.write(sb.toString());
        prBw.close();
        prFw.close();
    }
    
    public void editReorderAmt(String itemId, int newReorderAmt) throws Exception{
        StringBuffer sb = new StringBuffer();
        FileReader prItemFr = new FileReader(this.prItemF);
        BufferedReader prItemBr = new BufferedReader(prItemFr);
        String row;
        while ((row=prItemBr.readLine()) != null){
            String [] prItemInfo = row.split(",");
            
            if (prItemInfo[0].equals(this.prId) && prItemInfo[1].equals(itemId)){
                prItemInfo[2] = String.valueOf(newReorderAmt);
            }
            sb.append(String.join(",", prItemInfo)).append("\n");
        }
        prItemBr.close();
        prItemFr.close();
        
        // Write to file
        FileWriter prFw = new FileWriter(this.prItemF);
        BufferedWriter prBw = new BufferedWriter(prFw);
        prBw.write(sb.toString());
        prBw.close();
        prFw.close();
    }
    
    public void deletePRItem(String itemId) throws Exception{
        StringBuffer sb = new StringBuffer();
        FileReader prItemFr = new FileReader(this.prItemF);
        BufferedReader prItemBr = new BufferedReader(prItemFr);
        String row;
        while ((row=prItemBr.readLine()) != null){
            String [] prItemInfo = row.split(",");
            
            if (prItemInfo[0].equals(this.prId) && prItemInfo[1].equals(itemId)){
                continue;
            }
            sb.append(String.join(",", prItemInfo)).append("\n");
        }
        prItemBr.close();
        prItemFr.close();
        
        // Write to file
        FileWriter prFw = new FileWriter(this.prItemF);
        BufferedWriter prBw = new BufferedWriter(prFw);
        prBw.write(sb.toString());
        prBw.close();
        prFw.close();
    }
}