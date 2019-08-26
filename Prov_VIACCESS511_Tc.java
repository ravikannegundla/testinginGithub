package ProvisioningTC.ProvisioningAdaptersTC;

import java.util.ArrayList;
import java.util.Hashtable;

import mq.seleniumFW.connection.MQDBConnection;

import org.openqa.selenium.By;
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

public class Prov_VIACCESS511_Tc extends MQProvisioning
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


			String Prov_System_Name="VIACCESS511";
			String Prov_System_Code="VIACCESS511";
			String Eff_date="16-05-2019";
			String Plan_code="VIACCESS511" ;

			protected Prov_VIACCESS511_Tc(String sLogFileName) throws Exception 
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
			@Test(description="Define the provisioning system")
			public void PR_3038() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3038",this);
					System.out.println("PR-3038 Started");
					Thread.sleep(2000);
					relogin();
					oNavigate.toProvisioningSystem();
					Thread.sleep(2000);
					Screen_Validation=oProvisioningSystemCreation.ProvisioningSystem(Prov_System_Code,Prov_System_Name,"", "", "", "", "", "", "","GMT+05:30", "", "", "", "", "","Save") ;
					System.out.println("validation message"+Screen_Validation);
					if(Screen_Validation.length()!=0)
					{
						System.out.println("PR-3038 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Define the provisioning system is passed");
					}
					else
					{
						System.out.println("PR-3038 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Define the provisioning system is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3038",driver);
					System.out.println("Test__PR-3038___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			
			@Test(description="Hardware For provisioning")
			public void PR_3040() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3040",this);
					System.out.println("PR-3040 Started");
					Thread.sleep(2000);
					relogin();
					oNavigate.toHardwareForProvisioning();
					Thread.sleep(2000);
					Screen_Validation=oHardwareForProvisioning.HardwareMappingForProvisioningSystem(Prov_System_Name,Item1,Item2,"Save");
					System.out.println("validation message"+Screen_Validation);
					if(Screen_Validation.length()!=0)
					{
						System.out.println("PR-3040 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Hardware For provisioning is passed");
					}
					else
					{
						System.out.println("PR-3040 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Hardware For provisioning is failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3040",driver);
					System.out.println("Test__PR-3040___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			
			@Test(description="Basic Service definition")
			public void PR_3041() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3041",this);
					System.out.println("PR-3041 Started");
					Thread.sleep(2000);
					relogin();
					oNavigate.toBasicServices();
					Thread.sleep(2000);
					Screen_Validation=oBasicServices.BasicSerives_Creation_prov(Prov_System_Name,Prov_System_Name,Service_Class,packageable,Requires_Activation,"","",Basic_Service_status,Prov_System_Name,"","Notes @1",btn);
					System.out.println("validation message"+Screen_Validation);
					if(Screen_Validation.length()!=0)
					{
						System.out.println("PR-3041 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Basic Service definition is Passed");
					}
					else
					{
						System.out.println("PR-3041 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Basic Service definition is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3041",driver);
					System.out.println("Test__PR-3041___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			

			@Test(description="Service Group definition")
			public void PR_3409() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3409",this);
					System.out.println("PR-3409 Started");
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
						System.out.println("PR-3409 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Service Group definition is passed");
					}
					else
					{
						System.out.println("PR-3409 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Service Group definition is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3409",driver);
					System.out.println("Test__PR-3409___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			

			@Test(description="Bundle definition")
			public void PR_3042() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3042",this);
					System.out.println("PR-3042 Started");
					Thread.sleep(2000);
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

						System.out.println("PR-3042 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Bundle definition is Passed");
					}
					else
					{
						System.out.println("PR-3042 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Bundle definition is failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3042",driver);
					System.out.println("Test__PR-3042___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			
			@Test(description="Price plan definition")
			public void PR_3043() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3043",this);
					System.out.println("PR-3043 Started");
					Thread.sleep(2000);
					relogin();

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
						System.out.println("PR-3043 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Price plan definition is Passed");
					}
					else
					{
						System.out.println("PR-3043 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Price plan definition is failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3043",driver);
					System.out.println("Test__PR-3043___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			

			@Test(description="Pre Requisite Mapping - Product Actions")
			public void PR_3044() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3044",this);
					System.out.println("PR-3044 Started");
					Thread.sleep(2000);
					relogin();
					Actionids_List = new ArrayList(10);
					Actionids_List.add("1");
					Actionids_List.add("2");
					Actionids_List.add("17");
					Actionids_List.add("19");
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
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"1","13","Pre Action","N",1);

					driver.findElement(btnsave).click();
					String Screen_Validation1=driver.findElement(ValidationMessage).getText();
					Thread.sleep(2000);
					System.out.println(Screen_Validation1);
					if(Screen_Validation1.length()!=0 && Screen_Validation.length()!=0 )
					{
						System.out.println("PR-3044 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Pre Requisite Mapping - Product Actions is passed");
					}
					else
					{
						System.out.println("PR-3044 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Pre Requisite Mapping - Product Actions is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3044",driver);
					System.out.println("Test__PR-3044___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			// extra to add for provision Service.....	

			@Test(description="Provision Service Definition")
			public void PR_3440() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3440",this);
					System.out.println("PR-3440 Started");
					Thread.sleep(2000);
					relogin();
					oNavigate.toProvisioningService();
					Thread.sleep(2000);
					Screen_Validation=oProvisioningService.ProvisioningServiceMapping(Prov_System_Name,"SERVICE",Prov_System_Name,"Save");
					System.out.println("validation message is :-"+Screen_Validation);
					if(Screen_Validation.length()!=0)
					{
						System.out.println("PR-3440 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Provision Service Definition is Passed");
					}
					else
					{
						System.out.println("PR-3440 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Provision Service Definition is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3440",driver);
					System.out.println("Test__PR-3440___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			// End of Basic Configuration



			//   Start of  Bussiness Processes For viaccess 511



			@Test(description="Customer activation New aggreement")
			public void PR_3045() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3045",this);
					System.out.println("PR-3045 Started");
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
						System.out.println("PR-3045 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Customer activation New aggreement is passed");
					}
					else
					{
						System.out.println("PR-3045 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Customer activation New aggreement is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3045",driver);
					System.out.println("Test__PR-3045___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			

			@Test(description="Disconnection")
			public void PR_3046() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3046",this);
					System.out.println("PR-3046 Started");
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
					Thread.sleep(90000);

					CustomerNumber="59750";
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
						System.out.println("PR-3046 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Disconnection is Passed");
					}
					else
					{
						System.out.println("PR-3046 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Disconnection Is failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3046",driver);
					System.out.println("Test__PR-3046___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			

			@Test(description="Reconnection")
			public void PR_3047() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3047",this);
					System.out.println("PR-3047 Started");
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
						System.out.println("PR-3047 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Reconnection Is Passed");
					}
					else
					{
						System.out.println("PR-3047 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Reconnection is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3047",driver);
					System.out.println("Test__PR-3047___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			

			@Test(description="Hardware Change")
			public void PR_3048() throws Exception
			{

				try
				{
					testLinkConnection.setsTestCase("PR-3048",this);
					System.out.println("PR-3048 Started");
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
						System.out.println("PR-3048 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Hardware change Is Passed");
					}
					else
					{
						System.out.println("PR-3048 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Hardware change Is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3048",driver);
					System.out.println("Test__PR-3048___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			

			@Test(description="Renewal")
			public void PR_3049() throws Exception
			{

				try
				{
					testLinkConnection.setsTestCase("PR-3049",this);
					System.out.println("PR-3049 Started");
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
						System.out.println("PR-3049 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Renewal Is Passed");
					}
					else
					{
						System.out.println("PR-3049 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Renewal Is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3049",driver);
					System.out.println("Test__PR-3049___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			// END OF PRODUCT..List

			// Start of  all actions case's




			@Test(description="Provisioning system action")
			public void PR_3050() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3050",this);
					System.out.println("PR-3050 Started");
					Thread.sleep(2000);
					relogin();
					Actionids_List = new ArrayList(10);
					Actionids_List.add("1");
					Actionids_List.add("2");
					Actionids_List.add("17");
					Actionids_List.add("19");
					Actionids_List.add("18");

					System.out.println("array list is "+Actionids_List);

					oNavigate.toProvisioningSystemAction();
					Thread.sleep(2000);
					Screen_Validation=oProvisioningSystemAction.ProvisioningActionMapping(Prov_System_Name,Actionids_List,"Save");
					System.out.println("validation message"+Screen_Validation);

					Thread.sleep(2000);
					if( Screen_Validation.length()!=0 )
					{
						System.out.println("PR-3050 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Provisioning system action is Passed");
					}
					else
					{
						System.out.println("PR-3050 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Provisioning system action is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3050",driver);
					System.out.println("Test__PR-3050___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			

			@Test(description="Pre Requisite Mapping - All Actions")
			public void PR_3051() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3051",this);
					System.out.println("PR-3051 Started");
					Thread.sleep(2000);
					relogin();
					Thread.sleep(2000);
					oNavigate.toActivityPrerequisites();
					Thread.sleep(2000);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"1","13","Pre Action","N",1);

					driver.findElement(btnsave).click();
					String Screen_Validation1=driver.findElement(ValidationMessage).getText();
					Thread.sleep(2000);
					System.out.println(Screen_Validation1);
					if(Screen_Validation1.length()!=0)
					{
						System.out.println("PR-3051 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Pre Requisite Mapping - All Actions is Passed");
					}
					else
					{
						System.out.println("PR-3051 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Pre Requisite Mapping - All Actions is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3051",driver);
					System.out.println("Test__PR-3051___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			
			@Test(description="Suspend Agreement")
			public void PR_3052() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3052",this);
					System.out.println("PR-3052 Started");
					Thread.sleep(2000);
					relogin();
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
						System.out.println("PR-3052 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Suspend Agreement is Passed");
					}
					else
					{
						System.out.println("PR-3052 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Suspend Agreement is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3052",driver);
					System.out.println("Test__PR-3052___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			

			@Test(description="Reactivate Agreement")
			public void PR_3053() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3053",this);
					System.out.println("PR-3053 Started");
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
						System.out.println("PR-3053 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Reactivate Agreement is Passed");
					}
					else
					{
						System.out.println("PR-3053 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Reactivate Agreement is Passed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3053",driver);
					System.out.println("Test__PR-3053___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			

			@Test(description="Retracking")    
			public void PR_3054() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3054",this);
					System.out.println("PR-3054 Started");
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
						System.out.println("PR-3054 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Retracking is Passed");
					}
					else
					{
						System.out.println("PR-3054 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Retracking is failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3054",driver);
					System.out.println("Test__PR-3054___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			

			@Test(description="Retrack with initalize")
			public void PR_3055() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3055",this);
					System.out.println("PR-3055 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					Thread.sleep(80000);
					oNavigate.toRetracking();
					oRetracking.RetrackingScreen(CustomerNumber, "UniqueId",1, "Y", "Save");
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
						System.out.println("PR-3055 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Retrack with initalize is Passed");
					}
					else
					{
						System.out.println("PR-3055 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Retrack with initalize is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3055",driver);
					System.out.println("Test__PR-3055___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			// End of actvate all case's

			// End of  Bussiness Processes For viacess 5 11


}
