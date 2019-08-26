package ProvisioningTC.ProvisioningAdaptersTC;

import java.util.ArrayList;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

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
import mq.seleniumFW.connection.MQDBConnection;
import testlink.api.java.client.TestLinkAPIResults;

public class Prov_NAGRACARDLESS_STB_Tc extends MQProvisioning

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

			String Prov_System_Name="NAGRACARD";
			String Prov_System_Code="NAGRACARD";
			String Eff_date="29-04-2019";


			protected Prov_NAGRACARDLESS_STB_Tc(String sLogFileName) throws Exception 
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
				testLinkConnection.setsTestPlan("5.12");
				testLinkConnection.setsBuild("512");
				this.oNavigate=new Navigate(driver,dbConnection);
				this.oWaitTool= new WaitTool(driver);
				PageFactory.initElements(driver,this);
				verifyLogin("","");
			}


			public void relogin() throws Exception
			{
				oNavigate.Navigation_Action("Logout");
				oWaitTool.implicitWait(10);		
				verifyLogin("KRAVI","KRAVI123");
				oWaitTool.implicitWait(10);
			}



			// Basic Configuration
			@Test(description="Define the provisioning system")
			public void PR_3237() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3237",this);
					System.out.println("PR-3237 Started");
					Thread.sleep(2000);
					relogin();
					oNavigate.toProvisioningSystem();
					Thread.sleep(2000);
					Screen_Validation=oProvisioningSystemCreation.ProvisioningSystem(Prov_System_Code,Prov_System_Name,"", "", "", "", "", "", "","GMT+05:30", "", "", "", "", "","Save") ;
					System.out.println("validation message"+Screen_Validation);
					if(Screen_Validation.length()!=0)
					{
						System.out.println("PR-3237 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Define the provisioning system is Passed");
					}
					else
					{
						System.out.println("PR-3237 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Define the provisioning system is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3237",driver);
					System.out.println("Test__PR-3237___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			
			@Test(description="Hardware For provisioning")
			public void PR_3239() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3239",this);
					System.out.println("PR-3239 Started");
					Thread.sleep(2000);
					relogin();
					oNavigate.toHardwareForProvisioning();
					Thread.sleep(2000);

					String InactiveTheOldHardwaredetails = "update prov_system_hw_map set STATUS='I' where PROV_SYSTEM_ID IN (select PROV_SYSTEM_ID from PROVISIONING_SYSTEM  WHERE PROV_SYSTEM_NAME='"+Prov_System_Name+"') and STATUS='A'";
					this.record1=oDBConnection.updateRecords(InactiveTheOldHardwaredetails);
					System.out.println(" Records are updated and commited succefully");
					Thread.sleep(2000);

					new Select(driver.findElement(By.name("ctl00$uxPgCPH$cmbProv"))).selectByVisibleText(Prov_System_Name);
					Thread.sleep(3000);

					driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_btnadd']")).click();
					Thread.sleep(2000);
					new Select(driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvitem_ctl02_ddlClassCode']"))).selectByVisibleText("TV Channel");
					Thread.sleep(2000);
					new Select(driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvitem_ctl02_dhwitem']"))).selectByVisibleText(Item2);
					Thread.sleep(1000);
					driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvitem_ctl02_Isprimary']")).click();
					Thread.sleep(2000);
					driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_btnupdate']")).click();
					System.out.println("adding the primary hardware is completed");
					driver.findElement(btnsave).click();

					Screen_Validation=driver.findElement(ValidationMessage).getText();
					Thread.sleep(2000);
					System.out.println("validation message"+Screen_Validation);
					if(Screen_Validation.length()!=0)
					{
						System.out.println("PR-3239 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Hardware For provisioning is passed");
					}
					else
					{
						System.out.println("PR-3239 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Hardware For provisioning is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3239",driver);
					System.out.println("Test__PR-3239___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			

			@Test(description="Basic Service definition")
			public void PR_3240() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3240",this);
					System.out.println("PR-3240 Started");
					Thread.sleep(2000);
					relogin();
					oNavigate.toBasicServices();
					Thread.sleep(2000);
					Screen_Validation=oBasicServices.BasicSerives_Creation_prov(Prov_System_Name,Prov_System_Name,Service_Class,packageable,Requires_Activation,"","",Basic_Service_status,Prov_System_Name,"","Notes @1",btn);
					System.out.println("validation message"+Screen_Validation);
					if(Screen_Validation.length()!=0)
					{
						System.out.println("PR-3240 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Basic Service definition is Passed");
					}
					else
					{
						System.out.println("PR-3240 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Basic Service definition is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3240",driver);
					System.out.println("Test__PR-3240___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			

			@Test(description="Service Group definition")
			public void PR_3411() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3411",this);
					System.out.println("PR-3411 Started");
					Thread.sleep(2000);
					relogin();
					oNavigate.toServiceGroup();
					Thread.sleep(5000);
					oServiceGroup.ServiceGroup_Creation(1,Prov_System_Name,Prov_System_Name,Service_Class,"Add","Save");
					String Screen_Validation=driver.findElement(ValidationMessage).getText();
					Thread.sleep(2000);
					System.out.println(Screen_Validation);
					if(Screen_Validation.contains("Service Group definition"))
					{
						System.out.println("PR-3411 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Service Group definition is passed");
					}
					else
					{
						System.out.println("PR-3411 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Service Group definition is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3411",driver);
					System.out.println("Test__PR-3411___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			

			@Test(description="Bundle definition")
			public void PR_3241() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3241",this);
					System.out.println("PR-3241 Started");
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
						System.out.println("PR-3241 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Bundle definition is Passed");
					}
					else
					{
						System.out.println("PR-3241 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Bundle definition is failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3241",driver);
					System.out.println("Test__PR-3241___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			
			@Test(description="Price plan definition")
			public void PR_3242() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3242",this);
					System.out.println("PR-3242 Started");
					Thread.sleep(2000);
					relogin();
					Thread.sleep(2000);
					String PricePlanCountValue = "select COUNT(PLAN_ID) AS COUNT from priceplan where PLAN_CODE='"+Prov_System_Name+"' or PLAN_DESC='"+Prov_System_Name+"'";
					this.records=oDBConnection.fecthRecords(PricePlanCountValue);
					this.record=this.records.get(0);
					String PricePlanCount=record.get("COUNT");
					System.out.println("Group Id Count is "+PricePlanCount);
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
					}
					if(Screen_Validation.length()!=0)
					{
						System.out.println("PR-3242 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Price plan definition is Passed");
					}
					else
					{
						System.out.println("PR-3242 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Price plan definition is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3242",driver);
					System.out.println("Test__PR-3242___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			
			@Test(description="Provision Service Definition")
			public void PR_3243() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3243",this);
					System.out.println("PR-3243 Started");
					Thread.sleep(2000);
					relogin();
					oNavigate.toProvisioningService();
					Thread.sleep(2000);
					Screen_Validation=oProvisioningService.ProvisioningServiceMapping(Prov_System_Name,"SERVICE",Prov_System_Name,"Save");
					System.out.println("validation message"+Screen_Validation);
					if(Screen_Validation.length()!=0)
					{
						System.out.println("PR-3243 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Provision Service Definition is Passed");
					}
					else
					{
						System.out.println("PR-3243 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Provision Service Definition is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3243",driver);
					System.out.println("Test__PR-3243___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			// 3244 and 3245 need to write ..

			@Test(description="Pre Requisite Mapping - Product Actions")
			public void PR_3246() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3246",this);
					System.out.println("PR-3246 Started");
					Thread.sleep(2000);
					relogin();
					Actionids_List = new ArrayList(10);
					Actionids_List.add("1");
					Actionids_List.add("2");
					Actionids_List.add("22");
					Actionids_List.add("24");
					Actionids_List.add("83");
					Actionids_List.add("85");
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
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"1","152","Pre Action","N",1);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"1","157","Pre Action","N",2);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"1","12","Pre Action","N",3);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"1","21","Pre Action","N",4);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"16","87","Post Action","N",5);

					driver.findElement(btnsave).click();
					String Screen_Validation1=driver.findElement(ValidationMessage).getText();
					Thread.sleep(2000);
					System.out.println(Screen_Validation1);
					if(Screen_Validation1.length()!=0  && Screen_Validation.length()!=0 )
					{
						System.out.println("PR-3246 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Pre Requisite Mapping - Product Actions is Passed");
					}
					else
					{
						System.out.println("PR-3246 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Pre Requisite Mapping - Product Actions is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3246",driver);
					System.out.println("Test__PR-3246___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Pre Requisite Mapping - Product List Actions")
			public void PR_3257() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3257",this);
					System.out.println("PR-3257 Started");
					Thread.sleep(2000);
					relogin();
					Thread.sleep(2000);
					oNavigate.toActivityPrerequisites();
					Thread.sleep(2000);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"156","152","Pre Action","N",1);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"156","157","Pre Action","N",2);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"156","12","Pre Action","N",3);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"156","21","Pre Action","N",4);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"129","87","Post Action","N",5);


					driver.findElement(btnsave).click();
					String Screen_Validation1=driver.findElement(ValidationMessage).getText();
					Thread.sleep(2000);
					System.out.println(Screen_Validation1);
					if(Screen_Validation1.length()!=0)
					{
						System.out.println("PR-3257 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Pre Requisite Mapping - Product List Actions is Passed");
					}
					else
					{
						System.out.println("PR-3257 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Pre Requisite Mapping - Product List Actions is failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3257",driver);
					System.out.println("Test__PR-3257___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Pre Requisite Mapping - All Actions")
			public void PR_3268() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3268",this);
					System.out.println("PR-3268 Started");
					Thread.sleep(2000);
					relogin();
					Thread.sleep(2000);
					oNavigate.toActivityPrerequisites();
					Thread.sleep(2000);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"89","152","Pre Action","N",1);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"89","157","Pre Action","N",2);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"89","12","Pre Action","N",3);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"89","21","Pre Action","N",4);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"16","87","Post Action","N",5);

					driver.findElement(btnsave).click();
					String Screen_Validation1=driver.findElement(ValidationMessage).getText();
					Thread.sleep(2000);
					System.out.println(Screen_Validation1);
					if(Screen_Validation1.length()!=0)
					{
						System.out.println("PR-3268 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Pre Requisite Mapping - All Actions is Passed");
					}
					else
					{
						System.out.println("PR-3268 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Pre Requisite Mapping - All Actions is failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3268",driver);
					System.out.println("Test__PR-3268___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}




			// End of Basic Configuration



			//   Start of  Bussiness Processes For NAGRA CARDLESS

			// Start of Add product case's



			@Test(description="Activation – New Agreement - Add Product")
			public void PR_3247() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3247",this);
					System.out.println("PR-3247 Started");
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
						System.out.println("PR-3247 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Activation – New Agreement - Add Product is Done");
					}
					else
					{
						System.out.println("PR-3247 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Activation – New Agreement - Add Product is failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3247",driver);
					System.out.println("Test__PR-3247___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Disconnection Agreement - Remove Product")
			public void PR_3248() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3248",this);
					System.out.println("PR-3248 Started");
					Thread.sleep(5000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					Thread.sleep(3000);
					oNavigate.toDisconnection();
					Thread.sleep(5000);
					sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
					String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
					System.out.println("Success message is:"+success_Msg);
					System.out.println("Disconnection is done");
					Thread.sleep(80000);

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
						System.out.println("PR-3248 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Disconnection Agreement - Remove Product is Passed");
					}
					else
					{
						System.out.println("PR-3248 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Disconnection Agreement - Remove Product is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3248",driver);
					System.out.println("Test__PR-3248___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Reconnection Agreement - Reconnect Add Product")
			public void PR_3249() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3249",this);
					System.out.println("PR-3249 Started");
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
					Thread.sleep(80000);
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
						System.out.println("PR-3249 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Reconnection Agreement - Reconnect Add Product is Passed");
					}
					else
					{
						System.out.println("PR-3249 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Reconnection Agreement - Reconnect Add Product is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3249",driver);
					System.out.println("Test__PR-3249___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Suspend Agreement - Suspend Product")
			public void PR_3250() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3250",this);
					System.out.println("PR-3250 Started");
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
						System.out.println("PR-3250 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Suspend Agreement - Suspend Product is Passed");
					}
					else
					{
						System.out.println("PR-3250 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Suspend Agreement - Suspend Product is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3250",driver);
					System.out.println("Test__PR-3250___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Reactivate Agreement - Reactivate Product")
			public void PR_3251() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3251",this);
					System.out.println("PR-3251 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
					Thread.sleep(95000);
					Thread.sleep(95000);

					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Active", "", "Save");
					Thread.sleep(70000);
					Thread.sleep(95000);

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
						System.out.println("PR-3251 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Reactivate Agreement - Reactivate Product is Passed");
					}
					else
					{
						System.out.println("PR-3251 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Reactivate Agreement - Reactivate Product is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3251",driver);
					System.out.println("Test__PR-3251___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Hardware Change - HW Change Add Product")
			public void PR_3252() throws Exception
			{

				try
				{
					testLinkConnection.setsTestCase("PR-3252",this);
					System.out.println("PR-3252 Started");
					Thread.sleep(2000);
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
						System.out.println("PR-3252 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Hardware Change - HW Change Add Product is Passed");
					}
					else
					{
						System.out.println("PR-3252 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Hardware Change - HW Change Add Product is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3252",driver);
					System.out.println("Test__PR-3252___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Renewal - Renew Add Product")
			public void PR_3253() throws Exception
			{

				try
				{
					testLinkConnection.setsTestCase("PR-3253",this);
					System.out.println("PR-3253 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(2000);

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
						System.out.println("PR-3253 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Renewal - Renew Add Product is Passed");
					}
					else
					{
						System.out.println("PR-3253 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Renewal - Renew Add Product is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3253",driver);
					System.out.println("Test__PR-3253___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Retrack - Retrack Product")
			public void PR_3254() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3254",this);
					System.out.println("PR-3254 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					Thread.sleep(80000);
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
						System.out.println("PR-3254 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Retrack - Retrack Product is Passed");
					}
					else
					{
						System.out.println("PR-3254 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Retrack - Retrack Product is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3254",driver);
					System.out.println("Test__PR-3254___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Retrack with Initialize - Retrack Product")    
			public void PR_3255() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3255",this);
					System.out.println("PR-3255 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					Thread.sleep(80000);
					oNavigate.toRetracking();
					oRetracking.RetrackingScreen(CustomerNumber, "UniqueId",1, "N", "Save");
					Thread.sleep(80000);
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
						System.out.println("PR-3255 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Retrack with Initialize - Retrack Product is Done");
					}
					else
					{
						System.out.println("PR-3255 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Retrack with Initialize - Retrack Product is failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3255",driver);
					System.out.println("Test__PR-3255___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			// END OF ADD-PRODUCT..



			// Start of actvate Product list case's

			@Test(description="Provision Actions - Product List Action")
			public void PR_3256() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3256",this);
					System.out.println("PR-3256 Started");
					Thread.sleep(2000);
					relogin();
					Actionids_List = new ArrayList(10);
					Actionids_List.add("156");
					Actionids_List.add("129");
					Actionids_List.add("158");
					Actionids_List.add("159");
					Actionids_List.add("160");
					System.out.println("array list is "+Actionids_List);

					oNavigate.toProvisioningSystemAction();
					Thread.sleep(2000);
					Screen_Validation=oProvisioningSystemAction.ProvisioningActionMapping(Prov_System_Name,Actionids_List,"Save");
					System.out.println("validation message"+Screen_Validation);
					Thread.sleep(2000);

					if(Screen_Validation.length()!=0 )
					{
						System.out.println("PR-3256 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Provision Actions - Product List Action is passed");
					}
					else
					{
						System.out.println("PR-3256 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Provision Actions - Product List Action is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3256",driver);
					System.out.println("Test__PR-3256___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Activation – New Agreement - Activate Product List")
			public void PR_3258() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3258",this);
					System.out.println("PR-3258 Started");
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
						System.out.println("PR-3258 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Activation – New Agreement - Activate Product List is Passed");
					}
					else
					{
						System.out.println("PR-3258 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Activation – New Agreement - Activate Product List is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3258",driver);
					System.out.println("Test__PR-3258___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			
			@Test(description="Disconnection Agreement - Disconnect Products")
			public void PR_3259() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3259",this);
					System.out.println("PR-3259 Started");
					Thread.sleep(5000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					Thread.sleep(3000);
					oNavigate.toDisconnection();
					Thread.sleep(5000);
					sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
					String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
					System.out.println("Success message is:"+success_Msg);
					System.out.println("Disconnection is done");
					Thread.sleep(80000);

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
						System.out.println("PR-3259 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Disconnection Agreement - Disconnect Products is Passed");
					}
					else
					{
						System.out.println("PR-3259 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Disconnection Agreement - Disconnect Products is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3259",driver);
					System.out.println("Test__PR-3259___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			

			@Test(description="Reconnection Agreement - Activate Product List")
			public void PR_3260() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3260",this);
					System.out.println("PR-3260 Started");
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
					Thread.sleep(80000);
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
						System.out.println("PR-3260 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Reconnection Agreement - Activate Product List is Passed");
					}
					else
					{
						System.out.println("PR-3260 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Reconnection Agreement - Activate Product List is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3260",driver);
					System.out.println("Test__PR-3260___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			
			@Test(description="Hardware Change - Activate Product List & Disconnect Products")
			public void PR_3261() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3261",this);
					System.out.println("PR-3261 Started");
					Thread.sleep(2000);
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
						System.out.println("PR-3261 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Hardware Change - Activate Product List & Disconnect Products is passed");
					}
					else
					{
						System.out.println("PR-3261 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Hardware Change - Activate Product List & Disconnect Products is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3261",driver);
					System.out.println("Test__PR-3261___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			

			@Test(description="Suspend Agreement - Suspend Product List")
			public void PR_3262() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3262",this);
					System.out.println("PR-3262 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(10000);
					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
					Thread.sleep(95000);
					Thread.sleep(95000);

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
						System.out.println("PR-3262 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Suspend Agreement - Suspend Product List is Passed");
					}
					else
					{
						System.out.println("PR-3262 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Suspend Agreement - Suspend Product List is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3262",driver);
					System.out.println("Test__PR-3262___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			

			@Test(description="Reactivate Agreement - Reactivate Product List")
			public void PR_3263() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3263",this);
					System.out.println("PR-3263 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(10000);
					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
					Thread.sleep(95000);
					Thread.sleep(85000);

					oNavigate.toCollection();
					oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
					Thread.sleep(5000);
					oCollections.collectionGrid("", "","Active", "", "Save");
					Thread.sleep(90000);
					Thread.sleep(95000);

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
						System.out.println("PR-3263 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Reactivate Agreement - Reactivate Product List is Passed");
					}
					else
					{
						System.out.println("PR-3263 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Reactivate Agreement - Reactivate Product List is failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3263",driver);
					System.out.println("Test__PR-3263___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			
			@Test(description="Renewal - Activate Product List")
			public void PR_3264() throws Exception
			{

				try
				{
					testLinkConnection.setsTestCase("PR-3264",this);
					System.out.println("PR-3264 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(2000);

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
						System.out.println("PR-3264 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Renewal - Activate Product List is passed");
					}
					else
					{
						System.out.println("PR-3264 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Renewal - Activate Product List is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3264",driver);
					System.out.println("Test__PR-3264___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			

			@Test(description="Retrack - Retrack Product List")
			public void PR_3265() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3265",this);
					System.out.println("PR-3265 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					Thread.sleep(80000);
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
						System.out.println("PR-3265 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Retrack - Retrack Product List is Passed");
					}
					else
					{
						System.out.println("PR-3265 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Retrack - Retrack Product List is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3265",driver);
					System.out.println("Test__PR-3265___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			

			@Test(description="Retrack with Initialize - Retrack Product List")    
			public void PR_3266() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3266",this);
					System.out.println("PR-3266 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					Thread.sleep(80000);
					oNavigate.toRetracking();
					oRetracking.RetrackingScreen(CustomerNumber, "UniqueId",1, "N", "Save");
					Thread.sleep(80000);
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
						System.out.println("PR-3266 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Retrack with Initialize - Retrack Product List is Passed");
					}
					else
					{
						System.out.println("PR-3266 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Retrack with Initialize - Retrack Product List is failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3266",driver);
					System.out.println("Test__PR-3266___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}




			// End of actvate PRODUCT LIST case's



			// START OF ALL ACTION'S



			@Test(description="Provision Actions - All Actions")
			public void PR_3267() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3267",this);
					System.out.println("PR-3267 Started");
					Thread.sleep(2000);
					relogin();
					Actionids_List = new ArrayList(10);
					Actionids_List.add("89");
					Actionids_List.add("16");
					Actionids_List.add("17");
					Actionids_List.add("18");
					Actionids_List.add("19");
					System.out.println("array list is "+Actionids_List);

					oNavigate.toProvisioningSystemAction();
					Thread.sleep(2000);
					Screen_Validation=oProvisioningSystemAction.ProvisioningActionMapping(Prov_System_Name,Actionids_List,"Save");
					System.out.println("validation message"+Screen_Validation);

					Thread.sleep(2000);
					if(Screen_Validation.length()!=0 )
					{
						System.out.println("PR-3267 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Provision Actions - All Actions is Passed");
					}
					else
					{
						System.out.println("PR-3267 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Provision Actions - All Actions is failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3267",driver);
					System.out.println("Test__PR-3267___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			
			@Test(description="Activation – New Agreement - Activate All")
			public void PR_3269() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3269",this);
					System.out.println("PR-3269 Started");
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
						System.out.println("PR-3269 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Activation – New Agreement - Activate All is Passed");
					}
					else
					{
						System.out.println("PR-3269 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Activation – New Agreement - Activate All is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3269",driver);
					System.out.println("Test__PR-3269___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			
			@Test(description="Disconnection Agreement - Disconnect All")
			public void PR_3270() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3270",this);
					System.out.println("PR-3270 Started");
					Thread.sleep(5000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					Thread.sleep(3000);
					oNavigate.toDisconnection();
					Thread.sleep(5000);
					sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
					String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
					System.out.println("Success message is:"+success_Msg);
					System.out.println("Disconnection is done");
					Thread.sleep(80000);

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
						System.out.println("PR-3270 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Disconnection Agreement - Disconnect All is passed");
					}
					else
					{
						System.out.println("PR-3270 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Disconnection Agreement - Disconnect All is failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3270",driver);
					System.out.println("Test__PR-3270___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			

			@Test(description="Reconnection Agreement - Activate Product List")
			public void PR_3271() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3271",this);
					System.out.println("PR-3271 Started");
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
					Thread.sleep(80000);

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
						System.out.println("PR-3271 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Reconnection Agreement - Activate Product List is Passed");
					}
					else
					{
						System.out.println("PR-3271 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Reconnection Agreement - Activate Product List is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3271",driver);
					System.out.println("Test__PR-3271___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			

			@Test(description="Hardware Change - Activate Product List & Disconnect Products")
			public void PR_3272() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3272",this);
					System.out.println("PR-3272 Started");
					Thread.sleep(2000);
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
						System.out.println("PR-3272 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Hardware Change - Activate Product List & Disconnect Products is Passed");
					}
					else
					{
						System.out.println("PR-3272 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Hardware Change - Activate Product List & Disconnect Products is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3272",driver);
					System.out.println("Test__PR-3272___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			
			@Test(description="Suspend Agreement - Suspend Product List")
			public void PR_3273() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3273",this);
					System.out.println("PR-3273 Started");
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
						System.out.println("PR-3273 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Suspend Agreement - Suspend Product List is Passed");
					}
					else
					{
						System.out.println("PR-3273 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Suspend Agreement - Suspend Product List is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3273",driver);
					System.out.println("Test__PR-3273___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			

			@Test(description="Reactivate Agreement - Reactivate Product List")
			public void PR_3274() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3274",this);
					System.out.println("PR-3274 Started");
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
						System.out.println("PR-3274 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Reactivate Agreement - Reactivate Product List is Passed");
					}
					else
					{
						System.out.println("PR-3274 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Reactivate Agreement - Reactivate Product List is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3274",driver);
					System.out.println("Test__PR-3274___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Renewal - Activate Product List")
			public void PR_3275() throws Exception
			{

				try
				{
					testLinkConnection.setsTestCase("PR-3275",this);
					System.out.println("PR-3275 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(2000);

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
						System.out.println("PR-3275 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Renewal - Activate Product List is Passed");
					}
					else
					{
						System.out.println("PR-3275 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Renewal - Activate Product List isfailed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3275",driver);
					System.out.println("Test__PR-3275___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Provisioning Family Service")
			public void PR_3276() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3276",this);
					System.out.println("PR-3276 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					Thread.sleep(80000);
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
						System.out.println("PR-3276 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Provisioning Family Service is Passed");
					}
					else
					{
						System.out.println("PR-3276 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Provisioning Family Service is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3276",driver);
					System.out.println("Test__PR-3276___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Provisioning Family Mapping")    
			public void PR_3277() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3277",this);
					System.out.println("PR-3277 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					Thread.sleep(80000);
					oNavigate.toRetracking();
					oRetracking.RetrackingScreen(CustomerNumber, "UniqueId",1, "N", "Save");
					Thread.sleep(80000);
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
						System.out.println("PR-3277 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Provisioning Family Mapping is passed");
					}
					else
					{
						System.out.println("PR-3277 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Provisioning Family Mapping is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3277",driver);
					System.out.println("Test__PR-3277___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}




			// End of  Bussiness Processes For NAgra cardless






}
