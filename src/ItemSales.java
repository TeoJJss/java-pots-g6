
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.time.LocalDate;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ItemSales extends Item {
    private String date;
    private int sales;
    private static final String SALES_FILE = BASE_DIR + "item_sales.txt";
    private static File itemSalesF = new File(SALES_FILE);
    
    /* Constructors */
    ItemSales(){
        
    }
    
    private ItemSales(int sales, String date){
        this.sales = sales;
        this.date = date;
    }
    
    /* Set sales record */
    public void setItemSales(int sales){
        this.sales = sales;
    }
    
    /* Return info in an array */
    public String [] getItemSalesInfo(){
        String itemId = super.getItemId();
        String sales = String.valueOf(this.sales);
        String [] itemSalesInfo = {itemId, sales, this.date};
        
        return itemSalesInfo;
    }
    
    /* return item object based on item id */
    public Item getItem(){
        String itemId = super.getItemId();
        Item item = super.getItemById(itemId);
        
        return item;
    }
    
    /* Sales entry */
    public void addSalesRecord() throws Exception{
        // Get current date
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String currentDate = now.format(formatter);
        
        // Prepare new record string
        StringBuffer sb = new StringBuffer();
        String itemId = super.getItemId();
        if (itemId == null){
            throw new Exception("Empty item id");
        }
        sb.append(itemId).append(",").append(this.sales).append(",").append(currentDate).append("\n");
        
        // Add to file
        FileWriter salesFw = new FileWriter(itemSalesF, true);
        BufferedWriter salesBw = new BufferedWriter(salesFw);
        salesBw.write(sb.toString());
        salesBw.close();
        salesFw.close();
    }
    
    /* Get all sales */
    public ItemSales [] getSalesRecordsList(){
        try{
            String row;
            int count = getNumberOfSalesRecords();
            ItemSales [] itemSalesRecords = new ItemSales[count];
            int ind = 0;
            
            FileReader salesFr = new FileReader(itemSalesF);
            BufferedReader salesBr = new BufferedReader(salesFr);
            while((row=salesBr.readLine())!=null){
                String [] salesInfo = row.split(",");
                String itemId = salesInfo[0];
                int sales = Integer.parseInt(salesInfo[1]);
                ItemSales itemSalesRecord = new ItemSales(sales, salesInfo[2]);
                itemSalesRecord.setItemById(itemId);
                itemSalesRecords[ind] = itemSalesRecord;
                ind++;
            }

            salesBr.close();
            salesFr.close();
            return itemSalesRecords;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
    
    /* Get all sales between two dates */
    public static ItemSales [] getSalesRecordsList(String startDateStr, String endDateStr){
        try{
            String row;
            int count =getNumberOfSalesRecords();
            ItemSales [] itemSalesRecords = new ItemSales[count];
            int ind = 0;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate startDate = LocalDate.parse(startDateStr, formatter);
            LocalDate endDate = LocalDate.parse(endDateStr, formatter);
            
            FileReader salesFr = new FileReader(itemSalesF);
            BufferedReader salesBr = new BufferedReader(salesFr);
            while((row=salesBr.readLine())!=null){
                String [] salesInfo = row.split(",");
                String itemId = salesInfo[0];
                int sales = Integer.parseInt(salesInfo[1]);
                String salesDate = salesInfo[2];
                
                // Compare dates
                LocalDate itemSalesDate = LocalDate.parse(salesDate, formatter);
                if (
                        (itemSalesDate.isAfter(startDate) && itemSalesDate.isBefore(endDate)) || // after start date and before end date, or
                        itemSalesDate.isEqual(startDate) || itemSalesDate.isEqual(endDate) // equal start date or end date
                    ){
                    ItemSales itemSalesRecord = new ItemSales(sales, salesDate);
                    itemSalesRecord.setItemById(itemId);
                    itemSalesRecords[ind] = itemSalesRecord;
                    ind++;
                }                
            }

            salesBr.close();
            salesFr.close();
            return itemSalesRecords;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
    
    /* Get sales of an item id between two dates */
    public int getSalesByItem(String startDateStr, String endDateStr){
        try{
            String row;
            
            int itemSales = 0;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate startDate = LocalDate.parse(startDateStr, formatter);
            LocalDate endDate = LocalDate.parse(endDateStr, formatter);
            
            FileReader salesFr = new FileReader(itemSalesF);
            BufferedReader salesBr = new BufferedReader(salesFr);
            while((row=salesBr.readLine())!=null){
                String [] salesInfo = row.split(",");
                String itemId = salesInfo[0];
                if (!itemId.equals(super.getItemId())){
                    continue;
                }
                int sales = Integer.parseInt(salesInfo[1]);
                String salesDate = salesInfo[2];
                
                // Compare dates
                LocalDate itemSalesDate = LocalDate.parse(salesDate, formatter);
                if (
                        (itemSalesDate.isAfter(startDate) && itemSalesDate.isBefore(endDate)) || // after start date and before end date, or
                        itemSalesDate.isEqual(startDate) || itemSalesDate.isEqual(endDate) // equal start date or end date
                    ){
                    itemSales += sales;
                }                
            }

            salesBr.close();
            salesFr.close();
            return itemSales;
        }catch (Exception e){
            System.err.println(e);
            return 0;
        }
    }
    
    private static int getNumberOfSalesRecords(){
        try{
            FileReader salesFr = new FileReader(itemSalesF);
            BufferedReader salesBr = new BufferedReader(salesFr);
            int count = 0;
            while(salesBr.readLine() != null){
                count++;
            }
            salesBr.close();
            salesFr.close();
            return count;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }
}
