package com.ProvisioningPageObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.BasicConfigurationInventory.InventoryTransactions;
import com.BasicConfigurationPageObject.Role;
import com.BasicConfigurationsOfServiceOrder.BasicConfigurationsOfServiceOrder;
import com.InventoryAndLogisticsPageObject.OneTimeSaleOrder;
import com.InventoryAndLogisticsPageObject.OwnedHardware;
import com.JobsPageObject.ScheduleJob;
import com.MulticonnectionPageObject.Multiconnection;
import com.OrderManagementPageObject.CustomerRegistration;
import com.OrderManagementPageObject.HardwareAssociation;
import com.OrderManagementPageObject.ServiceOrder;
import com.PageObjects.OrderManagementMasters.PricingMaster.PricePlan;

import TestFrameWork.MQProvisioning;
import Utility.Navigate;
import Utility.WaitTool;
import mq.seleniumFW.connection.MQDBConnection;


public class BasicConfigurationsOfProvisioning 
{

	private WebDriver driver;
	public Navigate oNavigate;
	WaitTool oWaitTool;
	CustomerRegistration oCustomerRegistration;
	ServiceOrder oserviceorder;
	ProvisioningRequests oProvisioningRequests;
	ProvisioningSetUp oProvisioningSetUp;
	ScheduleJob oScheduleJob;
	OwnedHardware oOwnedHardware;
	PricePlan oPricePlan;
	HardwareAssociation oHardwareAssociation;
	public OneTimeSaleOrder OTS;
	public InventoryTransactions invTrans;

	
	public List<WebElement> listOfRows;
	public static  By CustomerRegistrationFirstName    =   By.xpath(".//*[@id='ctl00_uxPgCPH_firstname']");
	public static  By CustomerRegistrationLastName     =   By.xpath(".//*[@id='ctl00_uxPgCPH_lastname']");
	public static  By CustomerRegistrationScreenOutPut  =   By.xpath(".//*[@id='ctl00__infoSpan']");
	public static  By CustomerRegistrationSaveBtn =   By.xpath(".//*[@id='ctl00_uxPgCPH_btnsave']");
	public By PercentageOfTDS = By.xpath(".//*[@id='ctl00_uxPgCPH_custflexattr__flexattr_TDSPERCENT']");
	public By EffectDateFromTDS = By.xpath(".//*[@id='ctl00_uxPgCPH_custflexattr__flexattr_TDSEFFECTFROM']");
	public By loctxtCustNum=By.id("ctl00_uxPgCPH_uxSearchCtrl_txtCustnbr");
	public By locSaveBtn=By.id("ctl00_uxPgCPH_btnsave");
	public By locMsg=By.id("ctl00__infoSpan");
	public By locapprove=By.id("ctl00_uxPgCPH_chkapprove");
	public By locPlanSearchTab = By.id("ctl00_uxPgCPH_txtplnsrch");
	public By locAddToCart = By.xpath(".//*[@id='ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd']");
	public By locsuccessmsg=By.xpath(".//*[@id='ctl00__infoSpan']");
	public By locNoOfConnections=By.id("ctl00_uxPgCPH_txtNoofrooms");
//	public By locNoofconnections=By.id("ctl00_uxPgCPH_txtNoofrooms");
	

	
    String CustomerNumber,first_Name="Ravi",last_Name="Kannegundla";
    String mrnNumber;
    public String Inventory_Location="Head Location",Supplier="Conax Supplier",currentDate="29-04-2019";
   
    String PartyClass="Customer", Entity="CORP",Location="Head Location",Succ_Msg;
    String billing_Frequency = "One Month";
	
    
    public ArrayList<Hashtable<String, String>> records;
    public Hashtable<String,String> record;
   
    String Item1="Smart Card",Item2="Set Top Box";
    public     String ScreenValidation1,Sent="Sent",Accept="Accept",New="New";
    
