package ProvisioningTC;

import java.util.ArrayList;
import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.JobsPageObject.ScheduleJob;
import com.OrderManagementPageObject.CustomerRegistration;
import com.OrderManagementPageObject.NumberReservation;
import com.OrderManagementPageObject.ServiceOrder;
import com.PageObjects.InventoryAndLogisticsMasters.ItemMaster;
import com.PageObjects.InventoryAndLogisticsMasters.MerchandiseCatalog;
import com.PageObjectsProvisioning.UsageBasedServicesMasters.LogicalResourcePool;
import com.PageObjectsProvisioning.UsageBasedServicesMasters.NiceNumberCategory;
import com.PageObjectsProvisioning.UsageBasedServicesMasters.NumberPublishing;
import com.PageObjectsProvisioning.UsageBasedServicesMasters.NumbercategoryMarking;
import com.ProvisioningPageObject.ProvisioningRequests;
import com.ProvisioningPageObject.ProvisioningSetUp;

import TestFrameWork.MQProvisioning;
import Utility.Navigate;
import Utility.WaitTool;
import testlink.api.java.client.TestLinkAPIResults;

public class ProvisioningRequestsTC extends MQProvisioning
{
    //declaring driver
		WebDriver driver;
		//declaring objects
		Navigate oNavigate;
		WaitTool oWaitTool;
		
		CustomerRegistration oCustomerRegistration;
		NumberReservation oNumberReservation;
		ServiceOrder oserviceorder;
		ProvisioningRequests oProvisioningRequests;
		ProvisioningSetUp oProvisioningSetUp;
		ScheduleJob oScheduleJob;
		//Master Screens
		ItemMaster oItemMaster;
		NiceNumberCategory oNiceNumberCategory;
		LogicalResourcePool oLogicalResourcePool;
		MerchandiseCatalog oMerchandiseCatalog;
		NumberPublishing oNumberPublishing;
		NumbercategoryMarking oNumbercategoryMarking;
			
		
		
