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

public class Prov_NDS_Tc extends MQProvisioning

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

		
		String Prov_System_Name="NDS";
		
		
		protected Prov_NDS_Tc(String sLogFileName) throws Exception 
		{
			super(sLogFileName);
			this.oDBConnection=dbConnection;
			PageFactory.initElements(driver, this);
		}

		@Override
		protected void beforeClass() 
		{
			driver=getDriver();
			verifyLogin("POORNA","Magna@123");
			oWaitTool.implicitWait(20);
			driver.manage().window().maximize();
			this.oBasicConfigurationsOfProvisioning=new BasicConfigurationsOfProvisioning(driver,dbConnection);
			System.out.println("before class1");
			this.oDisconnection = new Disconnection(driver);
			this.oReconnection = new Reconnection(driver);
			this.oHardwareReplacement= new HardwareReplacement(driver,dbConnection);
			this.oCollections=new Collections(driver,dbConnection);
			this.oRenewal = new Renewal(driver);
			this.oRetracking=new Retracking(driver,dbConnection);
			oBasicConfigurationsOfProvisioning.OwnHardware_Privileges();
			System.out.println("before class2");
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
		@Test(description="Define the provisioning system ")
		public void PR_2257() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2257",this);
				System.out.println("PR-2257 Started");
				Thread.sleep(2000);

				oNavigate.toProvisioningSystem();
				Thread.sleep(2000);
				Screen_Validation=oProvisioningSystemCreation.ProvisioningSystem(Prov_System_Name,Prov_System_Name,"", "", "", "", "", "", "","GMT+05:30", "", "", "", "", "","Save") ;
				System.out.println("validation message"+Screen_Validation);
				if(Screen_Validation.length()!=0)
				{
					System.out.println("PR-2257 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
				}
				else
				{
					System.out.println("PR-2257 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Status is not active and success message is not displaying");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2257",driver);
				System.out.println("Test__PR-2257___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}


		
		@Test(description="Hardware For provisioning")
		public void PR_2258() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2258",this);
				System.out.println("PR-2258 Started");
				Thread.sleep(2000);

				oNavigate.toHardwareForProvisioning();
				Thread.sleep(2000);
				Screen_Validation=oHardwareForProvisioning.HardwareMappingForProvisioningSystem(Prov_System_Name,Item1,Item2,"Save");
				System.out.println("validation message"+Screen_Validation);
				if(Screen_Validation.length()!=0)
				{
					System.out.println("PR-2258 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
				}
				else
				{
					System.out.println("PR-2258 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Status is not active and success message is not displaying");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2258",driver);
				System.out.println("Test__PR-2258___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}


		
		@Test(description="Basic Service definition ")
		public void PR_2259() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2259",this);
				System.out.println("PR-2259 Started");
				Thread.sleep(2000);
				oNavigate.toBasicServices();
				Thread.sleep(2000);
				Screen_Validation=oBasicServices.BasicSerives_Creation_prov(Prov_System_Name,Prov_System_Name,Service_Class,packageable,Requires_Activation,"","",Basic_Service_status,Prov_System_Name,"","Notes @1",btn);
				System.out.println("validation message"+Screen_Validation);
				if(Screen_Validation.length()!=0)
				{
					System.out.println("PR-2259 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes(" Basic Service definition creation is done");
				}
				else
				{
					System.out.println("PR-2259 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Basic Service definition is not Created");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2259",driver);
				System.out.println("Test__PR-2259___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}


		
		@Test(description="Service Group definition")
		public void PR_2260() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2260",this);
				System.out.println("PR-2260 Started");
				Thread.sleep(2000);

				oNavigate.toServiceGroup();
				Thread.sleep(5000);
				oServiceGroup.ServiceGroup_Creation(1,Prov_System_Name,Prov_System_Name,Service_Class,"Add","Save");
				String Screen_Validation=driver.findElement(ValidationMessage).getText();
				Thread.sleep(2000);
				System.out.println(Screen_Validation);
				if(Screen_Validation.contains("Service Group has been added successfully"))
				{
					System.out.println("PR-2260 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
				}
				else
				{
					System.out.println("PR-2260 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Status is not active and success message is not displaying");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2260",driver);
				System.out.println("Test__PR-2260___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}


		@Test(description="Bundle definition")
		public void PR_2261() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2261",this);
				System.out.println("PR-2261 Started");
				Thread.sleep(2000);
				oNavigate.toBundle();
				Thread.sleep(3000);

				String msg=oBundle.Bundle_Creation(Prov_System_Name,Prov_System_Name,"General",Service_Class,"Active",1,"Add","Service",Prov_System_Name,"","","","Save");
				if(msg.equalsIgnoreCase("Bundle has been added successfully"))
				{
					System.out.println("PR-2261 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
				}
				else
				{
					System.out.println("PR-2261 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Status is not active and success message is not displaying");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2261",driver);
				System.out.println("Test__PR-2261___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}


		
		@Test(description="Price plan definition")
		public void PR_2262() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2262",this);
				System.out.println("PR-2262 Started");
				Thread.sleep(2000);
				oNavigate.toPricePlan();
				Thread.sleep(1000);

				oPricePlan.PricePlanCreation(chargeUnit,Prov_System_Name,Prov_System_Name,"",Prov_System_Name,"","","","",locPlanType,"One Month"," "," ","",""," ","","Basic",""," "," ","","",locSaveOrCancelBtn,locpricingBtn,"18-04-2019",locPrice,locPricingSaveBtn);
				String msg=driver.findElement(ScreenOutPut).getText();
				Thread.sleep(5000);
				System.out.println("msg is"+msg);
				if(msg.contains("Pricing details have been added successfully"))
				{
					System.out.println("PR-2262 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
				}
				else
				{
					System.out.println("PR-2262 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Status is not active and success message is not displaying");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2262",driver);
				System.out.println("Test__PR-2262___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}


		
		@Test(description="Prerequisites- PRODUCT actions ")
		public void PR_2263() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2263",this);
				System.out.println("PR-2263 Started");
				Thread.sleep(2000);

				Actionids_List = new ArrayList(10);
				Actionids_List.add("1");
				Actionids_List.add("2");
				Actionids_List.add("17");
				Actionids_List.add("19");
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
				oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"1","12","Pre Action","N",1);
				oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"1","10","Pre Action","N",2);
				oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"1","13","Pre Action","N",3);
				oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"1","21","Pre Action","N",4);
				oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"16","87","Post Action","N",5);

				driver.findElement(btnsave).click();
				String Screen_Validation1=driver.findElement(ValidationMessage).getText();
				Thread.sleep(2000);
				System.out.println(Screen_Validation1);
				if(Screen_Validation1.length()!=0  && Screen_Validation.length()!=0 )
				{
					System.out.println("PR-2263 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
				}
				else
				{
					System.out.println("PR-2263 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Status is not active and success message is not displaying");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2263",driver);
				System.out.println("Test__PR-2263___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}


		
		@Test(description="Prerequisites- ALL Actions ")
		public void PR_2267() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2267",this);
				System.out.println("PR-2267 Started");
				Thread.sleep(2000);
				oNavigate.toProvisioningService();
				Thread.sleep(2000);
				Screen_Validation=oProvisioningService.ProvisioningServiceMapping(Prov_System_Name,"SERVICE",Prov_System_Name,"Save");
				System.out.println("validation message"+Screen_Validation);
				if(Screen_Validation.length()!=0)
				{
					System.out.println("PR-2267 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
				}
				else
				{
					System.out.println("PR-2267 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Status is not active and success message is not displaying");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2267",driver);
				System.out.println("Test__PR-2267___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}


		
		@Test(description="Provision Service Definition")
		public void PR_3421() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-3421",this);
				System.out.println("PR-3421 Started");
				Thread.sleep(2000);
				oNavigate.toProvisioningService();
				Thread.sleep(2000);
				Screen_Validation=oProvisioningService.ProvisioningServiceMapping(Prov_System_Name,"SERVICE",Prov_System_Name,"Save");
				System.out.println("validation message"+Screen_Validation);
				if(Screen_Validation.length()!=0)
				{
					System.out.println("PR-3421 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
				}
				else
				{
					System.out.println("PR-3421 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Status is not active and success message is not displaying");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-3421",driver);
				System.out.println("Test__PR-3421___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}


		// End of Basic Configuration



		//   Start of  Bussiness Processes For Nds

		
		
		@Test(description="Customer activation")
		public void PR_2269() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2269",this);
				System.out.println("PR-2269 Started");
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
					System.out.println("PR-2269 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes(" Customer activation is done  ");
				}
				else
				{
					System.out.println("PR-2269 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Customer activation is pending ");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2269",driver);
				System.out.println("Test__PR-2269___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}


		
		@Test(description="Disconnection ")
		public void PR_2270() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2270",this);
				System.out.println("PR-2270 Started");
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
					System.out.println("PR-2270 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes(" Disconnection is Done");
				}
				else
				{
					System.out.println("PR-2270 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Disconnection Is Pending");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2270",driver);
				System.out.println("Test__PR-2270___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}



		@Test(description="Reconnection")
		public void PR_2271() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2271",this);
				System.out.println("PR-2271 Started");
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
					System.out.println("PR-2271 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Reconnection Is Done");
				}
				else
				{
					System.out.println("PR-2271 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Reconnection is In Pending");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2271",driver);
				System.out.println("Test__PR-2271___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}



		@Test(description="Hardware change")
		public void PR_2272() throws Exception
		{

			try
			{
				testLinkConnection.setsTestCase("PR-2272",this);
				System.out.println("PR-2272 Started");
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
					System.out.println("PR-2272 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Hardware change Is Done");
				}
				else
				{
					System.out.println("PR-2272 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Hardware change Is In Pending");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2272",driver);
				System.out.println("Test__PR-2272___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}


		//    End of Add-product


		// all action's start...

		
		@Test(description="Provisioning system action- list all actions")
		public void PR_2273() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2273",this);
				System.out.println("PR-2273 Started");
				Thread.sleep(2000);

				Actionids_List = new ArrayList(10);
				Actionids_List.add("1");
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
				relogin();
				Thread.sleep(2000);
				
				oNavigate.toActivityPrerequisites();
				Thread.sleep(2000);
				oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"1","12","Pre Action","N",1);
				oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"1","10","Pre Action","N",2);
				oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"1","13","Pre Action","N",3);
				oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"1","21","Pre Action","N",4);
				oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"16","87","Post Action","N",5);

				driver.findElement(btnsave).click();
				String Screen_Validation1=driver.findElement(ValidationMessage).getText();
				Thread.sleep(2000);
				System.out.println(Screen_Validation1);

				if(Screen_Validation1.length()!=0  && Screen_Validation.length()!=0 )
				{
					System.out.println("PR-2273 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Provisioning system action- list all actions Mapping Is Done");
				}
				else
				{
					System.out.println("PR-2273 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Provisioning system action- list all actions Mapping Is Failed");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2273",driver);
				System.out.println("Test__PR-2273___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}


		
		@Test(description="Suspend Agreement with suspend all")
		public void PR_2274() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2274",this);
				System.out.println("PR-2274 Started");
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
					System.out.println("PR-2274 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Suspend Agreement with suspend all Is Done");
				}
				else
				{
					System.out.println("PR-2274 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Suspend Agreement with suspend all Is In Pending");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2274",driver);
				System.out.println("Test__PR-2274___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}


		
		@Test(description="Reactivate Agreement with Reactivate all")
		public void PR_2275() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2275",this);
				System.out.println("PR-2275 Started");
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
					System.out.println("PR-2275 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Reactivate Agreement with Reactivate all Is Done");
				}
				else
				{
					System.out.println("PR-2275 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Reactivate Agreement with Reactivate all Is In Pending");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2275",driver);
				System.out.println("Test__PR-`___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}


		
		@Test(description="Retrack with INITIALISE ")    
		public void PR_2276() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2276",this);
				System.out.println("PR-2276 Started");
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
					System.out.println("PR-2276 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Retracking with uniqueid Is Done");
				}
				else
				{
					System.out.println("PR-2276 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Retracking with uniqueid Is In pending");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2276",driver);
				System.out.println("Test__PR-2276___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}


		
		@Test(description="Retracking")
		public void PR_2277() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-2277",this);
				System.out.println("PR-2277 Started");
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
					System.out.println("PR-2277 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Retracking Is Done");
				}
				else
				{
					System.out.println("PR-2277 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Retracking Is In Pending");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-2277",driver);
				System.out.println("Test__PR-2277___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}


		
		// need to map the service downgrdae service to nds system
		@Test(description="Reactivating Downgraded customer")
		public void PR_4469() throws Exception
		{
			try
			{
				testLinkConnection.setsTestCase("PR-4469",this);
				System.out.println("PR-4469 Started");
				Thread.sleep(2000);
				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
				System.out.println("Contract Number is : "+ContractNumber);

				oNavigate.toCollection();
				oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
				Thread.sleep(5000);
				oCollections.collectionGrid("", "","Service Downgrade", "", "Save");
				Thread.sleep(75000);
				Thread.sleep(70000);
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
					System.out.println("PR-4469 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("Reactivating Downgraded customer Is Done");
				}
				else
				{
					System.out.println("PR-4469 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Reactivating Downgraded customer Is In Pending");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-4469",driver);
				System.out.println("Test__PR-4469___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}



		
		@Test(description="verify received_date in provsion_request table")
		public void PR_4477() throws Exception
		{

			try
			{
				testLinkConnection.setsTestCase("PR-4477",this);
				System.out.println("PR-4477 Started");
				Thread.sleep(2000);
				CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
				ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
				System.out.println("Contract Number is : "+ContractNumber);

				Thread.sleep(40000);
				String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=1 order by 1 desc";
				this.records=oDBConnection.fecthRecords(Status);
				this.record=this.records.get(0);
				String Qstatus=record.get("STATUS");
				System.out.println(Qstatus);
				
				String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
				this.records=oDBConnection.fecthRecords(Customer_id);
				this.record=this.records.get(0);
				String Party_id=record.get("CUSTOMER_ID");
				getRequestCount="select COUNT(REQUEST_ID) from PROVSION_REQUEST where RECVD_RESPONSE is not null and PARTY_ID='"+Party_id+"' and BIZ_PROCESS_ID=1";
				this.records=oDBConnection.fecthRecords(getRequestCount);
				this.record=this.records.get(0);
				Request_Count=record.get("COUNT(REQUEST_ID)");
				RequestCount = Integer.parseInt(Request_Count);
				System.out.println("ReconnectionCount is "+RequestCount);
				if(RequestCount>0)
				{
					System.out.println("PR-4477 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes("verify received_date in provsion_request table Is Done");
				}
				else
				{
					System.out.println("PR-4477 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("verify received_date in provsion_request table is in pending ");
				}
			}
			catch(Exception e)
			{
				captureScreenShot.takeScreenShot("PR-4477",driver);
				System.out.println("Test__PR-4477___%Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
			}
		}



		// End of  Bussiness Processes For Nds

		

}
