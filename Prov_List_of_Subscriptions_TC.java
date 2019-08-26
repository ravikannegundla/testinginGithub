package ProvisioningTC;

import java.util.ArrayList;
import java.util.Hashtable;

import mq.seleniumFW.connection.MQDBConnection;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import testlink.api.java.client.TestLinkAPIResults;

import com.BasicConfigurationInventory.InventoryTransactions;
import com.BasicConfigurationsOfServiceOrder.OrderManagementPrivileges;
import com.InventoryAndLogisticsPageObject.HardwareReplacement;
import com.InventoryAndLogisticsPageObject.OwnedHardware;
import com.JobsPageObject.ScheduleJob;
import com.OrderManagementPageObject.CustomerRegistration;
import com.OrderManagementPageObject.Disconnection;
import com.OrderManagementPageObject.HardwareAssociation;
import com.OrderManagementPageObject.ListOfSubscriptions;
import com.OrderManagementPageObject.Reconnection;
import com.OrderManagementPageObject.Renewal;
import com.OrderManagementPageObject.ServiceOrder;
import com.ProvisioningPageObject.BasicConfigurationsOfProvisioning;
import com.ProvisioningPageObject.ProvisioningRequests;
import com.ReceivablesPageObject.Collections;

import TestFrameWork.MQProvisioning;
import Utility.Navigate;
import Utility.WaitTool;

public class Prov_List_of_Subscriptions_TC extends MQProvisioning


{

	
	   		WebDriver driver;
	   		Navigate oNavigate;
	   		WaitTool oWaitTool;

	   		CustomerRegistration oCustomerRegistration;
	   		ServiceOrder oserviceorder;
	   		ProvisioningRequests oProvisioningRequests;
	   		ScheduleJob oScheduleJob;
	   		OwnedHardware oOwnedHardware;
	   		HardwareAssociation oHardwareAssociation;
	   		BasicConfigurationsOfProvisioning oBasicConfigurationsOfProvisioning;
	   		Disconnection oDisconnection;
	   		Reconnection oReconnection;
	   		HardwareReplacement oHardwareReplacement;
	   		Renewal oRenewal;
	   		Collections oCollections;
	   		OrderManagementPrivileges oOrderManagementPrivileges;
	   		ListOfSubscriptions oListOfSubscriptions;



	   		public By locPlanSearchTab = By.id("ctl00_uxPgCPH_txtplnsrch");
	   		public By locAddToCart = By.xpath(".//*[@id='ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd']");
	   		public By locNoOfRooms = By.id("ctl00_uxPgCPH_txtNoofrooms");
	   		public By locApprove = By.id("ctl00_uxPgCPH_chkapprove");
	   		public By locsuccessmsg=By.xpath(".//*[@id='ctl00__infoSpan']");
	   		public By locModify = By.id("ctl00_uxPgCPH_grdcontractdtls_ctl02_lnkmodify");
	   		public By locContractNumber = By.xpath(".//*[@id='ctl00_uxPgCPH_txtcontractno']");
	   		public By locSaveBtn=By.id("ctl00_uxPgCPH_btnsave");
	   		public By locbtnsave = By.name("ctl00$uxPgCPH$btnsave");


	   		public ArrayList<Hashtable<String, String>> records;
	   		public Hashtable<String,String> record;
	   		public MQDBConnection oDBConnection;
	   		String strPlanCode = "PLAN1SERV1";
	   		String billing_Frequency = "One Month";
	   		String contract_validity = "One Month";
	   		String first_Name = "Ravi";
	   		String last_Name = "Kannegundla";


	   		String disconnectedOn ="23-01-2019";
	   		String futuredate_DisconnectedOn ="01-04-2020";
	   		String futuredate_Reconnection ="01-04-2020";
	   		String backDated="02-03-2019";
	   		String futureDate="01-04-2020";
	   		String currentDate="24-05-2019";


	   		String Status,StatusMRPL="Faulty";
	   		String C_Status="C",N_Status="N";
	   		String Party_id,Qstatus,Customer_id;
	   		String Item1="Smart Card",Item2="Set Top Box";

	   		String mrnNumber;
	   		public String Inventory_Location="Head Location",Supplier="Conax Supplier";
	   		String PartyClass="Customer", Entity="CORP",Location="Head Location",Succ_Msg;
	   		public InventoryTransactions invTrans;

	   		int services_count=1;
	   		String MultiRoomplan="NAGRA150";
	   		String hardwarePlan="HWPLAN1111";
	   		String Change_order_plan="PLANXVC";
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
	   		String title;
	   		String getReconnectionCount,Reconnection_Count,getDisconnectionCount,Disconnection_Count;
	   		int ReconnectionCount,DisconnectionCount;


	   		protected Prov_List_of_Subscriptions_TC(String sLogFileName) throws Exception
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
	   			this.oScheduleJob = new ScheduleJob(driver);
	   			this.oOwnedHardware=new OwnedHardware(driver, oDBConnection);
	   			this.invTrans=new InventoryTransactions(driver, dbConnection);
	   			this.oHardwareAssociation=new HardwareAssociation(driver,dbConnection);
	   			this.oBasicConfigurationsOfProvisioning=new BasicConfigurationsOfProvisioning(driver,dbConnection);
	   			this.oDisconnection = new Disconnection(driver);
	   			this.oDisconnection = new Disconnection(driver,dbConnection);
	   			this.oReconnection = new Reconnection(driver);
	   			this.oReconnection = new Reconnection(driver,dbConnection);
	   			this.oHardwareReplacement= new HardwareReplacement(driver,dbConnection);
	   			this.oRenewal = new Renewal(driver);
	   			this.oCollections=new Collections(driver,dbConnection);
	   			this.oBasicConfigurationsOfProvisioning=new BasicConfigurationsOfProvisioning(driver,dbConnection);
	   			this.oListOfSubscriptions=new ListOfSubscriptions(driver,dbConnection);
	   			this.oOrderManagementPrivileges=new OrderManagementPrivileges(dbConnection);

	   			oBasicConfigurationsOfProvisioning.OwnHardware_Privileges();
	   			oOrderManagementPrivileges.Disconnection_privileges();
	   			oOrderManagementPrivileges.Reconnection_privileges();
	   			oOrderManagementPrivileges.ServiceOrder_privileges();

