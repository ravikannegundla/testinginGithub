package ProvisioningTC;

import java.util.ArrayList;
import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.BasicConfigurationInventory.InventoryTransactions;
import com.BasicConfigurationsOfServiceOrder.BasicConfigurationsOfServiceOrder;
import com.BasicConfigurationsOfServiceOrder.OrderManagementPrivileges;
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
import com.PageObjects.OrderManagementMasters.PricingMaster.PricePlan;
import com.ProvisioningPageObject.BasicConfigurationsOfProvisioning;
import com.ProvisioningPageObject.ProvisioningRequests;
import com.ProvisioningPageObject.ProvisioningSetUp;
import com.ReceivablesPageObject.Collections;
import com.ReceivablesPageObject.CreditAllocation;

import TestFrameWork.MQProvisioning;
import Utility.Navigate;
import Utility.WaitTool;
import mq.seleniumFW.connection.MQDBConnection;
import testlink.api.java.client.TestLinkAPIResults;





public class Prov_Disconn_Reconn_TC extends MQProvisioning

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
	             Disconnection oDisconnection;
	             Reconnection oReconnection;
	             HardwareReplacement oHardwareReplacement;
	             Renewal oRenewal;
	             Collections oCollections;
	             OrderManagementPrivileges oOrderManagementPrivileges;
	             
	             
	 	        
	             public By locPlanSearchTab = By.id("ctl00_uxPgCPH_txtplnsrch");
	             public By locAddToCart = By.xpath(".//*[@id='ctl00_uxPgCPH_bundlesvcgrid_ctl02_imgbtnseladd']");
	             public By locNoOfRooms = By.id("ctl00_uxPgCPH_txtNoofrooms");
	             public By locApprove = By.id("ctl00_uxPgCPH_chkapprove");
	             public By locsuccessmsg=By.xpath(".//*[@id='ctl00__infoSpan']");
	             public By locModify = By.id("ctl00_uxPgCPH_grdcontractdtls_ctl02_lnkmodify");
	             public By locContractNumber = By.xpath(".//*[@id='ctl00_uxPgCPH_txtcontractno']");
	             public By locSaveBtn=By.id("ctl00_uxPgCPH_btnsave");
	             public By locbtnsave = By.name("ctl00$uxPgCPH$btnsave");


	             public ArrayList<Hashtable<String, String>> records;
	             public Hashtable<String,String> record;
	             public MQDBConnection oDBConnection;
	             String strPlanCode = "PLAN1SERV1";
	             String billing_Frequency = "One Month";
	             String contract_validity = "One Month";
	             String first_Name = "Ravi";
	             String last_Name = "Kannegundla";

	             
	             String disconnectedOn ="23-01-2019";
	             String futuredate_DisconnectedOn ="01-04-2020";
	             String futuredate_Reconnection ="01-04-2020";
	             String backDated="02-03-2019";
	             String futureDate="01-04-2020";
	             String currentDate="24-05-2019";
	             

	             String Status,StatusMRPL="Faulty";
	             String C_Status="C",N_Status="N";
	             String Party_id,Qstatus,Customer_id;
	             String Item1="Smart Card",Item2="Set Top Box";

	             String mrnNumber;
	             public String Inventory_Location="Head Location",Supplier="Conax Supplier";
	             String PartyClass="Customer", Entity="CORP",Location="Head Location",Succ_Msg;
	             public InventoryTransactions invTrans;
	             
	             int services_count=1;
	             String MultiRoomplan="NAGRA150";
	             String hardwarePlan="HWPLAN1111";
	             String Change_order_plan="PLANXVC";
	             String Reason="BADPAYER";
	             String ReasonMRPL="BAD";
	             public String ContractNumber;
	             String OperationalEntity="CORP";
	             public String OrderType = "One Time Sale Order";
	             String RenewalValidation;
	             public     String ScreenValidation1,Sent="Sent",Accept="Accept",New="New";

	             String sResult,Result,sRes;
	             String sQuery,CustomerNumber;
	             String getConnectionNumber,Connection_Number;
	             String title;
	             String getReconnectionCount,Reconnection_Count,getDisconnectionCount,Disconnection_Count;
                 int ReconnectionCount,DisconnectionCount;
	

	             protected Prov_Disconn_Reconn_TC(String sLogFileName) throws Exception
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
	            	 this.oDisconnection = new Disconnection(driver);
	            	 this.oDisconnection = new Disconnection(driver,dbConnection);
	            	 this.oReconnection = new Reconnection(driver);
	            	 this.oReconnection = new Reconnection(driver,dbConnection);
	            	 this.oHardwareReplacement= new HardwareReplacement(driver,dbConnection);
	            	 this.oRenewal = new Renewal(driver);
	            	 this.oCollections=new Collections(driver,dbConnection);
	            	 this.oBasicConfigurationsOfProvisioning=new BasicConfigurationsOfProvisioning(driver,dbConnection);
	            	 this.oOrderManagementPrivileges=new OrderManagementPrivileges(dbConnection);
	            	 
	            	 
	            	 oBasicConfigurationsOfProvisioning.OwnHardware_Privileges();
	            	 oOrderManagementPrivileges.Disconnection_privileges();
	            	 oOrderManagementPrivileges.Reconnection_privileges();
