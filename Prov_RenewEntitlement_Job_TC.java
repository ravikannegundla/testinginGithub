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
import com.ProvisioningPageObject.ActivityPrerequisites;
import com.ProvisioningPageObject.BasicConfigurationsOfProvisioning;
import com.ProvisioningPageObject.ProvisioningRequests;
import com.ProvisioningPageObject.ProvisioningSetUp;
import com.ProvisioningPageObject.ProvisioningSystemAction;
import com.ProvisioningPageObject.ProvisioningSystemCreation;
import com.ProvisioningPageObject.Retracking;
import com.ReceivablesPageObject.Collections;
import com.ReceivablesPageObject.ContractCollections;
import com.ReceivablesPageObject.CreditAllocation;

import TestFrameWork.MQProvisioning;
import Utility.Navigate;
import Utility.WaitTool;

public class Prov_RenewEntitlement_Job_TC extends MQProvisioning

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
				ProvisioningSystemCreation oProvisioningSystemCreation;
				ProvisioningSystemAction oProvisioningSystemAction;
				ActivityPrerequisites oActivityPrerequisites;
				ContractCollections oContractCollections;
				Retracking oRetracking;





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
				public By ValidationMessage=By.id("ctl00__infoSpan");
				public By btnsave = By.name("ctl00$uxPgCPH$btnsave");




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
				String RenewalValidation,Screen_Validation;

				String Prov_System_Name="NAGRA";
				String Prov_System_Code="NAGRA";
				public String Service_Class="TV Channel";
				String getConnectionNumber,Connection_Number;



				public List<WebElement> listOfRows;
				public     String ScreenValidation1,Sent="Sent",Accept="Accept",New="New";

				String sResult,Result,sRes;
				String sQuery,CustomerNumber;
				ArrayList<String> Actionids_List= new ArrayList<String>();


				protected Prov_RenewEntitlement_Job_TC(String sLogFileName) throws Exception 
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
					this.oProvisioningSystemCreation= new ProvisioningSystemCreation(driver,dbConnection);
					this.oProvisioningSystemAction= new ProvisioningSystemAction(driver,dbConnection);
					this.oActivityPrerequisites= new ActivityPrerequisites(driver,dbConnection);
					this.oContractCollections=new ContractCollections(driver,dbConnection);
					this.oRetracking=new Retracking(driver,dbConnection);



					//					oBasicConfigurationsOfProvisioning.OwnHardware_Privileges(); 
					//					oOrderManagementPrivileges.Disconnection_privileges();
					//					oOrderManagementPrivileges.Reconnection_privileges();
					//					oOrderManagementPrivileges.ServiceOrder_privileges();


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



				@Test(description="Provisioning system configration")
				public void PR_3752() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3752",this);
						System.out.println("PR-3752 Started");
						Thread.sleep(2000);
						oNavigate.toProvisioningSystem();
						Thread.sleep(2000);
						Screen_Validation=oProvisioningSystemCreation.ProvisioningSystem(Prov_System_Code,Prov_System_Name,"", "", "", "", "Y", "", "","GMT+05:30", "", "", "", "", "","Save") ;
						System.out.println("validation message"+Screen_Validation);
						if(Screen_Validation.length()!=0)
						{
							System.out.println("PR-3752 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Provisioning system configration is Passed");
						}
						else
						{
							System.out.println("PR-3752 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Provisioning system configration is Failed");
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


				@Test(description="Single contract with Activate Product List")
				public void PR_3753() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3753",this);
						System.out.println("PR-3753 Started");
						relogin();
						/*Thread.sleep(2000);
						relogin();
						Actionids_List = new ArrayList(10);
						Actionids_List.add("156");
						Actionids_List.add("129");
						Actionids_List.add("22");
						Actionids_List.add("24");
						Actionids_List.add("158");
						System.out.println("array list is "+Actionids_List);

						oNavigate.toProvisioningSystemAction();
						Thread.sleep(2000);
						Screen_Validation=oProvisioningSystemAction.ProvisioningActionMapping(Prov_System_Name,Actionids_List,"Save");
						System.out.println("validation message"+Screen_Validation);
						relogin();
						Thread.sleep(2000);
						oNavigate.toActivityPrerequisites();
						Thread.sleep(2000);
						oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"1","12","Pre Action","N",1);
						oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"16","87","Post Action","N",2);
						oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"85","12","Pre Action","N",3);
						driver.findElement(btnsave).click();
						String Screen_Validation1=driver.findElement(ValidationMessage).getText();
						Thread.sleep(2000);
						System.out.println(Screen_Validation1);

						relogin();
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);
						 */

						oNavigate.toScheduleJob();
						Thread.sleep(2000);
						oScheduleJob.RenewEntitlement_Job("", "Batch Jobs","Renew Entitlement", "", "", "","","NAGRA","","RENEW","");
						Thread.sleep(80000);

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
							testLinkConnection.setsNotes("Single contract with Activate Product List is Passed");
						}
						else
						{
							System.out.println("PR-3753 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Single contract with Activate Product List is Failed");
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


				@Test(description="Single contract with Activate All")
				public void PR_3754() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3754",this);
						System.out.println("PR-3754 Started");
						Thread.sleep(2000);
						relogin();
						Actionids_List = new ArrayList(10);
						Actionids_List.add("89");
						Actionids_List.add("16");
						Actionids_List.add("22");
						Actionids_List.add("24");
						Actionids_List.add("158");

						System.out.println("array list is "+Actionids_List);

						oNavigate.toProvisioningSystemAction();
						Thread.sleep(2000);
						Screen_Validation=oProvisioningSystemAction.ProvisioningActionMapping(Prov_System_Name,Actionids_List,"Save");
						System.out.println("validation message"+Screen_Validation);

						Thread.sleep(2000);
						relogin();
						Thread.sleep(2000);
						oNavigate.toActivityPrerequisites();
						Thread.sleep(2000);
						oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"89","12","Pre Action","N",1);
						oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"16","87","Post Action","N",2);

						driver.findElement(btnsave).click();
						String Screen_Validation1=driver.findElement(ValidationMessage).getText();
						Thread.sleep(2000);
						System.out.println(Screen_Validation1);

						relogin();
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);

						oNavigate.toScheduleJob();
						Thread.sleep(2000);
						oScheduleJob.RenewEntitlement_Job("", "Batch Jobs","Renew Entitlement", "", "", "","","NAGRA","","RENEW","");
						Thread.sleep(80000);

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
							System.out.println("PR-3754 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Single contract with Activate All is Passed");
						}
						else
						{
							System.out.println("PR-3754 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Single contract with Activate All is failed");
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


				@Test(description="Single contract with Add Product")
				public void PR_3756() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3756",this);
						System.out.println("PR-3756 Started");
						Thread.sleep(2000);
						relogin();
						Actionids_List = new ArrayList(10);
						Actionids_List.add("1");
						Actionids_List.add("2");
						Actionids_List.add("22");
						Actionids_List.add("24");
						Actionids_List.add("83");
						System.out.println("array list is "+Actionids_List);

						oNavigate.toProvisioningSystemAction();
						Thread.sleep(2000);
						Screen_Validation=oProvisioningSystemAction.ProvisioningActionMapping(Prov_System_Name,Actionids_List,"Save");
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

						relogin();
						Thread.sleep(2000);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(30000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						Thread.sleep(3000);

						oNavigate.toScheduleJob();
						Thread.sleep(2000);
						oScheduleJob.RenewEntitlement_Job("", "Batch Jobs","Renew Entitlement", "", "", "","","NAGRA","","RENEW","");
						Thread.sleep(80000);

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
							System.out.println("PR-3756 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Single contract with Add Product is Passed");
						}
						else
						{
							System.out.println("PR-3756 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Single contract with Add Product Is failed");
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


				@Test(description="Job after disconnection")
				public void PR_3757() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3757",this);
						System.out.println("PR-3757 Started");
						Thread.sleep(3000);
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

						oNavigate.toScheduleJob();
						Thread.sleep(2000);
						oScheduleJob.RenewEntitlement_Job("", "Batch Jobs","Renew Entitlement", "", "", "","","NAGRA","","RENEW","");
						Thread.sleep(80000);

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
							System.out.println("PR-3757 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Job after disconnection is Passed");
						}
						else
						{
							System.out.println("PR-3757 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Job after disconnection is Failed");
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


				@Test(description="Reconnection of contract with Activate Product List")
				public void PR_3759() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3759",this);
						System.out.println("PR-3759 Started");
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

						oNavigate.toScheduleJob();
						Thread.sleep(2000);
						oScheduleJob.RenewEntitlement_Job("", "Batch Jobs","Renew Entitlement", "", "", "","","NAGRA","","RENEW","");
						Thread.sleep(80000);

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
							System.out.println("PR-3759 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Reconnection of contract with Activate Product List is Passed");
						}
						else
						{
							System.out.println("PR-3759 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Reconnection of contract with Activate Product List is Failed");
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


				@Test(description="Reconnection of contract with Activate All")
				public void PR_3760() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3760",this);
						System.out.println("PR-3760 Started");
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


						oNavigate.toScheduleJob();
						Thread.sleep(2000);
						oScheduleJob.RenewEntitlement_Job("", "Batch Jobs","Renew Entitlement", "", "", "","","NAGRA","","RENEW","");
						Thread.sleep(80000);



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
							System.out.println("PR-3760 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Reconnection of contract with Activate All is Passed");
						}
						else
						{
							System.out.println("PR-3760 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Reconnection of contract with Activate All is Failed");
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


				@Test(description="Reconnection of contract with Add Product")
				public void PR_3762() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3762",this);
						System.out.println("PR-3762 Started");
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


						oNavigate.toScheduleJob();
						Thread.sleep(2000);
						oScheduleJob.RenewEntitlement_Job("", "Batch Jobs","Renew Entitlement", "", "", "","","NAGRA","","RENEW","");
						Thread.sleep(80000);



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
							System.out.println("PR-3762 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Reconnection of contract with Add Product is Passed");
						}
						else
						{
							System.out.println("PR-3762 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Reconnection of contract with Add Product is Failed");
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


				@Test(description="Hardware Replacement  with Activate Product List")
				public void PR_3763() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3763",this);
						System.out.println("PR-3763 Started");
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

						oNavigate.toScheduleJob();
						Thread.sleep(2000);
						oScheduleJob.RenewEntitlement_Job("", "Batch Jobs","Renew Entitlement", "", "", "","","NAGRA","","RENEW","");
						Thread.sleep(80000);




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
							System.out.println("PR-3763 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Hardware Replacement  with Activate Product List Is Passed");
						}
						else
						{
							System.out.println("PR-3763 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Hardware Replacement  with Activate Product List is failed");
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


				@Test(description="Hardware Replacement with Activate All")
				public void PR_3764() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3764",this);
						System.out.println("PR-3764 Started");
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


						oNavigate.toScheduleJob();
						Thread.sleep(2000);
						oScheduleJob.RenewEntitlement_Job("", "Batch Jobs","Renew Entitlement", "", "", "","","NAGRA","","RENEW","");
						Thread.sleep(80000);





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
							System.out.println("PR-3764 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Hardware Replacement with Activate All is Passed");
						}
						else
						{
							System.out.println("PR-3764 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Hardware Replacement with Activate All is failed");
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


				@Test(description="Hardware Replacement  with Add Product")
				public void PR_3765() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3765",this);
						System.out.println("PR-3765 Started");
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


						oNavigate.toScheduleJob();
						Thread.sleep(2000);
						oScheduleJob.RenewEntitlement_Job("", "Batch Jobs","Renew Entitlement", "", "", "","","NAGRA","","RENEW","");
						Thread.sleep(80000);


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
							System.out.println("PR-3765 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Hardware Replacement  with Add Product is Passed");
						}
						else
						{
							System.out.println("PR-3765 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Hardware Replacement  with Add Product is failed");
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


				@Test(description="Association change with Activate Product List")
				public void PR_3766() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3766",this);
						System.out.println("PR-3766 Started");
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


						oNavigate.toScheduleJob();
						Thread.sleep(2000);
						oScheduleJob.RenewEntitlement_Job("", "Batch Jobs","Renew Entitlement", "", "", "","","NAGRA","","RENEW","");
						Thread.sleep(80000);

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
							System.out.println("PR-3766 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Association change with Activate Product List is Passed");
						}
						else
						{
							System.out.println("PR-3766 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Association change with Activate Product List is Failed");
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


				@Test(description="Association change with Activate All")
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


						oNavigate.toScheduleJob();
						Thread.sleep(2000);
						oScheduleJob.RenewEntitlement_Job("", "Batch Jobs","Renew Entitlement", "", "", "","","NAGRA","","RENEW","");
						Thread.sleep(80000);



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
							testLinkConnection.setsNotes("Association change with Activate All is Passed");
						}
						else
						{
							System.out.println("PR-3767 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Association change with Activate All is Failed");
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


				@Test(description="Association change with Add Product")
				public void PR_3768() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3768",this);
						System.out.println("PR-3768 Started");
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


						oNavigate.toScheduleJob();
						Thread.sleep(2000);
						oScheduleJob.RenewEntitlement_Job("", "Batch Jobs","Renew Entitlement", "", "", "","","NAGRA","","RENEW","");
						Thread.sleep(80000);

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
							System.out.println("PR-3768 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Association change with Add Product");
						}
						else
						{
							System.out.println("PR-3768 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Association change with Add Product");
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


				@Test(description="Retracking with Activate Product List")
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


						oNavigate.toScheduleJob();
						Thread.sleep(2000);
						oScheduleJob.RenewEntitlement_Job("", "Batch Jobs","Renew Entitlement", "", "", "","","NAGRA","","RENEW","");
						Thread.sleep(80000);

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
							testLinkConnection.setsNotes("Retracking with Activate Product List is Passed");
						}
						else
						{
							System.out.println("PR-3769 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Retracking with Activate Product List is Failed");
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


				@Test(description="Retracking with Activate All")
				public void PR_3770() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3770",this);
						System.out.println("PR-3770 Started");
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


						oNavigate.toScheduleJob();
						Thread.sleep(2000);
						oScheduleJob.RenewEntitlement_Job("", "Batch Jobs","Renew Entitlement", "", "", "","","NAGRA","","RENEW","");
						Thread.sleep(80000);


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
							System.out.println("PR-3770 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Retracking with Activate All is Passed");
						}
						else
						{
							System.out.println("PR-3770 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Retracking with Activate All is failed");
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


				@Test(description="Retracking with Add Product")
				public void PR_3771() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3771",this);
						System.out.println("PR-3771 Started");
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

						oNavigate.toScheduleJob();
						Thread.sleep(2000);
						oScheduleJob.RenewEntitlement_Job("", "Batch Jobs","Renew Entitlement", "", "", "","","NAGRA","","RENEW","");
						Thread.sleep(80000);


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
							System.out.println("PR-3771 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Retracking with Add Product is Passed");
						}
						else
						{
							System.out.println("PR-3771 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Retracking with Add Product is Failed");
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


				@Test(description="Suspend with Activate Product List")
				public void PR_3772() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3772",this);
						System.out.println("PR-3772 Started");
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


						oNavigate.toScheduleJob();
						Thread.sleep(2000);
						oScheduleJob.RenewEntitlement_Job("", "Batch Jobs","Renew Entitlement", "", "", "","","NAGRA","","RENEW","");
						Thread.sleep(80000);


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
							System.out.println("PR-3772 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Suspend with Activate Product List is Passed");
						}
						else
						{
							System.out.println("PR-3772 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Suspend with Activate Product List is Failed");
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


				@Test(description="Suspend with Activate All")
				public void PR_3773() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3773",this);
						System.out.println("PR-3773 Started");
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


						oNavigate.toScheduleJob();
						Thread.sleep(2000);
						oScheduleJob.RenewEntitlement_Job("", "Batch Jobs","Renew Entitlement", "", "", "","","NAGRA","","RENEW","");
						Thread.sleep(80000);


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
							System.out.println("PR-3773 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Suspend with Activate All is Passed");
						}
						else
						{
							System.out.println("PR-3773 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Suspend with Activate All is Failed"); 
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


				@Test(description="Suspend with Add Product")
				public void PR_3774() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3774",this);
						System.out.println("PR-3774 Started");
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

						oNavigate.toScheduleJob();
						Thread.sleep(2000);
						oScheduleJob.RenewEntitlement_Job("", "Batch Jobs","Renew Entitlement", "", "", "","","NAGRA","","RENEW","");
						Thread.sleep(80000);


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
							System.out.println("PR-3774 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Suspend with Add Product is Passed");
						}
						else
						{
							System.out.println("PR-3774 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Suspend with Add Product is failed");
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


				@Test(description="Service downgrade with Activate Product List")
				public void PR_3775() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3775",this);
						System.out.println("PR-3775 Started");
						Thread.sleep(3000);
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


						oNavigate.toScheduleJob();
						Thread.sleep(2000);
						oScheduleJob.RenewEntitlement_Job("", "Batch Jobs","Renew Entitlement", "", "", "","","NAGRA","","RENEW","");
						Thread.sleep(80000);

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
							testLinkConnection.setsNotes("Service downgrade with Activate Product List is Passed");
						}
						else
						{
							System.out.println("PR-3775 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Service downgrade with Activate Product List is Failed");
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


				@Test(description="Service downgrade with Activate All")
				public void PR_3776() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3776",this);
						System.out.println("PR-3776 Started");
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


						oNavigate.toScheduleJob();
						Thread.sleep(2000);
						oScheduleJob.RenewEntitlement_Job("", "Batch Jobs","Renew Entitlement", "", "", "","","NAGRA","","RENEW","");
						Thread.sleep(80000);

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
							System.out.println("PR-3776 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Service downgrade with Activate All is Passed");
						}
						else
						{
							System.out.println("PR-3776 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Service downgrade with Activate All is Failed");
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


				@Test(description="Service downgrade with Add Product")
				public void PR_3777() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3777",this);
						System.out.println("PR-3777 Started");
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

						oNavigate.toScheduleJob();
						Thread.sleep(2000);
						oScheduleJob.RenewEntitlement_Job("", "Batch Jobs","Renew Entitlement", "", "", "","","NAGRA","","RENEW","");
						Thread.sleep(80000);


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
							System.out.println("PR-3777 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Service downgrade with Add Product is Passed");
						}
						else
						{
							System.out.println("PR-3777 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Service downgrade with Add Product is Failed");
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

				/*
				------------------Renewal --------------------
				@Test(description="Renewal the contract (Non date driven) with Activate Product List")
				public void PR_3778() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3778",this);
						System.out.println("PR-3778 Started");
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(2000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

						Thread.sleep(3000);
						oNavigate.toServiceOrder();
						oWaitTool.implicitWait(100);
						driver.findElement(By.id("ctl00_uxPgCPH_grdcontractdtls_ctl02_lnkmodify")).click();
						Thread.sleep(3000);
						driver.findElement(locApprove).click();
						Thread.sleep(1000);

						new Select(driver.findElement(By.id("ctl00_uxPgCPH_contractvalidity"))).selectByVisibleText("One Month");
						Thread.sleep(2000);
						driver.findElement(locSaveBtn).click();			 
						Thread.sleep(3000);

						String succ_Msg = driver.findElement(locsuccessmsg).getText();
						System.out.println("Success message is:"+succ_Msg);
						Thread.sleep(3000);
						System.out.println("Test case completed verifying the db request's");
						Thread.sleep(5000);

		 				oNavigate.toScheduleJob();
	            		 Thread.sleep(2000);
	            		 oScheduleJob.RenewEntitlement_Job("", "Batch Jobs","Renew Entitlement", "", "", "","","NAGRA","","RENEW","");
	            		 Thread.sleep(80000);


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
							System.out.println("PR-3778 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Renewal the contract (Non date driven) with Activate Product List is Passed");
						}
						else
						{
							System.out.println("PR-3778 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Renewal the contract (Non date driven) with Activate Product List is Failed");
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


				@Test(description="Renewal the contract (Non date driven) with Activate All")
				public void PR_3779() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3779",this);
						System.out.println("PR-3779 Started");
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
							System.out.println("PR-3779 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Renewal the contract (Non date driven) with Activate All is Passed");
						}
						else
						{
							System.out.println("PR-3779 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Renewal the contract (Non date driven) with Activate All is Failed");
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


				@Test(description="Renewal the contract (Non date driven) with Add Product")
				public void PR_3780() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3780",this);
						System.out.println("PR-3780 Started");
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", backDated, backDated,contract_validity,strPlanCode,"Y",billing_Frequency);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(10000);

						oNavigate.toProvisioningRequest();
						Thread.sleep(3000);

						oProvisioningRequests.ProvisioningRequestsScreen("","",backDated,"","","",New,Sent,"","Search","Save");
						String successMsg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
						System.out.println("Success message is:"+successMsg1 );
						Thread.sleep(2000);
						driver.navigate().refresh();
						oProvisioningRequests.ProvisioningRequestsScreen("","",backDated,"","","",Sent,Accept,"","Search","Save");

						String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
						this.records=oDBConnection.fecthRecords(Customer_id);
						this.record=this.records.get(0);
						String Party_id=record.get("CUSTOMER_ID");
						System.out.println(Party_id);
						oNavigate.toScheduleJob();
						Thread.sleep(2000);
						oScheduleJob.ScheduleJobAutoExpiry_SingleCustomer("", "Batch Jobs","Autoexpire", "", "", "","","AutoExpiry For Single Customer","",Party_id,"");
						Thread.sleep(80000);

						Thread.sleep(2000);
						oNavigate.toRenewal();
						RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"","","",contract_validity,"Save");
						System.out.println("Success message is:"+RenewalValidation);
						Thread.sleep(3000);

						oNavigate.toServiceOrder();
						oWaitTool.implicitWait(100);
						driver.findElement(locModify).click();
						Thread.sleep(5000);
						driver.findElement(locApprove).click();
						Thread.sleep(3000);
						driver.findElement(By.id("ctl00_uxPgCPH_grdproductpricedtls_ctl02_imgbtnremove1")).click();
						Thread.sleep(5000);
						driver.findElement(locPlanSearchTab).sendKeys("NAGRA150"+Keys.TAB);
						Thread.sleep(1000);
						driver.findElement(locAddToCart).click();
						Thread.sleep(1000);
						driver.findElement(locSaveBtn).click();
						Thread.sleep(5000);
						String Succ_Msg = driver.findElement(locsuccessmsg).getText();

						Thread.sleep(10000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=2 order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println("status of the request is :"+Qstatus+""); 
						if(Qstatus.equalsIgnoreCase(C_Status))
						{
							System.out.println("PR-3780 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Renewal the contract (Non date driven) with Add Product is Passed");
						}
						else
						{
							System.out.println("PR-3780 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Renewal the contract (Non date driven) with Add Product is Failed");
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


				@Test(description="Renewal the contract (Date Driven) with Activate Product List")
				public void PR_3781() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3781",this);
						System.out.println("PR-3781 Started");
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
						System.out.println("Contract Number is : "+ContractNumber);

						Thread.sleep(3000);
						oNavigate.toServiceOrder();
						oWaitTool.implicitWait(100);
						driver.findElement(locModify).click();
						Thread.sleep(2000);
						driver.findElement(locApprove).click();
						Thread.sleep(1000);
						driver.findElement(By.id("ctl00_uxPgCPH_grdproductpricedtls_ctl02_imgbtnremove1")).click();
						Thread.sleep(5000);
						driver.findElement(locPlanSearchTab).sendKeys("PLANZ21"+Keys.TAB);
						Thread.sleep(1000);
						driver.findElement(locAddToCart).click();
						Thread.sleep(1000);
						driver.findElement(locSaveBtn).click();
						Thread.sleep(5000);
						String Succ_Msg = driver.findElement(locsuccessmsg).getText();
						Thread.sleep(3000);
						System.out.println("Success message is:"+Succ_Msg);

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
						if(Qstatus.equalsIgnoreCase(N_Status))
						{
							System.out.println("PR-3781 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Renewal the contract (Date Driven) with Activate Product List Is Passed");
						}
						else
						{
							System.out.println("PR-3781 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Renewal the contract (Date Driven) with Activate Product List is Failed");
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


				@Test(description="Renewal the contract (Date Driven) with Activate All")
				public void PR_3782() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3782",this);
						System.out.println("PR-3782 Started");
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "", "", "","",strPlanCode,"Y",billing_Frequency);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);

						Thread.sleep(3000);
						oNavigate.toServiceOrder();
						oWaitTool.implicitWait(100);
						driver.findElement(locModify).click();
						Thread.sleep(2000);
						driver.findElement(locApprove).click();
						Thread.sleep(1000);
						driver.findElement(By.id("ctl00_uxPgCPH_grdproductpricedtls_ctl02_imgbtnremove1")).click();
						Thread.sleep(5000);
						driver.findElement(locPlanSearchTab).sendKeys("PLANZ21"+Keys.TAB);
						Thread.sleep(1000);
						driver.findElement(locAddToCart).click();
						Thread.sleep(1000);
						driver.findElement(locSaveBtn).click();
						Thread.sleep(5000);
						String Succ_Msg = driver.findElement(locsuccessmsg).getText();
						Thread.sleep(3000);
						System.out.println("Success message is:"+Succ_Msg);

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
						if(Qstatus.equalsIgnoreCase(N_Status))
						{
							System.out.println("PR-3782 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Renewal the contract (Date Driven) with Activate All is Passed");
						}
						else
						{
							System.out.println("PR-3782 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Renewal the contract (Date Driven) with Activate All is Failed");
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


				@Test(description="Renewal the contract (Date Driven) with Add Product")
				public void PR_3783() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3783",this);
						System.out.println("PR-3783 Started");
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", backDated, backDated,contract_validity,strPlanCode,"Y",billing_Frequency);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(10000);

						oNavigate.toProvisioningRequest();
						Thread.sleep(3000);

						oProvisioningRequests.ProvisioningRequestsScreen("","",backDated,"","","",New,Sent,"","Search","Save");
						String successMsg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
						System.out.println("Success message is:"+successMsg1 );
						Thread.sleep(2000);
						driver.navigate().refresh();
						oProvisioningRequests.ProvisioningRequestsScreen("","",backDated,"","","",Sent,Accept,"","Search","Save");

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
						Thread.sleep(3000);

						oNavigate.toServiceOrder();
						oWaitTool.implicitWait(100);

						driver.findElement(By.id("ctl00_uxPgCPH_grdcontractdtls_ctl02_lnkmodify")).click();
						Thread.sleep(5000);
						driver.findElement(locApprove).click();
						Thread.sleep(2000);
						new Select(driver.findElement(By.id("ctl00_uxPgCPH_contractvalidity"))).selectByVisibleText("One Month");
						Thread.sleep(5000);
						driver.findElement(locSaveBtn).click();			 
						Thread.sleep(2000);
						String Succ_Msg = driver.findElement(locsuccessmsg).getText();

						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=2 order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println("status of the request is :"+Qstatus+""); 
						if(Qstatus.equalsIgnoreCase(N_Status))	              
						{
							System.out.println("PR-3783 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Renewal the contract (Date Driven) with Add Product is Passed");
						}
						else
						{
							System.out.println("PR-3783 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Renewal the contract (Date Driven) with Add Product is Failed");
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

				------------------renewal ------------------
				 */



				@Test(description="Multi contract with Activate Product List")
				public void PR_3784() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3784",this);
						System.out.println("PR-3784 Started");

						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", backDated, backDated,contract_validity,strPlanCode,"Y",billing_Frequency);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(10000);
						oNavigate.toProvisioningRequest();
						Thread.sleep(5000);

						oProvisioningRequests.ProvisioningRequestsScreen("","",backDated,"","","",New,Sent,"","Search","Save");
						String successMsg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
						System.out.println("Success message is:"+successMsg1 );
						Thread.sleep(2000);
						driver.navigate().refresh();
						oProvisioningRequests.ProvisioningRequestsScreen("","",backDated,"","","",Sent,Accept,"","Search","Save");

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
						RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y","","",contract_validity,"Save");
						System.out.println("Success message is:"+RenewalValidation);
						Thread.sleep(3000);

						oNavigate.toServiceOrder();
						oWaitTool.implicitWait(100);

						driver.findElement(By.id("ctl00_uxPgCPH_grdcontractdtls_ctl02_lnkmodify")).click();
						Thread.sleep(5000);
						driver.findElement(locApprove).click();
						Thread.sleep(2000);
						new Select(driver.findElement(By.id("ctl00_uxPgCPH_contractvalidity"))).selectByVisibleText("One Month");
						Thread.sleep(5000);
						driver.findElement(locSaveBtn).click();			 
						Thread.sleep(3000);
						String Succ_Msg = driver.findElement(locsuccessmsg).getText();
						Thread.sleep(3000);


						oNavigate.toScheduleJob();
						Thread.sleep(2000);
						oScheduleJob.RenewEntitlement_Job("", "Batch Jobs","Renew Entitlement", "", "", "","","NAGRA","","RENEW","");
						Thread.sleep(80000);


						String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=2 order by 1 desc ";
						this.records=oDBConnection.fecthRecords(Status);
						this.record=this.records.get(0);
						String Qstatus=record.get("STATUS");
						System.out.println("status of the request is :"+Qstatus+""); 
						if(Qstatus.equalsIgnoreCase(N_Status))	              
						{
							System.out.println("PR-3784 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Multi contract with Activate Product List is Passed");
						}
						else
						{
							System.out.println("PR-3784 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Multi contract with Activate Product List is Failed");
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



				@Test(description="Multi contract with Activate All")
				public void PR_3785() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3785",this);
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);

						Thread.sleep(2000);
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
						driver.findElement(locPlanSearchTab).sendKeys("PLANZ21"+Keys.TAB);
						Thread.sleep(1000);
						driver.findElement(locAddToCart).click();
						Thread.sleep(1000);
						driver.findElement(locSaveBtn).click();
						Thread.sleep(5000);
						String Succ_Msg = driver.findElement(locsuccessmsg).getText();
						Thread.sleep(3000);
						System.out.println("Success message is:"+Succ_Msg);
						Thread.sleep(20000); 

						oNavigate.toScheduleJob();
						Thread.sleep(2000);
						oScheduleJob.RenewEntitlement_Job("", "Batch Jobs","Renew Entitlement", "", "", "","","NAGRA","","RENEW","");
						Thread.sleep(80000);


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
							System.out.println("PR-3785 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Multi contract with Activate All is Passed");
						}
						else
						{
							System.out.println("PR-3785 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Multi contract with Activate All is Failed");
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




				@Test(description="Multi contract with Add Product")
				public void PR_3786() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3786",this);
						System.out.println("PR-3786 Started");
						CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
						ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
						System.out.println("Contract Number is : "+ContractNumber);
						Thread.sleep(10000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
						System.out.println("Actvation completed");
						Thread.sleep(8000);
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
						Thread.sleep(5000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

						System.out.println("Change order @1  completed");
						Thread.sleep(10000);
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
						String Succ_Msg1 = driver.findElement(locsuccessmsg).getText();
						Thread.sleep(3000);
						System.out.println("Success message is:"+Succ_Msg1);

						Thread.sleep(2000);
						oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();


						oNavigate.toScheduleJob();
						Thread.sleep(2000);
						oScheduleJob.RenewEntitlement_Job("", "Batch Jobs","Renew Entitlement", "", "", "","","NAGRA","","RENEW","");
						Thread.sleep(80000);


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
							System.out.println("PR-3786 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Multi contract with Add Product is Passed");
						}
						else
						{
							System.out.println("PR-3786 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Multi contract with Add Product is failed");
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



				@Test(description="Multi Room with Activate Product List")
				public void PR_3787() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3787",this);
						System.out.println("PR-3787 Started");
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

						oNavigate.toScheduleJob();
						Thread.sleep(2000);
						oScheduleJob.RenewEntitlement_Job("", "Batch Jobs","Renew Entitlement", "", "", "","","NAGRA","","RENEW","");
						Thread.sleep(80000);



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
							System.out.println("PR-3787 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Multi Room with Activate Product List is Passed");
						}
						else
						{
							System.out.println("PR-3787 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Multi Room with Activate Product List is failed");
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



				@Test(description="Multi Room with Activate All")
				public void PR_3788() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3788",this);
						System.out.println("PR-3788 Started");
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
							System.out.println("PR-3788 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Multi Room with Activate All is Passed");
						}
						else
						{
							System.out.println("PR-3788 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Multi Room with Activate All is failed");
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



				@Test(description="Multi Room  with Add Product")
				public void PR_3789() throws Exception
				{
					try
					{
						testLinkConnection.setsTestCase("PR-3789",this);
						System.out.println("PR-3789 Started");
						Thread.sleep(2000);
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
							System.out.println("PR-3789 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Multi Room  with Add Product is Passed");
						}
						else
						{
							System.out.println("PR-3789 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Multi Room  with Add Product is failed");
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



				@Test(description="Single contract with is_bulk as Y")
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
							System.out.println("PR-4478 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Single contract with is_bulk as Y is Passed");
						}
						else
						{
							System.out.println("PR-4478 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Single contract with is_bulk as Y is Failed");
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








	
}
