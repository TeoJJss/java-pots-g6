
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class PR extends PRItems{
    private Item [] items;
//    private User user;
    private String prId, timestamp, dueDate, status;
    private String prF = BASE_DIR + "pr.txt";
    
    PR(){
        
    }
    
    PR(String dueDate){
        this.dueDate = dueDate;
    }
    
    private PR(String prId, String timestamp, String dueDate, String status){
        this.prId = prId;
        this.timestamp = timestamp;
        this.dueDate = dueDate;
        this.status = status;
    }
    
    public String [] getCurrentPR(){
        String [] prInfo = {this.prId, this.timestamp, this.dueDate, this.status};
        return prInfo;
    }
    
    public void setItems(Item [] items){
        this.items = items;
    }
    
    public Item [] getItems(){
        return this.items;
    }
    
    public void setUser(){
        // Create User obj after ready
    }
    
    public String createPR(){
        try{
            if (this.items == null || this.items.length == 0){
                throw new Exception("Empty items");
            }
            String prId = this.generateNewId();

            // Get current timestamp
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String timestamp = now.format(formatter);

            String userId = "U1"; // change to actual user

            StringBuffer sb = new StringBuffer(prId);
            sb.append(",").append(userId);
            sb.append(",").append(timestamp);
            sb.append(",").append(this.dueDate);
            sb.append(",").append("pending\n");

            // Write to file
            FileWriter prFw = new FileWriter(this.prF, true);
            BufferedWriter prBw = new BufferedWriter(prFw);
            prBw.write(sb.toString());
            prBw.close();
            prFw.close();

            // Update PRItems
            super.setPRItems(prId, items);
            super.createPrItems();
            
            return prId;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
    
    public void editReorderAmt(Item item, int newReorderAmt) throws Exception{
        super.setPRId(this.prId);
        String itemId = item.getItemInfo()[0];
        
        super.editReorderAmt(itemId, newReorderAmt);
    }
    
    public void deletePRItem(Item item) throws Exception{
        super.setPRId(this.prId);
        String itemId = item.getItemInfo()[0];
        
        super.deletePRItem(itemId);
    }
    
    public void deletePR() throws Exception{
        Item [] itemsPr = this.getPrItems();
        
        for (Item item : itemsPr){
            this.deletePRItem(item);
        }
        
        PR pr = new PR();
        PR [] prList = pr.getPRList();
        PR [] newPrList = new PR[prList.length-1];
        int ind = 0;
        
        for (PR prtmp : prList){
            if (this.prId.equals(prtmp.getCurrentPR()[0])){
                continue;
            }
            newPrList[ind] = prtmp;
            ind++;
        }
        this.upPRFile(newPrList);
    }
    
    private void upPRFile(PR [] newPrList){
        try {
            FileWriter prFw = new FileWriter(this.prF);
            BufferedWriter prBw = new BufferedWriter(prFw);
            
            for (PR pr : newPrList){
                String [] prInfo = pr.getCurrentPR();
                // wait user
            }
            prBw.close();
            prFw.close();
        } catch (Exception e) {
            System.out.println("An error occurred, "+e);
        } 
    }
    
    public PR [] getPRList(){
        int count = this.getNumberOfPr();
        PR [] prList = new PR[count];
        
        try{
            FileReader prFr = new FileReader(this.prF);
            BufferedReader prBr = new BufferedReader(prFr);
            String row;
            int ind = 0;
            while ((row=prBr.readLine()) != null){
                String [] prInfo = row.split(",");
                String prId = prInfo[0];
                PR pr = new PR(prId, prInfo[2], prInfo[3], prInfo[4]);
                super.setPRId(prId);
                Item [] itemsPR = super.getPrItems();
                pr.setItems(itemsPR);
                
                // set user
                
                prList[ind] = pr;
                
                ind ++;
            }
            prBr.close();
            prFr.close();
            return prList;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
    
    public String generateNewId(){
        int count = this.getNumberOfPr();
        count++;
        String prId = "PR"+count;
        
        return prId;
    }
    
    public int getNumberOfPr(){
        try{
            FileReader prFr = new FileReader(this.prF);
            BufferedReader prBr = new BufferedReader(prFr);
            int count = 0;
            String row;
            while ((row=prBr.readLine()) != null){
                count++;
            }
            prBr.close();
            prFr.close();
            
            return count;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }
}
