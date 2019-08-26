package ProvisioningTC.ProvisioningAdaptersTC;

import java.util.ArrayList;
import java.util.Hashtable;

import mq.seleniumFW.connection.MQDBConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import testlink.api.java.client.TestLinkAPIResults;
import TestFrameWork.MQProvisioning;
import Utility.Navigate;
import Utility.WaitTool;

import com.BasicConfigurationsOfServiceOrder.OrderManagementPrivileges;
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

public class Prov_ONLY1_Tc  extends MQProvisioning

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
			OrderManagementPrivileges oOrderManagementPrivileges;

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
			public String 	btn="Save";
			public String Basic_Service_status="Active",Screen_Validation;
			String chargeUnit = "One Month";
			String locPlanType = "Perpetual";
			String locSaveOrCancelBtn = "Save";
			String locpricingBtn = "Pricing";
			String locPrice = "100";
			String locPricingSaveBtn = "Save";


			String Prov_System_Name="ONLY1";
			String Prov_System_Code="ONLY1";
			String Eff_date="02-08-2019";
			String Plan_code="ONLY1" ;

			protected Prov_ONLY1_Tc(String sLogFileName) throws Exception 
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
				
				/*
				oBasicConfigurationsOfProvisioning.OwnHardware_Privileges(); 
				oOrderManagementPrivileges.Disconnection_privileges();
				oOrderManagementPrivileges.Reconnection_privileges();
				oOrderManagementPrivileges.ServiceOrder_privileges();
				 */
				
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
				verifyLogin("SYSADMIN","SYSADMIN");
				oWaitTool.implicitWait(10);
			}



			// Basic Configuration
			@Test(description="Define the provisioning system")
			public void PR_2655() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2655",this);
					System.out.println("PR-2655 Started");
					Thread.sleep(2000);
					relogin();
					oNavigate.toProvisioningSystem();
					Thread.sleep(2000);
					Screen_Validation=oProvisioningSystemCreation.ProvisioningSystem(Prov_System_Code,Prov_System_Name,"", "", "", "", "", "", "","GMT+05:30", "", "", "", "", "","Save") ;
					System.out.println("validation message"+Screen_Validation);
					if(Screen_Validation.length()!=0)
					{
						System.out.println("PR-2655 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Define the provisioning system is Passed");
					}
					else
					{
						System.out.println("PR-2655 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Define the provisioning system is Failed");
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



			@Test(description="Hardware For provisioning")
			public void PR_2656() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2656",this);
					System.out.println("PR-2656 Started");
					Thread.sleep(2000);
					relogin();
					oNavigate.toHardwareForProvisioning();
					Thread.sleep(2000);
					Screen_Validation=oHardwareForProvisioning.HardwareMappingForProvisioningSystem(Prov_System_Name,Item1,Item2,"Save");
					System.out.println("validation message"+Screen_Validation);
					if(Screen_Validation.length()!=0)
					{
						System.out.println("PR-2656 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Hardware For provisioning is Passed");
					}
					else
					{
						System.out.println("PR-2656 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Hardware For provisioning is Failed");
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



			@Test(description="Basic Service definition")
			public void PR_2657() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2657",this);
					System.out.println("PR-2657 Started");
					Thread.sleep(2000);
					relogin();
					oNavigate.toBasicServices();
					Thread.sleep(2000);
					Screen_Validation=oBasicServices.BasicSerives_Creation_prov(Prov_System_Name,Prov_System_Name,Service_Class,packageable,Requires_Activation,"","",Basic_Service_status,Prov_System_Name,"","Notes @1",btn);
					System.out.println("validation message"+Screen_Validation);
					if(Screen_Validation.length()!=0)
					{
						System.out.println("PR-2657 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Basic Service definition is Passed");
					}
					else
					{
						System.out.println("PR-2657 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Basic Service definition is Failed");
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



			@Test(description="Service Group definition")
			public void PR_2658() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2658",this);
					System.out.println("PR-2658 Started");
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
						System.out.println("PR-2658 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Service Group definition is Passed");
					}
					else
					{
						System.out.println("PR-2658 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Service Group definition is Failed");
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



			@Test(description="Bundle definition")
			public void PR_2659() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2659",this);
					System.out.println("PR-2659 Started");
					Thread.sleep(2000);
					relogin();
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
						System.out.println("PR-2659 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Bundle definition is Passed");
					}
					else
					{
						System.out.println("PR-2659 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Bundle definition is Failed");
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



			@Test(description="Price plan definition")
			public void PR_2660() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2660",this);
					System.out.println("PR-2660 Started");
					Thread.sleep(2000);
					relogin();
					String PricePlanCountValue = "select COUNT(PLAN_ID) AS COUNT from priceplan where PLAN_CODE='"+Plan_code+"' or PLAN_DESC='"+Plan_code+"'";
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
						oPricePlan.PricePlanCreation(chargeUnit,Plan_code,Plan_code,"",Prov_System_Name,"","","","",locPlanType,"One Month"," "," ","",""," ","","Basic",""," "," ","","dfgf",locSaveOrCancelBtn,locpricingBtn,Eff_date,locPrice,locPricingSaveBtn);
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
						System.out.println("PR-2660 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Price plan definition is Passed");
					}
					else
					{
						System.out.println("PR-2660 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Price plan definition Is failed");
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



			@Test(description="Activity prerequisites")
			public void PR_2661() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2661",this);
					System.out.println("PR-2661 Started");
					Thread.sleep(2000);
					relogin();
					Actionids_List = new ArrayList(10);
					Actionids_List.add("156");
					Actionids_List.add("129");
					Actionids_List.add("17");
					Actionids_List.add("19");
					Actionids_List.add("30");
					
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
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"156","10","Pre Action","N",2);
					
					driver.findElement(btnsave).click();
					String Screen_Validation1=driver.findElement(ValidationMessage).getText();
					Thread.sleep(2000);
					System.out.println(Screen_Validation1);
					if(Screen_Validation1.length()!=0  && Screen_Validation.length()!=0 )
					{
						System.out.println("PR-2661 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Activity prerequisites is Passed");
					}
					else
					{
						System.out.println("PR-2661 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Activity prerequisites is Failed");
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



			@Test(description="Provision Service Definition")
			public void PR_3390() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3390",this);
					System.out.println("PR-3390 Started");

					Thread.sleep(2000);
					relogin();
					oNavigate.toProvisioningService();
					Thread.sleep(2000);
					Screen_Validation=oProvisioningService.ProvisioningServiceMapping(Prov_System_Name,"SERVICE",Prov_System_Name,"Save");
					System.out.println("validation message"+Screen_Validation);
					if(Screen_Validation.length()!=0)
					{
						System.out.println("PR-3390 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Provision Service Definition is Passed");
					}
					else
					{
						System.out.println("PR-3390 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Provision Service Definition is Failed");
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


			// End of Basic Configuration

			//   Start of  Bussiness Processes For Abv

			// Start of Add product case's



			@Test(description="Customer activation")
			public void PR_2662() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2662",this);
					System.out.println("PR-2662 Started");
					relogin();
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Plan_code,"Y",billing_Frequency);
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
						System.out.println("PR-2662 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Customer activation is Passed");
					}
					else
					{
						System.out.println("PR-2662 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Customer activation is Failed");
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



			@Test(description="Disconnection")
			public void PR_2663() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2663",this);
					System.out.println("PR-2663 Started");
					Thread.sleep(5000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Plan_code,"Y",billing_Frequency);
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
						System.out.println("PR-2663 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Disconnection is Passed");
					}
					else
					{
						System.out.println("PR-2663 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Disconnection Is Failed");
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



			@Test(description="Reconnection")
			public void PR_2664() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2664",this);
					System.out.println("PR-2664 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Plan_code,"Y",billing_Frequency);
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
						System.out.println("PR-2664 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Reconnection Is Passed");
					}
					else
					{
						System.out.println("PR-2664 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Reconnection is failed");
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



			@Test(description="Suspend")
			public void PR_2665() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2665",this);
					System.out.println("PR-2665 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Plan_code,"Y",billing_Frequency);
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
						System.out.println("PR-2665 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Suspend is Passed");
					}
					else
					{
						System.out.println("PR-2665 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Suspend Agreement is Failed");
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



			@Test(description="Reactivate")
			public void PR_2666() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2666",this);
					System.out.println("PR-2666 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Plan_code,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					Thread.sleep(10000);
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
						System.out.println("PR-2666 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Reactivate Agreement is passed");
					}
					else
					{
						System.out.println("PR-2666 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Reactivate Agreement is failed");
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



			@Test(description="Retracking")
			public void PR_2667() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2667",this);
					System.out.println("PR-2667 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Plan_code,"Y",billing_Frequency);
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
						System.out.println("PR-2667 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Retracking Is Passed");
					}
					else
					{
						System.out.println("PR-2667 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Retracking Is failed");
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



			@Test(description="Retrack with unique id")    
			public void PR_2668() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-2668",this);
					System.out.println("PR-2668 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Plan_code,"Y",billing_Frequency);
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
						System.out.println("PR-2668 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Retracking with uniqueid Is passed");
					}
					else
					{
						System.out.println("PR-2668 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Retracking with uniqueid Is Failed");
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



			@Test(description="Hardware change")
			public void PR_2669() throws Exception
			{

				try
				{
					testLinkConnection.setsTestCase("PR-2669",this);
					System.out.println("PR-2669 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Sale", "", "","",Plan_code,"Y",billing_Frequency);
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
						System.out.println("PR-2669 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Hardware change Is Passed");
					}
					else
					{
						System.out.println("PR-2669 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Hardware change Is Failed");
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



			@Test(description="Renewal")
			public void PR_2670() throws Exception
			{

				try
				{
					testLinkConnection.setsTestCase("PR-2670",this);
					System.out.println("PR-2670 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Plan_code,"Y",billing_Frequency);
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
						System.out.println("PR-2670 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Renewal Is Passed");
					}
					else
					{
						System.out.println("PR-2670 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Renewal Is failed");
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


		
}
