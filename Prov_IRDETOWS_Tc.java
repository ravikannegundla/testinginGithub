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

public class Prov_IRDETOWS_Tc extends MQProvisioning

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

	
	String Prov_System_Name="IRDETOWS";
	String Prov_System_Code="IRDETOWS";
	String Eff_date="20-05-2019";
	String Plan_code="IRDETOWS1" ;

	protected Prov_IRDETOWS_Tc(String sLogFileName) throws Exception 
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
	public void PR_416() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-416",this);
			System.out.println("PR-416 Started");
			Thread.sleep(2000);
			relogin();
			oNavigate.toProvisioningSystem();
			Thread.sleep(2000);
			Screen_Validation=oProvisioningSystemCreation.ProvisioningSystem(Prov_System_Code,Prov_System_Name,"", "", "", "", "", "", "","GMT+05:30", "", "", "", "", "","Save") ;
			System.out.println("validation message"+Screen_Validation);
			if(Screen_Validation.length()!=0)
			{
				System.out.println("PR-416 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-416 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-416",driver);
			System.out.println("Test__PR-416___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description="Hardware For provisioning")
	public void PR_418() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-418",this);
			System.out.println("PR-418 Started");
			Thread.sleep(2000);
			relogin();
			oNavigate.toHardwareForProvisioning();
			Thread.sleep(2000);
			Screen_Validation=oHardwareForProvisioning.HardwareMappingForProvisioningSystem(Prov_System_Name,Item1,Item2,"Save");
			System.out.println("validation message"+Screen_Validation);
			if(Screen_Validation.length()!=0)
			{
				System.out.println("PR-418 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-418 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-418",driver);
			System.out.println("Test__PR-418___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description="Basic Service definition ")
	public void PR_419() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-419",this);
			System.out.println("PR-419 Started");
			Thread.sleep(2000);
			relogin();
			oNavigate.toBasicServices();
			Thread.sleep(2000);
			Screen_Validation=oBasicServices.BasicSerives_Creation_prov(Prov_System_Name,Prov_System_Name,Service_Class,packageable,Requires_Activation,"","",Basic_Service_status,Prov_System_Name,"","Notes @1",btn);
			System.out.println("validation message"+Screen_Validation);
			if(Screen_Validation.length()!=0)
			{
				System.out.println("PR-419 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Basic Service definition creation is done");
			}
			else
			{
				System.out.println("PR-419 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Basic Service definition is not Created");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-419",driver);
			System.out.println("Test__PR-419___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description="Service Group definition")
	public void PR_3389() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-3389",this);
			System.out.println("PR-3389 Started");
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
				System.out.println("PR-3389 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-3389 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3389",driver);
			System.out.println("Test__PR-3389___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description="Bundle definition")
	public void PR_420() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-420",this);
			System.out.println("PR-420 Started");
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

				System.out.println("PR-420 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-420 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-420",driver);
			System.out.println("Test__PR-420___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description="Price plan definition")
	public void PR_421() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-421",this);
			System.out.println("PR-421 Started");
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
				System.out.println("PR-421 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-421 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-421",driver);
			System.out.println("Test__PR-421___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	



	@Test(description="Provision Service Definition")
	public void PR_3386() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-3386",this);
			System.out.println("PR-3386 Started");

			Thread.sleep(2000);
			relogin();
			oNavigate.toProvisioningService();
			Thread.sleep(2000);
			Screen_Validation=oProvisioningService.ProvisioningServiceMapping(Prov_System_Name,"SERVICE",Prov_System_Name,"Save");
			System.out.println("validation message is :-"+Screen_Validation);
			if(Screen_Validation.length()!=0)
			{
				System.out.println("PR-3386 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-3386 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3386",driver);
			System.out.println("Test__PR-3386___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}

	// End of Basic Configuration


	

	@Test(description="Provision Actions")
	public void PR_4466() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-4466",this);
			System.out.println("PR-4466 Started");
			Thread.sleep(2000);
			relogin();
			Actionids_List = new ArrayList(10);
			Actionids_List.add("1");
			Actionids_List.add("2");
			Actionids_List.add("83");
			Actionids_List.add("22");
			Actionids_List.add("24");
			System.out.println("array list is "+Actionids_List);

			oNavigate.toProvisioningSystemAction();
			Thread.sleep(2000);
			Screen_Validation=oProvisioningSystemAction.ProvisioningActionMapping(Prov_System_Name,Actionids_List,"Save");
			System.out.println("validation message"+Screen_Validation);

			Thread.sleep(2000);
			if(Screen_Validation.length()!=0 )
			{
				System.out.println("PR-4466 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-4466 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-4466",driver);
			System.out.println("Test__PR-4466___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}

	
	
	@Test(description="Pre Requisite Mapping - Product Action")
	public void PR_422() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-422",this);
			System.out.println("PR-422 Started");
			Thread.sleep(2000);
			relogin();
			
			Thread.sleep(2000);
			oNavigate.toActivityPrerequisites();
			Thread.sleep(2000);
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"1","12","Pre Action","N",1);
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"1","10","Pre Action","N",2);
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"83","12","Pre Action","N",3);
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"83","10","Pre Action","N",4);
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"16","87","Post Action","N",5);

			driver.findElement(btnsave).click();
			String Screen_Validation1=driver.findElement(ValidationMessage).getText();
			Thread.sleep(2000);
			System.out.println(Screen_Validation1);
			if(Screen_Validation1.length()!=0)
			{
				System.out.println("PR-422 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-422 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-422",driver);
			System.out.println("Test__PR-422___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}

	
	@Test(description="Pre Requisite Mapping - Product lIST Action")
	public void PR_433() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-433",this);
			System.out.println("PR-433 Started");
			Thread.sleep(2000);
			relogin();
			
			Thread.sleep(2000);
			oNavigate.toActivityPrerequisites();
			Thread.sleep(2000);
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"156","12","Pre Action","N",1);
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"156","10","Pre Action","N",2);
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"129","87","Post Action","N",3);

			driver.findElement(btnsave).click();
			String Screen_Validation1=driver.findElement(ValidationMessage).getText();
			Thread.sleep(2000);
			System.out.println(Screen_Validation1);
			if(Screen_Validation1.length()!=0)
			{
				System.out.println("PR-433 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-433 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-433",driver);
			System.out.println("Test__PR-433___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}

	
	@Test(description="Pre Requisite Mapping - All Actions")
	public void PR_442() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-442",this);
			System.out.println("PR-442 Started");
			Thread.sleep(2000);
			relogin();
			
			Thread.sleep(2000);
			oNavigate.toActivityPrerequisites();
			Thread.sleep(2000);
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"156","12","Pre Action","N",1);
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"156","10","Pre Action","N",2);
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"129","87","Post Action","N",3);

			driver.findElement(btnsave).click();
			String Screen_Validation1=driver.findElement(ValidationMessage).getText();
			Thread.sleep(2000);
			System.out.println(Screen_Validation1);
			if(Screen_Validation1.length()!=0)
			{
				System.out.println("PR-442 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-442 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-442",driver);
			System.out.println("Test__PR-442___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}

	

	
	@Test(description="AActivation – New Agreement - Add Product")
	public void PR_423() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-423",this);
			System.out.println("PR-423 Started");
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
				System.out.println("PR-423 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer activation is done  ");
			}
			else
			{
				System.out.println("PR-423 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Customer activation is pending ");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-423",driver);
			System.out.println("Test__PR-423___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	@Test(description="Disconnection Agreement - Remove Product ")
	public void PR_424() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-424",this);
			System.out.println("PR-424 Started");
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
			Thread.sleep(75000);
		
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
				System.out.println("PR-424 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Disconnection is Done");
			}
			else
			{
				System.out.println("PR-424 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Disconnection Is Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-424",driver);
			System.out.println("Test__PR-424___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	@Test(description="Reconnection Agreement - Reconnection Add Product ")
	public void PR_425() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-425",this);
			System.out.println("PR-425 Started");
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
				System.out.println("PR-425 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Reconnection Is Done");
			}
			else
			{
				System.out.println("PR-425 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Reconnection is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-425",driver);
			System.out.println("Test__PR-425___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}




	@Test(description="Suspend Agreement - Suspend Product ")
	public void PR_426() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-426",this);
			System.out.println("PR-426 Started");
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
				System.out.println("PR-426 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Suspend Agreement with suspend all Is Done");
			}
			else
			{
				System.out.println("PR-426 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Suspend Agreement with suspend all Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-426",driver);
			System.out.println("Test__PR-426___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	@Test(description="Reactivate Agreement - Reactivate Product")
	public void PR_427() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-427",this);
			System.out.println("PR-427 Started");
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
				System.out.println("PR-427 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Reactivate Agreement with Reactivate all Is Done");
			}
			else
			{
				System.out.println("PR-427 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Reactivate Agreement with Reactivate all Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-427",driver);
			System.out.println("Test__PR-427___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	
	@Test(description="Hardware Change - Hardware Change Add Product ")
	public void PR_428() throws Exception
	{

		try
		{
			testLinkConnection.setsTestCase("PR-428",this);
			System.out.println("PR-428 Started");
			Thread.sleep(20000);
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
				System.out.println("PR-428 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Hardware change Is Done");
			}
			else
			{
				System.out.println("PR-428 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Hardware change Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-428",driver);
			System.out.println("Test__PR-428___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	
	@Test(description="Renewal - Renewal Add Product ")
	public void PR_429() throws Exception
	{

		try
		{
			testLinkConnection.setsTestCase("PR-429",this);
			System.out.println("PR-429 Started");
			Thread.sleep(2000);
			CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Plan_code,"Y",billing_Frequency);
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
			Thread.sleep(90000);

			String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13  order by 1 desc ";
			this.records=oDBConnection.fecthRecords(Status);
			this.record=this.records.get(0);
			String Qstatus=record.get("STATUS");
			System.out.println(Qstatus);
			if(Qstatus.equalsIgnoreCase(C_Status))        	
			{
				System.out.println("PR-429 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Renewal Is Done");
			}
			else
			{
				System.out.println("PR-429 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Renewal Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-429",driver);
			System.out.println("Test__PR-429___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}

	

	@Test(description="Retrack - Retrack Product")    
	public void PR_430() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-430",this);
			System.out.println("PR-430 Started");
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
				System.out.println("PR-430 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Retracking with uniqueid Is Done");
			}
			else
			{
				System.out.println("PR-430 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Retracking with uniqueid Is In pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-430",driver);
			System.out.println("Test__PR-430___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}	

	
	

	@Test(description="Retracking with INITIALISE- Retrack Product")
	public void PR_431() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-431",this);
			System.out.println("PR-431 Started");
			Thread.sleep(2000);
			CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Plan_code,"Y",billing_Frequency);
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
				System.out.println("PR-431 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Retracking Is Done");
			}
			else
			{
				System.out.println("PR-431 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Retracking Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-431",driver);
			System.out.println("Test__PR-431___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}

	
	
	// End of add product  case's
	
	
	
	@Test(description="Provision Actions - Product List Actions ")
	public void PR_432() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-432",this);
			System.out.println("PR-432 Started");
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
				System.out.println("PR-432 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-432 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-432",driver);
			System.out.println("Test__PR-432___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}

	
	@Test(description="AActivation – New Agreement - Activate Product List ")
	public void PR_434() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-434",this);
			System.out.println("PR-434 Started");
			Thread.sleep(2000);
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
				System.out.println("PR-434 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer activation is done  ");
			}
			else
			{
				System.out.println("PR-434 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Customer activation is pending ");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-434",driver);
			System.out.println("Test__PR-434___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description="Disconnection Agreement - Disconnect Product List ")
	public void PR_435() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-435",this);
			System.out.println("PR-435 Started");
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
			Thread.sleep(90000);

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
				System.out.println("PR-435 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Disconnection is Done");
			}
			else
			{
				System.out.println("PR-435 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Disconnection Is Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-435",driver);
			System.out.println("Test__PR-435___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description="Reconnection Agreement - Activate Product List  ")
	public void PR_436() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-436",this);
			System.out.println("PR-436 Started");
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
				System.out.println("PR-436 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Reconnection Is Done");
			}
			else
			{
				System.out.println("PR-436 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Reconnection is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-436",driver);
			System.out.println("Test__PR-436___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description="Hardware Change - Activate Product List and disconnect Product List ")
	public void PR_437() throws Exception
	{

		try
		{
			testLinkConnection.setsTestCase("PR-437",this);
			System.out.println("PR-437 Started");
			Thread.sleep(20000);
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
				System.out.println("PR-437 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Hardware change Is Done");
			}
			else
			{
				System.out.println("PR-437 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Hardware change Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-437",driver);
			System.out.println("Test__PR-437___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	
	@Test(description="Renewal - Activate Product List  ")
	public void PR_438() throws Exception
	{

		try
		{
			testLinkConnection.setsTestCase("PR-438",this);
			System.out.println("PR-438 Started");
			Thread.sleep(2000);
			CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Plan_code,"Y",billing_Frequency);
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
				System.out.println("PR-438 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Renewal Is Done");
			}
			else
			{
				System.out.println("PR-438 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Renewal Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-438",driver);
			System.out.println("Test__PR-438___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}

	

	@Test(description="Retrack - Retrack Product List")    
	public void PR_439() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-439",this);
			System.out.println("PR-439 Started");
			Thread.sleep(2000);
			CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Plan_code,"Y",billing_Frequency);
			ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
			System.out.println("Contract Number is : "+ContractNumber);

			Thread.sleep(60000);
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
				System.out.println("PR-439 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Retracking with uniqueid Is Done");
			}
			else
			{
				System.out.println("PR-439 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Retracking with uniqueid Is In pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-439",driver);
			System.out.println("Test__PR-439___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}	

	
	
	@Test(description="Retracking with INITIALISE- Retrack Product List ")
	public void PR_440() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-440",this);
			System.out.println("PR-440 Started");
			Thread.sleep(2000);
			CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Plan_code,"Y",billing_Frequency);
			ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
			System.out.println("Contract Number is : "+ContractNumber);

			
			Thread.sleep(70000);
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
				System.out.println("PR-440 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Retracking Is Done");
			}
			else
			{
				System.out.println("PR-440 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Retracking Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-440",driver);
			System.out.println("Test__PR-440___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	

	@Test(description="Provision Actions - Product List Actions ")
	public void PR_441() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-441",this);
			System.out.println("PR-441 Started");
			Thread.sleep(2000);
			relogin();
			Actionids_List = new ArrayList(10);
			Actionids_List.add("156");
			Actionids_List.add("129");
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
				System.out.println("PR-441 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-441 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-441",driver);
			System.out.println("Test__PR-441___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}
	
	
	

	@Test(description="Suspend Agreement - Suspend All ")
	public void PR_444() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-444",this);
			System.out.println("PR-444 Started");
			Thread.sleep(2000);
			CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Plan_code,"Y",billing_Frequency);
			ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
			System.out.println("Contract Number is : "+ContractNumber);

			oNavigate.toCollection();
			oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
			Thread.sleep(5000);
			oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
			Thread.sleep(75000);
			Thread.sleep(85000);

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
				System.out.println("PR-444 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Suspend Agreement with suspend all Is Done");
			}
			else
			{
				System.out.println("PR-444 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Suspend Agreement with suspend all Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-444",driver);
			System.out.println("Test__PR-444___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	@Test(description="Reactivate Agreement - Reactivate All")
	public void PR_445() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-445",this);
			System.out.println("PR-445 Started");
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

			oNavigate.toCollection();
			oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
			Thread.sleep(5000);
			oCollections.collectionGrid("", "","Active", "", "Save");
			Thread.sleep(70000);
			Thread.sleep(85000);

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
				System.out.println("PR-445 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Reactivate Agreement with Reactivate all Is Done");
			}
			else
			{
				System.out.println("PR-445 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Reactivate Agreement with Reactivate all Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-445",driver);
			System.out.println("Test__PR-445___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}

	


	@Test(description="Retrack - Retrack All")    
	public void PR_446() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-446",this);
			System.out.println("PR-446 Started");
			Thread.sleep(2000);
			CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Plan_code,"Y",billing_Frequency);
			ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
			System.out.println("Contract Number is : "+ContractNumber);

			Thread.sleep(60000);
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
				System.out.println("PR-446 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Retracking with uniqueid Is Done");
			}
			else
			{
				System.out.println("PR-446 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Retracking with uniqueid Is In pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-446",driver);
			System.out.println("Test__PR-446___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}	


	

	
	@Test(description="Retracking with INITIALISE- Retrack All ")
	public void PR_447() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-447",this);
			System.out.println("PR-447 Started");
			Thread.sleep(2000);
			CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Plan_code,"Y",billing_Frequency);
			ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
			System.out.println("Contract Number is : "+ContractNumber);

			
			Thread.sleep(70000);
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
				System.out.println("PR-447 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Retracking Is Done");
			}
			else
			{
				System.out.println("PR-447 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Retracking Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-447",driver);
			System.out.println("Test__PR-447___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	
	
	
	
	
	
	
	
	
	


	// End of  Bussiness Processes For irdetows...




	
}
