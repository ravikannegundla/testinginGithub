package ProvisioningTC;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.BasicConfigurationInventory.InventoryTransactions;
import com.BasicConfigurationsOfServiceOrder.BasicConfigurationsOfServiceOrder;
import com.BasicConfigurationsOfServiceOrder.OrderManagementPrivileges;
import com.InventoryAndLogisticsPageObject.HardwareReplacement;
import com.InventoryAndLogisticsPageObject.OwnedHardware;
import com.JobsPageObject.ScheduleJob;
import com.MulticonnectionPageObject.Multiconnection;
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

	public class Prov_Modify_Association_TC extends MQProvisioning
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
	    Renewal oRenewal;
	    BasicConfigurationsOfServiceOrder oBasicConfigurationsOfServiceOrder;
	    Multiconnection oMulticonnection;
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
	
	    
	    public List<WebElement> listOfRows;
	    public ArrayList<Hashtable<String, String>> records;
	    public Hashtable<String,String> record;
	    public MQDBConnection oDBConnection;
	    String strPlanCode = "PLAN1SERV1";
	    String billing_Frequency = "One Month";
	    String contract_validity = "One Month";
	
	    String first_Name = "Ravi";
	    String last_Name = "Kannegundla";
	
	    String renewalBackdated="09-03-2019";
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
	    String item3="Cable";
	    int services_count=1;
	    String MultiRoomplan="NAGRA150";
	    String hardwarePlan="HWPLAN1111";
	    String Reason="BADPAYER";
	    String ReasonMRPL="BAD";
	    public String ContractNumber;
	    String OperationalEntity="CORP";
	    public String OrderType = "One Time Sale Order";
	    String RenewalValidation;
	
	    public     String ScreenValidation1,Sent="Sent",Accept="Accept",New="New";
	    
	    String sResult,Result,sRes;
	    String sQuery,CustomerNumber;
	    String getConnectionNumber,Connection_Number;
	
	    
	    	
		@Override
	    protected void beforeClass() 
	    {
	   	 driver=getDriver();
	   	 driver.manage().window().maximize();
	   	 this.oCustomerRegistration = new CustomerRegistration(driver);
	   	 this.oserviceorder=new ServiceOrder(driver,dbConnection);
	   	 this.oProvisioningRequests =new ProvisioningRequests(driver);
	   	 this.oBasicConfigurationsOfServiceOrder=new BasicConfigurationsOfServiceOrder(driver, oDBConnection);
	   	 this.oOwnedHardware=new OwnedHardware(driver, oDBConnection);
	   	 this.oHardwareAssociation=new HardwareAssociation(driver,dbConnection);
	   	 this.oMulticonnection=new  Multiconnection(driver);
	   	 this.oBasicConfigurationsOfProvisioning=new BasicConfigurationsOfProvisioning(driver,dbConnection);
	   	 this.oDisconnection = new Disconnection(driver);
	   	 this.oReconnection = new Reconnection(driver);
	     this.invTrans=new InventoryTransactions(driver, dbConnection);
	   	 this.oRenewal = new Renewal(driver);
	   	 this.oRenewal = new Renewal(driver,dbConnection);
	   	 this.oRetracking=new Retracking(driver,dbConnection);
	   	 this.oCollections=new Collections(driver,dbConnection);
	   	 this.oOrderManagementPrivileges=new OrderManagementPrivileges(dbConnection);
    	 
    	 
    	 oBasicConfigurationsOfProvisioning.OwnHardware_Privileges();
    	 oOrderManagementPrivileges.Disconnection_privileges();
    	 oOrderManagementPrivileges.Reconnection_privileges();
//    	 oOrderManagementPrivileges.ServiceOrder_privileges();
	   	 
	   	 
	   	 
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
			 //verifyLogin("KRAVI","KRAVI123");
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
		
		
	    
		protected Prov_Modify_Association_TC(String sLogFileName) throws Exception 
		{
			super(sLogFileName);
			 this.oDBConnection=dbConnection;
			 PageFactory.initElements(driver, this);
		}
	
		
		
	
		
		
	// Need to Handle Profile Option..
		@Test(description="Associating Hardware for Single Contract ")
		public void PR_4089() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-4089",this);
				System.out.println("PR-4089 Started");
				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
				System.out.println("Contract Number is : "+ContractNumber);
				Thread.sleep(10000);
				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
				getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
				this.records=oDBConnection.fecthRecords(getConnectionNumber);
				this.record=this.records.get(0);
				Connection_Number=record.get("CONNECTION_NUMBER");
				System.out.println("connection Number is "+Connection_Number);
				Thread.sleep(2000);
				oNavigate.toHardwareAssociation();
				Thread.sleep(5000);
				oHardwareAssociation.changeAssociation(CustomerNumber,"ChangeWithSingle",Connection_Number,"Smart Card");  	
	
				Thread.sleep(70000);
				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
				
				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
				this.records=oDBConnection.fecthRecords(Customer_id);
				this.record=this.records.get(0);
				String Party_id=record.get("CUSTOMER_ID");
				System.out.println(Party_id);
				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=21  order by 1 desc ";
				this.records=oDBConnection.fecthRecords(Status);
				this.record=this.records.get(0);
				String Qstatus=record.get("STATUS");
				System.out.println(Qstatus);
				if(Qstatus.equalsIgnoreCase(C_Status))
				{
					System.out.println("PR-4089 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
				}
				else
				{
					System.out.println("PR-4089 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Status is not active and success message is not displaying");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-4089",driver);
				System.out.println("Test__PR-4089___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}
	
	
		
	// Need to handle the profile Option
		@Test(description="Associating Hardware for Multiple contracts.")
	    public void PR_4090() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4090",this);
	    	   System.out.println("PR-4090 Started");
	    	   System.out.println("entered");
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
	    	   
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=85 order by 1 desc ";
	    	   this.records=oDBConnection.fecthRecords(Status);
	    	   this.record=this.records.get(0);
	    	   String Qstatus=record.get("STATUS");
	    	   System.out.println("status of the request is :"+Qstatus+""); 
	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	   {
	    		   System.out.println("PR-4090 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4090 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4090",driver);
	    	   System.out.println("Test__PR-4090___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
		
		
	// Need to handle the code
		@Test(description="Associating Hardware for Multi Room contract ")
	    public void PR_4091() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4091",this);
	    	   System.out.println("PR-4091 Started");
	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	   Thread.sleep(10000);
	
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	   getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	    	   this.records=oDBConnection.fecthRecords(getConnectionNumber);
	    	   this.record=this.records.get(0);
	    	   Connection_Number=record.get("CONNECTION_NUMBER");
	    	   System.out.println("connection Number is "+Connection_Number);
	    	   Thread.sleep(2000);
	    	   oNavigate.toHardwareAssociation();
	    	   Thread.sleep(5000);
	
	    	   //    	   oHardwareAssociation.changeAssociation(CustomerNumber,"ChangeWithSingle",Connection_Number,"Smart Card");  	
	
	    	   int k;
	    	   WebElement table1 = driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody"));	
	    	   listOfRows = table1.findElements(By.tagName("img"));
	    	   System.out.println("Rows: "+listOfRows.size());	
	    	   for(int j =1;j<=listOfRows.size();j++)
	    	   {
	    		   k=j+1;
	    		   System.out.println("start k value" +k+" value of j : "+j);
	    		   Thread.sleep(2000);
	    		   driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody/tr["+j+"]/td[4]/a/img")).click();				
	    		   new Select(driver.findElement(By.xpath("//*[@id='ctl00_uxPgCPH_grdvAssociate_ctl0"+k+"_cmbunique']"))).selectByIndex(1);
	    		   driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody/tr["+j+"]/td[4]/a/img")).click();
	    		   Thread.sleep(1000);
	    	   }
	    	   driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_btnsave']")).click();
	
	    	   Thread.sleep(70000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=1  order by 1 desc ";
	    	   this.records=oDBConnection.fecthRecords(Status);
	    	   this.record=this.records.get(0);
	    	   String Qstatus=record.get("STATUS");
	    	   System.out.println(Qstatus);
	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	   {
	    		   System.out.println("PR-4091 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4091 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4091",driver);
	    	   System.out.println("Test__PR-4091___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
	
		
		
		@Test(description="Associating Hardware for a contract with Is_Bulk as Y")
	    public void PR_4092() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4092",this);
	    	   System.out.println("PR-4092 Started");
	    	   Thread.sleep(2000);
	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation_WithBulk();
	    	   System.out.println("Customer number is : "+CustomerNumber); 
	
	    	   Thread.sleep(80000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	   Thread.sleep(3000);
	    	   
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=1 order by 1 desc ";
	    	   this.records=oDBConnection.fecthRecords(Status);
	    	   this.record=this.records.get(0);
	    	   String Qstatus=record.get("STATUS");
	    	   System.out.println("status of the request is :"+Qstatus+""); 
	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	   {
	    		   System.out.println("PR-4092 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4092 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4092",driver);
	    	   System.out.println("Test__PR-4092___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
		
	
		@Test(description="Modify Association after Change Agreement ")
	    public void PR_4094() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4094",this);
	    	   System.out.println("PR-4094 Started");
	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	   Thread.sleep(10000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   Thread.sleep(3000);
	    	   oNavigate.toServiceOrder();
	    	   oWaitTool.implicitWait(100);
	    	   Thread.sleep(2000);
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
	
	    	   Thread.sleep(60000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	    	   this.records=oDBConnection.fecthRecords(getConnectionNumber);
	    	   this.record=this.records.get(0);
	    	   Connection_Number=record.get("CONNECTION_NUMBER");
	    	   System.out.println("connection Number is "+Connection_Number);
	    	   Thread.sleep(2000);
	    	   oNavigate.toHardwareAssociation();
	    	   Thread.sleep(5000);
	    	   oHardwareAssociation.changeAssociation(CustomerNumber,"ChangeWithSingle",Connection_Number,"Smart Card");  	
	
	    	   Thread.sleep(70000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=21  order by 1 desc ";
	    	   this.records=oDBConnection.fecthRecords(Status);
	    	   this.record=this.records.get(0);
	    	   String Qstatus=record.get("STATUS");
	    	   System.out.println(Qstatus);
	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	   {
	    		   System.out.println("PR-4094 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes("Customer is Actvated with out hardware");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4094 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4094",driver);
	    	   System.out.println("Test__PR-4094___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
		
		
		@Test(description="Modify Association after Disconnection ")
	    public void PR_4095() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4095",this);
	    	   System.out.println("PR-4095 Started");
	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	   Thread.sleep(25000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	   
	    	   Thread.sleep(3000);
	    	   oNavigate.toDisconnection();
	    	   Thread.sleep(5000);
	    	   logger.info("Successfully Navigated to Disconnection Screen ");
	    	   sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	   System.out.println("Success message is:"+success_Msg);
	    	   Thread.sleep(5000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	   Thread.sleep(3000);
	    	  
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String getCount="select COUNT(DISTINCT UNIQUE_ID) as COUNT from PROVSION_ENTITLEMENT where PARTY_ID='"+Party_id+"' AND DISCONNECT_STATUS IS NOT NULL";
	    	   
	    	   this.records=oDBConnection.fecthRecords(getCount);
	    	   this.record=this.records.get(0);
	    	   String Disconnect_Count=record.get("COUNT");
	    	   System.out.println("count is "+Disconnect_Count);
	    	   int Disconnect_UniqueId_Count = Integer.parseInt(Disconnect_Count);
	    	   System.out.println("count is "+Disconnect_UniqueId_Count);
	    	   if(Disconnect_UniqueId_Count>0)
	    	   {
	    		   System.out.println("PR-4095 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4095 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4095",driver);
	    	   System.out.println("Test__PR-4095___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
	
	
		@Test(description="Modify Association after Reconnection ")
	    public void PR_4096() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4096",this);
	    	   System.out.println("PR-4096 Started");
	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	   Thread.sleep(10000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   Thread.sleep(3000);
	    	   oNavigate.toDisconnection();
	    	   Thread.sleep(5000);
	    	   logger.info("Successfully Navigated to Disconnection Screen ");
	    	   sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	   System.out.println("Success message is:"+success_Msg);
	    	   Thread.sleep(5000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	   Thread.sleep(3000);
	
	    	   oNavigate.toReconnection();
	    	   oWaitTool.implicitWait(10);
	    	   oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
	    	   oWaitTool.implicitWait(50);
	    	   String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	   System.out.println("Success message is:"+success_Msg1);
	    	   System.out.println("Reconnection is done");
	    	   Thread.sleep(5000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	    	   this.records=oDBConnection.fecthRecords(getConnectionNumber);
	    	   this.record=this.records.get(0);
	    	   Connection_Number=record.get("CONNECTION_NUMBER");
	    	   System.out.println("connection Number is "+Connection_Number);
	    	   Thread.sleep(2000);
	    	   oNavigate.toHardwareAssociation();
	    	   Thread.sleep(5000);
	    	   oHardwareAssociation.changeAssociation(CustomerNumber,"ChangeWithSingle",Connection_Number,"Smart Card");  	
	    	   Thread.sleep(70000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=21  order by 1 desc ";
	    	   this.records=oDBConnection.fecthRecords(Status);
	    	   this.record=this.records.get(0);
	    	   String Qstatus=record.get("STATUS");
	    	   System.out.println(Qstatus);
	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	   {
	    		   System.out.println("PR-4096 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4096 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4096",driver);
	    	   System.out.println("Test__PR-4096___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
	
	
		@Test(description="Modify Association for a suspended customer")
	    public void PR_4097() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4097",this);
	    	   System.out.println("PR-4097 Started");
	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	   Thread.sleep(10000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   oNavigate.toCollection();
	    	   oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
	    	   Thread.sleep(5000);
	    	   oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
	    	   Thread.sleep(90000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String getCount="select COUNT(DISTINCT(UNIQUE_ID)) as COUNT from PROVSION_SUSPEND_ENTITLEMENT where suspend_STATUS in ('N','C')  AND   PARTY_ID='"+Party_id+"' AND RECONNECT_STATUS IS NULL";
	    	   this.records=oDBConnection.fecthRecords(getCount);
	    	   this.record=this.records.get(0);
	    	   String Suspend_Count=record.get("COUNT");
	    	   int Suspended_UniqueId_Count = Integer.parseInt(Suspend_Count);
	    	   System.out.println("count is "+Suspended_UniqueId_Count);
	    	   if(Suspended_UniqueId_Count>0)
	    	   {
	    		   System.out.println("PR-4097 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4097 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4097",driver);
	    	   System.out.println("Test__PR-4097___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
	
		
		@Test(description="Modify Association for Reactivated customer ")
	    public void PR_4098() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4098",this);
	    	   System.out.println("PR-4098 Started");
	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	   Thread.sleep(10000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   oNavigate.toCollection();
	    	   oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
	    	   Thread.sleep(5000);
	    	   oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
	    	   Thread.sleep(90000);
	    	   Thread.sleep(10000);
	
	    	   oNavigate.toCollection();
	    	   oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
	    	   Thread.sleep(5000);
	    	   oCollections.collectionGrid("", "","Active", "", "Save");
	    	   Thread.sleep(90000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	    	   this.records=oDBConnection.fecthRecords(getConnectionNumber);
	    	   this.record=this.records.get(0);
	    	   Connection_Number=record.get("CONNECTION_NUMBER");
	    	   System.out.println("connection Number is "+Connection_Number);
	    	   Thread.sleep(2000);
	    	   oNavigate.toHardwareAssociation();
	    	   Thread.sleep(5000);
	    	   oHardwareAssociation.changeAssociation(CustomerNumber,"ChangeWithSingle",Connection_Number,"Smart Card");  	
	    	   Thread.sleep(70000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=21  order by 1 desc ";
	    	   this.records=oDBConnection.fecthRecords(Status);
	    	   this.record=this.records.get(0);
	    	   String Qstatus=record.get("STATUS");
	    	   System.out.println(Qstatus);
	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	   {
	    		   System.out.println("PR-4098 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4098 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4098",driver);
	    	   System.out.println("Test__PR-4098___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
	
		
		@Test(description="Modify Association for a Multi Room Contract ")
	    public void PR_4099() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4099",this);
	    	   System.out.println("PR-4099 Started");
	    	   System.out.println("entered");
	    	   Thread.sleep(2000);
	    	   oNavigate.toCustomerRegistration();
	    	   Thread.sleep(2000);
	    	   CustomerNumber = oCustomerRegistration.NewCustomerRegistration(first_Name,last_Name,"","");
	    	   System.out.println("Customer number is : "+CustomerNumber); 
	    	   Thread.sleep(3000);
	    	   System.out.println("entered");
	    	   Thread.sleep(3000);
	
	    	   oNavigate.toOwnedHardware();
	    	   String SC ="PRO"+randomAlphaNumeric(8);
	    	   String STB ="PRO"+randomAlphaNumeric(8);
	    	   String SC1 ="PRO"+randomAlphaNumeric(8);
	    	   String STB1 ="PRO"+randomAlphaNumeric(8);
	    	   String SC2 = "PROVSC"+randomAlphaNumeric(8);
	    	   String STB2 = "PROVSTB"+randomAlphaNumeric(8);
	    	   String SC3 = "PROVSC"+randomAlphaNumeric(8);
	    	   String STB3 = "PROVSTB"+randomAlphaNumeric(8);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item1,SC,"","","",1);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item2,STB,"","","",2);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item1,SC1,"","","",3);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item2,STB1,"","","",4);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item1,SC2,"","","",5);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item2,STB2,"","","",6);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item1,SC3,"","","",7);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item2,STB3,"","","",8);
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
	    	   Thread.sleep(3000);
	    	   getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	    	   this.records=oDBConnection.fecthRecords(getConnectionNumber);
	    	   this.record=this.records.get(0);
	    	   Connection_Number=record.get("CONNECTION_NUMBER");
	    	   System.out.println("connection Number is "+Connection_Number);
	    	   Thread.sleep(2000);
	    	   oNavigate.toHardwareAssociation();
	    	   Thread.sleep(5000);
	    	   oHardwareAssociation.changeAssociation(CustomerNumber,"ChangeWithSingle",Connection_Number,"Smart Card");  	
	
	    	   Thread.sleep(70000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=21  order by 1 desc ";
	    	   this.records=oDBConnection.fecthRecords(Status);
	    	   this.record=this.records.get(0);
	    	   String Qstatus=record.get("STATUS");
	    	   System.out.println(Qstatus);
	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	   {
	    		   System.out.println("PR-4099 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4099 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4099",driver);
	    	   System.out.println("Test__PR-4099___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
	
		
		@Test(description="Modify Association for contract having is_bulk as Y")
	    public void PR_4100() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4100",this);
	    	   System.out.println("PR-4100 Started");
	    	   Thread.sleep(2000);
	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation_WithBulk();
	    	   System.out.println("Customer number is : "+CustomerNumber); 
	
	    	   Thread.sleep(80000);
	    	   System.out.println("waiting is completed and now going  to provisiong request screen ");
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	   Thread.sleep(5000);
	
	    	   oNavigate.toOwnedHardware();
	    	   String SC1 = "PROsc"+randomAlphaNumeric(8);
	    	   String STB1 = "PROstb"+randomAlphaNumeric(8);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item1,SC1,"","","",3);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item2,STB1,"","","",4);
	    	   driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
	    	   oWaitTool.implicitWait(50);
	
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
	    		   new Select(driver.findElement(By.xpath("//*[@id='ctl00_uxPgCPH_grdvAssociate_ctl0"+k+"_cmbunique']"))).selectByIndex(2);//need to modify
	    		   driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody/tr["+j+"]/td[5]/a/img")).click();
	    		   Thread.sleep(2000);
	    	   }
	
	    	   Thread.sleep(4000);
	    	   driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_btnsave']")).click();
	    	   System.out.println("Hardware Association Is Done Successfully");
	
	    	   Thread.sleep(80000);
	    	   System.out.println("waiting is completed and now going  to provisiong request screen ");
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	   Thread.sleep(5000);
	
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=21 order by 1 desc ";
	    	   this.records=oDBConnection.fecthRecords(Status);
	    	   this.record=this.records.get(0);
	    	   String Qstatus=record.get("STATUS");
	    	   System.out.println("status of the request is :"+Qstatus+""); 
	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	   {
	    		   System.out.println("PR-4100 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4100 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4100",driver);
	    	   System.out.println("Test__PR-4100___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
		
	
		@Test(description="Modify Association for customer having Service plan and Hardware Plan in different contracts ")
	    public void PR_4101() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4101",this);
	    	   System.out.println("PR-4101 Started");
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
	    	   Thread.sleep(10000);
	
	    	   oNavigate.toOwnedHardware();
	    	   String SC = "PROVSC"+randomAlphaNumeric(8);
	    	   String STB = "PROVSTB"+randomAlphaNumeric(8);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item1,SC,"","","",1);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item2,STB,"","","",2);
	    	   driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
	    	   Thread.sleep(70000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	   Thread.sleep(5000);
	
	    	   getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	    	   this.records=oDBConnection.fecthRecords(getConnectionNumber);
	    	   this.record=this.records.get(0);
	    	   Connection_Number=record.get("CONNECTION_NUMBER");
	    	   System.out.println("connection Number is "+Connection_Number);
	    	   Thread.sleep(2000);
	    	   oNavigate.toHardwareAssociation();
	    	   Thread.sleep(5000);
	    	   oHardwareAssociation.changeAssociation(CustomerNumber,"ChangeWithSingle",Connection_Number,"Smart Card");  	
	    	   Thread.sleep(70000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=21  order by 1 desc ";
	    	   this.records=oDBConnection.fecthRecords(Status);
	    	   this.record=this.records.get(0);
	    	   String Qstatus=record.get("STATUS");
	    	   System.out.println(Qstatus);
	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	   {
	    		   System.out.println("PR-4101 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4101 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4101",driver);
	    	   System.out.println("Test__PR-4101___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
	
	
		@Test(description="Modify Association details of a customer having Custom Number ")
	    public void PR_4102() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4102",this);
	    	   System.out.println("PR-4102 Started");
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
	    	   String SC1 = "PROVSC"+randomAlphaNumeric(8);
	    	   String STB1 = "PROVSTB"+randomAlphaNumeric(8);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item1,SC,"","","",1);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item2,STB,"","","",2);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item1,SC1,"","","",3);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item2,STB1,"","","",4);
	    	   driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
	    	   
	    	   Thread.sleep(2000);
	    	   oWaitTool.implicitWait(50);
	    	   oNavigate.toServiceOrder();
	    	   oWaitTool.implicitWait(100);
	    	   String sResult = oserviceorder.BookPlan(CustomerNumber,"","","",strPlanCode,"Y",billing_Frequency);
	    	   System.out.println("is:"+sResult);
	    	   Thread.sleep(5000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	   
	    	   getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	    	   this.records=oDBConnection.fecthRecords(getConnectionNumber);
	    	   this.record=this.records.get(0);
	    	   Connection_Number=record.get("CONNECTION_NUMBER");
	    	   System.out.println("connection Number is "+Connection_Number);
	    	   Thread.sleep(2000);
	    	   oNavigate.toHardwareAssociation();
	    	   Thread.sleep(5000);
	    	   oHardwareAssociation.changeAssociation(CustomerNumber,"ChangeWithSingle",Connection_Number,"Smart Card");  	
	    	   Thread.sleep(70000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=21  order by 1 desc ";
	    	   this.records=oDBConnection.fecthRecords(Status);
	    	   this.record=this.records.get(0);
	    	   String Qstatus=record.get("STATUS");
	    	   System.out.println(Qstatus);
	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	   {
	    		   System.out.println("PR-4102 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4102 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4102",driver);
	    	   System.out.println("Test__PR-4102___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
	
		
		@Test(description=" Modify Association of a Renewed customer ")
	    public void PR_4103() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4103",this);
	    	   System.out.println("PR-4103 Started");
	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", renewalBackdated, renewalBackdated,contract_validity,strPlanCode,"Y",billing_Frequency);
	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	   Thread.sleep(8000);
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
	    	   
	    	   getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	    	   this.records=oDBConnection.fecthRecords(getConnectionNumber);
	    	   this.record=this.records.get(0);
	    	   Connection_Number=record.get("CONNECTION_NUMBER");
	    	   System.out.println("connection Number is "+Connection_Number);
	    	   Thread.sleep(2000);
	    	   oNavigate.toHardwareAssociation();
	    	   Thread.sleep(5000);
	    	   oHardwareAssociation.changeAssociation(CustomerNumber,"ChangeWithSingle",Connection_Number,"Smart Card");  	
	    	   	
	    	   Thread.sleep(70000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=21  order by 1 desc ";
	    	   this.records=oDBConnection.fecthRecords(Status);
	    	   this.record=this.records.get(0);
	    	   String Qstatus=record.get("STATUS");
	    	   System.out.println(Qstatus);
	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	   {
	    		   System.out.println("PR-4103 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4103 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4103",driver);
	    	   System.out.println("Test__PR-4103___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
			
	
		@Test(description="Modify Association of a customer who is not active")
	    public void PR_4104() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4104",this);
	    	   System.out.println("PR-4104 Started");
	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	   Thread.sleep(5000);
	
	    	   getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	    	   this.records=oDBConnection.fecthRecords(getConnectionNumber);
	    	   this.record=this.records.get(0);
	    	   Connection_Number=record.get("CONNECTION_NUMBER");
	    	   System.out.println("connection Number is "+Connection_Number);
	    	   Thread.sleep(2000);
	    	   oNavigate.toHardwareAssociation();
	    	   Thread.sleep(5000);
	    	   oHardwareAssociation.changeAssociation(CustomerNumber,"ChangeWithSingle",Connection_Number,"Smart Card");  	
	
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=21  order by 1 desc ";
	    	   this.records=oDBConnection.fecthRecords(Status);
	    	   this.record=this.records.get(0);
	    	   String Qstatus=record.get("STATUS");
	    	   System.out.println(Qstatus);
	    	   if(Qstatus.equalsIgnoreCase(N_Status))
	    	   {
	    		   System.out.println("PR-4104 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4104 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4104",driver);
	    	   System.out.println("Test__PR-4104___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
	
		
		@Test(description="Modify Association for a customer having multiple contracts on single card ")
	    public void PR_4105() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4105",this);
	    	   System.out.println("PR-4105 Started");
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
	    	   
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=85  order by 1 desc ";
	    	   this.records=oDBConnection.fecthRecords(Status);
	    	   this.record=this.records.get(0);
	    	   String Qstatus=record.get("STATUS");
	    	   System.out.println(Qstatus);
	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	   {
	    		   System.out.println("PR-4105 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4105 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	       }
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4105",driver);
	    	   System.out.println("Test__PR-4105___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
	
	
		@Test(description="Modify Association for a customer having multiple contracts on multiple cards ")
	    public void PR_4106() throws Exception
	     {
	       try
	       {
	    	   testLinkConnection.setsTestCase("PR-4106",this);
	    	   System.out.println("PR-4106 Started");
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
	    	   String SC1 = "PROVSC"+randomAlphaNumeric(8);
	    	   String STB1 = "PROVSTB"+randomAlphaNumeric(8);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item1,SC,"","","",1);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item2,STB,"","","",2);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item1,SC1,"","","",3);
	    	   oOwnedHardware.Owned_Hardware_Add("",Item2,STB1,"","","",4);
	    	   
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
	
	//    	   CustomerNumber="60755";
	    	   Thread.sleep(2000);
	    	   oNavigate.toHardwareAssociation();
	    	   Thread.sleep(2000);
	    	   oHardwareAssociation.NonMultiConnection(CustomerNumber, "MultipleContractWithDiffrentHardware");
	
	    	   Thread.sleep(90000);
	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	   Thread.sleep(6000);
	    	   
	    	 
	    	  
	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	   this.record=this.records.get(0);
	    	   String Party_id=record.get("CUSTOMER_ID");
	    	   System.out.println(Party_id);
	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=85  order by 1 desc ";
	    	   this.records=oDBConnection.fecthRecords(Status);
	    	   this.record=this.records.get(0);
	    	   String Qstatus=record.get("STATUS");
	    	   System.out.println(Qstatus);
	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	   {
	    		   System.out.println("PR-4106 Pass");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	   }
	    	   else
	    	   {
	    		   System.out.println("PR-4106 Failed");
	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	   }
	    	   /*
	     	  Assert.assertTrue(true);
	     	 Assert.assertt;
	
	    	    */     	  
	       }
	
	       catch(Exception e)
	       {
	    	   captureScreenShot.takeScreenShot("PR-4106",driver);
	    	   System.out.println("Test__PR-4106___%Failed");
	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	       }
	     }
	
	
		
	}
