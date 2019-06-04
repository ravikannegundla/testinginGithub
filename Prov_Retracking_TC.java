package ProvisioningTC;

import java.util.ArrayList;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.BasicConfigurationInventory.InventoryTransactions;
import com.BasicConfigurationsOfServiceOrder.OrderManagementPrivileges;
import com.InventoryAndLogisticsPageObject.HardwareReplacement;
import com.InventoryAndLogisticsPageObject.OwnedHardware;
import com.OrderManagementPageObject.CustomerRegistration;
import com.OrderManagementPageObject.Disconnection;
import com.OrderManagementPageObject.HardwareAssociation;
import com.OrderManagementPageObject.Reconnection;
import com.OrderManagementPageObject.Renewal;
import com.OrderManagementPageObject.ServiceOrder;
import com.ProvisioningPageObject.BasicConfigurationsOfProvisioning;
import com.ProvisioningPageObject.ProvisioningRequests;
import com.ProvisioningPageObject.Retracking;
import com.ReceivablesPageObject.Collections;

import TestFrameWork.MQProvisioning;
import Utility.Navigate;
import Utility.WaitTool;
import mq.seleniumFW.connection.MQDBConnection;
import testlink.api.java.client.TestLinkAPIResults;

		public class Prov_Retracking_TC extends MQProvisioning
		{

	
		WebDriver driver;
		Navigate oNavigate;
		WaitTool oWaitTool;
	
		CustomerRegistration oCustomerRegistration;
		ServiceOrder oserviceorder;
		ProvisioningRequests oProvisioningRequests;
		OwnedHardware oOwnedHardware;
		HardwareAssociation oHardwareAssociation;
		BasicConfigurationsOfProvisioning oBasicConfigurationsOfProvisioning;
		Disconnection oDisconnection;
		Reconnection oReconnection;
		HardwareReplacement oHardwareReplacement;
		Renewal oRenewal;
		Collections oCollections;
		Retracking oRetracking;
		OrderManagementPrivileges oOrderManagementPrivileges;




		public By locPlanSearchTab = By.id("ctl00_uxPgCPH_txtplnsrch");
		public By locAddToCart = By.xpath(".//*[@id='ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd']");
		public By locNoOfRooms = By.id("ctl00_uxPgCPH_txtNoofrooms");
		public By locApprove = By.id("ctl00_uxPgCPH_chkapprove");
		public By locsuccessmsg=By.xpath(".//*[@id='ctl00__infoSpan']");
		public By locModify = By.id("ctl00_uxPgCPH_grdcontractdtls_ctl02_lnkmodify");
		public By locContractNumber = By.xpath(".//*[@id='ctl00_uxPgCPH_txtcontractno']");
		public By locSaveBtn=By.id("ctl00_uxPgCPH_btnsave");

		
		public ArrayList<Hashtable<String, String>> records;
		public Hashtable<String,String> record;
		public MQDBConnection oDBConnection;
		String strPlanCode = "PLAN1SERV1";
		String billing_Frequency = "One Month";
		String contract_validity = "One Month";
		String first_Name = "Ravi";
		String last_Name = "Kannegundla";
	
		String renewalBackdated="13-02-2019";
		String Status,StatusMRPL="Faulty";
		String C_Status="C",N_Status="N";
		String Party_id,Qstatus,Customer_id;
		String Item1="Smart Card",Item2="Set Top Box";
	
		// One Time Sale related
		String mrnNumber;
		public String Inventory_Location="Head Location",Supplier="Conax Supplier",currentDate="18-02-2019";
		public By loctxtCustNum=By.id("ctl00_uxPgCPH_uxSearchCtrl_txtCustnbr");
		public By locMsg=By.id("ctl00__infoSpan");
		String PartyClass="Customer", Entity="CORP",Location="Head Location",Succ_Msg;
		public InventoryTransactions invTrans;
		int services_count=1;
		String MultiRoomplan="NAGRA150";
		String hardwarePlan="HWPLAN1111";
		String Reason="BADPAYER";
		public String ContractNumber;
		String OperationalEntity="CORP";
		public String OrderType = "One Time Sale Order";
		String RenewalValidation;
		public     String Sent="Sent",Accept="Accept",New="New";
		String sResult;
		String sQuery,CustomerNumber;
		String getConnectionNumber,Connection_Number;
		int RetrackCount;
		String Retrack_Count;
    
		
		
	
		protected Prov_Retracking_TC(String sLogFileName) throws Exception
		{
			super(sLogFileName);
			 this.oDBConnection=dbConnection;
			 PageFactory.initElements(driver, this);
			 
			// TODO Auto-generated constructor stub
		}

	
		
		
		
		@Override
	    protected void beforeClass() 
	    {
	   	 driver=getDriver();
	   	 driver.manage().window().maximize();
	   	 this.oCustomerRegistration = new CustomerRegistration(driver);
	   	 this.oserviceorder=new ServiceOrder(driver,dbConnection);
	   	 this.oProvisioningRequests =new ProvisioningRequests(driver);
	   	 this.oOwnedHardware=new OwnedHardware(driver, oDBConnection);
	   	 this.oHardwareAssociation=new HardwareAssociation(driver,dbConnection);
	   	 this.oBasicConfigurationsOfProvisioning=new BasicConfigurationsOfProvisioning(driver,dbConnection);
	   	 this.oDisconnection = new Disconnection(driver);
	   	 this.oReconnection = new Reconnection(driver);
	     this.invTrans=new InventoryTransactions(driver, dbConnection);
	   	 this.oHardwareReplacement= new HardwareReplacement(driver,dbConnection);
	   	 this.oRenewal = new Renewal(driver);
	   	 this.oRenewal = new Renewal(driver,dbConnection);
	   	 this.oRetracking=new Retracking(driver,dbConnection);
	   	 this.oCollections=new Collections(driver,dbConnection);
	   	 this.oBasicConfigurationsOfProvisioning=new BasicConfigurationsOfProvisioning(driver,dbConnection);
	
		 oBasicConfigurationsOfProvisioning.OwnHardware_Privileges();
		 oOrderManagementPrivileges.Disconnection_privileges();
		 oOrderManagementPrivileges.Reconnection_privileges();
	//	 oOrderManagementPrivileges.ServiceOrder_privileges();
		
		 
	   	 
	   	 
	   	 testLinkConnection.setsTestProject("PayTV Provisioning");
	   	 testLinkConnection.setsTestPlan("5.12.1");
	   	 testLinkConnection.setsBuild("5.12.1");
	   	 this.oNavigate=new Navigate(driver,dbConnection);
	   	 this.oWaitTool= new WaitTool(driver);
	   	 PageFactory.initElements(driver,this);
	   	 verifyLogin("","");
	
	    }
		
		
		public void relogin() throws Exception
		{
		 	 oNavigate.Navigation_Action("Logout");
			 oWaitTool.implicitWait(10);		
	//		 verifyLogin("KRAVI","KRAVI123");
			 verifyLogin("SYSADMIN","SYSADMIN");
			 oWaitTool.implicitWait(10);
		}
	
		
		  //To generate Random numbers      ABCDEFGHIJKLMNOPQRSTUVWXYZ
	    private static final String ALPHA_NUMERIC_STRING = "0123456789";
	    public static String randomAlphaNumeric(int count) {
	    StringBuilder builder = new StringBuilder();
	    while (count-- != 0) {
	    int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
	    builder.append(ALPHA_NUMERIC_STRING.charAt(character));
	    }
	    System.out.println("value is:"+builder.toString());
	    return builder.toString();
	    }
	
		
		
		
		@Test(description="Retracking an Active Customer with specified services")
	    public void PR_4069() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4069",this);
	    	   System.out.println("PR-4069 Started");
	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	   Thread.sleep(15000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   oNavigate.toRetracking();
	    	   oRetracking.RetrackingScreen(CustomerNumber, "Service",1, "N", "Save");
	    	   Thread.sleep(5000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	   
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID in (17,18)   order by 1 desc ";
	    	   this.records=oDBConnection.fecthRecords(Status);
	    	   this.record=this.records.get(0);
	    	   String Qstatus=record.get("STATUS");
	    	   System.out.println(Qstatus);
	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	   {
	    		   System.out.println("PR-4069 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4069 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4069",driver);
	    	   System.out.println("Test__PR-4069___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }

	
		
		@Test(description="Retracking all Services for an Active customer ")
	    public void PR_4070() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4070",this);
	    	   System.out.println("PR-4070 Started");
	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	   Thread.sleep(10000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	   oNavigate.toRetracking();
	    	   oRetracking.RetrackingScreen(CustomerNumber, "UniqueId",1, "N", "Save");
	
	    	   Thread.sleep(10000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID in (17,18)   order by 1 desc ";
	    	   this.records=oDBConnection.fecthRecords(Status);
	    	   this.record=this.records.get(0);
	    	   String Qstatus=record.get("STATUS");
	    	   System.out.println(Qstatus);
	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	   {
	    		   System.out.println("PR-4070 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4070 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4070",driver);
	    	   System.out.println("Test__PR-4070___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
		
		
		@Test(description="Retracking Services with Initialize ")
	    public void PR_4071() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4071",this);
	    	   System.out.println("PR-4071 Started");
	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	   Thread.sleep(10000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   oNavigate.toRetracking();
	    	   oRetracking.RetrackingScreen(CustomerNumber, "UniqueId",1, "Y", "Save");
	    	   Thread.sleep(10000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	   
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID in (17,18)   order by 1 desc ";
	    	   this.records=oDBConnection.fecthRecords(Status);
	    	   this.record=this.records.get(0);
	    	   String Qstatus=record.get("STATUS");
	    	   System.out.println(Qstatus);
	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	   {
	    		   System.out.println("PR-4071 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4071 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4071",driver);
	    	   System.out.println("Test__PR-4071___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
	
		
		@Test(description="Retracking Services for Disconnected Customer ")
	    public void PR_4072() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4072",this);
	    	   System.out.println("PR-4072 Started");
	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	   Thread.sleep(5000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   Thread.sleep(3000);
	    	   oNavigate.toDisconnection();
	    	   Thread.sleep(5000);
	    	   logger.info("Successfully Navigated to Disconnection Screen ");
	    	   sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	   System.out.println("Success message is:"+success_Msg);
	    	   Thread.sleep(10000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	  
	    	   Thread.sleep(3000);
	    	   oNavigate.toRetracking();
	    	   oRetracking.RetrackingScreen(CustomerNumber, "UniqueId",1, "N", "Save");
	    	  
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String getRetrackCount = "select COUNT(*) as COUNT from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID in (17,18)   order by 1 desc ";
	    	   this.records=oDBConnection.fecthRecords(getRetrackCount);
	    	   this.record=this.records.get(0);
	    	   Retrack_Count=record.get("COUNT");
	    	   RetrackCount = Integer.parseInt(Retrack_Count);
	    	   System.out.println("Retrack Count is "+RetrackCount);
	    	   if(RetrackCount==0)       
	    	   {
	    		   System.out.println("PR-4072 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4072 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4072",driver);
	    	   System.out.println("Test__PR-4072___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
	
		
		@Test(description="Retracking Services for Reconnected customer")
	    public void PR_4073() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4073",this);
	    	   System.out.println("PR-4073 Started");
	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	   Thread.sleep(5000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	   
	    	   Thread.sleep(5000);
	    	   oNavigate.toDisconnection();
	    	   Thread.sleep(5000);
	    	   sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	   System.out.println("Success message is:"+success_Msg);
	    	   Thread.sleep(10000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   oNavigate.toReconnection();
	    	   oWaitTool.implicitWait(10);
	    	   oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
	    	   oWaitTool.implicitWait(50);
	    	   String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	   System.out.println("Success message is:"+success_Msg1);
	    	   System.out.println("Reconnection is done");
	    	   
	    	   Thread.sleep(10000);
	           oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	   oNavigate.toRetracking();
	    	   oRetracking.RetrackingScreen(CustomerNumber, "UniqueId",1, "N", "Save");
	    	   Thread.sleep(10000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	   
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID in (17,18)   order by 1 desc ";
	    	   this.records=oDBConnection.fecthRecords(Status);
	    	   this.record=this.records.get(0);
	    	   String Qstatus=record.get("STATUS");
	    	   System.out.println(Qstatus);
	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	   {
	    		   System.out.println("PR-4073 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4073 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4073",driver);
	    	   System.out.println("Test__PR-4073___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
	
		
		@Test(description="Retracking for Suspended customer")
	    public void PR_4074() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4074",this);
	    	   System.out.println("PR-4074 Started");
	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	   Thread.sleep(2000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   oNavigate.toCollection();
	    	   oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
	    	   Thread.sleep(5000);
	    	   oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
	    	   Thread.sleep(80000);
	    	  
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String getCount1="select COUNT(DISTINCT(UNIQUE_ID)) from PROVSION_SUSPEND_ENTITLEMENT where suspend_STATUS in ('N','C')  AND   PARTY_ID='"+Party_id+"' AND RECONNECT_STATUS IS NULL";
	    	   this.records=oDBConnection.fecthRecords(getCount1);
	    	   this.record=this.records.get(0);
	    	   String count1=record.get("COUNT(DISTINCT(UNIQUE_ID))");
	    	   int DB_UniqueId_Count = Integer.parseInt(count1);
	    	   System.out.println("count1 is "+DB_UniqueId_Count);
	    	   if(DB_UniqueId_Count>0)       
	    	   {
	    		   System.out.println("PR-4074 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4074 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4074",driver);
	    	   System.out.println("Test__PR-4074___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
	
		
		@Test(description="Retracking Services for Reactivated customer")
	    public void PR_4075() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4075",this);
	    	   System.out.println("PR-4075 Started");
	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	   Thread.sleep(2000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	   
	    	   oNavigate.toCollection();
	    	   oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
	    	   Thread.sleep(5000);
	    	   oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
	    	   Thread.sleep(50000);
	    	  
	    	   oNavigate.toCollection();
	    	   oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
	    	   Thread.sleep(5000);
	    	   oCollections.collectionGrid("", "","Active", "", "Save");
	    	   Thread.sleep(70000);
	    	   Thread.sleep(75000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	   oNavigate.toRetracking();
	    	   oRetracking.RetrackingScreen(CustomerNumber, "UniqueId",1, "Y", "Save");
	    	   Thread.sleep(80000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	   
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID in (17,18)   order by 1 desc ";
	    	   this.records=oDBConnection.fecthRecords(Status);
	    	   this.record=this.records.get(0);
	    	   String Qstatus=record.get("STATUS");
	    	   System.out.println(Qstatus);
	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	   {
	    		   System.out.println("PR-4075 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4075 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4075",driver);
	    	   System.out.println("Test__PR-4075___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
		
		// doubt need to write later 
	
		@Test(description="Retracking Services for Renewed customer ")
	    public void PR_4076() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4076",this);
	    	   System.out.println("PR-4076 Started");
	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", renewalBackdated, renewalBackdated,contract_validity,strPlanCode,"Y",billing_Frequency);
	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	   System.out.println("Contract Number is : "+ContractNumber);
	
	    	   Thread.sleep(5000);
	    	   oNavigate.toProvisioningRequest();
	    	   Thread.sleep(3000);
	
	    	   oProvisioningRequests.ProvisioningRequestsScreen("","",renewalBackdated,"","","",New,Sent,"","Search","Save");
	    	   String successMsg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	   System.out.println("Success message is:"+successMsg1 );
	    	   Thread.sleep(2000);
	    	   driver.navigate().refresh();
	    	   oProvisioningRequests.ProvisioningRequestsScreen("","",renewalBackdated,"","","",Sent,Accept,"","Search","Save");
	
	    	   oNavigate.toRenewal();
	    	   RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y","","",contract_validity,"Save");
	    	   System.out.println("Success message is:"+RenewalValidation);
	
	    	   Thread.sleep(10000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	   oNavigate.toRetracking();
	    	   oRetracking.RetrackingScreen(CustomerNumber, "UniqueId",1, "Y", "Save");
	    	   Thread.sleep(80000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID in (17,18)   order by 1 desc ";
	    	   this.records=oDBConnection.fecthRecords(Status);
	    	   this.record=this.records.get(0);
	    	   String Qstatus=record.get("STATUS");
	    	   System.out.println(Qstatus);
	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	   {
	    		   System.out.println("PR-4076 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4076 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4076",driver);
	    	   System.out.println("Test__PR-4076___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
	
		
		@Test(description=" Retracking Services after Hardware Change(Primary Hardware) ")
	    public void PR_4077() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4077",this);
	    	   System.out.println("PR-4077 Started");
	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	   Thread.sleep(30000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	    	   this.records=oDBConnection.fecthRecords(getConnectionNumber);
	    	   this.record=this.records.get(0);
	    	   Connection_Number=record.get("CONNECTION_NUMBER");
	    	   System.out.println("connection Number is "+Connection_Number);
	    	   Thread.sleep(2000);
	    	   oNavigate.toHardwareAssociation();
	    	   Thread.sleep(5000);
	    	   oHardwareAssociation.changeAssociation(CustomerNumber,"Change",Connection_Number,"");
	    	   Thread.sleep(50000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   Thread.sleep(5000);
	    	   oNavigate.toRetracking();
	    	   oRetracking.RetrackingScreen(CustomerNumber, "UniqueId",1, "Y", "Save");
	    	   Thread.sleep(20000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID in (17,18)   order by 1 desc ";
	    	   this.records=oDBConnection.fecthRecords(Status);
	    	   this.record=this.records.get(0);
	    	   String Qstatus=record.get("STATUS");
	    	   System.out.println(Qstatus);
	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	   {
	    		   System.out.println("PR-4077 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4077 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4077",driver);
	    	   System.out.println("Test__PR-4077___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
	
		
		@Test(description="Retracking Services after Change Agreement ")
	    public void PR_4078() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4078",this);
	    	   System.out.println("PR-4078 Started");
	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	   Thread.sleep(10000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   Thread.sleep(3000);
	    	   oNavigate.toServiceOrder();
	    	   oWaitTool.implicitWait(100);
	    	   driver.findElement(locModify).click();
	    	   Thread.sleep(7000);
	    	   driver.findElement(locApprove).click();
	    	   Thread.sleep(1000);
	    	   driver.findElement(By.id("ctl00_uxPgCPH_grdproductpricedtls_ctl02_imgbtnremove1")).click();
	    	   Thread.sleep(5000);
	    	   driver.findElement(locPlanSearchTab).sendKeys("NAGRA150"+Keys.TAB);
	    	   Thread.sleep(1000);
	    	   driver.findElement(locAddToCart).click();
	    	   Thread.sleep(1000);
	    	   driver.findElement(locSaveBtn).click();
	    	   Thread.sleep(5000);
	    	   String Succ_Msg = driver.findElement(locsuccessmsg).getText();
	    	   Thread.sleep(3000);
	    	   System.out.println("Success message is:"+Succ_Msg);
	    	  
	    	   Thread.sleep(20000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	   oNavigate.toRetracking();
	    	   oRetracking.RetrackingScreen(CustomerNumber, "UniqueId",1, "Y", "Save");
	    	   Thread.sleep(80000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID in (17,18)   order by 1 desc ";
	    	   this.records=oDBConnection.fecthRecords(Status);
	    	   this.record=this.records.get(0);
	    	   String Qstatus=record.get("STATUS");
	    	   System.out.println(Qstatus);
	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	   {
	    		   System.out.println("PR-4078 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4078 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4078",driver);
	    	   System.out.println("Test__PR-4078___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
	
		
		@Test(description="Retracking services for Customer having Custom Number ")
	    public void PR_4079() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4079",this);
	    	   System.out.println("PR-4079 Started");
	    	   invTrans.profile("Auto Generate Customer Number", "false","N");
	    	   relogin();
	    	   Thread.sleep(2000);
	    	   oNavigate.toCustomerRegistration();
	    	   Thread.sleep(2000);
	    	   CustomerNumber = "CST"+randomAlphaNumeric(3);
	    	   oCustomerRegistration.CustomCustomerRegisteration(CustomerNumber,first_Name,last_Name,"","");
	    	   System.out.println("Customer number is : "+CustomerNumber); 
	    	   Thread.sleep(3000);
	    	   System.out.println("entered");
	    	   Thread.sleep(3000);
	
	    	   oNavigate.toOwnedHardware();
	    	   String SC = "PROVSC"+randomAlphaNumeric(8);
	    	   String STB = "PROVSTB"+randomAlphaNumeric(8);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item1,SC,"","","",1);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item2,STB,"","","",2);
	    	   driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
	    	   oWaitTool.implicitWait(50);
	    	   
	    	   oNavigate.toServiceOrder();
	    	   oWaitTool.implicitWait(100);
	    	   String sResult = oserviceorder.BookPlan(CustomerNumber,"","","",strPlanCode,"Y",billing_Frequency);
	    	   System.out.println("is:"+sResult);
	    	   Thread.sleep(5000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   oNavigate.toRetracking();
	    	   System.out.println("nagaveted to retracking screen");
	    	   oRetracking.RetrackingScreen(CustomerNumber, "UniqueId",1, "Y", "Save");
	    	   Thread.sleep(80000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID in (17,18)   order by 1 desc ";
	    	   this.records=oDBConnection.fecthRecords(Status);
	    	   this.record=this.records.get(0);
	    	   String Qstatus=record.get("STATUS");
	    	   System.out.println(Qstatus);
	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	   {
	    		   System.out.println("PR-4079 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4079 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4079",driver);
	    	   System.out.println("Test__PR-4079___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	       finally 
	       {
	    	   invTrans.profile("Auto Generate Customer Number", "true","N");  
	    	   relogin();
	       }
	     }
	
	
		
		@Test(description="Retracking Customer who is in pending ")
	    public void PR_4080() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4080",this);
	    	   System.out.println("PR-4080 Started");
	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	   
	    	   oNavigate.toRetracking();
	    	   oRetracking.RetrackingScreen(CustomerNumber, "UniqueId",1, "Y", "Save");
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String getRetrackCount = "select COUNT(*) as COUNT from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID in (17,18)   order by 1 desc ";
	    	   this.records=oDBConnection.fecthRecords(getRetrackCount);
	    	   this.record=this.records.get(0);
	    	   Retrack_Count=record.get("COUNT");
	    	   RetrackCount = Integer.parseInt(Retrack_Count);
	    	   System.out.println("Retrack Count is "+RetrackCount);
	    	   if(RetrackCount==0) 
	    	   {
	    		   System.out.println("PR-4080 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4080 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4080",driver);
	    	   System.out.println("Test__PR-4080___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
		
		
		@Test(description="Retracking customer having Hardware plan and Service plan in different contracts ")
		public void PR_4082() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-4082",this);
				System.out.println("PR-4082 Started");
				System.out.println("entered");
				Thread.sleep(2000);
				oNavigate.toCustomerRegistration();
				Thread.sleep(2000);
				CustomerNumber = oCustomerRegistration.NewCustomerRegistration(first_Name,last_Name,"","");
				System.out.println("Customer number is : "+CustomerNumber); 
				Thread.sleep(3000);
				System.out.println("entered");
				Thread.sleep(3000);
	
				mrnNumber=invTrans.mrnTrans("","1",Inventory_Location,Supplier,currentDate,"N",1,"Y","N","Y");
				System.out.println("Mrn Completed");
				Thread.sleep(5000);
				oNavigate.toServiceOrder();
				oWaitTool.implicitWait(100);
				Thread.sleep(1000);
				driver.findElement(locApprove).click();
				Thread.sleep(1000);
				oserviceorder.serviceOrder_MulitplePlans(MultiRoomplan,"","",hardwarePlan,1);
				Thread.sleep(3000);
				driver.findElement(By.id("ctl00_uxPgCPH_CheckHw")).click();     
	
				String Stb_SERIAL_NUMBER="select SERIAL_NUMBER from item_serial_dtl where item_id=19 and mrn_id in (select mrn_id from mtl_receipt_note where mrn_number = '"+mrnNumber+"')";										
				this.records=oDBConnection.fecthRecords(Stb_SERIAL_NUMBER);					
				this.record=this.records.get(0);					
				String SERIAL_NUMBER=this.record.get("SERIAL_NUMBER");
				System.out.println(SERIAL_NUMBER +"the value of SERIAL_NUMBER is");     					  		
				Thread.sleep(3000);
				driver.findElement(By.id("ctl00_uxPgCPH_GridHwDetail_ctl02_slnodl_ctl00_txtLeaseslno")).sendKeys(SERIAL_NUMBER+Keys.TAB);
				Thread.sleep(1000);
				System.out.println("End  of Set top box");
	
				String Sc_SERIAL_NUMBER="select SERIAL_NUMBER from item_serial_dtl where item_id=16 and mrn_id in (select mrn_id from mtl_receipt_note where mrn_number = '"+mrnNumber+"')";										
				this.records=oDBConnection.fecthRecords(Sc_SERIAL_NUMBER);					
				this.record=this.records.get(0);					
				String SERIAL_NUMBER1=this.record.get("SERIAL_NUMBER");
				System.out.println(SERIAL_NUMBER1 +"the value of SERIAL_NUMBER is");     					  		
				Thread.sleep(3000);
				driver.findElement(By.id("ctl00_uxPgCPH_GridHwDetail_ctl03_slnodl_ctl00_txtLeaseslno")).sendKeys(SERIAL_NUMBER1+Keys.TAB);
				Thread.sleep(1000);
				System.out.println("End  of Smart Card");
				driver.findElement(locSaveBtn).click();	        	  
				Thread.sleep(7000);
	
				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
				Thread.sleep(3000);
				oNavigate.toRetracking();
				oRetracking.RetrackingScreen(CustomerNumber, "UniqueId",1, "Y", "Save");
				Thread.sleep(80000);
				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
				this.records=oDBConnection.fecthRecords(Customer_id);
				this.record=this.records.get(0);
				String Party_id=record.get("CUSTOMER_ID");
				System.out.println(Party_id);
				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID in (17,18)   order by 1 desc ";
				this.records=oDBConnection.fecthRecords(Status);
				this.record=this.records.get(0);
				String Qstatus=record.get("STATUS");
				System.out.println(Qstatus);
				if(Qstatus.equalsIgnoreCase(C_Status))
				{
					System.out.println("PR-4082 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
				}
				else
				{
					System.out.println("PR-4082 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Status is not active and success message is not displaying");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-4082",driver);
				System.out.println("Test__PR-4082___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}
	
		
			
		@Test(description="Retracking Customer having Multi room contract")
	    public void PR_4083() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4083",this);
	    	   System.out.println("PR-4083 Started");
	    	   Thread.sleep(2000);
	    	   oNavigate.toCustomerRegistration();
	    	   Thread.sleep(2000);
	    	   CustomerNumber = oCustomerRegistration.NewCustomerRegistration(first_Name,last_Name,"","");
	    	   System.out.println("Customer number is : "+CustomerNumber); 
	    	   Thread.sleep(3000);
	
	    	   oNavigate.toOwnedHardware();
	    	   String SC ="PRO"+randomAlphaNumeric(8);
	    	   String STB ="PRO"+randomAlphaNumeric(8);
	    	   String SC1 ="PRO"+randomAlphaNumeric(8);
	    	   String STB1 ="PRO"+randomAlphaNumeric(8);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item1,SC,"","","",1);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item2,STB,"","","",2);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item1,SC1,"","","",3);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item2,STB1,"","","",4);
	    	   driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
	    	   oWaitTool.implicitWait(50);
	    	   oNavigate.toServiceOrder();
	    	   oWaitTool.implicitWait(100);
	    	   driver.findElement(locApprove).click();
	    	   Thread.sleep(1000);
	    	   
	    	   driver.findElement(locNoOfRooms).clear();
	    	   Thread.sleep(2000);
	    	   driver.findElement(locNoOfRooms).sendKeys("2"+Keys.TAB);
	    	   Thread.sleep(3000);
	    	   oserviceorder.BookPlan("","","",contract_validity,MultiRoomplan,"","");
	    	   Thread.sleep(3000);
	    	   String SROutput=driver.findElement(By.id("ctl00__infoSpan")).getText();
	    	   Thread.sleep(2000);
	    	   System.out.println("validation msg= "+SROutput);
	    	   Thread.sleep(5000);
	    	
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	   Thread.sleep(5000);
	    	   oNavigate.toRetracking();
	    	   Thread.sleep(5000);
	    	   oRetracking.RetrackingScreen(CustomerNumber, "UniqueId",1, "Y", "Save");
	    	   Thread.sleep(80000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	   
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID in (17,18)   order by 1 desc ";
	    	   this.records=oDBConnection.fecthRecords(Status);
	    	   this.record=this.records.get(0);
	    	   String Qstatus=record.get("STATUS");
	    	   System.out.println(Qstatus);
	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	   {
	    		   System.out.println("PR-4083 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4083 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4083",driver);
	    	   System.out.println("Test__PR-4083___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
	
		
		@Test(description="Retracking customer having contract with is_bulk as Y")
	    public void PR_4084() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4084",this);
	    	   System.out.println("PR-4084 Started");
	    	   Thread.sleep(2000);
	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation_WithBulk();
	    	   System.out.println("Customer number is : "+CustomerNumber); 
	    	   Thread.sleep(3000);
	    	  
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String ContractId = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=1  order by 1 desc ";
	    	   this.records=oDBConnection.fecthRecords(ContractId);
	    	   this.record=this.records.get(0);
	    	   String ContractNumber=record.get("CONTRACT_ID");
	    	   System.out.println("ContractNumber is :"+ContractNumber);
	
	    	   Thread.sleep(80000);
	    	   System.out.println("waiting is completed and now going  to provisiong request screen ");
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	   Thread.sleep(3000);
	  
	    	   oNavigate.toRetracking();
	    	   oRetracking.RetrackingScreen(CustomerNumber, "UniqueId",1, "Y", "Save");
	    	   Thread.sleep(80000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	  
	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID in (17,18)   order by 1 desc ";
	    	   this.records=oDBConnection.fecthRecords(Status);
	    	   this.record=this.records.get(0);
	    	   String Qstatus=record.get("STATUS");
	    	   System.out.println("status of the request is :"+Qstatus+""); 
	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	   {
	    		   System.out.println("PR-4084 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4084 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4084",driver);
	    	   System.out.println("Test__PR-4084___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
	
		
		@Test(description="Retracking Customer having Multiple contracts on Single card ")
	    public void PR_4085() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4085",this);
	    	   System.out.println("PR-4085 Started");
	    	   Thread.sleep(2000);
	    	   oNavigate.toCustomerRegistration();
	    	   Thread.sleep(2000);
	
	    	   CustomerNumber = oCustomerRegistration.NewCustomerRegistration(first_Name,last_Name,"","");
	    	   System.out.println("Customer number is : "+CustomerNumber); 
	    	   Thread.sleep(3000);
	    	   System.out.println("entered");
	    	   Thread.sleep(3000);
	
	    	   oNavigate.toOwnedHardware();
	    	   String SC = "PROVSC"+randomAlphaNumeric(8);
	    	   String STB = "PROVSTB"+randomAlphaNumeric(8);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item1,SC,"","","",1);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item2,STB,"","","",2);
	    	   driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
	    	   oWaitTool.implicitWait(50);
	
	    	   oNavigate.toServiceOrder();
	    	   oWaitTool.implicitWait(100);
	    	   driver.findElement(locApprove).click();
	    	   Thread.sleep(2000);
	    	   oserviceorder.serviceOrder_MulitplePlans("PLAN1SERV1","","","",services_count);
	    	   Thread.sleep(4000);
	    	   oserviceorder.serviceOrder_MulitplePlans("NAGRA150","","","",services_count);
	    	   System.out.println("Out of method");
	    	   driver.findElement(locSaveBtn).click();
	
	    	   Thread.sleep(2000);
	    	   oNavigate.toHardwareAssociation();
	    	   Thread.sleep(2000);
	    	   oHardwareAssociation.NonMultiConnection(CustomerNumber, "MultipleContractWithSameHardware");
	
	    	   Thread.sleep(60000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	   Thread.sleep(6000);
	
	    	   oNavigate.toRetracking();
	    	   oRetracking.RetrackingScreen(CustomerNumber, "UniqueId",1, "Y", "Save");
	    	   Thread.sleep(80000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID in (17,18)   order by 1 desc ";
	    	   this.records=oDBConnection.fecthRecords(Status);
	    	   this.record=this.records.get(0);
	    	   String Qstatus=record.get("STATUS");
	    	   System.out.println("status of the request is :"+Qstatus+""); 
	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	   {
	    		   System.out.println("PR-4085 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4085 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4085",driver);
	    	   System.out.println("Test__PR-4085___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
	
		
		@Test(description=" Retracking Customer having Multiple contracts on multiple hardware items")
	    public void PR_4086() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4086",this);
	    	   System.out.println("PR-4086 Started");
	    	   Thread.sleep(2000);
	    	   oNavigate.toCustomerRegistration();
	    	   Thread.sleep(2000);
	
	    	   CustomerNumber = oCustomerRegistration.NewCustomerRegistration(first_Name,last_Name,"","");
	    	   System.out.println("Customer number is : "+CustomerNumber); 
	    	   Thread.sleep(3000);
	    	   System.out.println("entered");
	    	   Thread.sleep(3000);
	
	    	   oNavigate.toOwnedHardware();
	    	   String SC = "PRO"+randomAlphaNumeric(8);
	    	   String STB = "PRO"+randomAlphaNumeric(8);
	    	   String SC1 = "PRO"+randomAlphaNumeric(8);
	    	   String STB1 = "PRO"+randomAlphaNumeric(8);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item1,SC,"","","",1);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item2,STB,"","","",2);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item1,SC1,"","","",3);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item2,STB1,"","","",4);
	    	   driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
	    	   oWaitTool.implicitWait(50);
	
	    	   oNavigate.toServiceOrder();
	    	   oWaitTool.implicitWait(100);
	    	   driver.findElement(locApprove).click();
	    	   Thread.sleep(4000);
	    	   oserviceorder.serviceOrder_MulitplePlans("PLAN1SERV1","","","",services_count);
	    	   Thread.sleep(4000);
	    	   oserviceorder.serviceOrder_MulitplePlans("NAGRA150","","","",services_count);
	    	   System.out.println("Out of multiple plan's method");
	    	   driver.findElement(locSaveBtn).click();
	
	    	   Thread.sleep(70000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	   Thread.sleep(3000);
	
	    	   oNavigate.toRetracking();
	    	   oRetracking.RetrackingScreen(CustomerNumber, "UniqueId",1, "Y", "Save");
	    	   Thread.sleep(80000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID in (17,18)   order by 1 desc ";
	    	   this.records=oDBConnection.fecthRecords(Status);
	    	   this.record=this.records.get(0);
	    	   String Qstatus=record.get("STATUS");
	    	   System.out.println(Qstatus);
	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	   {
	    		   System.out.println("PR-4086 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4086 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4086",driver);
	    	   System.out.println("Test__PR-4086___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
	
		
		@Test(description="Retracking for Downgraded customer")
	    public void PR_4464() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4464",this);
	    	   System.out.println("PR-4464 Started");
	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	   Thread.sleep(2000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   oNavigate.toCollection();
	    	   oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
	    	   Thread.sleep(5000);
	    	   oCollections.collectionGrid("", "","Service Downgrade", "", "Save");
	    	   Thread.sleep(70000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String getCount1="select COUNT(DISTINCT(UNIQUE_ID)) from PROV_SVCDWNGRD_ENTITLEMENT where ACTIVATE_STATUS in ('N','C')   AND   PARTY_ID='"+Party_id+"' AND DISCONNECT_STATUS IS NULL";
	    	   this.records=oDBConnection.fecthRecords(getCount1);
	    	   this.record=this.records.get(0);
	    	   String count1=record.get("COUNT(DISTINCT(UNIQUE_ID))");
	    	   int DB_UniqueId_Count = Integer.parseInt(count1);
	    	   System.out.println("count1 is "+DB_UniqueId_Count);
	    	   if(DB_UniqueId_Count>0)      
	    	   {
	    		   System.out.println("PR-4464 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4464 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4464",driver);
	    	   System.out.println("Test__PR-4464___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
	
		
		@Test(description="Retracking for Downgraded customer having two contracts")
	    public void PR_4465() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4465",this);
	    	   System.out.println("PR-4465 Started");
	    	   Thread.sleep(5000);
	    	   oNavigate.toCustomerRegistration();
	    	   Thread.sleep(2000);
	
	    	   CustomerNumber = oCustomerRegistration.NewCustomerRegistration(first_Name,last_Name,"","");
	    	   System.out.println("Customer number is : "+CustomerNumber); 
	    	   Thread.sleep(3000);
	    	   System.out.println("entered");
	    	   Thread.sleep(3000);
	
	    	   oNavigate.toOwnedHardware();
	    	   String SC = "PROVSC"+randomAlphaNumeric(8);
	    	   String STB = "PROVSTB"+randomAlphaNumeric(8);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item1,SC,"","","",1);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item2,STB,"","","",2);
	    	   driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
	    	   oWaitTool.implicitWait(50);
	
	    	   oNavigate.toServiceOrder();
	    	   oWaitTool.implicitWait(100);
	    	   driver.findElement(locApprove).click();
	    	   Thread.sleep(2000);
	    	   oserviceorder.serviceOrder_MulitplePlans("PLAN1SERV1","","","",services_count);
	    	   Thread.sleep(4000);
	    	   oserviceorder.serviceOrder_MulitplePlans("NAGRA150","","","",services_count);
	    	   System.out.println("Out of method");
	    	   driver.findElement(locSaveBtn).click();
	
	    	   Thread.sleep(2000);
	    	   oNavigate.toHardwareAssociation();
	    	   Thread.sleep(2000);
	    	   oHardwareAssociation.NonMultiConnection(CustomerNumber, "MultipleContractWithSameHardware");
	
	    	   Thread.sleep(60000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	   Thread.sleep(6000);
	
	    	   oNavigate.toCollection();
	    	   oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
	    	   Thread.sleep(5000);
	    	   oCollections.collectionGrid("", "","Service Downgrade", "", "Save");
	    	   Thread.sleep(70000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String getCount1="select COUNT(DISTINCT(UNIQUE_ID)) from PROV_SVCDWNGRD_ENTITLEMENT where ACTIVATE_STATUS in ('N','C')   AND   PARTY_ID='"+Party_id+"' AND DISCONNECT_STATUS IS NULL";
	    	   this.records=oDBConnection.fecthRecords(getCount1);
	    	   this.record=this.records.get(0);
	    	   String count1=record.get("COUNT(DISTINCT(UNIQUE_ID))");
	    	   int DB_UniqueId_Count = Integer.parseInt(count1);
	    	   System.out.println("count1 is "+DB_UniqueId_Count);
	    	   if(DB_UniqueId_Count>0)     
	    	   {
	    		   System.out.println("PR-4465 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4465 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4465",driver);
	    	   System.out.println("Test__PR-4465___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
	
		
		
	}