    BasicConfigurationsOfServiceOrder oBasicConfigurationsOfServiceOrder;
    Multiconnection oMulticonnection;
	private MQDBConnection oDBConnection;
    
	
        
    
    public BasicConfigurationsOfProvisioning(WebDriver driver,MQDBConnection dbConnection)
    {
    	
    	this.driver=driver;
       	this.oDBConnection=dbConnection;
       	this.role=new Role(dbConnection);
        this.oNavigate=new Navigate(driver,oDBConnection); 
        PageFactory.initElements(driver, this);
        this.oCustomerRegistration = new CustomerRegistration(driver);
        this.oserviceorder=new ServiceOrder(driver,oDBConnection);
        this.oProvisioningRequests =new ProvisioningRequests(driver);
        this.oProvisioningSetUp =new ProvisioningSetUp(driver);
        this.oScheduleJob = new ScheduleJob(driver);
        this.invTrans=new InventoryTransactions(driver, dbConnection);
        this.oBasicConfigurationsOfServiceOrder=new BasicConfigurationsOfServiceOrder(driver, oDBConnection);
        this.oOwnedHardware=new OwnedHardware(driver, oDBConnection);
        this.OTS=new OneTimeSaleOrder (driver, oDBConnection);
        this.oHardwareAssociation=new HardwareAssociation(driver,oDBConnection);
        this.oMulticonnection=new  Multiconnection(driver);
       	
       }

    
    
    Role role;
    public String addPriv,modifyPriv,viewPriv,approvePriv,cancelPriv,autoApprovePriv;

    public void OwnHardware_Privileges()
	{
		//Setting the Privileges for the Material Receipt Note screen
		System.out.println("OwnHardware_Privileges");
		addPriv=role.Role_Privilege("A","Add Customer Hardware");
		viewPriv=role.Role_Privilege("A","View Customer Hardware");
		System.out.println("Add privilege Status is: "+addPriv+" \n  View privilege Status is: "+viewPriv+"\n ");
	}
    
    
    
    
    
    
    
  //To generate Random numbers      ABCDEFGHIJKLMNOPQRSTUVWXYZ
    private static final String ALPHA_NUMERIC_STRING = "0123456789";
//    private static final String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyz";
    public static String randomAlphaNumeric(int count) {
    StringBuilder builder = new StringBuilder();
    while (count-- != 0) {
    int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
    builder.append(ALPHA_NUMERIC_STRING.charAt(character));
    }
    System.out.println("value is:"+builder.toString());
    return builder.toString();
    }

// provisiong request screen calling    
    
    public void ProvisioningRequestsCalling() throws InterruptedException
    {
    	
    	Thread.sleep(3000);
    	oNavigate.toProvisioningRequest();
    	driver.navigate().refresh();
    	Thread.sleep(5000);
    	oProvisioningRequests.ProvisioningRequestsScreen("","","","","","",New,Sent,"","Search","Save");
    	String successMsg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
    	System.out.println("Success message is:"+successMsg1 );
    	Thread.sleep(2000);
    	driver.navigate().refresh();
    	oProvisioningRequests.ProvisioningRequestsScreen("","","","","","",Sent,Accept,"","Search","Save");
    	
    }
    
    
    
    // Activation of a customer with sale or own hardware's   
    
