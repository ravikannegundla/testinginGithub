package com.ProvisioningPageObject;

import java.util.ArrayList;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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
import com.PageObjects.OrderManagementMasters.PricingMaster.PricePlan;
import com.ReceivablesPageObject.Collections;
import com.ReceivablesPageObject.CreditAllocation;

import Utility.Navigate;
import Utility.WaitTool;
import mq.seleniumFW.connection.MQDBConnection;

public class Retracking 

{

	private WebDriver driver;
	public Navigate oNavigate;
	WaitTool oWaitTool;
	public MQDBConnection oDBConnection;
	public ArrayList<Hashtable<String, String>> records;
	public Hashtable<String, String> record;
	public By uxSearchCtrltxtCustnbr = By.name("ctl00$uxPgCPH$uxSearchCtrl$txtCustnbr");
	public By uxSearchCtrlsearchimgcustsrchimg = By.name("ctl00$uxPgCPH$uxSearchCtrl$searchimg$custsrchimg");
	public By uxSearchCtrltxtsearch_external_id = By.name("ctl00$uxPgCPH$uxSearchCtrl$txtsearch_external_id");
	public By uxSearchCtrltxtsearch_device_no = By.name("ctl00$uxPgCPH$uxSearchCtrl$txtsearch_device_no");
	public By uxSearchCtrltxtsearch_contact = By.name("ctl00$uxPgCPH$uxSearchCtrl$txtsearch_contact");
	public By uxInitialize = By.name("ctl00$uxPgCPH$uxInitialize");
	public By uxSelect = By.name("ctl00$uxPgCPH$uxSelect");
	public By btnsave = By.name("ctl00$uxPgCPH$btnsave");
	public By btncancel = By.name("ctl00$uxPgCPH$btncancel");
	public By uxService = By.name("ctl00$uxPgCPH$uxService");
	public By uxUniqueID = By.name("ctl00$uxPgCPH$uxUniqueID");
	public By ScreenOutPut =By.xpath(".//*[@id='ctl00__infoSpan']");
	String ScreenValidation;
	
	
	public Retracking(WebDriver driver,MQDBConnection dbConnection) 
 	{
 		this.driver=driver;
 		this.oDBConnection=dbConnection;
 		PageFactory.initElements(driver, this);		
     }
	
	
	// Retracking --Customer Number should be Mandatory  and Service_UniqueId is For when retrack with Service wise Then send as Service
	// When retrack with Unique id then send as Unique
	// Need to send the Service_Unique_Count for how many services or unique's to retrack

     public void RetrackingScreen(String CustomerNumber,String Service_UniqueId,int Service_Unique_Count,String Initialize,String Save_cancel) throws InterruptedException