	   			testLinkConnection.setsTestProject("PayTV Provisioning");
	   			testLinkConnection.setsTestPlan("5.12.2");
	   			testLinkConnection.setsBuild("5.12.2");
	   			this.oNavigate=new Navigate(driver,dbConnection);
	   			this.oWaitTool= new WaitTool(driver);
	   			PageFactory.initElements(driver,this);
	   			verifyLogin("","");
	   			Alert alert=driver.switchTo().alert();
	   			alert.accept();


	   		}

	   		/*	    	@BeforeClass
      	public void preRequisites() throws Exception
          {
		    //Profile option prerequisites 	    			
           invTrans.profile("Auto Generate Customer Number", "true","N");

          } 
	   		 */	        

	   		public void relogin() throws Exception
	   		{
	   			oNavigate.Navigation_Action("Logout");
	   			oWaitTool.implicitWait(10);		
	   			//	            	 verifyLogin("KRAVI","KRAVI123");
	   			verifyLogin("SYSADMIN","SYSADMIN");
	   			oWaitTool.implicitWait(10);
	   		}



	   		//To generate Random numbers      ABCDEFGHIJKLMNOPQRSTUVWXYZ
	   		private static final String ALPHA_NUMERIC_STRING = "0123456789";
	   		public static String randomAlphaNumeric(int count) 
	   		{
	   			StringBuilder builder = new StringBuilder();
	   			while (count-- != 0) 
	   			{
	   				int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
	   				builder.append(ALPHA_NUMERIC_STRING.charAt(character));
	   			}

	   			System.out.println("value is:"+builder.toString());
	   			return builder.toString();
	   		}


	   		@Test(description="Check  View Privilege(list of subscriptions)")
	   		public void PR_3975() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-3975",this);
	   				System.out.println("PR-3975 Started");
	   				Thread.sleep(3000);
	   				oNavigate.toListofSubscriptions();
	   				Thread.sleep(2000);
	   				title=driver.getTitle();
	   				System.out.println("Success message is:"+title);
	   				if(title.equalsIgnoreCase("List of Subscriptions"))
	   				{
	   					System.out.println("PR-3975 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("CCheck  View Privilege(list of subscriptions) is Done");
	   				}
	   				else
	   				{
	   					System.out.println("PR-3975 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Check  View Privilege(list of subscriptions) Is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-3975",driver);
	   				System.out.println("Test__PR-3975___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		// Expired
	   		
	   		@Test(description="Check About to expire Privilege")
	   		public void PR_3976() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-3976",this);
	   				System.out.println("PR-3976 Started");
	   				oNavigate.toListofSubscriptions();

	   				
	   				
	   				
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);
	   				Thread.sleep(30000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				Thread.sleep(5000);
	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				Thread.sleep(10000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-3976 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Check About to expire Privilege is Done");
	   				}
	   				else
	   				{
	   					System.out.println("PR-3976 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Check About to expire Privilege is Failed");
	   				}

	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-3976",driver);
	   				System.out.println("Test__PR-3976___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Drop down type as Date range")
	   		public void PR_3977() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-3977",this);
	   				System.out.println("PR-3977 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);
	   				Thread.sleep(2000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				
	   				
	   				oNavigate.toListofSubscriptions();
		   			
//	   				Abouttoexpire(String sCustomerNumber,String sContractNumber,String sDuration,String sDateRangeFrom,String sDateRangeTo,String sSearch, String sDownload,String sClearFilters)(String sCustomerNumber,String sContractNumber,String sDuration,String sDateRangeFrom,String sDateRangeTo,String sSearch, String sDownload,String sClearFilters)
	   				
	   				oListOfSubscriptions.Abouttoexpire(CustomerNumber,ContractNumber,"Date Range","from date","to date","Y","N","N");
	   				
	   				Thread.sleep(3000);
	   				oNavigate.toServiceOrder();
	   				oWaitTool.implicitWait(100);

	   				driver.findElement(locModify).click();
	   				Thread.sleep(2000);
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
	   				Thread.sleep(10000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				Thread.sleep(3000);

	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				Thread.sleep(5000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-3977 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Drop down type as Date range Is Done");
	   				}
	   				else
	   				{
	   					System.out.println("PR-3977 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Drop down type as Date range is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-3977",driver);
	   				System.out.println("Test__PR-3977___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Drop down type as Next Month")
	   		public void PR_3978() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-3978",this);
	   				System.out.println("PR-3978 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);
	   				Thread.sleep(8000);

	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				Thread.sleep(2000);
	   				getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	   				this.records=oDBConnection.fecthRecords(getConnectionNumber);
	   				this.record=this.records.get(0);
	   				Connection_Number=record.get("CONNECTION_NUMBER");
	   				System.out.println("connection Number is "+Connection_Number);

	   				Thread.sleep(2000);
	   				oNavigate.toHardwareAssociation();
	   				Thread.sleep(2000);
	   				oHardwareAssociation.changeAssociation(CustomerNumber,"Change",Connection_Number,"");
	   				Thread.sleep(3000);
	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				Thread.sleep(8000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-3978 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Drop down type as Next Month is Done");
	   				}
	   				else
	   				{
	   					System.out.println("PR-3978 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Drop down type as Next Month is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-3978",driver);
	   				System.out.println("Test__PR-3978___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Drop down type as Next Week")
	   		public void PR_3979() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-3979",this);
	   				System.out.println("PR-3979 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);
	   				Thread.sleep(2000);

	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	   				this.records=oDBConnection.fecthRecords(getConnectionNumber);
	   				this.record=this.records.get(0);
	   				Connection_Number=record.get("CONNECTION_NUMBER");
	   				System.out.println("connection Number is "+Connection_Number);
	   				Thread.sleep(2000);
	   				oNavigate.toHardwareAssociation();
	   				Thread.sleep(2000);
	   				oHardwareAssociation.changeAssociation(CustomerNumber,"Change",Connection_Number,"");

	   				Thread.sleep(3000);
	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				Thread.sleep(9000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-3979 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Drop down type as Next Week is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-3979 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Drop down type as Next Week is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-3979",driver);
	   				System.out.println("Test__PR-3979___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Clear filters")
	   		public void PR_3980() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-3980",this);
	   				System.out.println("PR-3980 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Sale", "", "","",strPlanCode,"Y",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);
	   				Thread.sleep(5000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				Thread.sleep(2000);
	   				oNavigate.toHardwareReplacement();
	   				oWaitTool.implicitWait(20);		        	  
	   				oHardwareReplacement.HardwareReplacement_Header(CustomerNumber,"", "","",ReasonMRPL,"","","","","","","","","","","New");
	   				oHardwareReplacement.HardwareReplacement_Item_Dtls(Item1,"",StatusMRPL,Item1,OperationalEntity,Location,OrderType,1);
	   				driver.findElement(locSaveBtn).click();
	   				Thread.sleep(5000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				Thread.sleep(3000);
	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				Thread.sleep(10000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"'and BIZ_PROCESS_ID=4  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-3980 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Clear filters is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-3980 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Clear filters is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-3980",driver);
	   				System.out.println("Test__PR-3980___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Clear filters with search")
	   		public void PR_3981() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-3981",this);
	   				System.out.println("PR-3981 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);
	   				Thread.sleep(7000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				oNavigate.toCollection();
	   				oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
	   				Thread.sleep(5000);
	   				oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
	   				Thread.sleep(80000);
	   				Thread.sleep(70000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				Thread.sleep(3000);
	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				System.out.println("Successfully Navigated to Disconnection Screen");
	   				Thread.sleep(3000);
	   				driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvDisconnection_ctl02_chkapprove']")).click();
	   				Thread.sleep(2000);
	   				System.out.println("approve done");
	   				driver.findElement(locbtnsave).click();
	   				System.out.println("Save clicked");

	   				Thread.sleep(2000);
	   				Alert alert=driver.switchTo().alert();
	   				alert.accept();
	   				Thread.sleep(2000);
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				Thread.sleep(10000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"'and BIZ_PROCESS_ID=4  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					Thread.sleep(5000);
	   					System.out.println("PR-3981 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Clear filters with search is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-3981 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Clear filters with search is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-3981",driver);
	   				System.out.println("Test__PR-3981___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Date not be less than sysdate")
	   		public void PR_4108() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4108",this);
	   				System.out.println("PR-4108 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);
	   				Thread.sleep(7000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				oNavigate.toCollection();
	   				oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
	   				Thread.sleep(5000);
	   				oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
	   				Thread.sleep(75000);
	   				Thread.sleep(10000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				oNavigate.toCollection();
	   				oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
	   				Thread.sleep(5000);
	   				oCollections.collectionGrid("", "","Disconnect Contract", "", "Save");
	   				Thread.sleep(70000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"'and BIZ_PROCESS_ID=4  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					Thread.sleep(5000);
	   					System.out.println("PR-4108 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Date not be less than sysdate is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4108 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Date not be less than sysdate is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4108",driver);
	   				System.out.println("Test__PR-4108___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Date range is not allowed more than 90 days")
	   		public void PR_3982() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-3982",this);
	   				System.out.println("PR-3982 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);
	   				Thread.sleep(7000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				oNavigate.toCollection();
	   				oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
	   				Thread.sleep(5000);
	   				oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
	   				System.out.println("Suspend Provisioning is done");
	   				Thread.sleep(50000);

	   				oNavigate.toCollection();
	   				oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
	   				Thread.sleep(5000);
	   				oCollections.collectionGrid("", "","Active", "", "Save");
	   				System.out.println("Reactivation  is done");
	   				Thread.sleep(50000);

	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				Thread.sleep(10000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"'and BIZ_PROCESS_ID=4  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-3982 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Date range is not allowed more than 90 days is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-3982 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Date range is not allowed more than 90 days is failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-3982",driver);
	   				System.out.println("Test__PR-3982___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Activation with Single contract")
	   		public void PR_3983() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-3983",this);
	   				System.out.println("PR-3983 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);
	   				Thread.sleep(2000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				oNavigate.toRenewal();
	   				RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y","","",contract_validity,"Save");
	   				System.out.println("Success message is:"+RenewalValidation);
	   				Thread.sleep(3000);

	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(N_Status))
	   				{
	   					System.out.println("PR-3983 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Activation with Single contract is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-3983 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Activation with Single contract is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-3983",driver);
	   				System.out.println("Test__PR-3983___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Disconnection")
	   		public void PR_3984() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("`",this);
	   				System.out.println("PR-3984 Started");
	   				invTrans.profile("Auto Generate Customer Number", "false","N");
	   				relogin();

	   				Thread.sleep(2000);
	   				oNavigate.toCustomerRegistration();
	   				Thread.sleep(2000);
	   				CustomerNumber = "CST"+randomAlphaNumeric(3);
	   				oCustomerRegistration.CustomCustomerRegisteration(CustomerNumber,first_Name,last_Name,"","");
	   				System.out.println("Customer number is : "+CustomerNumber); 
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
	   				oWaitTool.implicitWait(30);
	   				Thread.sleep(8000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				Thread.sleep(5000);
	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");
	   				Thread.sleep(8000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-3984 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Disconnection Is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-3984 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Disconnection is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-3984",driver);
	   				System.out.println("Test__PR-3984___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   			finally 
	   			{
	   				invTrans.profile("Auto Generate Customer Number", "true","N");  
	   				relogin();
	   			}
	   		}


	   		@Test(description="Reconnection")
	   		public void PR_3985() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-3985",this);
	   				System.out.println("PR-3985 Started");  	    	  
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", futureDate, futureDate,"",strPlanCode,"Y","");
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);
	   				Thread.sleep(5000);

	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,futuredate_DisconnectedOn,Reason,"Y","");
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(N_Status))
	   				{
	   					System.out.println("PR-3985 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Reconnection is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-3985 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Reconnection Is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-3985",driver);
	   				System.out.println("Test__PR-3985___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Change basic pack")
	   		public void PR_3986() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-3986",this);
	   				System.out.println("PR-3986 Started");
	   				Thread.sleep(2000);
	   				oNavigate.toCustomerRegistration();
	   				Thread.sleep(2000);

	   				CustomerNumber = oCustomerRegistration.NewCustomerRegistration(first_Name,last_Name,"","");
	   				System.out.println("Customer number is : "+CustomerNumber); 
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
	   				Thread.sleep(5000);

	   				oNavigate.toHardwareAssociation();
	   				Thread.sleep(2000);
	   				oHardwareAssociation.NonMultiConnection(CustomerNumber, "MultipleContractWithSameHardware");
	   				Thread.sleep(70000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	   				this.records=oDBConnection.fecthRecords(getConnectionNumber);
	   				this.record=this.records.get(0);
	   				Connection_Number=record.get("CONNECTION_NUMBER");
	   				System.out.println("connection Number is "+Connection_Number);

	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				sResult = oDisconnection.Disconnect_MultipleContracts("",Connection_Number,"","","","");
	   				driver.findElement(locSaveBtn).click();	
	   				Thread.sleep(1000);
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				Thread.sleep(70000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				Thread.sleep(3000);

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=86 order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println("status of the request is :"+Qstatus+"");// need to add message..
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-3986 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Change basic pack is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-3986 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Change basic pack is Failed");
	   				}
	   			}
	   			catch(Exception e)

	   			{
	   				captureScreenShot.takeScreenShot("PR-3986",driver);
	   				System.out.println("Test__PR-3986___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Change validity")
	   		public void PR_3987() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-3987",this);
	   				System.out.println("PR-3987 Started");
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
	   				Thread.sleep(10000);

	   				getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	   				this.records=oDBConnection.fecthRecords(getConnectionNumber);
	   				this.record=this.records.get(0);
	   				Connection_Number=record.get("CONNECTION_NUMBER");
	   				System.out.println("connection Number is "+Connection_Number);

	   				Thread.sleep(5000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				Thread.sleep(3000);

	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				sResult = oDisconnection.Disconnect_MultipleContracts("",Connection_Number,"","","","");
	   				driver.findElement(locSaveBtn).click();	
	   				Thread.sleep(1000);
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				Thread.sleep(5000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				Thread.sleep(3000);

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=86 order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println("status of the request is :"+Qstatus+"");
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-3987 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Change validity is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-3987 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Change validity is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-3987",driver);
	   				System.out.println("Test__PR-3987___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Renewal")
	   		public void PR_3988() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-3988",this);
	   				System.out.println("PR-3988 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);
	   				Thread.sleep(5000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				Thread.sleep(3000);

	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,futuredate_DisconnectedOn,Reason,"Y","");
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(N_Status))
	   				{
	   					System.out.println("PR-3988 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Renewal is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-3988 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Renewal is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-3988",driver);
	   				System.out.println("Test__PR-3988___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Activation with Multi contract")
	   		public void PR_3990() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-3990",this);
	   				System.out.println("PR-3990 Started");
	   				oNavigate.toCustomerRegistration();
	   				Thread.sleep(2000);
	   				CustomerNumber = oCustomerRegistration.NewCustomerRegistration(first_Name,last_Name,"","");
	   				System.out.println("Customer number is : "+CustomerNumber); 
	   				Thread.sleep(3000);

	   				mrnNumber=invTrans.mrnTrans("","1",Inventory_Location,Supplier,currentDate,"N",1,"Y","N","Y");
	   				System.out.println("Mrn Completed");

	   				oNavigate.toServiceOrder();
	   				oWaitTool.implicitWait(100);
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
	   				getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	   				this.records=oDBConnection.fecthRecords(getConnectionNumber);
	   				this.record=this.records.get(0);
	   				Connection_Number=record.get("CONNECTION_NUMBER");
	   				System.out.println("connection Number is "+Connection_Number);

	   				Thread.sleep(5000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				Thread.sleep(3000);

	   				getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	   				this.records=oDBConnection.fecthRecords(getConnectionNumber);
	   				this.record=this.records.get(0);
	   				Connection_Number=record.get("CONNECTION_NUMBER");
	   				System.out.println("connection Number is "+Connection_Number);

	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				sResult = oDisconnection.Disconnect_MultipleContracts("",Connection_Number,"","","","");
	   				driver.findElement(locSaveBtn).click();	
	   				Thread.sleep(1000);
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				Thread.sleep(10000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				Thread.sleep(3000);

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4 order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println("status of the request is :"+Qstatus+"");
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-3990 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Activation with Multi contract is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-3990 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Activation with Multi contract is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-3990",driver);
	   				System.out.println("Test__PR-3990___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Activation with Single Connection")
	   		public void PR_3991() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-3991",this);
	   				System.out.println("PR-3991 Started");
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
	   				oserviceorder.BookPlan("","","","",MultiRoomplan,"","");
	   				Thread.sleep(3000);
	   				String SROutput=driver.findElement(By.id("ctl00__infoSpan")).getText();
	   				Thread.sleep(2000);
	   				System.out.println("validation msg= "+SROutput);
	   				Thread.sleep(5000);

	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				Thread.sleep(3000);

	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				Thread.sleep(3000);

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-3991 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Activation with Single Connection is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-3991 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Activation with Single Connection is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-3991",driver);
	   				System.out.println("Test__PR-3991___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Activation with Multi Connection")
	   		public void PR_3992() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-3992",this);
	   				System.out.println("PR-3992 Started");
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

	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				Thread.sleep(80000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				Thread.sleep(3000);

	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-3992 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Activation with Multi Connection is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-3992 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Activation with Multi Connection is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-3992",driver);
	   				System.out.println("Test__PR-3992___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Activation with is_bulk check")
	   		public void PR_3993() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-3993",this);
	   				System.out.println("PR-3993 Started");
	   				Thread.sleep(2000);
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);

	   				Thread.sleep(3000);
	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(N_Status))
	   				{
	   					System.out.println("PR-3993 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Activation with is_bulk check is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-3993 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Activation with is_bulk check is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-3993",driver);
	   				System.out.println("Test__PR-3993___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}

	   		//       Expired Related Test Case's

	   		@Test(description="Check Expired")
	   		public void PR_3994() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-3994",this);
	   				System.out.println("PR-3994 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", backDated, backDated,contract_validity,strPlanCode,"",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);
	   				Thread.sleep(10000);

	   				oNavigate.toProvisioningRequest();
	   				Thread.sleep(7000);

	   				oProvisioningRequests.ProvisioningRequestsScreen(CustomerNumber,"",backDated,"","","",New,Sent,"","Search","Save");
	   				String successMsg2 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+successMsg2 );
	   				Thread.sleep(2000);
	   				driver.navigate().refresh();
	   				oProvisioningRequests.ProvisioningRequestsScreen(CustomerNumber,"",backDated,"","","",Sent,Accept,"","Search","Save");
	   				Thread.sleep(5000);

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				oNavigate.toScheduleJob();
	   				Thread.sleep(2000);
	   				oScheduleJob.ScheduleJobAutoExpiry_SingleCustomer("", "Batch Jobs","Autoexpire", "", "", "","","AutoExpiry For Single Customer","",Party_id,"");
	   				Thread.sleep(80000);

	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(N_Status))
	   				{
	   					System.out.println("PR-3994 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Check Expired is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-3994 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Check Expired id Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-3994",driver);
	   				System.out.println("Test__PR-3994___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Drop down type as Date Range")
	   		public void PR_3995() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-3995",this);
	   				System.out.println("PR-3995 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);
	   				Thread.sleep(3000);

	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(N_Status))
	   				{
	   					System.out.println("PR-3995 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Drop down type as Date Range is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-3995 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Drop down type as Date Range is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-3995",driver);
	   				System.out.println("Test__PR-3995___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Drop down type as Last Month")
	   		public void PR_3996() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-3996",this);
	   				System.out.println("PR-3996 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);
	   				Thread.sleep(7000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				oNavigate.toCollection();
	   				oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
	   				Thread.sleep(5000);
	   				oCollections.collectionGrid("", "","Service Downgrade", "", "Save");
	   				System.out.println("Service Downgrade done");
	   				Thread.sleep(50000);

	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				System.out.println("Successfully Navigated to Disconnection Screen");
	   				Thread.sleep(3000);
	   				driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvDisconnection_ctl02_chkapprove']")).click();
	   				Thread.sleep(2000);
	   				System.out.println("approve done");
	   				driver.findElement(locbtnsave).click();
	   				System.out.println("Save clicked");

	   				Thread.sleep(2000);
	   				Alert alert=driver.switchTo().alert();
	   				alert.accept();
	   				Thread.sleep(2000);
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				Thread.sleep(10000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"'and BIZ_PROCESS_ID=4  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-3996 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Drop down type as Last Month is passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-3996 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Drop down type as Last Month is failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-3996",driver);
	   				System.out.println("Test__PR-3996___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}

	   		
	   		@Test(description="Drop down type as Last Week")
	   		public void PR_3998() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-3998",this);
	   				System.out.println("PR-3998 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);

	   				Thread.sleep(3000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				Thread.sleep(3000);
	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"","");
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				getDisconnectionCount="select COUNT(*) as COUNT from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(getDisconnectionCount);
	   				this.record=this.records.get(0);
	   				Disconnection_Count=record.get("COUNT");
	   				DisconnectionCount = Integer.parseInt(Disconnection_Count);
	   				System.out.println("connectionCount is "+DisconnectionCount);
	   				if(DisconnectionCount==0)
	   				{
	   					System.out.println("PR-3998 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Drop down type as Last Week is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-3998 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Drop down type as Last Week is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-3998",driver);
	   				System.out.println("Test__PR-3998___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Drop down type as MTD")
	   		public void PR_3999() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-3999",this);
	   				System.out.println("PR-3999 Started");
	   				Thread.sleep(3000);
	   				oNavigate.toReconnection();
	   				Thread.sleep(5000);
	   				title=driver.getTitle();
	   				System.out.println("Success message is:"+title);
	   				if(title.equalsIgnoreCase("Contract Reconnection"))
	   				{
	   					System.out.println("PR-3999 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Drop down type as MTD is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-3999 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Drop down type as MTD is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-3999",driver);
	   				System.out.println("Test__PR-3999___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Drop down type as WTD")
	   		public void PR_4000() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4000",this);
	   				System.out.println("PR-4000 Started");
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
	   				System.out.println("Disconnection is done");
	   				Thread.sleep(5000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();           

	   				oNavigate.toReconnection();
	   				oWaitTool.implicitWait(10);
	   				oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
	   				oWaitTool.implicitWait(50);
	   				String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg1);
	   				System.out.println("Reconnection is done");
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-4000 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Drop down type as WTD is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4000 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Drop down type as WTDss is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4000",driver);
	   				System.out.println("Test__PR-4000___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Clear filters")
	   		public void PR_4001() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4001",this);
	   				System.out.println("PR-4001 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", backDated, backDated,"",strPlanCode,"Y",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);
	   				Thread.sleep(5000);

	   				oNavigate.toProvisioningRequest();
	   				Thread.sleep(8000);
	   				oProvisioningRequests.ProvisioningRequestsScreen(CustomerNumber,"",backDated,"","","",New,Sent,"","Search","Save");
	   				String successMsg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+successMsg1 );
	   				Thread.sleep(2000);
	   				driver.navigate().refresh();
	   				oProvisioningRequests.ProvisioningRequestsScreen(CustomerNumber,"",backDated,"","","",Sent,Accept,"","Search","Save");
	   				Thread.sleep(3000);

	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,backDated,Reason,"Y","");
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");
	   				Thread.sleep(5000);
	   				oNavigate.toProvisioningRequest();
	   				Thread.sleep(7000);

	   				oProvisioningRequests.ProvisioningRequestsScreen(CustomerNumber,"",backDated,"","","",New,Sent,"","Search","Save");
	   				String successMsg2 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+successMsg2 );
	   				Thread.sleep(2000);
	   				driver.navigate().refresh();
	   				oProvisioningRequests.ProvisioningRequestsScreen(CustomerNumber,"",backDated,"","","",Sent,Accept,"","Search","Save");

	   				Thread.sleep(5000);
	   				oNavigate.toReconnection();
	   				oWaitTool.implicitWait(10);
	   				oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
	   				oWaitTool.implicitWait(50);
	   				String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg1);
	   				System.out.println("Reconnection is done");

	   				Thread.sleep(5000);
	   				oNavigate.toProvisioningRequest();
	   				Thread.sleep(5000);
	   				oProvisioningRequests.ProvisioningRequestsScreen(CustomerNumber,"",backDated,"","","",New,Sent,"","Search","Save");
	   				String successMsg3 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+successMsg3 );
	   				Thread.sleep(2000);
	   				driver.navigate().refresh();
	   				oProvisioningRequests.ProvisioningRequestsScreen(CustomerNumber,"",backDated,"","","",Sent,Accept,"","Search","Save");

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-4001 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Clear filters is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4001 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Clear filters is failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4001",driver);
	   				System.out.println("Test__PR-4001___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Clear filters with search")
	   		public void PR_4002() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4002",this);
	   				System.out.println("PR-4002 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", backDated, backDated,contract_validity,strPlanCode,"Y",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);
	   				Thread.sleep(10000);

	   				oNavigate.toProvisioningRequest();
	   				Thread.sleep(7000);
	   				oProvisioningRequests.ProvisioningRequestsScreen(CustomerNumber,"",backDated,"","","",New,Sent,"","Search","Save");
	   				String successMsg2 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+successMsg2 );
	   				Thread.sleep(2000);
	   				driver.navigate().refresh();
	   				oProvisioningRequests.ProvisioningRequestsScreen(CustomerNumber,"",backDated,"","","",Sent,Accept,"","Search","Save");
	   				Thread.sleep(5000);

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				oNavigate.toScheduleJob();
	   				Thread.sleep(2000);
	   				oScheduleJob.ScheduleJobAutoExpiry_SingleCustomer("", "Batch Jobs","Autoexpire", "", "", "","","AutoExpiry For Single Customer","",Party_id,"");
	   				Thread.sleep(80000);

	   				oProvisioningRequests.ProvisioningRequestsScreen(CustomerNumber,"",backDated,"","","",New,Sent,"","Search","Save");
	   				String successMsg3 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+successMsg3 );
	   				Thread.sleep(2000);
	   				driver.navigate().refresh();
	   				oProvisioningRequests.ProvisioningRequestsScreen(CustomerNumber,"",backDated,"","","",Sent,Accept,"","Search","Save");

	   				Thread.sleep(5000);
	   				oNavigate.toReconnection();
	   				oWaitTool.implicitWait(10);
	   				oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
	   				oWaitTool.implicitWait(50);
	   				String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg1);
	   				System.out.println("Reconnection is done");

	   				Thread.sleep(5000);
	   				oNavigate.toProvisioningRequest();
	   				Thread.sleep(5000);

	   				oProvisioningRequests.ProvisioningRequestsScreen(CustomerNumber,"",backDated,"","","",New,Sent,"","Search","Save");
	   				String successMsg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+successMsg1 );
	   				Thread.sleep(2000);
	   				driver.navigate().refresh();
	   				oProvisioningRequests.ProvisioningRequestsScreen(CustomerNumber,"",backDated,"","","",Sent,Accept,"","Search","Save");

	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-4002 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Clear filters with search is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4002 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Clear filters with search is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4002",driver);
	   				System.out.println("Test__PR-4002___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Must be less  than sysdate")
	   		public void PR_4003() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4003",this);
	   				System.out.println("PR-4003 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);
	   				Thread.sleep(2000);

	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	   				this.records=oDBConnection.fecthRecords(getConnectionNumber);
	   				this.record=this.records.get(0);
	   				Connection_Number=record.get("CONNECTION_NUMBER");

	   				System.out.println("connection Number is "+Connection_Number);
	   				Thread.sleep(2000);
	   				oNavigate.toHardwareAssociation();
	   				Thread.sleep(2000);
	   				oHardwareAssociation.changeAssociation(CustomerNumber,"Change",Connection_Number,"");

	   				Thread.sleep(3000);
	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				Thread.sleep(7000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				Thread.sleep(5000);
	   				oNavigate.toReconnection();
	   				oWaitTool.implicitWait(10);
	   				oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
	   				oWaitTool.implicitWait(50);
	   				String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg1);
	   				System.out.println("Reconnection is done");
	   				Thread.sleep(7000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-4003 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Must be less  than sysdate is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4003 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Must be less  than sysdate is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4003",driver);
	   				System.out.println("Test__PR-4003___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Date range is not allowed more than 90 days")
	   		public void PR_4004() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4004",this);
	   				System.out.println("PR-4004 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);
	   				Thread.sleep(5000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	   				this.records=oDBConnection.fecthRecords(getConnectionNumber);
	   				this.record=this.records.get(0);
	   				Connection_Number=record.get("CONNECTION_NUMBER");

	   				System.out.println("connection Number is "+Connection_Number);
	   				Thread.sleep(2000);
	   				oNavigate.toHardwareAssociation();
	   				Thread.sleep(2000);
	   				oHardwareAssociation.changeAssociation(CustomerNumber,"Change",Connection_Number,"");

	   				Thread.sleep(3000);
	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				Thread.sleep(7000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				Thread.sleep(5000);
	   				oNavigate.toReconnection();
	   				oWaitTool.implicitWait(10);
	   				oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
	   				oWaitTool.implicitWait(50);
	   				String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg1);
	   				System.out.println("Reconnection is done");

	   				Thread.sleep(7000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-4004 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Date range is not allowed more than 90 days is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4004 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Date range is not allowed more than 90 days is failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4004",driver);
	   				System.out.println("Test__PR-4004___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Activation with Single contract")
	   		public void PR_4005() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4005",this);
	   				System.out.println("PR-4005 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Sale", "", "","",strPlanCode,"Y",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);

	   				Thread.sleep(5000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				Thread.sleep(3000);
	   				oNavigate.toServiceOrder();
	   				oWaitTool.implicitWait(100);
	   				driver.findElement(locModify).click();
	   				Thread.sleep(2000);
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

	   				Thread.sleep(7000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				oNavigate.toHardwareReplacement();
	   				oWaitTool.implicitWait(20);		        	  
	   				oHardwareReplacement.HardwareReplacement_Header(CustomerNumber,"", "","",ReasonMRPL,"","","","","","","","","","","New");
	   				oHardwareReplacement.HardwareReplacement_Item_Dtls(Item1,"",StatusMRPL,Item1,OperationalEntity,Location,OrderType,1);
	   				driver.findElement(locSaveBtn).click();

	   				Thread.sleep(3000);
	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				Thread.sleep(7000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				Thread.sleep(5000);
	   				oNavigate.toReconnection();
	   				oWaitTool.implicitWait(10);
	   				oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
	   				oWaitTool.implicitWait(50);
	   				String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg1);
	   				System.out.println("Reconnection is done");

	   				Thread.sleep(7000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"'and BIZ_PROCESS_ID=4  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-4005 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Activation with Single contract is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4005 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Activation with Single contract Is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4005",driver);
	   				System.out.println("Test__PR-4005___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Activation with Multi contract")
	   		public void PR_4006() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4006",this);
	   				System.out.println("PR-4006 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);
	   				Thread.sleep(7000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				oNavigate.toCollection();
	   				oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
	   				Thread.sleep(5000);
	   				oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
	   				Thread.sleep(75000);

	   				oNavigate.toDisconnection();
	   				Thread.sleep(15000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				System.out.println("Successfully Navigated to Disconnection Screen");
	   				Thread.sleep(3000);
	   				driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvDisconnection_ctl02_chkapprove']")).click();
	   				Thread.sleep(2000);
	   				System.out.println("approve done");
	   				driver.findElement(locbtnsave).click();
	   				System.out.println("Save clicked");
	   				Thread.sleep(2000);
	   				Alert alert=driver.switchTo().alert();
	   				alert.accept();
	   				Thread.sleep(2000);
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				Thread.sleep(10000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				Thread.sleep(5000);
	   				oNavigate.toReconnection();
	   				oWaitTool.implicitWait(10);
	   				oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
	   				oWaitTool.implicitWait(50);
	   				String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg1);
	   				System.out.println("Reconnection is done");

	   				Thread.sleep(7000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"'and BIZ_PROCESS_ID=7  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				Qstatus="C";
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-4006 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Activation with Multi contract is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4006 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Activation with Multi contract is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4006",driver);
	   				System.out.println("Test__PR-4006___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Activation with Single Connection")
	   		public void PR_4008() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4008",this);
	   				System.out.println("PR-4008 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);
	   				Thread.sleep(7000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				oNavigate.toRenewal();
	   				RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y","","",contract_validity,"Save");
	   				System.out.println("Success message is:"+RenewalValidation);

	   				Thread.sleep(3000);
	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				Thread.sleep(10000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				Thread.sleep(5000);
	   				oNavigate.toReconnection();
	   				oWaitTool.implicitWait(10);
	   				oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
	   				oWaitTool.implicitWait(50);
	   				String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg1);
	   				System.out.println("Reconnection is done");

	   				Thread.sleep(7000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"'and BIZ_PROCESS_ID=7  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-4008 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Activation with Single Connection is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4008 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Activation with Single Connection is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4008",driver);
	   				System.out.println("Test__PR-4008___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Activation with Multi Connection")
	   		public void PR_4009() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4009",this);
	   				System.out.println("PR-4009 Started");
	   				invTrans.profile("Auto Generate Customer Number", "false","N");
	   				relogin();
	   				Alert alert=driver.switchTo().alert();
	   				alert.accept();

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
	   				oWaitTool.implicitWait(30);
	   				Thread.sleep(7000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");
	   				Thread.sleep(5000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				Thread.sleep(5000);
	   				oNavigate.toReconnection();
	   				oWaitTool.implicitWait(10);
	   				oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
	   				oWaitTool.implicitWait(50);
	   				String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg1);
	   				System.out.println("Reconnection is done");

	   				Thread.sleep(7000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-4009 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Activation with Multi Connection is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4009 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Activation with Multi Connection is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4009",driver);
	   				System.out.println("Test__PR-4009___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Activation with is_bulk check")
	   		public void PR_4010() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4010",this);
	   				System.out.println("PR-4010 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", futureDate, futureDate,"",strPlanCode,"Y",billing_Frequency);
	   				System.out.println("Customer Number is : "+CustomerNumber);
	   				Thread.sleep(3000);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);
	   				Thread.sleep(5000);

	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,futuredate_DisconnectedOn,Reason,"Y","");
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				Thread.sleep(5000);
	   				oNavigate.toReconnection();
	   				oWaitTool.implicitWait(10);
	   				oReconnection.Reconnect_SingleContract("",futuredate_Reconnection,"",ContractNumber,"Y","");
	   				oWaitTool.implicitWait(50);
	   				String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg1);
	   				System.out.println("Reconnection is done");

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(N_Status))
	   				{
	   					System.out.println("PR-4010 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Activation with is_bulk check is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4010 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Activation with is_bulk check Is failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4010",driver);
	   				System.out.println("Test__PR-4010___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}

	   		// Activations Realted test Case's

	   		@Test(description="Check Activations")
	   		public void PR_4011() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4011",this);
	   				System.out.println("PR-4011 Started");
	   				Thread.sleep(2000);
	   				oNavigate.toCustomerRegistration();
	   				Thread.sleep(2000);

	   				CustomerNumber = oCustomerRegistration.NewCustomerRegistration(first_Name,last_Name,"","");
	   				System.out.println("Customer number is : "+CustomerNumber); 
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

	   				Thread.sleep(5000);
	   				oNavigate.toHardwareAssociation();
	   				Thread.sleep(2000);
	   				oHardwareAssociation.NonMultiConnection(CustomerNumber, "MultipleContractWithSameHardware");
	   				Thread.sleep(60000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	   				this.records=oDBConnection.fecthRecords(getConnectionNumber);
	   				this.record=this.records.get(0);
	   				Connection_Number=record.get("CONNECTION_NUMBER");
	   				System.out.println("connection Number is "+Connection_Number);

	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				sResult = oDisconnection.Disconnect_MultipleContracts("",Connection_Number,"","","Y","");
	   				driver.findElement(locSaveBtn).click();	
	   				Thread.sleep(1000);
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");
	   				Thread.sleep(70000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				Thread.sleep(5000);
	   				oNavigate.toReconnection();
	   				oWaitTool.implicitWait(10);
	   				oReconnection.Reconnect_MultipleContract("",Connection_Number, "", "", "Y", "");
	   				oWaitTool.implicitWait(50);
	   				driver.findElement(locSaveBtn).click();
	   				String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg1);
	   				System.out.println("Reconnection is done");

	   				Thread.sleep(70000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				Thread.sleep(3000);

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=87 order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println("status of the request is :"+Qstatus+"");
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-4011 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Check Activations is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4011 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Check Activations  is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4011",driver);
	   				System.out.println("Test__PR-4011___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Change  order with Active status")
	   		public void PR_4012() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4012",this);
	   				System.out.println("PR-4012 Started");
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
	   				Thread.sleep(6000);

	   				getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	   				this.records=oDBConnection.fecthRecords(getConnectionNumber);
	   				this.record=this.records.get(0);
	   				Connection_Number=record.get("CONNECTION_NUMBER");
	   				System.out.println("connection Number is "+Connection_Number);

	   				Thread.sleep(5000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				Thread.sleep(3000);

	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				sResult = oDisconnection.Disconnect_MultipleContracts("",Connection_Number,"","","","");
	   				driver.findElement(locSaveBtn).click();	
	   				Thread.sleep(1000);
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				Thread.sleep(5000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				Thread.sleep(5000);
	   				oNavigate.toReconnection();
	   				oWaitTool.implicitWait(10);
	   				oReconnection.Reconnect_MultipleContract("",Connection_Number, "", "", "Y", "");
	   				oWaitTool.implicitWait(50);
	   				driver.findElement(locSaveBtn).click();
	   				String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg1);
	   				System.out.println("Reconnection is done");

	   				Thread.sleep(10000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				Thread.sleep(3000);

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=87 order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println("status of the request is :"+Qstatus+"");
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-4012 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Change  order with Active status is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4012 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Change  order with Active status is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4012",driver);
	   				System.out.println("Test__PR-4012___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="New  order with Active status")
	   		public void PR_4013() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4013",this);
	   				System.out.println("PR-4013 Started");
	   				Thread.sleep(2000);
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
	   				System.out.println("Disconnection is done");
	   				Thread.sleep(5000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				Thread.sleep(2000);
	   				oNavigate.toReconnection();
	   				oWaitTool.implicitWait(10);
	   				oReconnection.Reconnect_SingleContract("",futuredate_Reconnection,"",ContractNumber,"Y","");
	   				oWaitTool.implicitWait(50);
	   				String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg1);
	   				System.out.println("Reconnection is done");

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(N_Status))
	   				{
	   					System.out.println("PR-4013 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("New  order with Active status is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4013 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("New  order with Active status  is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4013",driver);
	   				System.out.println("Test__PR-4013___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Reconnection   with Active status")
	   		public void PR_4015() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4015",this);
	   				System.out.println("PR-4015 Started");
	   				oNavigate.toCustomerRegistration();
	   				Thread.sleep(2000);
	   				CustomerNumber = oCustomerRegistration.NewCustomerRegistration(first_Name,last_Name,"","");
	   				System.out.println("Customer number is : "+CustomerNumber); 
	   				Thread.sleep(3000);

	   				mrnNumber=invTrans.mrnTrans("","1",Inventory_Location,Supplier,currentDate,"N",1,"Y","N","Y");
	   				System.out.println("Mrn Completed");

	   				oNavigate.toServiceOrder();
	   				oWaitTool.implicitWait(100);
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
	   				getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	   				this.records=oDBConnection.fecthRecords(getConnectionNumber);
	   				this.record=this.records.get(0);
	   				Connection_Number=record.get("CONNECTION_NUMBER");
	   				System.out.println("connection Number is "+Connection_Number);

	   				Thread.sleep(5000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				Thread.sleep(3000);

	   				getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	   				this.records=oDBConnection.fecthRecords(getConnectionNumber);
	   				this.record=this.records.get(0);
	   				Connection_Number=record.get("CONNECTION_NUMBER");
	   				System.out.println("connection Number is "+Connection_Number);

	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				sResult = oDisconnection.Disconnect_MultipleContracts("",Connection_Number,"","","","");
	   				driver.findElement(locSaveBtn).click();	
	   				Thread.sleep(1000);
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				Thread.sleep(10000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				Thread.sleep(3000);

	   				Thread.sleep(5000);
	   				oNavigate.toReconnection();
	   				oWaitTool.implicitWait(10);
	   				oReconnection.Reconnect_MultipleContract("",Connection_Number, "", "", "Y", "");
	   				oWaitTool.implicitWait(50);
	   				driver.findElement(locSaveBtn).click();
	   				String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg1);
	   				System.out.println("Reconnection is done");

	   				Thread.sleep(10000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				Thread.sleep(3000);

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=87 order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println("status of the request is :"+Qstatus+"");
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-4015 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Reconnection   with Active status is passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4015 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Reconnection   with Active status is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4015",driver);
	   				System.out.println("Test__PR-4015___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Renew Order    with Active status")
	   		public void PR_4016() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4016",this);
	   				System.out.println("PR-4016 Started");
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
	   				oserviceorder.BookPlan("","","","",MultiRoomplan,"","");
	   				Thread.sleep(3000);
	   				String SROutput=driver.findElement(By.id("ctl00__infoSpan")).getText();
	   				Thread.sleep(2000);
	   				System.out.println("validation msg= "+SROutput);
	   				Thread.sleep(5000);

	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				Thread.sleep(3000);

	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				Thread.sleep(3000);

	   				oNavigate.toReconnection();
	   				oWaitTool.implicitWait(10);
	   				oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
	   				oWaitTool.implicitWait(50);
	   				String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg1);
	   				System.out.println("Reconnection is done");

	   				Thread.sleep(8000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				Thread.sleep(3000);

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-4016 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Renew Order    with Active status is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4016 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Renew Order    with Active status is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4016",driver);
	   				System.out.println("Test__PR-4016___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Change  order with Failed  status")
	   		public void PR_4017() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4017",this);
	   				System.out.println("PR-4017 Started");
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

	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");
	   				Thread.sleep(70000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				Thread.sleep(3000);

	   				oNavigate.toReconnection();
	   				oWaitTool.implicitWait(10);
	   				oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
	   				oWaitTool.implicitWait(50);
	   				String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg1);
	   				System.out.println("Reconnection is done");

	   				Thread.sleep(70000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				Thread.sleep(3000);

	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-4017 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Change  order with Failed  status ");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4017 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Change  order with Failed  status is failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4017",driver);
	   				System.out.println("Test__PR-4017___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="New  order with Failed status")
	   		public void PR_4018() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4018",this);
	   				System.out.println("PR-4018 Started");
	   				Thread.sleep(2000);
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);

	   				Thread.sleep(3000);
	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				oNavigate.toReconnection();
	   				oWaitTool.implicitWait(10);
	   				oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
	   				oWaitTool.implicitWait(50);
	   				String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg1);
	   				System.out.println("Reconnection is done");

	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(N_Status))
	   				{
	   					System.out.println("PR-4018 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("New  order with Failed status is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4018 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("New  order with Failed status id Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4018",driver);
	   				System.out.println("Test__PR-4018___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Reconnection   with Failed status")
	   		public void PR_4019() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4019",this);
	   				System.out.println("PR-4019 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);

	   				Thread.sleep(3000);
	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				Thread.sleep(3000);
	   				oNavigate.toReconnection();
	   				oWaitTool.implicitWait(10);
	   				oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
	   				oWaitTool.implicitWait(50);
	   				String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg1);
	   				System.out.println("Reconnection is done");

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(N_Status))
	   				{
	   					System.out.println("PR-4019 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Reconnection   with Failed status is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4019 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Reconnection   with Failed status is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4019",driver);
	   				System.out.println("Test__PR-4019___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Renew Order  with Failed status")
	   		public void PR_4020() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4020",this);
	   				System.out.println("PR-4020 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);
	   				Thread.sleep(7000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				oNavigate.toCollection();
	   				oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
	   				Thread.sleep(5000);
	   				oCollections.collectionGrid("", "","Service Downgrade", "", "Save");
	   				Thread.sleep(50000);

	   				Thread.sleep(3000);
	   				Thread.sleep(3000);
	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				System.out.println("Successfully Navigated to Disconnection Screen");
	   				Thread.sleep(3000);
	   				driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvDisconnection_ctl02_chkapprove']")).click();
	   				Thread.sleep(2000);
	   				System.out.println("approve done");
	   				driver.findElement(locbtnsave).click();
	   				System.out.println("Save clicked");

	   				Thread.sleep(2000);
	   				Alert alert=driver.switchTo().alert();
	   				alert.accept();
	   				Thread.sleep(2000);
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				Thread.sleep(10000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				Thread.sleep(5000);
	   				oNavigate.toReconnection();
	   				oWaitTool.implicitWait(10);
	   				oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
	   				oWaitTool.implicitWait(50);
	   				String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg1);
	   				System.out.println("Reconnection is done");

	   				Thread.sleep(7000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"'and BIZ_PROCESS_ID=7  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-4020 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Renew Order  with Failed status is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4020 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Renew Order  with Failed status is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4020",driver);
	   				System.out.println("Test__PR-4020___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Change  order with Pending status")
	   		public void PR_4022() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4022",this);
	   				System.out.println("PR-4022 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);

	   				Thread.sleep(3000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				Thread.sleep(3000);

	   				oNavigate.toDisconnection();
	   				Thread.sleep(5000);
	   				logger.info("Successfully Navigated to Disconnection Screen ");
	   				sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Disconnection is done");

	   				Thread.sleep(3000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				Thread.sleep(3000);

	   				oNavigate.toReconnection();
	   				oWaitTool.implicitWait(10);
	   				oReconnection.Reconnect_SingleContract("","","",ContractNumber,"N","");
	   				oWaitTool.implicitWait(50);
	   				String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg1);
	   				System.out.println("Reconnection is done");

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				getReconnectionCount="select COUNT(*) as COUNT from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(getReconnectionCount);
	   				this.record=this.records.get(0);
	   				Reconnection_Count=record.get("COUNT");
	   				ReconnectionCount = Integer.parseInt(Reconnection_Count);
	   				System.out.println("ReconnectionCount is "+ReconnectionCount);
	   				if(ReconnectionCount==0)
	   				{
	   					System.out.println("PR-4022 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Change  order with Pending status is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4022 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Change  order with Pending status is failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4022",driver);
	   				System.out.println("Test__PR-4022___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="New  order with Pending status")
	   		public void PR_4048() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4048",this);
	   				System.out.println("PR-4048 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);
	   				Thread.sleep(2000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				oNavigate.toRenewal();
	   				RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y","","",contract_validity,"Save");
	   				System.out.println("Success message is:"+RenewalValidation);
	   				Thread.sleep(3000);

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(N_Status))
	   				{
	   					System.out.println("PR-4048 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("New  order with Pending status is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4048 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("New  order with Pending status is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4048",driver);
	   				System.out.println("Test__PR-4048___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Reconnection   with Pending status")
	   		public void PR_4049() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4049",this);
	   				System.out.println("PR-4049 Started");
	   				   				
	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-4049 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Reconnection   with Pending status is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4049 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Reconnection   with Pending status is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4049",driver);
	   				System.out.println("Test__PR-4049___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Renew Order    with Pending status")
	   		public void PR_4050() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4050",this);
	   				System.out.println("PR-4050 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", backDated, backDated,contract_validity,strPlanCode,"Y",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);
	   				Thread.sleep(10000);
	   				oNavigate.toProvisioningRequest();
	   				Thread.sleep(7000);

	   				oProvisioningRequests.ProvisioningRequestsScreen(CustomerNumber,"",backDated,"","","",New,Sent,"","Search","Save");
	   				String successMsg2 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+successMsg2 );
	   				Thread.sleep(2000);
	   				driver.navigate().refresh();
	   				oProvisioningRequests.ProvisioningRequestsScreen(CustomerNumber,"",backDated,"","","",Sent,Accept,"","Search","Save");
	   				Thread.sleep(5000);

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				oNavigate.toScheduleJob();
	   				Thread.sleep(2000);
	   				oScheduleJob.ScheduleJobAutoExpiry_SingleCustomer("", "Batch Jobs","Autoexpire", "", "", "","","AutoExpiry For Single Customer","",Party_id,"");
	   				Thread.sleep(80000);

	   				oNavigate.toRenewal();
	   				RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"","","",contract_validity,"Save");
	   				System.out.println("Success message is:"+RenewalValidation);

	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(N_Status))
	   				{
	   					System.out.println("PR-4050 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Renew Order    with Pending status is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4050 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Renew Order    with Pending status is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4050",driver);
	   				System.out.println("Test__PR-4050___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Clear filters")
	   		public void PR_4051() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4051",this);
	   				System.out.println("PR-4051 Started");
	   				
	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-4051 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Clear filters is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4051 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Clear filters is failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4051",driver);
	   				System.out.println("Test__PR-4051___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Clear filters with search")
	   		public void PR_4052() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4052",this);
	   				System.out.println("PR-4052 Started");
	   			
	   				
	   				oNavigate.toCollection();
	   				oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
	   				Thread.sleep(5000);
	   				oCollections.collectionGrid("", "","Active", "", "Save");
	   				Thread.sleep(80000);

	   				Thread.sleep(5000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				oNavigate.toRenewal();
	   				RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y","","",contract_validity,"Save");
	   				System.out.println("Success message is:"+RenewalValidation);

	   				Thread.sleep(5000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(C_Status))
	   				{
	   					System.out.println("PR-4052 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Clear filters with search is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4052 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Clear filters with search is failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4052",driver);
	   				System.out.println("Test__PR-4052___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Date cannot be blank in date range")
	   		public void PR_4053() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4053",this);
	   				System.out.println("PR-4053 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "","",contract_validity,strPlanCode,"Y",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);
	   				Thread.sleep(2000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	   				this.records=oDBConnection.fecthRecords(getConnectionNumber);
	   				this.record=this.records.get(0);
	   				Connection_Number=record.get("CONNECTION_NUMBER");

	   				System.out.println("connection Number is "+Connection_Number);
	   				Thread.sleep(2000);
	   				oNavigate.toHardwareAssociation();
	   				Thread.sleep(2000);
	   				oHardwareAssociation.changeAssociation(CustomerNumber,"Change",Connection_Number,"");

	   				oNavigate.toRenewal();
	   				RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y","","",contract_validity,"Save");
	   				System.out.println("Success message is:"+RenewalValidation);
	   				Thread.sleep(3000);

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(N_Status))
	   				{
	   					System.out.println("PR-4053 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Date cannot be blank in date range is passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4053 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Date cannot be blank in date range is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4053",driver);
	   				System.out.println("Test__PR-4053___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Date range is not allowed more than 90 days")
	   		public void PR_4054() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4054",this);
	   				System.out.println("PR-4054 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
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
	   				Thread.sleep(2000);
	   				oHardwareAssociation.changeAssociation(CustomerNumber,"Change",Connection_Number,"");

	   				oNavigate.toRenewal();
	   				RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y","","",contract_validity,"Save");
	   				System.out.println("Success message is:"+RenewalValidation);
	   				Thread.sleep(3000);

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(N_Status))
	   				{
	   					System.out.println("PR-4054 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Date range is not allowed more than 90 days is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4054 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Date range is not allowed more than 90 days Is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4054",driver);
	   				System.out.println("Test__PR-4054___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="From Date cannot be later than To Date")
	   		public void PR_4055() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4055",this);
	   				System.out.println("PR-4055 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Sale", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);

	   				Thread.sleep(5000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				oNavigate.toHardwareReplacement();
	   				oWaitTool.implicitWait(20);		        	  
	   				oHardwareReplacement.HardwareReplacement_Header(CustomerNumber,"", "","",ReasonMRPL,"","","","","","","","","","","New");
	   				oHardwareReplacement.HardwareReplacement_Item_Dtls(Item1,"",StatusMRPL,Item1,OperationalEntity,Location,OrderType,1);
	   				driver.findElement(locSaveBtn).click();
	   				Thread.sleep(20000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	   				oNavigate.toRenewal();
	   				RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y","","",contract_validity,"Save");
	   				System.out.println("Success message is:"+RenewalValidation);
	   				Thread.sleep(3000);

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(N_Status))
	   				{
	   					System.out.println("PR-4055 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("From Date cannot be later than To Date is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4055 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("From Date cannot be later than To Date is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4055",driver);
	   				System.out.println("Test__PR-4055___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Activation with Single contract")
	   		public void PR_4056() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4056",this);
	   				System.out.println("PR-4056 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own","","","",strPlanCode,"Y",billing_Frequency);
	   				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	   				System.out.println("Contract Number is : "+ContractNumber);
	   				Thread.sleep(5000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				Thread.sleep(3000);

	   				oNavigate.toServiceOrder();
	   				oWaitTool.implicitWait(100);

	   				driver.findElement(By.id("ctl00_uxPgCPH_grdcontractdtls_ctl02_lnkmodify")).click();
	   				Thread.sleep(3000);
	   				driver.findElement(locApprove).click();
	   				Thread.sleep(2000);
	   				new Select(driver.findElement(By.id("ctl00_uxPgCPH_contractvalidity"))).selectByVisibleText("One Month");
	   				Thread.sleep(5000);
	   				driver.findElement(locSaveBtn).click();			 
	   				Thread.sleep(3000);
	   				String succ_Msg = driver.findElement(locsuccessmsg).getText();
	   				System.out.println("Success message is:"+succ_Msg);
	   				Thread.sleep(5000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				Thread.sleep(3000);

	   				oNavigate.toRenewal();
	   				RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y","","",contract_validity,"Save");
	   				System.out.println("Success message is:"+RenewalValidation);
	   				Thread.sleep(3000);

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13  order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(N_Status))
	   				{
	   					System.out.println("PR-4056 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Activation with Single contract is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4056 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Activation with Single contract is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4056",driver);
	   				System.out.println("Test__PR-4056___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Activation with Multi contract")
	   		public void PR_4057() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4057",this);
	   				System.out.println("PR-4057 Started");
	   				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation_WithBulk();
	   				System.out.println("Customer number is : "+CustomerNumber); 
	   				Thread.sleep(3000);

	   				System.out.println("entered Into method for Customer Actvation");
	   				Thread.sleep(2000);
	   				oNavigate.toCustomerRegistration();
	   				Thread.sleep(2000);
	   				System.out.println("entered IN CUSTOMER REG ");
	   				CustomerNumber = oCustomerRegistration.NewCustomerRegistration(first_Name,last_Name,"","");
	   				System.out.println("CUSTOMER REG COMPLETED");
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
	   				oNavigate.toProvisioningRequest();
	   				Thread.sleep(3000);

	   				RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y","","",contract_validity,"Save");
	   				System.out.println("Success message is:"+RenewalValidation);
	   				Thread.sleep(3000);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13 and IS_PROVISIONED='N' order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(N_Status))
	   				{
	   					System.out.println("PR-4057 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Activation with Multi contract is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4057 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Activation with Multi contract is failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4057",driver);
	   				System.out.println("Test__PR-4057___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Activation with Single Connection")
	   		public void PR_4058() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4058",this);
	   				System.out.println("PR-4058 Started");
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
	   				Thread.sleep(3000);

	   				oNavigate.toRenewal();
	   				RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y","","",contract_validity,"Save");
	   				System.out.println("Success message is:"+RenewalValidation);
	   				Thread.sleep(3000);

	   				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	   				this.records=oDBConnection.fecthRecords(Customer_id);
	   				this.record=this.records.get(0);
	   				String Party_id=record.get("CUSTOMER_ID");
	   				System.out.println(Party_id);
	   				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13 and IS_PROVISIONED='N' order by 1 desc ";
	   				this.records=oDBConnection.fecthRecords(Status);
	   				this.record=this.records.get(0);
	   				String Qstatus=record.get("STATUS");
	   				System.out.println(Qstatus);
	   				if(Qstatus.equalsIgnoreCase(N_Status))
	   				{
	   					System.out.println("PR-4058 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Activation with Single Connection is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4058 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Activation with Single Connection is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4058",driver);
	   				System.out.println("Test__PR-4058___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Activation with Multi Connection")
	   		public void PR_4059() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4059",this);
	   				System.out.println("PR-4059 Started");
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
	   				Thread.sleep(4000);
	   				new Select(driver.findElement(By.id("ctl00_uxPgCPH_contractvalidity"))).selectByVisibleText("One Month");
	   				Thread.sleep(5000);
	   				oserviceorder.serviceOrder_MulitplePlans("PLAN1SERV1","","","",services_count);
	   				Thread.sleep(4000);
	   				oserviceorder.serviceOrder_MulitplePlans("NAGRA150","","","",services_count);
	   				System.out.println("Out of multiple plan's method");
	   				driver.findElement(locSaveBtn).click();
	   				Thread.sleep(6000);
	   				oNavigate.toHardwareAssociation();

	   				Thread.sleep(2000);
	   				oHardwareAssociation.NonMultiConnection(CustomerNumber, "MultipleContractWithSameHardware");
	   				Thread.sleep(60000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				Thread.sleep(6000);

	   				getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	   				this.records=oDBConnection.fecthRecords(getConnectionNumber);
	   				this.record=this.records.get(0);
	   				Connection_Number=record.get("CONNECTION_NUMBER");
	   				System.out.println("connection Number is "+Connection_Number);

	   				// need to write the code for renewal 
	   				oNavigate.toRenewal();
	   				RenewalValidation =oRenewal.Renewal_MultipleContract("",Connection_Number,"Y","","",contract_validity,"Save");
	   				driver.findElement(locSaveBtn).click();	
	   				Thread.sleep(1000);
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Renewal  is done");
	   				Thread.sleep(3000);

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
	   				if(Qstatus.equalsIgnoreCase(N_Status))
	   				{
	   					System.out.println("PR-4059 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Activation with Multi Connection is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4059 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Activation with Multi Connection is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4059",driver);
	   				System.out.println("Test__PR-4059___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}


	   		@Test(description="Activation with is_bulk check")
	   		public void PR_4060() throws Exception
	   		{
	   			try
	   			{
	   				testLinkConnection.setsTestCase("PR-4060",this);
	   				System.out.println("PR-4060 Started");
	   				oNavigate.toCustomerRegistration();
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
	   				new Select(driver.findElement(By.id("ctl00_uxPgCPH_contractvalidity"))).selectByVisibleText("One Month");
	   				Thread.sleep(5000);
	   				oserviceorder.serviceOrder_MulitplePlans("PLAN1SERV1","","","",services_count);
	   				Thread.sleep(4000);
	   				oserviceorder.serviceOrder_MulitplePlans("NAGRA150","","","",services_count);
	   				System.out.println("Out of multiple plan's method");
	   				driver.findElement(locSaveBtn).click();
	   				Thread.sleep(6000);

	   				getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	   				this.records=oDBConnection.fecthRecords(getConnectionNumber);
	   				this.record=this.records.get(0);
	   				Connection_Number=record.get("CONNECTION_NUMBER");
	   				System.out.println("connection Number is "+Connection_Number);

	   				Thread.sleep(5000);
	   				oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	   				Thread.sleep(3000);

	   				oNavigate.toRenewal();
	   				RenewalValidation =oRenewal.Renewal_MultipleContract("",Connection_Number,"Y","","",contract_validity,"Save");
	   				driver.findElement(locSaveBtn).click();	
	   				Thread.sleep(1000);
	   				String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	   				System.out.println("Success message is:"+success_Msg);
	   				System.out.println("Renewal  is done");
	   				Thread.sleep(3000);

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
	   				if(Qstatus.equalsIgnoreCase(N_Status))
	   				{
	   					System.out.println("PR-4060 Pass");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	   					testLinkConnection.setsNotes("Activation with is_bulk check Is Passed");
	   				}
	   				else
	   				{
	   					System.out.println("PR-4060 Failed");
	   					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   					testLinkConnection.setsNotes("Activation with is_bulk check is Failed");
	   				}
	   			}
	   			catch(Exception e)
	   			{
	   				captureScreenShot.takeScreenShot("PR-4060",driver);
	   				System.out.println("Test__PR-4060___%Failed");
	   				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	   				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	   			}
	   		}



}
