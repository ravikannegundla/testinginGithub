package ProvisioningTC.Prov_Collections_TC;

import java.util.ArrayList;
import java.util.Hashtable;

import mq.seleniumFW.connection.MQDBConnection;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import testlink.api.java.client.TestLinkAPIResults;

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
import com.ProvisioningPageObject.ActivityPrerequisites;
import com.ProvisioningPageObject.BasicConfigurationsOfProvisioning;
import com.ProvisioningPageObject.ProvisioningRequests;
import com.ProvisioningPageObject.ProvisioningSystemAction;
import com.ProvisioningPageObject.Retracking;
import com.ReceivablesPageObject.Collections;
import com.ReceivablesPageObject.ContractCollections;

import TestFrameWork.MQProvisioning;
import Utility.Navigate;
import Utility.WaitTool;

public class Prov_Collections_Disconnection_TC extends MQProvisioning 

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
			ContractCollections oContractCollections;
			ProvisioningSystemAction oProvisioningSystemAction;
			ActivityPrerequisites oActivityPrerequisites;


			public By locPlanSearchTab = By.id("ctl00_uxPgCPH_txtplnsrch");
			public By locAddToCart = By.xpath(".//*[@id='ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd']");
			public By locNoOfRooms = By.id("ctl00_uxPgCPH_txtNoofrooms");
			public By locApprove = By.id("ctl00_uxPgCPH_chkapprove");
			public By locsuccessmsg=By.xpath(".//*[@id='ctl00__infoSpan']");
			public By locModify = By.id("ctl00_uxPgCPH_grdcontractdtls_ctl02_lnkmodify");
			public By locContractNumber = By.xpath(".//*[@id='ctl00_uxPgCPH_txtcontractno']");
			public By locSaveBtn=By.id("ctl00_uxPgCPH_btnsave");
			public By ValidationMessage=By.id("ctl00__infoSpan");
			public By btnsave = By.name("ctl00$uxPgCPH$btnsave");


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
			String ReasonMRPL="BAD";
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
			String Provisioned_Status="Y";
			ArrayList<String> Actionids_List= new ArrayList<String>();
			String Prov_System_Name="NAGRA";
			public String Service_Class="TV Channel";
			int record1;




			protected Prov_Collections_Disconnection_TC(String sLogFileName) throws Exception
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
				this.oContractCollections=new ContractCollections(driver,dbConnection);
				this.oProvisioningSystemAction= new ProvisioningSystemAction(driver,dbConnection);
				this.oActivityPrerequisites= new ActivityPrerequisites(driver,dbConnection);


				this.oBasicConfigurationsOfProvisioning=new BasicConfigurationsOfProvisioning(driver,dbConnection);
				this.oOrderManagementPrivileges=new OrderManagementPrivileges(dbConnection);

				/*	oBasicConfigurationsOfProvisioning.OwnHardware_Privileges();
oOrderManagementPrivileges.Disconnection_privileges();
oOrderManagementPrivileges.Reconnection_privileges();
oOrderManagementPrivileges.ServiceOrder_privileges();

				 */

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


			@Test(description="Disconnect Contract after New Aggrement(single contract)")
			public void PR_4069() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4069",this);
					System.out.println("PR-4069 Started");
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(7000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Disconnect Contract", "", "Save");
					System.out.println("Service Downgrade done");
					Thread.sleep(50000);
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status))
					{
						System.out.println("PR-4069 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Disconnect Contract after New Aggrement(single contract) is Passed");
					}
					else
					{
						System.out.println("PR-4069 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Disconnect Contract after New Aggrement(single contract) is failed");
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


			@Test(description="Disconnect Contract after New Aggrement(Multi contracts)")
			public void PR_4072() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4072",this);
					System.out.println("PR-4072 Started");
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(20000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Disconnect Contract", "", "Save");
					System.out.println("Service Downgrade done");
					Thread.sleep(50000);
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status))
					{
						System.out.println("PR-4072 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Disconnect Contract after New Aggrement(Multi contracts) is Passed");
					}
					else
					{
						System.out.println("PR-4072 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Disconnect Contract after New Aggrement(Multi contracts) is Failed");
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


			@Test(description="Disconnect Contract after New Aggrement(Multi room)")
			public void PR_4073() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4073",this);
					System.out.println("PR-4073 Started");
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					Thread.sleep(5000);
					oNavigate.toServiceOrder();
					oWaitTool.implicitWait(100);
					driver.findElement(locApprove).click();
					Thread.sleep(1000);
					driver.findElement(locPlanSearchTab).sendKeys("NAGRA150"+Keys.TAB);
					Thread.sleep(1000);
					driver.findElement(locAddToCart).click();
					Thread.sleep(1000);
					driver.findElement(locSaveBtn).click();
					Thread.sleep(5000);
					String succ_Msg = driver.findElement(locsuccessmsg).getText();
					System.out.println("Success message is:"+succ_Msg);

					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
					Thread.sleep(3000);

					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Disconnect Contract", "", "Save");
					System.out.println("Service Downgrade done");Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status))        	
					{
						System.out.println("PR-4073 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Disconnect Contract after New Aggrement(Multi room) is Passed");
					}
					else
					{
						System.out.println("PR-4073 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Disconnect Contract after New Aggrement(Multi room) is Failed");
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


			@Test(description="Disconnect Contract after Single Contract with is _bulk as Y")
			public void PR_4074() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4074",this);
					System.out.println("PR-4074 Started");
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
					Thread.sleep(90000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
					Thread.sleep(3000);

					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Disconnect Contract", "", "Save");
					System.out.println("Disconnect Contract done");
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status))      
					{
						System.out.println("PR-4074 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Disconnect Contract after Single Contract with is _bulk as Y is Passed");
					}
					else
					{
						System.out.println("PR-4074 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Disconnect Contract after Single Contract with is _bulk as Y is Failed");
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


			@Test(description="Disconnect Contract after Suspend")
			public void PR_4075() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4075",this);
					System.out.println("PR-4075 Started");
					Thread.sleep(2000);

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

					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Disconnect Contract", "", "Save");
					System.out.println("Disconnect Contract done");
					Thread.sleep(50000);
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status))
					{
						System.out.println("PR-4075 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Disconnect Contract after Suspend is Passed");
					}
					else
					{
						System.out.println("PR-4075 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Disconnect Contract after Suspend is Failed");
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


			@Test(description="Disconnect Contract after Reconnection(From Collections)")
			public void PR_4076() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4076",this);
					System.out.println("PR-4076 Started");

					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(7000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Disconnect Contract", "", "Save");
					System.out.println("Service Downgrade done");
					Thread.sleep(50000);
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Active", "", "Save");
					System.out.println("reconnection done done");
					Thread.sleep(50000);
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Disconnect Contract", "", "Save");
					System.out.println("Disconnect Contract done");
					Thread.sleep(50000);
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status))


					{
						System.out.println("PR-4076 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Disconnect Contract after Reconnection(From Collections) is Passed");
					}
					else
					{
						System.out.println("PR-4076 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Disconnect Contract after Reconnection(From Collections) is Failed");
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


			@Test(description="Disconnect Contract after Hardware Change")
			public void PR_4077() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4077",this);
					System.out.println("PR-4077 Started");
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

					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Disconnect Contract", "", "Save");
					System.out.println("Disconnect Contract done");
					Thread.sleep(70000);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status))   
					{
						System.out.println("PR-4077 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Disconnect Contract after Hardware Change is Passed");
					}
					else
					{
						System.out.println("PR-4077 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Disconnect Contract after Hardware Change is failed");
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


			@Test(description="Disconnect Contract after Hardware Association Change")
			public void PR_4055() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4055",this);
					System.out.println("PR-4055 Started");
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
					logger.info("Successfully Navigated to Disconnection Screen ");
					sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
					String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
					System.out.println("Success message is:"+success_Msg);
					System.out.println("Disconnection is done");
					Thread.sleep(8000);

					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Disconnect Contract", "", "Save");
					System.out.println("Disconnect Contract done");
					Thread.sleep(50000);
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status))
					{
						System.out.println("PR-4055 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Disconnect Contract after Hardware Association Change is Passed");
					}
					else
					{
						System.out.println("PR-4055 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Disconnect Contract after Hardware Association Change is Failed");
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


			@Test(description="Disconnect Contract after Service Down Grade")
			public void PR_4000() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4000",this);
					System.out.println("PR-4000 Started");
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(8000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					Thread.sleep(5000);
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Service Downgrade", "", "Save");
					System.out.println("Service Downgrade done");
					Thread.sleep(70000);
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Disconnect Contract", "", "Save");
					System.out.println("Disconnect Contract done");
					Thread.sleep(50000);
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status))
					{
						System.out.println("PR-4000 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Disconnect Contract after Service Down Grade is Passed");
					}
					else
					{
						System.out.println("PR-4000 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Disconnect Contract after Service Down Grade is Failed");
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


			@Test(description="Disconnect Contract After Retracking")
			public void PR_4001() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4001",this);
					System.out.println("PR-4001 Started");
					Thread.sleep(3000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(15000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toRetracking();
					oRetracking.RetrackingScreen(CustomerNumber, "Service",1, "N", "Save");
					Thread.sleep(5000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
					Thread.sleep(5000);

					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Disconnect Contract", "", "Save");
					System.out.println("Disconnect Contract done");
					Thread.sleep(50000);
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status)) 
					{
						System.out.println("PR-4001 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Disconnect Contract After Retracking is Passed");
					}
					else
					{
						System.out.println("PR-4001 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Disconnect Contract After Retracking is failed");
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


			@Test(description="Disconnect Contract After Change Order")
			public void PR_4002() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4002",this);
					System.out.println("PR-4002 Started");
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
					Thread.sleep(30000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Disconnect Contract", "", "Save");
					System.out.println("Disconnect Contract done");
					Thread.sleep(50000);
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status)) 
					{
						System.out.println("PR-4002 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Disconnect Contract After Change Order is Passed");
					}
					else
					{
						System.out.println("PR-4002 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Disconnect Contract After Change Order is Failed");
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


			@Test(description="Disconnect Contract after Renewal")
			public void PR_4003() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4003",this);
					System.out.println("PR-4003 Started");
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own",renewalBackdated,renewalBackdated,contract_validity,strPlanCode,"Y",billing_Frequency);
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
					Thread.sleep(30000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Disconnect Contract", "", "Save");
					System.out.println("Disconnect Contract done");
					Thread.sleep(50000);
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();


					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=67 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status)) 
					{
						System.out.println("PR-4003 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Disconnect Contract after Renewal is Passed");
					}
					else
					{
						System.out.println("PR-4003 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Disconnect Contract after Renewal is Failed");
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


			@Test(description="Disconnect Contract After  Service Down Grade and Suspend")
			public void PR_4004() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4004",this);
					System.out.println("PR-4004 Started");
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(8000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					Thread.sleep(5000);
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Service Downgrade", "", "Save");
					System.out.println("Service Downgrade done");
					Thread.sleep(70000);
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
					Thread.sleep(80000);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Disconnect Contract", "", "Save");
					System.out.println("Disconnect Contract done");
					Thread.sleep(50000);
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status))
					{
						System.out.println("PR-4004 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Disconnect Contract After  Service Down Grade and Suspend is Passed");
					}
					else
					{
						System.out.println("PR-4004 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Disconnect Contract After  Service Down Grade and Suspend is failed");
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


			@Test(description="Disconnect Contract with action as Remove Product")
			public void PR_2661() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2661",this);
					System.out.println("PR-2661 Started");
					Thread.sleep(2000);
					relogin();
					Actionids_List = new ArrayList(10);
					Actionids_List.add("1");
					Actionids_List.add("2");
					Actionids_List.add("22");
					Actionids_List.add("24");
					System.out.println("array list is "+Actionids_List);
					oNavigate.toProvisioningSystemAction();
					Thread.sleep(2000);
					String Screen_Validation=oProvisioningSystemAction.ProvisioningActionMapping(Prov_System_Name,Actionids_List,"Save");
					System.out.println("validation message"+Screen_Validation);

					Thread.sleep(2000);
					relogin();
					Thread.sleep(2000);
					oNavigate.toActivityPrerequisites();
					Thread.sleep(2000);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"1","12","Pre Action","N",1);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"16","87","Post Action","N",2);
					driver.findElement(btnsave).click();
					String Screen_Validation1=driver.findElement(ValidationMessage).getText();
					Thread.sleep(2000);
					System.out.println(Screen_Validation1);
					Thread.sleep(2000);
					relogin();

					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Disconnect Contract", "", "Save");
					System.out.println("Disconnect Contract done");
					Thread.sleep(50000);
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status))
					{
						System.out.println("PR-2661 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Disconnect Contract with action as Remove Product is Passed");
					}
					else
					{
						System.out.println("PR-2661 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Disconnect Contract with action as Remove Product is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-2661",driver);
					System.out.println("Test__PR-2661___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			@Test(description="Disconnect Contract with action as Disconnect All")
			public void PR_2655() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2655",this);
					System.out.println("PR-2655 Started");
					Thread.sleep(2000);
					relogin();
					Actionids_List = new ArrayList(10);
					Actionids_List.add("1");
					Actionids_List.add("2");
					Actionids_List.add("17");
					Actionids_List.add("19");
					System.out.println("array list is "+Actionids_List);
					oNavigate.toProvisioningSystemAction();
					Thread.sleep(2000);
					String Screen_Validation=oProvisioningSystemAction.ProvisioningActionMapping(Prov_System_Name,Actionids_List,"Save");
					System.out.println("validation message"+Screen_Validation);

					Thread.sleep(2000);
					relogin();
					Thread.sleep(2000);
					oNavigate.toActivityPrerequisites();
					Thread.sleep(2000);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"1","12","Pre Action","N",1);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"16","87","Post Action","N",2);
					driver.findElement(btnsave).click();
					String Screen_Validation1=driver.findElement(ValidationMessage).getText();
					Thread.sleep(2000);
					System.out.println(Screen_Validation1);
					Thread.sleep(2000);
					relogin();

					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Disconnect Contract", "", "Save");
					System.out.println("Disconnect Contract done");
					Thread.sleep(50000);
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status))
					{
						System.out.println("PR-2655 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Disconnect Contract with action as Disconnect All is Passed");
					}
					else
					{
						System.out.println("PR-2655 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Disconnect Contract with action as Disconnect All is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-2655",driver);
					System.out.println("Test__PR-2655___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			@Test(description="Disconnect Contract with action as Disconnect Product List")
			public void PR_2656() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2656",this);
					System.out.println("PR-2656 Started");
					Thread.sleep(2000);
					relogin();
					Actionids_List = new ArrayList(10);
					Actionids_List.add("156");
					Actionids_List.add("129");
					Actionids_List.add("158");
					Actionids_List.add("160");
					Actionids_List.add("159");
					System.out.println("array list is "+Actionids_List);
					oNavigate.toProvisioningSystemAction();
					Thread.sleep(2000);
					String Screen_Validation=oProvisioningSystemAction.ProvisioningActionMapping(Prov_System_Name,Actionids_List,"Save");
					System.out.println("validation message"+Screen_Validation);

					Thread.sleep(2000);
					relogin();
					Thread.sleep(2000);
					oNavigate.toActivityPrerequisites();
					Thread.sleep(2000);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"156","12","Pre Action","N",1);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"129","87","Post Action","N",2);
					driver.findElement(btnsave).click();
					String Screen_Validation1=driver.findElement(ValidationMessage).getText();
					Thread.sleep(2000);
					System.out.println(Screen_Validation1);
					Thread.sleep(2000);
					relogin();

					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Suspend_Validatation_Msg= oContractCollections.SuspendCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Suspend_Validatation message is:"+Suspend_Validatation_Msg);

					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					System.out.println(Party_id);
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=10  order by 1 desc ";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus);
					if(Qstatus.equalsIgnoreCase(C_Status))
					{
						System.out.println("PR-2656 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Disconnect Contract with action as Disconnect Product List is Passed");
					}
					else
					{
						System.out.println("PR-2656 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Disconnect Contract with action as Disconnect Product List is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-2656",driver);
					System.out.println("Test__PR-2656___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			@Test(description="Service Downgrade with Activite product list action")
			public void PR_4071() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4071",this);
					System.out.println("PR-4071 Started");
					Thread.sleep(2000);
					relogin();
					Actionids_List = new ArrayList(10);
					Actionids_List.add("156");
					Actionids_List.add("129");
					Actionids_List.add("158");
					Actionids_List.add("160");
					Actionids_List.add("159");
					System.out.println("array list is "+Actionids_List);
					oNavigate.toProvisioningSystemAction();
					Thread.sleep(2000);
					String Screen_Validation=oProvisioningSystemAction.ProvisioningActionMapping(Prov_System_Name,Actionids_List,"Save");
					System.out.println("validation message"+Screen_Validation);

					Thread.sleep(2000);
					relogin();
					Thread.sleep(2000);
					oNavigate.toActivityPrerequisites();
					Thread.sleep(2000);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"156","12","Pre Action","N",1);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"129","87","Post Action","N",2);
					driver.findElement(btnsave).click();
					String Screen_Validation1=driver.findElement(ValidationMessage).getText();
					Thread.sleep(2000);
					System.out.println(Screen_Validation1);
					Thread.sleep(2000);
					relogin();

					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
					Thread.sleep(5000);

					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Disconnect Contract", "", "Save");
					System.out.println("Disconnect Contract done");
					Thread.sleep(50000);
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status))
					{
						System.out.println("PR-4071 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Service Downgrade with Activite product list action is Passed");
					}
					else
					{
						System.out.println("PR-4071 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Service Downgrade with Activite product list action is failed");
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


			@Test(description="Active After Disconnect Contract")
			public void PR_4078() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4078",this);
					System.out.println("PR-4078 Started");

					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
					Thread.sleep(5000);

					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Disconnect Contract", "", "Save");
					System.out.println("Disconnect Contract done");
					Thread.sleep(50000);
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Active", "", "Save");
					System.out.println("Disconnect Contract done");
					Thread.sleep(50000);
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status))
					{
						System.out.println("PR-4078 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Active After Disconnect Contract is Passed");
					}
					else
					{
						System.out.println("PR-4078 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Active After Disconnect Contract is Failed");
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


			@Test(description="Suspend after New Aggrement (Single Connection)")
			public void PR_2664() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2664",this);
					System.out.println("PR-2664 Started");
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(7000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Disconnect Contract", "", "Save");
					System.out.println("Service Downgrade done");
					Thread.sleep(50000);
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status))
					{
						System.out.println("PR-2664 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Suspend after New Aggrement (Single Connection) is  Passed");
					}
					else
					{
						System.out.println("PR-2664 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Suspend after New Aggrement (Single Connection) is failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-2664",driver);
					System.out.println("Test__PR-2664___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			@Test(description="Suspend after New Aggrement (Multi Connections)")
			public void PR_2665() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2665",this);
					System.out.println("PR-2665 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(7000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Suspend_Validatation_Msg= oContractCollections.SuspendCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Suspend_Validatation message is:"+Suspend_Validatation_Msg);
					Thread.sleep(70000);
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
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					System.out.println(Party_id);
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=2  order by 1 desc ";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus);
					if(Qstatus.equalsIgnoreCase(C_Status))
					{
						System.out.println("PR-2665 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Suspend after New Aggrement (Multi Connections) is Passed");
					}
					else
					{
						System.out.println("PR-2665 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Suspend after New Aggrement (Multi Connections) is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-2665",driver);
					System.out.println("Test__PR-2665___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			@Test(description="Multi_connections:-No of  connections as 1 (Add-on and ala carte)")
			public void PR_2666() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2666",this);
					System.out.println("PR-2666 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(7000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Suspend_Validatation_Msg= oContractCollections.SuspendCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Suspend_Validatation message is:"+Suspend_Validatation_Msg);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					Thread.sleep(5000);
					oNavigate.toDisconnection();
					Thread.sleep(5000);
					sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
					String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
					System.out.println("Success message is:"+success_Msg);
					System.out.println("Disconnection is done");
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status))
					{
						System.out.println("PR-2666 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Multi_connections:-No of  connections as 1 (Add-on and ala carte) is passed");
					}
					else
					{
						System.out.println("PR-2666 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Multi_connections:-No of  connections as 1 (Add-on and ala carte) is failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-2666",driver);
					System.out.println("Test__PR-2666___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			@Test(description="Multi_Connections:-No of  connections as 2 (Add-on and ala carte)")
			public void PR_2667() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2667",this);
					System.out.println("PR-2667 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(7000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Suspend_Validatation_Msg= oContractCollections.SuspendCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Suspend_Validatation message is:"+Suspend_Validatation_Msg);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toRenewal();
					RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y","","",contract_validity,"Save");
					System.out.println("Success message is:"+RenewalValidation);
					Thread.sleep(3000);

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					System.out.println(" Party Id is " +Party_id);

					String updateRenewalRequestasSysdate = "update PROVSION_PROCESS_REQUEST set EFFECTIVE_DATE=MQSSYSDATE where PARTY_ID='"+Party_id+"' and BIZ_PROCESS_ID=13";
					this.record1=oDBConnection.updateRecords(updateRenewalRequestasSysdate);
					System.out.println(" Renewal Request Is updated and commited succefully");
					Thread.sleep(2000);
					Thread.sleep(90000);
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13  order by 1 desc ";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus);
					if(Qstatus.equalsIgnoreCase(C_Status))        	
					{
						System.out.println("PR-2667 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Multi_Connections:-No of  connections as 2 (Add-on and ala carte) Is Passed");
					}
					else
					{
						System.out.println("PR-2667 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Multi_Connections:-No of  connections as 2 (Add-on and ala carte) Is failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-2667",driver);
					System.out.println("Test__PR-2667___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			@Test(description="Service down Grade after Disconnect Contract")
			public void PR_2669() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2669",this);
					System.out.println("PR-2669 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(7000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Suspend_Validatation_Msg= oContractCollections.SuspendCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Suspend_Validatation message is:"+Suspend_Validatation_Msg);
					Thread.sleep(70000);
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
						System.out.println("PR-2669 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Service down Grade after Disconnect Contract Is Passed");
					}
					else
					{
						System.out.println("PR-2669 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Service down Grade after Disconnect Contract Is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-2669",driver);
					System.out.println("Test__PR-2669___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			@Test(description="Suspend After Disconnect Contract")    
			public void PR_2668() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2668",this);
					System.out.println("PR-2668 Started");
					Thread.sleep(2000);

					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(7000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Suspend_Validatation_Msg= oContractCollections.SuspendCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Suspend_Validatation message is:"+Suspend_Validatation_Msg);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					Thread.sleep(2000);
					oNavigate.toHardwareReplacement();
					oWaitTool.implicitWait(20);		        	  
					oHardwareReplacement.HardwareReplacement_Header(CustomerNumber,"", "","",ReasonMRPL,"","","","","","","","","","","New");
					oHardwareReplacement.HardwareReplacement_Item_Dtls(Item1,"",StatusMRPL,Item1,OperationalEntity,Location,OrderType,1);
					driver.findElement(locSaveBtn).click();
					Thread.sleep(40000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
					Thread.sleep(2000);

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					System.out.println(Party_id);
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=3  order by 1 desc ";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus);
					if(Qstatus.equalsIgnoreCase(C_Status))
					{
						System.out.println("PR-2668 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Suspend After Disconnect Contract Is passed");
					}
					else
					{
						System.out.println("PR-2668 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Suspend After Disconnect Contract Is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-2668",driver);
					System.out.println("Test__PR-2668___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			/*------------------------------------------------------------------
---------------------------------------------------------------
----------------------------------------------------------------
			 */


			@Test(description="Disconnect Contract After Disconnect Contract.")
			public void PR_2657() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2657",this);
					System.out.println("PR-2657 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(7000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Suspend_Validatation_Msg= oContractCollections.SuspendCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Suspend_Validatation message is:"+Suspend_Validatation_Msg);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Reactivate_Validatation_Msg=oContractCollections.ReactivateCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Reactivate_Validatation message is:"+Reactivate_Validatation_Msg);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					System.out.println(Party_id);
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"'and BIZ_PROCESS_ID=6  order by 1 desc ";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus);
					if(Qstatus.equalsIgnoreCase(C_Status))
					{
						System.out.println("PR-2657 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Disconnect Contract After Disconnect Contract is Passed");
					}
					else
					{
						System.out.println("PR-2657 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Disconnect Contract After Disconnect Contract. is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-2657",driver);
					System.out.println("Test__PR-2657___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			@Test(description="Reactivate after suspend(Multi Contract)")
			public void PR_2658() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2658",this);
					System.out.println("PR-2658 Started");
					System.out.println("PR-4073 Started");
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(7000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Suspend_Validatation_Msg= oContractCollections.SuspendCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Suspend_Validatation message is:"+Suspend_Validatation_Msg);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Reactivate_Validatation_Msg=oContractCollections.ReactivateCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Reactivate_Validatation message is:"+Reactivate_Validatation_Msg);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					System.out.println(Party_id);
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"'and BIZ_PROCESS_ID=6  order by 1 desc ";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus);
					if(Qstatus.equalsIgnoreCase(C_Status))
					{
						System.out.println("PR-2658 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Reactivate after suspend(Multi Contract) is Passed");
					}
					else
					{
						System.out.println("PR-2658 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Reactivate after suspend(Multi Contract) is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-2658",driver);
					System.out.println("Test__PR-2658___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			@Test(description="Reactivate after suspend(Multi Room)")
			public void PR_2659() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2659",this);
					System.out.println("PR-2659 Started");
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
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
					Thread.sleep(3000);

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Suspend_Validatation_Msg= oContractCollections.SuspendCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Suspend_Validatation message is:"+Suspend_Validatation_Msg);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Reactivate_Validatation_Msg=oContractCollections.ReactivateCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Reactivate_Validatation message is:"+Reactivate_Validatation_Msg);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					System.out.println(Party_id);
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"'and BIZ_PROCESS_ID=6  order by 1 desc ";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus);
					if(Qstatus.equalsIgnoreCase(C_Status))	
					{
						System.out.println("PR-2659 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Reactivate after suspend(Multi Room) is Passed");
					}
					else
					{
						System.out.println("PR-2659 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Reactivate after suspend(Multi Room) is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-2659",driver);
					System.out.println("Test__PR-2659___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			@Test(description="Reactivate after suspend(Single contract with is _bulk as Y)")
			public void PR_2660() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2660",this);
					System.out.println("PR-2660 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation_WithBulk();
					System.out.println("Customer number is : "+CustomerNumber); 
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
					Thread.sleep(5000);

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Suspend_Validatation_Msg= oContractCollections.SuspendCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Suspend_Validatation message is:"+Suspend_Validatation_Msg);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Reactivate_Validatation_Msg=oContractCollections.ReactivateCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Reactivate_Validatation message is:"+Reactivate_Validatation_Msg);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					System.out.println(Party_id);
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"'and BIZ_PROCESS_ID=6  order by 1 desc ";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus);
					if(Qstatus.equalsIgnoreCase(C_Status))	
					{
						System.out.println("PR-2660 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Reactivate after suspend(Single contract with is _bulk as Y) is Passed");
					}
					else
					{
						System.out.println("PR-2660 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Reactivate after suspend(Single contract with is _bulk as Y) Is failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-2660",driver);
					System.out.println("Test__PR-2660___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			@Test(description="Reactivate with action as Reactivate product(action mapping)")
			public void PR_3390() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3390",this);
					System.out.println("PR-3390 Started");
					Thread.sleep(2000);
					relogin();
					Actionids_List = new ArrayList(10);
					Actionids_List.add("1");
					Actionids_List.add("2");
					Actionids_List.add("22");
					Actionids_List.add("24");
					System.out.println("array list is "+Actionids_List);
					oNavigate.toProvisioningSystemAction();
					Thread.sleep(2000);
					String Screen_Validation=oProvisioningSystemAction.ProvisioningActionMapping(Prov_System_Name,Actionids_List,"Save");
					System.out.println("validation message"+Screen_Validation);

					Thread.sleep(2000);
					relogin();
					Thread.sleep(2000);
					oNavigate.toActivityPrerequisites();
					Thread.sleep(2000);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"1","12","Pre Action","N",1);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"16","87","Post Action","N",2);
					driver.findElement(btnsave).click();
					String Screen_Validation1=driver.findElement(ValidationMessage).getText();
					Thread.sleep(2000);
					System.out.println(Screen_Validation1);
					Thread.sleep(2000);
					relogin();
					Thread.sleep(2000);

					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Suspend_Validatation_Msg= oContractCollections.SuspendCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Suspend_Validatation message is:"+Suspend_Validatation_Msg);
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Reactivate_Validatation_Msg=oContractCollections.ReactivateCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Reactivate_Validatation message is:"+Reactivate_Validatation_Msg);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					System.out.println(Party_id);
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"'and BIZ_PROCESS_ID=6  order by 1 desc ";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus);
					if(Qstatus.equalsIgnoreCase(C_Status))	
					{
						System.out.println("PR-3390 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Reactivate with action as Reactivate product(action mapping) is Passed");
					}
					else
					{
						System.out.println("PR-3390 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Reactivate with action as Reactivate product(action mapping) is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3390",driver);
					System.out.println("Test__PR-3390___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			@Test(description="Reactivate with action as Reactivate all(action mapping)")
			public void PR_2662() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2662",this);
					System.out.println("PR-2662 Started");
					Thread.sleep(2000);
					relogin();
					Actionids_List = new ArrayList(10);
					Actionids_List.add("1");
					Actionids_List.add("2");
					Actionids_List.add("17");
					Actionids_List.add("19");
					System.out.println("array list is "+Actionids_List);
					oNavigate.toProvisioningSystemAction();
					Thread.sleep(2000);
					String Screen_Validation=oProvisioningSystemAction.ProvisioningActionMapping(Prov_System_Name,Actionids_List,"Save");
					System.out.println("validation message"+Screen_Validation);

					Thread.sleep(2000);
					relogin();
					Thread.sleep(2000);
					oNavigate.toActivityPrerequisites();
					Thread.sleep(2000);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"1","12","Pre Action","N",1);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"16","87","Post Action","N",2);
					driver.findElement(btnsave).click();
					String Screen_Validation1=driver.findElement(ValidationMessage).getText();
					Thread.sleep(2000);
					System.out.println(Screen_Validation1);
					Thread.sleep(2000);
					relogin();
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Suspend_Validatation_Msg= oContractCollections.SuspendCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Suspend_Validatation message is:"+Suspend_Validatation_Msg);
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Reactivate_Validatation_Msg=oContractCollections.ReactivateCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Reactivate_Validatation message is:"+Reactivate_Validatation_Msg);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					System.out.println(Party_id);
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"'and BIZ_PROCESS_ID=6  order by 1 desc ";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus);
					if(Qstatus.equalsIgnoreCase(C_Status))						
					{
						System.out.println("PR-2662 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Reactivate with action as Reactivate all(action mapping) is Passed");
					}
					else
					{
						System.out.println("PR-2662 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Reactivate with action as Reactivate all(action mapping) is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-2662",driver);
					System.out.println("Test__PR-2662___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			@Test(description="Reactivate with action as Reactivate productlist(action mapping)")
			public void PR_2663() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2663",this);
					System.out.println("PR-2663 Started");

					Thread.sleep(2000);
					relogin();
					Actionids_List = new ArrayList(10);
					Actionids_List.add("156");
					Actionids_List.add("129");
					Actionids_List.add("158");
					Actionids_List.add("160");
					Actionids_List.add("159");
					System.out.println("array list is "+Actionids_List);
					oNavigate.toProvisioningSystemAction();
					Thread.sleep(2000);
					String Screen_Validation=oProvisioningSystemAction.ProvisioningActionMapping(Prov_System_Name,Actionids_List,"Save");
					System.out.println("validation message"+Screen_Validation);

					Thread.sleep(2000);
					relogin();
					Thread.sleep(2000);
					oNavigate.toActivityPrerequisites();
					Thread.sleep(2000);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"156","12","Pre Action","N",1);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"129","87","Post Action","N",2);
					driver.findElement(btnsave).click();
					String Screen_Validation1=driver.findElement(ValidationMessage).getText();
					Thread.sleep(2000);
					System.out.println(Screen_Validation1);
					Thread.sleep(2000);
					relogin();

					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Suspend_Validatation_Msg= oContractCollections.SuspendCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Suspend_Validatation message is:"+Suspend_Validatation_Msg);
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Reactivate_Validatation_Msg=oContractCollections.ReactivateCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Reactivate_Validatation message is:"+Reactivate_Validatation_Msg);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					System.out.println(Party_id);
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"'and BIZ_PROCESS_ID=6  order by 1 desc ";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus);
					if(Qstatus.equalsIgnoreCase(C_Status))						
					{
						System.out.println("PR-2663 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Reactivate with action as Reactivate productlist(action mapping) is Passed");
					}
					else
					{
						System.out.println("PR-2663 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Reactivate with action as Reactivate productlist(action mapping) Is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-2663",driver);
					System.out.println("Test__PR-2663___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			@Test(description="New order after reactivate")
			public void PR_2670() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2670",this);
					System.out.println("PR-2670 Started");
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(7000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Suspend_Validatation_Msg= oContractCollections.SuspendCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Suspend_Validatation message is:"+Suspend_Validatation_Msg);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Reactivate_Validatation_Msg=oContractCollections.ReactivateCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Reactivate_Validatation message is:"+Reactivate_Validatation_Msg);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();












					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=1 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 





					if(Qstatus.equalsIgnoreCase(C_Status))        	
					{
						System.out.println("PR-2670 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("New order after reactivate Is Passed");
					}
					else
					{
						System.out.println("PR-2670 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("New order after reactivate Is failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-2670",driver);
					System.out.println("Test__PR-2670___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			@Test(description="Change order after reactivate")
			public void PR_2671() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2671",this);
					System.out.println("PR-2671 Started");
					Thread.sleep(2000);
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(7000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Suspend_Validatation_Msg= oContractCollections.SuspendCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Suspend_Validatation message is:"+Suspend_Validatation_Msg);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Reactivate_Validatation_Msg=oContractCollections.ReactivateCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Reactivate_Validatation message is:"+Reactivate_Validatation_Msg);
					Thread.sleep(70000);
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
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					System.out.println(Party_id);
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=2  order by 1 desc ";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus);
					if(Qstatus.equalsIgnoreCase(C_Status))
					{
						System.out.println("PR-2671 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Change order after reactivate is Passed");
					}
					else
					{
						System.out.println("PR-2671 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Change order after reactivate is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-2671",driver);
					System.out.println("Test__PR-2671___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			@Test(description="Disconnection after reactivate")
			public void PR_2672() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2672",this);
					System.out.println("PR-2672 Started");
					Thread.sleep(2000);
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(7000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Suspend_Validatation_Msg= oContractCollections.SuspendCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Suspend_Validatation message is:"+Suspend_Validatation_Msg);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Reactivate_Validatation_Msg=oContractCollections.ReactivateCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Reactivate_Validatation message is:"+Reactivate_Validatation_Msg);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					Thread.sleep(5000);
					oNavigate.toDisconnection();
					Thread.sleep(5000);
					sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
					String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
					System.out.println("Success message is:"+success_Msg);
					System.out.println("Disconnection is done");
					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status))
					{
						System.out.println("PR-2672 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Disconnection after reactivate is Passed");
					}
					else
					{
						System.out.println("PR-2672 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Disconnection after reactivate is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-2672",driver);
					System.out.println("Test__PR-2672___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			@Test(description="Hardware Association change after Reactivate")
			public void PR_2673() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2673",this);
					System.out.println("PR-2673 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(7000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Suspend_Validatation_Msg= oContractCollections.SuspendCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Suspend_Validatation message is:"+Suspend_Validatation_Msg);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Reactivate_Validatation_Msg=oContractCollections.ReactivateCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Reactivate_Validatation message is:"+Reactivate_Validatation_Msg);
					Thread.sleep(70000);
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
						System.out.println("PR-2673 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Hardware Association change after Reactivate is Passed");
					}
					else
					{
						System.out.println("PR-2673 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Hardware Association change after Reactivate is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-2673",driver);
					System.out.println("Test__PR-2673___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			@Test(description="Hardware Change after Reactivate")
			public void PR_2674() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2674",this);
					System.out.println("PR-2674 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(7000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Suspend_Validatation_Msg= oContractCollections.SuspendCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Suspend_Validatation message is:"+Suspend_Validatation_Msg);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Reactivate_Validatation_Msg=oContractCollections.ReactivateCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Reactivate_Validatation message is:"+Reactivate_Validatation_Msg);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					Thread.sleep(2000);
					oNavigate.toHardwareReplacement();
					oWaitTool.implicitWait(20);		        	  
					oHardwareReplacement.HardwareReplacement_Header(CustomerNumber,"", "","",ReasonMRPL,"","","","","","","","","","","New");
					oHardwareReplacement.HardwareReplacement_Item_Dtls(Item1,"",StatusMRPL,Item1,OperationalEntity,Location,OrderType,1);
					driver.findElement(locSaveBtn).click();
					Thread.sleep(40000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
					Thread.sleep(2000);

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					System.out.println(Party_id);
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=3  order by 1 desc ";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus);
					if(Qstatus.equalsIgnoreCase(C_Status))
					{
						System.out.println("PR-2674 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Hardware Change after Reactivate Is Passed");
					}
					else
					{
						System.out.println("PR-2674 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Hardware Change after Reactivate Is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-2674",driver);
					System.out.println("Test__PR-2674___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			@Test(description="Renewal after Reactivate")
			public void PR_2675() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2675",this);
					System.out.println("PR-2675 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(7000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Suspend_Validatation_Msg= oContractCollections.SuspendCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Suspend_Validatation message is:"+Suspend_Validatation_Msg);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();


					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Reactivate_Validatation_Msg=oContractCollections.ReactivateCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Reactivate_Validatation message is:"+Reactivate_Validatation_Msg);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toRenewal();
					RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y","","",contract_validity,"Save");
					System.out.println("Success message is:"+RenewalValidation);
					Thread.sleep(3000);

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					System.out.println(" Party Id is " +Party_id);

					String updateRenewalRequestasSysdate = "update PROVSION_PROCESS_REQUEST set EFFECTIVE_DATE=MQSSYSDATE where PARTY_ID='"+Party_id+"' and BIZ_PROCESS_ID=13";
					this.record1=oDBConnection.updateRecords(updateRenewalRequestasSysdate);
					System.out.println(" Renewal Request Is updated and commited succefully");
					Thread.sleep(2000);
					Thread.sleep(90000);
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13  order by 1 desc ";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus);
					if(Qstatus.equalsIgnoreCase(C_Status))        	
					{
						System.out.println("PR-2675 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Renewal after Reactivate Is Passed");
					}
					else
					{
						System.out.println("PR-2675 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Renewal after Reactivate Is failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-2675",driver);
					System.out.println("Test__PR-2675___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			@Test(description="Reactivate after Suspend (Single Connection)")
			public void PR_2676() throws Exception
			{

				try
				{
					testLinkConnection.setsTestCase("PR-2676",this);
					System.out.println("PR-2676 Started");
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(7000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Suspend_Validatation_Msg= oContractCollections.SuspendCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Suspend_Validatation message is:"+Suspend_Validatation_Msg);

					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					System.out.println(Party_id);
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"'and BIZ_PROCESS_ID=10  order by 1 desc ";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus);
					if(Qstatus.equalsIgnoreCase(C_Status))
					{
						System.out.println("PR-2676 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Reactivate after Suspend (Single Connection) is Passed");
					}
					else
					{
						System.out.println("PR-2676 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Reactivate after Suspend (Single Connection) is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-2676",driver);
					System.out.println("Test__PR-2676___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}




}
