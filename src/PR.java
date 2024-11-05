
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class PR extends PRItems{
    private User user;
    private String timestamp, dueDate, status;
    private final String prF = BASE_DIR + "pr.txt";
    
    /* Constructors */
    PR(){
        
    }
    
    PR(String dueDate){
        this.dueDate = dueDate;
    }
    
    
    private PR(String prId, String timestamp, String dueDate, String status){
        super.setPRId(prId);
        this.timestamp = timestamp;
        this.dueDate = dueDate;
        this.status = status;
    }
    
    /* Return info in an array */
    public String [] getCurrentPR(){
        String [] prInfo = {super.getPRId(), this.timestamp, this.dueDate, this.status};
        return prInfo;
    }
    
    /*Set items record*/
    public void setItems(Item [] items){
        super.setPRItems(items);        
    }
    
    /*return status*/
    public String getStatus(){
        return this.status;
    }
    
    /*return PRitems*/
    public Item [] getItems(){
        return super.getPRItems();
    }
    
    /*set user*/
    public void setUser(User user){
        this.user = user;
    }
    
    /*return user*/
    public User getUser(){
        return this.user;
    }
    
    /*create PR record*/
    public String createPR(){
        try{
            Item [] prItems = this.getItems();
            if (prItems == null || prItems.length == 0){
                throw new Exception("Empty items");
            }
            String prId = this.generateNewId();

            // Get current timestamp
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String timestamp = now.format(formatter);
            
            // Convert due date to date obj
            LocalDateTime dueDateObj = LocalDateTime.parse(this.dueDate);
            
            if (dueDateObj.isBefore(now)){
                throw new Exception("Invalid due date");
            }
            
            if (this.user == null){
                throw new Exception("Empty user");
            }
            
            String userId = this.user.getUserId(); // change to actual user

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
            super.setPRId(prId);
            super.createPrItems();
            
            return prId;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
    
    /* Edit reorder amount of an item (used in PO) */
    public void editReorderAmt(Item item, int newReorderAmt) throws Exception{

        String itemId = item.getItemId();
        
        super.editReorderAmt(itemId, newReorderAmt);
    }
    
    /* retrieve itemID to delete the entry of PRItem of itemID retrieved*/
    public void deletePRItem(Item item) throws Exception{

        String itemId = item.getItemId();
        
        super.deletePRItem(itemId);
    }
    
    /*delete an entry of PR and update PRList*/
    public void deletePR() throws Exception{
        /*retrieve current item record*/
        Item [] itemsPr = this.getPrItemsRecords();
        
        /*delete current item record*/
        for (Item item : itemsPr){
            this.deletePRItem(item);
        }
        
        /*create array of prlist without the deleted pr*/
        PR pr = new PR();
        PR [] prList = pr.getPRList();
        PR [] newPrList = new PR[prList.length-1];
        int ind = 0;
        String prId = super.getPRId();
        
        for (PR prtmp : prList){
            if (prId.equals(prtmp.getPRId())){
                continue;
            }
            newPrList[ind] = prtmp;
            ind++;
        }
        
        /*update file with new pr list*/
        this.upPRFile(newPrList);
    }
    
    /*write pr into file*/
    private void upPRFile(PR [] newPrList){
        try {
            FileWriter prFw = new FileWriter(this.prF);
            BufferedWriter prBw = new BufferedWriter(prFw);
            
            for (PR pr : newPrList){
                String [] prInfo = pr.getCurrentPR();
                // wait user
                String userId = this.user.getUserId();
                String PRRecord = prInfo[0] + "," + userId + "," + prInfo[1] + "," + prInfo[2] + "," + prInfo[3] + "\n";
                prBw.write(PRRecord);
            }
            prBw.close();
            prFw.close();
        } catch (Exception e) {
            System.out.println("An error occurred, "+e);
        } 
    }
    
    /*return PRList*/
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
                Item [] itemsPR = super.getPrItemsRecords();
                pr.setItems(itemsPR);
                
                // set user by id
                String prUserId = prInfo[1];
                pr.user = User.getUserById(prUserId);
                
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
       
    /*Generate new PR ID*/
    public String generateNewId(){
        // Get the last PR ID
        PR [] prList = this.getPRList();
        int count = 0;
        if (prList.length != 0){
            String lastPRId = prList[prList.length-1].getPRId();
            count = Integer.parseInt(lastPRId.replace("PR", ""));
        }
        count++;
        String prId = "PR"+count;
        
        return prId;
    }
    
    /*Return number of PR in file*/
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
    
    /*return PR based on ID requested*/
    public PR getPRByID(String prId) {
        try {
            FileReader prFr = new FileReader(this.prF);
            BufferedReader prBr = new BufferedReader(prFr);
            String row;

            while ((row = prBr.readLine()) != null) {
                String[] prInfo = row.split(",");
                /*check row to look for ID requested*/
                if (prInfo[0].equals(prId)) {
                    PR prByID = new PR(prInfo[0], prInfo[2], prInfo[3], prInfo[4]);
                    super.setPRId(prId);
                    Item[] itemsPR = super.getPrItemsRecords();
                    prByID.setItems(itemsPR);
                    
                    
                    String userId = prInfo[1];
                    prByID.setUser(new User().getUserById(userId));
                
                    prBr.close();
                    prFr.close();
                    return prByID;
                }
            }
            prBr.close();
            prFr.close();
            
        } catch (Exception e) {
            System.out.println("An error occurred: " + e);
            
        }
        return null;
    }
    
    /*update status of PR to approved or rejected*/
    public void updatePRStatus(String newStatus) throws Exception{
        /*check if status is approved or rejected*/
        if (!newStatus.equals("approved") && !newStatus.equals("rejected")) {
            System.out.println("Invalid status. Status should be either 'approved' or 'rejected'.");
            return;
        }
            
        StringBuffer sb = new StringBuffer();
        FileReader prFr = new FileReader(this.prF);
        BufferedReader prBr = new BufferedReader(prFr);
        String row;

        while ((row = prBr.readLine()) != null) {
            String[] prInfo = row.split(",");
            if (prInfo[0].equals(super.getPRId())) {
                prInfo[4] = newStatus;                                 
            }
            sb.append(String.join(",", prInfo)).append("\n");
        }
        prBr.close();
        prFr.close();
        FileWriter prFw = new FileWriter(this.prF);
        BufferedWriter prBw = new BufferedWriter(prFw);
        prBw.write(sb.toString());
        prBw.close();
        prFw.close();
    }
}
