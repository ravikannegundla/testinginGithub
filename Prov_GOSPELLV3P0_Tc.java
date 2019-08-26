package ProvisioningTC.ProvisioningAdaptersTC;

import java.util.ArrayList;
import java.util.Hashtable;

import mq.seleniumFW.connection.MQDBConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import testlink.api.java.client.TestLinkAPIResults;

import com.InventoryAndLogisticsPageObject.HardwareReplacement;
import com.OrderManagementPageObject.Disconnection;
import com.OrderManagementPageObject.Reconnection;
import com.OrderManagementPageObject.Renewal;
import com.PageObjects.OrderManagementMasters.PricingMaster.BasicServices;
import com.PageObjects.OrderManagementMasters.PricingMaster.Bundle;
import com.PageObjects.OrderManagementMasters.PricingMaster.PricePlan;
import com.PageObjects.OrderManagementMasters.PricingMaster.ServiceGroup;
import com.ProvisioningPageObject.ActivityPrerequisites;
import com.ProvisioningPageObject.BasicConfigurationsOfProvisioning;
import com.ProvisioningPageObject.HardwareForProvisioning;
import com.ProvisioningPageObject.ProvisioningService;
import com.ProvisioningPageObject.ProvisioningSystemAction;
import com.ProvisioningPageObject.ProvisioningSystemCreation;
import com.ProvisioningPageObject.Retracking;
import com.ReceivablesPageObject.Collections;

import TestFrameWork.MQProvisioning;
import Utility.Navigate;
import Utility.WaitTool;

public class Prov_GOSPELLV3P0_Tc extends MQProvisioning


{

	
			WebDriver driver;
			Navigate oNavigate;
			WaitTool oWaitTool;
			PricePlan oPricePlan;
			BasicConfigurationsOfProvisioning oBasicConfigurationsOfProvisioning;
			Disconnection oDisconnection;
			Reconnection oReconnection;
			HardwareReplacement oHardwareReplacement;
			Renewal oRenewal;
			Collections oCollections;
			Retracking oRetracking;
			public MQDBConnection oDBConnection;

			BasicServices  oBasicServices;
			Bundle         oBundle;
			ServiceGroup   oServiceGroup;
			ProvisioningSystemCreation oProvisioningSystemCreation;
			HardwareForProvisioning oHardwareForProvisioning;
			ProvisioningService oProvisioningService;
			ProvisioningSystemAction oProvisioningSystemAction;
			ActivityPrerequisites oActivityPrerequisites;

			public By ScreenOutPut =By.xpath(".//*[@id='ctl00__infoSpan']");
			public By locContractNumber = By.xpath(".//*[@id='ctl00_uxPgCPH_txtcontractno']");
			public By ValidationMessage=By.id("ctl00__infoSpan");
			public By btnsave = By.name("ctl00$uxPgCPH$btnsave");
			public By locModify = By.id("ctl00_uxPgCPH_grdcontractdtls_ctl02_lnkmodify");
			public By locPlanSearchTab = By.id("ctl00_uxPgCPH_txtplnsrch");
			public By locAddToCart = By.xpath(".//*[@id='ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd']");
			public By locSaveBtn=By.id("ctl00_uxPgCPH_btnsave");
			public By locApprove=By.id("ctl00_uxPgCPH_chkapprove");
			public By locsuccessmsg=By.xpath(".//*[@id='ctl00__infoSpan']");


			public ArrayList<Hashtable<String, String>> records;
			public Hashtable<String,String> record;
			ArrayList<String> Actionids_List= new ArrayList<String>();

			String billing_Frequency = "One Month";
			String contract_validity = "One Month";

			String StatusMRPL="Faulty";
			String C_Status="C",N_Status="N";
			String Party_id,Qstatus,Customer_id;
			String Item1="Smart Card",Item2="Set Top Box",item3="Cable";

