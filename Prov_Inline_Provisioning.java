package ProvisioningTC;

import java.util.ArrayList;
import java.util.Hashtable;

import mq.seleniumFW.connection.MQDBConnection;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import testlink.api.java.client.TestLinkAPIResults;
import TestFrameWork.MQProvisioning;
import Utility.Navigate;
import Utility.WaitTool;

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
import com.ReceivablesPageObject.ContractCollections;

public class Prov_Inline_Provisioning extends MQProvisioning 
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




			protected Prov_Inline_Provisioning(String sLogFileName) throws Exception
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




			@Test(description="Activate a Customer")
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
						testLinkConnection.setsNotes("Activate a Customer is Passed");
					}
					else
					{
						System.out.println("PR-4069 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Activate a Customer is failed");
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


			@Test(description="Disconnecting a Customer")
			public void PR_4072() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4072",this);
					System.out.println("PR-4072 Started");
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					Thread.sleep(3000);
					oNavigate.toDisconnection();
					Thread.sleep(5000);
					sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
					String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
					System.out.println("Success message is:"+success_Msg);
					Thread.sleep(10000);

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					System.out.println(Party_id);
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4 order by 1 desc ";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("IS_PROVISIONED");
					System.out.println(Qstatus);
					if(Qstatus.equalsIgnoreCase(Provisioned_Status))
					{
						System.out.println("PR-4072 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Disconnecting a Customer is Passed");
					}
					else
					{
						System.out.println("PR-4072 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Disconnecting a Customer is Failed");
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


			@Test(description="Reconnecting Customer")
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

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					System.out.println(Party_id);
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7 order by 1 desc ";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("IS_PROVISIONED");
					System.out.println(Qstatus);
					if(Qstatus.equalsIgnoreCase(Provisioned_Status))
					{
						System.out.println("PR-4073 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Reconnecting Customer is Passed");
					}
					else
					{
						System.out.println("PR-4073 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Reconnecting Customer is Failed");
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


			@Test(description="Suspending a customer")
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
					Thread.sleep(5000);
					
					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Suspend_Validatation_Msg= oContractCollections.SuspendCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Suspend_Validatation message is:"+Suspend_Validatation_Msg);
					Thread.sleep(40000);
					
					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					System.out.println(Party_id);
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=10 order by 1 desc ";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("IS_PROVISIONED");
					System.out.println(Qstatus);
					if(Qstatus.equalsIgnoreCase(Provisioned_Status))
					{
						System.out.println("PR-4074 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Suspending a customer is Passed");
					}
					else
					{
						System.out.println("PR-4074 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Suspending a customer is Failed");
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


			@Test(description="Reactivating customer")
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
					Thread.sleep(5000);

					oNavigate.toContractCollection();
					Thread.sleep(3000);
					String Reactivate_Validatation_Msg=oContractCollections.ReactivateCustomer(CustomerNumber,Reason,ContractNumber);
					System.out.println("Reactivate_Validatation message is:"+Reactivate_Validatation_Msg);
					Thread.sleep(40000);
					
					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					System.out.println(Party_id);
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=6 order by 1 desc ";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("IS_PROVISIONED");
					System.out.println(Qstatus);
					if(Qstatus.equalsIgnoreCase(Provisioned_Status))
					{
						System.out.println("PR-4075 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Reactivating customer is Passed");
					}
					else
					{
						System.out.println("PR-4075 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Reactivating customer is Failed");
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


			@Test(description="Renewal a customer")
			public void PR_4076() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4076",this);
					System.out.println("PR-4076 Started");
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

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					System.out.println(Party_id);
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13 order by 1 desc ";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("IS_PROVISIONED");
					System.out.println(Qstatus);
					if(Qstatus.equalsIgnoreCase(Provisioned_Status))
					{
						System.out.println("PR-4076 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Renewal a customer is Passed");
					}
					else
					{
						System.out.println("PR-4076 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Renewal a customer is Failed");
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


			@Test(description="Association change a customer")
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
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=21 order by 1 desc ";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("IS_PROVISIONED");
					System.out.println(Qstatus);
					if(Qstatus.equalsIgnoreCase(Provisioned_Status))
					{
						System.out.println("PR-4077 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Association change a customer is Passed");
					}
					else
					{
						System.out.println("PR-4077 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Association change a customer is failed");
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


			
			@Test(description="Hardware Replacement a Customer")
			public void PR_4055() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4055",this);
					System.out.println("PR-4055 Started");
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Sale", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					Thread.sleep(50000);
					oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

					oNavigate.toHardwareReplacement();
					oWaitTool.implicitWait(20);		        	  
					oHardwareReplacement.HardwareReplacement_Header(CustomerNumber,"", "","",ReasonMRPL,"","","","","","","","","","","New");
					oHardwareReplacement.HardwareReplacement_Item_Dtls(Item1,"",StatusMRPL,Item1,OperationalEntity,Location,OrderType,1);
					driver.findElement(locSaveBtn).click();
					Thread.sleep(20000);

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
						testLinkConnection.setsNotes("Hardware Replacement a Customer is Passed");
					}
					else
					{
						System.out.println("PR-4055 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Hardware Replacement a Customer is Failed");
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











	}
