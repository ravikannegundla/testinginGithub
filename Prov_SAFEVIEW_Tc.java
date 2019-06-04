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

public class Prov_SAFEVIEW_Tc extends MQProvisioning

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

	String Prov_System_Name="SAFEVIEW";
	String Prov_System_Code="SAFEVIEW";
	String Eff_date="16-05-2019";
	String Plan_code="SAFEVIEW1" ;

	protected Prov_SAFEVIEW_Tc(String sLogFileName) throws Exception 
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
	public void PR_2917() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2917",this);
			System.out.println("PR-2917 Started");
			Thread.sleep(2000);
			relogin();
			oNavigate.toProvisioningSystem();
			Thread.sleep(2000);
			Screen_Validation=oProvisioningSystemCreation.ProvisioningSystem(Prov_System_Code,Prov_System_Name,"", "", "", "", "", "", "","GMT+05:30", "", "", "", "", "","Save") ;
			System.out.println("validation message"+Screen_Validation);
			if(Screen_Validation.length()!=0)
			{
				System.out.println("PR-2917 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-2917 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2917",driver);
			System.out.println("Test__PR-2917___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}

	
	@Test(description="Hardware For provisioning")
	public void PR_2919() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2919",this);
			System.out.println("PR-2919 Started");
			Thread.sleep(2000);
			relogin();
			oNavigate.toHardwareForProvisioning();
			Thread.sleep(2000);
			Screen_Validation=oHardwareForProvisioning.HardwareMappingForProvisioningSystem(Prov_System_Name,Item1,Item2,"Save");
			System.out.println("validation message"+Screen_Validation);
			if(Screen_Validation.length()!=0)
			{
				System.out.println("PR-2919 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-2919 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2919",driver);
			System.out.println("Test__PR-2919___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description="Basic Service definition ")
	public void PR_2920() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2920",this);
			System.out.println("PR-2920 Started");
			Thread.sleep(2000);
			relogin();
			oNavigate.toBasicServices();
			Thread.sleep(2000);
			Screen_Validation=oBasicServices.BasicSerives_Creation_prov(Prov_System_Name,Prov_System_Name,Service_Class,packageable,Requires_Activation,"","",Basic_Service_status,Prov_System_Name,"","Notes @1",btn);
			System.out.println("validation message"+Screen_Validation);
			if(Screen_Validation.length()!=0)
			{
				System.out.println("PR-2920 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Basic Service definition creation is done");
			}
			else
			{
				System.out.println("PR-2920 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Basic Service definition is not Created");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2920",driver);
			System.out.println("Test__PR-2920___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description="Service Group definition")
	public void PR_3407() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-3407",this);
			System.out.println("PR-3407 Started");
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
				System.out.println("PR-3407 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-3407 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3407",driver);
			System.out.println("Test__PR-3407___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description="Bundle definition")
	public void PR_2921() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2921",this);
			System.out.println("PR-2921 Started");
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

				System.out.println("PR-2921 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-2921 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2921",driver);
			System.out.println("Test__PR-2921___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description="Price plan definition")
	public void PR_2922() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2922",this);
			System.out.println("PR-2922 Started");
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
				System.out.println("PR-2922 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-2922 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2922",driver);
			System.out.println("Test__PR-2922___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}


	@Test(description="Provision Service Definition")
	public void PR_3438() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-3438",this);
			System.out.println("PR-3438 Started");

			Thread.sleep(2000);
			relogin();
			oNavigate.toProvisioningService();
			Thread.sleep(2000);
			Screen_Validation=oProvisioningService.ProvisioningServiceMapping(Prov_System_Name,"SERVICE",Prov_System_Name,"Save");
			System.out.println("validation message is :-"+Screen_Validation);
			if(Screen_Validation.length()!=0)
			{
				System.out.println("PR-3438 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-3438 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-3438",driver);
			System.out.println("Test__PR-3438___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}

	
	@Test(description=" Action's and Pre Requisite Mapping - Product Actions")
	public void PR_2923() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2923",this);
			System.out.println("PR-2923 Started");
			Thread.sleep(2000);
			relogin();
			Actionids_List = new ArrayList(10);
			Actionids_List.add("1");
			Actionids_List.add("2");
			Actionids_List.add("22");
			Actionids_List.add("24");
			Actionids_List.add("83");
//			Actionids_List.add("85");
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
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"83","10","Pre Action","N",3);
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"83","12","Pre Action","N",4);
		

			driver.findElement(btnsave).click();
			String Screen_Validation1=driver.findElement(ValidationMessage).getText();
			Thread.sleep(2000);
			System.out.println(Screen_Validation1);
			if(Screen_Validation1.length()!=0  && Screen_Validation.length()!=0 )
			{
				System.out.println("PR-2923 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-2923 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2923",driver);
			System.out.println("Test__PR-2923___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}

	
	@Test(description="Pre Requisite Mapping - All Actions")
	public void PR_2934() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2934",this);
			System.out.println("PR-2934 Started");
			Thread.sleep(2000);
			
			relogin();
			Thread.sleep(2000);
			oNavigate.toActivityPrerequisites();
			Thread.sleep(2000);
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"1","12","Pre Action","N",1);
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"1","10","Pre Action","N",2);
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"83","10","Pre Action","N",3);
			oActivityPrerequisites.ProvActivityPrerequisitesMapping(Prov_System_Name,Service_Class,"83","12","Pre Action","N",4);
		
			driver.findElement(btnsave).click();
			String Screen_Validation1=driver.findElement(ValidationMessage).getText();
			Thread.sleep(2000);
			System.out.println(Screen_Validation1);
			if(Screen_Validation1.length()!=0 )
			{
				System.out.println("PR-2934 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-2934 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2934",driver);
			System.out.println("Test__PR-2934___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}

	
	
	
	// End of Basic Configuration



	//   Start of  Bussiness Processes For verimatrixVas

	// Start of Add product case's



	@Test(description="Customer activation")
	public void PR_2924() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2924",this);
			System.out.println("PR-2924 Started");
			Thread.sleep(2000);
			relogin();
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
				System.out.println("PR-2924 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer activation is done  ");
			}
			else
			{
				System.out.println("PR-2924 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Customer activation is pending ");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2924",driver);
			System.out.println("Test__PR-2924___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	@Test(description="Disconnection")
	public void PR_2925() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2925",this);
			System.out.println("PR-2925 Started");
			Thread.sleep(5000);
			CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Plan_code,"Y",billing_Frequency);
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
				System.out.println("PR-2925 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Disconnection is Done");
			}
			else
			{
				System.out.println("PR-2925 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Disconnection Is Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2925",driver);
			System.out.println("Test__PR-2925___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	@Test(description="Reconnection")
	public void PR_2926() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2926",this);
			System.out.println("PR-2926 Started");
			Thread.sleep(2000);
			CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Plan_code,"Y",billing_Frequency);
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
				System.out.println("PR-2926 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Reconnection Is Done");
			}
			else
			{
				System.out.println("PR-2926 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Reconnection is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2926",driver);
			System.out.println("Test__PR-2926___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}

	

	@Test(description="Suspend Agreement")
	public void PR_2927() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2927",this);
			System.out.println("PR-2927 Started");
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
				System.out.println("PR-2927 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Suspend Agreement with suspend all Is Done");
			}
			else
			{
				System.out.println("PR-2927 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Suspend Agreement with suspend all Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2927",driver);
			System.out.println("Test__PR-2927___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	@Test(description="Reactivate Agreement")
	public void PR_2928() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2928",this);
			System.out.println("PR-2928 Started");
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
				System.out.println("PR-2928 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Reactivate Agreement with Reactivate all Is Done");
			}
			else
			{
				System.out.println("PR-2928 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Reactivate Agreement with Reactivate all Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2928",driver);
			System.out.println("Test__PR-2928___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}

	
	

	@Test(description="Hardware Change")
	public void PR_2929() throws Exception
	{

		try
		{
			testLinkConnection.setsTestCase("PR-2929",this);
			System.out.println("PR-2929 Started");
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
				System.out.println("PR-2929 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Hardware change Is Done");
			}
			else
			{
				System.out.println("PR-2929 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Hardware change Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2929",driver);
			System.out.println("Test__PR-2929___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	@Test(description="Renewal")
	public void PR_2930() throws Exception
	{

		try
		{
			testLinkConnection.setsTestCase("PR-2930",this);
			System.out.println("PR-2930 Started");
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
				System.out.println("PR-2930 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Renewal Is Done");
			}
			else
			{
				System.out.println("PR-2930 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Renewal Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2930",driver);
			System.out.println("Test__PR-2930___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}

	
	@Test(description="Retracking")
	public void PR_2931() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2931",this);
			System.out.println("PR-2931 Started");
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
				System.out.println("PR-2931 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Retracking Is Done");
			}
			else
			{
				System.out.println("PR-2931 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Retracking Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2931",driver);
			System.out.println("Test__PR-2931___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}

	
	@Test(description="Retrack with INITIALISE")    
	public void PR_2932() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2932",this);
			System.out.println("PR-2932 Started");
			Thread.sleep(2000);
			CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Plan_code,"Y",billing_Frequency);
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
				System.out.println("PR-2932 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Retracking with uniqueid Is Done");
			}
			else
			{
				System.out.println("PR-2932 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Retracking with uniqueid Is In pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2932",driver);
			System.out.println("Test__PR-2932___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}



	// END OF ADD-PRODUCT..

	// Start of  all actions case's

	@Test(description="Provision Actions - All Actions")
	public void PR_2933() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2933",this);
			System.out.println("PR-2933 Started");
			Thread.sleep(2000);
			relogin();
			Actionids_List = new ArrayList(10);
			Actionids_List.add("1");
			Actionids_List.add("2");
			Actionids_List.add("22");
			Actionids_List.add("24");
			Actionids_List.add("18");
			System.out.println("array list is "+Actionids_List);

			oNavigate.toProvisioningSystemAction();
			Thread.sleep(2000);
			Screen_Validation=oProvisioningSystemAction.ProvisioningActionMapping(Prov_System_Name,Actionids_List,"Save");
			System.out.println("validation message"+Screen_Validation);

			Thread.sleep(2000);
			if(Screen_Validation.length()!=0 )
			{
				System.out.println("PR-2933 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
			}
			else
			{
				System.out.println("PR-2933 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Status is not active and success message is not displaying");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2933",driver);
			System.out.println("Test__PR-2933___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}

	
	@Test(description="Retrack - Retrack All ")
	public void PR_2935() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2935",this);
			System.out.println("PR-2935 Started");
			Thread.sleep(2000);
			relogin();
			Thread.sleep(2000);
			CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Plan_code,"Y",billing_Frequency);
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
				System.out.println("PR-2935 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Retracking Is Done");
			}
			else
			{
				System.out.println("PR-2935 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Retracking Is In Pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2935",driver);
			System.out.println("Test__PR-2935___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}

	
	@Test(description="Retrack with INITIALISE")    
	public void PR_2936() throws Exception
	{
		try
		{
			testLinkConnection.setsTestCase("PR-2936",this);
			System.out.println("PR-2936 Started");
			Thread.sleep(2000);
			CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,Plan_code,"Y",billing_Frequency);
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
				System.out.println("PR-2936 Pass");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
				testLinkConnection.setsNotes("Retracking with uniqueid Is Done");
			}
			else
			{
				System.out.println("PR-2936 Failed");
				testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
				testLinkConnection.setsNotes("Retracking with uniqueid Is In pending");
			}
		}
		catch(Exception e)
		{
			captureScreenShot.takeScreenShot("PR-2936",driver);
			System.out.println("Test__PR-2936___%Failed");
			testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
			testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
		}
	}

	


		// End of actvate all case's



	// End of  Bussiness Processes For safeview






	
	
	
}
