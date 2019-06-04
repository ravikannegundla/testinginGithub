package ProvisioningTC.ProvisioningAdaptersTC;

import java.util.ArrayList;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
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

public class Prov_NAGRAVISION_Tc extends MQProvisioning

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


	String Prov_System_Name="NAGRA";
	String Prov_System_Code="NAGRA";
	String Eff_date="24-04-2019";
	String Plan_code="NAGRA" ;

	protected Prov_NAGRAVISION_Tc(String sLogFileName) throws Exception 
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
	@Test(description="Define the provisioning system ")
	public void PR_3338() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-3338",this);
			System.out.println("PR-3338 Started");
			Thread.sleep(2000);
			relogin();
			oNavigate.toProvisioningSystem();
			Thread.sleep(2000);
			Screen_Validation=oProvisioningSystemCreation.ProvisioningSystem(Prov_System_Code,Prov_System_Name,"", "", "", "", "", "", "","GMT+05:30", "", "", "", "", "","Save") ;
			System.out.println("validation message"+Screen_Validation);
			if(Screen_Validation.length()!=0)
			{
				System.out.println("PR-3338 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-3338 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3338",driver);
			System.out.println("Test__PR-3338___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	@Test(description="Hardware For provisioning")
	public void PR_3340() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-3340",this);
			System.out.println("PR-3340 Started");
			Thread.sleep(2000);
			relogin();
			oNavigate.toHardwareForProvisioning();
			Thread.sleep(2000);
			Screen_Validation=oHardwareForProvisioning.HardwareMappingForProvisioningSystem(Prov_System_Name,Item1,Item2,"Save");
			System.out.println("validation message"+Screen_Validation);
			if(Screen_Validation.length()!=0)
			{
				System.out.println("PR-3340 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-3340 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3340",driver);
			System.out.println("Test__PR-3340___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description="Basic Service definition ")
	public void PR_3341() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-3341",this);
			System.out.println("PR-3341 Started");
			Thread.sleep(2000);
			relogin();
			oNavigate.toBasicServices();
			Thread.sleep(2000);
			Screen_Validation=oBasicServices.BasicSerives_Creation_prov(Prov_System_Name,Prov_System_Name,Service_Class,packageable,Requires_Activation,"","",Basic_Service_status,Prov_System_Name,"","Notes @1",btn);
			System.out.println("validation message"+Screen_Validation);
			if(Screen_Validation.length()!=0)
			{
				System.out.println("PR-3341 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Basic Service definition creation is done");
			}
			else
			{
				System.out.println("PR-3341 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Basic Service definition is not Created");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3341",driver);
			System.out.println("Test__PR-3341___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description="Service Group definition")
	public void PR_3412() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-3412",this);
			System.out.println("PR-3412 Started");
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
				System.out.println("PR-3412 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-3412 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3412",driver);
			System.out.println("Test__PR-3412___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description="Bundle definition")
	public void PR_3342() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-3342",this);
			System.out.println("PR-3342 Started");
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
				String Screen_Validation=oBundle.Bundle_Creation(Prov_System_Name,Prov_System_Name,"General",Service_Class,"Active",1,"Add","Service",Prov_System_Name,"","","","Save");
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

				System.out.println("PR-3342 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-3342 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3342",driver);
			System.out.println("Test__PR-3342___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description="Price plan definition")
	public void PR_3343() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-3343",this);
			System.out.println("PR-3343 Started");
			Thread.sleep(2000);
			relogin();

			String PricePlanCountValue = "select COUNT(BUNDLE_ID) AS COUNT from bundle where BUNDLE_NAME='"+Prov_System_Name+"' or BUNDLE_CODE='"+Prov_System_Name+"'";
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
				String Screen_Validation=driver.findElement(ScreenOutPut).getText();
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
				System.out.println("PR-3343 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-3343 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3343",driver);
			System.out.println("Test__PR-3343___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}

	
	
	

	@Test(description="Provision Service Definition")
	public void PR_3428() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-3428",this);
			System.out.println("PR-3428 Started");

			Thread.sleep(2000);
			relogin();
			oNavigate.toProvisioningService();
			Thread.sleep(2000);
			Screen_Validation=oProvisioningService.ProvisioningServiceMapping(Prov_System_Name,"SERVICE",Prov_System_Name,"Save");
			System.out.println("validation message is :-"+Screen_Validation);
			if(Screen_Validation.length()!=0)
			{
				System.out.println("PR-3428 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-3428 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3428",driver);
			System.out.println("Test__PR-3428___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	// 3345 and 3346 need to write ..

	@Test(description="Pre Requisite Mapping - Product Actions ")
	public void PR_3347() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-3347",this);
			System.out.println("PR-3347 Started");
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
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"1","10","Pre Action","N",2);
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"1","12","Pre Action","N",3);
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"1","21","Pre Action","N",4);
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"16","87","Post Action","N",5);

			driver.findElement(btnsave).click();
			String Screen_Validation1=driver.findElement(ValidationMessage).getText();
			Thread.sleep(2000);
			System.out.println(Screen_Validation1);
			if(Screen_Validation1.length()!=0  && Screen_Validation.length()!=0 )
			{
				System.out.println("PR-3347 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-3347 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3347",driver);
			System.out.println("Test__PR-3347___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description=" Pre Requisite Mapping - Product List Actions ")
	public void PR_3358() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-3358",this);
			System.out.println("PR-3358 Started");
			Thread.sleep(2000);
			relogin();

			Thread.sleep(2000);
			oNavigate.toActivityPrerequisites();
			Thread.sleep(2000);
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"156","152","Pre Action","N",1);
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"156","10","Pre Action","N",2);
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"156","12","Pre Action","N",3);
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"156","21","Pre Action","N",4);
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"129","87","Post Action","N",5);

			driver.findElement(btnsave).click();
			String Screen_Validation1=driver.findElement(ValidationMessage).getText();
			Thread.sleep(2000);
			System.out.println(Screen_Validation1);
			if(Screen_Validation1.length()!=0)
			{
				System.out.println("PR-3358 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-3358 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3358",driver);
			System.out.println("Test__PR-3358___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description=" Pre Requisite Mapping - Family Actions  ")
	public void PR_3371() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-3371",this);
			System.out.println("PR-3371 Started");
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
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"1","10","Pre Action","N",1);
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"1","13","Pre Action","N",2);
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"16","87","Post Action","N",3);

			driver.findElement(btnsave).click();
			String Screen_Validation1=driver.findElement(ValidationMessage).getText();
			Thread.sleep(2000);
			System.out.println(Screen_Validation1);
			if(Screen_Validation1.length()!=0  && Screen_Validation.length()!=0 )
			{
				System.out.println("PR-3371 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-3371 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3371",driver);
			System.out.println("Test__PR-3371___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}




	// End of Basic Configuration



	//   Start of  Bussiness Processes For Abv

	// Start of Add product case's



	@Test(description="Activation – New Agreement - Add Product ")
	public void PR_3348() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-3348",this);
			System.out.println("PR-3348 Started");
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
				System.out.println("PR-3348 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer activation is done  ");
			}
			else
			{
				System.out.println("PR-3348 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Customer activation is pending ");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3348",driver);
			System.out.println("Test__PR-3348___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	@Test(description="Disconnection Agreement - Remove Product ")
	public void PR_3349() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-3349",this);
			System.out.println("PR-3349 Started");
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
				System.out.println("PR-3349 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Disconnection is Done");
			}
			else
			{
				System.out.println("PR-3349 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Disconnection Is Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3349",driver);
			System.out.println("Test__PR-3349___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	@Test(description="Reconnection Agreement - Reconnect Add Product ")
	public void PR_3350() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-3350",this);
			System.out.println("PR-3350 Started");
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
				System.out.println("PR-3350 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Reconnection Is Done");
			}
			else
			{
				System.out.println("PR-3350 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Reconnection is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3350",driver);
			System.out.println("Test__PR-3350___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	@Test(description="Suspend Agreement - Suspend Product ")
	public void PR_3351() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-3351",this);
			System.out.println("PR-3351 Started");
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
				System.out.println("PR-3351 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Suspend Agreement with suspend all Is Done");
			}
			else
			{
				System.out.println("PR-3351 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Suspend Agreement with suspend all Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3351",driver);
			System.out.println("Test__PR-3351___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	@Test(description="Reactivate Agreement - Reactivate Product ")
	public void PR_3352() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-3352",this);
			System.out.println("PR-3352 Started");
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
				System.out.println("PR-3352 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Reactivate Agreement with Reactivate all Is Done");
			}
			else
			{
				System.out.println("PR-3352 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Reactivate Agreement with Reactivate all Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3352",driver);
			System.out.println("Test__PR-3352___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	@Test(description="Hardware Change - HW Change Add Product ")
	public void PR_3353() throws Exception
	{

		try
		{
			testLinkConnection.setsTestCase("PR-3353",this);
			System.out.println("PR-3353 Started");
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
				System.out.println("PR-3353 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Hardware change Is Done");
			}
			else
			{
				System.out.println("PR-3353 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Hardware change Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3353",driver);
			System.out.println("Test__PR-3353___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	@Test(description="Renewal - Renew Add Product ")
	public void PR_3354() throws Exception
	{

		try
		{
			testLinkConnection.setsTestCase("PR-3354",this);
			System.out.println("PR-3354 Started");
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
				System.out.println("PR-3354 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Renewal Is Done");
			}
			else
			{
				System.out.println("PR-3354 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Renewal Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3354",driver);
			System.out.println("Test__PR-3354___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	@Test(description=" Retrack - Retrack Product ")
	public void PR_3355() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-3355",this);
			System.out.println("PR-3355 Started");
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
				System.out.println("PR-3355 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Retracking Is Done");
			}
			else
			{
				System.out.println("PR-3355 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Retracking Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3355",driver);
			System.out.println("Test__PR-3355___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	@Test(description=" Retrack with Initialize - Retrack Product ")    
	public void PR_3356() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-3356",this);
			System.out.println("PR-3356 Started");
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
				System.out.println("PR-3356 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Retracking with uniqueid Is Done");
			}
			else
			{
				System.out.println("PR-3356 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Retracking with uniqueid Is In pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3356",driver);
			System.out.println("Test__PR-3356___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	// END OF ADD-PRODUCT..

	// Start of actvate all case's

	@Test(description="Provision Actions - Product List Action")
	public void PR_3357() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-3357",this);
			System.out.println("PR-3357 Started");
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
				System.out.println("PR-3357 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-3357 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3357",driver);
			System.out.println("Test__PR-3357___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	@Test(description="Activation – New Agreement - Activate Product List")
	public void PR_3359() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-3359",this);
			System.out.println("PR-3359 Started");
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
				System.out.println("PR-3359 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer activation is done  ");
			}
			else
			{
				System.out.println("PR-3359 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Customer activation is pending ");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3359",driver);
			System.out.println("Test__PR-3359___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	@Test(description="Disconnection Agreement - Disconnect Products ")
	public void PR_3360() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-3360",this);
			System.out.println("PR-3360 Started");
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
				System.out.println("PR-3360 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Disconnection is Done");
			}
			else
			{
				System.out.println("PR-3360 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Disconnection Is Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3360",driver);
			System.out.println("Test__PR-3360___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description="Reconnection Agreement - Activate Product List ")
	public void PR_3361() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-3361",this);
			System.out.println("PR-3361 Started");
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
				System.out.println("PR-3361 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Reconnection Is Done");
			}
			else
			{
				System.out.println("PR-3361 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Reconnection is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3361",driver);
			System.out.println("Test__PR-3361___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description="Hardware Change - Activate Product List & Disconnect Products")
	public void PR_3362() throws Exception
	{

		try
		{
			testLinkConnection.setsTestCase("PR-3362",this);
			System.out.println("PR-3362 Started");
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
				System.out.println("PR-3362 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Hardware change Is Done");
			}
			else
			{
				System.out.println("PR-3362 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Hardware change Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3362",driver);
			System.out.println("Test__PR-3362___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description="Suspend Agreement - Suspend Product List ")
	public void PR_3363() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-3363",this);
			System.out.println("PR-3363 Started");
			Thread.sleep(2000);
			CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
			ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
			System.out.println("Contract Number is : "+ContractNumber);

			oNavigate.toCollection();
			oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
			Thread.sleep(5000);
			oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
			Thread.sleep(85000);
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
				System.out.println("PR-3363 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Suspend Agreement with suspend all Is Done");
			}
			else
			{
				System.out.println("PR-3363 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Suspend Agreement with suspend all Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3363",driver);
			System.out.println("Test__PR-3363___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description="Reactivate Agreement - Reactivate Product List ")
	public void PR_3364() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-3364",this);
			System.out.println("PR-3364 Started");
			Thread.sleep(2000);
			CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
			ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
			System.out.println("Contract Number is : "+ContractNumber);

			oNavigate.toCollection();
			oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
			Thread.sleep(5000);
			oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
			Thread.sleep(85000);
			Thread.sleep(95000);

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
				System.out.println("PR-3364 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Reactivate Agreement with Reactivate all Is Done");
			}
			else
			{
				System.out.println("PR-3364 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Reactivate Agreement with Reactivate all Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3364",driver);
			System.out.println("Test__PR-3364___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description="Renewal - Activate Product List")
	public void PR_3365() throws Exception
	{

		try
		{
			testLinkConnection.setsTestCase("PR-3365",this);
			System.out.println("PR-3365 Started");
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
				System.out.println("PR-3365 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Renewal Is Done");
			}
			else
			{
				System.out.println("PR-3365 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Renewal Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3365",driver);
			System.out.println("Test__PR-3365___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description=" Retrack - Retrack Product ")
	public void PR_3366() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-3366",this);
			System.out.println("PR-3366 Started");
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
				System.out.println("PR-3366 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Retracking Is Done");
			}
			else
			{
				System.out.println("PR-3366 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Retracking Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3366",driver);
			System.out.println("Test__PR-3366___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description="Retrack with Initialize - Retrack Product List")    
	public void PR_3367() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-3367",this);
			System.out.println("PR-3367 Started");
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
				System.out.println("PR-3367 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Retracking with uniqueid Is Done");
			}
			else
			{
				System.out.println("PR-3367 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Retracking with uniqueid Is In pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3367",driver);
			System.out.println("Test__PR-3367___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	

	@Test(description="Reactivating Downgraded customer")
	public void PR_4472() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-4472",this);
			System.out.println("PR-4472 Started");
			Thread.sleep(2000);
			
			CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Prov_System_Name,"Y",billing_Frequency);
			ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
			System.out.println("Contract Number is : "+ContractNumber);

			oNavigate.toCollection();
			oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
			Thread.sleep(5000);
			oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
			Thread.sleep(85000);
			Thread.sleep(95000);

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
				System.out.println("PR-4472 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Reactivate Agreement with Reactivate all Is Done");
			}
			else
			{
				System.out.println("PR-4472 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Reactivate Agreement with Reactivate all Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-4472",driver);
			System.out.println("Test__PR-4472___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	
	
	
	
	
	

	// End of actvate all case's



	// End of  Bussiness Processes For ABV













}
