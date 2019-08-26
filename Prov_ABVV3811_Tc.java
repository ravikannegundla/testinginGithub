package ProvisioningTC.ProvisioningAdaptersTC;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.BasicConfigurationInventory.InventoryTransactions;
import com.BasicConfigurationsOfServiceOrder.BasicConfigurationsOfServiceOrder;
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
import com.PageObjects.OrderManagementMasters.PricingMaster.BasicServices;
import com.PageObjects.OrderManagementMasters.PricingMaster.Bundle;
import com.PageObjects.OrderManagementMasters.PricingMaster.PricePlan;
import com.PageObjects.OrderManagementMasters.PricingMaster.ServiceGroup;
import com.ProvisioningPageObject.ActivityPrerequisites;
import com.ProvisioningPageObject.BasicConfigurationsOfProvisioning;
import com.ProvisioningPageObject.HardwareForProvisioning;
import com.ProvisioningPageObject.ProvisioningRequests;
import com.ProvisioningPageObject.ProvisioningService;
import com.ProvisioningPageObject.ProvisioningSetUp;
import com.ProvisioningPageObject.ProvisioningSystemAction;
import com.ProvisioningPageObject.ProvisioningSystemCreation;
import com.ProvisioningPageObject.Retracking;
import com.ReceivablesPageObject.Collections;
import com.ReceivablesPageObject.CreditAllocation;

import TestFrameWork.MQProvisioning;
import Utility.Navigate;
import Utility.WaitTool;
import mq.seleniumFW.connection.MQDBConnection;
import testlink.api.java.client.TestLinkAPIResults;

