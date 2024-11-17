
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class PO implements Config {

    private static final String PO_FILE = BASE_DIR + "po.txt";
    private String poId, timestamp, status;
    private PR pr;
    private User user; 
    
    /* Constructors*/
    PO(){
        
    }
    
    private PO(String poId, String timestamp, String status) {
        this.poId = poId;
        this.timestamp = timestamp;
        this.status = status;
    }
    
    /* Set status of PO */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /* Get PO Id */
    public String getPoId() {
        return this.poId;
    }
    
    /* Get PO created datetime */
    public String getTimestamp() {
        return this.timestamp;
    }
    
    /* Get PO status*/
    public String getStatus() {
        return this.status;
    }
    
    /* set PR to the current PO */
    public void setPR(PR pr){
        this.pr = pr;
    }
    
    /* get PR of the current PO */
    public PR getPR(){
        return pr;
    }
    
    /* Set creator of current PO */
    public void setUser(User user){
        this.user = user;
    }
    
    /* Get creator of current PO */
    public User getUser(){
        return user;
    }
    
    /* Get list of items in the PO */
    public Item [] getPOItems(){
        Item [] poItems = this.pr.getItems();
        
        return poItems;
    }
    
    /* Get list of items in the PO (for specific supplier) */
    public Item [] getPOItems(String supplierId){
        Item [] poItems = this.pr.getItems();
        Item [] supplierPoItems = new Item[poItems.length];
        int ind = 0;
        
        for(Item poItem : poItems){
            String itemSupplierId = poItem.getSupplier().getSupplierId();
            if (itemSupplierId.equals(supplierId)){
                supplierPoItems[ind] = poItem;
                ind++;
            }
        }
        
        return supplierPoItems;
    }
    
    /* Get number of records in txt file */
    private static int getNumberOfPO(){
        try{
            FileReader poFr = new FileReader(PO_FILE);
            BufferedReader poBr = new BufferedReader(poFr);
            int count = 0;
            String row;
            while ((row=poBr.readLine()) != null){
                count++;
            }
            poBr.close();
            poFr.close();
            
            return count;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }
    
    /* get list of PO */
    public static PO [] getPOList(){
        PO [] poList = null;
        
        try{
            int count = getNumberOfPO();
            poList = new PO[count];
            
            FileReader poFr = new FileReader(PO_FILE);
            BufferedReader poBr = new BufferedReader(poFr);
            String row;
            int ind = 0;
            while ((row=poBr.readLine()) != null){
                String [] poInfo = row.split(",");
                String poId = poInfo[0];
                PO po = new PO(poId, poInfo[3], poInfo[4]);
                
                // set PR in PO
                String prId = poInfo[1];
                PR poPR = new PR().getPRByID(prId);
                po.setPR(poPR);
                
                // set User in PO
                String userId = poInfo[2];
                User creator = User.getUserById(userId);
                if (creator == null){
                    throw new Exception("Invalid user for " + poId);
                }
                po.setUser(creator);
                
                poList[ind] = po;
                
                ind ++;
            }
            poBr.close();
            poFr.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return poList;
    }
    
    /* get list of pending PO */
    public static PO [] getPOList(String status){
        PO [] poList = null;
        
        try{
            int count = getNumberOfPO();
            poList = new PO[count];
            
            FileReader poFr = new FileReader(PO_FILE);
            BufferedReader poBr = new BufferedReader(poFr);
            String row;
            int ind = 0;
            while ((row=poBr.readLine()) != null){
                String [] poInfo = row.split(",");
                if (!poInfo[4].equals(status)){
                    continue;
                }
                String poId = poInfo[0];
                PO po = new PO(poId, poInfo[3], poInfo[4]);
                
                // set PR in PO
                String prId = poInfo[1];
                PR poPR = new PR().getPRByID(prId);
                po.setPR(poPR);
                
                // set User in PO
                String userId = poInfo[2];
                User creator = User.getUserById(userId);
                if (creator == null){
                    throw new Exception("Invalid user for " + poId);
                }
                po.setUser(creator);
                
                poList[ind] = po;
                
                ind ++;
            }
            poBr.close();
            poFr.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return poList;
    }
    
    /* Generate new PO Id */
    @Override
    public String generateNewId(){
        // Get the last PO ID
        PO [] poList = getPOList();
        int count = 0;
        if (poList.length != 0){
            String lastPOId = poList[poList.length-1].getPoId();
            count = Integer.parseInt(lastPOId.replace("PO", ""));
        }
        count++;
        String poId = "PO"+count;
        
        return poId;
    }
    
    /* Generate new PO for PR*/
    public void createPO() throws Exception{
        if (this.pr == null || this.pr.getPRId() == null || this.user == null || this.user.getUserId() == null){
            throw new NullValException();
        }
        
        // Ensure the PR is pending
        String prStatus = this.pr.getStatus();
        if (!prStatus.equals("pending")){
            throw new Exception("This PR is not in pending status");
        }
        
        String newPoId = this.generateNewId();
        
        // Get current timestamp
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String timestamp = now.format(formatter);
        String status = "pending";
        
        // Create a new record
        StringBuffer sb = new StringBuffer(newPoId + ",");
        sb.append(this.pr.getPRId()).append(",");
        sb.append(this.user.getUserId()).append(",");
        sb.append(timestamp).append(",").append(status).append("\n");
        
        // Append to file
        FileWriter poFw = new FileWriter(PO_FILE, true);
        BufferedWriter poBw = new BufferedWriter(poFw);
        poBw.write(sb.toString());
        poBw.close();
        poFw.close();
        
        // Change PR status to approved
        this.pr.updatePRStatus("approved");
    }

    /*retrun PO based on ID requested*/
    public static PO getPOById(String id) {
        try {
            PO [] poList = getPOList();
            for (PO po : poList){
                if (po.getPoId().equals(id)){
                    return po;
                }
            }
            
        } catch (Exception e) {
            System.err.println("An error occurred: " + e);
            
        }
        return null;
    }

    /*delete object from file*/
    public void deletePO() throws Exception{
        //get line count
        int linecount = getNumberOfPO();
        //create array to store new lines
        String[] lines = new String[linecount-1];
        int index = 0;
        //update the new line array
        try (BufferedReader reader = new BufferedReader(new FileReader(PO_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains(this.getPoId())) {
                    lines[index++] = line;
                }
            }
        }
        //Write back the new lines to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PO_FILE))) {
            for (String outputLine : lines) {
                if (outputLine != null) {  // Check to avoid writing nulls
                    writer.write(outputLine + System.lineSeparator());
                }
            }
        }

    }
    
    /* Change status of PO */
    public void updatePOStatus(String newStatus) throws Exception{
        //get line count
        int linecount = getNumberOfPO();
        
        if(!(newStatus.equals("approved")||newStatus.equals("rejected") || newStatus.equals("paid"))){
            System.err.println("Invalid status.");
            throw new Exception("Invalid status.");
        }
        
        //create array to store new lines
        String[] lines = new String[linecount];
        int index = 0;
        //update the new line array
        try (BufferedReader reader = new BufferedReader(new FileReader(PO_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (!parts[0].equals(this.getPoId())) {
                    lines[index++] = line;
                }else{
                    if (parts[4].equals("paid")){ // do not allow status update if the PO is paid
                        throw new Exception("You are not allowed to update PO with 'paid' status");
                    }
                    parts[4] = newStatus;
                    String newline = parts[0] + "," + parts[1] + "," + parts[2] + "," + parts[3] + "," + parts[4];
                    lines[index++] = newline;
                }
            }
        }
        //Write back the new lines to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PO_FILE))) {
            for (String outputLine : lines) {
                if (outputLine != null) {  // Check to avoid writing nulls
                    writer.write(outputLine + System.lineSeparator());
                }
            }
        }
        
    }
    
}
