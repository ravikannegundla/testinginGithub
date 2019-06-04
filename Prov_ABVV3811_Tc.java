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

		@Test(description="Define the provisioning system ")
		public void PR_2734() throws Exception
		{

			try
			{
				testLinkConnection.setsTestCase("PR-2734",this);
				System.out.println("PR-2734 Started");
				Thread.sleep(2000);

				oNavigate.toProvisioningSystem();
				Thread.sleep(2000);
				Screen_Validation=oProvisioningSystemCreation.ProvisioningSystem(Prov_System_Name,Prov_System_Name,"", "", "", "", "", "", "","GMT+05:30", "", "", "", "", "","Save") ;
				System.out.println("validation message"+Screen_Validation);
				if(Screen_Validation.length()!=0)
				{
					System.out.println("PR-2734 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
				}
				else
				{
					System.out.println("PR-2734 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Status is not active and success message is not displaying");
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

				oNavigate.toHardwareForProvisioning();
				Thread.sleep(2000);
				Screen_Validation=oHardwareForProvisioning.HardwareMappingForProvisioningSystem(Prov_System_Name,Item1,Item2,"Save");
				System.out.println("validation message"+Screen_Validation);
				if(Screen_Validation.length()!=0)
				{
					System.out.println("PR-2735 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
				}
				else
				{
					System.out.println("PR-2735 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Status is not active and success message is not displaying");
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


		@Test(description="Basic Service definition ")
		public void PR_2736() throws Exception
		{

			try
			{
				testLinkConnection.setsTestCase("PR-2736",this);
				System.out.println("PR-2736 Started");
				Thread.sleep(2000);
				oNavigate.toBasicServices();
				Thread.sleep(2000);
				Screen_Validation=oBasicServices.BasicSerives_Creation_prov(Prov_System_Name,Prov_System_Name,Service_Class,packageable,Requires_Activation,"","",Basic_Service_status,Prov_System_Name,"","Notes @1",btn);
				System.out.println("validation message"+Screen_Validation);
				if(Screen_Validation.length()!=0)
				{
					System.out.println("PR-2736 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes(" Basic Service definition creation is done");
				}
				else
				{
					System.out.println("PR-2736 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Basic Service definition is not Created");
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

				oNavigate.toServiceGroup();
				Thread.sleep(5000);
				oServiceGroup.ServiceGroup_Creation(1,Prov_System_Name,Prov_System_Name,Service_Class,"Add","Save");

				String Screen_Validation=driver.findElement(ValidationMessage).getText();
				Thread.sleep(2000);
				System.out.println(Screen_Validation);

				if(Screen_Validation.contains("Service Group has been added successfully"))
				{
					System.out.println("PR-2737 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
				}
				else
				{
					System.out.println("PR-2737 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Status is not active and success message is not displaying");
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
				oNavigate.toBundle();
				Thread.sleep(3000);

				String msg=oBundle.Bundle_Creation(Prov_System_Name,Prov_System_Name,"General",Service_Class,"Active",1,"Add","Service",Prov_System_Name,"","","","Save");
				if(msg.equalsIgnoreCase("Bundle has been added successfully"))
				{
					System.out.println("PR-2738 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
				}
				else
				{
					System.out.println("PR-2738 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Status is not active and success message is not displaying");
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
				oNavigate.toPricePlan();
				Thread.sleep(1000);

				oPricePlan.PricePlanCreation(chargeUnit,Prov_System_Name,Prov_System_Name,"",Prov_System_Name,"","","","",locPlanType,"One Month"," "," ","",""," ","","Basic",""," "," ","","",locSaveOrCancelBtn,locpricingBtn,"17-04-2019",locPrice,locPricingSaveBtn);
				String msg=driver.findElement(ScreenOutPut).getText();
				Thread.sleep(5000);
				System.out.println("msg is"+msg);
				if(msg.contains("Pricing details have been added successfully"))
				{
					System.out.println("PR-2739 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
				}
				else
				{
					System.out.println("PR-2739 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Status is not active and success message is not displaying");
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


		@Test(description="Activity Prerequisites ")
		public void PR_2740() throws Exception
		{

			try
			{
				testLinkConnection.setsTestCase("PR-2740",this);
				System.out.println("PR-2740 Started");
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
					testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
				}
				else
				{
					System.out.println("PR-2740 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Status is not active and success message is not displaying");
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
				oNavigate.toProvisioningService();
				Thread.sleep(2000);
				Screen_Validation=oProvisioningService.ProvisioningServiceMapping(Prov_System_Name,"SERVICE",Prov_System_Name,"Save");
				System.out.println("validation message"+Screen_Validation);
				if(Screen_Validation.length()!=0)
				{
					System.out.println("PR-3391 Pass");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
					testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
				}
				else
				{
					System.out.println("PR-3391 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Status is not active and success message is not displaying");
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
					testLinkConnection.setsNotes(" Customer activation is done  ");
				}
				else
				{
					System.out.println("PR-2741 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Customer activation is pending ");
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


		@Test(description="Disconnection ")
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
					testLinkConnection.setsNotes(" Disconnection is Done");
				}
				else
				{
					System.out.println("PR-2742 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Disconnection Is Pending");
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
					testLinkConnection.setsNotes("Reconnection Is Done");
				}
				else
				{
					System.out.println("PR-2743 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Reconnection is In Pending");
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


		@Test(description="Suspend ")
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
					testLinkConnection.setsNotes("Suspend Is Done");
				}
				else
				{
					System.out.println("PR-2744 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Suspend Is in Pending");
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
					testLinkConnection.setsNotes("Reactivate Is Done");
				}
				else
				{
					System.out.println("PR-2745 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Reactivate Is In Pending");
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
					testLinkConnection.setsNotes("Retracking Is Done");
				}
				else
				{
					System.out.println("PR-2746 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Retracking Is In Pending");
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
					testLinkConnection.setsNotes("Hardware change Is Done");
				}
				else
				{
					System.out.println("PR-2748 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Hardware change Is In Pending");
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
					testLinkConnection.setsNotes("Renewal Is Done");
				}
				else
				{
					System.out.println("PR-2749 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Renewal Is In Pending");
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
					testLinkConnection.setsNotes("Provisioning system action- PRODUCTLIST actions Mapping Is Done");
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
					testLinkConnection.setsNotes("Customer activation with ACTIVATEPRODUCTLIST action Is Done");
				}
				else
				{
					System.out.println("PR-2751 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Customer activation with ACTIVATEPRODUCTLIST action Is In Pending");
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
					testLinkConnection.setsNotes("Disconnection with DISCONNECTPRODUCTS action Is Done");
				}
				else
				{
					System.out.println("PR-2752 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Disconnection with DISCONNECTPRODUCTS action Is In Pending");
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
					testLinkConnection.setsNotes("Reconnection – ACTIVATEPRODUCTLIST Is Done");
				}
				else
				{
					System.out.println("PR-2753 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Reconnection – ACTIVATEPRODUCTLIST Is In Pending");
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
					testLinkConnection.setsNotes("Suspend agreement with SUSPENDPRODUCTLIST action Is Done");
				}
				else
				{
					System.out.println("PR-2754 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Suspend agreement with SUSPENDPRODUCTLIST action Is In Pending");
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
					testLinkConnection.setsNotes("Reactivate agreement with REACTIVATEPRODUCTLIST action Is Done");
				}
				else
				{
					System.out.println("PR-2755 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Reactivate agreement with REACTIVATEPRODUCTLIST action Is In Pending");
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
					testLinkConnection.setsNotes("Retracking with RETRACKPRODUCTLIST action Is Done");
				}
				else
				{
					System.out.println("PR-2756 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Retracking with RETRACKPRODUCTLIST action Is In Pending");
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
					testLinkConnection.setsNotes("Retrack with INITIALISE- RETRACKPRODUCTLIST action Is Done");
				}
				else
				{
					System.out.println("PR-2757 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Retrack with INITIALISE- RETRACKPRODUCTLIST action Is In Pending");
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
					testLinkConnection.setsNotes("Provisioning system action- list all actions Mapping Is Done");
				}
				else
				{
					System.out.println("PR-2758 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Provisioning system action- list all actions Mapping Is Failed");
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
					testLinkConnection.setsNotes("Suspend Agreement with suspend all Is Done");
				}
				else
				{
					System.out.println("PR-2759 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Suspend Agreement with suspend all Is In Pending");
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
					testLinkConnection.setsNotes("Reactivate Agreement with Reactivate all Is Done");
				}
				else
				{
					System.out.println("PR-2760 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Reactivate Agreement with Reactivate all Is In Pending");
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
					testLinkConnection.setsNotes("Hardware Change with Hardware change single request action Is Done");
				}
				else
				{
					System.out.println("PR-2761 Failed");
					testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
					testLinkConnection.setsNotes("Hardware Change with Hardware change single request action Is In Pending");
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