public class Prov_ABVV3811_Tc extends MQProvisioning

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

		

		String Prov_System_Name="ABVV3811";
		String Prov_System_Code="ABVV3811";
		String Eff_date="23-04-2019";
		String Plan_code="ABVV3811" ;

		
		
		protected Prov_ABVV3811_Tc(String sLogFileName) throws Exception 
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
		public void PR_2734() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2734",this);
				System.out.println("PR-2734 Started");
				Thread.sleep(2000);
				relogin();
				Thread.sleep(2000);
				oNavigate.toProvisioningSystem();
				Thread.sleep(2000);
				Screen_Validation=oProvisioningSystemCreation.ProvisioningSystem(Prov_System_Name,Prov_System_Name,"", "", "", "", "", "", "","GMT+05:30", "", "", "", "", "","Save") ;
				System.out.println("validation message"+Screen_Validation);
				if(Screen_Validation.length()!=0)
				{
					System.out.println("PR-2734 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Define the provisioning system is Passed");
				}
				else
				{
					System.out.println("PR-2734 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Define the provisioning system is Failed");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2734",driver);
				System.out.println("Test__PR-2734___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}



		@Test(description="Hardware For provisioning")
		public void PR_2735() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2735",this);
				System.out.println("PR-2735 Started");
				Thread.sleep(2000);
				relogin();
				Thread.sleep(2000);
				oNavigate.toHardwareForProvisioning();
				Thread.sleep(2000);
				Screen_Validation=oHardwareForProvisioning.HardwareMappingForProvisioningSystem(Prov_System_Name,Item1,Item2,"Save");
				System.out.println("validation message"+Screen_Validation);
				if(Screen_Validation.length()!=0)
				{
					System.out.println("PR-2735 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Hardware For provisioning is Passed");
				}
				else
				{
					System.out.println("PR-2735 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Hardware For provisioning is Failed");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2735",driver);
				System.out.println("Test__PR-2735___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}

		

		@Test(description="Basic Service definition")
		public void PR_2736() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2736",this);
				System.out.println("PR-2736 Started");
				Thread.sleep(2000);
				relogin();
				Thread.sleep(2000);
				oNavigate.toBasicServices();
				Thread.sleep(2000);
				Screen_Validation=oBasicServices.BasicSerives_Creation_prov(Prov_System_Name,Prov_System_Name,Service_Class,packageable,Requires_Activation,"","",Basic_Service_status,Prov_System_Name,"","Notes @1",btn);
				System.out.println("validation message"+Screen_Validation);
				if(Screen_Validation.length()!=0)
				{
					System.out.println("PR-2736 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Basic Service definition is Passed");
				}
				else
				{
					System.out.println("PR-2736 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Basic Service definition is Failed");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2736",driver);
				System.out.println("Test__PR-2736___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}


		
		@Test(description="Service Group definition")
		public void PR_2737() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2737",this);
				System.out.println("PR-2737 Started");
				Thread.sleep(2000);
				relogin();
				Thread.sleep(2000);
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
					System.out.println("PR-2737 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Service Group definition is passed");
				}
				else
				{
					System.out.println("PR-2737 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Service Group definition is Failed");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2737",driver);
				System.out.println("Test__PR-2737___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}

		

		@Test(description="Bundle definition")
		public void PR_2738() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2738",this);
				System.out.println("PR-2738 Started");
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
					System.out.println("PR-2738 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Bundle definition is Passed");
				}
				else
				{
					System.out.println("PR-2738 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Bundle definition is Failed");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2738",driver);
				System.out.println("Test__PR-2738___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}

		

		@Test(description="Price plan definition")
		public void PR_2739() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2739",this);
				System.out.println("PR-2739 Started");
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
					oPricePlan.PricePlanCreation(chargeUnit,Plan_code,Plan_code,"",Prov_System_Name,"","","","",locPlanType,"One Month"," "," ","",""," ","","Basic",""," "," ","","",locSaveOrCancelBtn,locpricingBtn,Eff_date,locPrice,locPricingSaveBtn);
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
					System.out.println("PR-2739 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Price plan definition is Passed");
				}
				else
				{
					System.out.println("PR-2739 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Price plan definition is Failed");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2739",driver);
				System.out.println("Test__PR-2739___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}

		

		@Test(description="Activity Prerequisites")
		public void PR_2740() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2740",this);
				System.out.println("PR-2740 Started");
				Thread.sleep(2000);
				relogin();
				Thread.sleep(2000);
				Actionids_List = new ArrayList(10);
				Actionids_List.add("1");
				Actionids_List.add("16");
				Actionids_List.add("17");
				Actionids_List.add("19");
				Actionids_List.add("83");
				Actionids_List.add("85");
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
				oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"1","10","Pre Action","Y",2);
				oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"1","13","Pre Action","N",3);
				oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"16","87","Post Action","N",4);

				driver.findElement(btnsave).click();
				String Screen_Validation1=driver.findElement(ValidationMessage).getText();
				Thread.sleep(2000);
				System.out.println(Screen_Validation1);

				if(Screen_Validation1.length()!=0  && Screen_Validation.length()!=0 )
				{
					System.out.println("PR-2740 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Activity Prerequisites is Passed");
				}
				else
				{
					System.out.println("PR-2740 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Activity Prerequisites is Failed");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2740",driver);
				System.out.println("Test__PR-2740___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}


		
		@Test(description="Provision Service Definition")
		public void PR_3391() throws Exception
		{

			try
			{
				testLinkConnection.setsTestCase("PR-3391",this);
				System.out.println("PR-3391 Started");
				Thread.sleep(2000);
				relogin();
				Thread.sleep(2000);
				oNavigate.toProvisioningService();
				Thread.sleep(2000);
				Screen_Validation=oProvisioningService.ProvisioningServiceMapping(Prov_System_Name,"SERVICE",Prov_System_Name,"Save");
				System.out.println("validation message"+Screen_Validation);
				if(Screen_Validation.length()!=0)
				{
					System.out.println("PR-3391 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Provision Service Definition is Passed");
				}
				else
				{
					System.out.println("PR-3391 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Provision Service Definition is Failed");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-3391",driver);
				System.out.println("Test__PR-3391___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}

		// End of Basic Configuration





		//   Start of  Bussiness Processes For ABV_V3.8.1.1

		@Test(description="Customer activation")
		public void PR_2741() throws Exception
		{

			try
			{
				testLinkConnection.setsTestCase("PR-2741",this);
				System.out.println("PR-2741 Started");
				Thread.sleep(2000);
				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
				System.out.println("Contract Number is : "+ContractNumber);

				Thread.sleep(80000);
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
					System.out.println("PR-2741 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Customer activation is Passed");
				}
				else
				{
					System.out.println("PR-2741 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Customer activation is Failed");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2741",driver);
				System.out.println("Test__PR-2741___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}


		
		@Test(description="Disconnection")
		public void PR_2742() throws Exception
		{

			try
			{
				testLinkConnection.setsTestCase("PR-2742",this);
				System.out.println("PR-2742 Started");
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
					System.out.println("PR-2742 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Disconnection is passed");
				}
				else
				{
					System.out.println("PR-2742 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Disconnection Is failed");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2742",driver);
				System.out.println("Test__PR-2742___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}

		

		@Test(description="Reconnection")
		public void PR_2743() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2743",this);
				System.out.println("PR-2743 Started");
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
					System.out.println("PR-2743 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Reconnection Is Passed");
				}
				else
				{
					System.out.println("PR-2743 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Reconnection is Failed");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2743",driver);
				System.out.println("Test__PR-2743___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}

		

		@Test(description="Suspend")
		public void PR_2744() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2744",this);
				System.out.println("PR-2744 Started");
				Thread.sleep(2000);
				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
				System.out.println("Contract Number is : "+ContractNumber);
				Thread.sleep(2000);
				oNavigate.toCollection();
				oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
				Thread.sleep(5000);
				oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
				Thread.sleep(75000);
				Thread.sleep(50000);


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
					System.out.println("PR-2744 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Suspend Is Passed");
				}
				else
				{
					System.out.println("PR-2744 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Suspend Is Failed");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2744",driver);
				System.out.println("Test__PR-2744___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}

		

		@Test(description="Reactivate")
		public void PR_2745() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2745",this);
				System.out.println("PR-2745 Started");
				Thread.sleep(2000);
				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
				System.out.println("Contract Number is : "+ContractNumber);
				Thread.sleep(3000);
				oNavigate.toCollection();
				oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
				Thread.sleep(5000);
				oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
				Thread.sleep(95000);

				oNavigate.toCollection();
				oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
				Thread.sleep(5000);
				oCollections.collectionGrid("", "","Active", "", "Save");
				Thread.sleep(70000);
				Thread.sleep(50000);
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
					System.out.println("PR-2745 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Reactivate Is Passed");
				}
				else
				{
					System.out.println("PR-2745 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Reactivate Is Failed");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2745",driver);
				System.out.println("Test__PR-2745___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}

		

		@Test(description="Retracking")
		public void PR_2746() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2746",this);
				System.out.println("PR-2746 Started");
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
					System.out.println("PR-2746 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Retracking Is Passed");
				}
				else
				{
					System.out.println("PR-2746 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Retracking Is failed");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2746",driver);
				System.out.println("Test__PR-2746___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}

		

		@Test(description="Retracking with uniqueid")
		public void PR_2747() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2747",this);
				System.out.println("PR-2747 Started");
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
					System.out.println("PR-2747 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Retracking with uniqueid Is Done");
				}
				else
				{
					System.out.println("PR-2747 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Retracking with uniqueid Is In pending");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2747",driver);
				System.out.println("Test__PR-2747___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}

		

		@Test(description="Hardware change")
		public void PR_2748() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2748",this);
				System.out.println("PR-2748 Started");
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
					System.out.println("PR-2748 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Retracking with uniqueid is Passed");
				}
				else
				{
					System.out.println("PR-2748 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Retracking with uniqueid is failed");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2748",driver);
				System.out.println("Test__PR-2748___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}


		
		@Test(description="Renewal")
		public void PR_2749() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2749",this);
				System.out.println("PR-2749 Started");
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
					System.out.println("PR-2749 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Renewal is Passed");
				}
				else
				{
					System.out.println("PR-2749 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Renewal Is failed");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2749",driver);
				System.out.println("Test__PR-2749___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}


		//    End of Add-product


		@Test(description="Provisioning system action- PRODUCTLIST actions")
		public void PR_2750() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2750",this);
				System.out.println("PR-2750 Started");

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
				relogin();

				Thread.sleep(5000);
				oNavigate.toActivityPrerequisites();
				Thread.sleep(2000);
				oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"156","12","Pre Action","N",1);
				oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"156","10","Pre Action","Y",2);
				oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"156","13","Pre Action","N",3);
				oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"129","87","Post Action","N",4);

				driver.findElement(btnsave).click();
				String Screen_Validation1=driver.findElement(ValidationMessage).getText();
				Thread.sleep(2000);
				System.out.println(Screen_Validation1);

				if(Screen_Validation1.length()!=0  && Screen_Validation.length()!=0 )
				{
					System.out.println("PR-2740 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Provisioning system action- PRODUCTLIST actions Mapping Is Passed");
				}
				else
				{
					System.out.println("PR-2740 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Provisioning system action- PRODUCTLIST actions Mapping Is Failed");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2750",driver);
				System.out.println("Test__PR-2750___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}

		

		@Test(description="Customer activation with ACTIVATEPRODUCTLIST action")
		public void PR_2751() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2751",this);
				System.out.println("PR-2751 Started");
				Thread.sleep(2000);
				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
				System.out.println("Contract Number is : "+ContractNumber);

				Thread.sleep(80000);
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
					System.out.println("PR-2751 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Customer activation with ACTIVATEPRODUCTLIST action is Passed");
				}
				else
				{
					System.out.println("PR-2751 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Customer activation with ACTIVATEPRODUCTLIST action is Failed");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2751",driver);
				System.out.println("Test__PR-2751___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}

		
		
		@Test(description="Disconnection with DISCONNECTPRODUCTS action")
		public void PR_2752() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2752",this);
				System.out.println("PR-2752 Started");
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
					System.out.println("PR-2752 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Disconnection with DISCONNECTPRODUCTS action is Passed");
				}
				else
				{
					System.out.println("PR-2752 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Disconnection with DISCONNECTPRODUCTS action is Failed");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2752",driver);
				System.out.println("Test__PR-2752___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}

		

		@Test(description="Reconnection – ACTIVATEPRODUCTLIST")
		public void PR_2753() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2753",this);
				System.out.println("PR-2753 Started");
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
					System.out.println("PR-2753 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Reconnection – ACTIVATEPRODUCTLIST Is passed");
				}
				else
				{
					System.out.println("PR-2753 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Reconnection – ACTIVATEPRODUCTLIST Is failed");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2753",driver);
				System.out.println("Test__PR-2753___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}

		

		@Test(description="Suspend agreement with SUSPENDPRODUCTLIST action")
		public void PR_2754() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2754",this);
				System.out.println("PR-2754 Started");
				Thread.sleep(2000);
				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
				System.out.println("Contract Number is : "+ContractNumber);

				oNavigate.toCollection();
				oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
				Thread.sleep(5000);
				oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
				Thread.sleep(75000);

				Thread.sleep(50000);
				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
				this.records=oDBConnection.fecthRecords(Customer_id);
				this.record=this.records.get(0);
				String Party_id=record.get("CUSTOMER_ID");
				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=10 order by 1 desc";
				this.records=oDBConnection.fecthRecords(Status);
				this.record=this.records.get(0);
				String Qstatus=record.get("STATUS");
				System.out.println(Qstatus); 
				if(Qstatus.equalsIgnoreCase(C_Status))        	{
					System.out.println("PR-2754 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Suspend agreement with SUSPENDPRODUCTLIST action is Passed");
				}
				else
				{
					System.out.println("PR-2754 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Suspend agreement with SUSPENDPRODUCTLIST action is failed");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2754",driver);
				System.out.println("Test__PR-2754___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}

		

		@Test(description="Reactivate agreement with REACTIVATEPRODUCTLIST action")
		public void PR_2755() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2755",this);
				System.out.println("PR-2755 Started");
				Thread.sleep(2000);
				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
				System.out.println("Contract Number is : "+ContractNumber);

				oNavigate.toCollection();
				oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
				Thread.sleep(5000);
				oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
				Thread.sleep(75000);

				oNavigate.toCollection();
				oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
				Thread.sleep(5000);
				oCollections.collectionGrid("", "","Active", "", "Save");
				Thread.sleep(70000);

				Thread.sleep(50000);
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
					System.out.println("PR-2755 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Reactivate agreement with REACTIVATEPRODUCTLIST action is Passsed");
				}
				else
				{
					System.out.println("PR-2755 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Reactivate agreement with REACTIVATEPRODUCTLIST action is failed");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2755",driver);
				System.out.println("Test__PR-2755___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}


		
		@Test(description="Retracking with RETRACKPRODUCTLIST action")
		public void PR_2756() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2756",this);
				System.out.println("PR-2756 Started");
				Thread.sleep(2000);
				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
				System.out.println("Contract Number is : "+ContractNumber);
				Thread.sleep(75000);
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
					System.out.println("PR-2756 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Retracking with RETRACKPRODUCTLIST action is Passed");
				}
				else
				{
					System.out.println("PR-2756 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Retracking with RETRACKPRODUCTLIST action is Failed");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2756",driver);
				System.out.println("Test__PR-2756___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}

		

		@Test(description="Retrack with INITIALISE- RETRACKPRODUCTLIST action")
		public void PR_2757() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2757",this);
				System.out.println("PR-2757 Started");
				Thread.sleep(2000);
				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
				System.out.println("Contract Number is : "+ContractNumber);

				Thread.sleep(75000);
				oNavigate.toRetracking();
				oRetracking.RetrackingScreen(CustomerNumber, "UniqueId",1, "Y", "Save");
				Thread.sleep(75000);
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
					System.out.println("PR-2757 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Retrack with INITIALISE- RETRACKPRODUCTLIST action is Passed");
				}
				else
				{
					System.out.println("PR-2757 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Retrack with INITIALISE- RETRACKPRODUCTLIST action is Failed");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2757",driver);
				System.out.println("Test__PR-2757___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}


		
		@Test(description="Provisioning system action- list all actions")
		public void PR_2758() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2758",this);
				System.out.println("PR-2758 Started");
				Thread.sleep(2000);

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
				relogin();

				Thread.sleep(5000);
				oNavigate.toActivityPrerequisites();
				Thread.sleep(2000);
				oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"89","12","Pre Action","N",1);
				oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"89","10","Pre Action","Y",2);
				oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"89","13","Pre Action","N",3);
				oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"16","87","Post Action","N",4);

				driver.findElement(btnsave).click();
				String Screen_Validation1=driver.findElement(ValidationMessage).getText();
				Thread.sleep(2000);
				System.out.println(Screen_Validation1);

				if(Screen_Validation1.length()!=0  && Screen_Validation.length()!=0 )
				{
					System.out.println("PR-2758 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Provisioning system action- list all actions is passed");
				}
				else
				{
					System.out.println("PR-2758 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Provisioning system action- list all actions Is Failed");
				}

			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2758",driver);
				System.out.println("Test__PR-2758___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}

		

		@Test(description="Suspend Agreement with suspend all")
		public void PR_2759() throws Exception
		{

			try
			{
				testLinkConnection.setsTestCase("PR-2759",this);
				System.out.println("PR-2759 Started");
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
				Thread.sleep(70000);

				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
				this.records=oDBConnection.fecthRecords(Customer_id);
				this.record=this.records.get(0);
				String Party_id=record.get("CUSTOMER_ID");
				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=10 order by 1 desc";
				this.records=oDBConnection.fecthRecords(Status);
				this.record=this.records.get(0);
				String Qstatus=record.get("STATUS");
				System.out.println(Qstatus); 
				if(Qstatus.equalsIgnoreCase(C_Status))        	{
					System.out.println("PR-2759 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Suspend Agreement with suspend all Is Passed");
				}
				else
				{
					System.out.println("PR-2759 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Suspend Agreement with suspend all Is Failed");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2759",driver);
				System.out.println("Test__PR-2759___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}


		
		@Test(description="Reactivate Agreement with Reactivate all")
		public void PR_2760() throws Exception
		{

			try
			{
				testLinkConnection.setsTestCase("PR-2760",this);
				System.out.println("PR-2760 Started");
				Thread.sleep(2000);
				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
				System.out.println("Contract Number is : "+ContractNumber);

				oNavigate.toCollection();
				oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
				Thread.sleep(5000);
				oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
				Thread.sleep(75000);
				Thread.sleep(50000);

				oNavigate.toCollection();
				oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
				Thread.sleep(5000);
				oCollections.collectionGrid("", "","Active", "", "Save");
				Thread.sleep(70000);
				Thread.sleep(90000);

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
					System.out.println("PR-2760 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Reactivate Agreement with Reactivate all is Passed");
				}
				else
				{
					System.out.println("PR-2760 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Reactivate Agreement with Reactivate all is failed");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2760",driver);
				System.out.println("Test__PR-2760___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}


		
		@Test(description="Hardware Change with Hardware change single request action")
		public void PR_2761() throws Exception
		{

			try
			{
				testLinkConnection.setsTestCase("PR-2761",this);
				System.out.println("PR-2761 Started");
				Thread.sleep(2000);

				Actionids_List = new ArrayList(10);
				Actionids_List.add("1");
				Actionids_List.add("2");
				Actionids_List.add("30");
				System.out.println("array list is "+Actionids_List);

				oNavigate.toProvisioningSystemAction();
				Thread.sleep(2000);
				Screen_Validation=oProvisioningSystemAction.ProvisioningActionMapping(Prov_System_Name,Actionids_List,"Save");
				System.out.println("validation message"+Screen_Validation);

				Thread.sleep(2000);
				relogin();
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
				if(Qstatus.equalsIgnoreCase(C_Status))        	{
					System.out.println("PR-2761 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Hardware Change with Hardware change single request action Is Passed");
				}
				else
				{
					System.out.println("PR-2761 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Hardware Change with Hardware change single request action Is failed");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2761",driver);
				System.out.println("Test__PR-2761___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}



		// End of  Bussiness Processes For ABV_V3.8.1.1


}
