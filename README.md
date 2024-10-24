# OODJ G6 - POTS
## Instruction
Upload Java files in `src/` folder.  
Upload txt files in `src/storage/` folder.  

## Txt file content
**supplier.txt**  
`col 1` : supplier ID (unique)  
`col 2` : supplier name   
`col 3` : supplier email  
`col 4` : supplier status [active, deleted]  

**item.txt**  
`col 1` : item ID (unique)  
`col 2` : item name   
`col 3` : current stock  
`col 4` : reorder level    
`col 5` : supplier ID (foreign)    
`col 6` : item status [active, deleted]  

**item_sales.txt**  
`col 1` : item ID (foreign)  
`col 2` : sales   
`col 3` : sales date  

**pr.txt**  
`col 1` : PR ID (unique)  
`col 2` : user ID (foreign)   
`col 3` : created timestamp  
`col 4` : due date    
`col 5` : status [pending, approved, rejected]  

**pr_items.txt**  
`col 1` : PR ID (foreign)  
`col 2` : item ID (foreign)   
`col 3` : reorder amount   