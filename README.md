# OODJ Assignment G6 - POTS
## Instruction
Upload Java files in `src/` folder.  
Upload txt files in `src/storage/` folder.  
`LGoodDatePicker-11.2.1.jar` file is for the datetime picker plugin.  

## Txt file content
**user.txt**  
`col 1` : user ID (unique)  
`col 2` : user name   
`col 3` : password  
`col 4` : user role [SM, PM, IM, FM, AM]  
`col 5` : status [active, deleted]  

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

**po.txt**  
`col 1` : PO ID (unique)  
`col 2` : PR ID (foreign)   
`col 3` : user ID (foreign)  
`col 4` : created timestamp  
`col 5` : status [pending, approved, rejected, paid]  

## Credits  
- <a href="https://github.com/TeoJJss">Teo Jun Jia</a>  
- <a href="https://github.com/mysticsyf">Sim Yi Feng</a>  
- <a href="https://github.com/TakoLouie">Louis Ng Yu Hern</a>  
- <a href="https://github.com/auna1314">Au Lok Yen</a>  
- <a href="https://github.com/Maxsaw1">Saw Wen Jun</a>  