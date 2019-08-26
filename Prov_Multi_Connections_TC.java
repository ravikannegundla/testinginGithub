package ProvisioningTC;

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
import TestFrameWork.MQProvisioning;
import Utility.Navigate;
import Utility.WaitTool;

import com.BasicConfigurationInventory.InventoryTransactions;
import com.BasicConfigurationsOfServiceOrder.BasicConfigurationsOfServiceOrder;
import com.BasicConfigurationsOfServiceOrder.OrderManagementPrivileges;
import com.InventoryAndLogisticsPageObject.HardwareReplacement;
import com.InventoryAndLogisticsPageObject.OneTimeSaleOrder;
import com.InventoryAndLogisticsPageObject.OwnedHardware;
import com.JobsPageObject.ScheduleJob;
import com.MulticonnectionPageObject.Multiconnection;
import com.OrderManagementPageObject.CustomerRegistration;
import com.OrderManagementPageObject.Disconnection;
import com.OrderManagementPageObject.HardwareAssociation;
import com.OrderManagementPageObject.NumberReservation;
import com.OrderManagementPageObject.Reconnection;
import com.OrderManagementPageObject.Renewal;
import com.OrderManagementPageObject.ServiceOrder;
import com.PageObjects.OrderManagementMasters.PricingMaster.PricePlan;
import com.ProvisioningPageObject.BasicConfigurationsOfProvisioning;
import com.ProvisioningPageObject.ProvisioningRequests;
import com.ProvisioningPageObject.ProvisioningSetUp;
import com.ProvisioningPageObject.ProvisioningSystemCreation;
import com.ProvisioningPageObject.Retracking;
import com.ReceivablesPageObject.Collections;
import com.ReceivablesPageObject.ContractCollections;
import com.ReceivablesPageObject.CreditAllocation;

public class Prov_Multi_Connections_TC extends MQProvisioning

{

	
				WebDriver driver;
				Navigate oNavigate;
				WaitTool oWaitTool;
				CustomerRegistration oCustomerRegistration;
				NumberReservation oNumberReservation;
				ServiceOrder oserviceorder;
				ProvisioningRequests oProvisioningRequests;
				ProvisioningSetUp oProvisioningSetUp;
				ScheduleJob oScheduleJob;
				OwnedHardware oOwnedHardware;
				PricePlan oPricePlan;
				HardwareAssociation oHardwareAssociation;
				public OneTimeSaleOrder OTS;
				BasicConfigurationsOfProvisioning oBasicConfigurationsOfProvisioning;
				Disconnection oDisconnection;
				Reconnection oReconnection;
				HardwareReplacement oHardwareReplacement;
				CreditAllocation ocreditallocation;
				Renewal oRenewal;
				BasicConfigurationsOfServiceOrder oBasicConfigurationsOfServiceOrder;
				Multiconnection oMulticonnection;
				Collections oCollections;
				OrderManagementPrivileges oOrderManagementPrivileges;
				ContractCollections oContractCollections;
				Retracking oRetracking;
				ProvisioningSystemCreation oProvisioningSystemCreation;

				public By locPlanSearchTab = By.id("ctl00_uxPgCPH_txtplnsrch");
				public By locNotes = By.id("ctl00_uxPgCPH_uxNotes");
				public By locTxtCustNo = By.id("ctl00_uxPgCPH_uxSearchCtrl_txtCustnbr");
				public By locAddToCart = By.xpath(".//*[@id='ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd']");
				public By locOrderDate = By.id("ctl00_uxPgCPH_uxOrderDate");
				public By locNoOfOutlets = By.id("ctl00_uxPgCPH_txtNofooutlets");
				public By locNoOfRooms = By.id("ctl00_uxPgCPH_txtNoofrooms");
				public By locFlex = By.id("ctl00_uxPgCPH_custflexattr__flexattr_F");
				public By locapprove=By.id("ctl00_uxPgCPH_chkapprove");
				public By locbtnsavepost = By.name("ctl00$uxPgCPH$btnsavepost");
				public By ScreenOutPut =By.xpath(".//*[@id='ctl00__infoSpan']");
				public By locApprove = By.id("ctl00_uxPgCPH_chkapprove");
				public By locsuccessmsg=By.xpath(".//*[@id='ctl00__infoSpan']");
				public By locNewContract = By.id("ctl00_uxPgCPH_chknewcontract");
				public By locModify = By.id("ctl00_uxPgCPH_grdcontractdtls_ctl02_lnkmodify");
				public By locContractNumber = By.xpath(".//*[@id='ctl00_uxPgCPH_txtcontractno']");

				public ArrayList<Hashtable<String, String>> records;
				public Hashtable<String,String> record;
				public MQDBConnection oDBConnection;
				String strPlanCode = "PLAN1SERV1";
				String fixedTerm_strPlanCode = "PLAN1PKG";
				String billing_Frequency = "One Month";
				String contract_validity = "One Month";
				String ChangeContractValidity="Three Month";
				public String noOfOutLet1 = "1";

				String first_Name = "Ravi";
				String last_Name = "Kannegundla";

				WebElement status;
				WebElement validation;
				WebElement contract;
				WebElement sCalPriceBtn;
				String disconnectedOn = "23-01-2019";
				public By locSaveBtn=By.id("ctl00_uxPgCPH_btnsave");
				public String PLANOPTIONAL ;

				String locEndDate = "06-06-9999";
				String validationMsgInactive;
				String Save_Btn = "Save";

				String orderDate="01-05-2019";
				String	currentDate="01-05-2019";
				String effectiveDate="01-05-2019";
				String backDated="01-04-2019";
				String futureDate="31-12-2025";
				String auto_Approve="Y";
				String Status,Status1="C",Status2="N",StatusMRPL="Faulty";
				String C_Status="C",N_Status="N";
				String Party_id,Qstatus,Customer_id;
				String Item1="Smart Card",Item2="Set Top Box";

				String mrnNumber;
				public String Inventory_Location="Head Location",Supplier="Conax Supplier";
				public By loctxtCustNum=By.id("ctl00_uxPgCPH_uxSearchCtrl_txtCustnbr");
				public By locMsg=By.id("ctl00__infoSpan");
				String PartyClass="Customer", Entity="CORP",Location="Head Location",Succ_Msg;
				public InventoryTransactions invTrans;
				String item3="Cable";
				String HO_UserName = "KRAVI";
				String HO_Password = "KRAVI1234";
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
				
				String Prov_System_Name="NAGRA";
				String Prov_System_Code="NAGRA";
				

				public List<WebElement> listOfRows;
				public     String ScreenValidation1,Sent="Sent",Accept="Accept",New="New";

				String sResult,Result,sRes;
				String sQuery,CustomerNumber;
				String getConnectionNumber,Connection_Number,Screen_Validation;


				protected Prov_Multi_Connections_TC(String sLogFileName) throws Exception 
				{
					super(sLogFileName);
					this.oDBConnection=dbConnection;
					PageFactory.initElements(driver, this);
				}

