package ProvisioningTC;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import mq.seleniumFW.connection.MQDBConnection;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import testlink.api.java.client.TestLinkAPIResults;

import com.BasicConfigurationInventory.InventoryTransactions;
import com.BasicConfigurationsOfServiceOrder.OrderManagementPrivileges;
import com.CustomerCarePageObject.SendEmail;
import com.InventoryAndLogisticsPageObject.HardwareReplacement;
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

public class Prov_Send_Email_TC extends MQProvisioning

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
			SendEmail oSendEmail;


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


			String orderDate="07-06-2019";
			String effectiveDate="07-06-2019";
		

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





			protected Prov_Send_Email_TC(String sLogFileName) throws Exception
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
				this.oSendEmail=new SendEmail(driver,dbConnection);
				
				oBasicConfigurationsOfProvisioning.OwnHardware_Privileges(); 
				oOrderManagementPrivileges.Disconnection_privileges();
				oOrderManagementPrivileges.Reconnection_privileges();
				oOrderManagementPrivileges.ServiceOrder_privileges();
				oOrderManagementPrivileges.Renewal_privileges();


				testLinkConnection.setsTestProject("PayTV Provisioning");
				testLinkConnection.setsTestPlan("5.12.2");
				testLinkConnection.setsBuild("5.12.2");
				this.oNavigate=new Navigate(driver,dbConnection);
				this.oWaitTool= new WaitTool(driver);
				PageFactory.initElements(driver,this);
				verifyLogin("","");

				Alert alert=driver.switchTo().alert();
				alert.accept();


			}


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



			@Test(description="Navigation to send email screen")
			public void PR_4048() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4048",this);
					System.out.println("PR-4048 Started");

					oNavigate.toSendEmail();
					Thread.sleep(2000);
					String title=driver.getTitle();
					System.out.println("Success message is:"+title);
					if(title.equalsIgnoreCase("SendEmail Adhoc"))
					{
						System.out.println("PR-4048 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Navigation to send email screen is Passed");
					}
					else
					{
						System.out.println("PR-4048 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Navigation to send email screen is Failed");
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


			@Test(description="Send email to customer")
			public void PR_4049() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4049",this);
					System.out.println("PR-4049 Started");
					oNavigate.toCustomerRegistration();
					Thread.sleep(2000);
					CustomerNumber = oCustomerRegistration.NewCustomerRegistrationWithEmail(first_Name,last_Name,"","","ravi@gmail.com");
					System.out.println("Customer number is : "+CustomerNumber); 
					Thread.sleep(2000);
	

					
					oNavigate.toSendEmail();
					Thread.sleep(2000);
					String ValidationMessage=oSendEmail.SendEmailScreen("subjest11", "LOW" ,"Message1111","Add",CustomerNumber,"1","Send");

					
					
					
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
						System.out.println("PR-4049 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Send email to customer is Passed");
					}
					else
					{
						System.out.println("PR-4049 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Send email to customer is Failed");
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


			@Test(description="Send mail to  Multiple Customer")
			public void PR_4050() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4050",this);
					System.out.println("PR-4050 Started");
					
					oNavigate.toCustomerRegistration();
					Thread.sleep(2000);
					CustomerNumber = oCustomerRegistration.NewCustomerRegistrationWithEmail(first_Name,last_Name,"","","ravi@gmail.com");
					System.out.println("Customer number is : "+CustomerNumber); 
					Thread.sleep(2000);
					
					
					oNavigate.toCustomerRegistration();
					Thread.sleep(2000);
					String CustomerNumber1 = oCustomerRegistration.NewCustomerRegistrationWithEmail(first_Name,last_Name,"","","ravi@gmail.com");
					System.out.println("Customer number is : "+CustomerNumber1); 
					Thread.sleep(2000);
					
					
					oNavigate.toSendEmail();
					Thread.sleep(2000);
					oSendEmail.SendEmailScreen("subjest11", "LOW" ,"Message1111","Add",CustomerNumber,"1","");
					String ValidationMessage=oSendEmail.SendEmailScreen("", "" ,"","Add",CustomerNumber1,"2","Send");
										
					
					
					String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
					this.records=oDBConnection.fecthRecords(Customer_id);
					this.record=this.records.get(0);
					String Party_id=record.get("CUSTOMER_ID");
					System.out.println(Party_id);
					oNavigate.toScheduleJob();
					Thread.sleep(2000);
					oScheduleJob.ScheduleJobAutoExpiry_SingleCustomer("", "Batch Jobs","Autoexpire", "", "", "","","AutoExpiry For Single Customer","",Party_id,"");

					Thread.sleep(80000);
					String Status = "select * from provsion_process_request where party_id= '"+Party_id+"' and BIZ_PROCESS_ID=13  order by 1 desc ";
					this.records=oDBConnection.fecthRecords(Status);
					this.record=this.records.get(0);
					String Qstatus=record.get("STATUS");
					System.out.println(Qstatus);
					if(Qstatus.equalsIgnoreCase(N_Status))
					{
						System.out.println("PR-4050 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Send mail to  Multiple Customer is Passed");
					}
					else
					{
						System.out.println("PR-4050 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Send mail to  Multiple Customer is Failed");
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


			@Test(description="Delete Customer")
			public void PR_4051() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4051",this);
					System.out.println("PR-4051 Started");
					oNavigate.toCustomerRegistration();
					Thread.sleep(2000);
					CustomerNumber = oCustomerRegistration.NewCustomerRegistrationWithEmail(first_Name,last_Name,"","","ravi@gmail.com");
					System.out.println("Customer number is : "+CustomerNumber); 
					Thread.sleep(2000);
			
					
					
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
						System.out.println("PR-4051 Pass");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_PASSED);
						testLinkConnection.setsNotes("Delete Customer is Passed");
					}
					else
					{
						System.out.println("PR-4051 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Delete Customer is failed");
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


			@Test(description="Delete  multiple customers")
			public void PR_4052() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4052",this);
					System.out.println("PR-4052 Started");
					
					
					oNavigate.toCustomerRegistration();
					Thread.sleep(2000);
					CustomerNumber = oCustomerRegistration.NewCustomerRegistrationWithEmail(first_Name,last_Name,"","","ravi@gmail.com");
					System.out.println("Customer number is : "+CustomerNumber); 
					Thread.sleep(2000);
			
					
					
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
						testLinkConnection.setsNotes("Delete  multiple customers is Passed");
					}
					else
					{
						System.out.println("PR-4052 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Delete  multiple customers is failed");
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


			@Test(description="Adding same customer number Multiple times")
			public void PR_4053() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4053",this);
					System.out.println("PR-4053 Started");
			
					oNavigate.toCustomerRegistration();
					Thread.sleep(2000);
					CustomerNumber = oCustomerRegistration.NewCustomerRegistrationWithEmail(first_Name,last_Name,"","","ravi@gmail.com");
					System.out.println("Customer number is : "+CustomerNumber); 
					Thread.sleep(2000);
			
					oNavigate.toSendEmail();
					Thread.sleep(2000);
					oSendEmail.SendEmailScreen("subjest11", "LOW" ,"Message1111","Add",CustomerNumber,"1","");
					Thread.sleep(2000);
					String ValidationMessage=oSendEmail.SendEmailScreen("", "" ,"","Add",CustomerNumber,"2","Send");
										
					
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
						testLinkConnection.setsNotes("Adding same customer number Multiple times is passed");
					}
					else
					{
						System.out.println("PR-4053 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Adding same customer number Multiple times is Failed");
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


			@Test(description="Customer without mail id")
			public void PR_4054() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4054",this);
					System.out.println("PR-4054 Started");
					oNavigate.toCustomerRegistration();
					Thread.sleep(2000);
					CustomerNumber = oCustomerRegistration.NewCustomerRegistration(first_Name,last_Name,"","");
					System.out.println("Customer number is : "+CustomerNumber); 
					Thread.sleep(2000);
			
					oNavigate.toSendEmail();
					Thread.sleep(2000);
					String ValidationMessage=oSendEmail.SendEmailScreen("subjest11", "LOW" ,"Message1111","Add",CustomerNumber,"1","Send");

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
						testLinkConnection.setsNotes("Customer without mail id is Passed");
					}
					else
					{
						System.out.println("PR-4054 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Customer without mail id Is Failed");
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


			@Test(description="Enter the Wrong Customer Number")
			public void PR_4055() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4055",this);
					System.out.println("PR-4055 Started");
			
					oNavigate.toCustomerRegistration();
					Thread.sleep(2000);
					CustomerNumber = oCustomerRegistration.NewCustomerRegistrationWithEmail(first_Name,last_Name,"","","ravi@gmail.com");
					System.out.println("Customer number is : "+CustomerNumber); 
					Thread.sleep(2000);
					
					oNavigate.toSendEmail();
					Thread.sleep(2000);
					String ValidationMessage=oSendEmail.SendEmailScreen("subjest11", "LOW" ,"Message1111","Add","aksjsl","1","Send");

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
						testLinkConnection.setsNotes("Enter the Wrong Customer Number is Passed");
					}
					else
					{
						System.out.println("PR-4055 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Enter the Wrong Customer Number is Failed");
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


			@Test(description="Customer Number coloum as Empty")
			public void PR_4056() throws Exception
			{
				try
				{
					testLinkConnection.setsTestCase("PR-4056",this);
					System.out.println("PR-4056 Started");
					oNavigate.toCustomerRegistration();
					Thread.sleep(2000);
					CustomerNumber = oCustomerRegistration.NewCustomerRegistrationWithEmail(first_Name,last_Name,"","","ravi@gmail.com");
					System.out.println("Customer number is : "+CustomerNumber); 
					Thread.sleep(2000);

					oNavigate.toSendEmail();
					Thread.sleep(2000);
					String ValidationMessage=oSendEmail.SendEmailScreen("subjest11", "LOW" ,"Message1111","Add","","1","Send");

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
						testLinkConnection.setsNotes("Customer Number coloum as Empty is Passed");
					}
					else
					{
						System.out.println("PR-4056 Failed");
						testLinkConnection.setsResult(TestLinkAPIResults.TEST_FAILED);
						testLinkConnection.setsNotes("Customer Number coloum as Empty is Failed");
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



}