    public String CustomerActvation(String Multiple_Hardware,String Sale_Own,String sOrderDate,String sEffectiveDate,String sContractValidity,String strPlanCode,String auto_ApproveOrCancel,String sBillingFrequency) throws Exception
    {
    	    	
    	  System.out.println("entered Into method for Customer Actvation");
		  Thread.sleep(8000);
		  oNavigate.toCustomerRegistration();
	      Thread.sleep(8000);
	      System.out.println("Entered In to CustomerRegistration ");
	      Thread.sleep(5000);
	      CustomerNumber = oCustomerRegistration.NewCustomerRegistration(first_Name,last_Name,"","");
	      System.out.println("CustomerRegistration COMPLETED");
	      System.out.println("Customer number is : "+CustomerNumber); 
	      Thread.sleep(3000);
	
	      
	      // for own hardware if multiple hardware as N means one SC and STB  AND for multiple hardware Y means 2 SC's and 2 STB's
	      if(Sale_Own=="Own")
	      {
	    	  System.out.println("Entered in to Own Hardware");
	    	  if(Multiple_Hardware=="N")
	    	  {
	    		  System.out.println("Entered in to Own Hardware of Single Hardware ");
	    		  Thread.sleep(5000);
	    		  oNavigate.toOwnedHardware();
	    	
	    		  String STB = "100"+randomAlphaNumeric(4); 
	    		  String SC = "100"+randomAlphaNumeric(4);
	    		  
//	    		  String STB = "ab:cd:ef:gh:"+randomAlphaNumeric(2)+":"+randomAlphaNumeric(2); 
//	    		  String SC = "ab:cd:ef:gh:"+randomAlphaNumeric(2)+":"+randomAlphaNumeric(2);
	    		  
	    		  Thread.sleep(2000);
	    		  oOwnedHardware.Owned_Hardware_Add("",Item2,STB,"","","",1);
	    		  oOwnedHardware.Owned_Hardware_Add("",Item1,SC,"","","",2);
	    		  Thread.sleep(2000);
	    		  System.out.println("Giveing owned hardware with 1 sc and 1 stb");
	    		  Thread.sleep(3000);
	    		  driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
	    		  Thread.sleep(3000);
	    		  System.out.println("Given owned hardware with 1 sc and 1 stb");
	    	  }
	    	  else
	    	  {
	    		  oNavigate.toOwnedHardware();
	    		  String STB_serialnum1 = "PROVSC"+randomAlphaNumeric(8);
	    		  String SC_serialnum1 = "PROVSC"+randomAlphaNumeric(8);
	    		  String STB_serialnum2 = "PROVSC"+randomAlphaNumeric(8);
	    		  String SC_serialnum2 = "PROVSC"+randomAlphaNumeric(8);

	    		  oOwnedHardware.Owned_Hardware_Add("",Item2,STB_serialnum1,"","","",1);
	    		  oOwnedHardware.Owned_Hardware_Add("",Item1,SC_serialnum1,"","","",2);
	    		  oOwnedHardware.Owned_Hardware_Add("",Item2,STB_serialnum2,"","","",3);
	    		  oOwnedHardware.Owned_Hardware_Add("",Item1,SC_serialnum2,"","","",4);

	    		  driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
	    		  Thread.sleep(3000);
	    		  System.out.println("Given owned hardware with 2 sc and 2 stb");
	    	  }
	      }
		  
		  // for Sale hardware
	      else if (Sale_Own=="Sale")

	      {
	    	  System.out.println("Entered in to Sale hardware");

	    	  if(Multiple_Hardware=="N")
	    	  {
	    		  System.out.println("entered in to Sale hardware with Multiple Hardware As N");
	    		  Thread.sleep(5000);
//	    		  mrnNumber=invTrans.mrnTrans("","1",Inventory_Location,Supplier,currentDate,"N",1,"Y","N","Y");
	    		  mrnNumber ="2844";
	    		  Thread.sleep(5000);
	    		  oNavigate.toOneTimeSale();		            
	    		  driver.findElement(loctxtCustNum).clear();		      
	    		  Thread.sleep(3000);	        

	    		  OTS.OneTimeSale_Cust_Header(PartyClass,CustomerNumber,"",Entity,Location,"","NEW");	        
	    		  OTS.OneTimeSale_Cust_Item_dtls(Item1,"1","1","","",1,mrnNumber);	         
	    		  Thread.sleep(2000);	         
	    		  OTS.OneTimeSale_Cust_Item_dtls(Item2,"1","1","","",2,mrnNumber);

	    		  Thread.sleep(2000);	         
	    		  driver.findElement(locSaveBtn).click();	        		       
	    		  Thread.sleep(3000);			 
	    		  Succ_Msg=driver.findElement(locMsg).getText();
	    		  System.out.println("Success message is" + Succ_Msg);
	    		  System.out.println("Given Sale hardware with 1 sc and 1 stb");
	    	  } 	

	    	  else
	    	  {

	    		  mrnNumber=invTrans.mrnTrans("","2",Inventory_Location,Supplier,currentDate,"N",1,"Y","N","Y"); 		      
	    		  String sQuery="select CUSTOMER_NBR from customer_tbl order by customer_id desc";
	    		  this.records=oDBConnection.fecthRecords(sQuery);
	    		  this.record=this.records.get(0);
	    		  String CustomerNumber=record.get("CUSTOMER_NBR");
	    		  System.out.println("The Customer Number is"+CustomerNumber);	
	    		  oNavigate.toOneTimeSale();		            
	    		  driver.findElement(loctxtCustNum).clear();		      
	    		  Thread.sleep(3000);	        
	    		  OTS.OneTimeSale_Cust_Header(PartyClass,CustomerNumber,"",Entity,Location,"","NEW");	        
	    		  OTS.OneTimeSale_Cust_Item_dtls(Item1,"2","2","","",1,mrnNumber);	         
	    		  Thread.sleep(2000);	         
	    		  OTS.OneTimeSale_Cust_Item_dtls(Item2,"2","2","","",2,mrnNumber);	                 
	    		  Thread.sleep(2000);	         
	    		  driver.findElement(locSaveBtn).click();	        		       
	    		  Thread.sleep(3000);			 
	    		  Succ_Msg=driver.findElement(locMsg).getText();
	    		  System.out.println("Success message is" + Succ_Msg);

	    		  System.out.println("Given owned hardware with 2 sc and 2 stb");

	    	  }

	      }
		  
        
         System.out.println("Hardware completed");
         oNavigate.toServiceOrder();
    	 System.out.println("Entered in to service order");
  	     String sResult = oserviceorder.BookPlan(CustomerNumber,sOrderDate,sEffectiveDate,sContractValidity,strPlanCode,auto_ApproveOrCancel,billing_Frequency);
  	     System.out.println("is:"+sResult);
  	  	 Thread.sleep(5000);
		 
  	  	 return CustomerNumber;
  	  

    
    }
    
    

    
    // This method is to Create a customer with Multi-Connection Customer Check box as Y and Activation of a customer with sale or own hardware's  
    public String CustomerActvationMultiConnections(String Multiple_Hardware,String Sale_Own,String sOrderDate,String sEffectiveDate,String sContractValidity,String strPlanCode,String auto_ApproveOrCancel,String sBillingFrequency,String sNoofConnections) throws Exception
    {
    	    	
    
    	  System.out.println("entered Into method for Customer Actvation");
		  Thread.sleep(8000);
		  oNavigate.toCustomerRegistration();
	      Thread.sleep(8000);
	      System.out.println("Entered In to CustomerRegistration ");
	      Thread.sleep(5000);
	      
	      String CustmrNum=oMulticonnection.NewCustomerRegistration_MultiConnection("Y",first_Name,last_Name,"","");
	      System.out.println("CustomerRegistration COMPLETED");
	      System.out.println("CustmrNum is"+CustmrNum);
  	      Thread.sleep(3000);
	
	      
	      // for own hardware if multiple hardware as N means one SC and STB  AND for multiple hardware Y means 2 SC's and 2 STB's
	      if(Sale_Own=="Own")
	      {
	    	  System.out.println("Entered in to Own Hardware");
	    	  if(Multiple_Hardware=="N")
	    	  {
	    		  System.out.println("Entered in to Own Hardware of Single Hardware ");
	    		  Thread.sleep(5000);
	    		  oNavigate.toOwnedHardware();
	    	
	    		  String STB = "100"+randomAlphaNumeric(4); 
	    		  String SC = "100"+randomAlphaNumeric(4);
	    		  
//	    		  String STB = "ab:cd:ef:gh:"+randomAlphaNumeric(2)+":"+randomAlphaNumeric(2); 
//	    		  String SC = "ab:cd:ef:gh:"+randomAlphaNumeric(2)+":"+randomAlphaNumeric(2);
	    		  
	    		  Thread.sleep(2000);
	    		  oOwnedHardware.Owned_Hardware_Add("",Item2,STB,"","","",1);
	    		  oOwnedHardware.Owned_Hardware_Add("",Item1,SC,"","","",2);
	    		  Thread.sleep(2000);
	    		  System.out.println("Giveing owned hardware with 1 sc and 1 stb");
	    		  Thread.sleep(3000);
	    		  driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
	    		  Thread.sleep(3000);
	    		  System.out.println("Given owned hardware with 1 sc and 1 stb");
	    	  }
	    	  else
	    	  {
	    		  oNavigate.toOwnedHardware();
	    		  String STB_serialnum1 = "PROVSC"+randomAlphaNumeric(8);
	    		  String SC_serialnum1 = "PROVSC"+randomAlphaNumeric(8);
	    		  String STB_serialnum2 = "PROVSC"+randomAlphaNumeric(8);
	    		  String SC_serialnum2 = "PROVSC"+randomAlphaNumeric(8);

	    		  oOwnedHardware.Owned_Hardware_Add("",Item2,STB_serialnum1,"","","",1);
	    		  oOwnedHardware.Owned_Hardware_Add("",Item1,SC_serialnum1,"","","",2);
	    		  oOwnedHardware.Owned_Hardware_Add("",Item2,STB_serialnum2,"","","",3);
	    		  oOwnedHardware.Owned_Hardware_Add("",Item1,SC_serialnum2,"","","",4);

	    		  driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
	    		  Thread.sleep(3000);
	    		  System.out.println("Given owned hardware with 2 sc and 2 stb");
	    	  }
	      }
		  
		  // for Sale hardware
	      else if (Sale_Own=="Sale")

	      {
	    	  System.out.println("Entered in to Sale hardware");

	    	  if(Multiple_Hardware=="N")
	    	  {
	    		  System.out.println("entered in to Sale hardware with Multiple Hardware As N");
	    		  Thread.sleep(5000);
	    		  mrnNumber=invTrans.mrnTrans("","1",Inventory_Location,Supplier,currentDate,"N",1,"Y","N","Y");
	    		  Thread.sleep(5000);
	    		  oNavigate.toOneTimeSale();		            
	    		  driver.findElement(loctxtCustNum).clear();		      
	    		  Thread.sleep(3000);	        

	    		  OTS.OneTimeSale_Cust_Header(PartyClass,CustomerNumber,"",Entity,Location,"","NEW");	        
	    		  OTS.OneTimeSale_Cust_Item_dtls(Item1,"1","1","","",1,mrnNumber);	         
	    		  Thread.sleep(2000);	         
	    		  OTS.OneTimeSale_Cust_Item_dtls(Item2,"1","1","","",2,mrnNumber);

	    		  Thread.sleep(2000);	         
	    		  driver.findElement(locSaveBtn).click();	        		       
	    		  Thread.sleep(3000);			 
	    		  Succ_Msg=driver.findElement(locMsg).getText();
	    		  System.out.println("Success message is" + Succ_Msg);
	    		  System.out.println("Given Sale hardware with 1 sc and 1 stb");
	    	  } 	

	    	  else
	    	  {

	    		  mrnNumber=invTrans.mrnTrans("","2",Inventory_Location,Supplier,currentDate,"N",1,"Y","N","Y"); 		      
	    		  String sQuery="select CUSTOMER_NBR from customer_tbl order by customer_id desc";
	    		  this.records=oDBConnection.fecthRecords(sQuery);
	    		  this.record=this.records.get(0);
	    		  String CustomerNumber=record.get("CUSTOMER_NBR");
	    		  System.out.println("The Customer Number is"+CustomerNumber);	
	    		  oNavigate.toOneTimeSale();		            
	    		  driver.findElement(loctxtCustNum).clear();		      
	    		  Thread.sleep(3000);	        
	    		  OTS.OneTimeSale_Cust_Header(PartyClass,CustomerNumber,"",Entity,Location,"","NEW");	        
	    		  OTS.OneTimeSale_Cust_Item_dtls(Item1,"2","2","","",1,mrnNumber);	         
	    		  Thread.sleep(2000);	         
	    		  OTS.OneTimeSale_Cust_Item_dtls(Item2,"2","2","","",2,mrnNumber);	                 
	    		  Thread.sleep(2000);	         
	    		  driver.findElement(locSaveBtn).click();	        		       
	    		  Thread.sleep(3000);			 
	    		  Succ_Msg=driver.findElement(locMsg).getText();
	    		  System.out.println("Success message is" + Succ_Msg);

	    		  System.out.println("Given owned hardware with 2 sc and 2 stb");

	    	  }

	      }
	     System.out.println("Hardware completed");
    
	     // This block if the No of Connection as 1 or more 
    	 if(sNoofConnections=="1")
    	 {
    		 oNavigate.toServiceOrder();
        	 System.out.println("Entered in to service order");
        	 String sResult = oserviceorder.BookPlan(CustomerNumber,sOrderDate,sEffectiveDate,sContractValidity,strPlanCode,auto_ApproveOrCancel,billing_Frequency);
        	 System.out.println("is:"+sResult);
        	 Thread.sleep(5000);
         }
    	 
    	 else 
    	 {
    		 	oNavigate.toServiceOrder();
        	 	System.out.println("Entered in to service order");
        	 	driver.findElement(locNoOfConnections).clear();
    	        Thread.sleep(8000);
    	        driver.findElement(locNoOfConnections).sendKeys(sNoofConnections+Keys.TAB);
    	        Thread.sleep(1500);
    	        String sResult = oserviceorder.BookPlan(CustomerNumber,sOrderDate,sEffectiveDate,sContractValidity,strPlanCode,auto_ApproveOrCancel,billing_Frequency);
    	        System.out.println("is:"+sResult);
    	  	  	Thread.sleep(5000);

    	 }
    	 
    	 return CustmrNum;
    	 
    }

    
    
