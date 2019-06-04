package ProvisioningTC;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.BasicConfigurationInventory.InventoryTransactions;
import com.BasicConfigurationsOfServiceOrder.BasicConfigurationsOfServiceOrder;
import com.BasicConfigurationsOfServiceOrder.OrderManagementPrivileges;
import com.InventoryAndLogisticsPageObject.HardwareReplacement;
import com.InventoryAndLogisticsPageObject.OneTimeSaleOrder;
import com.InventoryAndLogisticsPageObject.OwnedHardware;
import com.JobsPageObject.ScheduleJob;
import com.OrderManagementPageObject.CustomerRegistration;
import com.OrderManagementPageObject.HardwareAssociation;
import com.OrderManagementPageObject.Renewal;
import com.OrderManagementPageObject.ServiceOrder;
import com.ProvisioningPageObject.BasicConfigurationsOfProvisioning;
import com.ProvisioningPageObject.ProvisioningRequests;
import com.ReceivablesPageObject.Collections;

import TestFrameWork.MQProvisioning;
import Utility.Navigate;
import Utility.WaitTool;
import mq.seleniumFW.connection.MQDBConnection;
import testlink.api.java.client.TestLinkAPIResults;

public class Prov_Renewal_TC extends MQProvisioning 


{
	WebDriver driver;
    Navigate oNavigate;
    WaitTool oWaitTool;

    CustomerRegistration oCustomerRegistration;
    ServiceOrder oserviceorder;
    ProvisioningRequests oProvisioningRequests;
    ScheduleJob oScheduleJob;
    OwnedHardware oOwnedHardware;
    HardwareAssociation oHardwareAssociation;
    BasicConfigurationsOfProvisioning oBasicConfigurationsOfProvisioning;
    HardwareReplacement oHardwareReplacement;
    Renewal oRenewal;
    Collections oCollections;
    OrderManagementPrivileges oOrderManagementPrivileges;


    public List<WebElement> listOfRows;
    public By locPlanSearchTab = By.id("ctl00_uxPgCPH_txtplnsrch");
    public By locAddToCart = By.xpath(".//*[@id='ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd']");
    public By locOrderDate = By.id("ctl00_uxPgCPH_uxOrderDate");
    public By locNoOfRooms = By.id("ctl00_uxPgCPH_txtNoofrooms");
    public By locEffectiveDate=By.id("ctl00_uxPgCPH_uxEffDate");
    public By locApprove = By.id("ctl00_uxPgCPH_chkapprove");
    public By locsuccessmsg=By.xpath(".//*[@id='ctl00__infoSpan']");
    public By locContractNumber = By.xpath(".//*[@id='ctl00_uxPgCPH_txtcontractno']");
    public By locSaveBtn=By.id("ctl00_uxPgCPH_btnsave");

    public ArrayList<Hashtable<String, String>> records;
    public Hashtable<String,String> record;
    public MQDBConnection oDBConnection;
    
    String strPlanCode = "PLAN1SERV1";
    String billing_Frequency = "One Month";
    String contract_validity = "One Month";
    String ChangeContractValidity="Three Months";
    String first_Name = "Ravi";
    String last_Name = "Kannegundla";

    
    String orderDate="30-05-2019";
    String effectiveDate="30-05-2019";
    String backDated="27-02-2019";
    String renewalBackdated="25-02-2019";
    String futureDate="01-03-2020";
    
    
    
    String Status,StatusMRPL="Faulty";
    String C_Status="C",N_Status="N";
    String Party_id,Qstatus,Customer_id;
    String Item1="Smart Card",Item2="Set Top Box";

    // One Time Sale related
    String mrnNumber;
    public String Inventory_Location="Head Location",Supplier="Conax Supplier",currentDate="18-02-2019";
    String PartyClass="Customer" ,Location="Head Location",Succ_Msg;

    public InventoryTransactions invTrans;
    int services_count=1;
    String MultiRoomplan="NAGRA150";
    String hardwarePlan="HWPLAN1111";
    String ReasonMRPL="BAD";
    public String ContractNumber;
    String OperationalEntity="CORP";
    public String OrderType = "One Time Sale Order";
    String RenewalValidation;
    public   String Sent="Sent",Accept="Accept",New="New";

    String sResult,CustomerNumber;
    String getConnectionNumber,Connection_Number;
    String getRenewalCount,Renewal_Count;;
    int RenewalCount;
    
	  
	
	

	protected Prov_Renewal_TC(String sLogFileName) throws Exception
	{
		super(sLogFileName);
		 this.oDBConnection=dbConnection;
		 PageFactory.initElements(driver, this);
		 
		// TODO Auto-generated constructor stub
	}

	
	 @Override
     protected void beforeClass() 
     {
    	 driver=getDriver();
    	 driver.manage().window().maximize();
    	 this.oCustomerRegistration = new CustomerRegistration(driver);
    	 this.oserviceorder=new ServiceOrder(driver,dbConnection);
    	 this.oProvisioningRequests =new ProvisioningRequests(driver);
    	 this.oScheduleJob = new ScheduleJob(driver);
    	 this.oOwnedHardware=new OwnedHardware(driver, oDBConnection);
    	 this.invTrans=new InventoryTransactions(driver, dbConnection);
    	 this.oHardwareAssociation=new HardwareAssociation(driver,dbConnection);
    	 this.oBasicConfigurationsOfProvisioning=new BasicConfigurationsOfProvisioning(driver,dbConnection);
    	 this.oHardwareReplacement= new HardwareReplacement(driver,dbConnection);
    	 this.oRenewal = new Renewal(driver);
    	 this.oRenewal = new Renewal(driver,dbConnection);
    	 this.oCollections=new Collections(driver,dbConnection);
    	 this.oOrderManagementPrivileges=new OrderManagementPrivileges(dbConnection);


    	 oBasicConfigurationsOfProvisioning.OwnHardware_Privileges(); 
//    	 oOrderManagementPrivileges.Disconnection_privileges();
//    	 oOrderManagementPrivileges.Reconnection_privileges();
//    	 oOrderManagementPrivileges.ServiceOrder_privileges();
// need to Add renewal Prilvalges
    	 oOrderManagementPrivileges.Renewal_privileges();
    	 
    	 
    	 
    	 
    	 testLinkConnection.setsTestProject("PayTV Provisioning");
    	 testLinkConnection.setsTestPlan("5.12.1");
    	 testLinkConnection.setsBuild("5.12.1");
    	 this.oNavigate=new Navigate(driver,dbConnection);
    	 this.oWaitTool= new WaitTool(driver);
    	 PageFactory.initElements(driver,this);
    	 verifyLogin("","");

     }

     /*	    	@BeforeClass
    	public void preRequisites() throws Exception
        {
	    //Profile option prerequisites 	    			
         invTrans.profile("Auto Generate Customer Number", "true","N");

        } 
      */	        

     public void relogin() throws Exception
     {
    	 oNavigate.Navigation_Action("Logout");
    	 oWaitTool.implicitWait(10);		
//    	 verifyLogin("KRAVI","KRAVI1234");
    	 verifyLogin("SYSADMIN","SYSADMIN");
    	 oWaitTool.implicitWait(10);
     }

     
   //To generate Random numbers      ABCDEFGHIJKLMNOPQRSTUVWXYZ
     private static final String ALPHA_NUMERIC_STRING = "0123456789";
     public static String randomAlphaNumeric(int count) {
     StringBuilder builder = new StringBuilder();
     while (count-- != 0) {
     int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
     builder.append(ALPHA_NUMERIC_STRING.charAt(character));
     }
     System.out.println("value is:"+builder.toString());
     return builder.toString();
     }

     
     
     
     
