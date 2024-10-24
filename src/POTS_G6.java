
import java.util.Arrays;


public class POTS_G6 {
    
    public static void main(String args[]) throws Exception {
        /* For getting items
        Item item = new Item();
        Item [] items = item.getItemList();
        for (Item i : items){
            
            String [] itemInfo = i.getItemInfo();
            Supplier supplierItem = i.getSupplier();
            String [] supplierInfo = supplierItem.getCurrentSupplier();
            
            System.out.println(itemInfo[0] + ", " + itemInfo[1]+ ", " +itemInfo[2]+ ", " +itemInfo[3] + ", " + itemInfo[4]);
            System.out.println(supplierInfo[0] + ", " + supplierInfo[1] + ", " +supplierInfo[2] + ", " + supplierInfo[3]);
            System.out.println("\n");
            
        }
        */
        
        /* To group items by supplier
        Supplier supp = new Supplier();
        Supplier [] suppliers = supp.getActiveSupplierList();
        for (Supplier supplier : suppliers){
            String [] supplierInfo = supplier.getCurrentSupplier();
            System.out.println(supplierInfo[0] + ", " + supplierInfo[1] + ", " +supplierInfo[2] + ", " + supplierInfo[3]);
            
            Item [] itemsOfSupplier = supplier.getCurrentSupplierItems();
            for (Item item : itemsOfSupplier){
                if (item != null){
                    String [] itemInfo = item.getItemInfo();
                    System.out.println(itemInfo[0] + ", " + itemInfo[1]+ ", " +itemInfo[2]+ ", " +itemInfo[3] + ", " + itemInfo[4]);
                }
                
            }
            System.out.println("\n");
        }
        */
        
        /* Add item with new supplier
        Supplier newSupplier = new Supplier("Fast Sdn Bhd", "fast@sdn.com");
        Item [] newItems = new Item [2];
        newItems[0] = new Item("carpet", 30, 20);
        newItems[1] = new Item("whiteboard", 35, 18);
        newSupplier.setItems(newItems);
        newSupplier.addSupplier();
        */
        
        /* Add item with existing supplier
        Item item = new Item("test item", 25, 14);
        Supplier supplier = new Supplier();
        supplier.setCurrentSupplier("S2", "Living Sdn Bhd", "living@sdn.com", "active");
        item.setSupplier(supplier);
        
        item.addItem();
        */
        
        /* edit supplier
        Supplier supp = new Supplier();
        Supplier [] suppliers = supp.getSupplierList();
        
        suppliers[3].editSupplier("Boost House Supp", "boosthousesupp@g.com");
        */      
        
        /* Delete supplier
        Supplier supp = new Supplier();
        Supplier [] suppliers = supp.getSupplierList();
        suppliers[1].deleteSupplier();
        */
        
        /* Add new PR
          PR pr = new PR("31/10/2024 12:00");
          Item [] itList = new Item().getItemList();
          Item [] itemsPR = new Item[itList.length];
          int ind = 0;
          
          for(Item it : itList){
              if (it != null){
                  it.setReorderAmt(ind+10);
                  itemsPR[ind] = it;
                  ind++;
              }
          }
          
          pr.setItems(itemsPR);
          pr.createPR();
           */
        
        /* Get PR List
        PR [] prList = new PR().getPRList();
        
        for (PR pr : prList){
            String [] prInfo = pr.getCurrentPR();
            System.out.println(Arrays.toString(prInfo));
            Item [] itemsPR = pr.getItems();
            for (Item item : itemsPR){
                System.out.println(Arrays.toString(item.getItemInfo()) + " "+ item.getReorderAmt());
            }
            System.out.println("\n");
        }
        */
        
        /* Edit & Delete PR
        PR [] prList = new PR().getPRList();
        PR testPr = prList[0];
        Item [] itemsPR = testPr.getItems();
        Item testItem = itemsPR[1];
        
        testPr.editReorderAmt(testItem, 40);
        testPr.deletePRItem(testItem);
        */
           /*
          ItemSales itemsales = new ItemSales();
          ItemSales [] itemSalesList = itemsales.getSalesRecordsList("21/08/2024", "23/10/2024");
          for (ItemSales i : itemSalesList){
              if (i != null){
                System.out.println(Arrays.toString(i.getItemSalesInfo()));
                System.out.println(Arrays.toString(i.getItemInfo()));
                System.out.println(Arrays.toString(i.getSupplier().getCurrentSupplier()));
                System.out.println("\n");
              }
          }
        */
          
           /*
            ItemSales itemsales = new ItemSales();
            itemsales.setItemById("I2");
            itemsales.setItemSales(20);
            itemsales.addSalesRecord();
           */

        /* Sales Report
        ItemSales itemsales = new ItemSales();
        Item [] itemList = itemsales.getItemList();
        
        for (Item item : itemList){
            itemsales.setItemById(item.getItemId());
            System.out.println(Arrays.toString(item.getItemInfo()));
            String sales = String.valueOf(itemsales.getSalesByItemId("21/05/2024", "23/11/2024"));
            System.out.println(sales);
            System.out.println("\n");
        }
        */
    }
}
