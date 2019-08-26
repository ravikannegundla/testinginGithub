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

public class Prov_VERIMATRIXWS35_Tc extends MQProvisioning

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

			String Prov_System_Name="VERIMATWS35";
			String Prov_System_Code="VERIMATWS35";
			String Eff_date="10-05-2019";
			String Plan_code="VERIMATWS35" ;

			protected Prov_VERIMATRIXWS35_Tc(String sLogFileName) throws Exception 
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
				//		verifyLogin("POORNIMAM","POORNIMAM");--KRAVI
				verifyLogin("KRAVI","KRAVI1234");
				oWaitTool.implicitWait(10);
			}



			// Basic Configuration
			@Test(description="Define the provisioning system")
			public void PR_1570() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-1570",this);
					System.out.println("PR-1570 Started");
					Thread.sleep(2000);
					relogin();
					oNavigate.toProvisioningSystem();
					Thread.sleep(2000);
					Screen_Validation=oProvisioningSystemCreation.ProvisioningSystem(Prov_System_Code,Prov_System_Name,"", "", "", "", "", "", "","GMT+05:30", "", "", "", "", "","Save") ;
					System.out.println("validation message"+Screen_Validation);
					if(Screen_Validation.length()!=0)
					{
						System.out.println("PR-1570 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Define the provisioning system is Passed");
					}
					else
					{
						System.out.println("PR-1570 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Define the provisioning system is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-1570",driver);
					System.out.println("Test__PR-1570___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			

			@Test(description="Hardware For provisioning")
			public void PR_1572() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-1572",this);
					System.out.println("PR-1572 Started");
					Thread.sleep(2000);
					relogin();
					oNavigate.toHardwareForProvisioning();
					Thread.sleep(2000);
					Screen_Validation=oHardwareForProvisioning.HardwareMappingForProvisioningSystem(Prov_System_Name,Item1,Item2,"Save");
					System.out.println("validation message"+Screen_Validation);
					if(Screen_Validation.length()!=0)
					{
						System.out.println("PR-1572 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Hardware For provisioning is Passed");
					}
					else
					{
						System.out.println("PR-1572 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Hardware For provisioning is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-1572",driver);
					System.out.println("Test__PR-1572___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			
			@Test(description="Basic Service definition")
			public void PR_1573() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-1573",this);
					System.out.println("PR-1573 Started");
					Thread.sleep(2000);
					relogin();
					oNavigate.toBasicServices();
					Thread.sleep(2000);
					Screen_Validation=oBasicServices.BasicSerives_Creation_prov(Prov_System_Name,Prov_System_Name,Service_Class,packageable,Requires_Activation,"","",Basic_Service_status,Prov_System_Name,"","Notes @1",btn);
					System.out.println("validation message"+Screen_Validation);
					if(Screen_Validation.length()!=0)
					{
						System.out.println("PR-1573 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Basic Service definition is Passed");
					}
					else
					{
						System.out.println("PR-1573 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Basic Service definition is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-1573",driver);
					System.out.println("Test__PR-1573___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			

			@Test(description="Service Group definition")
			public void PR_3396() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3396",this);
					System.out.println("PR-3396 Started");
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
						System.out.println("PR-3396 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Service Group definition is Passed");
					}
					else
					{
						System.out.println("PR-3396 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Service Group definition is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3396",driver);
					System.out.println("Test__PR-3396___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			
			@Test(description="Bundle definition")
			public void PR_1574() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-1574",this);
					System.out.println("PR-1574 Started");
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

						System.out.println("PR-1574 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Bundle definition is Passed");
					}
					else
					{
						System.out.println("PR-1574 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Bundle definition is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-1574",driver);
					System.out.println("Test__PR-1574___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			

			@Test(description="Price plan definition")
			public void PR_1575() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-1575",this);
					System.out.println("PR-1575 Started");
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
					}
					if(Screen_Validation.length()!=0)
					{
						System.out.println("PR-1575 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Price plan definition is Passed");
					}
					else
					{
						System.out.println("PR-1575 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Price plan definition is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-1575",driver);
					System.out.println("Test__PR-1575___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Provision Service Definition")
			public void PR_3429() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-3429",this);
					System.out.println("PR-3429 Started");
					Thread.sleep(2000);
					relogin();
					oNavigate.toProvisioningService();
					Thread.sleep(2000);
					Screen_Validation=oProvisioningService.ProvisioningServiceMapping(Prov_System_Name,"SERVICE",Prov_System_Name,"Save");
					System.out.println("validation message is :-"+Screen_Validation);
					if(Screen_Validation.length()!=0)
					{
						System.out.println("PR-3429 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Provision Service Definition is Passed");
					}
					else
					{
						System.out.println("PR-3429 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Provision Service Definition is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-3429",driver);
					System.out.println("Test__PR-3429___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}

			// End of Basic Configuration



			//   Start of  Bussiness Processes For verimatrixws3.5

			// Start of  product  list case's



			@Test(description="Provision Actions - Product List Actions")
			public void PR_1576() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-1576",this);
					System.out.println("PR-1576 Started");
					Thread.sleep(2000);
					relogin();
					Actionids_List = new ArrayList(10);
					Actionids_List.add("156");
					Actionids_List.add("16");
					Actionids_List.add("17");
					Actionids_List.add("19");
					Actionids_List.add("18");

					System.out.println("array list is "+Actionids_List);

					oNavigate.toProvisioningSystemAction();
					Thread.sleep(2000);
					Screen_Validation=oProvisioningSystemAction.ProvisioningActionMapping(Prov_System_Name,Actionids_List,"Save");
					System.out.println("validation message"+Screen_Validation);

					Thread.sleep(2000);
					if(Screen_Validation.length()!=0 )
					{
						System.out.println("PR-1576 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Provision Actions - Product List Actions is Passed");
					}
					else
					{
						System.out.println("PR-1576 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Provision Actions - Product List Actions is failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-1576",driver);
					System.out.println("Test__PR-1576___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Pre Requisite Mapping - Product List Actions")
			public void PR_1577() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-1577",this);
					System.out.println("PR-1577 Started");
					Thread.sleep(2000);
					relogin();
					Thread.sleep(2000);
					oNavigate.toActivityPrerequisites();
					Thread.sleep(2000);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"156","12","Pre Action","N",1);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"16","87","Post Action","N",2);


					driver.findElement(btnsave).click();
					String Screen_Validation1=driver.findElement(ValidationMessage).getText();
					Thread.sleep(2000);
					System.out.println(Screen_Validation1);
					if(Screen_Validation1.length()!=0)
					{
						System.out.println("PR-1577 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Pre Requisite Mapping - Product List Actions is Passed");
					}
					else
					{
						System.out.println("PR-1577 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Pre Requisite Mapping - Product List Actions is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-1577",driver);
					System.out.println("Test__PR-1577___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Activation – New Agreement")
			public void PR_1578() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-1578",this);
					System.out.println("PR-1578 Started");
					Thread.sleep(2000);
					relogin();
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
						System.out.println("PR-1578 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Activation – New Agreement is Passed");
					}
					else
					{
						System.out.println("PR-1578 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Activation – New Agreement is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-1578",driver);
					System.out.println("Test__PR-1578___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Disconnection Agreement")
			public void PR_1579() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-1579",this);
					System.out.println("PR-1579 Started");
					Thread.sleep(5000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					Thread.sleep(30000);
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
						System.out.println("PR-1579 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Disconnection Agreement is Passed");
					}
					else
					{
						System.out.println("PR-1579 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Disconnection Agreement is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-1579",driver);
					System.out.println("Test__PR-1579___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Reconnection Agreement")
			public void PR_1580() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-1580",this);
					System.out.println("PR-1580 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					Thread.sleep(30000);
					oNavigate.toDisconnection();
					Thread.sleep(5000);
					logger.info("Successfully Navigated to Disconnection Screen ");
					sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
					String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
					System.out.println("Success message is:"+success_Msg);
					System.out.println("Disconnection is done");

					Thread.sleep(5000);
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
						System.out.println("PR-1580 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Reconnection Agreement is Passed");
					}
					else
					{
						System.out.println("PR-1580 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Reconnection Agreement is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-1580",driver);
					System.out.println("Test__PR-1580___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Hardware Change")
			public void PR_1581() throws Exception
			{

				try
				{
					testLinkConnection.setsTestCase("PR-1581",this);
					System.out.println("PR-1581 Started");
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
						System.out.println("PR-1581 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Hardware change Is Passed");
					}
					else
					{
						System.out.println("PR-1581 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Hardware change Is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-1581",driver);
					System.out.println("Test__PR-1581___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			@Test(description="Action Mapping - All Actions")
			public void PR_1582() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-1582",this);
					System.out.println("PR-1582 Started");
					Thread.sleep(2000);
					relogin();
					Actionids_List = new ArrayList(10);
					Actionids_List.add("156");
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
						System.out.println("PR-1582 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Action Mapping - All Actions is Passed");
					}
					else
					{
						System.out.println("PR-1582 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Action Mapping - All Actions is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-1582",driver);
					System.out.println("Test__PR-1582___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Pre Requisite Mapping - All Actions")
			public void PR_1583() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-1583",this);
					System.out.println("PR-1583 Started");
					Thread.sleep(2000);
					relogin();
					Thread.sleep(2000);
					oNavigate.toActivityPrerequisites();
					Thread.sleep(2000);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"156","12","Pre Action","N",1);
					oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"16","87","Post Action","N",2);

					driver.findElement(btnsave).click();
					String Screen_Validation1=driver.findElement(ValidationMessage).getText();
					Thread.sleep(2000);
					System.out.println(Screen_Validation1);
					if(Screen_Validation1.length()!=0)
					{
						System.out.println("PR-1583 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Pre Requisite Mapping - All Actions is Passed");
					}
					else
					{
						System.out.println("PR-1583 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Pre Requisite Mapping - All Actions is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-1583",driver);
					System.out.println("Test__PR-1583___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Suspend Agreement")
			public void PR_1584() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-1584",this);
					System.out.println("PR-1584 Started");
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
						System.out.println("PR-1584 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Suspend Agreement is passed");
					}
					else
					{
						System.out.println("PR-1584 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Suspend Agreement is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-1584",driver);
					System.out.println("Test__PR-1584___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Reactivate Agreement")
			public void PR_1585() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-1585",this);
					System.out.println("PR-1585 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);
					Thread.sleep(3000);
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
						System.out.println("PR-1585 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Reactivate Agreement is Passed");
					}
					else
					{
						System.out.println("PR-1585 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Reactivate Agreement is failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-1585",driver);
					System.out.println("Test__PR-1585___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Retracking")
			public void PR_1586() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-1586",this);
					System.out.println("PR-1586 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					Thread.sleep(80000);
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
						System.out.println("PR-1586 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Retracking Is Passed");
					}
					else
					{
						System.out.println("PR-1586 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Retracking Is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-1586",driver);
					System.out.println("Test__PR-1586___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}



			@Test(description="Retrack with INITIALISE")    
			public void PR_1587() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-1587",this);
					System.out.println("PR-1587 Started");
					Thread.sleep(2000);
					CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
					ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
					System.out.println("Contract Number is : "+ContractNumber);

					Thread.sleep(80000);
					oNavigate.toRetracking();
					oRetracking.RetrackingScreen(CustomerNumber, "UniqueId",1, "Y", "Save");
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
						System.out.println("PR-1587 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Retrack with INITIALISE is Passed");
					}
					else
					{
						System.out.println("PR-1587 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Retrack with INITIALISE is Failed");
					}
				}
				catch(Exception e)
				{
					captureScreenShot.takeScreenShot("PR-1587",driver);
					System.out.println("Test__PR-1587___%Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
				}
			}


			// END OF PRODUCTLIST


			// End of  Bussiness Processes For Verimatrix ws 3.5


}
