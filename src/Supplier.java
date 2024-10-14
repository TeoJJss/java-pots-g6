import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public abstract class Supplier implements Config{
    private String id, name, email;
    private final String SUPPLIER_FILE = BASE_DIR + "supplier.txt";
    private File supplierF = new File(SUPPLIER_FILE);
    
    /* Default constructor */
    Supplier(){
        
    }
    
    /* Constructor for new supplier entry (without ID) */
    Supplier(String name, String email){
        this.name = name;
        this.email = email;
    }
    
    /* Constructor for private use (with ID) */
    private Supplier(String id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }
    
    /* Set new Supplier info (without ID) */
    public void setCurrentSupplier(String name, String email){
        this.name = name;
        this.email = email;
    }
    
    /* Set Supplier info (with ID) */
    public void setCurrentSupplier(String id, String name, String email){
        
        this.id = id;
        this.name = name;
        this.email = email;
    }
    
    /* Get info of current Supplier object */
    public String [] getCurrentSupplier(){
        /* Elements in the array are arranged in: ID, Name, Email */
        String [] supplierInfo = {this.id, this.name, this.email};
        return supplierInfo;
    }
    
    /* Check supplier if exist */
    public Boolean validateSupplierId(String id){
        String [] supplier = this.getSupplierInfoById(id);
        return supplier != null;
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
    
    /* Generate unique supplierID */
    private String generateNewId(){
        int count = this.getNumberOfSuppliers();
        count++;
        String supplierId = "S"+count;
        
        return supplierId;
    }
    
    /* Get Supplier Info by ID */
    public String [] getSupplierInfoById(String id){
        try{
            FileReader supplierFr = new FileReader(this.supplierF);
            BufferedReader supplierBr = new BufferedReader(supplierFr);
            String row;
            String [] supplier = new String[3];
            
            while ((row=supplierBr.readLine()) != null){
                String [] supplierInfo = row.split(",");
                String supplierId = supplierInfo[0];
                String supplierName = supplierInfo[1];
                String supplierEmail = supplierInfo[2];
                if (supplierId.equals(id)){
                    supplier[0] = supplierId;
                    supplier[1] = supplierName;
                    supplier[2] = supplierEmail;
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
    
    /* Add new supplier */
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
            StringBuffer sb = new StringBuffer(supplierId);
            sb.append(",").append(this.name);
            sb.append(",").append(this.email);
            sb.append(",").append("active");

            // Open file to append new record
            FileWriter supplierFw = new FileWriter(supplierF, true);
            BufferedWriter supplierBw = new BufferedWriter(supplierFw);
            supplierBw.write(sb.append("\n").toString());
            supplierBw.close();
            supplierFw.close();
            
            return supplierId;
        }catch (NullValException e){
            System.out.print(e);
            return null;
        }catch (Exception e){
            System.out.print(e);
            return null;
        }        
    }
    
    /* Get multi-dimensional array of Supplier */
    public String [][] getSupplierList(){
        try{
            
            // Get number of rows in the file
            int count = this.getNumberOfSuppliers();
            if (count < 1){
                throw new Exception("No supplier data");
            }
            
            // Create a multidimensional array
            String [][] supplierArr = new String[count][4];
            
            // Enter data into array
            String row;
            int ind = 0;
            FileReader supplierFr = new FileReader(this.supplierF);
            BufferedReader supplierBr = new BufferedReader(supplierFr);
            while ((row=supplierBr.readLine()) != null){
                // Add data from txt to multidimensional array
                String [] supplierInfo = row.split(",");
                String supplierId = supplierInfo[0];
                String supplierName = supplierInfo[1];
                String supplierEmail = supplierInfo[2];
                String supplierStatus = supplierInfo[3];
                supplierArr[ind][0] = supplierId;
                supplierArr[ind][1] = supplierName;
                supplierArr[ind][2] = supplierEmail;
                supplierArr[ind][3] = supplierStatus;
                
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
    
    
    /* Rewrite supplier file */
    private void upSupplierFile(String [][] supplierArr){
        try {
            FileWriter supplierFw = new FileWriter(SUPPLIER_FILE);
            BufferedWriter supplierBw = new BufferedWriter(supplierFw);
            
            for (int i=0; i<supplierArr.length; i++){
                StringBuffer sb = new StringBuffer(supplierArr[i][0]);
                sb.append(",").append(supplierArr[i][1]);
                sb.append(",").append(supplierArr[i][2]);
                sb.append(",").append(supplierArr[i][3]);
                supplierBw.write(sb.append("\n").toString());
            }
            supplierBw.close();
            supplierFw.close();
        } catch (Exception e) {
            System.out.println("An error occurred, "+e);
        } 
    }
    
    /* Edit supplier */
    public void editSupplier(String supplierId, String supplierName, String supplierEmail) throws Exception{
        // Check if supplier ID exists
        if (!(this.validateSupplierId(supplierId))){
            throw new Exception("Invalid supplier ID");
        }
        
        // Validate email format
        if (!(supplierEmail).contains("@")){
            throw new Exception("Invalid email address");
        }
        
        // Get array of suppliers
        String [][] currentSupplierList = this.getSupplierList();
        Boolean isEditted = false;
        
        // Loop to find the supplier ID and edit
        for (int ind=0; ind<currentSupplierList.length; ind++){
            if ((currentSupplierList[ind][0]).equals(supplierId)){
                currentSupplierList[ind][1] = supplierName;
                currentSupplierList[ind][2] = supplierEmail;
                isEditted = true;
                break;
            }
        }
        
        // Write to file if edit take place
        if (isEditted){
            this.upSupplierFile(currentSupplierList);
        }else{
            throw new Exception("Fail to edit");
        }
    }
    
    /* Delete supplier */
    public void deleteSupplier(String supplierId) throws Exception{
        // Check if supplier ID exists
        if (!(this.validateSupplierId(supplierId))){
            throw new Exception("Invalid supplier ID");
        }
        
        // Get array of suppliers
        String [][] currentSupplierList = this.getSupplierList();
        Boolean isDeleted = false;
        
        // Loop to find the supplier ID and delete
        for (int ind=0; ind<currentSupplierList.length; ind++){
            if ((currentSupplierList[ind][0]).equals(supplierId)){
                currentSupplierList[ind][3] = "deleted";
                isDeleted = true;
                break;
            }
        }
        
        // Write to file if deletion take place
        if (isDeleted){
            this.upSupplierFile(currentSupplierList);
        }else{
            throw new Exception("Fail to delete");
        }
    }
}