		//declaring variables
		String sResult,Result,CustomerId;
		String sQuery,ScheduleJobQuery,ScheduleJobQuery1;
		public ArrayList<Hashtable<String, String>> records;
		public Hashtable<String,String> record;
		String ItemMasterValidation,MerchandiseCatalogValidation,NiceNumberCategoryValidation,LogicalResourcePoolValidation;
        String NumberPublishingValidation,NumbercategoryMarkingValidation,CustomerNumber,Order,ScheduleJobValidation,ScheduleJobValidation1;
        
        
protected ProvisioningRequestsTC(String sLogFileName) throws Exception
{
	super("Provisioning Requests screen");
	// TODO Auto-generated constructor stub
}
@Override
protected void beforeClass() {
driver=getDriver();
this.oCustomerRegistration = new CustomerRegistration(driver);
this.oNumberReservation = new NumberReservation(driver);
this.oserviceorder=new ServiceOrder(driver,dbConnection);
this.oProvisioningRequests =new ProvisioningRequests(driver);
this.oProvisioningSetUp =new ProvisioningSetUp(driver);
this.oScheduleJob = new ScheduleJob(driver);
this.oItemMaster = new ItemMaster(driver);
this.oNiceNumberCategory = new NiceNumberCategory(driver);	
this.oLogicalResourcePool =new LogicalResourcePool(driver);
this.oMerchandiseCatalog = new MerchandiseCatalog(driver);
this.oNumberPublishing = new NumberPublishing(driver);
this.oNumbercategoryMarking = new NumbercategoryMarking(driver);


testLinkConnection.setsTestProject("Mediation");
testLinkConnection.setsTestPlan("5.9.2");
testLinkConnection.setsBuild("592Globetec");
this.oNavigate=new Navigate(driver,dbConnection);
this.oWaitTool= new WaitTool(driver);
PageFactory.initElements(driver,this);
//driver.getCurrentUrl();	
verifyLogin("","");
}
/*

@Test(description="Navigation to Provisioning Requests screen")
public void dummy() throws Exception
{
//testLinkConnection.setsTestCase("UM-179",this);
logger.info("Navigation to Provisioning Requests screen");
isbSkipTest();
isbCaseDepend(true);
try
{
  oNavigate.toProvisioningRequest();
  logger.info("Navigation to Provisioning Requests screen");
  String ProvisioningRequestsValidation =oProvisioningRequests.ProvisioningRequestsScreen("126960","","","","","","","","","Search","Sent","save");
  Thread.sleep(2300);
  System.out.println("ProvisioningSetUpValidation is :"+ProvisioningRequestsValidation);
 if(ProvisioningRequestsValidation.equalsIgnoreCase("Successfully updated the Request(s) Status"))
 {
 System.out.println("Tesst Passed1");
 
 //Click on the Operations button
 driver.findElement(oLogicalResourcePool.locOperationScreens).click();
 Thread.sleep(1000);
 oNavigate.toProvisioningRequest();
 logger.info("Navigation to Provisioning Requests screen");
 String ProvisioningRequestsValidation1 =oProvisioningRequests.ProvisioningRequestsScreen("126960","","","","","","Sent","","","Search","Accept","save");
 Thread.sleep(2300);
 System.out.println("ProvisioningSetUpValidation is :"+ProvisioningRequestsValidation1);
if(ProvisioningRequestsValidation1.equalsIgnoreCase("Successfully updated the Request(s) Status"))
{
 
	System.out.println("Test Passed on Provisioning Request Screen");
	
}
else
{
	System.out.println("Test failed on Provisioning Request Screen");
	
}
 }//if case - View Payment
 else
 {
 System.out.println("Test Failed1");
}
 
}
catch(Exception e)
{
   //captureScreenShot.takeScreenShot("UM-179",driver);
System.out.println("Test_____%Failed");
//testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
setbCaseDepend(false);
}
}

*/


@Test(description="Navigation to Provisioning Requests screen")
public void MED_2423() throws Exception
{
testLinkConnection.setsTestCase("MED-2423",this);
logger.info("Navigation to Provisioning Requests screen");
isbSkipTest();
isbCaseDepend(true);
try
{

 	 //Navigate to Item Master Screen
 	  oNavigate.toItemMaster();
 	  logger.info("Successfully Navigated to Item Master screen.,");
 	  ItemMasterValidation =oItemMaster.ItemMasterCreation("AMBCITEM08","AMBCITEM08","Soft Charge","","Yes","Yes","Yes","","Number(s)","","","","","","Save");
 	  // Thread.sleep(2300);
 	  System.out.println("Item Master Validation  is :"+ItemMasterValidation);
 	  if(ItemMasterValidation.equalsIgnoreCase("Inventory Item has been added successfully"))
 	  {
 	  Thread.sleep(600);
	  driver.findElement(oLogicalResourcePool.locOperationScreens).click();
	  Thread.sleep(600);
 	  //Navigate to Merchandise Catalog Screen for forwarding the item code for generating the price
 	  oNavigate.toMerchandiseCatalog();
 	  logger.info("Successfully Navigated to Merchandise Catalog screen.,");
      MerchandiseCatalogValidation =oMerchandiseCatalog.MerchandiseCatalogCreation("","","","","","AMBCITEM08","Installation Charge","SALE","Yes","","","","200","Save");
 	  // Thread.sleep(2300);
 	  System.out.println("Merchandise Catalog Validation  is :"+MerchandiseCatalogValidation);
 	  if(MerchandiseCatalogValidation.equalsIgnoreCase("Merchandise Catalog has been updated successfully"))
 	  {
 		 Thread.sleep(600);
 		  driver.findElement(oLogicalResourcePool.locOperationScreens).click();
 		  Thread.sleep(600);
 	  
 	  oNavigate.toNiceNumberCategory();
 	  logger.info("Successfully Navigated to Nice Number Category screen.,");
 	  NiceNumberCategoryValidation =oNiceNumberCategory.NewNiceNumberCategoryCreation("AMBCCATEG08","AMBCCATEG08","","AMBCITEM07","","112233141","Start With","112233150","","1","No","No","Save");
 	  // Thread.sleep(2300);
 	  System.out.println("Nice Number Category Validation  is :"+NiceNumberCategoryValidation);
 	  if(NiceNumberCategoryValidation.equalsIgnoreCase("Details Saved Successfully"))
 	  {
 	  Thread.sleep(600);
 	  driver.findElement(oLogicalResourcePool.locOperationScreens).click();
 	  Thread.sleep(600);
 	 		  
	
      //Navigate to Logical Resource Pool Screen Provision Masters
   	  oNavigate.toLogicalResourcePool();
   	  logger.info("Successfully Navigated to Logical Resource Pool Validation screen.,");
   	  LogicalResourcePoolValidation =oLogicalResourcePool.LogicalResourcesPoolCreation("AMBCPOOL08","AMBCPOOL08","VOIPNo","","","112233151","112233159","AMBCPOOL08","Yes","Yes","Yes","Save");
   	  //Thread.sleep(2300);"
   	  System.out.println("Logical Resource Pool Validation is :"+LogicalResourcePoolValidation);
      if(LogicalResourcePoolValidation.equalsIgnoreCase("Successfully created the pool"))
   	  {
	    
      Thread.sleep(600);
  	  driver.findElement(oLogicalResourcePool.locOperationScreens).click();
   	  Thread.sleep(600);
	 
   	  oNavigate.toNiceNumbercategoryMarking();
  	  logger.info("Successfully Navigated to Nice Number Category screen.,");
  	  NumbercategoryMarkingValidation =oNumbercategoryMarking.NewNumberCategoryMarking("","Search","AMBCCATEG08","Pool","AMBCPOOL08","","","Save");
  	  // Thread.sleep(2300);
  	  System.out.println("Number category Marking Validation  is :"+NumbercategoryMarkingValidation);
  	  if(NumbercategoryMarkingValidation.contains("successfully "))
  	  {   		  
  	   	  Thread.sleep(600);
  	   	  driver.findElement(oLogicalResourcePool.locOperationScreens).click();
  	   	  Thread.sleep(600);
   		
   	  oNavigate.toNumberPublishing();
   	  logger.info("Successfully Navigated to Number Publishing screen. for Pool,");
   	  NumberPublishingValidation =oNumberPublishing.NewNumberPublishing("","AMBCPOOL08","","","","Publish");
   	 // Thread.sleep(2300);
   	  System.out.println("Number Publishing Validation  is :"+NumberPublishingValidation);
   	  if(NumberPublishingValidation.equalsIgnoreCase("Published successfully"))
   	  {


       
   	  Thread.sleep(600);
   	  driver.findElement(oLogicalResourcePool.locOperationScreens).click();
   	  Thread.sleep(600);
   		  
	  //Navigate to Customer Registration Screen
	  oNavigate.toCustomerRegistration();
      // Thread.sleep(3000);
 	  logger.info("Successfully Navigated to Customer Registration Screen");
 	  CustomerNumber = oCustomerRegistration.NewCustomerRegistration("Jack","Aadvik","","");
 	  if(!(CustomerNumber == null))
 	  {
 		 
   	  //Navigate to Number Reservation Screen
 	  oNavigate.toNumberReservation();
  	  Thread.sleep(600);
 	  logger.info("Successfully Navigated to Number Reservation Screen");
  	  String NumberReservationValidation = oNumberReservation.NumberReservationCreation("","","Add","112233152","","","Search","Update","Save");
 	  if(NumberReservationValidation.contains("VoIP number(s) are reserved successfully"))
  	  { 		  
 	  //Navigate to Service Order Screen
      oNavigate.toServiceOrder();
      Thread.sleep(600);
      logger.info("Successfully Navigated to Service Order Screen ");
      Order = oserviceorder.BookPlan("","","","One Month","Intel Basic(Default RP)");
      System.out.println("Order--->"+Order);
     // Thread.sleep(2000);
      if(Order.contains("successfully"))
      {
      //Navigate to Schedule jobs screen in Jobs and set the category as Batch Jobs and the Program as SetUp Provision Details   
      oNavigate.toScheduleJob();
      logger.info("Navigation to Schedule Jobs screen");
      ScheduleJobValidation =oScheduleJob.ScheduleJobScreen("","Batch Jobs","Setup Provision Details","","","","","");
      //Thread.sleep(2300);
      System.out.println("ScheduleJobValidation is :"+ScheduleJobValidation);
      if(ScheduleJobValidation.equalsIgnoreCase("Schedule has been saved successfully"))
      { 		  
     // Thread.sleep(500);
      sQuery="SELECT * FROM customer_tbl WHERE customer_nbr='"+CustomerNumber+"'";
 	  System.out.println("Query in Database : "+sQuery);
      //Thread.sleep(10000);
  	  this.records=dbConnection.fecthRecords(sQuery);
  	  this.record=this.records.get(0);
  	  CustomerId=record.get("CUSTOMER_ID");
      System.out.println("Customer Id of the Customer is : "+CustomerId);
      //with the reference of customer id we have to check the column is_associated is updated as 'Y' or not .'Y' indicates that job will be succeded
      //Thread.sleep(600);
      
     // Thread.sleep(1000);
      ScheduleJobQuery="select * from provsion_process_request where party_id='"+CustomerId+"'";
 	  System.out.println("Query in Database : "+ScheduleJobQuery);
      //Thread.sleep(10000);
  	  this.records=dbConnection.fecthRecords(ScheduleJobQuery);
  	  this.record=this.records.get(0);
  	  String AssociatedStatus=record.get("IS_ASSOCIATED");
      System.out.println("Associated Status in the data base is : "+AssociatedStatus);
      
      if(AssociatedStatus.equalsIgnoreCase("N")) 
      {
      for(int i=1;i<=3;i++)
      {
      Thread.sleep(3000);
      ScheduleJobQuery="select * from provsion_process_request where party_id='"+CustomerId+"'";
      System.out.println("Query in Database : "+ScheduleJobQuery);
      //Thread.sleep(10000);
      this.records=dbConnection.fecthRecords(ScheduleJobQuery);
      this.record=this.records.get(0);
      String AssociatedStatus1=record.get("IS_ASSOCIATED");
      System.out.println("Associated Status in the data base is : "+AssociatedStatus1);
      if(AssociatedStatus1.equalsIgnoreCase("Y")) 
      {   
      break;
      }
      } //for loop i 
      
      //Navigate to Schedule jobs screen in Jobs and set the category as Batch Jobs and the Program as Generate request   
      oNavigate.toScheduleJob();
      logger.info("Navigation to Schedule Jobs screen");
      ScheduleJobValidation1 =oScheduleJob.ScheduleJobProgramGetRequest("","Batch Jobs","Generate Request","","","","","Default","");
      //Thread.sleep(2300);
      System.out.println("ScheduleJobValidation is :"+ScheduleJobValidation1);
      if(ScheduleJobValidation1.equalsIgnoreCase("Schedule has been saved successfully"))
      { 		  
      
       //with the reference of customer id we have to check the column is_provisoned is updated as 'Y' or not .'Y' indicates that job will be succeded
       //Thread.sleep(600);
       
       //Thread.sleep(3000);
       ScheduleJobQuery1="select * from provsion_process_request where party_id='"+CustomerId+"'";
       System.out.println("Query in Database : "+ScheduleJobQuery1);
       //Thread.sleep(10000);
   	   this.records=dbConnection.fecthRecords(ScheduleJobQuery1);
       this.record=this.records.get(0);
       String ProvisionedStatus=record.get("IS_PROVISIONED");
       System.out.println("Provisoned Status in the data base is : "+ProvisionedStatus);
       if(ProvisionedStatus.equalsIgnoreCase("N")) 
       {  
       for(int j=1;j<=4;j++)
       {
       Thread.sleep(3000);
       ScheduleJobQuery1="select * from provsion_process_request where party_id='"+CustomerId+"'";
       System.out.println("Query in Database : "+ScheduleJobQuery1);
       //Thread.sleep(10000);
       this.records=dbConnection.fecthRecords(ScheduleJobQuery1);
       this.record=this.records.get(0);
       String ProvisionedStatus1=record.get("IS_PROVISIONED");
       System.out.println("Provisoned Status in the data base is : "+ProvisionedStatus1);
       if(ProvisionedStatus1.equalsIgnoreCase("Y")) 
       {   
       break;   
       }
       } //for loop j
       
       //Navigate to Provisioning set up screen
       oNavigate.toProvisioningSetUp();
       logger.info("Navigation to Provisioning SetUp screen");
       String ProvisioningSetUpValidation =oProvisioningSetUp.ProvisioningSetUpSearch("","","","","","112233152","","","Search","Save");
       //Thread.sleep(2300);
       System.out.println("Provisioning Set Up Validation is :"+ProvisioningSetUpValidation);
       if(ProvisioningSetUpValidation.equalsIgnoreCase("Network account has been activated successfully"))
       {  
    	   Thread.sleep(1000);
       //Navigate to Provisioning Set up Screen
        oNavigate.toProvisioningRequest();
    	logger.info("Navigation to Provisioning Requests screen");
        String ProvisioningRequestsValidation =oProvisioningRequests.ProvisioningRequestsScreen("130030","","","","","","","","","Search","Sent","save");
        //Thread.sleep(2300);
        System.out.println("ProvisioningSetUpValidation is :"+ProvisioningRequestsValidation);
        if(ProvisioningRequestsValidation.equalsIgnoreCase("Successfully updated the Request(s) Status"))
        {
        System.out.println("Tesst Passed1");
    	 
    	 //Click on the Operations button
    	// driver.findElement(oLogicalResourcePool.locOperationScreens).click();
    	// Thread.sleep(1000);
    	 oNavigate.toProvisioningRequest();
    	 logger.info("Navigation to Provisioning Requests screen");
    	 String ProvisioningRequestsValidation1 =oProvisioningRequests.ProvisioningRequestsScreen("","","","","","","Sent","","","Search","Accept","save");
    	// Thread.sleep(2300);
    	 System.out.println("Provisioning Request Validation is :"+ProvisioningRequestsValidation1);
    	if(ProvisioningRequestsValidation1.equalsIgnoreCase("Successfully updated the Request(s) Status"))
    	{    	  
      
    	 Thread.sleep(3000);
         ScheduleJobQuery1="select * from provsion_process_request where party_id='"+CustomerNumber+"'";
         System.out.println("Query in Database : "+ScheduleJobQuery1);
         //Thread.sleep(10000);
    	 this.records=dbConnection.fecthRecords(ScheduleJobQuery1);
         this.record=this.records.get(0);
         String Status=record.get("STATUS");
         System.out.println("Provisoned Status in the data base is : "+Status);
         if(Status.equalsIgnoreCase("C")) 
         { 
       	  testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
   	      testLinkConnection.setsNotes("Execution done sucessfully ,  In Logical_res_pool_detail table allocated_status should be displayed as 'U' for New VOIP Pool ,So test passed");
   	      System.out.println("Execution done sucessfully ,  In Logical_res_pool_detail table allocated_status should be displayed as 'U' for New VOIP Pool ,So test passed");
   	      testLinkConnection.setsNotes("Execution done sucessfully ,  In Logical_res_pool_detail table allocated_status should be displayed as 'U' for New VOIP Pool ,So test passed");
   		  System.out.println("Execution done sucessfully ,  In Logical_res_pool_detail table allocated_status should be displayed as 'U' for New VOIP Pool ,So test passed");
       	 
         }
         else
         {
   	      testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
   	      testLinkConnection.setsNotes("Execution Failed , In Logical_res_pool_detail table allocated_status should not be displayed as 'U' for New VOIP Pool  ,So test failed");
          System.out.println("Execution Failed , In Logical_res_pool_detail table allocated_status should not be displayed as 'U' for New VOIP Pool  ,So test failed");
   	      testLinkConnection.setsNotes("Execution Failed , In Logical_res_pool_detail table allocated_status should not be displayed as 'U' for New VOIP Pool  ,So test failed");
          System.out.println("Execution Failed , In Logical_res_pool_detail table allocated_status should not be displayed as 'U' for New VOIP Pool  ,So test failed");	        
         
         }
    	 }
    	 else
    	 {
    	 System.out.println("Test Failed in Provisioning Request screen ");
    	 } 
         }
    	 else
    	 {
    	 System.out.println("Test Failed in Provisioning Request screen ");
    	 }
         }
         else
         {
         System.out.println("Test Failed in Provisioing Set up Screen"); 
         }
         }
         else
         {
         System.out.println("Test Failed in the Schedule Jobs in data base details are not updated");
         }
         }
         else
         {
         System.out.println("Test Failed in Schedule Jobs Screen for the Program as Generate Request");
         }
         }
         else
         {
         System.out.println("Test Failed in the Schedule Jobs in data base details are not updated");
         }
         }
         else
         {
         System.out.println("Test Failed in Schedule Jobs Screen for the Program as Set up provision details");
         }
         }
         else
         {
         System.out.println("Test Failed in Service Order Screen");
         }
 	     }
 	     else
 	     {
 	     System.out.println("Test Failed in number Reservation Screen");
 	     }
 	     }
 	     else
 	     {
 	     System.out.println("Test Failed in Customer Registration screen");
 	     }
 	    }
   	     else
   	     {
   	     System.out.println("Test failed in Number Publishing Screen");
   	     }
   	     } 
   	     else
   	     {
         System.out.println("Test failed in Nice Number Marking Screen");  	
   	     }
   	     }
        else
        {
        System.out.println("Test failed in Logical Resource Pool Validation Screen");
         }
 	     }
 	    else
 	    {
 	    System.out.println("Test failed in Nice Number Category Screen");
 	     }
 	     }
   	   else
   	    {
   	     System.out.println("Test failed in Merchentaised Catalog Screen");  
   	     }
   	     }
         else
         {
         System.out.println("Test failed in Item Master Screen");
         }
}
   
catch(Exception e)
{
	 captureScreenShot.takeScreenShot("MED-2423",driver);
     System.out.println("Test_____%Failed");
     testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
     testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	  setbCaseDepend(false);
}
}



}
