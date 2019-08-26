package ProvisioningTC.Prov_Collections_TC;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

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

public class Prov_Suspend_Tc extends MQProvisioning

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
			public List<WebElement> listOfRows;
			ArrayList<String> Actionids_List= new ArrayList<String>();
			String Prov_System_Name="NAGRA";
			public String Service_Class="TV Channel";



			protected Prov_Suspend_Tc(String sLogFileName) throws Exception
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




			@Test(description="Suspend Stage Definition")
			public void PR_4069() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4069",this);
					System.out.println("PR-4069 Started");
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					System.out.println(Party_id);
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=1 order by 1 desc ";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("IS_PROVISIONED");
					System.out.println(Qstatus);
					if(Qstatus.equalsIgnoreCase(Provisioned_Status))
					{
						System.out.println("PR-4069 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Suspend Stage Definition is Passed");
					}
					else
					{
						System.out.println("PR-4069 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Suspend Stage Definition is failed");
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


			@Test(description="Suspend after new aggrement(single contract)")
			public void PR_4072() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4072",this);
					System.out.println("PR-4072 Started");
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

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=10 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status))        	
					{
						System.out.println("PR-4072 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Suspend after new aggrement(single contract) is Passed");
					}
					else
					{
						System.out.println("PR-4072 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Suspend after new aggrement(single contract) is Failed");
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


			@Test(description="Suspend after new aggrement(Multi contracts)")
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
					oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
					Thread.sleep(80000);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=10 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status))        	
					{
						System.out.println("PR-4073 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Suspend after new aggrement(Multi contracts) is Passed");
					}
					else
					{
						System.out.println("PR-4073 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Suspend after new aggrement(Multi contracts) is Failed");
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


			@Test(description="Suspend after new aggrement(Multi room)")
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
					oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
					Thread.sleep(80000);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=10 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status))      
					{
						System.out.println("PR-4074 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Suspend after new aggrement(Multi room) is Passed");
					}
					else
					{
						System.out.println("PR-4074 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Suspend after new aggrement(Multi room) is Failed");
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


			@Test(description="Suspend after Single contract with is _bulk as Y")
			public void PR_4075() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4075",this);
					System.out.println("PR-4075 Started");
					Thread.sleep(3000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation_WithBulk();
					System.out.println("Customer number is : "+CustomerNumber); 

					Thread.sleep(80000);
					System.out.println("Waiting is completed and now going  to provisiong request screen ");
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
					Thread.sleep(20000);
					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
					Thread.sleep(80000);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=10 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status)) 

					{
						System.out.println("PR-4075 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Suspend after Single contract with is _bulk as Y is Passed");
					}
					else
					{
						System.out.println("PR-4075 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Suspend after Single contract with is _bulk as Y is Failed");
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


			@Test(description="Suspend after disconnection")
			public void PR_4076() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4076",this);
					System.out.println("PR-4076 Started");
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(50000);
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

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Suspend_Validatation_Msg= oContractCollections.SuspendCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Suspend_Validatation message is:"+Suspend_Validatation_Msg);
					if(Suspend_Validatation_Msg.contains("Valid agreements are not found for customer"))
					{
						System.out.println("PR-4076 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Suspend after disconnection is Passed");
					}
					else
					{
						System.out.println("PR-4076 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Suspend after disconnection is Failed");
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


			@Test(description="Suspend after Reconnection")
			public void PR_4077() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4077",this);
					System.out.println("PR-4077 Started");
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(50000);
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

					oNavigate.toReconnection();
					oWaitTool.implicitWait(10);
					oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
					oWaitTool.implicitWait(50);
					String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
					System.out.println("Success message is:"+success_Msg1);
					System.out.println("Reconnection is done");
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
					Thread.sleep(80000);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=10 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status))       
					{
						System.out.println("PR-4077 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Suspend after Reconnection is Passed");
					}
					else
					{
						System.out.println("PR-4077 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Suspend after Reconnection is failed");
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


			@Test(description="Suspend after hardware change")
			public void PR_4055() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4055",this);
					System.out.println("PR-4055 Started");
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

					Thread.sleep(5000);
					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
					Thread.sleep(80000);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=10 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status))   
					{
						System.out.println("PR-4055 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Suspend after hardware change is Passed");
					}
					else
					{
						System.out.println("PR-4055 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Suspend after hardware change is Failed");
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


			@Test(description="Suspend after Hardware association Change")
			public void PR_2662() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2662",this);
					System.out.println("PR-2662 Started");
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
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
					Thread.sleep(5000);
					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
					Thread.sleep(80000);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=10 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status))						
					{
						System.out.println("PR-2662 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Suspend after Hardware association Change is Passed");
					}
					else
					{
						System.out.println("PR-2662 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Suspend after Hardware association Change is Failed");
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



			@Test(description="Suspend after service down grade")
			public void PR_5074() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-5074",this);
					System.out.println("PR-5074 Started");
					Thread.sleep(2000);
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

					Thread.sleep(5000);
					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
					Thread.sleep(80000);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=10 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status)) 
					{
						System.out.println("PR-5074 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Suspend after service down grade is Passed");
					}
					else
					{
						System.out.println("PR-5074 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Suspend after service down grade is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-5074",driver);
					System.out.println("Test__PR-5074___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			@Test(description="Suspend after Retracking")
			public void PR_5075() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-5075",this);
					System.out.println("PR-5075 Started");
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

					Thread.sleep(5000);
					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
					Thread.sleep(80000);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=10 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status)) 
					{
						System.out.println("PR-5075 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Suspend after Retracking is Passed");
					}
					else
					{
						System.out.println("PR-5075 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Suspend after Retracking is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-5075",driver);
					System.out.println("Test__PR-5075___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			@Test(description="Suspend after change order")
			public void PR_5076() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-5076",this);
					System.out.println("PR-5076 Started");
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

					Thread.sleep(5000);
					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
					Thread.sleep(80000);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=10 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status)) 
					{
						System.out.println("PR-5076 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Suspend after change order is Passed");
					}
					else
					{
						System.out.println("PR-5076 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Suspend after change order is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-5076",driver);
					System.out.println("Test__PR-5076___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			@Test(description="Suspend after Renewal")
			public void PR_5077() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-5077",this);
					System.out.println("PR-5077 Started");
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

					Thread.sleep(5000);
					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
					Thread.sleep(80000);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=10 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status)) 
					{
						System.out.println("PR-5077 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Suspend after Renewal is Passed");
					}
					else
					{
						System.out.println("PR-5077 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Suspend after Renewal is failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-5077",driver);
					System.out.println("Test__PR-5077___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			@Test(description="Suspend with action as suspend product")
			public void PR_2663() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2663",this);
					System.out.println("PR-2663 Started");
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

					Thread.sleep(5000);
					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
					Thread.sleep(80000);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=10 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					if(Qstatus.equalsIgnoreCase(C_Status))
					{
						System.out.println("PR-2663 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Suspend with action as suspend product is Passed");
					}
					else
					{
						System.out.println("PR-2663 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Suspend with action as suspend product is Failed");
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


			@Test(description="Suspend with action as suspend product all")
			public void PR_4070() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4070",this);
					System.out.println("PR-4070 Started");

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

					Thread.sleep(5000);
					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
					Thread.sleep(80000);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=10 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					if(Qstatus.equalsIgnoreCase(C_Status))
					{
						System.out.println("PR-4070 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Suspend with action as suspend product all is Passed");
					}
					else
					{
						System.out.println("PR-4070 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Suspend with action as suspend product all is Failed");
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



			@Test(description="Suspend with action as suspend productlist")
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

					Thread.sleep(5000);
					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
					Thread.sleep(80000);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=67 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					if(Qstatus.equalsIgnoreCase(C_Status))
					{
						System.out.println("PR-4071 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Suspend with action as suspend productlist is Passed");
					}
					else
					{
						System.out.println("PR-4071 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Suspend with action as suspend productlist is failed");
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


			@Test(description="Service down Grade after suspend")
			public void PR_4078() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4078",this);
					System.out.println("PR-4078 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(7000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					Thread.sleep(5000);
					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
					Thread.sleep(80000);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
					Thread.sleep(5000);

					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Service Downgrade", "", "Save");
					String Screen_Validation1=driver.findElement(ValidationMessage).getText();
					Thread.sleep(2000);
					System.out.println(Screen_Validation1);
					if(Screen_Validation1.contains("Cannot downgrade disconnected or suspended contract"))
					{
						System.out.println("PR-4078 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Service down Grade after suspend is Passed");
					}
					else
					{
						System.out.println("PR-4078 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Service down Grade after suspend is Failed");
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


			@Test(description="Reactivate after suspend")
			public void PR_5078() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-5078",this);
					System.out.println("PR-5078 Started");

					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(7000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					Thread.sleep(5000);
					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
					Thread.sleep(80000);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					Thread.sleep(5000);
					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Active", "", "Save");
					Thread.sleep(80000);
					Thread.sleep(70000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=6 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					if(Qstatus.equalsIgnoreCase(C_Status))
					{
						System.out.println("PR-5078 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Reactivate after suspend is Passed");
					}
					else
					{
						System.out.println("PR-5078 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Reactivate after suspend is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-5078",driver);
					System.out.println("Test__PR-5078___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			@Test(description="Suspend after New Aggrement (Single Connection)")
			public void PR_4082() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4082",this);
					System.out.println("PR-4082 Started");
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

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=10 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status))        	
					{
						System.out.println("PR-4082 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Suspend after New Aggrement (Single Connection) is Passed");
					}
					else
					{
						System.out.println("PR-4082 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Suspend after New Aggrement (Single Connection) is Failed");
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


			@Test(description="Suspend after New Aggrement (Multi Connections)")
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
						testLinkConnection.setsNotes("Suspend after New Aggrement (Multi Connections) is Passed");
					}
					else
					{
						System.out.println("PR-4083 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Suspend after New Aggrement (Multi Connections) is Failed");
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


			@Test(description="Multi_connections:-No of  connections as 1 (Add-on and ala carte)")
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
						testLinkConnection.setsNotes("Multi_connections:-No of  connections as 1 (Add-on and ala carte) is Passed");
					}
					else
					{
						System.out.println("PR-4084 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Multi_connections:-No of  connections as 1 (Add-on and ala carte) is Failed");
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


			@Test(description="Multi_Connections:-No of  connections as 2 (Add-on and ala carte)")
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
						testLinkConnection.setsNotes("Retracking Customer having Multiple contracts on Single card is Passed");
					}
					else
					{
						System.out.println("PR-4085 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Retracking Customer having Multiple contracts on Single card is failed");
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




}
