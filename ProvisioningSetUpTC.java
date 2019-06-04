package ProvisioningTC;

import java.util.ArrayList;
import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.AccountsPageObject.AccountSummary;
import com.AccountsPageObject.Deposit;
import com.BillingPageObject.NewBill;
import com.JobsPageObject.ScheduleJob;
import com.OrderManagementPageObject.CustomerRegistration;
import com.OrderManagementPageObject.ServiceOrder;
import com.ProvisioningPageObject.ProvisioningSetUp;
import com.ReceivablesPageObject.CheckBounce;
import com.ReceivablesPageObject.NewPayment;
import com.ReceivablesPageObject.PayInSlip;
import com.ReceivablesPageObject.PendingOrDishonouredPayments;
import com.ReceivablesPageObject.ViewPayments;


import TestFrameWork.MQProvisioning;
import Utility.Navigate;
import Utility.WaitTool;
import mq.seleniumFW.connection.MQDBConnection;

public class ProvisioningSetUpTC extends MQProvisioning
{
	    //declaring driver
	    WebDriver driver;
	    //declaring objects
	    Navigate oNavigate;
	    WaitTool oWaitTool;
		ProvisioningSetUp oProvisioningSetUp;
		ScheduleJob oScheduleJob;
		public MQDBConnection oDBConnection;
		
		//declaring variables
		String sResult,Result;
		String sQuery;
		public ArrayList<Hashtable<String, String>> records;
		public Hashtable<String,String> record;
		

protected ProvisioningSetUpTC(String sLogFileName) throws Exception
{

	super("Provisioning Set Up screen");
	
	// TODO Auto-generated constructor stub
	this.oDBConnection=dbConnection;
	 PageFactory.initElements(driver, this);
	//System.setProperty("webdriver.ie.driver","E:\\Lib\\VersionJars\\SeleniumStandalone2.50\\IEDriverServer.exe");


}


@Override
protected void beforeClass()
{
	//this.driver = new InternetExplorerDriver();

driver=getDriver();
//driver = new FirefoxDriver();

this.oProvisioningSetUp =new ProvisioningSetUp(driver);
this.oScheduleJob = new ScheduleJob(driver);
this.oNavigate=new Navigate(driver);
this.oWaitTool= new WaitTool(driver);


testLinkConnection.setsTestProject("Finance");
testLinkConnection.setsTestPlan("5.9");
testLinkConnection.setsBuild("5.9");

PageFactory.initElements(driver,this);


//driver.get("http://172.16.9.130/592/login.aspx");	
verifyLogin("","");
}

/*
@Test(description="Navigation to Provisioning SetUp screen")
public void dummy() throws Exception
{
//testLinkConnection.setsTestCase("UM-179",this);
logger.info("Navigation to Provisioning SetUp screen");
isbSkipTest();
isbCaseDepend(true);
try
{
  oNavigate.toProvisioningSetUp();
  logger.info("Navigation to Provisioning SetUp screen");
  String ProvisioningSetUpValidation =oProvisioningSetUp.ProvisioningSetUpScreen("124532","","","","","9988666762","","","","VOIP");
  //Thread.sleep(2300);
  System.out.println("ProvisioningSetUpValidation is :"+ProvisioningSetUpValidation);
 if(ProvisioningSetUpValidation.equalsIgnoreCase("Provisioning Setup"))
 {
 System.out.println("Tesst Passed1");
 }//if case - View Payment
 else
 {
 System.out.println("Test Failed1");
}
 
}
catch(Exception e)
{
   //captureScreenShot.takeScreenShot("UM-179",driver);
System.out.println("Test_____%Failed");
//testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
setbCaseDepend(false);
}
}
*/


@Test(description="Navigation to Provisioning SetUp screen")
public void ProvisionSetupSearchWithOutCustomProcedure() throws Exception
{
//testLinkConnection.setsTestCase("UM-179",this);
logger.info("Navigation to Provisioning SetUp screen");
isbSkipTest();
isbCaseDepend(true);
try
{
  oNavigate.toProvisioningSetUp();
  logger.info("Navigation to Provisioning SetUp screen");
  String ProvisioningSetUpValidation =oProvisioningSetUp.ProvisioningSetUpSearch("129945","","","","","111129","","","Search","Save");
  //Thread.sleep(2300);
  System.out.println("ProvisioningSetUpValidation is :"+ProvisioningSetUpValidation);
 if(ProvisioningSetUpValidation.equalsIgnoreCase("Network account has been activated successfully"))
 {
 System.out.println("Tesst Passed1");
 }//if case - View Payment
 else
 {
 System.out.println("Test Failed1");
}
 
}
catch(Exception e)
{
   //captureScreenShot.takeScreenShot("UM-179",driver);
System.out.println("Test_____%Failed");
//testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
setbCaseDepend(false);
}
}

}