    public String CustomerActvation_WithBulk() throws Exception
    {
    	  System.out.println("entered Into method for Customer Actvation");
		  Thread.sleep(2000);
		  oNavigate.toCustomerRegistration();
	      Thread.sleep(2000);
	      System.out.println("entered IN CUSTOMER REG ");
	      CustomerNumber = oCustomerRegistration.NewCustomerRegistration(first_Name,last_Name,"","");
	      System.out.println("CUSTOMER REG COMPLETED");
	      System.out.println("Customer number is : "+CustomerNumber); 
	      Thread.sleep(3000);
	      
	      oNavigate.toOwnedHardware();
          String STB = "PROVSTB"+randomAlphaNumeric(8);
          String SC = "PROVSC"+randomAlphaNumeric(8);
          oOwnedHardware.Owned_Hardware_Add("",Item2,STB,"","","",1);
          oOwnedHardware.Owned_Hardware_Add("",Item1,SC,"","","",2);
          driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
          Thread.sleep(3000);
         
          oNavigate.toServiceOrder();
          System.out.println("Entered in to service order");
          Thread.sleep(4000);
          driver.findElement(locapprove).click();
          Thread.sleep(2000);
          driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_chkIsBulk']")).click();
          Thread.sleep(3000);

          driver.findElement(locPlanSearchTab).sendKeys("NAGRA150"+Keys.TAB);
          Thread.sleep(3000);
          driver.findElement(locAddToCart).click();
          Thread.sleep(2000);
          driver.findElement(locSaveBtn).click();
          Thread.sleep(5000);
          String Succ_Msg = driver.findElement(locsuccessmsg).getText();
          Thread.sleep(3000);
          System.out.println("Success message is:"+Succ_Msg);
          
          oNavigate.toHardwareAssociation();
   	      Thread.sleep(5000);
   	      int k;
   	      WebElement table = driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody"));
   	      listOfRows = table.findElements(By.tagName("img"));
   	      System.out.println("Rows: "+listOfRows.size());


   	      for(int j =1;j<=listOfRows.size();j++)
   	      {
   	    	  k=j+1;
   	    	  System.out.println("start k value" +k);
   	    	  driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody/tr["+j+"]/td[5]/a/img")).click();				
   	    	  new Select(driver.findElement(By.xpath("//*[@id='ctl00_uxPgCPH_grdvAssociate_ctl0"+k+"_cmbunique']"))).selectByIndex(1);//need to modify
   	    	  driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody/tr["+j+"]/td[5]/a/img")).click();
   	    	  Thread.sleep(2000);
   	      }
   	      
   	      Thread.sleep(4000);
   	      driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_btnsave']")).click();
   	      System.out.println("Hardware Association Is Done Successfully");
   	      return CustomerNumber;
	
    }
    
    
    
}
    
    