				@Override
				protected void beforeClass() 
				{
					driver=getDriver();
					driver.manage().window().maximize();
					this.oCustomerRegistration = new CustomerRegistration(driver);
					this.oNumberReservation = new NumberReservation(driver);
					this.oserviceorder=new ServiceOrder(driver,dbConnection);
					this.oProvisioningRequests =new ProvisioningRequests(driver);
					this.oProvisioningSetUp =new ProvisioningSetUp(driver);
					this.oScheduleJob = new ScheduleJob(driver);
					this.oBasicConfigurationsOfServiceOrder=new BasicConfigurationsOfServiceOrder(driver, oDBConnection);
					this.oOwnedHardware=new OwnedHardware(driver, oDBConnection);
					this.OTS=new OneTimeSaleOrder (driver, dbConnection);
					this.invTrans=new InventoryTransactions(driver, dbConnection);
					this.oHardwareAssociation=new HardwareAssociation(driver,dbConnection);
					this.oMulticonnection=new  Multiconnection(driver);
					this.oBasicConfigurationsOfProvisioning=new BasicConfigurationsOfProvisioning(driver,dbConnection);
					this.oDisconnection = new Disconnection(driver);
					this.oReconnection = new Reconnection(driver);
					this.oHardwareReplacement= new HardwareReplacement(driver,dbConnection);
					this.oCollections=new Collections(driver,dbConnection);
					this.oRenewal = new Renewal(driver);
					this.oOrderManagementPrivileges=new OrderManagementPrivileges(dbConnection);
					this.oContractCollections=new ContractCollections(driver,dbConnection);
					this.oRetracking=new Retracking(driver,dbConnection);
					this.oProvisioningSystemCreation= new ProvisioningSystemCreation(driver,dbConnection);

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



				public void relogin() throws Exception
				{
					oNavigate.Navigation_Action("Logout");
					oWaitTool.implicitWait(10);		
					//		 verifyLogin("KRAVI","KRAVI1234");
					verifyLogin("SYSADMIN","SYSADMIN");
					oWaitTool.implicitWait(10);
				}



				//To generate Random numbers      
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



				@Test(description="Action mapping")
				public void PR_3752() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3752",this);
						System.out.println("PR-3752 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
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
						System.out.println(Qstatus); 
						if(Qstatus.equalsIgnoreCase(C_Status))       
						{
							System.out.println("PR-3752 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Action mapping is Passed");
						}
						else
						{
							System.out.println("PR-3752 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Action mapping is Failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3752",driver);
						System.out.println("Test__PR-3752___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Activation for Single Room Customer")
				public void PR_3753() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3753",this);
						System.out.println("PR-3753 Started");
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
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
						System.out.println(Qstatus); 
						if(Qstatus.equalsIgnoreCase(C_Status))       
						{
							System.out.println("PR-3753 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Activation for Single Room Customer is Passed");
						}
						else
						{
							System.out.println("PR-3753 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Activation for Single Room Customer is Failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3753",driver);
						System.out.println("Test__PR-3753___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Disconnection of contract for Single Room Customer")
				public void PR_3754() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3754",this);
						System.out.println("PR-3754 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
						Thread.sleep(3000);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);

						Thread.sleep(5000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();           

						Thread.sleep(2000);
						oNavigate.toDisconnection();
						Thread.sleep(5000);
						logger.info("Successfully Navigated to Disconnection Screen ");
						sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
						String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
						System.out.println("Success message is:"+success_Msg);
						System.out.println("Disconnection is done");

						Thread.sleep(2000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();           

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
							System.out.println("PR-3754 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Disconnection of contract for Single Room Customer is Passed");
						}
						else
						{
							System.out.println("PR-3754 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Disconnection of contract for Single Room Customer is failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3754",driver);
						System.out.println("Test__PR-3754___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Disconnection of contract from collections screen for Single Room Customer")
				public void PR_3755() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3755",this);
						System.out.println("PR-3755 Started");
						Thread.sleep(2000);
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
							System.out.println("PR-3755 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Disconnection of contract from collections screen for Single Room Customer is passed");
						}
						else
						{
							System.out.println("PR-3755 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Disconnection of contract from collections screen for Single Room Customer Is Failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3755",driver);
						System.out.println("Test__PR-3755___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Reconnection of contract For Single Room Customer")
				public void PR_3756() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3756",this);
						System.out.println("PR-3756 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
						Thread.sleep(3000);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(5000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();           

						Thread.sleep(2000);
						oNavigate.toDisconnection();
						Thread.sleep(5000);
						sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
						String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
						System.out.println("Success message is:"+success_Msg);
						System.out.println("Disconnection is done");
						Thread.sleep(2000);
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

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						System.out.println(Party_id);
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7 order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println("status of the request is :"+Qstatus+""); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-3756 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Reconnection of contract For Single Room Customer is Passed");
						}
						else
						{
							System.out.println("PR-3756 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Reconnection of contract For Single Room Customer Is failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3756",driver);
						System.out.println("Test__PR-3756___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Change order for Single Room Customer")
				public void PR_3757() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3757",this);
						System.out.println("PR-3757 Started");
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
						Thread.sleep(3000);
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

						Thread.sleep(2000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						System.out.println(Party_id);
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=2 order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println("status of the request is :"+Qstatus+""); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-3757 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Change order for Single Room Customer Passed");
						}
						else
						{
							System.out.println("PR-3757 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Change order for Single Room Customer is Failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3757",driver);
						System.out.println("Test__PR-3757___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Service_downgrade for Single Room Customer")
				public void PR_3759() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3759",this);
						System.out.println("PR-3759 Started");
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

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						System.out.println(Party_id);
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=67 order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println("status of the request is :"+Qstatus+""); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-3759 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Service_downgrade for Single Room Customer is Passed");
						}
						else
						{
							System.out.println("PR-3759 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Service_downgrade for Single Room Customer is Failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3759",driver);
						System.out.println("Test__PR-3759___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Suspend for Single Room Customer")
				public void PR_3760() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3760",this);
						System.out.println("PR-3760 Started");
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
							System.out.println("PR-3760 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Suspend for Single Room Customer is Passed");
						}
						else
						{
							System.out.println("PR-3760 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Suspend for Single Room Customer is Failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3760",driver);
						System.out.println("Test__PR-3760___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Suspend From Contract collections for Single Room Customer")
				public void PR_3762() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3762",this);
						System.out.println("PR-3762 Started");
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
							System.out.println("PR-3762 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Suspend From Contract collections for Single Room Customer is Passed");
						}
						else
						{
							System.out.println("PR-3762 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Suspend From Contract collections for Single Room Customer is Failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3762",driver);
						System.out.println("Test__PR-3762___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Reactivate for Single Room Customer")
				public void PR_3763() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3763",this);
						System.out.println("PR-3763 Started");
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
						System.out.println(Qstatus); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-3763 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Reactivate for Single Room Customer Is Passed");
						}
						else
						{
							System.out.println("PR-3763 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Reactivate for Single Room Customer is failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3763",driver);
						System.out.println("Test__PR-3763___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Reactivate from Contract Collections for Single Room Customer")
				public void PR_3764() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3764",this);
						System.out.println("PR-3764 Started");
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
							System.out.println("PR-3764 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Reactivate from Contract Collections for Single Room Customer is Passed");
						}
						else
						{
							System.out.println("PR-3764 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Reactivate from Contract Collections for Single Room Customer is failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3764",driver);
						System.out.println("Test__PR-3764___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Renewal the contract for Single Room Customer with Data_driven as 'Y'")
				public void PR_3765() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3765",this);
						System.out.println("PR-3765 Started");
						Thread.sleep(2000);
						oNavigate.toProvisioningSystem();
						Thread.sleep(2000);
						Screen_Validation=oProvisioningSystemCreation.ProvisioningSystem(Prov_System_Code,Prov_System_Name,"", "", "", "", "Y", "", "","GMT+05:30", "", "", "", "", "","Save") ;
						System.out.println("validation message"+Screen_Validation);
						Thread.sleep(2000);
						relogin();
						
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
							System.out.println("PR-3765 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Renewal the contract for Single Room Customer with Data_driven as 'Y' is Passed");
						}
						else
						{
							System.out.println("PR-3765 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Renewal the contract for Single Room Customer with Data_driven as 'Y' is failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3765",driver);
						System.out.println("Test__PR-3765___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Renewal the contract for Single Room Customer with Data_driven as 'N'")
				public void PR_3766() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3766",this);
						System.out.println("PR-3766 Started");
						Thread.sleep(2000);
						oNavigate.toProvisioningSystem();
						Thread.sleep(2000);
						Screen_Validation=oProvisioningSystemCreation.ProvisioningSystem(Prov_System_Code,Prov_System_Name,"", "", "", "", "N", "", "","GMT+05:30", "", "", "", "", "","Save") ;
						System.out.println("validation message"+Screen_Validation);
						Thread.sleep(2000);
						relogin();
						
						
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
							System.out.println("PR-3766 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Renewal the contract for Single Room Customer with Data_driven as 'N' is Passed");
						}
						else
						{
							System.out.println("PR-3766 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Renewal the contract for Single Room Customer with Data_driven as 'N' is Failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3766",driver);
						System.out.println("Test__PR-3766___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Association change  For Single Room Customer")
				public void PR_3767() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3767",this);
						System.out.println("PR-3767 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(7000);
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
							System.out.println("PR-3767 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Association change  For Single Room Customer is Passed");
						}
						else
						{
							System.out.println("PR-3767 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Association change  For Single Room Customer is Failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3767",driver);
						System.out.println("Test__PR-3767___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Hardware Replacement  For Single Room Customer")
				public void PR_3768() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3768",this);
						System.out.println("PR-3768 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Sale", "", "","",strPlanCode,"Y",billing_Frequency);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(7000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

						Thread.sleep(2000);
						oNavigate.toHardwareReplacement();
						oWaitTool.implicitWait(20);		        	  
						oHardwareReplacement.HardwareReplacement_Header(CustomerNumber,"", "","",ReasonMRPL,"","","","","","","","","","","New");
						oHardwareReplacement.HardwareReplacement_Item_Dtls(Item1,"",StatusMRPL,Item1,OperationalEntity,Location,OrderType,1);
						driver.findElement(locSaveBtn).click();

						Thread.sleep(5000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						System.out.println(Party_id);
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=3 order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println("status of the request is :"+Qstatus+""); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-3768 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Hardware Replacement  For Single Room Customer is Passed");
						}
						else
						{
							System.out.println("PR-3768 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Hardware Replacement  For Single Room Customer is Failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3768",driver);
						System.out.println("Test__PR-3768___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Retracking  for Single Room Customer")
				public void PR_3769() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3769",this);
						System.out.println("PR-3769 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(7000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

						oNavigate.toRetracking();
						oRetracking.RetrackingScreen(CustomerNumber, "Service",1, "N", "Save");
						Thread.sleep(5000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(5000);

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID in (17,18) order by 1 desc";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-3769 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Retracking  for Single Room Customer is Passed");
						}
						else
						{
							System.out.println("PR-3769 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Retracking  for Single Room Customer is Failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3769",driver);
						System.out.println("Test__PR-3769___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}


				@Test(description="Activation for Multi Room Customer")
				public void PR_3770() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3770",this);
						System.out.println("PR-3770 Started");
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
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(90000);
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
							System.out.println("PR-3770 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Activation for Multi Room Customer is Passed");
						}
						else
						{
							System.out.println("PR-3770 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Activation for Multi Room Customer is failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3770",driver);
						System.out.println("Test__PR-3770___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Disconnection of contract for Multi Room Customer")
				public void PR_3771() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3771",this);
						System.out.println("PR-3771 Started");
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
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(90000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);

						oNavigate.toDisconnection();
						Thread.sleep(5000);
						sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
						String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
						System.out.println("Success message is:"+success_Msg);
						System.out.println("Disconnection is done");

						Thread.sleep(2000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();           

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
							System.out.println("PR-3771 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Disconnection of contract for Multi Room Customer is Passed");
						}
						else
						{
							System.out.println("PR-3771 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Disconnection of contract for Multi Room Customer is Failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3771",driver);
						System.out.println("Test__PR-3771___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Disconnection of contract from collections screen for Multi Room Customer")
				public void PR_3772() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3772",this);
						System.out.println("PR-3772 Started");
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
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(90000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);

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
							System.out.println("PR-3772 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Disconnection of contract from collections screen for Multi Room Customer is Passed");
						}
						else
						{
							System.out.println("PR-3772 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Disconnection of contract from collections screen for Multi Room Customer Is Failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3772",driver);
						System.out.println("Test__PR-3772___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Reconnection of contract for Multi Room Customer")
				public void PR_3773() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3773",this);
						System.out.println("PR-3773 Started");
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
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(90000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);

						oNavigate.toDisconnection();
						Thread.sleep(5000);
						sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
						String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
						System.out.println("Success message is:"+success_Msg);
						System.out.println("Disconnection is done");
						Thread.sleep(20000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();           

						oNavigate.toReconnection();
						oWaitTool.implicitWait(10);
						oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
						oWaitTool.implicitWait(50);
						String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
						System.out.println("Success message is:"+success_Msg1);
						System.out.println("Reconnection is done");
						Thread.sleep(20000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						System.out.println(Party_id);
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7 order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println("status of the request is :"+Qstatus+""); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-3773 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Reconnection of contract for Multi Room Customer is Passed");
						}
						else
						{
							System.out.println("PR-3773 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Reconnection of contract for Multi Room Customer is Failed"); 
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3773",driver);
						System.out.println("Test__PR-3773___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Change order for Multi Room Customer")
				public void PR_3774() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3774",this);
						System.out.println("PR-3774 Started");
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
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(90000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);


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

						Thread.sleep(2000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						System.out.println(Party_id);
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=2 order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println("status of the request is :"+Qstatus+""); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-3774 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Change order for Multi Room Customer is Passed");
						}
						else
						{
							System.out.println("PR-3774 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Change order for Multi Room Customer is failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3774",driver);
						System.out.println("Test__PR-3774__%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Service_downgrade for Multi Room Customer")
				public void PR_3775() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3775",this);
						System.out.println("PR-3775 Started");
						Thread.sleep(3000);
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
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(90000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);
						Thread.sleep(5000);
						oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
						Thread.sleep(5000);
						oCollections.collectionGrid("", "","Service Downgrade", "", "Save");
						System.out.println("Service Downgrade done");
						Thread.sleep(70000);
						Thread.sleep(50000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						System.out.println(Party_id);
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=67 order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println("status of the request is :"+Qstatus+""); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-3775 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Service_downgrade for Multi Room Customer is Passed");
						}
						else
						{
							System.out.println("PR-3775 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Service_downgrade for Multi Room Customer is Failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3775",driver);
						System.out.println("Test__PR-3775___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Suspend for Multi Room Customer")
				public void PR_3776() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3776",this);
						System.out.println("PR-3776 Started");
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
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
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
							System.out.println("PR-3776 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Suspend for Multi Room Customer is Passed");
						}
						else
						{
							System.out.println("PR-3776 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Suspend for Multi Room Customer is Failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3776",driver);
						System.out.println("Test__PR-3776___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Suspend From Contract collections for Multi Room Customer")
				public void PR_3777() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3777",this);
						System.out.println("PR-3777 Started");
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
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(90000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);

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
							System.out.println("PR-3777 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Suspend From Contract collections for Multi Room Customer is Passed");
						}
						else
						{
							System.out.println("PR-3777 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Suspend From Contract collections for Multi Room Customer is Failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3777",driver);
						System.out.println("Test__PR-3777___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Reactivate for Multi Room Customer")
				public void PR_3778() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3778",this);
						System.out.println("PR-3778 Started");
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
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
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
						System.out.println(Qstatus); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-3778 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Reactivate for Multi Room Customer is Passed");
						}
						else
						{
							System.out.println("PR-3778 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Reactivate for Multi Room Customer is Failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3778",driver);
						System.out.println("Test__PR-3778___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Reactivate from Contract Collections for Multi Room Customer")
				public void PR_3779() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3779",this);
						System.out.println("PR-3779 Started");
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
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(90000);
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
							System.out.println("PR-3779 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Reactivate from Contract Collections for Multi Room Customer is Passed");
						}
						else
						{
							System.out.println("PR-3779 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Reactivate from Contract Collections for Multi Room Customer is Failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3779",driver);
						System.out.println("Test__PR-3779___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Renewal the contract for Multi Room Customer with Data_driven as 'Y'")
				public void PR_3780() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3780",this);
						System.out.println("PR-3780 Started");
						
						Thread.sleep(2000);
						oNavigate.toProvisioningSystem();
						Thread.sleep(2000);
						Screen_Validation=oProvisioningSystemCreation.ProvisioningSystem(Prov_System_Code,Prov_System_Name,"", "", "", "", "Y", "", "","GMT+05:30", "", "", "", "", "","Save") ;
						System.out.println("validation message"+Screen_Validation);
						Thread.sleep(2000);
						relogin();
						
						
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
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(90000);
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
							System.out.println("PR-3780 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Renewal the contract for Multi Room Customer with Data_driven as 'Y' is Passed");
						}
						else
						{
							System.out.println("PR-3780 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Renewal the contract for Multi Room Customer with Data_driven as 'Y' is Failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3780",driver);
						System.out.println("Test__PR-3780___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Renewal the contract for Multi Room Customer with Data_driven as 'N'")
				public void PR_3781() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3781",this);
						System.out.println("PR-3781 Started");
						Thread.sleep(2000);
						oNavigate.toProvisioningSystem();
						Thread.sleep(2000);
						Screen_Validation=oProvisioningSystemCreation.ProvisioningSystem(Prov_System_Code,Prov_System_Name,"", "", "", "", "N", "", "","GMT+05:30", "", "", "", "", "","Save") ;
						System.out.println("validation message"+Screen_Validation);
						Thread.sleep(2000);
						relogin();
						
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
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(90000);
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
							System.out.println("PR-3781 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Renewal the contract for Multi Room Customer with Data_driven as 'N' Is Passed");
						}
						else
						{
							System.out.println("PR-3781 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Renewal the contract for Multi Room Customer with Data_driven as 'N' is Failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3781",driver);
						System.out.println("Test__PR-3781___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Association change  For Multi Room Customer")
				public void PR_3782() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3782",this);
						System.out.println("PR-3782 Started");
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
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(90000);
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
							System.out.println("PR-3782 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Association change  For Multi Room Customer is Passed");
						}
						else
						{
							System.out.println("PR-3782 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Association change  For Multi Room Customer is Failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3782",driver);
						System.out.println("Test__PR-3782___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Hardware Replacement  For Multi Room Customer")
				public void PR_3783() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3783",this);
						System.out.println("PR-3783 Started");
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
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(90000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);


						Thread.sleep(2000);
						oNavigate.toHardwareReplacement();
						oWaitTool.implicitWait(20);		        	  
						oHardwareReplacement.HardwareReplacement_Header(CustomerNumber,"", "","",ReasonMRPL,"","","","","","","","","","","New");
						oHardwareReplacement.HardwareReplacement_Item_Dtls(Item1,"",StatusMRPL,Item1,OperationalEntity,Location,OrderType,1);
						driver.findElement(locSaveBtn).click();

						Thread.sleep(5000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						System.out.println(Party_id);
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=3 order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println("status of the request is :"+Qstatus+""); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-3783 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Hardware Replacement  For Multi Room Customer is Passed");
						}
						else
						{
							System.out.println("PR-3783 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Hardware Replacement  For Multi Room Customer is Failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3783",driver);
						System.out.println("Test__PR-3783___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Retracking  for Multi Room Customer")
				public void PR_3784() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3784",this);
						System.out.println("PR-3784 Started");
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
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(90000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);

						oNavigate.toRetracking();
						oRetracking.RetrackingScreen(CustomerNumber, "Service",1, "N", "Save");
						Thread.sleep(5000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(5000);

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID in (17,18) order by 1 desc";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-3784 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Retracking  for Multi Room Customer is Passed");
						}
						else
						{
							System.out.println("PR-3784 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Retracking  for Multi Room Customer is Failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3784",driver);
						System.out.println("Test__PR-3784___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Activation of a  Customer with is_bulk as 'Y'")
				public void PR_3785() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3785",this);
						System.out.println("PR-3785 Started");
						Thread.sleep(3000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation_WithBulk();
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
							System.out.println("PR-3785 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Activation of a  Customer with is_bulk as 'Y' is Passed");
						}
						else
						{
							System.out.println("PR-3785 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Activation of a  Customer with is_bulk as 'Y' is Failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3785",driver);
						System.out.println("Test__PR-3785___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}

				

				@Test(description="Disconnection of a  Customer with is_bulk as 'Y'")
				public void PR_3786() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3786",this);
						System.out.println("PR-3786 Started");
						Thread.sleep(3000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation_WithBulk();
						Thread.sleep(80000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
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
							System.out.println("PR-3786 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Disconnection of a  Customer with is_bulk as 'Y' is Passed");
						}
						else
						{
							System.out.println("PR-3786 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Disconnection of a  Customer with is_bulk as 'Y' is failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3786",driver);
						System.out.println("Test__PR-3786___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Disconnection of a  Customer from collections screen with is_bulk as 'Y'")
				public void PR_3787() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3787",this);
						System.out.println("PR-3787 Started");
						Thread.sleep(3000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation_WithBulk();
						Thread.sleep(80000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);
						
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
							System.out.println("PR-3787 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Disconnection of a  Customer from collections screen with is_bulk as 'Y' is Passed");
						}
						else
						{
							System.out.println("PR-3787 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Disconnection of a  Customer from collections screen with is_bulk as 'Y' is failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3787",driver);
						System.out.println("Test__PR-3787___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Reconnection of a  Customer with is_bulk as 'Y'")
				public void PR_3788() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3788",this);
						System.out.println("PR-3788 Started");
						Thread.sleep(3000);
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
							System.out.println("PR-3788 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Reconnection of a  Customer with is_bulk as 'Y' is Passed");
						}
						else
						{
							System.out.println("PR-3788 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Reconnection of a  Customer with is_bulk as 'Y' is failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3788",driver);
						System.out.println("Test__PR-3788___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Change order of a  Customer with is_bulk as 'Y'")
				public void PR_3789() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3789",this);
						System.out.println("PR-3789 Started");
						Thread.sleep(3000);
						 CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation_WithBulk();
		            	  System.out.println("Customer number is : "+CustomerNumber); 

		            	  Thread.sleep(80000);
		            	  System.out.println("Waiting is completed and now going  to provisiong request screen ");
		            	  oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
		            	  Thread.sleep(3000);
			      		  oNavigate.toServiceOrder();
			              oWaitTool.implicitWait(100);
			              driver.findElement(locModify).click();
			              Thread.sleep(5000);
			              driver.findElement(locApprove).click();
			              Thread.sleep(2000);
			              driver.findElement(By.id("ctl00_uxPgCPH_grdproductpricedtls_ctl02_imgbtnremove1")).click();
			              Thread.sleep(3000);
			              driver.findElement(locPlanSearchTab).sendKeys("NAGRA100"+Keys.TAB);
			              Thread.sleep(1000);
			              driver.findElement(locAddToCart).click();
			              Thread.sleep(1000);
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
			       	      System.out.println("Hardware Association Is Done Successfully After Change aggrement");
			       	      Thread.sleep(70000);
			       	      oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

			       	      String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
			       	      this.records=oDBConnection.fecthRecords(Customer_id);
			       	      this.record=this.records.get(0);
			       	      String Party_id=record.get("CUSTOMER_ID");
			       	      System.out.println(Party_id);
			       	      String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=2 order by 1 desc ";
		                  this.records=oDBConnection.fecthRecords(Status);
		                  this.record=this.records.get(0);
		                  String Qstatus=record.get("STATUS");
		                  System.out.println("status of the request is :"+Qstatus+""); 
			              if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-3789 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Change order of a  Customer with is_bulk as 'Y' is Passed");
						}
						else
						{
							System.out.println("PR-3789 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Change order of a  Customer with is_bulk as 'Y' is failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3789",driver);
						System.out.println("Test__PR-3789___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}



				@Test(description="Service_downgrade of a  Customer with is_bulk as 'Y'")
				public void PR_4478() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4478",this);
						System.out.println("PR-4478 Started");
						Thread.sleep(3000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation_WithBulk();
						Thread.sleep(80000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);

						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=2 order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println("status of the request is :"+Qstatus+""); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-4478 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Service_downgrade of a  Customer with is_bulk as 'Y' is Passed");
						}
						else
						{
							System.out.println("PR-4478 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Service_downgrade of a  Customer with is_bulk as 'Y' is Failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-4478",driver);
						System.out.println("Test__PR-4478___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}
















				@Test(description="Suspend of a  Customer with is_bulk as 'Y'")
				public void PR_3975() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3975",this);
						System.out.println("PR-3975 Started");
						Thread.sleep(3000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation_WithBulk();
						Thread.sleep(80000);
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
							System.out.println("PR-3975 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Suspend of a  Customer with is_bulk as 'Y' is Done");
						}
						else
						{
							System.out.println("PR-3975 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Suspend of a  Customer with is_bulk as 'Y' Is Failed");
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



				@Test(description="Suspend of a  Customer with is_bulk as 'Y' From Contract collections Screen")
				public void PR_3976() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3976",this);
						System.out.println("PR-3976 Started");
						Thread.sleep(3000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation_WithBulk();
						Thread.sleep(80000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
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

						oNavigate.toContractCollection();
						Thread.sleep(3000);
						String Suspend_Validatation_Msg= oContractCollections.SuspendCustomer(CustomerNumber,Reason,ContractNumber);
						System.out.println("Suspend_Validatation message is:"+Suspend_Validatation_Msg);
						Thread.sleep(70000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"'and BIZ_PROCESS_ID=10  order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println(Qstatus);
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-3976 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Suspend of a  Customer with is_bulk as 'Y' From Contract collections Screen is Done");
						}
						else
						{
							System.out.println("PR-3976 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Suspend of a  Customer with is_bulk as 'Y' From Contract collections Screen is Failed");
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



				@Test(description="Reactivate of a  Customer with is_bulk as 'Y'")
				public void PR_3977() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3977",this);
						System.out.println("PR-3977 Started");
						Thread.sleep(3000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation_WithBulk();
						Thread.sleep(80000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);

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
						System.out.println(Qstatus); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-3977 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Reactivate of a  Customer with is_bulk as 'Y' Is Done");
						}
						else
						{
							System.out.println("PR-3977 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Reactivate of a  Customer with is_bulk as 'Y' is Failed");
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


				@Test(description="Reactivate of a  Customer with is_bulk as 'Y' From Contract collections Screen")
				public void PR_3978() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3978",this);
						System.out.println("PR-3978 Started");
						Thread.sleep(3000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation_WithBulk();
						Thread.sleep(80000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
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


						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"'and BIZ_PROCESS_ID=6  order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println(Qstatus);
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-3978 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Reactivate of a  Customer with is_bulk as 'Y' From Contract collections Screen is Done");
						}
						else
						{
							System.out.println("PR-3978 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Reactivate of a  Customer with is_bulk as 'Y' From Contract collections Screen is Failed");
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



				@Test(description="Renewal of a  Customer with is_bulk as 'Y' with Data_driven as 'Y'")
				public void PR_3979() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3979",this);
						System.out.println("PR-3979 Started");
						Thread.sleep(3000);
						oNavigate.toProvisioningSystem();
						Thread.sleep(2000);
						Screen_Validation=oProvisioningSystemCreation.ProvisioningSystem(Prov_System_Code,Prov_System_Name,"", "", "", "", "Y", "", "","GMT+05:30", "", "", "", "", "","Save") ;
						System.out.println("validation message"+Screen_Validation);
						Thread.sleep(2000);
						relogin();
						
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation_WithBulk();
						Thread.sleep(80000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
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

						oNavigate.toRenewal();
						RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y","","",contract_validity,"Save");
						System.out.println("Success message is:"+RenewalValidation);
						Thread.sleep(3000);

						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13  order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println(Qstatus);
						if(Qstatus.equalsIgnoreCase(N_Status))
						{
							System.out.println("PR-3979 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Renewal of a  Customer with is_bulk as 'Y' with Data_driven as 'Y' is Passed");
						}
						else
						{
							System.out.println("PR-3979 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Renewal of a  Customer with is_bulk as 'Y' with Data_driven as 'Y' is Failed");
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



				@Test(description="Renewal of a  Customer with is_bulk as 'Y' with Data_driven as 'N'")
				public void PR_3980() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3980",this);
						System.out.println("PR-3980 Started");
						Thread.sleep(3000);
						oNavigate.toProvisioningSystem();
						Thread.sleep(2000);
						Screen_Validation=oProvisioningSystemCreation.ProvisioningSystem(Prov_System_Code,Prov_System_Name,"", "", "", "", "N", "", "","GMT+05:30", "", "", "", "", "","Save") ;
						System.out.println("validation message"+Screen_Validation);
						Thread.sleep(2000);
						relogin();
						
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation_WithBulk();
						Thread.sleep(80000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
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

						oNavigate.toRenewal();
						RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y","","",contract_validity,"Save");
						System.out.println("Success message is:"+RenewalValidation);
						Thread.sleep(3000);

						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13  order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println(Qstatus);
						if(Qstatus.equalsIgnoreCase(N_Status))
						{
							System.out.println("PR-3980 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Renewal of a  Customer with is_bulk as 'Y' with Data_driven as 'N' is Passed");
						}
						else
						{
							System.out.println("PR-3980 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Renewal of a  Customer with is_bulk as 'Y' with Data_driven as 'N' is Failed");
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



				@Test(description="Association change of a  Customer with is_bulk as 'Y'")
				public void PR_3981() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3981",this);
						System.out.println("PR-3981 Started");
						Thread.sleep(3000);
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
							Thread.sleep(5000);
							System.out.println("PR-3981 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Association change of a  Customer with is_bulk as 'Y' is Passed");
						}
						else
						{
							System.out.println("PR-3981 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Association change of a  Customer with is_bulk as 'Y' is Failed");
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



				@Test(description="Hardware Replacement of a  Customer with is_bulk as 'Y'")
				public void PR_4108() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4108",this);
						System.out.println("PR-4108 Started");
						Thread.sleep(3000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation_WithBulk();
						Thread.sleep(80000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);

						
						
						
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
							testLinkConnection.setsNotes("Hardware Replacement of a  Customer with is_bulk as 'Y' is Passed");
						}
						else
						{
							System.out.println("PR-4108 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Hardware Replacement of a  Customer with is_bulk as 'Y' is Failed");
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



				@Test(description="Retracking of a  Customer with is_bulk as 'Y'")
				public void PR_3982() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3982",this);
						System.out.println("PR-3982 Started");
						Thread.sleep(3000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation_WithBulk();
						Thread.sleep(80000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);

						oNavigate.toRetracking();
						oRetracking.RetrackingScreen(CustomerNumber, "Service",1, "N", "Save");
						Thread.sleep(5000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(5000);

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID in (17,18) order by 1 desc";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-3982 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Retracking of a  Customer with is_bulk as 'Y' is Passed");
						}
						else
						{
							System.out.println("PR-3982 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Retracking of a  Customer with is_bulk as 'Y' is failed");
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



				@Test(description="Activation Customer with Multiple Contracts")
				public void PR_3983() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3983",this);
						System.out.println("PR-3983 Started");
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(20000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						
						oNavigate.toServiceOrder();
						oWaitTool.implicitWait(100);
						driver.findElement(By.id("ctl00_uxPgCPH_chknewcontract")).click();
						driver.findElement(locApprove).click();
						Thread.sleep(2000);

					  	driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).click();
					  	driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(MultiRoomplan+Keys.TAB);
					  	driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();
					  	driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
					  	Thread.sleep(20000);
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
							System.out.println("PR-3983 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Activation Customer with Multiple Contracts is Passed");
						}
						else
						{
							System.out.println("PR-3983 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Activation Customer with Multiple Contracts is Failed");
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



				@Test(description="Disconnection  Multiple Contracts for Customer")
				public void PR_3984() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("`",this);
						System.out.println("PR-3984 Started");
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(20000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						
						oNavigate.toServiceOrder();
						oWaitTool.implicitWait(100);
						driver.findElement(By.id("ctl00_uxPgCPH_chknewcontract")).click();
						driver.findElement(locApprove).click();
						Thread.sleep(2000);

					  	driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).click();
					  	driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(MultiRoomplan+Keys.TAB);
					  	driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();
					  	driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
					  	Thread.sleep(20000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

						Thread.sleep(2000);
						oNavigate.toDisconnection();
						Thread.sleep(5000);
						logger.info("Successfully Navigated to Disconnection Screen ");
						sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
						String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
						System.out.println("Success message is:"+success_Msg);
						System.out.println("Disconnection is done");

						Thread.sleep(2000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();           

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
							System.out.println("PR-3984 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Disconnection  Multiple Contracts for Customer Is Passed");
						}
						else
						{
							System.out.println("PR-3984 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Disconnection  Multiple Contracts for Customer is Failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-3984",driver);
						System.out.println("Test__PR-3984___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}

				}



				@Test(description="Disconnection  Multiple Contracts for Customer from collections screen.")
				public void PR_3985() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3985",this);
						System.out.println("PR-3985 Started");  	    	  
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(20000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						
						oNavigate.toServiceOrder();
						oWaitTool.implicitWait(100);
						driver.findElement(By.id("ctl00_uxPgCPH_chknewcontract")).click();
						driver.findElement(locApprove).click();
						Thread.sleep(2000);

					  	driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).click();
					  	driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(MultiRoomplan+Keys.TAB);
					  	driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();
					  	driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
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
							System.out.println("PR-3985 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Disconnection  Multiple Contracts for Customer from collections screen is Passed");
						}
						else
						{
							System.out.println("PR-3985 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Disconnection  Multiple Contracts for Customer from collections screen Is Failed");
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




				@Test(description="Reconnection  Multiple Contracts for Customer")
				public void PR_3986() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3986",this);
						System.out.println("PR-3986 Started");
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(20000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						
						oNavigate.toServiceOrder();
						oWaitTool.implicitWait(100);
						driver.findElement(By.id("ctl00_uxPgCPH_chknewcontract")).click();
						driver.findElement(locApprove).click();
						Thread.sleep(2000);

					  	driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).click();
					  	driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(MultiRoomplan+Keys.TAB);
					  	driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();
					  	driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
					  	Thread.sleep(20000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

						Thread.sleep(2000);
						oNavigate.toDisconnection();
						Thread.sleep(5000);
						sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
						String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
						System.out.println("Success message is:"+success_Msg);
						System.out.println("Disconnection is done");
						Thread.sleep(2000);
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

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						System.out.println(Party_id);
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7 order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println("status of the request is :"+Qstatus+""); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-3986 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Reconnection  Multiple Contracts for Customer is Passed");
						}
						else
						{
							System.out.println("PR-3986 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Reconnection  Multiple Contracts for Customer is Failed");
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



				@Test(description="Change order d for  Multi_Contracts Customer")
				public void PR_3987() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3987",this);
						System.out.println("PR-3987 Started");
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(20000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						
						oNavigate.toServiceOrder();
						oWaitTool.implicitWait(100);
						driver.findElement(By.id("ctl00_uxPgCPH_chknewcontract")).click();
						driver.findElement(locApprove).click();
						Thread.sleep(2000);

					  	driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).click();
					  	driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(MultiRoomplan+Keys.TAB);
					  	driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();
					  	driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
					  	Thread.sleep(20000);
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
						driver.findElement(locPlanSearchTab).sendKeys("NAGRA100"+Keys.TAB);
						Thread.sleep(1000);
						driver.findElement(locAddToCart).click();
						Thread.sleep(1000);
						driver.findElement(locSaveBtn).click();
						Thread.sleep(5000);
						String Succ_Msg = driver.findElement(locsuccessmsg).getText();
						Thread.sleep(3000);
						System.out.println("Success message is:"+Succ_Msg);

						Thread.sleep(2000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						System.out.println(Party_id);
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=2 order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println("status of the request is :"+Qstatus+""); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-3987 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Change order d for  Multi_Contracts Customer is Passed");
						}
						else
						{
							System.out.println("PR-3987 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Change order d for  Multi_Contracts Customer is Failed");
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



				@Test(description="Service_downgrade Multiple Contracts for Customer from collections screen")
				public void PR_3988() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3988",this);
						System.out.println("PR-3988 Started");
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(20000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						
						oNavigate.toServiceOrder();
						oWaitTool.implicitWait(100);
						driver.findElement(By.id("ctl00_uxPgCPH_chknewcontract")).click();
						driver.findElement(locApprove).click();
						Thread.sleep(2000);

					  	driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).click();
					  	driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(MultiRoomplan+Keys.TAB);
					  	driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();
					  	driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
					  	Thread.sleep(20000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

						
						Thread.sleep(5000);
						oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
						Thread.sleep(5000);
						oCollections.collectionGrid("", "","Service Downgrade", "", "Save");
						System.out.println("Service Downgrade done");
						Thread.sleep(70000);
						Thread.sleep(50000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						System.out.println(Party_id);
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=67 order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println("status of the request is :"+Qstatus+""); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-3988 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Service_downgradeMultiple Contracts for Customer from collections screen is Passed");
						}
						else
						{
							System.out.println("PR-3988 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Service_downgradeMultiple Contracts for Customer from collections screen is Failed");
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



				@Test(description="Suspend Multiple Contracts for Customer from collections screen")
				public void PR_3990() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3990",this);
						System.out.println("PR-3990 Started");
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(20000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						
						oNavigate.toServiceOrder();
						oWaitTool.implicitWait(100);
						driver.findElement(By.id("ctl00_uxPgCPH_chknewcontract")).click();
						driver.findElement(locApprove).click();
						Thread.sleep(2000);

					  	driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).click();
					  	driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(MultiRoomplan+Keys.TAB);
					  	driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();
					  	driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
					  	Thread.sleep(20000);
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
							System.out.println("PR-3990 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Suspend Multiple Contracts for Customer from collections screen is Passed");
						}
						else
						{
							System.out.println("PR-3990 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Suspend Multiple Contracts for Customer from collections screen is Failed");
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



				@Test(description="Suspend Multiple Contracts for Customer from Contract collections screen")
				public void PR_3991() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3991",this);
						System.out.println("PR-3991 Started");
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(20000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						
						oNavigate.toServiceOrder();
						oWaitTool.implicitWait(100);
						driver.findElement(By.id("ctl00_uxPgCPH_chknewcontract")).click();
						driver.findElement(locApprove).click();
						Thread.sleep(2000);

					  	driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).click();
					  	driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(MultiRoomplan+Keys.TAB);
					  	driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();
					  	driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
					  	Thread.sleep(20000);
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
							System.out.println("PR-3991 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Suspend Multiple Contracts for Customer from Contract collections screen is Passed");
						}
						else
						{
							System.out.println("PR-3991 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Suspend Multiple Contracts for Customer from Contract collections screen is Failed");
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



				@Test(description="Reactivate Multiple Contracts for Customer from collections screen")
				public void PR_3992() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3992",this);
						System.out.println("PR-3992 Started");
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(20000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						
						oNavigate.toServiceOrder();
						oWaitTool.implicitWait(100);
						driver.findElement(By.id("ctl00_uxPgCPH_chknewcontract")).click();
						driver.findElement(locApprove).click();
						Thread.sleep(2000);

					  	driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).click();
					  	driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(MultiRoomplan+Keys.TAB);
					  	driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();
					  	driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
					  	Thread.sleep(20000);
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
						System.out.println(Qstatus); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-3992 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Reactivate Multiple Contracts for Customer from collections screen is Passed");
						}
						else
						{
							System.out.println("PR-3992 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Reactivate Multiple Contracts for Customer from collections screen is Failed");
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



				@Test(description="Reactivate Multiple Contracts for Customer from Contract collections screen")
				public void PR_3993() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3993",this);
						System.out.println("PR-3993 Started");
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(20000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						
						oNavigate.toServiceOrder();
						oWaitTool.implicitWait(100);
						driver.findElement(By.id("ctl00_uxPgCPH_chknewcontract")).click();
						driver.findElement(locApprove).click();
						Thread.sleep(2000);

					  	driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).click();
					  	driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(MultiRoomplan+Keys.TAB);
					  	driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();
					  	driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
					  	Thread.sleep(20000);
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
							System.out.println("PR-3993 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Reactivate Multiple Contracts for Customer from Contract collections screen is Passed");
						}
						else
						{
							System.out.println("PR-3993 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Reactivate Multiple Contracts for Customer from Contract collections screen is Failed");
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



				@Test(description="Renewal the Multi_Contracts for  Customer with Data_driven as 'Y'")
				public void PR_3994() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3994",this);
						System.out.println("PR-3994 Started");
						
						Thread.sleep(2000);
						oNavigate.toProvisioningSystem();
						Thread.sleep(2000);
						Screen_Validation=oProvisioningSystemCreation.ProvisioningSystem(Prov_System_Code,Prov_System_Name,"", "", "", "", "Y", "", "","GMT+05:30", "", "", "", "", "","Save") ;
						System.out.println("validation message"+Screen_Validation);
						Thread.sleep(2000);
						relogin();
						
						
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(20000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						
						oNavigate.toServiceOrder();
						oWaitTool.implicitWait(100);
						driver.findElement(By.id("ctl00_uxPgCPH_chknewcontract")).click();
						driver.findElement(locApprove).click();
						Thread.sleep(2000);

					  	driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).click();
					  	driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(MultiRoomplan+Keys.TAB);
					  	driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();
					  	driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
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
							System.out.println("PR-3994 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Renewal the Multi_Contracts for  Customer with Data_driven as 'Y' is Passed");
						}
						else
						{
							System.out.println("PR-3994 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Renewal the Multi_Contracts for  Customer with Data_driven as 'Y' id Failed");
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



				@Test(description="Renewal the Multi_Contracts for  Customer with Data_driven as 'N'")
				public void PR_3995() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3995",this);
						System.out.println("PR-3995 Started");
						Thread.sleep(2000);
						oNavigate.toProvisioningSystem();
						Thread.sleep(2000);
						Screen_Validation=oProvisioningSystemCreation.ProvisioningSystem(Prov_System_Code,Prov_System_Name,"", "", "", "", "N", "", "","GMT+05:30", "", "", "", "", "","Save") ;
						System.out.println("validation message"+Screen_Validation);
						Thread.sleep(2000);
						relogin();
						
						
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(20000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						
						oNavigate.toServiceOrder();
						oWaitTool.implicitWait(100);
						driver.findElement(By.id("ctl00_uxPgCPH_chknewcontract")).click();
						driver.findElement(locApprove).click();
						Thread.sleep(2000);

					  	driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).click();
					  	driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(MultiRoomplan+Keys.TAB);
					  	driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();
					  	driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
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
							System.out.println("PR-3995 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Renewal the Multi_Contracts for  Customer with Data_driven as 'N' is Passed");
						}
						else
						{
							System.out.println("PR-3995 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Renewal the Multi_Contracts for  Customer with Data_driven as 'N' is Failed");
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



				@Test(description="Association change  For the multiple contracts to the Customer")
				public void PR_3996() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3996",this);
						System.out.println("PR-3996 Started");
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(20000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						
						oNavigate.toServiceOrder();
						oWaitTool.implicitWait(100);
						driver.findElement(By.id("ctl00_uxPgCPH_chknewcontract")).click();
						driver.findElement(locApprove).click();
						Thread.sleep(2000);

					  	driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).click();
					  	driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(MultiRoomplan+Keys.TAB);
					  	driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();
					  	driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
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
							System.out.println("PR-3996 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Association change  For the multiple contracts to the Customer is passed");
						}
						else
						{
							System.out.println("PR-3996 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Association change  For the multiple contracts to the Customer is failed");
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



				@Test(description="Hardware Replacement For the multiple contracts to the Customer")
				public void PR_3998() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3998",this);
						System.out.println("PR-3998 Started");
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(20000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						
						oNavigate.toServiceOrder();
						oWaitTool.implicitWait(100);
						driver.findElement(By.id("ctl00_uxPgCPH_chknewcontract")).click();
						driver.findElement(locApprove).click();
						Thread.sleep(2000);

					  	driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).click();
					  	driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(MultiRoomplan+Keys.TAB);
					  	driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();
					  	driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
					  	Thread.sleep(20000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

						Thread.sleep(2000);
						oNavigate.toHardwareReplacement();
						oWaitTool.implicitWait(20);		        	  
						oHardwareReplacement.HardwareReplacement_Header(CustomerNumber,"", "","",ReasonMRPL,"","","","","","","","","","","New");
						oHardwareReplacement.HardwareReplacement_Item_Dtls(Item1,"",StatusMRPL,Item1,OperationalEntity,Location,OrderType,1);
						driver.findElement(locSaveBtn).click();
						Thread.sleep(5000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						System.out.println(Party_id);
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=3 order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println("status of the request is :"+Qstatus+""); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-3998 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Hardware Replacement For the multiple contracts to the Customer is Passed");
						}
						else
						{
							System.out.println("PR-3998 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Hardware Replacement For the multiple contracts to the Customer is Failed");
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



				@Test(description="Retracking For the multiple contracts to the Customer")
				public void PR_3999() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3999",this);
						System.out.println("PR-3999 Started");
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(20000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						
						oNavigate.toServiceOrder();
						oWaitTool.implicitWait(100);
						driver.findElement(By.id("ctl00_uxPgCPH_chknewcontract")).click();
						driver.findElement(locApprove).click();
						Thread.sleep(2000);

					  	driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).click();
					  	driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(MultiRoomplan+Keys.TAB);
					  	driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();
					  	driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
					  	Thread.sleep(20000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

						oNavigate.toRetracking();
						oRetracking.RetrackingScreen(CustomerNumber, "Service",1, "N", "Save");
						Thread.sleep(5000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(5000);

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID in (17,18) order by 1 desc";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-3999 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Retracking For the multiple contracts to the Customer is Passed");
						}
						else
						{
							System.out.println("PR-3999 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Retracking For the multiple contracts to the Customer is Failed");
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



				@Test(description="Activation of a Customer having Multiple Base plan's")
				public void PR_4000() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4000",this);
						System.out.println("PR-4000 Started");
						
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
						driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(strPlanCode+Keys.TAB);
					    driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();  	
					    Thread.sleep(5000);
				  		driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(MultiRoomplan+Keys.TAB);
				  		driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();
				  		Thread.sleep(5000);
				  		driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
				  		ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(50000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						
						
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
							System.out.println("PR-4000 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Activation of a  Customer having Multiple Base plan's is Passed");
						}
						else
						{
							System.out.println("PR-4000 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Activation of a  Customer having Multiple Base plan's Screen is Failed");
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



				@Test(description="Disconnection of a  Customer having Multiple Base plan's")
				public void PR_4001() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4001",this);
						System.out.println("PR-4001 Started");
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
						driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(strPlanCode+Keys.TAB);
					    driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();  	
					    Thread.sleep(5000);
				  		driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(MultiRoomplan+Keys.TAB);
				  		driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();
				  		Thread.sleep(5000);
				  		driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
				  		ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(50000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						
						Thread.sleep(2000);
						oNavigate.toDisconnection();
						Thread.sleep(5000);
						logger.info("Successfully Navigated to Disconnection Screen ");
						sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
						String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
						System.out.println("Success message is:"+success_Msg);
						System.out.println("Disconnection is done");

						Thread.sleep(2000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();           

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
							System.out.println("PR-4001 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Disconnection of a  Customer having Multiple Base plan's is Passed");
						}
						else
						{
							System.out.println("PR-4001 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Disconnection of a  Customer having Multiple Base plan's is failed");
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



				@Test(description="Disconnection of a  Customer having Multiple Base plan's from Collection's Screen")
				public void PR_4002() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4002",this);
						System.out.println("PR-4002 Started");
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
						driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(strPlanCode+Keys.TAB);
					    driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();  	
					    Thread.sleep(5000);
				  		driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(MultiRoomplan+Keys.TAB);
				  		driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();
				  		Thread.sleep(5000);
				  		driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
				  		ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(50000);
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
							System.out.println("PR-4002 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Disconnection of a  Customer having Multiple Base plan's from Collection's Screen is Passed");
						}
						else
						{
							System.out.println("PR-4002 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Disconnection of a  Customer having Multiple Base plan's from Collection's Screen is Failed");
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



				@Test(description="Reconnection of a  Customer having Multiple Base plan's")
				public void PR_4003() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4003",this);
						System.out.println("PR-4003 Started");
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
						driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(strPlanCode+Keys.TAB);
					    driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();  	
					    Thread.sleep(5000);
				  		driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(MultiRoomplan+Keys.TAB);
				  		driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();
				  		Thread.sleep(5000);
				  		driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
				  		ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(50000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						
						
						Thread.sleep(2000);
						oNavigate.toDisconnection();
						Thread.sleep(5000);
						sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
						String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
						System.out.println("Success message is:"+success_Msg);
						System.out.println("Disconnection is done");
						Thread.sleep(2000);
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

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						System.out.println(Party_id);
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7 order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println("status of the request is :"+Qstatus+""); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-4003 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Reconnection of a  Customer having Multiple Base plan's is Passed");
						}
						else
						{
							System.out.println("PR-4003 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Reconnection of a  Customer having Multiple Base plan's is Failed");
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



				@Test(description="Change order of a  Customer having Multiple Base plan's")
				public void PR_4004() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4004",this);
						System.out.println("PR-4004 Started");
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
						driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(strPlanCode+Keys.TAB);
					    driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();  	
					    Thread.sleep(5000);
				  		driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(MultiRoomplan+Keys.TAB);
				  		driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();
				  		Thread.sleep(5000);
				  		driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
				  		ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(50000);
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
						driver.findElement(locPlanSearchTab).sendKeys("NAGRA100"+Keys.TAB);
						Thread.sleep(1000);
						driver.findElement(locAddToCart).click();
						Thread.sleep(1000);
						driver.findElement(locSaveBtn).click();
						Thread.sleep(5000);
						String Succ_Msg = driver.findElement(locsuccessmsg).getText();
						Thread.sleep(3000);
						System.out.println("Success message is:"+Succ_Msg);

						Thread.sleep(2000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						System.out.println(Party_id);
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=2 order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println("status of the request is :"+Qstatus+""); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-4004 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Change order of a  Customer having Multiple Base plan's is Passed");
						}
						else
						{
							System.out.println("PR-4004 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Change order of a  Customer having Multiple Base plan's is failed");
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



				@Test(description="Service_downgrade of a  Customer having Multiple Base plan's")
				public void PR_4005() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4005",this);
						System.out.println("PR-4005 Started");
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
						driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(strPlanCode+Keys.TAB);
					    driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();  	
					    Thread.sleep(5000);
				  		driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(MultiRoomplan+Keys.TAB);
				  		driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();
				  		
				  		Thread.sleep(5000);
				  		driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
				  		ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);

						Thread.sleep(50000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						
						
						Thread.sleep(5000);
						oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
						Thread.sleep(5000);
						oCollections.collectionGrid("", "","Service Downgrade", "", "Save");
						System.out.println("Service Downgrade done");
						Thread.sleep(70000);
						Thread.sleep(50000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						System.out.println(Party_id);
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=67 order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println("status of the request is :"+Qstatus+""); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-4005 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Service_downgrade of a  Customer having Multiple Base plan's is Passed");
						}
						else
						{
							System.out.println("PR-4005 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Service_downgrade of a  Customer having Multiple Base plan's Is Failed");
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



				@Test(description="Suspend of a  Customer having Multiple Base plan's")
				public void PR_4006() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4006",this);
						System.out.println("PR-4006 Started");
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
						driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(strPlanCode+Keys.TAB);
					    driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();  	
					    Thread.sleep(5000);
				  		driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(MultiRoomplan+Keys.TAB);
				  		driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();
				  		Thread.sleep(5000);
				  		driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
				  		ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);

						Thread.sleep(50000);
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
							System.out.println("PR-4006 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Suspend of a  Customer having Multiple Base plan's is Passed");
						}
						else
						{
							System.out.println("PR-4006 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Suspend of a  Customer having Multiple Base plan's is Failed");
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



				@Test(description="Suspend of a  Customer having Multiple Base plan's From Contract collections Screen")
				public void PR_4008() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4008",this);
						System.out.println("PR-4008 Started");
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
						driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(strPlanCode+Keys.TAB);
					    driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();  	
					    Thread.sleep(5000);
				  		driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(MultiRoomplan+Keys.TAB);
				  		driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();
				  		Thread.sleep(5000);
				  		driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
				  		ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);

						Thread.sleep(50000);
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
							System.out.println("PR-4008 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Suspend of a  Customer having Multiple Base plan's From Contract collections Screen is Passed");
						}
						else
						{
							System.out.println("PR-4008 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Suspend of a  Customer having Multiple Base plan's From Contract collections Screen is Failed");
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



				@Test(description="Reactivate of a  Customer having Multiple Base plan's")
				public void PR_4009() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4009",this);
						System.out.println("PR-4009 Started");

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
						driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(strPlanCode+Keys.TAB);
					    driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();  	
					    Thread.sleep(5000);
				  		driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(MultiRoomplan+Keys.TAB);
				  		driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();
				  		Thread.sleep(5000);
				  		driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
				  		ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);

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
						System.out.println(Qstatus); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-4009 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Reactivate of a  Customer having Multiple Base plan's is Passed");
						}
						else
						{
							System.out.println("PR-4009 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Reactivate of a  Customer having Multiple Base plan's is Failed");
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



				@Test(description="Reactivate of a  Customer having Multiple Base plan's From Contract collections Screen")
				public void PR_4010() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4010",this);
						System.out.println("PR-4010 Started");
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
						driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(strPlanCode+Keys.TAB);
					    driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();  	
					    Thread.sleep(5000);
				  		driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(MultiRoomplan+Keys.TAB);
				  		driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();
				  		Thread.sleep(5000);
				  		driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
				  		ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);

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
						System.out.println(Qstatus); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-4010 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Reactivate of a  Customer having Multiple Base plan's From Contract collections Screen is Passed");
						}
						else
						{
							System.out.println("PR-4010 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Reactivate of a  Customer having Multiple Base plan's From Contract collections Screen Is failed");
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



				@Test(description="Renewal of a  Customer having Multiple Base plan's with Data_driven as 'Y'")
				public void PR_4011() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4011",this);
						System.out.println("PR-4011 Started");
						
						Thread.sleep(2000);
						oNavigate.toProvisioningSystem();
						Thread.sleep(2000);
						Screen_Validation=oProvisioningSystemCreation.ProvisioningSystem(Prov_System_Code,Prov_System_Name,"", "", "", "", "Y", "", "","GMT+05:30", "", "", "", "", "","Save") ;
						System.out.println("validation message"+Screen_Validation);
						Thread.sleep(2000);
						relogin();
						
						
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
						driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(strPlanCode+Keys.TAB);
					    driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();  	
					    Thread.sleep(5000);
				  		driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(MultiRoomplan+Keys.TAB);
				  		driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();
				  		Thread.sleep(5000);
				  		driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
				  		ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(50000);
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
							System.out.println("PR-4011 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Renewal of a  Customer having Multiple Base plan's with Data_driven as 'Y' is Passed");
						}
						else
						{
							System.out.println("PR-4011 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Renewal of a  Customer having Multiple Base plan's with Data_driven as 'Y'  is Failed");
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



				@Test(description="Renewal of a  Customer having Multiple Base plan's with Data_driven as 'N'")
				public void PR_4012() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4012",this);
						System.out.println("PR-4012 Started");
						
						Thread.sleep(2000);
						oNavigate.toProvisioningSystem();
						Thread.sleep(2000);
						Screen_Validation=oProvisioningSystemCreation.ProvisioningSystem(Prov_System_Code,Prov_System_Name,"", "", "", "", "N", "", "","GMT+05:30", "", "", "", "", "","Save") ;
						System.out.println("validation message"+Screen_Validation);
						Thread.sleep(2000);
						relogin();
						
						
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
						driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(strPlanCode+Keys.TAB);
					    driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();  	
					    Thread.sleep(5000);
				  		driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(MultiRoomplan+Keys.TAB);
				  		driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();
				  		Thread.sleep(5000);
				  		driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
				  		ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(50000);
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
							System.out.println("PR-4012 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Renewal of a  Customer having Multiple Base plan's with Data_driven as 'N' is Passed");
						}
						else
						{
							System.out.println("PR-4012 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Renewal of a  Customer having Multiple Base plan's with Data_driven as 'N' is Failed");
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



				@Test(description="Association change of a  Customer having Multiple Base plan's")
				public void PR_4013() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4013",this);
						System.out.println("PR-4013 Started");
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
						driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(strPlanCode+Keys.TAB);
					    driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();  	
					    Thread.sleep(5000);
				  		driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(MultiRoomplan+Keys.TAB);
				  		driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();
				  		Thread.sleep(5000);
				  		driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
				  		ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);

						Thread.sleep(50000);
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
							System.out.println("PR-4013 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Association change of a  Customer having Multiple Base plan's is Passed");
						}
						else
						{
							System.out.println("PR-4013 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Association change of a  Customer having Multiple Base plan's  is Failed");
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



				@Test(description="Hardware Replacement of a  Customer having Multiple Base plan's")
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
						driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(strPlanCode+Keys.TAB);
					    driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();  	
					    Thread.sleep(5000);
				  		driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(MultiRoomplan+Keys.TAB);
				  		driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();
				  		Thread.sleep(5000);
				  		driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
				  		ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);

						Thread.sleep(50000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						
						
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
							System.out.println("PR-4015 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Hardware Replacement of a  Customer having Multiple Base plan's is passed");
						}
						else
						{
							System.out.println("PR-4015 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Hardware Replacement of a  Customer having Multiple Base plan's is Failed");
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



				@Test(description="Retracking of a  Customer having Multiple Base plan's")
				public void PR_4016() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4016",this);
						System.out.println("PR-4016 Started");
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
						driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(strPlanCode+Keys.TAB);
					    driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();  	
					    Thread.sleep(5000);
				  		driver.findElement(By.id("ctl00_uxPgCPH_btnbasicplan")).sendKeys(MultiRoomplan+Keys.TAB);
				  		driver.findElement(By.id("ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd")).click();
				  		Thread.sleep(5000);
				  		driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
				  		ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);

						Thread.sleep(50000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						
						oNavigate.toRetracking();
						oRetracking.RetrackingScreen(CustomerNumber, "Service",1, "N", "Save");
						Thread.sleep(5000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(5000);

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID in (17,18) order by 1 desc";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-4016 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Retracking of a  Customer having Multiple Base plan's is Passed");
						}
						else
						{
							System.out.println("PR-4016 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Retracking of a  Customer having Multiple Base plan's is Failed");
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



				@Test(description="Reconnecting Contract with is_bulk as Y")
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
							testLinkConnection.setsNotes("Reconnecting Contract with is_bulk as Y ");
						}
						else
						{
							System.out.println("PR-4017 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Retracking of a  Customer having Multiple Base plan's is failed");
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



				@Test(description="Activation of the Customer when No of  connections as 1")
				public void PR_4018() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4018",this);
						System.out.println("PR-4018 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvationMultiConnections("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency,"1");
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
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
						System.out.println(Qstatus); 
						if(Qstatus.equalsIgnoreCase(C_Status))       
						{
							System.out.println("PR-4018 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Activation of the Customer when No of  connections as 1 is Passed");
						}
						else
						{
							System.out.println("PR-4018 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Activation of the Customer when No of  connections as 1 id Failed");
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


				@Test(description="Disconnection of the Customer when No of  connections as 1")
				public void PR_4019() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4019",this);
						System.out.println("PR-4019 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvationMultiConnections("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency,"1");
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
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
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4 order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println("status of the request is :"+Qstatus+""); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-4019 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Disconnection of the Customer when No of  connections as 1 is Passed");
						}
						else
						{
							System.out.println("PR-4019 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Disconnection of the Customer when No of  connections as 1 is Failed");
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



				@Test(description="Disconnection of the Customer when No of  connections as 1 from collections screen")
				public void PR_4020() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4020",this);
						System.out.println("PR-4020 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvationMultiConnections("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency,"1");
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);

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
							System.out.println("PR-4020 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Disconnection of the Customer when No of  connections as 1 from collections screen is Passed");
						}
						else
						{
							System.out.println("PR-4020 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Disconnection of the Customer when No of  connections as 1 from collections screen is Failed");
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



				@Test(description="Reconnection of the Customer when No of  connections as 1")
				public void PR_4022() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4022",this);
						System.out.println("PR-4022 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvationMultiConnections("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency,"1");
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);

						oNavigate.toDisconnection();
						Thread.sleep(5000);
						sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
						String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
						System.out.println("Success message is:"+success_Msg);
						System.out.println("Disconnection is done");
						Thread.sleep(2000);
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

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						System.out.println(Party_id);
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7 order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println("status of the request is :"+Qstatus+""); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-4022 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Reconnection of the Customer when No of  connections as 1 is Passed");
						}
						else
						{
							System.out.println("PR-4022 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Reconnection of the Customer when No of  connections as 1 is failed");
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




				@Test(description="Change order of the Customer when No of  connections as 1")
				public void PR_4069() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4069",this);
						System.out.println("PR-4069 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvationMultiConnections("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency,"1");
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
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
						Thread.sleep(2000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						System.out.println(Party_id);
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=2 order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println("status of the request is :"+Qstatus+""); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-4069 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Change order of the Customer when No of  connections as 1 is Passed");
						}
						else
						{
							System.out.println("PR-4069 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Change order of the Customer when No of  connections as 1 is failed");
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



				@Test(description="Service_downgrade of the Customer when No of  connections as 1")
				public void PR_4070() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4070",this);
						System.out.println("PR-4070 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvationMultiConnections("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency,"1");
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);

						oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
						Thread.sleep(5000);
						oCollections.collectionGrid("", "","Service Downgrade", "", "Save");
						System.out.println("Service Downgrade done");
						Thread.sleep(70000);
						Thread.sleep(50000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						System.out.println(Party_id);
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=67 order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println("status of the request is :"+Qstatus+""); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-4070 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Service_downgradeof the Customer when No of  connections as 1 is Passed");
						}
						else
						{
							System.out.println("PR-4070 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Service_downgradeof the Customer when No of  connections as 1 is Failed");
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



				@Test(description="Suspend of the Customer when No of  connections as 1")
				public void PR_4071() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4071",this);
						System.out.println("PR-4071 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvationMultiConnections("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency,"1");
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
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
							System.out.println("PR-4071 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Suspend of the Customer when No of  connections as 1 is Passed");
						}
						else
						{
							System.out.println("PR-4071 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Suspend of the Customer when No of  connections as 1 is failed");
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



				@Test(description="Suspend of the Customer when No of  connections as 1 From Contract collections")
				public void PR_4072() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4072",this);
						System.out.println("PR-4072 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvationMultiConnections("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency,"1");
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);

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
							System.out.println("PR-4072 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Suspend of the Customer when No of  connections as 1 From Contract collections is Passed");
						}
						else
						{
							System.out.println("PR-4072 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Suspend of the Customer when No of  connections as 1 From Contract collections is Failed");
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



				@Test(description="Reactivate of the Customer when No of  connections as 1")
				public void PR_4073() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4073",this);
						System.out.println("PR-4073 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvationMultiConnections("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency,"1");
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);

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
						System.out.println(Qstatus); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-4073 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Reactivate of the Customer when No of  connections as 1 is Passed");
						}
						else
						{
							System.out.println("PR-4073 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Reactivate of the Customer when No of  connections as 1 is Failed");
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



				@Test(description="Reactivate of the Customer when No of  connections as 1 From Contract collections")
				public void PR_4074() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4074",this);
						System.out.println("PR-4074 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvationMultiConnections("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency,"1");
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
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
							System.out.println("PR-4074 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Reactivate of the Customer when No of  connections as 1 From Contract collections is Passed");
						}
						else
						{
							System.out.println("PR-4074 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Reactivate of the Customer when No of  connections as 1 From Contract collections is Failed");
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



				@Test(description="Renewal the Customer when No of  connections as 1 with Data_driven as 'Y'")
				public void PR_4075() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4075",this);
						System.out.println("PR-4075 Started");
						
						Thread.sleep(2000);
						oNavigate.toProvisioningSystem();
						Thread.sleep(2000);
						Screen_Validation=oProvisioningSystemCreation.ProvisioningSystem(Prov_System_Code,Prov_System_Name,"", "", "", "", "Y", "", "","GMT+05:30", "", "", "", "", "","Save") ;
						System.out.println("validation message"+Screen_Validation);
						Thread.sleep(2000);
						relogin();
						
						
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvationMultiConnections("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency,"1");
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
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
							System.out.println("PR-4075 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Renewal  the Customer when No of  connections as 1 with Data_driven as 'Y' is Passed");
						}
						else
						{
							System.out.println("PR-4075 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Renewal  the Customer when No of  connections as 1 with Data_driven as 'Y' is Failed");
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


				@Test(description="Renewal  the Customer when No of  connections as 1 with Data_driven as 'N'")
				public void PR_4076() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4076",this);
						System.out.println("PR-4076 Started");
						
						Thread.sleep(2000);
						oNavigate.toProvisioningSystem();
						Thread.sleep(2000);
						Screen_Validation=oProvisioningSystemCreation.ProvisioningSystem(Prov_System_Code,Prov_System_Name,"", "", "", "", "N", "", "","GMT+05:30", "", "", "", "", "","Save") ;
						System.out.println("validation message"+Screen_Validation);
						Thread.sleep(2000);
						relogin();
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvationMultiConnections("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency,"1");
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
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
							System.out.println("PR-4076 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Renewal  the Customer when No of  connections as 1 with Data_driven as 'N' is Passed");
						}
						else
						{
							System.out.println("PR-4076 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Renewal  the Customer when No of  connections as 1 with Data_driven as 'N' is Failed");
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

				
				@Test(description="Association change  the Customer when No of  connections as 1")
				public void PR_4077() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4077",this);
						System.out.println("PR-4077 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvationMultiConnections("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency,"1");
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
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
							System.out.println("PR-4077 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Association change  the Customer when No of  connections as 1 is Passed");
						}
						else
						{
							System.out.println("PR-4077 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Association change  the Customer when No of  connections as 1 is failed");
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



				@Test(description="Hardware Replacement the Customer when No of  connections as 1")
				public void PR_4078() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4078",this);
						System.out.println("PR-4078 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvationMultiConnections("N", "Sale", "", "",contract_validity,strPlanCode,"Y",billing_Frequency,"1");
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);

						Thread.sleep(2000);
						oNavigate.toHardwareReplacement();
						oWaitTool.implicitWait(20);		        	  
						oHardwareReplacement.HardwareReplacement_Header(CustomerNumber,"", "","",ReasonMRPL,"","","","","","","","","","","New");
						oHardwareReplacement.HardwareReplacement_Item_Dtls(Item1,"",StatusMRPL,Item1,OperationalEntity,Location,OrderType,1);
						driver.findElement(locSaveBtn).click();

						Thread.sleep(5000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						System.out.println(Party_id);
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=3 order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println("status of the request is :"+Qstatus+""); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-4078 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Hardware Replacement the Customer when No of  connections as 1 is Passed");
						}
						else
						{
							System.out.println("PR-4078 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Hardware Replacement the Customer when No of  connections as 1 is Failed");
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


				@Test(description="Retracking the Customer when No of  connections as 1")
				public void PR_4079() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4079",this);
						System.out.println("PR-4079 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvationMultiConnections("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency,"1");
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);
						
						oNavigate.toRetracking();
						oRetracking.RetrackingScreen(CustomerNumber, "Service",1, "N", "Save");
						Thread.sleep(5000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(5000);

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID in (17,18) order by 1 desc";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-4079 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Retracking the Customer when No of  connections as 1 is Passed");
						}
						else
						{
							System.out.println("PR-4079 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Retracking the Customer when No of  connections as 1 is Failed");
						}
					}
					catch(Exception e)
					{
						captureScreenShot.takeScreenShot("PR-4079",driver);
						System.out.println("Test__PR-4079___%Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
					}
				}


				@Test(description="Activation of the Customer when No of  connections as 2")
				public void PR_4080() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4080",this);
						System.out.println("PR-4080 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvationMultiConnections("Y", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency,"2");
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);
		
						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						System.out.println(Party_id);
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=3 order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println("status of the request is :"+Qstatus+""); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-4080 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Activation of the Customer when No of  connections as 2 is Passed");
						}
						else
						{
							System.out.println("PR-4080 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Activation of the Customer when No of  connections as 2 is Failed");
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

				
				@Test(description="Disconnection of the Customer when No of  connections as 2")
				public void PR_4082() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4082",this);
						System.out.println("PR-4082 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvationMultiConnections("Y", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency,"2");
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);
						oNavigate.toDisconnection();
						Thread.sleep(5000);
						logger.info("Successfully Navigated to Disconnection Screen ");
						sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
						String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
						System.out.println("Success message is:"+success_Msg);
						System.out.println("Disconnection is done");

						Thread.sleep(2000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();           

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
							System.out.println("PR-4082 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Disconnection of the Customer when No of  connections as 2 is Passed");
						}
						else
						{
							System.out.println("PR-4082 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Disconnection of the Customer when No of  connections as 2 is Failed");
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



				@Test(description="Disconnection of the Customer when No of  connections as 2 from collections screen")
				public void PR_4083() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4083",this);
						System.out.println("PR-4083 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvationMultiConnections("Y", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency,"2");
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);
						
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
							System.out.println("PR-4083 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Disconnection of the Customer when No of  connections as 2 from collections screen is Passed");
						}
						else
						{
							System.out.println("PR-4083 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Disconnection of the Customer when No of  connections as 2 from collections screen is Failed");
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



				@Test(description="Reconnection of the Customer when No of  connections as 2")
				public void PR_4084() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4084",this);
						System.out.println("PR-4084 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvationMultiConnections("Y", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency,"2");
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);
		
						Thread.sleep(2000);
						oNavigate.toDisconnection();
						Thread.sleep(5000);
						sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
						String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
						System.out.println("Success message is:"+success_Msg);
						System.out.println("Disconnection is done");
						Thread.sleep(20000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();           

						Thread.sleep(2000);
						oNavigate.toReconnection();
						oWaitTool.implicitWait(10);
						oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
						oWaitTool.implicitWait(50);
						String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
						System.out.println("Success message is:"+success_Msg1);
						System.out.println("Reconnection is done");
						Thread.sleep(10000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						System.out.println(Party_id);
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7 order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println("status of the request is :"+Qstatus+""); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-4084 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Reconnection of the Customer when No of  connections as 2 is Passed");
						}
						else
						{
							System.out.println("PR-4084 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Reconnection of the Customer when No of  connections as 2 is Failed");
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



				@Test(description="Change order of the Customer when No of  connections as 2")
				public void PR_4085() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4085",this);
						System.out.println("PR-4085 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvationMultiConnections("Y", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency,"2");
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);
		
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
						Thread.sleep(2000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						System.out.println(Party_id);
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=2 order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println("status of the request is :"+Qstatus+""); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-4085 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Change order of the Customer when No of  connections as 2 is Passed");
						}
						else
						{
							System.out.println("PR-4085 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Change order of the Customer when No of  connections as 2 is failed");
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



				@Test(description="Service_downgrade of the Customer when No of  connections as 2")
				public void PR_4086() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4086",this);
						System.out.println("PR-4086 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvationMultiConnections("Y", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency,"2");
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);
		
						Thread.sleep(5000);
						oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
						Thread.sleep(5000);
						oCollections.collectionGrid("", "","Service Downgrade", "", "Save");
						System.out.println("Service Downgrade done");
						Thread.sleep(70000);
						Thread.sleep(50000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						System.out.println(Party_id);
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=67 order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println("status of the request is :"+Qstatus+""); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-4086 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Service_downgradeof the Customer when No of  connections as 2 id Passed");
						}
						else
						{
							System.out.println("PR-4086 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Service_downgradeof the Customer when No of  connections as 2 is Failed");
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


				@Test(description="Suspend of the Customer when No of  connections as 2")
				public void PR_4464() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4464",this);
						System.out.println("PR-4464 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvationMultiConnections("Y", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency,"2");
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
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
							System.out.println("PR-4464 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Suspend of the Customer when No of  connections as 2 is Passed");
						}
						else
						{
							System.out.println("PR-4464 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Suspend of the Customer when No of  connections as 2 is Failed");
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

				
				@Test(description="Suspend of the Customer when No of  connections as 2 From Contract collections")
				public void PR_4465() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4465",this);
						System.out.println("PR-4465 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvationMultiConnections("Y", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency,"2");
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						
						Thread.sleep(30000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);
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
							System.out.println("PR-4465 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Suspend of the Customer when No of  connections as 2 From Contract collections is Passed");
						}
						else
						{
							System.out.println("PR-4465 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Suspend of the Customer when No of  connections as 2 From Contract collections is Failed");
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


				@Test(description="Reactivate of the Customer when No of  connections as 2")
				public void PR_4048() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4048",this);
						System.out.println("PR-4048 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvationMultiConnections("Y", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency,"2");
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);
		
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
						String Qstatus=record.get("STATUS");
						System.out.println(Qstatus); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-4048 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Reactivate of the Customer when No of  connections as 2 is Passed");
						}
						else
						{
							System.out.println("PR-4048 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Reactivate of the Customer when No of  connections as 2 is Failed");
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



				@Test(description="Reactivate of the Customer when No of  connections as 2 From Contract collections")
				public void PR_4049() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4049",this);
						System.out.println("PR-4049 Started");
						Thread.sleep(2000);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
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
							System.out.println("PR-4049 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Reactivate of the Customer when No of  connections as 2 From Contract collections is Passed");
						}
						else
						{
							System.out.println("PR-4049 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Reactivate of the Customer when No of  connections as 2 From Contract collections is Failed");
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



				@Test(description="Renewal  the Customer when No of  connections as 2 with Data_driven as 'Y'")
				public void PR_4050() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4050",this);
						System.out.println("PR-4050 Started");
						Thread.sleep(2000);
						oNavigate.toProvisioningSystem();
						Thread.sleep(2000);
						Screen_Validation=oProvisioningSystemCreation.ProvisioningSystem(Prov_System_Code,Prov_System_Name,"", "", "", "", "Y", "", "","GMT+05:30", "", "", "", "", "","Save") ;
						System.out.println("validation message"+Screen_Validation);
						Thread.sleep(2000);
						relogin();
						
						
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvationMultiConnections("Y", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency,"2");
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
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
							System.out.println("PR-4050 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Renewal  the Customer when No of  connections as 2 with Data_driven as 'Y' is Passed");
						}
						else
						{
							System.out.println("PR-4050 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Renewal  the Customer when No of  connections as 2 with Data_driven as 'Y' is Failed");
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




				@Test(description="Renewal  the Customer when No of  connections as 2 with Data_driven as 'N'")
				public void PR_4051() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4051",this);
						System.out.println("PR-4051 Started");
						Thread.sleep(2000);
						oNavigate.toProvisioningSystem();
						Thread.sleep(2000);
						Screen_Validation=oProvisioningSystemCreation.ProvisioningSystem(Prov_System_Code,Prov_System_Name,"", "", "", "", "N", "", "","GMT+05:30", "", "", "", "", "","Save") ;
						System.out.println("validation message"+Screen_Validation);
						Thread.sleep(2000);
						relogin();
						Thread.sleep(2000);
						
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvationMultiConnections("Y", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency,"2");
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
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
							System.out.println("PR-4051 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Renewal  the Customer when No of  connections as 2 with Data_driven as 'N' is Passed");
						}
						else
						{
							System.out.println("PR-4051 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Renewal  the Customer when No of  connections as 2 with Data_driven as 'N' is failed");
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


				@Test(description="Association change  the Customer when No of  connections as 2")
				public void PR_4052() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4052",this);
						System.out.println("PR-4052 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvationMultiConnections("Y", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency,"2");
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
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
							System.out.println("PR-4052 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Association change  the Customer when No of  connections as 2 is Passed");
						}
						else
						{
							System.out.println("PR-4052 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Association change  the Customer when No of  connections as 2 is failed");
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



				@Test(description="Hardware Replacement the Customer when No of  connections as 2")
				public void PR_4053() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4053",this);
						System.out.println("PR-4053 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvationMultiConnections("Y", "Sale", "", "",contract_validity,strPlanCode,"Y",billing_Frequency,"2");
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);
		
						
						Thread.sleep(2000);
						oNavigate.toHardwareReplacement();
						oWaitTool.implicitWait(20);		        	  
						oHardwareReplacement.HardwareReplacement_Header(CustomerNumber,"", "","",ReasonMRPL,"","","","","","","","","","","New");
						oHardwareReplacement.HardwareReplacement_Item_Dtls(Item1,"",StatusMRPL,Item1,OperationalEntity,Location,OrderType,1);
						driver.findElement(locSaveBtn).click();
						Thread.sleep(5000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						System.out.println(Party_id);
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=3 order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println("status of the request is :"+Qstatus+""); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-4053 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Hardware Replacement the Customer when No of  connections as 2 is passed");
						}
						else
						{
							System.out.println("PR-4053 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Hardware Replacement the Customer when No of  connections as 2 is Failed");
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



				@Test(description="Retracking the Customer when No of  connections as 2")
				public void PR_4054() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-4054",this);
						System.out.println("PR-4054 Started");
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvationMultiConnections("Y", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency,"2");
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);
		
						oNavigate.toRetracking();
						oRetracking.RetrackingScreen(CustomerNumber, "Service",1, "N", "Save");
						Thread.sleep(5000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(5000);

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID in (17,18) order by 1 desc";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-4054 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Retracking the Customer when No of  connections as 2 is Passed");
						}
						else
						{
							System.out.println("PR-4054 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Retracking the Customer when No of  connections as 2 Is Failed");
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








}