               @Test(description="Renew Fixed Term Contract before Expiry ")
               public void PR_4048() throws Exception
                {
                  try
                  {
                	  testLinkConnection.setsTestCase("PR-4048",this);
                	  System.out.println("PR-4048 Started");
                	  CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
                	  ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
                	  System.out.println("Contract Number is : "+ContractNumber);
                	  Thread.sleep(2000);
                	  oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

                	  oNavigate.toRenewal();
                	  RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y","","",contract_validity,"Save");
                	  System.out.println("Success message is:"+RenewalValidation);
                	  Thread.sleep(3000);
                	  
                	  String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
                	  this.records=oDBConnection.fecthRecords(Customer_id);
                	  this.record=this.records.get(0);
                	  String Party_id=record.get("CUSTOMER_ID");
                	  System.out.println(Party_id);
                	  String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13  order by 1 desc ";
                	  this.records=oDBConnection.fecthRecords(Status);
                	  this.record=this.records.get(0);
                	  String Qstatus=record.get("STATUS");
                	  System.out.println(Qstatus);
                	  if(Qstatus.equalsIgnoreCase(N_Status))
                	  {
                		  System.out.println("PR-4048 Pass");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
                		  testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
                	  }
                	  else
                	  {
                		  System.out.println("PR-4048 Failed");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                		  testLinkConnection.setsNotes("Status is not active and success message is not displaying");
                	  }

                  }
                  catch(Exception e)
                  {
                	  captureScreenShot.takeScreenShot("PR-4048",driver);
                	  System.out.println("Test__PR-4048___%Failed");
                	  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                	  testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
                  }
                }


               
               @Test(description="Renewal after Expiry ")
               public void PR_4049() throws Exception
                {
                  try
                  {
                	  testLinkConnection.setsTestCase("PR-4049",this);
                	  System.out.println("PR-4049 Started");
                	  CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", renewalBackdated, renewalBackdated,contract_validity,strPlanCode,"Y",billing_Frequency);
                	  ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
                	  System.out.println("Contract Number is : "+ContractNumber);

                	  Thread.sleep(5000);
                	  oNavigate.toProvisioningRequest();
                	  Thread.sleep(3000);

                	  oProvisioningRequests.ProvisioningRequestsScreen("","",renewalBackdated,"","","",New,Sent,"","Search","Save");
                	  String successMsg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
                	  System.out.println("Success message is:"+successMsg1 );
                	  Thread.sleep(2000);
                	  driver.navigate().refresh();
                	  oProvisioningRequests.ProvisioningRequestsScreen("","",renewalBackdated,"","","",Sent,Accept,"","Search","Save");

                	  oNavigate.toRenewal();
                	  RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y","","",contract_validity,"Save");
                	  System.out.println("Success message is:"+RenewalValidation);
                	  Thread.sleep(5000);
                	  oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

                	  String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
                	  this.records=oDBConnection.fecthRecords(Customer_id);
                	  this.record=this.records.get(0);
                	  String Party_id=record.get("CUSTOMER_ID");
                	  System.out.println(Party_id);
                	  String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13  order by 1 desc ";
                	  this.records=oDBConnection.fecthRecords(Status);
                	  this.record=this.records.get(0);
                	  String Qstatus=record.get("STATUS");
                	  System.out.println(Qstatus);// need to add message..
                	  if(Qstatus.equalsIgnoreCase(C_Status))
                	  {
                		  System.out.println("PR-4049 Pass");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
                		  testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
                	  }
                	  else
                	  {
                		  System.out.println("PR-4049 Failed");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                		  testLinkConnection.setsNotes("Status is not active and success message is not displaying");
                	  }

                  }
                  catch(Exception e)
                  {
                	  captureScreenShot.takeScreenShot("PR-4049",driver);
                	  System.out.println("Test__PR-4049___%Failed");
                	  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                	  testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
                  }
                }


               
               @Test(description="Renewal after Expiry ")
               public void PR_4050() throws Exception
                {
                  try
                  {
                	  testLinkConnection.setsTestCase("PR-4050",this);
                	  System.out.println("PR-4050 Started");
                	  CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", backDated, backDated,contract_validity,strPlanCode,"Y",billing_Frequency);
                	  ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
                	  System.out.println("Contract Number is : "+ContractNumber);
                	  Thread.sleep(10000);
                	  oNavigate.toProvisioningRequest();
                	  Thread.sleep(7000);

                	  oProvisioningRequests.ProvisioningRequestsScreen(CustomerNumber,"",backDated,"","","",New,Sent,"","Search","Save");
                	  String successMsg2 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
                	  System.out.println("Success message is:"+successMsg2 );
                	  Thread.sleep(2000);
                	  driver.navigate().refresh();
                	  oProvisioningRequests.ProvisioningRequestsScreen(CustomerNumber,"",backDated,"","","",Sent,Accept,"","Search","Save");
                	  Thread.sleep(5000);

                	  String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
                	  this.records=oDBConnection.fecthRecords(Customer_id);
                	  this.record=this.records.get(0);
                	  String Party_id=record.get("CUSTOMER_ID");
                	  System.out.println(Party_id);
                	  oNavigate.toScheduleJob();
                	  Thread.sleep(2000);
                	  oScheduleJob.ScheduleJobAutoExpiry_SingleCustomer("", "Batch Jobs","Autoexpire", "", "", "","","AutoExpiry For Single Customer","",Party_id,"");
                	  Thread.sleep(80000);

                	  oNavigate.toRenewal();
                	  RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"","","",contract_validity,"Save");
                	  System.out.println("Success message is:"+RenewalValidation);
                	 
                	  String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13  order by 1 desc ";
                	  this.records=oDBConnection.fecthRecords(Status);
                	  this.record=this.records.get(0);
                	  String Qstatus=record.get("STATUS");
                	  System.out.println(Qstatus);// need to add message..
                	  if(Qstatus.equalsIgnoreCase(N_Status))
                	  {
                		  System.out.println("PR-4050 Pass");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
                		  testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
                	  }
                	  else
                	  {
                		  System.out.println("PR-4050 Failed");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                		  testLinkConnection.setsNotes("Status is not active and success message is not displaying");
                	  }
                  }
                  catch(Exception e)
                  {
                	  captureScreenShot.takeScreenShot("PR-4050",driver);
                	  System.out.println("Test__PR-4050___%Failed");
                	  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                	  testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
                  }
                }


               
               // doubt Need to handle in screen
               @Test(description="Renew Suspended Customer ")
               public void PR_4051() throws Exception
                {
                  try
                  {
                	  testLinkConnection.setsTestCase("PR-4051",this);
                	  System.out.println("PR-4051 Started");
                	  CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", renewalBackdated, renewalBackdated,contract_validity,strPlanCode,"Y",billing_Frequency);
                	  ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
                	  System.out.println("Contract Number is : "+ContractNumber);

                	  Thread.sleep(5000);
                	  oNavigate.toProvisioningRequest();
                	  Thread.sleep(3000);

                	  oProvisioningRequests.ProvisioningRequestsScreen("","",renewalBackdated,"","","",New,Sent,"","Search","Save");
                	  String successMsg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
                	  System.out.println("Success message is:"+successMsg1 );
                	  Thread.sleep(2000);
                	  driver.navigate().refresh();
                	  oProvisioningRequests.ProvisioningRequestsScreen("","",renewalBackdated,"","","",Sent,Accept,"","Search","Save");

                	  oNavigate.toCollection();
                	  oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
                	  Thread.sleep(5000);
                	  oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
                	  Thread.sleep(50000);

                	  oNavigate.toRenewal();
                	  Thread.sleep(5000);
                	  System.out.println("Successfully Navigated to renewal Screen");
                	  Thread.sleep(3000);
                	  driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvRenewal_ctl02_chkapprove']")).click();
                	  Thread.sleep(2000);
                	  System.out.println("approve done");
                	  driver.findElement(locSaveBtn).click();
                	  System.out.println("Save clicked");
                	  Thread.sleep(2000);
                	  Alert alert=driver.switchTo().alert();
                	  alert.accept();

                	  Thread.sleep(5000);
                	  oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

                	  String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
                	  this.records=oDBConnection.fecthRecords(Customer_id);
                	  this.record=this.records.get(0);
                	  String Party_id=record.get("CUSTOMER_ID");
                	  System.out.println(Party_id);
                	  String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13  order by 1 desc ";
                	  this.records=oDBConnection.fecthRecords(Status);
                	  this.record=this.records.get(0);
                	  String Qstatus=record.get("STATUS");
                	  System.out.println(Qstatus);// need to add message..
                	  if(Qstatus.equalsIgnoreCase(C_Status))
                	  {
                		  System.out.println("PR-4051 Pass");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
                		  testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
                	  }
                	  else
                	  {
                		  System.out.println("PR-4051 Failed");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                		  testLinkConnection.setsNotes("Status is not active and success message is not displaying");
                	  }
                  }
                  catch(Exception e)
                  {
                	  captureScreenShot.takeScreenShot("PR-4051",driver);
                	  System.out.println("Test__PR-4051___%Failed");
                	  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                	  testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
                  }
                }

               
             
               @Test(description="Renew Reactivated Customer")
               public void PR_4052() throws Exception
                {
                  try
                  {
                	  testLinkConnection.setsTestCase("PR-4052",this);
                	  System.out.println("PR-4052 Started");
                	  CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", renewalBackdated, renewalBackdated,contract_validity,strPlanCode,"Y",billing_Frequency);
                	  ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
                	  System.out.println("Contract Number is : "+ContractNumber);

                	  Thread.sleep(5000);
                	  oNavigate.toProvisioningRequest();
                	  Thread.sleep(3000);

                	  oProvisioningRequests.ProvisioningRequestsScreen("","",renewalBackdated,"","","",New,Sent,"","Search","Save");
                	  String successMsg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
                	  System.out.println("Success message is:"+successMsg1 );
                	  Thread.sleep(2000);
                	  driver.navigate().refresh();
                	  oProvisioningRequests.ProvisioningRequestsScreen("","",renewalBackdated,"","","",Sent,Accept,"","Search","Save");

                	  oNavigate.toCollection();
                	  oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
                	  Thread.sleep(5000);
                	  oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
                	  Thread.sleep(80000);

                	  oNavigate.toCollection();
                	  oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
                	  Thread.sleep(5000);
                	  oCollections.collectionGrid("", "","Active", "", "Save");
                	  Thread.sleep(80000);

                	  Thread.sleep(5000);
                	  oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

                	  oNavigate.toRenewal();
                	  RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y","","",contract_validity,"Save");
                	  System.out.println("Success message is:"+RenewalValidation);

                	  Thread.sleep(5000);
                	  oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

                	  String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
                	  this.records=oDBConnection.fecthRecords(Customer_id);
                	  this.record=this.records.get(0);
                	  String Party_id=record.get("CUSTOMER_ID");
                	  System.out.println(Party_id);
                	  String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13  order by 1 desc ";
                	  this.records=oDBConnection.fecthRecords(Status);
                	  this.record=this.records.get(0);
                	  String Qstatus=record.get("STATUS");
                	  System.out.println(Qstatus);
                	  if(Qstatus.equalsIgnoreCase(C_Status))
                	  {
                		  System.out.println("PR-4052 Pass");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
                		  testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
                	  }
                	  else
                	  {
                		  System.out.println("PR-4052 Failed");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                		  testLinkConnection.setsNotes("Status is not active and success message is not displaying");
                	  }
                  }
                  catch(Exception e)
                  {
                	  captureScreenShot.takeScreenShot("PR-4052",driver);
                	  System.out.println("Test__PR-4052___%Failed");
                	  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                	  testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
                  }
                }

               
               
               @Test(description="Renewal after Hardware Change – Smart Card ")
               public void PR_4053() throws Exception
                {
                  try
                  {
                	  testLinkConnection.setsTestCase("PR-4053",this);
                	  System.out.println("PR-4053 Started");
                	  CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "","",contract_validity,strPlanCode,"Y",billing_Frequency);
                	  ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
                	  System.out.println("Contract Number is : "+ContractNumber);
                	  Thread.sleep(2000);
                	  oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

                	  getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
                	  this.records=oDBConnection.fecthRecords(getConnectionNumber);
                	  this.record=this.records.get(0);
                	  Connection_Number=record.get("CONNECTION_NUMBER");

                	  System.out.println("connection Number is "+Connection_Number);
                	  Thread.sleep(2000);
                	  oNavigate.toHardwareAssociation();
                	  Thread.sleep(2000);
                	  oHardwareAssociation.changeAssociation(CustomerNumber,"Change",Connection_Number,"");

                	  oNavigate.toRenewal();
                	  RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y","","",contract_validity,"Save");
                	  System.out.println("Success message is:"+RenewalValidation);
                	  Thread.sleep(3000);
                	  
                	  String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
                	  this.records=oDBConnection.fecthRecords(Customer_id);
                	  this.record=this.records.get(0);
                	  String Party_id=record.get("CUSTOMER_ID");
                	  System.out.println(Party_id);
                	  String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13  order by 1 desc ";
                	  this.records=oDBConnection.fecthRecords(Status);
                	  this.record=this.records.get(0);
                	  String Qstatus=record.get("STATUS");
                	  System.out.println(Qstatus);
					  if(Qstatus.equalsIgnoreCase(N_Status))
                	  {
                		  System.out.println("PR-4053 Pass");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
                		  testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
                	  }
                	  else
                	  {
                		  System.out.println("PR-4053 Failed");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                		  testLinkConnection.setsNotes("Status is not active and success message is not displaying");
                	  }
                  }
                  catch(Exception e)
                  {
                	  captureScreenShot.takeScreenShot("PR-4053",driver);
                	  System.out.println("Test__PR-4053___%Failed");
                	  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                	  testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
                  }
                }


               
               @Test(description="Renewal after Hardware Change – Set-top-box ")
               public void PR_4054() throws Exception
                {
                  try
                  {
                	  testLinkConnection.setsTestCase("PR-4054",this);
                	  System.out.println("PR-4054 Started");
                	  CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
                	  ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
                	  System.out.println("Contract Number is : "+ContractNumber);
                	  Thread.sleep(10000);

                	  oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
                	  getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
                	  this.records=oDBConnection.fecthRecords(getConnectionNumber);
                	  this.record=this.records.get(0);
                	  Connection_Number=record.get("CONNECTION_NUMBER");

                	  System.out.println("connection Number is "+Connection_Number);
                	  Thread.sleep(2000);
                	  oNavigate.toHardwareAssociation();
                	  Thread.sleep(2000);
                	  oHardwareAssociation.changeAssociation(CustomerNumber,"Change",Connection_Number,"");

                	  oNavigate.toRenewal();
                	  RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y","","",contract_validity,"Save");
                	  System.out.println("Success message is:"+RenewalValidation);
                	  Thread.sleep(3000);
                	  
                	  String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
                	  this.records=oDBConnection.fecthRecords(Customer_id);
                	  this.record=this.records.get(0);
                	  String Party_id=record.get("CUSTOMER_ID");
                	  System.out.println(Party_id);
                	  String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13  order by 1 desc ";
                	  this.records=oDBConnection.fecthRecords(Status);
                	  this.record=this.records.get(0);
                	  String Qstatus=record.get("STATUS");
                	  System.out.println(Qstatus);
                	  if(Qstatus.equalsIgnoreCase(N_Status))
                	  {
                		  System.out.println("PR-4054 Pass");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
                		  testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
                	  }
                	  else
                	  {
                		  System.out.println("PR-4054 Failed");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                		  testLinkConnection.setsNotes("Status is not active and success message is not displaying");
                	  }
                  }
                  catch(Exception e)
                  {
                	  captureScreenShot.takeScreenShot("PR-4054",driver);
                	  System.out.println("Test__PR-4054___%Failed");
                	  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                	  testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
                  }
                }


               
               @Test(description="Renew Contract after Hardware Replacement")
               public void PR_4055() throws Exception
                {
                  try
                  {
                	  testLinkConnection.setsTestCase("PR-4055",this);
                	  System.out.println("PR-4055 Started");
                	  CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Sale", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
                	  ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
                	  System.out.println("Contract Number is : "+ContractNumber);

                	  Thread.sleep(5000);
                	  oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

                	  oNavigate.toHardwareReplacement();
                	  oWaitTool.implicitWait(20);		        	  
                	  oHardwareReplacement.HardwareReplacement_Header(CustomerNumber,"", "","",ReasonMRPL,"","","","","","","","","","","New");
                	  oHardwareReplacement.HardwareReplacement_Item_Dtls(Item1,"",StatusMRPL,Item1,OperationalEntity,Location,OrderType,1);
                	  driver.findElement(locSaveBtn).click();
                	  Thread.sleep(20000);
                	  oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
                	 
                	  oNavigate.toRenewal();
                	  RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y","","",contract_validity,"Save");
                	  System.out.println("Success message is:"+RenewalValidation);
                	  Thread.sleep(3000);
                	  
                	  String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
                	  this.records=oDBConnection.fecthRecords(Customer_id);
                	  this.record=this.records.get(0);
                	  String Party_id=record.get("CUSTOMER_ID");
                	  System.out.println(Party_id);
                	  String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13  order by 1 desc ";
                	  this.records=oDBConnection.fecthRecords(Status);
                	  this.record=this.records.get(0);
                	  String Qstatus=record.get("STATUS");
                	  System.out.println(Qstatus);
                	  if(Qstatus.equalsIgnoreCase(N_Status))
                	  {
                		  System.out.println("PR-4055 Pass");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
                		  testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
                	  }
                	  else
                	  {
                		  System.out.println("PR-4055 Failed");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                		  testLinkConnection.setsNotes("Status is not active and success message is not displaying");
                	  }
                  }
                  catch(Exception e)
                  {
                	  captureScreenShot.takeScreenShot("PR-4055",driver);
                	  System.out.println("Test__PR-4055___%Failed");
                	  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                	  testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
                  }
                }


               
               @Test(description="Renew Contract after Change Agreement ")
               public void PR_4056() throws Exception
                {
                  try
                  {
                	  testLinkConnection.setsTestCase("PR-4056",this);
                	  System.out.println("PR-4056 Started");
                	  CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own","","","",strPlanCode,"Y",billing_Frequency);
    	        	  ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
    	        	  System.out.println("Contract Number is : "+ContractNumber);
    	        	  Thread.sleep(5000);
    	        	  oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
    	        	  Thread.sleep(3000);
    	        	  
    	        	  oNavigate.toServiceOrder();
    	        	  oWaitTool.implicitWait(100);

    	        	  driver.findElement(By.id("ctl00_uxPgCPH_grdcontractdtls_ctl02_lnkmodify")).click();
    	        	  Thread.sleep(3000);
    	        	  driver.findElement(locApprove).click();
    	        	  Thread.sleep(2000);
    	        	  new Select(driver.findElement(By.id("ctl00_uxPgCPH_contractvalidity"))).selectByVisibleText("One Month");
    	        	  Thread.sleep(5000);
    	        	  driver.findElement(locSaveBtn).click();			 
    	        	  Thread.sleep(3000);
    	        	  String succ_Msg = driver.findElement(locsuccessmsg).getText();
    	        	  System.out.println("Success message is:"+succ_Msg);
    	        	  Thread.sleep(5000);
    	        	  oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
    	        	  Thread.sleep(3000);
                	  
                	  oNavigate.toRenewal();
                	  RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y","","",contract_validity,"Save");
                	  System.out.println("Success message is:"+RenewalValidation);
                	  Thread.sleep(3000);

                	  String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
                	  this.records=oDBConnection.fecthRecords(Customer_id);
                	  this.record=this.records.get(0);
                	  String Party_id=record.get("CUSTOMER_ID");
                	  System.out.println(Party_id);
                	  String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13  order by 1 desc ";
                	  this.records=oDBConnection.fecthRecords(Status);
                	  this.record=this.records.get(0);
                	  String Qstatus=record.get("STATUS");
                	  System.out.println(Qstatus);
                	  if(Qstatus.equalsIgnoreCase(N_Status))
                	  {
                		  System.out.println("PR-4056 Pass");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
                		  testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
                	  }
                	  else
                	  {
                		  System.out.println("PR-4056 Failed");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                		  testLinkConnection.setsNotes("Status is not active and success message is not displaying");
                	  }
                  }
                  catch(Exception e)
                  {
                	  captureScreenShot.takeScreenShot("PR-4056",driver);
                	  System.out.println("Test__PR-4056___%Failed");
                	  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                	  testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
                  }
                }

               
               
               @Test(description="Renew Contract with is_bulk as Y")
               public void PR_4057() throws Exception
                {
                  try
                  {
                	  testLinkConnection.setsTestCase("PR-4057",this);
                	  System.out.println("PR-4057 Started");
                	  CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation_WithBulk();
                	  System.out.println("Customer number is : "+CustomerNumber); 
                	  Thread.sleep(3000);
                	  
                	  System.out.println("entered Into method for Customer Actvation");
            		  Thread.sleep(2000);
            		  oNavigate.toCustomerRegistration();
            	      Thread.sleep(2000);
            	      System.out.println("entered IN CUSTOMER REG ");
            	      CustomerNumber = oCustomerRegistration.NewCustomerRegistration(first_Name,last_Name,"","");
            	      System.out.println("CUSTOMER REG COMPLETED");
            	      System.out.println("Customer number is : "+CustomerNumber); 
            	      Thread.sleep(3000);
            	      
            	      oNavigate.toOwnedHardware();
                      String STB = "PROVSTB"+randomAlphaNumeric(8);
                      String SC = "PROVSC"+randomAlphaNumeric(8);
                      oOwnedHardware.Owned_Hardware_Add("",Item2,STB,"","","",1);
                      oOwnedHardware.Owned_Hardware_Add("",Item1,SC,"","","",2);
                      driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
                      Thread.sleep(3000);
                     
                      oNavigate.toServiceOrder();
                      System.out.println("Entered in to service order");
                      Thread.sleep(4000);
                      driver.findElement(locApprove).click();
                      Thread.sleep(2000);
                      driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_chkIsBulk']")).click();
                      Thread.sleep(3000);
                      Thread.sleep(2000);
    	        	  new Select(driver.findElement(By.id("ctl00_uxPgCPH_contractvalidity"))).selectByVisibleText("One Month");
    	        	  Thread.sleep(5000);
                      driver.findElement(locPlanSearchTab).sendKeys("NAGRA150"+Keys.TAB);
                      Thread.sleep(3000);
                      driver.findElement(locAddToCart).click();
                      Thread.sleep(4000);
                      driver.findElement(locOrderDate).clear();
                      Thread.sleep(4000);
                      driver.findElement(locOrderDate).sendKeys(renewalBackdated);
                      System.out.println("Order End Date");
                      driver.findElement(locEffectiveDate).clear();
                      Thread.sleep(3000);
                      driver.findElement(locEffectiveDate).sendKeys(renewalBackdated);
                      System.out.println("Effective end date");
                      Thread.sleep(3000);
                      driver.findElement(locSaveBtn).click();
                      Thread.sleep(3000);
                      String Succ_Msg = driver.findElement(locsuccessmsg).getText();
                      Thread.sleep(2000);
                      System.out.println("Success message is:"+Succ_Msg);
                      
                      oNavigate.toHardwareAssociation();
               	      Thread.sleep(5000);
               	      int k;
               	      WebElement table = driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody"));
               	      listOfRows = table.findElements(By.tagName("img"));
               	      System.out.println("Rows: "+listOfRows.size());

               	      for(int j =1;j<=listOfRows.size();j++)
               	      {
               	    	  k=j+1;
               	    	  System.out.println("start k value" +k);
               	    	  driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody/tr["+j+"]/td[5]/a/img")).click();				
               	    	  new Select(driver.findElement(By.xpath("//*[@id='ctl00_uxPgCPH_grdvAssociate_ctl0"+k+"_cmbunique']"))).selectByIndex(1);//need to modify
               	    	  driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody/tr["+j+"]/td[5]/a/img")).click();
               	    	  Thread.sleep(2000);
               	      }
               	      Thread.sleep(4000);
               	      driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_btnsave']")).click();
               	      System.out.println("Hardware Association Is Done Successfully");
               	
                	  String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
                	  this.records=oDBConnection.fecthRecords(Customer_id);
                	  this.record=this.records.get(0);
                	  String Party_id=record.get("CUSTOMER_ID");
                	  System.out.println(Party_id);
                	  String ContractId = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=1  order by 1 desc ";
                	  this.records=oDBConnection.fecthRecords(ContractId);
                	  this.record=this.records.get(0);
                	  String ContractNumber=record.get("CONTRACT_ID");
                	  System.out.println("ContractNumber is :"+ContractNumber);
                	  Thread.sleep(80000);
                	  oNavigate.toProvisioningRequest();
                	  Thread.sleep(3000);

                	  oProvisioningRequests.ProvisioningRequestsScreen("","",renewalBackdated,"","","",New,Sent,"","Search","Save");
                	  String successMsg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
                	  System.out.println("Success message is:"+successMsg1 );
                	  Thread.sleep(2000);
                	  driver.navigate().refresh();
                	  oProvisioningRequests.ProvisioningRequestsScreen("","",renewalBackdated,"","","",Sent,Accept,"","Search","Save");
                	  Thread.sleep(3000);

                	  oNavigate.toRenewal();
                	  RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y","","",contract_validity,"Save");
                	  System.out.println("Success message is:"+RenewalValidation);
                	  Thread.sleep(3000);
                	  String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13 and IS_PROVISIONED='N' order by 1 desc ";
                	  this.records=oDBConnection.fecthRecords(Status);
                	  this.record=this.records.get(0);
                	  String Qstatus=record.get("STATUS");
                	  System.out.println(Qstatus);
                	  if(Qstatus.equalsIgnoreCase(N_Status))
                	  {
                		  System.out.println("PR-4057 Pass");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
                		  testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
                	  }
                	  else
                	  {
                		  System.out.println("PR-4057 Failed");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                		  testLinkConnection.setsNotes("Status is not active and success message is not displaying");
                	  }
                  }
                  catch(Exception e)
                  {
                	  captureScreenShot.takeScreenShot("PR-4057",driver);
                	  System.out.println("Test__PR-4057___%Failed");
                	  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                	  testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
                  }
                }

               
               
               @Test(description="Renew Multi-Room Contract ")
               public void PR_4058() throws Exception
                {
                  try
                  {
                	  testLinkConnection.setsTestCase("PR-4058",this);
                	  System.out.println("PR-4058 Started");
                	  oNavigate.toCustomerRegistration();
                	  Thread.sleep(2000);

                	  CustomerNumber = oCustomerRegistration.NewCustomerRegistration(first_Name,last_Name,"","");
                	  System.out.println("Customer number is : "+CustomerNumber); 
                	  Thread.sleep(3000);
                	  System.out.println("entered");
                	  Thread.sleep(3000);

                	  oNavigate.toOwnedHardware();
                	  String SC ="PRO"+randomAlphaNumeric(8);
                	  String STB ="PRO"+randomAlphaNumeric(8);
                	  String SC1 ="PRO"+randomAlphaNumeric(8);
                	  String STB1 ="PRO"+randomAlphaNumeric(8);
                	  oOwnedHardware.Owned_Hardware_Add("",Item1,SC,"","","",1);
                	  oOwnedHardware.Owned_Hardware_Add("",Item2,STB,"","","",2);
                	  oOwnedHardware.Owned_Hardware_Add("",Item1,SC1,"","","",3);
                	  oOwnedHardware.Owned_Hardware_Add("",Item2,STB1,"","","",4);
                	  driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
                	  oWaitTool.implicitWait(50);

                	  oNavigate.toServiceOrder();
                	  oWaitTool.implicitWait(100);
                	  driver.findElement(locApprove).click();
                	  Thread.sleep(1000);

                	  driver.findElement(locNoOfRooms).clear();
                	  Thread.sleep(2000);
                	  driver.findElement(locNoOfRooms).sendKeys("2"+Keys.TAB);
                	  Thread.sleep(3000);
                	  oserviceorder.BookPlan("","","",contract_validity,MultiRoomplan,"","");
                	  Thread.sleep(3000);
                	  String SROutput=driver.findElement(By.id("ctl00__infoSpan")).getText();
                	  Thread.sleep(2000);
                	  System.out.println("validation msg= "+SROutput);
                	  Thread.sleep(5000);
                	  oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
                	  Thread.sleep(3000);

                	  oNavigate.toRenewal();
                	  RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y","","",contract_validity,"Save");
                	  System.out.println("Success message is:"+RenewalValidation);
                	  Thread.sleep(3000);

                	  String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
                	  this.records=oDBConnection.fecthRecords(Customer_id);
                	  this.record=this.records.get(0);
                	  String Party_id=record.get("CUSTOMER_ID");
                	  System.out.println(Party_id);
                	  String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13 and IS_PROVISIONED='N' order by 1 desc ";
                	  this.records=oDBConnection.fecthRecords(Status);
                	  this.record=this.records.get(0);
                	  String Qstatus=record.get("STATUS");
                	  System.out.println(Qstatus);
                	  if(Qstatus.equalsIgnoreCase(N_Status))
                	  {
                		  System.out.println("PR-4058 Pass");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
                		  testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
                	  }
                	  else
                	  {
                		  System.out.println("PR-4058 Failed");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                		  testLinkConnection.setsNotes("Status is not active and success message is not displaying");
                	  }
                  }
                  catch(Exception e)
                  {
                	  captureScreenShot.takeScreenShot("PR-4058",driver);
                	  System.out.println("Test__PR-4058___%Failed");
                	  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                	  testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
                  }
                }

               
               
               @Test(description="Renew Multiple Contracts on a single card. ")
               public void PR_4059() throws Exception
                {
                  try
                  {
                	  testLinkConnection.setsTestCase("PR-4059",this);
                	  System.out.println("PR-4059 Started");
                	  Thread.sleep(2000);
                	  oNavigate.toCustomerRegistration();
                	  Thread.sleep(2000);

                	  CustomerNumber = oCustomerRegistration.NewCustomerRegistration(first_Name,last_Name,"","");
                	  System.out.println("Customer number is : "+CustomerNumber); 
                	  Thread.sleep(3000);
                	  System.out.println("entered");
                	  Thread.sleep(3000);

                	  oNavigate.toOwnedHardware();
                	  String SC = "PROVSC"+randomAlphaNumeric(8);
                	  String STB = "PROVSTB"+randomAlphaNumeric(8);
                	  oOwnedHardware.Owned_Hardware_Add("",Item1,SC,"","","",1);
                	  oOwnedHardware.Owned_Hardware_Add("",Item2,STB,"","","",2);
                	  driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
                	  oWaitTool.implicitWait(50);

                	  oNavigate.toServiceOrder();
                	  oWaitTool.implicitWait(100);
                	  driver.findElement(locApprove).click();
                	  Thread.sleep(4000);
                	  new Select(driver.findElement(By.id("ctl00_uxPgCPH_contractvalidity"))).selectByVisibleText("One Month");
                	  Thread.sleep(5000);
                	  oserviceorder.serviceOrder_MulitplePlans("PLAN1SERV1","","","",services_count);
                	  Thread.sleep(4000);
                	  oserviceorder.serviceOrder_MulitplePlans("NAGRA150","","","",services_count);
                	  System.out.println("Out of multiple plan's method");
                	  driver.findElement(locSaveBtn).click();
                	  Thread.sleep(6000);
                	  oNavigate.toHardwareAssociation();

                	  Thread.sleep(2000);
                	  oHardwareAssociation.NonMultiConnection(CustomerNumber, "MultipleContractWithSameHardware");
                	  Thread.sleep(60000);
                	  oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
                	  Thread.sleep(6000);

                	  getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
                	  this.records=oDBConnection.fecthRecords(getConnectionNumber);
                	  this.record=this.records.get(0);
                	  Connection_Number=record.get("CONNECTION_NUMBER");
                	  System.out.println("connection Number is "+Connection_Number);

                	  // need to write the code for renewal 
                	  oNavigate.toRenewal();
                	  RenewalValidation =oRenewal.Renewal_MultipleContract("",Connection_Number,"Y","","",contract_validity,"Save");
                	  driver.findElement(locSaveBtn).click();	
                	  Thread.sleep(1000);
                	  String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
                	  System.out.println("Success message is:"+success_Msg);
                	  System.out.println("Renewal  is done");
                	  Thread.sleep(3000);

                	  String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
                	  this.records=oDBConnection.fecthRecords(Customer_id);
                	  this.record=this.records.get(0);
                	  String Party_id=record.get("CUSTOMER_ID");
                	  System.out.println(Party_id);
                	  String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=85  order by 1 desc ";
                	  this.records=oDBConnection.fecthRecords(Status);
                	  this.record=this.records.get(0);
                	  String Qstatus=record.get("STATUS");
                	  System.out.println(Qstatus);
                	  if(Qstatus.equalsIgnoreCase(N_Status))
                	  {
                		  System.out.println("PR-4059 Pass");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
                		  testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
                	  }
                	  else
                	  {
                		  System.out.println("PR-4059 Failed");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                		  testLinkConnection.setsNotes("Status is not active and success message is not displaying");
                	  }
                  }
                  catch(Exception e)
                  {
                	  captureScreenShot.takeScreenShot("PR-4059",driver);
                	  System.out.println("Test__PR-4059___%Failed");
                	  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                	  testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
                  }
                }

	
               
               @Test(description="Renew Multiple Contracts on a different cards.")
               public void PR_4060() throws Exception
                {
                  try
                  {
                	  testLinkConnection.setsTestCase("PR-4060",this);
                	  System.out.println("PR-4060 Started");
                	  oNavigate.toCustomerRegistration();
                	  CustomerNumber = oCustomerRegistration.NewCustomerRegistration(first_Name,last_Name,"","");
                	  System.out.println("Customer number is : "+CustomerNumber); 
                	  Thread.sleep(3000);
                	  System.out.println("entered");
                	  Thread.sleep(3000);

                	  oNavigate.toOwnedHardware();
                	  String SC = "PRO"+randomAlphaNumeric(8);
                	  String STB = "PRO"+randomAlphaNumeric(8);
                	  String SC1 = "PRO"+randomAlphaNumeric(8);
                	  String STB1 = "PRO"+randomAlphaNumeric(8);
                	  oOwnedHardware.Owned_Hardware_Add("",Item1,SC,"","","",1);
                	  oOwnedHardware.Owned_Hardware_Add("",Item2,STB,"","","",2);
                	  oOwnedHardware.Owned_Hardware_Add("",Item1,SC1,"","","",3);
                	  oOwnedHardware.Owned_Hardware_Add("",Item2,STB1,"","","",4);
                	  driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
                	  oWaitTool.implicitWait(50);

                	  oNavigate.toServiceOrder();
                	  oWaitTool.implicitWait(100);
                	  driver.findElement(locApprove).click();
                	  Thread.sleep(4000);
                	  new Select(driver.findElement(By.id("ctl00_uxPgCPH_contractvalidity"))).selectByVisibleText("One Month");
                	  Thread.sleep(5000);
                	  oserviceorder.serviceOrder_MulitplePlans("PLAN1SERV1","","","",services_count);
                	  Thread.sleep(4000);
                	  oserviceorder.serviceOrder_MulitplePlans("NAGRA150","","","",services_count);
                	  System.out.println("Out of multiple plan's method");
                	  driver.findElement(locSaveBtn).click();
                	  Thread.sleep(6000);

                	  getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
                	  this.records=oDBConnection.fecthRecords(getConnectionNumber);
                	  this.record=this.records.get(0);
                	  Connection_Number=record.get("CONNECTION_NUMBER");
                	  System.out.println("connection Number is "+Connection_Number);

                	  Thread.sleep(5000);
                	  oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
                	  Thread.sleep(3000);

                	  oNavigate.toRenewal();
                	  RenewalValidation =oRenewal.Renewal_MultipleContract("",Connection_Number,"Y","","",contract_validity,"Save");
                	  driver.findElement(locSaveBtn).click();	
                	  Thread.sleep(1000);
                	  String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
                	  System.out.println("Success message is:"+success_Msg);
                	  System.out.println("Renewal  is done");
                	  Thread.sleep(3000);

                	  String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
                	  this.records=oDBConnection.fecthRecords(Customer_id);
                	  this.record=this.records.get(0);
                	  String Party_id=record.get("CUSTOMER_ID");
                	  System.out.println(Party_id);
                	  String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=85  order by 1 desc ";
                	  this.records=oDBConnection.fecthRecords(Status);
                	  this.record=this.records.get(0);
                	  String Qstatus=record.get("STATUS");
                	  System.out.println(Qstatus);
                	  if(Qstatus.equalsIgnoreCase(N_Status))
                	  {
                		  System.out.println("PR-4060 Pass");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
                		  testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
                	  }
                	  else
                	  {
                		  System.out.println("PR-4060 Failed");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                		  testLinkConnection.setsNotes("Status is not active and success message is not displaying");
                	  }
                  }
                  catch(Exception e)
                  {
                	  captureScreenShot.takeScreenShot("PR-4060",driver);
                	  System.out.println("Test__PR-4060___%Failed");
                	  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                	  testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
                  }
                }


               
               @Test(description="Renew Contracts having Service plan and Hardware Plan respectively ")
               public void PR_4062() throws Exception
                {
                  try
                  {
                	  testLinkConnection.setsTestCase("PR-4062",this);
                	  System.out.println("PR-4062 Started");
                	  Thread.sleep(2000);
                	  oNavigate.toCustomerRegistration();
                	  Thread.sleep(2000);
                	  CustomerNumber = oCustomerRegistration.NewCustomerRegistration(first_Name,last_Name,"","");
                	  System.out.println("Customer number is : "+CustomerNumber); 
                	  Thread.sleep(3000);
                	  System.out.println("entered");
                	  Thread.sleep(3000);

                	  mrnNumber=invTrans.mrnTrans("","1",Inventory_Location,Supplier,currentDate,"N",1,"Y","N","Y");
                	  System.out.println("Mrn Completed");
                	  Thread.sleep(5000);
                	  oNavigate.toServiceOrder();
                	  oWaitTool.implicitWait(100);
                	  driver.findElement(locApprove).click();
                	  Thread.sleep(1000);
                	  oserviceorder.serviceOrder_MulitplePlans(MultiRoomplan,"","",hardwarePlan,1);
                	  Thread.sleep(3000);
                	  new Select(driver.findElement(By.id("ctl00_uxPgCPH_contractvalidity"))).selectByVisibleText("One Month");
                	  Thread.sleep(5000);
                	  driver.findElement(By.id("ctl00_uxPgCPH_CheckHw")).click();     

                	  String Stb_SERIAL_NUMBER="select SERIAL_NUMBER from item_serial_dtl where item_id=19 and mrn_id in (select mrn_id from mtl_receipt_note where mrn_number = '"+mrnNumber+"')";										
                	  this.records=oDBConnection.fecthRecords(Stb_SERIAL_NUMBER);					
                	  this.record=this.records.get(0);					
                	  String SERIAL_NUMBER=this.record.get("SERIAL_NUMBER");
                	  System.out.println(SERIAL_NUMBER +"the value of SERIAL_NUMBER is");     					  		
                	  Thread.sleep(3000);
                	  driver.findElement(By.id("ctl00_uxPgCPH_GridHwDetail_ctl02_slnodl_ctl00_txtLeaseslno")).sendKeys(SERIAL_NUMBER+Keys.TAB);
                	  Thread.sleep(1000);
                	  System.out.println("End  of Set top box");

                	  String Sc_SERIAL_NUMBER="select SERIAL_NUMBER from item_serial_dtl where item_id=16 and mrn_id in (select mrn_id from mtl_receipt_note where mrn_number = '"+mrnNumber+"')";										
                	  this.records=oDBConnection.fecthRecords(Sc_SERIAL_NUMBER);					
                	  this.record=this.records.get(0);					
                	  String SERIAL_NUMBER1=this.record.get("SERIAL_NUMBER");
                	  System.out.println(SERIAL_NUMBER1 +"the value of SERIAL_NUMBER is");     					  		
                	  Thread.sleep(3000);
                	  driver.findElement(By.id("ctl00_uxPgCPH_GridHwDetail_ctl03_slnodl_ctl00_txtLeaseslno")).sendKeys(SERIAL_NUMBER1+Keys.TAB);
                	  Thread.sleep(1000);
                	  System.out.println("End  of Smart Card");
                	  driver.findElement(locSaveBtn).click();	        	  

                	  Thread.sleep(10000);
                	  oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
                	  Thread.sleep(3000);

                	  getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
                	  this.records=oDBConnection.fecthRecords(getConnectionNumber);
                	  this.record=this.records.get(0);
                	  Connection_Number=record.get("CONNECTION_NUMBER");
                	  System.out.println("connection Number is "+Connection_Number);

                	  oNavigate.toRenewal();
                	  RenewalValidation =oRenewal.Renewal_MultipleContract("",Connection_Number,"Y","","",contract_validity,"Save");
                	  driver.findElement(locSaveBtn).click();	
                	  Thread.sleep(1000);
                	  String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
                	  System.out.println("Success message is:"+success_Msg);
                	  System.out.println("Renewal  is done");
                	  Thread.sleep(3000);

                	  String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
                	  this.records=oDBConnection.fecthRecords(Customer_id);
                	  this.record=this.records.get(0);
                	  String Party_id=record.get("CUSTOMER_ID");
                	  System.out.println(Party_id);
                	  String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=85  order by 1 desc ";
                	  this.records=oDBConnection.fecthRecords(Status);
                	  this.record=this.records.get(0);
                	  String Qstatus=record.get("STATUS");
                	  System.out.println(Qstatus);
                	  if(Qstatus.equalsIgnoreCase(N_Status))
                	  {
                		  System.out.println("PR-4062 Pass");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
                		  testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
                	  }
                	  else
                	  {
                		  System.out.println("PR-4062 Failed");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                		  testLinkConnection.setsNotes("Status is not active and success message is not displaying");
                	  }
                  }
                  catch(Exception e)
                  {
                	  captureScreenShot.takeScreenShot("PR-4062",driver);
                	  System.out.println("Test__PR-4062___%Failed");
                	  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                	  testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
                  }
                }

               
               
               @Test(description="Changing Contract Validity from Renewal Screen ")
               public void PR_4063() throws Exception
                {
                  try
                  {
                	  testLinkConnection.setsTestCase("PR-4063",this);
                	  System.out.println("PR-4063 Started");
                	  CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
                	  ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
                	  System.out.println("Contract Number is : "+ContractNumber);
                	  Thread.sleep(2000);
                	  oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

                	  oNavigate.toRenewal();
                	  RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y","","",ChangeContractValidity,"Save");
                	  System.out.println("Success message is:"+RenewalValidation);
                	  Thread.sleep(3000);
                	  
                	  String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
                	  this.records=oDBConnection.fecthRecords(Customer_id);
                	  this.record=this.records.get(0);
                	  String Party_id=record.get("CUSTOMER_ID");
                	  System.out.println(Party_id);
                	  String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13  order by 1 desc ";
                	  this.records=oDBConnection.fecthRecords(Status);
                	  this.record=this.records.get(0);
                	  String Qstatus=record.get("STATUS");
                	  System.out.println(Qstatus);
                	  if(Qstatus.equalsIgnoreCase(N_Status))
                	  {
                		  System.out.println("PR-4063 Pass");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
                		  testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
                	  }
                	  else
                	  {
                		  System.out.println("PR-4063 Failed");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                		  testLinkConnection.setsNotes("Status is not active and success message is not displaying");
                	  }
                  }
                  catch(Exception e)
                  {
                	  captureScreenShot.takeScreenShot("PR-4063",driver);
                	  System.out.println("Test__PR-4063___%Failed");
                	  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                	  testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
                  }
                }


               
               @Test(description="Future Dated Renewal")
               public void PR_4064() throws Exception
                {
                  try
                  {
                	  testLinkConnection.setsTestCase("PR-4064",this);
                	  System.out.println("PR-4064 Started");
                	  CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
                	  ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
                	  System.out.println("Contract Number is : "+ContractNumber);
                	  Thread.sleep(2000);
                	  oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

                	  oNavigate.toRenewal();
                	  RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y",futureDate,"",contract_validity,"Save");
                	  System.out.println("Success message is:"+RenewalValidation);
                	  Thread.sleep(3000);
                	  
                	  String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
                	  this.records=oDBConnection.fecthRecords(Customer_id);
                	  this.record=this.records.get(0);
                	  String Party_id=record.get("CUSTOMER_ID");
                	  System.out.println(Party_id);
                	  String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13  order by 1 desc ";
                	  this.records=oDBConnection.fecthRecords(Status);
                	  this.record=this.records.get(0);
                	  String Qstatus=record.get("STATUS");
                	  System.out.println(Qstatus);
                	  if(Qstatus.equalsIgnoreCase(N_Status))
                	  {
                		  System.out.println("PR-4064 Pass");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
                		  testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
                	  }
                	  else
                	  {
                		  System.out.println("PR-4064 Failed");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                		  testLinkConnection.setsNotes("Status is not active and success message is not displaying");
                	  }
                  }
                  catch(Exception e)
                  {
                	  captureScreenShot.takeScreenShot("PR-4064",driver);
                	  System.out.println("Test__PR-4064___%Failed");
                	  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                	  testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
                  }
                }

               
               
               @Test(description="Renewal for the customer who is in Pending Stage")
               public void PR_4065() throws Exception
                {
                  try
                  {
                	  testLinkConnection.setsTestCase("PR-4065",this);
                	  System.out.println("PR-4065 Started");
                	  CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
                	  ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
                	  System.out.println("Contract Number is : "+ContractNumber);
                	  
                	  oNavigate.toRenewal();
                	  RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y","","",contract_validity,"Save");
                	  System.out.println("Success message is:"+RenewalValidation);
                	  Thread.sleep(3000);
                	  
                	  String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
                	  this.records=oDBConnection.fecthRecords(Customer_id);
                	  this.record=this.records.get(0);
                	  String Party_id=record.get("CUSTOMER_ID");
                	  System.out.println(Party_id);
                	  String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13  order by 1 desc ";
                	  this.records=oDBConnection.fecthRecords(Status);
                	  this.record=this.records.get(0);
                	  String Qstatus=record.get("STATUS");
                	  System.out.println(Qstatus);
                	  if(Qstatus.equalsIgnoreCase(N_Status))
                	  {
                		  System.out.println("PR-4065 Pass");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
                		  testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
                	  }
                	  else
                	  {
                		  System.out.println("PR-4065 Failed");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                		  testLinkConnection.setsNotes("Status is not active and success message is not displaying");
                	  }

                  }
                  catch(Exception e)
                  {
                	  captureScreenShot.takeScreenShot("PR-4065",driver);
                	  System.out.println("Test__PR-4065___%Failed");
                	  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                	  testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
                  }
                }

               
               
               @Test(description="Renewal Agreement in Staging ")
               public void PR_4066() throws Exception
                {
                  try
                  {
                	  testLinkConnection.setsTestCase("PR-4066",this);
                	  System.out.println("PR-4066 Started");
                	  CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
                	  ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
                	  System.out.println("Contract Number is : "+ContractNumber);
                	  Thread.sleep(2000);
                	  oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

                	  oNavigate.toRenewal();
                	  RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"","","",contract_validity,"Save");
                	  System.out.println("Success message is:"+RenewalValidation);
                	  Thread.sleep(3000);

                	  String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
                	  this.records=oDBConnection.fecthRecords(Customer_id);
                	  this.record=this.records.get(0);
                	  String Party_id=record.get("CUSTOMER_ID");
                	  System.out.println(Party_id);
                	  getRenewalCount="select COUNT(*) as COUNT from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13  order by 1 desc ";
                	  this.records=oDBConnection.fecthRecords(getRenewalCount);
                	  this.record=this.records.get(0);
                	  Renewal_Count=record.get("COUNT");
                	  RenewalCount = Integer.parseInt(Renewal_Count);
                	  System.out.println("RenewalCount is "+RenewalCount);
                	  if(RenewalCount==0)
                	  {
                		  System.out.println("PR-4066 Pass");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
                		  testLinkConnection.setsNotes(" Renewal  in Staging");
                	  }
                	  else
                	  {
                		  System.out.println("PR-4066 Failed");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                		  testLinkConnection.setsNotes("Renewal  is Not in Staging");
                	  }
                  }
                  catch(Exception e)
                  {
                	  captureScreenShot.takeScreenShot("PR-4066",driver);
                	  System.out.println("Test__PR-4066___%Failed");
                	  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                	  testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
                  }
                }


               
               @Test(description="Renew Customer having Custom Number")
               public void PR_4067() throws Exception
                {
                  try
                  {
                	  testLinkConnection.setsTestCase("PR-4067",this);
                	  System.out.println("PR-4067 Started");
                	  invTrans.profile("Auto Generate Customer Number", "false","N");
                	  relogin();

                	  Thread.sleep(2000);
                	  oNavigate.toCustomerRegistration();
                	  Thread.sleep(2000);
                	  CustomerNumber = "CST"+randomAlphaNumeric(3);
                	  oCustomerRegistration.CustomCustomerRegisteration(CustomerNumber,first_Name,last_Name,"","");
                	  System.out.println("Customer number is : "+CustomerNumber); 
                	  Thread.sleep(3000);
                	  System.out.println("entered");
                	  Thread.sleep(3000);

                	  oNavigate.toOwnedHardware();
                	  String SC = "PROVSC"+randomAlphaNumeric(8);
                	  String STB = "PROVSTB"+randomAlphaNumeric(8);
                	  oOwnedHardware.Owned_Hardware_Add("",Item1,SC,"","","",1);
                	  oOwnedHardware.Owned_Hardware_Add("",Item2,STB,"","","",2);
                	  driver.findElement(By.id("ctl00_uxPgCPH_btnsave")).click();
                	  oWaitTool.implicitWait(50);

                	  oNavigate.toServiceOrder();
                	  oWaitTool.implicitWait(100);
                	  String sResult = oserviceorder.BookPlan(CustomerNumber,"","",contract_validity,strPlanCode,"Y",billing_Frequency);
                	  System.out.println("is:"+sResult);
                	  oWaitTool.implicitWait(30);
                	  Thread.sleep(3000);
                	  oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
                	  
                	  oNavigate.toRenewal();
                	  RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y","","",contract_validity,"Save");
                	  System.out.println("Success message is:"+RenewalValidation);
                	  Thread.sleep(3000);

                	  String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
                	  this.records=oDBConnection.fecthRecords(Customer_id);
                	  this.record=this.records.get(0);
                	  String Party_id=record.get("CUSTOMER_ID");
                	  System.out.println(Party_id);
                	  String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13  order by 1 desc ";
                	  this.records=oDBConnection.fecthRecords(Status);
                	  this.record=this.records.get(0);
                	  String Qstatus=record.get("STATUS");
                	  System.out.println(Qstatus);// need to add message..
                	  if(Qstatus.equalsIgnoreCase(N_Status))
                	  {
                		  System.out.println("PR-4067 Pass");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
                		  testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
                	  }
                	  else
                	  {
                		  System.out.println("PR-4067 Failed");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                		  testLinkConnection.setsNotes("Status is not active and success message is not displaying");
                	  }
                  }
                  catch(Exception e)
                  {
                	  captureScreenShot.takeScreenShot("PR-4067",driver);
                	  System.out.println("Test__PR-4067___%Failed");
                	  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                	  testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
                  }
                  finally 
                  {
                	  invTrans.profile("Auto Generate Customer Number", "true","N");  
                	  relogin();
                  }

                }

        
               
               @Test(description=" Renwal with change of plan/service ")
               public void PR_4490() throws Exception
                {
                  try
                  {
                	  testLinkConnection.setsTestCase("PR-4490",this);
                	  System.out.println("PR-4490 Started");
                	  CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
                	  ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
                	  System.out.println("Contract Number is : "+ContractNumber);
                	  Thread.sleep(5000);
                	  oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

                	  Thread.sleep(2000);
                	  oNavigate.toRenewal();
                	  Thread.sleep(2000);
                	  driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvRenewal_ctl02_chkapprove']")).click();
                	  Thread.sleep(2000);
                	  driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvRenewal_ctl02_chngofplnl']")).click();
                	  Thread.sleep(5000);
                	  driver.findElement(By.id("ctl00_uxPgCPH_grdproductpricedtls_ctl02_imgbtnremove1")).click();
                	  Thread.sleep(5000);
                	  driver.findElement(locPlanSearchTab).sendKeys("NAGRA150"+Keys.TAB);
                	  Thread.sleep(1000);
                	  driver.findElement(locAddToCart).click();
                	  Thread.sleep(1000);
                	  driver.findElement(locSaveBtn).click();
                	  Thread.sleep(5000);
                	  String Succ_Msg = driver.findElement(locsuccessmsg).getText();
                	  Thread.sleep(3000);
                	  System.out.println("Success message is:"+Succ_Msg);

                	  String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
                	  this.records=oDBConnection.fecthRecords(Customer_id);
                	  this.record=this.records.get(0);
                	  String Party_id=record.get("CUSTOMER_ID");
                	  System.out.println(Party_id);
                	  String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13  order by 1 desc ";
                	  this.records=oDBConnection.fecthRecords(Status);
                	  this.record=this.records.get(0);
                	  String Qstatus=record.get("STATUS");
                	  System.out.println(Qstatus);
                	  if(Qstatus.equalsIgnoreCase(N_Status))
                	  {
                		  System.out.println("PR-4490 Pass");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
                		  testLinkConnection.setsNotes(" Renewal  in Staging");
                	  }
                	  else
                	  {
                		  System.out.println("PR-4490 Failed");
                		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                		  testLinkConnection.setsNotes("Renewal  is Not in Staging");
                	  }
                  }
                  catch(Exception e)
                  {
                	  captureScreenShot.takeScreenShot("PR-4490",driver);
                	  System.out.println("Test__PR-4490___%Failed");
                	  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
                	  testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
                  }
                }



               
}
