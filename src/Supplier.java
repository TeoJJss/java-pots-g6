import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Supplier implements Config{
    private String id, name, email, status;
    private final String SUPPLIER_FILE = BASE_DIR + "supplier.txt";
    private File supplierF = new File(SUPPLIER_FILE);
    private Item [] items;
    
    /* Default constructor */
    Supplier(){
        
    }
    
    /* Constructor for new supplier entry (without ID) */
    Supplier(String name, String email){
        this.name = name;
        this.email = email;
    }
    
    /* Constructor for private use (with ID) */
    private Supplier(String id, String name, String email, String status){
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
    }
    
    /* Set new Supplier info (without ID) */
    public void setCurrentSupplier(String name, String email, String status){
        this.name = name;
        this.email = email;
        this.status = status;
    }
    
    /* Set Supplier info (with ID) */
    public void setCurrentSupplier(String id, String name, String email, String status){
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
    }
    
    /* Add items into a supplier obj */
    public void setItems(Item [] items){
        this.items = items;
    }
    
    /* Get info of current Supplier */
    public String [] getCurrentSupplier(){
        /* Elements in the array are arranged in: ID, Name, Email */
        String [] supplierInfo = {this.id, this.name, this.email, this.status};
        return supplierInfo;
    }
    
    /* Get info of current Supplier's items */
    public Item [] getCurrentSupplierItems(){
        return this.items;
    }
    
    /* Check supplier if exist */
    private Boolean validateSupplierId(String id){
        String [] supplier = this.getSupplierInfoById(id);
        return supplier != null;
    }
    
    /* Vakidate current supplier obj */
    public Boolean validateSupplier(){
        try{
            FileReader supplierFr = new FileReader(this.supplierF);
            BufferedReader supplierBr = new BufferedReader(supplierFr);
            String row;
            while ((row=supplierBr.readLine()) != null){
                String [] supplierInfo = row.split(",");
                if (supplierInfo[0].equals(this.id) && supplierInfo[1].equals(this.name) && supplierInfo[2].equals(this.email)){
                    supplierBr.close();
                    supplierFr.close();
                    if (supplierInfo[3].equals("active")){
                        return true;
                    }else{
                        return false;
                    }
                }
            }
            supplierBr.close();
            supplierFr.close();
            return false;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    /* Get number of Suppliers in file */
    private int getNumberOfSuppliers(){
        try{
            FileReader supplierFr = new FileReader(this.supplierF);
            BufferedReader supplierBr = new BufferedReader(supplierFr);
            int count = 0;
            while (supplierBr.readLine() != null){
                count++;
            }
            supplierBr.close();
            supplierFr.close();
            return count;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }
    
    /* Get number of Suppliers in file */
    private int getNumberOfActiveSuppliers(){
        try{
            FileReader supplierFr = new FileReader(this.supplierF);
            BufferedReader supplierBr = new BufferedReader(supplierFr);
            int count = 0;
            String row;
            while ((row=supplierBr.readLine()) != null){
                String supplierStatus = row.split(",")[3];
                if (supplierStatus.equals("active")){
                    count++;
                }
            }
            supplierBr.close();
            supplierFr.close();
            return count;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }
    
    /* Generate unique supplierID */
    public String generateNewId(){
        int count = this.getNumberOfSuppliers();
        count++;
        String supplierId = "S"+count;
        
        return supplierId;
    }
    
    /* Get list of suppliers */
    public Supplier [] getSupplierList(){
        try{
            
            // Get number of rows in the file
            int count = this.getNumberOfSuppliers();
            if (count < 1){
                throw new Exception("No supplier data");
            }
            
            // Create array
            Supplier [] supplierArr = new Supplier[count];
            
            // Enter data into array
            String row;
            int ind = 0;
            FileReader supplierFr = new FileReader(this.supplierF);
            BufferedReader supplierBr = new BufferedReader(supplierFr);
            while ((row=supplierBr.readLine()) != null){
                // Add data from txt to array
                String [] supplierInfo = row.split(",");
                String supplierId = supplierInfo[0];
                String supplierName = supplierInfo[1];
                String supplierEmail = supplierInfo[2];
                String supplierStatus = supplierInfo[3];
                
                supplierArr[ind] = new Supplier(supplierId, supplierName, supplierEmail, supplierStatus);
                
                // get the supplier's items
                Item [] itemList = new Item().getItemList(supplierId);
                supplierArr[ind].items = itemList;
                
                ind++;
            }
            supplierBr.close();
            supplierFr.close();
            return supplierArr;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
    
    /* Get list of active suppliers */
    public Supplier [] getActiveSupplierList(){
        try{
            
            // Get number of rows in the file
            int count = this.getNumberOfActiveSuppliers();
            if (count < 1){
                throw new Exception("No active supplier data");
            }
            
            // Create array
            Supplier [] supplierArr = new Supplier[count];
            
            // Enter data into array
            String row;
            int ind = 0;
            FileReader supplierFr = new FileReader(this.supplierF);
            BufferedReader supplierBr = new BufferedReader(supplierFr);
            while ((row=supplierBr.readLine()) != null){
                // Add data from txt to array
                String [] supplierInfo = row.split(",");
                String supplierId = supplierInfo[0];
                String supplierName = supplierInfo[1];
                String supplierEmail = supplierInfo[2];
                String supplierStatus = supplierInfo[3];
                
                if (!supplierStatus.equals("active")){
                    continue;
                }
                
                supplierArr[ind] = new Supplier(supplierId, supplierName, supplierEmail, supplierStatus);
                
                // get the supplier's items
                Item [] itemList = new Item().getItemList(supplierId);
                supplierArr[ind].items = itemList;
                
                ind++;
            }
            supplierBr.close();
            supplierFr.close();
            return supplierArr;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
    
    /* Get Supplier Info by ID */
    public String [] getSupplierInfoById(String id){
        try{
            FileReader supplierFr = new FileReader(this.supplierF);
            BufferedReader supplierBr = new BufferedReader(supplierFr);
            String row;
            String [] supplier = new String[4];
            
            while ((row=supplierBr.readLine()) != null){
                String [] supplierInfo = row.split(",");
                String supplierId = supplierInfo[0];
                String supplierName = supplierInfo[1];
                String supplierEmail = supplierInfo[2];
                String supplierStatus = supplierInfo[3];
                if (supplierId.equals(id)){
                    supplier[0] = supplierId;
                    supplier[1] = supplierName;
                    supplier[2] = supplierEmail;
                    supplier[3] = supplierStatus;
                    break;
                }
            }            
            supplierBr.close();
            supplierFr.close();
            
            // Return supplier Info if found
            if (supplier[0] != null){
                return supplier;
            }else{
                throw new Exception("Invalid supplier ID");
            }
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    
    /* Add new supplier with new items */
    public String addSupplier(){
        try{
            // Make sure Supplier object has info
            if (this.name == null || this.email == null){
               throw new NullValException();
            }

            // Validate email format
            if (!(this.email).contains("@")){
                throw new Exception("Invalid email address");
            }

            // Generate new Supplier ID
            String supplierId = this.generateNewId();
            System.out.println("Supplier ID: "+supplierId);
            if (supplierId == null){
                throw new Exception();
            }

            // Create new record in String
            String status = "active";
            StringBuffer sb = new StringBuffer(supplierId);
            sb.append(",").append(this.name);
            sb.append(",").append(this.email);
            sb.append(",").append(status);

            // Open file to append new record
            FileWriter supplierFw = new FileWriter(supplierF, true);
            BufferedWriter supplierBw = new BufferedWriter(supplierFw);
            supplierBw.write(sb.append("\n").toString());
            supplierBw.close();
            supplierFw.close();
            
            // Add items
            Supplier supp = new Supplier(supplierId, this.name, this.email, status);
            for (Item item : this.items){
                item.setSupplier(supp);
                item.addItem();
            }
            
            return supplierId;
        }catch (NullValException e){
            System.out.print(e);
            return null;
        }catch (Exception e){
            System.out.print(e);
            return null;
        }        
    }    
    
    /* Rewrite supplier file */
    private void upSupplierFile(Supplier [] newSupplierArr){
        try {
            FileWriter supplierFw = new FileWriter(SUPPLIER_FILE);
            BufferedWriter supplierBw = new BufferedWriter(supplierFw);
            
            for (Supplier supp : newSupplierArr){
                String [] supplierArr = supp.getCurrentSupplier();
                StringBuffer sb = new StringBuffer(supplierArr[0]);
                sb.append(",").append(supplierArr[1]);
                sb.append(",").append(supplierArr[2]);
                sb.append(",").append(supplierArr[3]);
                supplierBw.write(sb.append("\n").toString());
            }
            supplierBw.close();
            supplierFw.close();
        } catch (Exception e) {
            System.out.println("An error occurred, "+e);
        } 
    }
    
    /* Edit supplier */
    public void editSupplier(String newSupplierName, String newSupplierEmail) throws Exception{
        // Check if supplier ID exists
        if (!(this.validateSupplier())){
            throw new Exception("Invalid supplier ID");
        }
        
        // Validate new data
        if (!(newSupplierName.length() > 3)){
            throw new Exception("The minimum length of supplier name is 3");
        }
        if (!(newSupplierEmail).contains("@")){
            throw new Exception("Invalid email address");
        }
        
        // Get array of suppliers
        Supplier [] supplierArr = this.getSupplierList();
        Boolean isEditted = false;
        int ind = 0;
        
        // Loop to find the supplier ID and edit
        for (Supplier supp : supplierArr){
            String supplierId = supp.getCurrentSupplier()[0];
            String status = supp.getCurrentSupplier()[3];
            if (this.id.equals(supp.getCurrentSupplier()[0])){ 
                supplierArr[ind] = new Supplier(supplierId, newSupplierName, newSupplierEmail, status);
                isEditted = true;
            }
            ind ++;
        }
        
        // Write to file if edit take place
        if (isEditted){
            this.upSupplierFile(supplierArr);
        }else{
            throw new Exception("Fail to edit");
        }
    }
    
    /* Delete supplier */
    public void deleteSupplier() throws Exception{
        // Check if supplier ID exists
        if (!(this.validateSupplier())){
            throw new Exception("Invalid supplier ID");
        }
        
        // Get array of suppliers
        Supplier [] supplierArr = this.getSupplierList();
        Boolean isDeleted = false;
        int ind =0;
        
        // Loop to find the supplier ID and delete
        for (Supplier supp : supplierArr){
            if (this.id.equals(supp.getCurrentSupplier()[0])){ 
                supp.setCurrentSupplier(this.name, this.email, "deleted");
                isDeleted = true;
            }
            ind ++;
        }
        
        // Write to file if deletion take place
        if (isDeleted){
            this.upSupplierFile(supplierArr);
        }else{
            throw new Exception("Fail to delete");
        }
    }
}