			String mrnNumber;
			public String Inventory_Location="Head Location",Supplier="Conax Supplier";
			String PartyClass="Customer", Entity="CORP",Location="Head Location",Succ_Msg;

			String Reason="BADPAYER";
			String ReasonMRPL="BAD";
			public String ContractNumber;
			String OperationalEntity="CORP";
			public String OrderType = "One Time Sale Order";
			String RenewalValidation;

			String sResult,Result,sRes;
			String sQuery,CustomerNumber;
			int record1;

			public String Service_Class="TV Channel";
			public String packageable="packageable";
			public String  Requires_Activation="Requires_Activation";
			public String btn="Save";
			public String Basic_Service_status="Active",Screen_Validation;
			String chargeUnit = "One Month";
			String locPlanType = "Perpetual";
			String locSaveOrCancelBtn = "Save";
			String locpricingBtn = "Pricing";
			String locPrice = "100";
			String locPricingSaveBtn = "Save";

			String getRequestCount,Request_Count;
			int RequestCount;


			String Prov_System_Name="GOSPELLV3P0";
			String Prov_System_Code="GOSPELLV3P0";
			String Eff_date="03-05-2019";
			String Plan_code="GOSPELLV3P0" ;

			protected Prov_GOSPELLV3P0_Tc(String sLogFileName) throws Exception 
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
				this.oBasicConfigurationsOfProvisioning=new BasicConfigurationsOfProvisioning(driver,dbConnection);
				this.oDisconnection = new Disconnection(driver);
				this.oReconnection = new Reconnection(driver);
				this.oHardwareReplacement= new HardwareReplacement(driver,dbConnection);
				this.oCollections=new Collections(driver,dbConnection);
				this.oRenewal = new Renewal(driver);
				this.oRetracking=new Retracking(driver,dbConnection);
				oBasicConfigurationsOfProvisioning.OwnHardware_Privileges();

