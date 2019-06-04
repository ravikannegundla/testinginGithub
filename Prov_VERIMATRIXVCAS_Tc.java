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

public class Prov_VERIMATRIXVCAS_Tc extends MQProvisioning

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

	String Prov_System_Name="VERIMATRIXVCAS";
	String Prov_System_Code="VERIMATRIXVCAS";
	String Eff_date="13-05-2019";
	String Plan_code="VERIMATRIXVCAS";

	protected Prov_VERIMATRIXVCAS_Tc(String sLogFileName) throws Exception 
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
//		verifyLogin("POORNIMAM","POORNIMAM");
		verifyLogin("KRAVI","KRAVI1234");
		oWaitTool.implicitWait(10);
	}



	// Basic Configuration
	@Test(description="Define the provisioning system ")
	public void PR_2347() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2347",this);
			System.out.println("PR-2347 Started");
			Thread.sleep(2000);
			relogin();
			oNavigate.toProvisioningSystem();
			Thread.sleep(2000);
			Screen_Validation=oProvisioningSystemCreation.ProvisioningSystem(Prov_System_Code,Prov_System_Name,"", "", "", "", "", "", "","GMT+05:30", "", "", "", "", "","Save") ;
			System.out.println("validation message"+Screen_Validation);
			if(Screen_Validation.length()!=0)
			{
				System.out.println("PR-2347 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-2347 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2347",driver);
			System.out.println("Test__PR-2347___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description="Hardware For provisioning")
	public void PR_2348() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2348",this);
			System.out.println("PR-2348 Started");
			Thread.sleep(2000);
			relogin();
			oNavigate.toHardwareForProvisioning();
			Thread.sleep(2000);
			Screen_Validation=oHardwareForProvisioning.HardwareMappingForProvisioningSystem(Prov_System_Name,Item1,Item2,"Save");
			System.out.println("validation message"+Screen_Validation);
			if(Screen_Validation.length()!=0)
			{
				System.out.println("PR-2348 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-2348 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2348",driver);
			System.out.println("Test__PR-2348___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description="Basic Service definition ")
	public void PR_2349() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2349",this);
			System.out.println("PR-2349 Started");
			Thread.sleep(2000);
			relogin();
			oNavigate.toBasicServices();
			Thread.sleep(2000);
			Screen_Validation=oBasicServices.BasicSerives_Creation_prov(Prov_System_Name,Prov_System_Name,Service_Class,packageable,Requires_Activation,"","",Basic_Service_status,Prov_System_Name,"","Notes @1",btn);
			System.out.println("validation message"+Screen_Validation);
			if(Screen_Validation.length()!=0)
			{
				System.out.println("PR-2349 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Basic Service definition creation is done");
			}
			else
			{
				System.out.println("PR-2349 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Basic Service definition is not Created");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2349",driver);
			System.out.println("Test__PR-2349___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description="Service Group definition")
	public void PR_2350() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2350",this);
			System.out.println("PR-2350 Started");
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
				String Screen_Validation=driver.findElement(ValidationMessage).getText();
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
				System.out.println("PR-2350 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-2350 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2350",driver);
			System.out.println("Test__PR-2350___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description="Bundle definition")
	public void PR_2351() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2351",this);
			System.out.println("PR-2351 Started");
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

				System.out.println("PR-2351 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-2351 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2351",driver);
			System.out.println("Test__PR-2351___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description="Price plan definition")
	public void PR_2352() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2352",this);
			System.out.println("PR-2352 Started");
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
				System.out.println("PR-2352 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-2352 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2352",driver);
			System.out.println("Test__PR-2352___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description="Activity Prerequisites")
	public void PR_2353() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2353",this);
			System.out.println("PR-2353 Started");
			Thread.sleep(2000);
			relogin();
			Actionids_List = new ArrayList(10);
			Actionids_List.add("1");
			Actionids_List.add("2");
			Actionids_List.add("22");
			Actionids_List.add("24");
//			Actionids_List.add("83");
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
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"2","9","Post Action","N",3);
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"2","87","Post Action","N",4);

			driver.findElement(btnsave).click();
			String Screen_Validation1=driver.findElement(ValidationMessage).getText();
			Thread.sleep(2000);
			System.out.println(Screen_Validation1);
			if(Screen_Validation1.length()!=0  && Screen_Validation.length()!=0 )
			{
				System.out.println("PR-2353 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-2353 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2353",driver);
			System.out.println("Test__PR-2353___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}

// pending 3392 case

	@Test(description="Provision Service Definition")
	public void PR_3392() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-3392",this);
			System.out.println("PR-3392 Started");

			Thread.sleep(2000);
			relogin();
			oNavigate.toProvisioningService();
			Thread.sleep(2000);
			Screen_Validation=oProvisioningService.ProvisioningServiceMapping(Prov_System_Name,"SERVICE",Prov_System_Name,"Save");
			System.out.println("validation message is :-"+Screen_Validation);
			if(Screen_Validation.length()!=0)
			{
				System.out.println("PR-3392 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-3392 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3392",driver);
			System.out.println("Test__PR-3392___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}

	// End of Basic Configuration



	//   Start of  Bussiness Processes For verimatrixVas

	// Start of Add product case's



	@Test(description="Customer activation")
	public void PR_2354() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2354",this);
			System.out.println("PR-2354 Started");
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
				System.out.println("PR-2354 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer activation is done  ");
			}
			else
			{
				System.out.println("PR-2354 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Customer activation is pending ");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2354",driver);
			System.out.println("Test__PR-2354___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	@Test(description="Disconnection")
	public void PR_2355() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2355",this);
			System.out.println("PR-2355 Started");
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
				System.out.println("PR-2355 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Disconnection is Done");
			}
			else
			{
				System.out.println("PR-2355 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Disconnection Is Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2355",driver);
			System.out.println("Test__PR-2355___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	@Test(description="Reconnection")
	public void PR_2356() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2356",this);
			System.out.println("PR-2356 Started");
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
				System.out.println("PR-2356 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Reconnection Is Done");
			}
			else
			{
				System.out.println("PR-2356 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Reconnection is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2356",driver);
			System.out.println("Test__PR-2356___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}

	

	@Test(description="Suspend Agreement")
	public void PR_2357() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2357",this);
			System.out.println("PR-2357 Started");
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
				System.out.println("PR-2357 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Suspend Agreement with suspend all Is Done");
			}
			else
			{
				System.out.println("PR-2357 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Suspend Agreement with suspend all Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2357",driver);
			System.out.println("Test__PR-2357___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	@Test(description="Reactivate Agreement")
	public void PR_2358() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2358",this);
			System.out.println("PR-2358 Started");
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
				System.out.println("PR-2358 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Reactivate Agreement with Reactivate all Is Done");
			}
			else
			{
				System.out.println("PR-2358 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Reactivate Agreement with Reactivate all Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2358",driver);
			System.out.println("Test__PR-2358___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}

	
	

	@Test(description="Hardware Change")
	public void PR_2359() throws Exception
	{

		try
		{
			testLinkConnection.setsTestCase("PR-2359",this);
			System.out.println("PR-2359 Started");
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
				System.out.println("PR-2359 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Hardware change Is Done");
			}
			else
			{
				System.out.println("PR-2359 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Hardware change Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2359",driver);
			System.out.println("Test__PR-2359___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	
	

	@Test(description="Retracking")
	public void PR_2360() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2360",this);
			System.out.println("PR-2360 Started");
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
				System.out.println("PR-2360 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Retracking Is Done");
			}
			else
			{
				System.out.println("PR-2360 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Retracking Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2360",driver);
			System.out.println("Test__PR-2360___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	
	@Test(description="Retrack with INITIALISE")    
	public void PR_2361() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2361",this);
			System.out.println("PR-2361 Started");
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
				System.out.println("PR-2361 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Retracking with uniqueid Is Done");
			}
			else
			{
				System.out.println("PR-2361 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Retracking with uniqueid Is In pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2361",driver);
			System.out.println("Test__PR-2361___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}






	// END OF ADD-PRODUCT..

	// Start of  PRODUCTLIST actions case's

	@Test(description="Provisioning system action- PRODUCTLIST actions")
	public void PR_2362() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2362",this);
			System.out.println("PR-2362 Started");
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
			relogin();
			Thread.sleep(2000);
			oNavigate.toActivityPrerequisites();
			Thread.sleep(2000);
			
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"156","12","Pre Action","N",1);
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"156","10","Pre Action","N",2);
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"129","9","Post Action","N",3);
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"129","87","Post Action","N",4);

			driver.findElement(btnsave).click();
			String Screen_Validation1=driver.findElement(ValidationMessage).getText();
			Thread.sleep(2000);
			System.out.println(Screen_Validation1);
			if(Screen_Validation1.length()!=0 && Screen_Validation.length()!=0 )
			{
				System.out.println("PR-2362 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-2362 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2362",driver);
			System.out.println("Test__PR-2362___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	@Test(description="Customer activation with ACTIVATEPRODUCTLIST action ")
	public void PR_2363() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2363",this);
			System.out.println("PR-2363 Started");
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
				System.out.println("PR-2363 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer activation is done  ");
			}
			else
			{
				System.out.println("PR-2363 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Customer activation is pending ");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2363",driver);
			System.out.println("Test__PR-2363___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	@Test(description="Disconnection with DISCONNECTPRODUCTS action")
	public void PR_2364() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2364",this);
			System.out.println("PR-2364 Started");
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
				System.out.println("PR-2364 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Disconnection is Done");
			}
			else
			{
				System.out.println("PR-2364 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Disconnection Is Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2364",driver);
			System.out.println("Test__PR-2364___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	
	@Test(description="Reconnection- ACTIVATEPRODUCTLIST")
	public void PR_2365() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2365",this);
			System.out.println("PR-2365 Started");
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
				System.out.println("PR-2365 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Reconnection Is Done");
			}
			else
			{
				System.out.println("PR-2365 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Reconnection is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2365",driver);
			System.out.println("Test__PR-2365___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	@Test(description="Suspend agreement with SUSPENDPRODUCTLIST action ")
	public void PR_2366() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2366",this);
			System.out.println("PR-2366 Started");
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
				System.out.println("PR-2366 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Suspend Agreement with suspend all Is Done");
			}
			else
			{
				System.out.println("PR-2366 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Suspend Agreement with suspend all Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2366",driver);
			System.out.println("Test__PR-2366___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	@Test(description="Reactivate agreement with REACTIVATEPRODUCTLIST action")
	public void PR_2367() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2367",this);
			System.out.println("PR-2367 Started");
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
				System.out.println("PR-2367 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Reactivate Agreement with Reactivate all Is Done");
			}
			else
			{
				System.out.println("PR-2367 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Reactivate Agreement with Reactivate all Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2367",driver);
			System.out.println("Test__PR-2367___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	@Test(description="Retracking with RETRACKPRODUCTLIST action")
	public void PR_2368() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2368",this);
			System.out.println("PR-2368 Started");
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
				System.out.println("PR-2368 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Retracking Is Done");
			}
			else
			{
				System.out.println("PR-2368 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Retracking Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2368",driver);
			System.out.println("Test__PR-2368___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	@Test(description="Retrack with INITIALISE- RETRACKPRODUCTLIST action")    
	public void PR_2369() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2369",this);
			System.out.println("PR-2369 Started");
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
				System.out.println("PR-2369 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Retracking with uniqueid Is Done");
			}
			else
			{
				System.out.println("PR-2369 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Retracking with uniqueid Is In pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2369",driver);
			System.out.println("Test__PR-2369___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}

	
		

	


	
	
	

	// End of actvate Product list case's



	// End of  Bussiness Processes For ABV







	
	

	
}