//	            	 oOrderManagementPrivileges.ServiceOrder_privileges();
	            	 
	            	 
	            	 testLinkConnection.setsTestProject("PayTV Provisioning");
	            	 testLinkConnection.setsTestPlan("5.13");
	            	 testLinkConnection.setsBuild("5.13");
	            	 this.oNavigate=new Navigate(driver,dbConnection);
	            	 this.oWaitTool= new WaitTool(driver);
	            	 PageFactory.initElements(driver,this);
	            	 verifyLogin("","");
	            	 Alert alert=driver.switchTo().alert();
	     	        alert.accept();


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
//	            	 verifyLogin("KRAVI","KRAVI123");
	            	 verifyLogin("SYSADMIN","SYSADMIN");
	            	 oWaitTool.implicitWait(10);
	             }

	

	             //To generate Random numbers      ABCDEFGHIJKLMNOPQRSTUVWXYZ
	             private static final String ALPHA_NUMERIC_STRING = "0123456789";
	             public static String randomAlphaNumeric(int count) 
	             {
	            	 StringBuilder builder = new StringBuilder();
	            	 while (count-- != 0) 
	            	 {
	            		 int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
	            		 builder.append(ALPHA_NUMERIC_STRING.charAt(character));
	            	 }
	            
	            	 System.out.println("value is:"+builder.toString());
	            	 return builder.toString();
	             }

	             
	             @Test(description="Pre Requisites -- Disconnection ")
	    	     public void PR_3975() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-3975",this);
	    	    	   System.out.println("PR-3975 Started");
	    	    	   Thread.sleep(3000);
	    	    	   oNavigate.toDisconnection();
	    	    	   Thread.sleep(2000);
	    	    	   title=driver.getTitle();
	    	    	   System.out.println("Success message is:"+title);
	    	    	   if(title.equalsIgnoreCase("Disconnect Contract"))
	    	    	   {
	    	    		   System.out.println("PR-3975 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	    	   }

	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-3975 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	    	   }

	    	        }

	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-3975",driver);
	    	    	   System.out.println("Test__PR-3975___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }

	             
	             
	             @Test(description=" Disconnecting an Active Customer.")
	             public void PR_3976() throws Exception
	             {
	             	try
	             {
	            	 testLinkConnection.setsTestCase("PR-3976",this);
	            	 System.out.println("PR-3976 Started");
	            	 CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	            	 ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	            	 System.out.println("Contract Number is : "+ContractNumber);
	            	 Thread.sleep(2000);
	            	 oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	            	 Thread.sleep(3000);
	            	 oNavigate.toDisconnection();
	            	 Thread.sleep(5000);
	            	 logger.info("Successfully Navigated to Disconnection Screen ");
	            	 sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	            	 String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	            	 System.out.println("Success message is:"+success_Msg);
	            	 System.out.println("Disconnection is done");

	            	 Thread.sleep(10000);
	            	 oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	            	 String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	            	 this.records=oDBConnection.fecthRecords(Customer_id);
	            	 this.record=this.records.get(0);
	            	 String Party_id=record.get("CUSTOMER_ID");
	            	 System.out.println(Party_id);
	            	 String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4  order by 1 desc ";
	            	 this.records=oDBConnection.fecthRecords(Status);
	            	 this.record=this.records.get(0);
	            	 String Qstatus=record.get("STATUS");
	            	 System.out.println(Qstatus);// need to add message..
	            	 if(Qstatus.equalsIgnoreCase(C_Status))
	            	 {
	            		 System.out.println("PR-3976 Pass");
	            		 testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	            		 testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	            	 }
	            	 else
	            	 {
	            		 System.out.println("PR-3976 Failed");
	            		 testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	            		 testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	            	 }

	             }

	             catch(Exception e)
	             {
	            	 captureScreenShot.takeScreenShot("PR-3976",driver);
	            	 System.out.println("Test__PR-3976___%Failed");
	            	 testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	            	 testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	             }
	             }

	             
	             
	             @Test(description="Disconnecting Customer after Change Agreement from Disconnection Screen")
	    	     public void PR_3977() throws Exception
	    	      {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-3977",this);
	    	    	   System.out.println("PR-3977 Started");
	    	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	    	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	    	   Thread.sleep(2000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   Thread.sleep(3000);
	    	    	   oNavigate.toServiceOrder();
	    	    	   oWaitTool.implicitWait(100);

	    	    	   driver.findElement(locModify).click();
	    	    	   Thread.sleep(2000);
	    	    	   driver.findElement(locApprove).click();
	    	    	   Thread.sleep(1000);
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
	    	    	   Thread.sleep(10000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	    	   Thread.sleep(3000);
	    	    	   
	    	    	   oNavigate.toDisconnection();
	    	    	   Thread.sleep(5000);
	    	    	   logger.info("Successfully Navigated to Disconnection Screen ");
	    	    	   sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	    	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg);
	    	    	   System.out.println("Disconnection is done");

	    	    	   Thread.sleep(5000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	    	   
	    	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	    	   this.record=this.records.get(0);
	    	    	   String Party_id=record.get("CUSTOMER_ID");
	    	    	   System.out.println(Party_id);
	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println(Qstatus);
	    	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	    	   {
	    	    		   System.out.println("PR-3977 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-3977 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	    	   }

	    	        }

	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-3977",driver);
	    	    	   System.out.println("Test__PR-3977___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	      }
 
		             
	             // Need to do the Changes related to that.......
	             @Test(description="Disconnection after Smart Card Change(Primary Hardware)")
	    	     public void PR_3978() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-3978",this);
	    	    	   System.out.println("PR-3978 Started");
	    	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	    	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	    	   Thread.sleep(8000);

	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	    	   Thread.sleep(2000);
	    	    	   getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	    	    	   this.records=oDBConnection.fecthRecords(getConnectionNumber);
	    	    	   this.record=this.records.get(0);
	    	    	   Connection_Number=record.get("CONNECTION_NUMBER");
	    	    	   System.out.println("connection Number is "+Connection_Number);

	    	    	   Thread.sleep(2000);
	    	    	   oNavigate.toHardwareAssociation();
	    	    	   Thread.sleep(2000);
	    	    	   oHardwareAssociation.changeAssociation(CustomerNumber,"Change",Connection_Number,"");
	    	    	   Thread.sleep(3000);
	    	    	   oNavigate.toDisconnection();
	    	    	   Thread.sleep(5000);
	    	    	   logger.info("Successfully Navigated to Disconnection Screen ");
	    	    	   sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	    	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg);
	    	    	   System.out.println("Disconnection is done");

	    	    	   Thread.sleep(8000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	    	   this.record=this.records.get(0);
	    	    	   String Party_id=record.get("CUSTOMER_ID");
	    	    	   System.out.println(Party_id);
	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println(Qstatus);// need to add message..
	    	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	    	   {
	    	    		   System.out.println("PR-3978 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-3978 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-3978",driver);
	    	    	   System.out.println("Test__PR-3978___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }

	             
	             
	             @Test(description="Disconnection after Set-top-box Change(Primary Hardware)")
	    	     public void PR_3979() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-3979",this);
	    	    	   System.out.println("PR-3979 Started");
	    	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
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

	    	    	   Thread.sleep(3000);
	    	    	   oNavigate.toDisconnection();
	    	    	   Thread.sleep(5000);
	    	    	   logger.info("Successfully Navigated to Disconnection Screen ");
	    	    	   sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	    	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg);
	    	    	   System.out.println("Disconnection is done");

	    	    	   Thread.sleep(9000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	    	   this.record=this.records.get(0);
	    	    	   String Party_id=record.get("CUSTOMER_ID");
	    	    	   System.out.println(Party_id);
	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println(Qstatus);// need to add message..
	    	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	    	   {
	    	    		   System.out.println("PR-3979 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-3979 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-3979",driver);
	    	    	   System.out.println("Test__PR-3979___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }

	             
	             
	             @Test(description="Disconnection after Hardware Replacement")
	    	     public void PR_3980() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-3980",this);
	    	    	   System.out.println("PR-3980 Started");
	    	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Sale", "", "","",strPlanCode,"Y",billing_Frequency);
	    	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	    	   Thread.sleep(5000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	    	   Thread.sleep(2000);
	    	    	   oNavigate.toHardwareReplacement();
	    	    	   oWaitTool.implicitWait(20);		        	  
	    	    	   oHardwareReplacement.HardwareReplacement_Header(CustomerNumber,"", "","",ReasonMRPL,"","","","","","","","","","","New");
	    	    	   oHardwareReplacement.HardwareReplacement_Item_Dtls(Item1,"",StatusMRPL,Item1,OperationalEntity,Location,OrderType,1);
	    	    	   driver.findElement(locSaveBtn).click();
	    	    	   Thread.sleep(5000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   Thread.sleep(3000);
	    	    	   oNavigate.toDisconnection();
	    	    	   Thread.sleep(5000);
	    	    	   logger.info("Successfully Navigated to Disconnection Screen ");
	    	    	   sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	    	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg);
	    	    	   System.out.println("Disconnection is done");

	    	    	   Thread.sleep(10000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	    	   this.record=this.records.get(0);
	    	    	   String Party_id=record.get("CUSTOMER_ID");
	    	    	   System.out.println(Party_id);
	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"'and BIZ_PROCESS_ID=4  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println(Qstatus);// need to add message..
	    	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	    	   {
	    	    		   System.out.println("PR-3980 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes("Disconnection after Hardware Replacement");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-3980 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-3980",driver);
	    	    	   System.out.println("Test__PR-3980___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }

	             
	             
	             @Test(description="Disconnecting a Suspended Contract")
	    	     public void PR_3981() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-3981",this);
	    	    	   System.out.println("PR-3981 Started");
	    	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
	    	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	    	   Thread.sleep(7000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   oNavigate.toCollection();
	    	    	   oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
	    	    	   Thread.sleep(5000);
	    	    	   oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
	    	    	   Thread.sleep(80000);
	    	    	   Thread.sleep(70000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   Thread.sleep(3000);
	    	    	   oNavigate.toDisconnection();
	    	    	   Thread.sleep(5000);
	    	    	   logger.info("Successfully Navigated to Disconnection Screen ");
	    	    	   System.out.println("Successfully Navigated to Disconnection Screen");
	    	    	   Thread.sleep(3000);
	    	    	   driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvDisconnection_ctl02_chkapprove']")).click();
	    	    	   Thread.sleep(2000);
	    	    	   System.out.println("approve done");
	    	    	   driver.findElement(locbtnsave).click();
	    	    	   System.out.println("Save clicked");

	    	    	   Thread.sleep(2000);
	    	    	   Alert alert=driver.switchTo().alert();
	    	    	   alert.accept();
	    	    	   Thread.sleep(2000);
	    	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg);
	    	    	   System.out.println("Disconnection is done");

	    	    	   Thread.sleep(10000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	    	   this.record=this.records.get(0);
	    	    	   String Party_id=record.get("CUSTOMER_ID");
	    	    	   System.out.println(Party_id);
	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"'and BIZ_PROCESS_ID=4  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println(Qstatus);// need to add message..
	    	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	    	   {
	    	    		   Thread.sleep(5000);
	    	    		   System.out.println("PR-3981 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes("Disconnection after Hardware Replacement");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-3981 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-3981",driver);
	    	    	   System.out.println("Test__PR-3981___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }

	             
	             
	             @Test(description="Disconnecting Suspended Customer through Collections ")
	    	     public void PR_4108() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-4108",this);
	    	    	   System.out.println("PR-4108 Started");
	    	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
	    	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	    	   Thread.sleep(7000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   oNavigate.toCollection();
	    	    	   oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
	    	    	   Thread.sleep(5000);
	    	    	   oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
	    	    	   Thread.sleep(75000);
	    	    	   
	    	    	   Thread.sleep(10000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   oNavigate.toCollection();
	    	    	   oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
	    	    	   Thread.sleep(5000);
	    	    	   oCollections.collectionGrid("", "","Disconnect Contract", "", "Save");
	    	    	   Thread.sleep(70000);

	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	    	   this.record=this.records.get(0);
	    	    	   String Party_id=record.get("CUSTOMER_ID");
	    	    	   System.out.println(Party_id);
	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"'and BIZ_PROCESS_ID=4  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println(Qstatus);// need to add message..
	    	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	    	   {
	    	    		   Thread.sleep(5000);
	    	    		   System.out.println("PR-4108 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes("Disconnection after Hardware Replacement");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-4108 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-4108",driver);
	    	    	   System.out.println("Test__PR-4108___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }

	             
	             
	             @Test(description=" Disconnecting Reactivated contract")
	    	     public void PR_3982() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-3982",this);
	    	    	   System.out.println("PR-3982 Started");
	    	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
	    	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	    	   Thread.sleep(7000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   oNavigate.toCollection();
	    	    	   oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
	    	    	   Thread.sleep(5000);
	    	    	   oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
	    	    	   System.out.println("Suspend Provisioning is done");
	    	    	   Thread.sleep(50000);

	    	    	   oNavigate.toCollection();
	    	    	   oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
	    	    	   Thread.sleep(5000);
	    	    	   oCollections.collectionGrid("", "","Active", "", "Save");
	    	    	   System.out.println("Reactivation  is done");
	    	    	   Thread.sleep(50000);

	    	    	   oNavigate.toDisconnection();
	    	    	   Thread.sleep(5000);
	    	    	   logger.info("Successfully Navigated to Disconnection Screen ");
	    	    	   sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	    	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg);
	    	    	   System.out.println("Disconnection is done");

	    	    	   Thread.sleep(10000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	    	   this.record=this.records.get(0);
	    	    	   String Party_id=record.get("CUSTOMER_ID");
	    	    	   System.out.println(Party_id);
	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"'and BIZ_PROCESS_ID=4  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println(Qstatus);// need to add message..
	    	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	    	   {
	    	    		   System.out.println("PR-3982 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes("Disconnection after Hardware Replacement");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-3982 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-3982",driver);
	    	    	   System.out.println("Test__PR-3982___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }

	             
	             
	             @Test(description="Disconnecting Renewed contract from Disconnection Screen")
	             public void PR_3983() throws Exception
	             {
	            	 try
	            	 {
	            		 testLinkConnection.setsTestCase("PR-3983",this);
	            		 System.out.println("PR-3983 Started");
	            		 CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
	            		 ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	            		 System.out.println("Contract Number is : "+ContractNumber);
	            		 Thread.sleep(2000);
	            		 oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	            		 oNavigate.toRenewal();
	            		 RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y","","",contract_validity,"Save");
	            		 System.out.println("Success message is:"+RenewalValidation);
	            		 Thread.sleep(3000);

	            		 oNavigate.toDisconnection();
	            		 Thread.sleep(5000);
	            		 logger.info("Successfully Navigated to Disconnection Screen ");
	            		 sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	            		 String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	            		 System.out.println("Success message is:"+success_Msg);
	            		 System.out.println("Disconnection is done");

	            		 String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	            		 this.records=oDBConnection.fecthRecords(Customer_id);
	            		 this.record=this.records.get(0);
	            		 String Party_id=record.get("CUSTOMER_ID");
	            		 System.out.println(Party_id);
	            		 String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4  order by 1 desc ";
	            		 this.records=oDBConnection.fecthRecords(Status);
	            		 this.record=this.records.get(0);
	            		 String Qstatus=record.get("STATUS");
	            		 System.out.println(Qstatus);// need to add message..
	            		 if(Qstatus.equalsIgnoreCase(N_Status))
	            		 {
	            			 System.out.println("PR-3983 Pass");
	            			 testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	            			 testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	            		 }
	            		 else
	            		 {
	            			 System.out.println("PR-3983 Failed");
	            			 testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	            			 testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	            		 }
	            	 }
	            	 catch(Exception e)
	            	 {
	            		 captureScreenShot.takeScreenShot("PR-3983",driver);
	            		 System.out.println("Test__PR-3983___%Failed");
	            		 testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	            		 testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	            	 }
	             }


	             
	             @Test(description="Disconnecting Customer having Customized number from Disconnection Screen")
	             public void PR_3984() throws Exception
	             {
	            	 try
	            	 {
	            		 testLinkConnection.setsTestCase("`",this);
	            		 System.out.println("PR-3984 Started");
	            		 invTrans.profile("Auto Generate Customer Number", "false","N");
	            		 relogin();

	            		 Thread.sleep(2000);
	            		 oNavigate.toCustomerRegistration();
	            		 Thread.sleep(2000);
	            		 CustomerNumber = "CST"+randomAlphaNumeric(3);
	            		 oCustomerRegistration.CustomCustomerRegisteration(CustomerNumber,first_Name,last_Name,"","");
	            		 System.out.println("Customer number is : "+CustomerNumber); 
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
	            		 String sResult = oserviceorder.BookPlan(CustomerNumber,"","","",strPlanCode,"Y",billing_Frequency);
	            		 System.out.println("is:"+sResult);
	            		 oWaitTool.implicitWait(30);
	            		 Thread.sleep(8000);
	            		 oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	            		 Thread.sleep(5000);
	            		 oNavigate.toDisconnection();
	            		 Thread.sleep(5000);
	            		 logger.info("Successfully Navigated to Disconnection Screen ");
	            		 sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	            		 String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	            		 System.out.println("Success message is:"+success_Msg);
	            		 System.out.println("Disconnection is done");
	            		 Thread.sleep(8000);
	            		 oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	            		 String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	            		 this.records=oDBConnection.fecthRecords(Customer_id);
	            		 this.record=this.records.get(0);
	            		 String Party_id=record.get("CUSTOMER_ID");
	            		 System.out.println(Party_id);
	            		 String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4  order by 1 desc ";
	            		 this.records=oDBConnection.fecthRecords(Status);
	            		 this.record=this.records.get(0);
	            		 String Qstatus=record.get("STATUS");
	            		 System.out.println(Qstatus);// need to add message..
	            		 if(Qstatus.equalsIgnoreCase(C_Status))
	            		 {
	            			 System.out.println("PR-3984 Pass");
	            			 testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	            			 testLinkConnection.setsNotes("Customer is Actvated with Customized number from Disconnection Screen ");
	            		 }
	            		 else
	            		 {
	            			 System.out.println("PR-3984 Failed");
	            			 testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	            			 testLinkConnection.setsNotes("Status is not active with Customized number from Disconnection Screen");
	            		 }
	            	 }
	            	 catch(Exception e)
	            	 {
	            		 captureScreenShot.takeScreenShot("PR-3984",driver);
	            		 System.out.println("Test__PR-3984___%Failed");
	            		 testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	            		 testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	            	 }
	            	 finally 
	            	 {
	            		 invTrans.profile("Auto Generate Customer Number", "true","N");  
	            		 relogin();
	            	 }
	             }


	             
	             @Test(description="Disconnecting Future dated Contract.")
	             public void PR_3985() throws Exception
	             {
	            	 try
	            	 {
	            		 testLinkConnection.setsTestCase("PR-3985",this);
	            		 System.out.println("PR-3985 Started");  	    	  
	            		 CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", futureDate, futureDate,"",strPlanCode,"Y","");
	            		 ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	            		 System.out.println("Contract Number is : "+ContractNumber);
	            		 Thread.sleep(5000);

	            		 oNavigate.toDisconnection();
	            		 Thread.sleep(5000);
	            		 logger.info("Successfully Navigated to Disconnection Screen ");
	            		 sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,futuredate_DisconnectedOn,Reason,"Y","");
	            		 String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	            		 System.out.println("Success message is:"+success_Msg);
	            		 System.out.println("Disconnection is done");

	            		 String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	            		 this.records=oDBConnection.fecthRecords(Customer_id);
	            		 this.record=this.records.get(0);
	            		 String Party_id=record.get("CUSTOMER_ID");
	            		 System.out.println(Party_id);
	            		 String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4  order by 1 desc ";
	            		 this.records=oDBConnection.fecthRecords(Status);
	            		 this.record=this.records.get(0);
	            		 String Qstatus=record.get("STATUS");
	            		 System.out.println(Qstatus);// need to add message..
	            		 if(Qstatus.equalsIgnoreCase(N_Status))
	            		 {
	            			 System.out.println("PR-3985 Pass");
	            			 testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	            			 testLinkConnection.setsNotes("Disconnecting Future dated Contract.");
	            		 }
	            		 else
	            		 {
	            			 System.out.println("PR-3985 Failed");
	            			 testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	            			 testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	            		 }
	            	 }
	            	 catch(Exception e)
	            	 {
	            		 captureScreenShot.takeScreenShot("PR-3985",driver);
	            		 System.out.println("Test__PR-3985___%Failed");
	            		 testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	            		 testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	            	 }
	             }

	             
	             
	             //	             -----Doubt
	             @Test(description="Disconnecting two contracts at a time for the customer with single pair of hardware. ")
	             public void PR_3986() throws Exception
	             {
	            	 try
	            	 {
	            		 testLinkConnection.setsTestCase("PR-3986",this);
	            		 System.out.println("PR-3986 Started");
	            		 Thread.sleep(2000);
	            		 oNavigate.toCustomerRegistration();
	            		 Thread.sleep(2000);

	            		 CustomerNumber = oCustomerRegistration.NewCustomerRegistration(first_Name,last_Name,"","");
	            		 System.out.println("Customer number is : "+CustomerNumber); 
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
	            		 Thread.sleep(2000);
	            		 oserviceorder.serviceOrder_MulitplePlans("PLAN1SERV1","","","",services_count);
	            		 Thread.sleep(4000);
	            		 oserviceorder.serviceOrder_MulitplePlans("NAGRA150","","","",services_count);
	            		 System.out.println("Out of method");
	            		 driver.findElement(locSaveBtn).click();
	            		 Thread.sleep(5000);

	            		 oNavigate.toHardwareAssociation();
	            		 Thread.sleep(2000);
	            		 oHardwareAssociation.NonMultiConnection(CustomerNumber, "MultipleContractWithSameHardware");
	            		 Thread.sleep(70000);
	            		 oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	            		 getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	            		 this.records=oDBConnection.fecthRecords(getConnectionNumber);
	            		 this.record=this.records.get(0);
	            		 Connection_Number=record.get("CONNECTION_NUMBER");
	            		 System.out.println("connection Number is "+Connection_Number);

	            		 oNavigate.toDisconnection();
	            		 Thread.sleep(5000);
	            		 logger.info("Successfully Navigated to Disconnection Screen ");
	            		 sResult = oDisconnection.Disconnect_MultipleContracts("",Connection_Number,"","","","");
	            		 driver.findElement(locSaveBtn).click();	
	            		 Thread.sleep(1000);
	            		 String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	            		 System.out.println("Success message is:"+success_Msg);
	            		 System.out.println("Disconnection is done");

	            		 Thread.sleep(70000);
	            		 oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	            		 Thread.sleep(3000);

	            		 String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	            		 this.records=oDBConnection.fecthRecords(Customer_id);
	            		 this.record=this.records.get(0);
	            		 String Party_id=record.get("CUSTOMER_ID");
	            		 System.out.println(Party_id);
	            		 String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=86 order by 1 desc ";
	            		 this.records=oDBConnection.fecthRecords(Status);
	            		 this.record=this.records.get(0);
	            		 String Qstatus=record.get("STATUS");
	            		 System.out.println("status of the request is :"+Qstatus+"");// need to add message..
	            		 if(Qstatus.equalsIgnoreCase(C_Status))
	            		 {
	            			 System.out.println("PR-3986 Pass");
	            			 testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	            			 testLinkConnection.setsNotes("Activating customer with multiple contracts and single pair of hardware ");
	            		 }
	            		 else
	            		 {
	            			 System.out.println("PR-3986 Failed");
	            			 testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	            			 testLinkConnection.setsNotes("Activating customer with multiple contracts and single pair of hardware Is Pending");
	            		 }
	            	 }
	            	 catch(Exception e)

	            	 {
	            		 captureScreenShot.takeScreenShot("PR-3986",driver);
	            		 System.out.println("Test__PR-3986___%Failed");
	            		 testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	            		 testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	            	 }
	             }

	             

	             @Test(description="Disconnecting two contracts at a time for the customer with two pairs of hardware. ")
	             public void PR_3987() throws Exception
	             {
	            	 try
	            	 {
	            		 testLinkConnection.setsTestCase("PR-3987",this);
	            		 System.out.println("PR-3987 Started");
	            		 oNavigate.toCustomerRegistration();
	            		 Thread.sleep(2000);

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
	            		 oserviceorder.serviceOrder_MulitplePlans("PLAN1SERV1","","","",services_count);
	            		 Thread.sleep(4000);
	            		 oserviceorder.serviceOrder_MulitplePlans("NAGRA150","","","",services_count);
	            		 System.out.println("Out of multiple plan's method");
	            		 driver.findElement(locSaveBtn).click();
	            		 Thread.sleep(10000);

	            		 getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	            		 this.records=oDBConnection.fecthRecords(getConnectionNumber);
	            		 this.record=this.records.get(0);
	            		 Connection_Number=record.get("CONNECTION_NUMBER");
	            		 System.out.println("connection Number is "+Connection_Number);

	            		 Thread.sleep(5000);
	            		 oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	            		 Thread.sleep(3000);

	            		 oNavigate.toDisconnection();
	            		 Thread.sleep(5000);
	            		 logger.info("Successfully Navigated to Disconnection Screen ");
	            		 sResult = oDisconnection.Disconnect_MultipleContracts("",Connection_Number,"","","","");
	            		 driver.findElement(locSaveBtn).click();	
	            		 Thread.sleep(1000);
	            		 String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	            		 System.out.println("Success message is:"+success_Msg);
	            		 System.out.println("Disconnection is done");

	            		 Thread.sleep(5000);
	            		 oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	            		 Thread.sleep(3000);

	            		 String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	            		 this.records=oDBConnection.fecthRecords(Customer_id);
	            		 this.record=this.records.get(0);
	            		 String Party_id=record.get("CUSTOMER_ID");
	            		 System.out.println(Party_id);
	            		 String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=86 order by 1 desc ";
	            		 this.records=oDBConnection.fecthRecords(Status);
	            		 this.record=this.records.get(0);
	            		 String Qstatus=record.get("STATUS");
	            		 System.out.println("status of the request is :"+Qstatus+"");// need to add message..
	            		 if(Qstatus.equalsIgnoreCase(C_Status))
	            		 {
	            			 System.out.println("PR-3987 Pass");
	            			 testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	            			 testLinkConnection.setsNotes("Activating of customer with multiple contracts and multiple pair of hardware ");
	            		 }
	            		 else
	            		 {
	            			 System.out.println("PR-3987 Failed");
	            			 testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	            			 testLinkConnection.setsNotes("Activating of customer with multiple contracts and multiple pair of hardware Is Pending");
	            		 }
	            	 }
	            	 catch(Exception e)
	            	 {
	            		 captureScreenShot.takeScreenShot("PR-3987",driver);
	            		 System.out.println("Test__PR-3987___%Failed");
	            		 testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	            		 testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	            	 }
	             }
	             
	             
	             
	             @Test(description="Future Dated Disconnection ")
	    	     public void PR_3988() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-3988",this);
	    	    	   System.out.println("PR-3988 Started");
	    	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	    	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	    	   Thread.sleep(5000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	    	   Thread.sleep(3000);

	    	    	   oNavigate.toDisconnection();
	    	    	   Thread.sleep(5000);
	    	    	   logger.info("Successfully Navigated to Disconnection Screen ");
	    	    	   sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,futuredate_DisconnectedOn,Reason,"Y","");
	    	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg);
	    	    	   System.out.println("Disconnection is done");

	    	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	    	   this.record=this.records.get(0);
	    	    	   String Party_id=record.get("CUSTOMER_ID");
	    	    	   System.out.println(Party_id);
	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println(Qstatus);// need to add message..
	    	    	   if(Qstatus.equalsIgnoreCase(N_Status))
	    	    	   {
	    	    		   System.out.println("PR-3988 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-3988 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-3988",driver);
	    	    	   System.out.println("Test__PR-3988___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }


	             
	             @Test(description="Disconnecting contract having both Service plan and Hardware plan in different contracts. ")
	             public void PR_3990() throws Exception
	             {
	            	 try
	            	 {
	            		 testLinkConnection.setsTestCase("PR-3990",this);
	            		 System.out.println("PR-3990 Started");
	            		 oNavigate.toCustomerRegistration();
	            		 Thread.sleep(2000);
	            		 CustomerNumber = oCustomerRegistration.NewCustomerRegistration(first_Name,last_Name,"","");
	            		 System.out.println("Customer number is : "+CustomerNumber); 
	            		 Thread.sleep(3000);

	            		 mrnNumber=invTrans.mrnTrans("","1",Inventory_Location,Supplier,currentDate,"N",1,"Y","N","Y");
	            		 System.out.println("Mrn Completed");

	            		 oNavigate.toServiceOrder();
	            		 oWaitTool.implicitWait(100);
	            		 driver.findElement(locApprove).click();
	            		 Thread.sleep(1000);
	            		 oserviceorder.serviceOrder_MulitplePlans(MultiRoomplan,"","",hardwarePlan,1);
	            		 Thread.sleep(3000);
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
	            		 getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	            		 this.records=oDBConnection.fecthRecords(getConnectionNumber);
	            		 this.record=this.records.get(0);
	            		 Connection_Number=record.get("CONNECTION_NUMBER");
	            		 System.out.println("connection Number is "+Connection_Number);

	            		 Thread.sleep(5000);
	            		 oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	            		 Thread.sleep(3000);

	            		 getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	            		 this.records=oDBConnection.fecthRecords(getConnectionNumber);
	            		 this.record=this.records.get(0);
	            		 Connection_Number=record.get("CONNECTION_NUMBER");
	            		 System.out.println("connection Number is "+Connection_Number);

	            		 oNavigate.toDisconnection();
	            		 Thread.sleep(5000);
	            		 logger.info("Successfully Navigated to Disconnection Screen ");
	            		 sResult = oDisconnection.Disconnect_MultipleContracts("",Connection_Number,"","","","");
	            		 driver.findElement(locSaveBtn).click();	
	            		 Thread.sleep(1000);
	            		 String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	            		 System.out.println("Success message is:"+success_Msg);
	            		 System.out.println("Disconnection is done");

	            		 Thread.sleep(10000);
	            		 oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	            		 Thread.sleep(3000);

	            		 String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	            		 this.records=oDBConnection.fecthRecords(Customer_id);
	            		 this.record=this.records.get(0);
	            		 String Party_id=record.get("CUSTOMER_ID");
	            		 System.out.println(Party_id);
	            		 String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4 order by 1 desc ";
	            		 this.records=oDBConnection.fecthRecords(Status);
	            		 this.record=this.records.get(0);
	            		 String Qstatus=record.get("STATUS");
	            		 System.out.println("status of the request is :"+Qstatus+"");// need to add message..
	            		 if(Qstatus.equalsIgnoreCase(C_Status))
	            		 {
	            			 System.out.println("PR-3990 Pass");
	            			 testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	            			 testLinkConnection.setsNotes("Activating customer with hardware plan and services plan in different Contract");
	            		 }
	            		 else
	            		 {
	            			 System.out.println("PR-3990 Failed");
	            			 testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	            			 testLinkConnection.setsNotes(" Customer is in pending state");
	            		 }
	            	 }
	            	 catch(Exception e)
	            	 {
	            		 captureScreenShot.takeScreenShot("PR-3990",driver);
	            		 System.out.println("Test__PR-3990___%Failed");
	            		 testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	            		 testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	            	 }
	             }

	             
	             
	             @Test(description="Disconnecting a Multi Room Contract")
	    	     public void PR_3991() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-3991",this);
	    	    	   System.out.println("PR-3991 Started");
	    	    	   Thread.sleep(2000);
	    	    	   oNavigate.toCustomerRegistration();
	    	    	   Thread.sleep(2000);

	    	    	   CustomerNumber = oCustomerRegistration.NewCustomerRegistration(first_Name,last_Name,"","");
	    	    	   System.out.println("Customer number is : "+CustomerNumber); 
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
	    	    	   oserviceorder.BookPlan("","","","",MultiRoomplan,"","");
	    	    	   Thread.sleep(3000);
	    	    	   String SROutput=driver.findElement(By.id("ctl00__infoSpan")).getText();
	    	    	   Thread.sleep(2000);
	    	    	   System.out.println("validation msg= "+SROutput);
	    	    	   Thread.sleep(5000);

	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	    	   Thread.sleep(3000);

	    	    	   oNavigate.toDisconnection();
	    	    	   Thread.sleep(5000);
	    	    	   logger.info("Successfully Navigated to Disconnection Screen ");
	    	    	   sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	    	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg);
	    	    	   System.out.println("Disconnection is done");

	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	    	   Thread.sleep(3000);

	    	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	    	   this.record=this.records.get(0);
	    	    	   String Party_id=record.get("CUSTOMER_ID");
	    	    	   System.out.println(Party_id);
	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println(Qstatus);// need to add message..
	    	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	    	   {
	    	    		   System.out.println("PR-3991 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-3991 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-3991",driver);
	    	    	   System.out.println("Test__PR-3991___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }


	             
	             @Test(description="Disconnecting Contract with Is_Bulk as Y ")
	    	     public void PR_3992() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-3992",this);
	    	    	   System.out.println("PR-3992 Started");
	    	    	   Thread.sleep(2000);
	    	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation_WithBulk();
	    	    	   System.out.println("Customer number is : "+CustomerNumber); 
	    	    	   Thread.sleep(3000);

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
	    	    	   System.out.println("waiting is completed and now going  to provisiong request screen ");
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	    	   Thread.sleep(3000);

	    	    	   oNavigate.toDisconnection();
	    	    	   Thread.sleep(5000);
	    	    	   logger.info("Successfully Navigated to Disconnection Screen ");
	    	    	   sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	    	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg);
	    	    	   System.out.println("Disconnection is done");

	    	    	   Thread.sleep(80000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	    	   Thread.sleep(3000);
	    	    	  
	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println(Qstatus);// need to add message..
	    	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	    	   {
	    	    		   System.out.println("PR-3992 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-3992 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-3992",driver);
	    	    	   System.out.println("Test__PR-3992___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }


	             
	             @Test(description="Disconnecting contract having no prov services")
	    	     public void PR_3993() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-3993",this);
	    	    	   System.out.println("PR-3993 Started");
	    	    	   Thread.sleep(2000);
	    	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
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

	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println(Qstatus);
	    	    	   if(Qstatus.equalsIgnoreCase(N_Status))
	    	    	   {
	    	    		   System.out.println("PR-3993 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-3993 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-3993",driver);
	    	    	   System.out.println("Test__PR-3993___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }


	             
	             @Test(description="Disconnecting Contract through Auto Expire Job ")
	    	     public void PR_3994() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-3994",this);
	    	    	   System.out.println("PR-3994 Started");
	    	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", backDated, backDated,contract_validity,strPlanCode,"",billing_Frequency);
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

	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println(Qstatus);
	    	    	   if(Qstatus.equalsIgnoreCase(N_Status))
	    	    	   {
	    	    		   System.out.println("PR-3994 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-3994 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-3994",driver);
	    	    	   System.out.println("Test__PR-3994___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }


	             
	             @Test(description="Disconnecting Contract who is in Pending stage ")
	    	     public void PR_3995() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-3995",this);
	    	    	   System.out.println("PR-3995 Started");
	    	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
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

	    	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	    	   this.record=this.records.get(0);
	    	    	   String Party_id=record.get("CUSTOMER_ID");
	    	    	   System.out.println(Party_id);
	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println(Qstatus);// need to add message..
	    	    	   if(Qstatus.equalsIgnoreCase(N_Status))
	    	    	   {
	    	    		   System.out.println("PR-3995 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-3995 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-3995",driver);
	    	    	   System.out.println("Test__PR-3995___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }


	             
	             @Test(description="Disconnecting Contract after Service Downgrade ")
	    	     public void PR_3996() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-3996",this);
	    	    	   System.out.println("PR-3996 Started");
	    	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
	    	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	    	   Thread.sleep(7000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   oNavigate.toCollection();
	    	    	   oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
	    	    	   Thread.sleep(5000);
	    	    	   oCollections.collectionGrid("", "","Service Downgrade", "", "Save");
	    	    	   System.out.println("Service Downgrade done");
	    	    	   Thread.sleep(50000);
	    	    	   
	    	    	   oNavigate.toDisconnection();
	    	    	   Thread.sleep(5000);
	    	    	   logger.info("Successfully Navigated to Disconnection Screen ");
	    	    	   System.out.println("Successfully Navigated to Disconnection Screen");
	    	    	   Thread.sleep(3000);
	    	    	   driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvDisconnection_ctl02_chkapprove']")).click();
	    	    	   Thread.sleep(2000);
	    	    	   System.out.println("approve done");
	    	    	   driver.findElement(locbtnsave).click();
	    	    	   System.out.println("Save clicked");

	    	    	   Thread.sleep(2000);
	    	    	   Alert alert=driver.switchTo().alert();
	    	    	   alert.accept();
	    	    	   Thread.sleep(2000);
	    	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg);
	    	    	   System.out.println("Disconnection is done");

	    	    	   Thread.sleep(10000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	    	   this.record=this.records.get(0);
	    	    	   String Party_id=record.get("CUSTOMER_ID");
	    	    	   System.out.println(Party_id);
	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"'and BIZ_PROCESS_ID=4  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println(Qstatus);
	    	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	    	   {
	    	    		   System.out.println("PR-3996 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes("Disconnection after Hardware Replacement");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-3996 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-3996",driver);
	    	    	   System.out.println("Test__PR-3996___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }


	             
	             @Test(description="Disconnection in Staging ")
	    	     public void PR_3998() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-3998",this);
	    	    	   System.out.println("PR-3998 Started");
	    	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	    	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	    	   
	    	    	   Thread.sleep(3000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	    	   Thread.sleep(3000);
	    	    	   oNavigate.toDisconnection();
	    	    	   Thread.sleep(5000);
	    	    	   oNavigate.toDisconnection();
	    	    	   Thread.sleep(5000);
	    	    	   logger.info("Successfully Navigated to Disconnection Screen ");
	    	    	   sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"","");
	    	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg);
	    	    	   System.out.println("Disconnection is done");

	    	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	    	   this.record=this.records.get(0);
	    	    	   String Party_id=record.get("CUSTOMER_ID");
	    	    	   System.out.println(Party_id);
	    	    	   getDisconnectionCount="select COUNT(*) as COUNT from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=4  order by 1 desc ";
						this.records=oDBConnection.fecthRecords(getDisconnectionCount);
						this.record=this.records.get(0);
						Disconnection_Count=record.get("COUNT");
						DisconnectionCount = Integer.parseInt(Disconnection_Count);
						System.out.println("connectionCount is "+DisconnectionCount);
						if(DisconnectionCount==0)
						{
							System.out.println("PR-3998 Pass");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
							testLinkConnection.setsNotes("Disconnection in Staging");
						}
						else
						{
							System.out.println("PR-3998 Failed");
							testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
							testLinkConnection.setsNotes("Disconnection Is Not in Staging");
						}
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-3998",driver);
	    	    	   System.out.println("Test__PR-3998___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }

	             
	             
	             @Test(description="Pre Requisites -- Reconnection  ")
	    	     public void PR_3999() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-3999",this);
	    	    	   System.out.println("PR-3999 Started");
	    	    	   Thread.sleep(3000);
	    	    	   oNavigate.toReconnection();
	    	    	   Thread.sleep(5000);
	    	    	   title=driver.getTitle();
	    	    	   System.out.println("Success message is:"+title);
	    	    	   if(title.equalsIgnoreCase("Contract Reconnection"))
	    	    	   {
	    	    		   System.out.println("PR-3999 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-3999 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-3999",driver);
	    	    	   System.out.println("Test__PR-3999___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }


	             
	             @Test(description="Reconnecting disconeected contract from Reconnection Screen")
	    	     public void PR_4000() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-4000",this);
	    	    	   System.out.println("PR-4000 Started");
	    	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	    	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	    	   Thread.sleep(5000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();           
	    	    	   Thread.sleep(3000);

	    	    	   oNavigate.toDisconnection();
	    	    	   Thread.sleep(5000);
	    	    	   logger.info("Successfully Navigated to Disconnection Screen ");
	    	    	   sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	    	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg);
	    	    	   System.out.println("Disconnection is done");
	    	    	   Thread.sleep(5000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();           

	    	    	   oNavigate.toReconnection();
	    	    	   oWaitTool.implicitWait(10);
	    	    	   oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
	    	    	   oWaitTool.implicitWait(50);
	    	    	   String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg1);
	    	    	   System.out.println("Reconnection is done");
                       oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	    	   
	    	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	    	   this.record=this.records.get(0);
	    	    	   String Party_id=record.get("CUSTOMER_ID");
	    	    	   System.out.println(Party_id);
	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println(Qstatus);// need to add message..
	    	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	    	   {
	    	    		   System.out.println("PR-4000 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-4000 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-4000",driver);
	    	    	   System.out.println("Test__PR-4000___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }


	             
	             @Test(description="Reconnecting disconeected contract from Reconnection Screen")
	    	     public void PR_4001() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-4001",this);
	    	    	   System.out.println("PR-4001 Started");
	    	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", backDated, backDated,"",strPlanCode,"Y",billing_Frequency);
	    	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	    	   Thread.sleep(5000);

	    	    	   oNavigate.toProvisioningRequest();
	    	    	   Thread.sleep(8000);
	    	    	   oProvisioningRequests.ProvisioningRequestsScreen(CustomerNumber,"",backDated,"","","",New,Sent,"","Search","Save");
	    	    	   String successMsg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+successMsg1 );
	    	    	   Thread.sleep(2000);
	    	    	   driver.navigate().refresh();
	    	    	   oProvisioningRequests.ProvisioningRequestsScreen(CustomerNumber,"",backDated,"","","",Sent,Accept,"","Search","Save");
	    	    	   Thread.sleep(3000);

	    	    	   oNavigate.toDisconnection();
	    	    	   Thread.sleep(5000);
	    	    	   logger.info("Successfully Navigated to Disconnection Screen ");
	    	    	   sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,backDated,Reason,"Y","");
	    	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg);
	    	    	   System.out.println("Disconnection is done");
	    	    	   Thread.sleep(5000);
	    	    	   oNavigate.toProvisioningRequest();
	    	    	   Thread.sleep(7000);

	    	    	   oProvisioningRequests.ProvisioningRequestsScreen(CustomerNumber,"",backDated,"","","",New,Sent,"","Search","Save");
	    	    	   String successMsg2 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+successMsg2 );
	    	    	   Thread.sleep(2000);
	    	    	   driver.navigate().refresh();
	    	    	   oProvisioningRequests.ProvisioningRequestsScreen(CustomerNumber,"",backDated,"","","",Sent,Accept,"","Search","Save");

	    	    	   Thread.sleep(5000);
	    	    	   oNavigate.toReconnection();
	    	    	   oWaitTool.implicitWait(10);
	    	    	   oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
	    	    	   oWaitTool.implicitWait(50);
	    	    	   String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg1);
	    	    	   System.out.println("Reconnection is done");

	    	    	   Thread.sleep(5000);
	    	    	   oNavigate.toProvisioningRequest();
	    	    	   Thread.sleep(5000);
	    	    	   oProvisioningRequests.ProvisioningRequestsScreen(CustomerNumber,"",backDated,"","","",New,Sent,"","Search","Save");
	    	    	   String successMsg3 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+successMsg3 );
	    	    	   Thread.sleep(2000);
	    	    	   driver.navigate().refresh();
	    	    	   oProvisioningRequests.ProvisioningRequestsScreen(CustomerNumber,"",backDated,"","","",Sent,Accept,"","Search","Save");

	    	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	    	   this.record=this.records.get(0);
	    	    	   String Party_id=record.get("CUSTOMER_ID");
	    	    	   System.out.println(Party_id);
	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println(Qstatus);
	    	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	    	   {
	    	    		   System.out.println("PR-4001 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-4001 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-4001",driver);
	    	    	   System.out.println("Test__PR-4001___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }


	             
	             @Test(description="Reconnecting disconeected contract from Reconnection Screen")
	    	     public void PR_4002() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-4002",this);
	    	    	   System.out.println("PR-4002 Started");
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

	    	    	   oProvisioningRequests.ProvisioningRequestsScreen(CustomerNumber,"",backDated,"","","",New,Sent,"","Search","Save");
	    	    	   String successMsg3 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+successMsg3 );
	    	    	   Thread.sleep(2000);
	    	    	   driver.navigate().refresh();
	    	    	   oProvisioningRequests.ProvisioningRequestsScreen(CustomerNumber,"",backDated,"","","",Sent,Accept,"","Search","Save");

	    	    	   Thread.sleep(5000);
	    	    	   oNavigate.toReconnection();
	    	    	   oWaitTool.implicitWait(10);
	    	    	   oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
	    	    	   oWaitTool.implicitWait(50);
	    	    	   String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg1);
	    	    	   System.out.println("Reconnection is done");

	    	    	   Thread.sleep(5000);
	    	    	   oNavigate.toProvisioningRequest();
	    	    	   Thread.sleep(5000);

	    	    	   oProvisioningRequests.ProvisioningRequestsScreen(CustomerNumber,"",backDated,"","","",New,Sent,"","Search","Save");
	    	    	   String successMsg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+successMsg1 );
	    	    	   Thread.sleep(2000);
	    	    	   driver.navigate().refresh();
	    	    	   oProvisioningRequests.ProvisioningRequestsScreen(CustomerNumber,"",backDated,"","","",Sent,Accept,"","Search","Save");

	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println(Qstatus);// need to add message..
	    	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	    	   {
	    	    		   System.out.println("PR-4002 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-4002 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-4002",driver);
	    	    	   System.out.println("Test__PR-4002___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }


	             
	             // Need to do the Changes related to that.......
	             @Test(description="Reconnecting Contract after Smart Card Change(Primary Card) ")
	    	     public void PR_4003() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-4003",this);
	    	    	   System.out.println("PR-4003 Started");
	    	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
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
	    	    	   
	    	    	   Thread.sleep(3000);
	    	    	   oNavigate.toDisconnection();
	    	    	   Thread.sleep(5000);
	    	    	   logger.info("Successfully Navigated to Disconnection Screen ");
	    	    	   sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	    	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg);
	    	    	   System.out.println("Disconnection is done");

	    	    	   Thread.sleep(7000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	    	   
	    	    	   Thread.sleep(5000);
	    	    	   oNavigate.toReconnection();
	    	    	   oWaitTool.implicitWait(10);
	    	    	   oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
	    	    	   oWaitTool.implicitWait(50);
	    	    	   String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg1);
	    	    	   System.out.println("Reconnection is done");
	    	    	   Thread.sleep(7000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	    	   this.record=this.records.get(0);
	    	    	   String Party_id=record.get("CUSTOMER_ID");
	    	    	   System.out.println(Party_id);
	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println(Qstatus);// need to add message..
	    	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	    	   {
	    	    		   System.out.println("PR-4003 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-4003 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-4003",driver);
	    	    	   System.out.println("Test__PR-4003___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }


	             
	             @Test(description="Reconnecting Contract after Set-top-box Change(Primary Card)")
	    	     public void PR_4004() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-4004",this);
	    	    	   System.out.println("PR-4004 Started");
	    	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("Y", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	    	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	    	   Thread.sleep(5000);
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
	    	    	   
	    	    	   Thread.sleep(3000);
	    	    	   oNavigate.toDisconnection();
	    	    	   Thread.sleep(5000);
	    	    	   logger.info("Successfully Navigated to Disconnection Screen ");
	    	    	   sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	    	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg);
	    	    	   System.out.println("Disconnection is done");

	    	    	   Thread.sleep(7000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	    	   
	    	    	   Thread.sleep(5000);
	    	    	   oNavigate.toReconnection();
	    	    	   oWaitTool.implicitWait(10);
	    	    	   oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
	    	    	   oWaitTool.implicitWait(50);
	    	    	   String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg1);
	    	    	   System.out.println("Reconnection is done");

	    	    	   Thread.sleep(7000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	    	   this.record=this.records.get(0);
	    	    	   String Party_id=record.get("CUSTOMER_ID");
	    	    	   System.out.println(Party_id);
	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println(Qstatus);// need to add message..
	    	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	    	   {
	    	    		   System.out.println("PR-4004 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-4004 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-4004",driver);
	    	    	   System.out.println("Test__PR-4004___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }


	             
	             @Test(description="Reconnecting Contract after Hardware Replacement ")
	    	     public void PR_4005() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-4005",this);
	    	    	   System.out.println("PR-4005 Started");
	    	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Sale", "", "","",strPlanCode,"Y",billing_Frequency);
	    	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	    	   System.out.println("Contract Number is : "+ContractNumber);

	    	    	   Thread.sleep(5000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   Thread.sleep(3000);
	    	    	   oNavigate.toServiceOrder();
	    	    	   oWaitTool.implicitWait(100);
	    	    	   driver.findElement(locModify).click();
	    	    	   Thread.sleep(2000);
	    	    	   driver.findElement(locApprove).click();
	    	    	   Thread.sleep(1000);
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

	    	    	   Thread.sleep(7000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   oNavigate.toHardwareReplacement();
	    	    	   oWaitTool.implicitWait(20);		        	  
	    	    	   oHardwareReplacement.HardwareReplacement_Header(CustomerNumber,"", "","",ReasonMRPL,"","","","","","","","","","","New");
	    	    	   oHardwareReplacement.HardwareReplacement_Item_Dtls(Item1,"",StatusMRPL,Item1,OperationalEntity,Location,OrderType,1);
	    	    	   driver.findElement(locSaveBtn).click();

	    	    	   Thread.sleep(3000);
	    	    	   oNavigate.toDisconnection();
	    	    	   Thread.sleep(5000);
	    	    	   logger.info("Successfully Navigated to Disconnection Screen ");
	    	    	   sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	    	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg);
	    	    	   System.out.println("Disconnection is done");

	    	    	   Thread.sleep(7000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   Thread.sleep(5000);
	    	    	   oNavigate.toReconnection();
	    	    	   oWaitTool.implicitWait(10);
	    	    	   oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
	    	    	   oWaitTool.implicitWait(50);
	    	    	   String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg1);
	    	    	   System.out.println("Reconnection is done");

	    	    	   Thread.sleep(7000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	    	   this.record=this.records.get(0);
	    	    	   String Party_id=record.get("CUSTOMER_ID");
	    	    	   System.out.println(Party_id);
	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"'and BIZ_PROCESS_ID=4  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println(Qstatus);// need to add message..
	    	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	    	   {
	    	    		   System.out.println("PR-4005 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes("Reconnecting Contract after Hardware Replacement");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-4005 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Reconnecting Contract after Hardware Replacement Is pending");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-4005",driver);
	    	    	   System.out.println("Test__PR-4005___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }


	             
	             @Test(description="Reconnecting Suspended customer after Disconnection.")
	    	     public void PR_4006() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-4006",this);
	    	    	   System.out.println("PR-4006 Started");
	    	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
	    	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	    	   Thread.sleep(7000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   oNavigate.toCollection();
	    	    	   oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
	    	    	   Thread.sleep(5000);
	    	    	   oCollections.collectionGrid("", "","Suspend Provisioning", "", "Save");
	    	    	   Thread.sleep(75000);

	    	    	   oNavigate.toDisconnection();
	    	    	   Thread.sleep(15000);
	    	    	   logger.info("Successfully Navigated to Disconnection Screen ");
	    	    	   System.out.println("Successfully Navigated to Disconnection Screen");
	    	    	   Thread.sleep(3000);
	    	    	   driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvDisconnection_ctl02_chkapprove']")).click();
	    	    	   Thread.sleep(2000);
	    	    	   System.out.println("approve done");
	    	    	   driver.findElement(locbtnsave).click();
	    	    	   System.out.println("Save clicked");
	    	    	   Thread.sleep(2000);
	    	    	   Alert alert=driver.switchTo().alert();
	    	    	   alert.accept();
	    	    	   Thread.sleep(2000);
	    	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg);
	    	    	   System.out.println("Disconnection is done");

	    	    	   Thread.sleep(10000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   Thread.sleep(5000);
	    	    	   oNavigate.toReconnection();
	    	    	   oWaitTool.implicitWait(10);
	    	    	   oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
	    	    	   oWaitTool.implicitWait(50);
	    	    	   String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg1);
	    	    	   System.out.println("Reconnection is done");

	    	    	   Thread.sleep(7000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	    	   this.record=this.records.get(0);
	    	    	   String Party_id=record.get("CUSTOMER_ID");
	    	    	   System.out.println(Party_id);
	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"'and BIZ_PROCESS_ID=7  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println(Qstatus);// need to add message..
	    	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	    	   {
	    	    		   System.out.println("PR-4006 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes("Disconnection after Hardware Replacement");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-4006 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-4006",driver);
	    	    	   System.out.println("Test__PR-4006___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }

	             
	             
	             @Test(description="Reconnecting Renewed Contract after disconnection")
	    	     public void PR_4008() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-4008",this);
	    	    	   System.out.println("PR-4008 Started");
	    	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
	    	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	    	   Thread.sleep(7000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	    	   
	    	    	   oNavigate.toRenewal();
	    	    	   RenewalValidation =oRenewal.RenewalCustomer("",ContractNumber,"Y","","",contract_validity,"Save");
	    	    	   System.out.println("Success message is:"+RenewalValidation);
	    	    	   
	    	    	   Thread.sleep(3000);
	    	    	   oNavigate.toDisconnection();
	    	    	   Thread.sleep(5000);
	    	    	   logger.info("Successfully Navigated to Disconnection Screen ");
	    	    	   sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	    	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg);
	    	    	   System.out.println("Disconnection is done");
	    	    
	    	    	   Thread.sleep(10000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	    	   
	    	    	   Thread.sleep(5000);
	    	    	   oNavigate.toReconnection();
	    	    	   oWaitTool.implicitWait(10);
	    	    	   oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
	    	    	   oWaitTool.implicitWait(50);
	    	    	   String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg1);
	    	    	   System.out.println("Reconnection is done");

	    	    	   Thread.sleep(7000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	
	    	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	    	   this.record=this.records.get(0);
	    	    	   String Party_id=record.get("CUSTOMER_ID");
	    	    	   System.out.println(Party_id);
	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"'and BIZ_PROCESS_ID=7  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println(Qstatus);// need to add message..
	    	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	    	   {
	    	    		   System.out.println("PR-4008 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes("Disconnection after Hardware Replacement");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-4008 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-4008",driver);
	    	    	   System.out.println("Test__PR-4008___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }


	             
	             @Test(description="Reconnecting contract for Customer having Custom customer number")
	    	     public void PR_4009() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-4009",this);
	    	    	   System.out.println("PR-4009 Started");
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
	    	    	   String sResult = oserviceorder.BookPlan(CustomerNumber,"","","",strPlanCode,"Y",billing_Frequency);
	    	    	   System.out.println("is:"+sResult);
	    	    	   oWaitTool.implicitWait(30);
	    	    	   Thread.sleep(7000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   oNavigate.toDisconnection();
	    	    	   Thread.sleep(5000);
	    	    	   logger.info("Successfully Navigated to Disconnection Screen ");
	    	    	   sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	    	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg);
	    	    	   System.out.println("Disconnection is done");
	    	    	   Thread.sleep(5000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	    	   
	    	    	   Thread.sleep(5000);
	    	    	   oNavigate.toReconnection();
	    	    	   oWaitTool.implicitWait(10);
	    	    	   oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
	    	    	   oWaitTool.implicitWait(50);
	    	    	   String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg1);
	    	    	   System.out.println("Reconnection is done");

	    	    	   Thread.sleep(7000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	    	   
	    	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	    	   this.record=this.records.get(0);
	    	    	   String Party_id=record.get("CUSTOMER_ID");
	    	    	   System.out.println(Party_id);
	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println(Qstatus);// need to add message..
	    	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	    	   {
	    	    		   System.out.println("PR-4009 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes(" Customer is Actvated with out hardware  ");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-4009 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-4009",driver);
	    	    	   System.out.println("Test__PR-4009___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	       finally 
	    	       {
	    	    	   invTrans.profile("Auto Generate Customer Number", "true","N");  
	    	    	   relogin();
	    	       }
	    	     }


	             
	             @Test(description="Reconnecting Future Dated Contract ")
	    	     public void PR_4010() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-4010",this);
	    	    	   System.out.println("PR-4010 Started");
	    	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", futureDate, futureDate,"",strPlanCode,"Y",billing_Frequency);
	    	    	   System.out.println("Customer Number is : "+CustomerNumber);
	    	    	   Thread.sleep(3000);
	    	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	    	   Thread.sleep(5000);
	    	    	  
	    	    	   oNavigate.toDisconnection();
	    	    	   Thread.sleep(5000);
	    	    	   logger.info("Successfully Navigated to Disconnection Screen ");
	    	    	   sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,futuredate_DisconnectedOn,Reason,"Y","");
	    	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg);
	    	    	   System.out.println("Disconnection is done");
	    	    	   
	    	    	   Thread.sleep(5000);
	    	    	   oNavigate.toReconnection();
	    	    	   oWaitTool.implicitWait(10);
	    	    	   oReconnection.Reconnect_SingleContract("",futuredate_Reconnection,"",ContractNumber,"Y","");
	    	    	   oWaitTool.implicitWait(50);
	    	    	   String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg1);
	    	    	   System.out.println("Reconnection is done");

	    	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	    	   this.record=this.records.get(0);
	    	    	   String Party_id=record.get("CUSTOMER_ID");
	    	    	   System.out.println(Party_id);
	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println(Qstatus);// need to add message..
	    	    	   if(Qstatus.equalsIgnoreCase(N_Status))
	    	    	   {
	    	    		   System.out.println("PR-4010 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes("Reconnecting Future Dated Contract ");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-4010 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Reconnecting Future Dated Contract Is failed");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-4010",driver);
	    	    	   System.out.println("Test__PR-4010___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }


	             
	             @Test(description="Reconnecting two contracts at a time which are active on single cards ")
	    	     public void PR_4011() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-4011",this);
	    	    	   System.out.println("PR-4011 Started");
	    	    	   Thread.sleep(2000);
	    	    	   oNavigate.toCustomerRegistration();
	    	    	   Thread.sleep(2000);

	    	    	   CustomerNumber = oCustomerRegistration.NewCustomerRegistration(first_Name,last_Name,"","");
	    	    	   System.out.println("Customer number is : "+CustomerNumber); 
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
	    	    	   Thread.sleep(2000);
	    	    	   oserviceorder.serviceOrder_MulitplePlans("PLAN1SERV1","","","",services_count);
	    	    	   Thread.sleep(4000);
	    	    	   oserviceorder.serviceOrder_MulitplePlans("NAGRA150","","","",services_count);
	    	    	   System.out.println("Out of method");
	    	    	   driver.findElement(locSaveBtn).click();

	    	    	   Thread.sleep(5000);
	    	    	   oNavigate.toHardwareAssociation();
	    	    	   Thread.sleep(2000);
	    	    	   oHardwareAssociation.NonMultiConnection(CustomerNumber, "MultipleContractWithSameHardware");
	    	    	   Thread.sleep(60000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	    	    	   this.records=oDBConnection.fecthRecords(getConnectionNumber);
	    	    	   this.record=this.records.get(0);
	    	    	   Connection_Number=record.get("CONNECTION_NUMBER");
	    	    	   System.out.println("connection Number is "+Connection_Number);

	    	    	   oNavigate.toDisconnection();
	    	    	   Thread.sleep(5000);
	    	    	   logger.info("Successfully Navigated to Disconnection Screen ");
	    	    	   sResult = oDisconnection.Disconnect_MultipleContracts("",Connection_Number,"","","Y","");
	    	    	   driver.findElement(locSaveBtn).click();	
	    	    	   Thread.sleep(1000);
	    	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg);
	    	    	   System.out.println("Disconnection is done");
	    	    	   Thread.sleep(70000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   Thread.sleep(5000);
	    	    	   oNavigate.toReconnection();
	    	    	   oWaitTool.implicitWait(10);
	    	    	   oReconnection.Reconnect_MultipleContract("",Connection_Number, "", "", "Y", "");
	    	    	   oWaitTool.implicitWait(50);
	    	    	   driver.findElement(locSaveBtn).click();
	    	    	   String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg1);
	    	    	   System.out.println("Reconnection is done");

	    	    	   Thread.sleep(70000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	    	   Thread.sleep(3000);

	    	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	    	   this.record=this.records.get(0);
	    	    	   String Party_id=record.get("CUSTOMER_ID");
	    	    	   System.out.println(Party_id);
	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=87 order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println("status of the request is :"+Qstatus+"");
	    	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	    	   {
	    	    		   System.out.println("PR-4011 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes(" Future Dated Reconnection");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-4011 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Future Dated Reconnection  is Failed");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-4011",driver);
	    	    	   System.out.println("Test__PR-4011___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }

	             
	             
	             @Test(description="Reconnecting two contracts at a time which are active on different cards ")
	    	     public void PR_4012() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-4012",this);
	    	    	   System.out.println("PR-4012 Started");
	    	    	   oNavigate.toCustomerRegistration();
	    	    	   Thread.sleep(2000);

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

	    	    	   oNavigate.toDisconnection();
	    	    	   Thread.sleep(5000);
	    	    	   logger.info("Successfully Navigated to Disconnection Screen ");
	    	    	   sResult = oDisconnection.Disconnect_MultipleContracts("",Connection_Number,"","","","");
	    	    	   driver.findElement(locSaveBtn).click();	
	    	    	   Thread.sleep(1000);
	    	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg);
	    	    	   System.out.println("Disconnection is done");

	    	    	   Thread.sleep(5000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   Thread.sleep(5000);
	    	    	   oNavigate.toReconnection();
	    	    	   oWaitTool.implicitWait(10);
	    	    	   oReconnection.Reconnect_MultipleContract("",Connection_Number, "", "", "Y", "");
	    	    	   oWaitTool.implicitWait(50);
	    	    	   driver.findElement(locSaveBtn).click();
	    	    	   String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg1);
	    	    	   System.out.println("Reconnection is done");

	    	    	   Thread.sleep(10000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	    	   Thread.sleep(3000);

	    	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	    	   this.record=this.records.get(0);
	    	    	   String Party_id=record.get("CUSTOMER_ID");
	    	    	   System.out.println(Party_id);
	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=87 order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println("status of the request is :"+Qstatus+"");
	    	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	    	   {
	    	    		   System.out.println("PR-4012 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes(" Future Dated Reconnection");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-4012 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Future Dated Reconnection  is Failed");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-4012",driver);
	    	    	   System.out.println("Test__PR-4012___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }

	             
	             
	             @Test(description=" Future Dated Reconnection ")
	    	     public void PR_4013() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-4013",this);
	    	    	   System.out.println("PR-4013 Started");
	    	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	    	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	    	   Thread.sleep(5000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   Thread.sleep(3000);
	    	    	   oNavigate.toDisconnection();
	    	    	   Thread.sleep(5000);
	    	    	   logger.info("Successfully Navigated to Disconnection Screen ");
	    	    	   sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	    	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg);
	    	    	   System.out.println("Disconnection is done");
	    	    	   Thread.sleep(5000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	    	   
	    	    	   Thread.sleep(2000);
	    	    	   oNavigate.toReconnection();
	    	    	   oWaitTool.implicitWait(10);
	    	    	   oReconnection.Reconnect_SingleContract("",futuredate_Reconnection,"",ContractNumber,"Y","");
	    	    	   oWaitTool.implicitWait(50);
	    	    	   String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg1);
	    	    	   System.out.println("Reconnection is done");
	    	    	   
	    	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	    	   this.record=this.records.get(0);
	    	    	   String Party_id=record.get("CUSTOMER_ID");
	    	    	   System.out.println(Party_id);
	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println(Qstatus);// need to add message..
	    	    	   if(Qstatus.equalsIgnoreCase(N_Status))
	    	    	   {
	    	    		   System.out.println("PR-4013 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes(" Future Dated Reconnection");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-4013 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Future Dated Reconnection  is Failed");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-4013",driver);
	    	    	   System.out.println("Test__PR-4013___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }


	             
	             @Test(description="Reconnecting contracts which has Service Plan and Hardware Plan")
	             public void PR_4015() throws Exception
	 	          {
	 	          try
	 	           {
	 	        	  testLinkConnection.setsTestCase("PR-4015",this);
	 	        	  System.out.println("PR-4015 Started");
	 	        	  oNavigate.toCustomerRegistration();
	 	        	  Thread.sleep(2000);
	 	        	  CustomerNumber = oCustomerRegistration.NewCustomerRegistration(first_Name,last_Name,"","");
	 	        	  System.out.println("Customer number is : "+CustomerNumber); 
	 	        	  Thread.sleep(3000);

	 	        	  mrnNumber=invTrans.mrnTrans("","1",Inventory_Location,Supplier,currentDate,"N",1,"Y","N","Y");
	 	        	  System.out.println("Mrn Completed");

	 	        	  oNavigate.toServiceOrder();
	 	        	  oWaitTool.implicitWait(100);
	 	        	  driver.findElement(locApprove).click();
	 	        	  Thread.sleep(1000);
	 	        	  oserviceorder.serviceOrder_MulitplePlans(MultiRoomplan,"","",hardwarePlan,1);
	 	        	  Thread.sleep(3000);
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
	 	        	  getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	 	        	  this.records=oDBConnection.fecthRecords(getConnectionNumber);
	 	        	  this.record=this.records.get(0);
	 	        	  Connection_Number=record.get("CONNECTION_NUMBER");
	 	        	  System.out.println("connection Number is "+Connection_Number);

	 	        	  Thread.sleep(5000);
	 	        	  oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	 	        	  Thread.sleep(3000);

	 	        	  getConnectionNumber="select CONNECTION_NUMBER from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
	 	        	  this.records=oDBConnection.fecthRecords(getConnectionNumber);
	 	        	  this.record=this.records.get(0);
	 	        	  Connection_Number=record.get("CONNECTION_NUMBER");
	 	        	  System.out.println("connection Number is "+Connection_Number);

	 	        	  oNavigate.toDisconnection();
	 	        	  Thread.sleep(5000);
	 	        	  logger.info("Successfully Navigated to Disconnection Screen ");
	 	        	  sResult = oDisconnection.Disconnect_MultipleContracts("",Connection_Number,"","","","");
	 	        	  driver.findElement(locSaveBtn).click();	
	 	        	  Thread.sleep(1000);
	 	        	  String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	 	        	  System.out.println("Success message is:"+success_Msg);
	 	        	  System.out.println("Disconnection is done");

	 	        	  Thread.sleep(10000);
	 	        	  oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	 	        	  Thread.sleep(3000);
	 	        	 
	 	        	  Thread.sleep(5000);
	 	        	  oNavigate.toReconnection();
	 	        	  oWaitTool.implicitWait(10);
	 	        	  oReconnection.Reconnect_MultipleContract("",Connection_Number, "", "", "Y", "");
	 	        	  oWaitTool.implicitWait(50);
	 	        	  driver.findElement(locSaveBtn).click();
	 	        	  String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	 	        	  System.out.println("Success message is:"+success_Msg1);
	 	        	  System.out.println("Reconnection is done");

	 	        	  Thread.sleep(10000);
	 	        	  oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	 	        	  Thread.sleep(3000);

	 	        	  String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	 	        	  this.records=oDBConnection.fecthRecords(Customer_id);
	 	        	  this.record=this.records.get(0);
	 	        	  String Party_id=record.get("CUSTOMER_ID");
	 	        	  System.out.println(Party_id);
	 	        	  String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=87 order by 1 desc ";
	 	        	  this.records=oDBConnection.fecthRecords(Status);
	 	        	  this.record=this.records.get(0);
	 	        	  String Qstatus=record.get("STATUS");
	 	        	  System.out.println("status of the request is :"+Qstatus+"");// need to add message..
	 	        	  if(Qstatus.equalsIgnoreCase(C_Status))
	 	        	  {
	 	        		  System.out.println("PR-4015 Pass");
	 	        		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	 	        		  testLinkConnection.setsNotes("Activating customer with hardware plan and services plan in different Contract");
	 	        	  }
	 	        	  else
	 	        	  {
	 	        		  System.out.println("PR-4015 Failed");
	 	        		  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	 	        		  testLinkConnection.setsNotes(" Customer is in pending state");
	 	        	  }
	 	           }
	 	          catch(Exception e)
	 	          {
	 	        	  captureScreenShot.takeScreenShot("PR-4015",driver);
	 	        	  System.out.println("Test__PR-4015___%Failed");
	 	        	  testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	 	        	  testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	 	          }
	 	        }

	             
	             
	             @Test(description="Reconnecting Multi Room contract.")
	    	     public void PR_4016() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-4016",this);
	    	    	   System.out.println("PR-4016 Started");
	    	    	   Thread.sleep(2000);
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
	    	    	   oserviceorder.BookPlan("","","","",MultiRoomplan,"","");
	    	    	   Thread.sleep(3000);
	    	    	   String SROutput=driver.findElement(By.id("ctl00__infoSpan")).getText();
	    	    	   Thread.sleep(2000);
	    	    	   System.out.println("validation msg= "+SROutput);
	    	    	   Thread.sleep(5000);

	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	    	   Thread.sleep(3000);

	    	    	   oNavigate.toDisconnection();
	    	    	   Thread.sleep(5000);
	    	    	   logger.info("Successfully Navigated to Disconnection Screen ");
	    	    	   sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	    	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg);
	    	    	   System.out.println("Disconnection is done");

	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	    	   Thread.sleep(3000);
	    	    	   
	    	    	   oNavigate.toReconnection();
	    	    	   oWaitTool.implicitWait(10);
	    	    	   oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
	    	    	   oWaitTool.implicitWait(50);
	    	    	   String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg1);
	    	    	   System.out.println("Reconnection is done");
	    	    	   
	    	    	   Thread.sleep(8000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	    	   Thread.sleep(3000);
	    	    	   
	    	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	    	   this.record=this.records.get(0);
	    	    	   String Party_id=record.get("CUSTOMER_ID");
	    	    	   System.out.println(Party_id);
	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println(Qstatus);
	    	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	    	   {
	    	    		   System.out.println("PR-4016 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes(" Reconnecting Multi Room contract.");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-4016 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Reconnecting Multi Room contract is in Pending");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-4016",driver);
	    	    	   System.out.println("Test__PR-4016___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }

	             
	             
	             @Test(description="Reconnecting Contract with is_bulk as Y ")
	    	     public void PR_4017() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-4017",this);
	    	    	   System.out.println("PR-4017 Started");
	    	    	   Thread.sleep(2000);
	    	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation_WithBulk();
	    	    	   System.out.println("Customer number is : "+CustomerNumber); 
	    	    	   Thread.sleep(3000);

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
	    	    	   System.out.println("waiting is completed and now going  to provisiong request screen ");
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	    	   Thread.sleep(3000);

	    	    	   oNavigate.toDisconnection();
	    	    	   Thread.sleep(5000);
	    	    	   logger.info("Successfully Navigated to Disconnection Screen ");
	    	    	   sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	    	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg);
	    	    	   System.out.println("Disconnection is done");
	    	    	   Thread.sleep(70000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	    	   Thread.sleep(3000);
	    	    	   
	    	    	   oNavigate.toReconnection();
	    	    	   oWaitTool.implicitWait(10);
	    	    	   oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
	    	    	   oWaitTool.implicitWait(50);
	    	    	   String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg1);
	    	    	   System.out.println("Reconnection is done");
	    	    	   
	    	    	   Thread.sleep(70000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	    	   Thread.sleep(3000);
	    	    	   
	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println(Qstatus);
	    	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	    	   {
	    	    		   System.out.println("PR-4017 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes(" Reconnecting Multi Room contract.");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-4017 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Reconnecting Multi Room contract is in Pending");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-4017",driver);
	    	    	   System.out.println("Test__PR-4017___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }


	             
	             @Test(description="Reconnecting Contract having 'No Prov' Services")
	    	     public void PR_4018() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-4018",this);
	    	    	   System.out.println("PR-4018 Started");
	    	    	   Thread.sleep(2000);
	    	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
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
	    	    	   
	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println(Qstatus);
	    	    	   if(Qstatus.equalsIgnoreCase(N_Status))
	    	    	   {
	    	    		   System.out.println("PR-4018 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes(" Reconnecting Multi Room contract.");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-4018 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Reconnecting Multi Room contract is in Pending");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-4018",driver);
	    	    	   System.out.println("Test__PR-4018___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }


	             
	             @Test(description="Reconnecting Customer who is in Pending ")
	    	     public void PR_4019() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-4019",this);
	    	    	   System.out.println("PR-4019 Started");
	    	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
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
	    	    	   
	    	    	   Thread.sleep(3000);
	    	    	   oNavigate.toReconnection();
	    	    	   oWaitTool.implicitWait(10);
	    	    	   oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
	    	    	   oWaitTool.implicitWait(50);
	    	    	   String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg1);
	    	    	   System.out.println("Reconnection is done");
	    	    	   	    	    	   
	    	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	    	   this.record=this.records.get(0);
	    	    	   String Party_id=record.get("CUSTOMER_ID");
	    	    	   System.out.println(Party_id);
	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println(Qstatus);// need to add message..
	    	    	   if(Qstatus.equalsIgnoreCase(N_Status))
	    	    	   {
	    	    		   System.out.println("PR-4019 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes("Reconnecting Customer who is in Pending ");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-4019 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Reconnecting Customer who is Not  in Pending ");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-4019",driver);
	    	    	   System.out.println("Test__PR-4019___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }


	             
	             @Test(description="Reconnecting Contract after Service Downgrade")
	    	     public void PR_4020() throws Exception
	    	     {
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-4020",this);
	    	    	   System.out.println("PR-4020 Started");
	    	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "",contract_validity,strPlanCode,"Y",billing_Frequency);
	    	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	    	   System.out.println("Contract Number is : "+ContractNumber);
	    	    	   Thread.sleep(7000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   oNavigate.toCollection();
	    	    	   oCollections.collectionHeader(CustomerNumber, "","", "", "", "", "", "");
	    	    	   Thread.sleep(5000);
	    	    	   oCollections.collectionGrid("", "","Service Downgrade", "", "Save");
	    	    	   Thread.sleep(50000);

	    	    	   Thread.sleep(3000);
	    	    	   Thread.sleep(3000);
	    	    	   oNavigate.toDisconnection();
	    	    	   Thread.sleep(5000);
	    	    	   logger.info("Successfully Navigated to Disconnection Screen ");
	    	    	   System.out.println("Successfully Navigated to Disconnection Screen");
	    	    	   Thread.sleep(3000);
	    	    	   driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvDisconnection_ctl02_chkapprove']")).click();
	    	    	   Thread.sleep(2000);
	    	    	   System.out.println("approve done");
	    	    	   driver.findElement(locbtnsave).click();
	    	    	   System.out.println("Save clicked");

	    	    	   Thread.sleep(2000);
	    	    	   Alert alert=driver.switchTo().alert();
	    	    	   alert.accept();
	    	    	   Thread.sleep(2000);
	    	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg);
	    	    	   System.out.println("Disconnection is done");

	    	    	   Thread.sleep(10000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   Thread.sleep(5000);
	    	    	   oNavigate.toReconnection();
	    	    	   oWaitTool.implicitWait(10);
	    	    	   oReconnection.Reconnect_SingleContract("","","",ContractNumber,"Y","");
	    	    	   oWaitTool.implicitWait(50);
	    	    	   String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg1);
	    	    	   System.out.println("Reconnection is done");

	    	    	   Thread.sleep(7000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();

	    	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	    	   this.record=this.records.get(0);
	    	    	   String Party_id=record.get("CUSTOMER_ID");
	    	    	   System.out.println(Party_id);
	    	    	   String Status = "select * from provsion_process_request where party_id= '"+Party_id+"'and BIZ_PROCESS_ID=7  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(Status);
	    	    	   this.record=this.records.get(0);
	    	    	   String Qstatus=record.get("STATUS");
	    	    	   System.out.println(Qstatus);
	    	    	   if(Qstatus.equalsIgnoreCase(C_Status))
	    	    	   {
	    	    		   System.out.println("PR-4020 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes("Disconnection after Hardware Replacement");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-4020 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Status is not active and success message is not displaying");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-4020",driver);
	    	    	   System.out.println("Test__PR-4020___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }

	             
	             
	             @Test(description="Reconnection in Staging ")
	    	     public void PR_4022() throws Exception
	    	     {
	    	       logger.info("Booking a new Contract");
	    	       try
	    	        {
	    	    	   testLinkConnection.setsTestCase("PR-4022",this);
	    	    	   System.out.println("PR-4022 Started");
	    	    	   CustomerNumber=oBasicConfigurationsOfProvisioning.CustomerActvation("N", "Own", "", "","",strPlanCode,"Y",billing_Frequency);
	    	    	   ContractNumber = driver.findElement(locContractNumber).getAttribute("value");
	    	    	   System.out.println("Contract Number is : "+ContractNumber);

	    	    	   Thread.sleep(3000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	    	   Thread.sleep(3000);

	    	    	   oNavigate.toDisconnection();
	    	    	   Thread.sleep(5000);
	    	    	   logger.info("Successfully Navigated to Disconnection Screen ");
	    	    	   sResult = oDisconnection.Disconnect_SingleContract("",ContractNumber,"",Reason,"Y","");
	    	    	   String success_Msg = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg);
	    	    	   System.out.println("Disconnection is done");

	    	    	   Thread.sleep(3000);
	    	    	   oBasicConfigurationsOfProvisioning.ProvisioningRequestsCalling();
	    	    	   Thread.sleep(3000);

	    	    	   oNavigate.toReconnection();
	    	    	   oWaitTool.implicitWait(10);
	    	    	   oReconnection.Reconnect_SingleContract("","","",ContractNumber,"N","");
	    	    	   oWaitTool.implicitWait(50);
	    	    	   String success_Msg1 = driver.findElement(By.xpath(".//*[@id='ctl00__infoSpan']")).getText();
	    	    	   System.out.println("Success message is:"+success_Msg1);
	    	    	   System.out.println("Reconnection is done");

	    	    	   String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
	    	    	   this.records=oDBConnection.fecthRecords(Customer_id);
	    	    	   this.record=this.records.get(0);
	    	    	   String Party_id=record.get("CUSTOMER_ID");
	    	    	   System.out.println(Party_id);
	    	    	   getReconnectionCount="select COUNT(*) as COUNT from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=7  order by 1 desc ";
	    	    	   this.records=oDBConnection.fecthRecords(getReconnectionCount);
	    	    	   this.record=this.records.get(0);
	    	    	   Reconnection_Count=record.get("COUNT");
	    	    	   ReconnectionCount = Integer.parseInt(Reconnection_Count);
	    	    	   System.out.println("ReconnectionCount is "+ReconnectionCount);
	    	    	   if(ReconnectionCount==0)
	    	    	   {
	    	    		   System.out.println("PR-4022 Pass");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
	    	    		   testLinkConnection.setsNotes("Reconnection in Staging");
	    	    	   }
	    	    	   else
	    	    	   {
	    	    		   System.out.println("PR-4022 Failed");
	    	    		   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    		   testLinkConnection.setsNotes("Reconnection is Not in Staging");
	    	    	   }
	    	        }
	    	       catch(Exception e)
	    	       {
	    	    	   captureScreenShot.takeScreenShot("PR-4022",driver);
	    	    	   System.out.println("Test__PR-4022___%Failed");
	    	    	   testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
	    	    	   testLinkConnection.setsNotes("Execution failed due to"+e.getMessage());
	    	       }
	    	     }

	             
	             
	             
}