				this.oBasicServices=new BasicServices(driver);
				this.oBasicServices=new BasicServices(driver, oDBConnection);
				this.oBundle = new Bundle(driver);
				this.oPricePlan = new PricePlan(driver, oDBConnection);
				this.oServiceGroup=new ServiceGroup(driver);
				this.oProvisioningSystemCreation= new ProvisioningSystemCreation(driver,dbConnection);
				this.oHardwareForProvisioning= new HardwareForProvisioning(driver,dbConnection);
				this.oProvisioningService= new ProvisioningService(driver,dbConnection);
				this.oProvisioningSystemAction= new ProvisioningSystemAction(driver,dbConnection);
				this.oActivityPrerequisites= new ActivityPrerequisites(driver,dbConnection);
				testLinkConnection.setsTestProject("PayTV Provisioning");
				testLinkConnection.setsTestPlan("5.13");
				testLinkConnection.setsBuild("5.13");
				this.oNavigate=new Navigate(driver,dbConnection);
				this.oWaitTool= new WaitTool(driver);
				PageFactory.initElements(driver,this);
				verifyLogin("","");
			}




			public void relogin() throws Exception
			{
				oNavigate.Navigation_Action("Logout");
				oWaitTool.implicitWait(10);		
				verifyLogin("KRAVI","KRAVI1234");
				oWaitTool.implicitWait(10);
			}



			// Basic Configuration
			@Test(description="Define the provisioning system ")
			public void PR_4332() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4332",this);
					System.out.println("PR-4332 Started");
					Thread.sleep(2000);
					relogin();
					oNavigate.toProvisioningSystem();
					Thread.sleep(2000);
					Screen_Validation=oProvisioningSystemCreation.ProvisioningSystem(Prov_System_Code,Prov_System_Name,"", "", "", "", "", "", "","GMT+05:30", "", "", "", "", "","Save") ;
					System.out.println("validation message"+Screen_Validation);
					if(Screen_Validation.length()!=0)
					{
						System.out.println("PR-4332 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
					}
					else
					{
						System.out.println("PR-4332 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Status is not active and success message is not displaying");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-4332",driver);
					System.out.println("Test__PR-4332___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			

			@Test(description="Hardware For provisioning")
			public void PR_4333() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4333",this);
					System.out.println("PR-4333 Started");
					Thread.sleep(2000);
					relogin();
					oNavigate.toHardwareForProvisioning();
					Thread.sleep(2000);
					Screen_Validation=oHardwareForProvisioning.HardwareMappingForProvisioningSystem(Prov_System_Name,Item1,Item2,"Save");
					System.out.println("validation message"+Screen_Validation);
					if(Screen_Validation.length()!=0)
					{
						System.out.println("PR-4333 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
					}
					else
					{
						System.out.println("PR-4333 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Status is not active and success message is not displaying");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-4333",driver);
					System.out.println("Test__PR-4333___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			
			@Test(description="Basic Service definition ")
			public void PR_4334() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4334",this);
					System.out.println("PR-4334 Started");
					Thread.sleep(2000);
					relogin();
					oNavigate.toBasicServices();
					Thread.sleep(2000);
					Screen_Validation=oBasicServices.BasicSerives_Creation_prov(Prov_System_Name,Prov_System_Name,Service_Class,packageable,Requires_Activation,"","",Basic_Service_status,Prov_System_Name,"","Notes @1",btn);
					System.out.println("validation message"+Screen_Validation);
					if(Screen_Validation.length()!=0)
					{
						System.out.println("PR-4334 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes(" Basic Service definition creation is done");
					}
					else
					{
						System.out.println("PR-4334 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Basic Service definition is not Created");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-4334",driver);
					System.out.println("Test__PR-4334___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			

			@Test(description="Service Group definition")
			public void PR_4335() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4335",this);
					System.out.println("PR-4335 Started");
					Thread.sleep(2000);
					relogin();

					String GroupIdCountValue = "select COUNT(GROUP_ID) AS COUNT from SERVICE_GROUP  where GROUP_CODE='"+Prov_System_Name+"' or GROUP_NAME='"+Prov_System_Name+"'";
					this.records=oDBConnection.fecthRecords(GroupIdCountValue);
					this.record=this.records.get(0);
					String GroupIdCount=record.get("COUNT");
					System.out.println("Group Id Count is "+GroupIdCount);
					int GroupId_Count = Integer.parseInt(GroupIdCount);
					System.out.println("Group Id Count is "+GroupId_Count);

					// if the  Group id  is not exist's in db then the New Service  will be created.
					if(GroupId_Count==0)  
					{
						oNavigate.toServiceGroup();
						Thread.sleep(5000);
						oServiceGroup.ServiceGroup_Creation(1,Prov_System_Name,Prov_System_Name,Service_Class,"Add","Save");
						Screen_Validation=driver.findElement(ValidationMessage).getText();
						Thread.sleep(2000);
						System.out.println(Screen_Validation);
					}
					else 
					{
						System.out.println("Group id already exists");
						Screen_Validation="Group id already exists";
					}
					if(Screen_Validation.length()!=0)
					{
						System.out.println("PR-4335 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
					}
					else
					{
						System.out.println("PR-4335 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Status is not active and success message is not displaying");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-4335",driver);
					System.out.println("Test__PR-4335___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			

			@Test(description="Bundle definition")
			public void PR_4336() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4336",this);
					System.out.println("PR-4336 Started");
					Thread.sleep(2000);
					relogin();
					String BundleCountValue = "select COUNT(BUNDLE_ID) AS COUNT from bundle where BUNDLE_NAME='"+Prov_System_Name+"' or BUNDLE_CODE='"+Prov_System_Name+"'";
					this.records=oDBConnection.fecthRecords(BundleCountValue);
					this.record=this.records.get(0);
					String BundleCount=record.get("COUNT");
					System.out.println("Group Id Count is "+BundleCount);
					int Bundle_Count = Integer.parseInt(BundleCount);
					System.out.println("Group Id Count is "+Bundle_Count);

					// if the  Bundle  is not exist's in db then the New Service  will be created.
					if(Bundle_Count==0)  
					{
						oNavigate.toBundle();
						Thread.sleep(3000);
						Screen_Validation=oBundle.Bundle_Creation(Prov_System_Name,Prov_System_Name,"General",Service_Class,"Active",1,"Add","Service",Prov_System_Name,"","","","Save");
						Thread.sleep(2000);
						System.out.println(Screen_Validation);

					}

					else 
					{
						System.out.println("Bundle already exists");
						Screen_Validation="Bundle already exists";
					}
					if(Screen_Validation.length()!=0)
					{

						System.out.println("PR-4336 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
					}
					else
					{
						System.out.println("PR-4336 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Status is not active and success message is not displaying");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-4336",driver);
					System.out.println("Test__PR-4336___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			
			@Test(description="Price plan definition")
			public void PR_4337() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4337",this);
					System.out.println("PR-4337 Started");
					Thread.sleep(2000);
					relogin();

					String PricePlanCountValue = "select COUNT(PLAN_ID) AS COUNT from priceplan where PLAN_CODE='"+Prov_System_Name+"' or PLAN_DESC='"+Prov_System_Name+"'";
					this.records=oDBConnection.fecthRecords(PricePlanCountValue);
					this.record=this.records.get(0);
					String PricePlanCount=record.get("COUNT");
					System.out.println("PricePlan Count is "+PricePlanCount);
					int PricePlan_Count = Integer.parseInt(PricePlanCount);
					System.out.println("Group Id Count is "+PricePlan_Count);

					// if the  Price plan  is not exist's in db then the New Service  will be created.
					if(PricePlan_Count==0)  
					{
						Thread.sleep(2000);
						oNavigate.toPricePlan();
						Thread.sleep(1000);
						oPricePlan.PricePlanCreation(chargeUnit,Prov_System_Name,Prov_System_Name,"",Prov_System_Name,"","","","",locPlanType,"One Month"," "," ","",""," ","","Basic",""," "," ","","",locSaveOrCancelBtn,locpricingBtn,Eff_date,locPrice,locPricingSaveBtn);
						Screen_Validation=driver.findElement(ScreenOutPut).getText();
						Thread.sleep(5000);
						System.out.println(Screen_Validation);

					}

					else 
					{
						System.out.println("Price Plan  already exists");
						Screen_Validation="Price Plan already exists";
						System.out.println("length of the vaildation message"+Screen_Validation.length());
					}
					if(Screen_Validation.length()!=0)
					{
						System.out.println("PR-4337 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
					}
					else
					{
						System.out.println("PR-4337 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Status is not active and success message is not displaying");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-4337",driver);
					System.out.println("Test__PR-4337___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			// extra to add for provision Service.....	

			@Test(description="Provision Service Definition")
			public void PR_4338() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4338",this);
					System.out.println("PR-4338 Started");
					Thread.sleep(2000);
					relogin();
					oNavigate.toProvisioningService();
					Thread.sleep(2000);
					Screen_Validation=oProvisioningService.ProvisioningServiceMapping(Prov_System_Name,"SERVICE",Prov_System_Name,"Save");
					System.out.println("validation message is :-"+Screen_Validation);
					if(Screen_Validation.length()!=0)
					{
						System.out.println("PR-4338 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
					}
					else
					{
						System.out.println("PR-4338 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Status is not active and success message is not displaying");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-4338",driver);
					System.out.println("Test__PR-4338___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			
			// End of Basic Configuration

			//   Start of  Bussiness Processes For Cdcasv1.11.1

			// Start of  product list case's




			@Test(description="Provision Actions")
			public void PR_4357() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4357",this);
					System.out.println("PR-4357 Started");
					Thread.sleep(2000);
					relogin();
					Actionids_List = new ArrayList(10);
					Actionids_List.add("156");
					Actionids_List.add("129");
					Actionids_List.add("158");
					Actionids_List.add("17");
					Actionids_List.add("19");
					System.out.println("array list is "+Actionids_List);

					oNavigate.toProvisioningSystemAction();
					Thread.sleep(2000);
					Screen_Validation=oProvisioningSystemAction.ProvisioningActionMapping(Prov_System_Name,Actionids_List,"Save");
					System.out.println("validation message"+Screen_Validation);

					Thread.sleep(2000);
					if(Screen_Validation.length()!=0 )
					{
						System.out.println("PR-4357 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
					}
					else
					{
						System.out.println("PR-4357 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Status is not active and success message is not displaying");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-4357",driver);
					System.out.println("Test__PR-4357___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Pre Requisite Mapping")
			public void PR_4358() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4358",this);
					System.out.println("PR-4358 Started");
					Thread.sleep(2000);
					relogin();
					Thread.sleep(2000);
					oNavigate.toActivityPrerequisites();
					Thread.sleep(2000);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"156","13","Pre Action","Y",1);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"156","12","Pre Action","N",2);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"129","87","Post Action","N",3);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"158","13","Pre Action","Y",4);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"158","12","Pre Action","N",5);

					driver.findElement(btnsave).click();
					String Screen_Validation1=driver.findElement(ValidationMessage).getText();
					Thread.sleep(2000);
					System.out.println(Screen_Validation1);
					if(Screen_Validation1.length()!=0)
					{
						System.out.println("PR-4358 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
					}
					else
					{
						System.out.println("PR-4358 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Status is not active and success message is not displaying");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-4358",driver);
					System.out.println("Test__PR-4358___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			

			@Test(description="Activation – New Agreement – Perpetual Contract ")
			public void PR_4359() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4359",this);
					System.out.println("PR-4359 Started");
					Thread.sleep(2000);
					relogin();
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(90000);

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
						System.out.println("PR-4359 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes(" Customer activation is done  ");
					}
					else
					{
						System.out.println("PR-4359 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Customer activation is pending ");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-4359",driver);
					System.out.println("Test__PR-4359___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			
			@Test(description="Activation – New Agreement – Perpetual Contract ")
			public void PR_4360() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4360",this);
					System.out.println("PR-4360 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(90000);

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
						System.out.println("PR-4360 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes(" Customer activation is done  ");
					}
					else
					{
						System.out.println("PR-4360 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Customer activation is pending ");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-4360",driver);
					System.out.println("Test__PR-4360___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			

			@Test(description="Change Order")
			public void PR_4361() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4361",this);
					System.out.println("PR-4361 Started");
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);

					Thread.sleep(3000);
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
						System.out.println("PR-4361 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes(" Change order done by deleting existing plan and adding new plan");
					}
					else
					{
						System.out.println("PR-4361 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes(" Customer is in pending state");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-4361",driver);
					System.out.println("Test__PR-4361___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			
			@Test(description="Disconnection Agreement")
			public void PR_4362() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4362",this);
					System.out.println("PR-4362 Started");
					Thread.sleep(5000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					Thread.sleep(5000);
					oNavigate.toDisconnection();
					Thread.sleep(5000);
					sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
					String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
					System.out.println("Success message is:"+success_Msg);
					System.out.println("Disconnection is done");
					Thread.sleep(70000);

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
						System.out.println("PR-4362 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes(" Disconnection is Done");
					}
					else
					{
						System.out.println("PR-4362 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Disconnection Is Pending");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-4362",driver);
					System.out.println("Test__PR-4362___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Reconnection Agreement ")
			public void PR_4363() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4363",this);
					System.out.println("PR-4363 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
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
					Thread.sleep(90000);

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
						System.out.println("PR-4363 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Reconnection Is Done");
					}
					else
					{
						System.out.println("PR-4363 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Reconnection is In Pending");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-4363",driver);
					System.out.println("Test__PR-4363___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			// END OF PRODUCT..List

			// Start of  all actions case's


			@Test(description="Suspend Agreement")
			public void PR_4364() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4364",this);
					System.out.println("PR-4364 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
					Thread.sleep(75000);
					Thread.sleep(75000);

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
						System.out.println("PR-4364 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Suspend Agreement with suspend all Is Done");
					}
					else
					{
						System.out.println("PR-4364 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Suspend Agreement with suspend all Is In Pending");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-4364",driver);
					System.out.println("Test__PR-4364___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Reactivate Agreement")
			public void PR_4365() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4365",this);
					System.out.println("PR-4365 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
					Thread.sleep(75000);
					Thread.sleep(75000);

					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Active", "", "Save");
					Thread.sleep(70000);
					Thread.sleep(75000);

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
						System.out.println("PR-4365 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Reactivate Agreement with Reactivate all Is Done");
					}
					else
					{
						System.out.println("PR-4365 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Reactivate Agreement with Reactivate all Is In Pending");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-4365",driver);
					System.out.println("Test__PR-4365___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Hardware Change")
			public void PR_4366() throws Exception
			{

				try
				{
					testLinkConnection.setsTestCase("PR-4366",this);
					System.out.println("PR-4366 Started");
					Thread.sleep(20000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Sale", "", "","",Prov_System_Name,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					Thread.sleep(5000);
					oNavigate.toHardwareReplacement();
					oWaitTool.implicitWait(20);		        	  
					oHardwareReplacement.HardwareReplacement_Header(CustomerNumber,"", "","",ReasonMRPL,"","","","","","","","","","","New");
					Thread.sleep(5000);
					oHardwareReplacement.HardwareReplacement_Item_Dtls(Item1,"",StatusMRPL,Item1,OperationalEntity,Location,OrderType,1);
					Thread.sleep(2000);
					driver.findElement(btnsave).click();

					Thread.sleep(70000);
					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=3 order by 1 desc";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 

					if(Qstatus.equalsIgnoreCase(C_Status))
					{
						System.out.println("PR-4366 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Hardware change Is Done");
					}
					else
					{
						System.out.println("PR-4366 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Hardware change Is In Pending");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-4366",driver);
					System.out.println("Test__PR-4366___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Renewal")
			public void PR_4367() throws Exception
			{

				try
				{
					testLinkConnection.setsTestCase("PR-4367",this);
					System.out.println("PR-4367 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(20000);

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
						System.out.println("PR-4367 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Renewal Is Done");
					}
					else
					{
						System.out.println("PR-4367 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Renewal Is In Pending");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-4367",driver);
					System.out.println("Test__PR-4367___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Retracking")    
			public void PR_4368() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4368",this);
					System.out.println("PR-4368 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					Thread.sleep(2000);
					oNavigate.toRetracking();
					oRetracking.RetrackingScreen(CustomerNumber, "Service",1, "N", "Save");
					Thread.sleep(70000);

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID in (17,18)   order by 1 desc ";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status))
					{
						System.out.println("PR-4368 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Retracking with uniqueid Is Done");
					}
					else
					{
						System.out.println("PR-4368 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Retracking with uniqueid Is In pending");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-4368",driver);
					System.out.println("Test__PR-4368___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}	



			@Test(description="Retracking with INITIALISE")
			public void PR_4369() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4369",this);
					System.out.println("PR-4369 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					Thread.sleep(70000);
					oNavigate.toRetracking();
					oRetracking.RetrackingScreen(CustomerNumber, "UniqueId",1, "N", "Save");
					Thread.sleep(70000);

					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID in (17,18)   order by 1 desc ";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus); 
					if(Qstatus.equalsIgnoreCase(C_Status))
					{
						System.out.println("PR-4369 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Retracking Is Done");
					}
					else
					{
						System.out.println("PR-4369 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Retracking Is In Pending");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-4369",driver);
					System.out.println("Test__PR-4369___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			// End of actvate all case's



			// End of  Bussiness Processes For Gospell V.3





}