     {	 

    	 
    	     if(CustomerNumber.isEmpty())
 		       {
    	    	   System.out.println("\n");	
 		       }
    	       else if (CustomerNumber.length()!=0)
    	       {
    	    	   driver.findElement(uxSearchCtrltxtCustnbr).clear();	
    	    	   driver.findElement(uxSearchCtrltxtCustnbr).sendKeys(CustomerNumber+Keys.TAB);	
    	    	   Thread.sleep(1000);
    	       }
 	
    	     
    	     
    	     System.out.println("Giving the customer number is done");
    	     String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"'";
    	     this.records=oDBConnection.fecthRecords(Customer_id);
    	     this.record=this.records.get(0);
    	     String Party_id=record.get("CUSTOMER_ID");
    	     System.out.println("Partyid is "+Party_id);

    	    
    	     
    	   
				
				switch (Service_UniqueId)
				
				{
				
				// Case : Service is for retrack with Service wise 
				// case: Unique is for retrack with Unique wise
				
				case "Service":		
					
					 				System.out.println("ENTERED IN TO SERVICE WISE Retacking");
									String getCount="select COUNT(DISTINCT(SERVICE_ID)) from PROVSION_ENTITLEMENT where ACTIVATE_STATUS='C'  AND  PARTY_ID='"+Party_id+"' AND DISCONNECT_STATUS IS NULL";
									this.records=oDBConnection.fecthRecords(getCount);
									this.record=this.records.get(0);
									String count=record.get("COUNT(DISTINCT(SERVICE_ID))");
									int DB_Service_Count = Integer.parseInt(count);
									System.out.println("count1 is "+DB_Service_Count);

									if(DB_Service_Count==0)
									{
										System.out.println("Customer has No active Services");
										break;
									}
									
									else if(Service_Unique_Count>DB_Service_Count)
									{
										System.out.println("Given count is more than the actual Count");
										break;
									}
									
									else if(Service_Unique_Count<=DB_Service_Count)
									{
										
										for(int i=1;i<=Service_Unique_Count;i++)
										{
										
											System.out.println("start i value" +i);
											new Select(driver.findElement(uxService)).selectByIndex(i);//need to modify
											Thread.sleep(1000);
											driver.findElement(uxSelect).click();
											Thread.sleep(1000);
											driver.findElement(btnsave).click();	
											Thread.sleep(1000);
											ScreenValidation = driver.findElement(ScreenOutPut).getText();
											Thread.sleep(2000);
											System.out.println("Screen Validation after clicking the Save button is : "+ScreenValidation);
											Thread.sleep(1000);
											driver.navigate().refresh();
										}
									}
									break;
									
				
									
									
									
									
				case "UniqueId":						
											System.out.println("ENTERED IN TO uniques id  WISE Retacking");
											String getCount1="select COUNT(DISTINCT(UNIQUE_ID)) from PROVSION_ENTITLEMENT where ACTIVATE_STATUS='C'  AND   PARTY_ID='"+Party_id+"' AND DISCONNECT_STATUS IS NULL";
											this.records=oDBConnection.fecthRecords(getCount1);
											this.record=this.records.get(0);
											String count1=record.get("COUNT(DISTINCT(UNIQUE_ID))");
											int DB_UniqueId_Count = Integer.parseInt(count1);
											System.out.println("count1 is "+DB_UniqueId_Count);
											
					// Retrack with initialize as N
											
											if(Initialize=="N")
											{
												System.out.println("entered into unique_id wise and initialixe as N case ");
												
											if(DB_UniqueId_Count==0)
											{
												System.out.println("Customer has No active serial Numbers");
												break;
											}

											else if(Service_Unique_Count>DB_UniqueId_Count)
											{
												System.out.println("Given count is more than the actual Count");
												break;
											}


											else if(Service_Unique_Count<=DB_UniqueId_Count)
											{
												for(int i=1;i<=Service_Unique_Count;i++)
												{
													System.out.println("start i value" +i);
													new Select(driver.findElement(uxUniqueID)).selectByIndex(i);//need to modify
													Thread.sleep(1000);
													driver.findElement(uxSelect).click();
													Thread.sleep(1000);
													driver.findElement(btnsave).click();	
													Thread.sleep(1000);
													ScreenValidation = driver.findElement(ScreenOutPut).getText();
													Thread.sleep(2000);
													System.out.println("Screen Validation after clicking the Save button is : "+ScreenValidation);
													Thread.sleep(1000);
													driver.navigate().refresh();
												}
											}

											break;
									
											}	

											
											
											
			// Retrack with initialize as Y
											
											else if(Initialize=="Y")	
											{
												System.out.println("entered into unique_id wise and initialixe as Y case ");
												if(DB_UniqueId_Count==0)
												{
													System.out.println("Customer has No active serial Numbers");
													break;
												}


												else if(Service_Unique_Count>DB_UniqueId_Count)
												{
													System.out.println("Given count is more than the actual Count");
													break;
												}


												else if(Service_Unique_Count<=DB_UniqueId_Count)
												{
													for(int i=1;i<=Service_Unique_Count;i++)
													{

														System.out.println("start i value" +i);
														new Select(driver.findElement(uxUniqueID)).selectByIndex(i);//need to modify
														Thread.sleep(1000);
														driver.findElement(uxSelect).click();
														Thread.sleep(1000);
														driver.findElement(uxInitialize).click();
														Thread.sleep(1000);
														driver.findElement(btnsave).click();	
														Thread.sleep(1000);
														ScreenValidation = driver.findElement(ScreenOutPut).getText();
														Thread.sleep(2000);
														System.out.println("Screen Validation after clicking the Save button is : "+ScreenValidation);
														Thread.sleep(1000);
														driver.navigate().refresh();

													}
												}

												break;

												
												
											}
											
											
											
				}
    	 
    		 }


}
