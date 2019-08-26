package com.OrderManagementPageObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import mq.seleniumFW.connection.MQDBConnection;


public class HardwareAssociation 

{
	
	static WebDriver driver;
	
	public MQDBConnection oDBConnection;
    public ArrayList<Hashtable<String, String>> records;
    public Hashtable<String, String> record;
	public List<WebElement> listOfRows;
    String ItemCount;
    String date="29-01-2019";
	
	public By uxSearchCtrltxtCustnbr = By.name("ctl00$uxPgCPH$uxSearchCtrl$txtCustnbr");
	public By uxSearchCtrlsearchimgcustsrchimg = By.name("ctl00$uxPgCPH$uxSearchCtrl$searchimg$custsrchimg");
	public By uxSearchCtrltxtsearch_external_id = By.name("ctl00$uxPgCPH$uxSearchCtrl$txtsearch_external_id");
	public By uxSearchCtrltxtsearch_device_no = By.name("ctl00$uxPgCPH$uxSearchCtrl$txtsearch_device_no");
	public By uxSearchCtrltxtsearch_contact = By.name("ctl00$uxPgCPH$uxSearchCtrl$txtsearch_contact");
	public By btnsave = By.name("ctl00$uxPgCPH$btnsave");
	public By btncancel = By.name("ctl00$uxPgCPH$btncancel");
	public By ddlConnection = By.name("ctl00$uxPgCPH$ddlConnection");
	public By cmbContract = By.name("ctl00$uxPgCPH$cmbContract");

	
	ArrayList<String> contract_List= new ArrayList<String>();
	
	//variables
	public String getContractCount , Contract_count, Connection_Count , ScreenValidation , getConnectionCount;
	public int ContractCount , connectionCount;
	
	String getUnassociatedSmartCard,getUnassociatedSmartCardValue,getUnassociatedSetupbox,getUnassociatedSetupboxValue;	
	
	
	
	
	public HardwareAssociation(WebDriver driver,MQDBConnection dbConnection) 
	{
		this.driver=driver;
		this.oDBConnection=dbConnection;
		PageFactory.initElements(driver, this);		
    }


	
// When the multi_connection customer as N	
	public void NonMultiConnection(String CustomerNumber,String Case) throws InterruptedException
	{			
     	
		switch (Case)
		{
         	//Case 1   one connection one contract
			//		case 2 one connection two contract with same hardware
			//		case 3 one connection with multiple hardware
		
			case "OneContract":		if(CustomerNumber.isEmpty())
									{
										System.out.println("\n");	
									}
									else if(CustomerNumber.length()!=0)
									{
										driver.findElement(uxSearchCtrltxtCustnbr).clear();	
										driver.findElement(uxSearchCtrltxtCustnbr).sendKeys(CustomerNumber+Keys.TAB);	
										Thread.sleep(1000);
									}
									int k;
	    							WebElement table = driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody"));

	    							listOfRows = table.findElements(By.tagName("img"));
	    							System.out.println("Rows: "+listOfRows.size());	
	    							for(int j =1;j<=listOfRows.size();j++)
	    							{
	    								k=j+1;
	    								System.out.println("start k value" +k);
	    								driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody/tr["+j+"]/td[4]/a/img")).click();				
	    								new Select(driver.findElement(By.xpath("//*[@id='ctl00_uxPgCPH_grdvAssociate_ctl0"+k+"_cmbunique']"))).selectByIndex(1);//need to modify
	    								driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody/tr["+j+"]/td[4]/a/img")).click();
	    								Thread.sleep(1000);
	    							}
	    							driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_btnsave']")).click();
	    							break;
		
	    							
	    							
	 // Customer having Multiple Contract's and need to map Same hardware's
	    							
			case "MultipleContractWithSameHardware": 	
				                                       if(CustomerNumber.isEmpty())
														{
															System.out.println("\n");	
														}
														else if(CustomerNumber.length()!=0)
														{
															driver.findElement(uxSearchCtrltxtCustnbr).clear();	
															driver.findElement(uxSearchCtrltxtCustnbr).sendKeys(CustomerNumber+Keys.TAB);	
															Thread.sleep(1000);
														}
														System.out.println("ENTERED IN to MultipleContractWithSameHardware case");
														String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"'";
														this.records=oDBConnection.fecthRecords(Customer_id);
														this.record=this.records.get(0);
														String Party_id=record.get("CUSTOMER_ID");
														
														System.out.println("CustomerNumber AND ENTERED AFTER TAKING THE NUMBER");
														String getCount="select COUNT(*) as COUNT from PRICEPLAN,contract where PLAN_ID in (select plan_id from Contract_Priceline where  CONTRACT_PRICELINE.CONTRACT_ID=CONTRACT.CONTRACT_ID and PARTY_ID='"+Party_id+"')";
														this.records=oDBConnection.fecthRecords(getCount);
														this.record=this.records.get(0);
														String count=record.get("COUNT");
														int count1 = Integer.parseInt(count);
														System.out.println("count1 is "+count1);
														
														
														for(int c =1;c<=count1;c++)
														{
															System.out.println("ENTERED IN TO LOOP");
															new Select(driver.findElement(cmbContract)).selectByIndex(c);//need to modify
															System.out.println("SELECTED");
															WebElement table1 = driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody"));	
															listOfRows = table1.findElements(By.tagName("img"));
															System.out.println("Rows: "+listOfRows.size());	
															for(int j =1;j<=listOfRows.size();j++)
															{
																k=j+1;
																System.out.println("start k value" +k);
																driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody/tr["+j+"]/td[4]/a/img")).click();				
																new Select(driver.findElement(By.xpath("//*[@id='ctl00_uxPgCPH_grdvAssociate_ctl0"+k+"_cmbunique']"))).selectByIndex(1);//need to modify
																driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody/tr["+j+"]/td[4]/a/img")).click();
																Thread.sleep(1000);
															}
															driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_btnsave']")).click();
														}
														
														
														break;
														
			// Customer having Multiple Contract's and need to map different hardware's 							
				
			case "MultipleContractWithDiffrentHardware":
					                                          if(CustomerNumber.isEmpty())
					                                             {
					                                        	  System.out.println("\n");	
					                                             }
					                                          else if(CustomerNumber.length()!=0)
					                                          {
					                                        	  driver.findElement(uxSearchCtrltxtCustnbr).clear();	
					                                        	  driver.findElement(uxSearchCtrltxtCustnbr).sendKeys(CustomerNumber+Keys.TAB);	
					                                        	  Thread.sleep(1000);
					                                          }
					                                          System.out.println("ENTERED into MultipleContractWithDiffrentHardware");
					                                          String Customer_id1 = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"'";
					                                          this.records=oDBConnection.fecthRecords(Customer_id1);
					                                          this.record=this.records.get(0);
					                                          String Party_id1=record.get("CUSTOMER_ID");

					                                          System.out.println("CustomerNumber AND ENTERED AFTER TAKING THE NUMBER");
					                                          String getCount1="select COUNT(*) as COUNT from PRICEPLAN,contract where PLAN_ID in (select plan_id from Contract_Priceline where  CONTRACT_PRICELINE.CONTRACT_ID=CONTRACT.CONTRACT_ID and PARTY_ID='"+Party_id1+"')";
					                                          this.records=oDBConnection.fecthRecords(getCount1);
					                                          this.record=this.records.get(0);
					                                          String count2=record.get("COUNT");
					                                          int count3 = Integer.parseInt(count2);
					                                          System.out.println("count1 is "+count3);


					                                          for(int c =1;c<=count3;c++)
					                                          {
					                                        	  System.out.println("ENTERED IN TO LOOP");
					                                        	  new Select(driver.findElement(cmbContract)).selectByIndex(c);//need to modify
					                                        	  System.out.println("SELECTED");
					                                        	  WebElement table1 = driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody"));	
					                                        	  listOfRows = table1.findElements(By.tagName("img"));
					                                        	  System.out.println("Rows: "+listOfRows.size());	
					                                        	  for(int j =1;j<=listOfRows.size();j++)
					                                        	  {
					                                        		  k=j+1;
					                                        		  System.out.println("start k value" +k);
					                                        		  driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody/tr["+j+"]/td[4]/a/img")).click();				
					                                        		  new Select(driver.findElement(By.xpath("//*[@id='ctl00_uxPgCPH_grdvAssociate_ctl0"+k+"_cmbunique']"))).selectByIndex(k);//need to modify
					                                        		  driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody/tr["+j+"]/td[4]/a/img")).click();
					                                        		  Thread.sleep(1000);
					                                        		  System.out.println("End of k value" +k);
					                                        	  }
					                                        	  driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_btnsave']")).click();
					                                          }
					                                          
					                                          break;
		}
	}

	
	
	
	
	
	
	
	
	// when the Customer is Multi_connection customer
	
	public void MultiConnection_Customer(String CustomerNumber,String Case) throws InterruptedException
	{			
     	
		switch (Case)
		{
        
		
		// Customer having Single connection with one Contract 
		
			case "SingleConnection-Contract":		if(CustomerNumber.isEmpty())
													{
														System.out.println("\n");	
													}
													else if(CustomerNumber.length()!=0)
													{
														driver.findElement(uxSearchCtrltxtCustnbr).clear();	
														driver.findElement(uxSearchCtrltxtCustnbr).sendKeys(CustomerNumber+Keys.TAB);	
														Thread.sleep(1000);
													}
													int k;
													WebElement table = driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody"));
													listOfRows = table.findElements(By.tagName("img"));
													System.out.println("Rows: "+listOfRows.size());	
													for(int j =1;j<=listOfRows.size();j++)
													{
														k=j+1;
														System.out.println("start k value" +k);
														driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody/tr["+j+"]/td[4]/a/img")).click();				
														new Select(driver.findElement(By.xpath("//*[@id='ctl00_uxPgCPH_grdvAssociate_ctl0"+k+"_cmbunique']"))).selectByIndex(1);//need to modify
														driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody/tr["+j+"]/td[4]/a/img")).click();
														Thread.sleep(1000);
													}
													driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_btnsave']")).click();
													break;	
		
		// Customer having Single connection with Multiple Contract												
													
			case "SingleConnection-MultipleContracts":
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
														System.out.println("entered into SingleConnection-MultipleContracts");
														getContractCount="select COUNT(*) as COUNT from contract_connections where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
														this.records=oDBConnection.fecthRecords(getContractCount);
														this.record=this.records.get(0);
														Contract_count=record.get("COUNT");
													    ContractCount = Integer.parseInt(Contract_count);
														System.out.println("ContractCoount is "+ContractCount);
														

														for(int c =1;c<=ContractCount;c++)
														{
															System.out.println("ENTERED IN TO LOOP");
															new Select(driver.findElement(cmbContract)).selectByIndex(c);//need to modify
															System.out.println("SELECTED");
															WebElement table1 = driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody"));	
															listOfRows = table1.findElements(By.tagName("img"));
															System.out.println("Rows: "+listOfRows.size());	
															for(int j =1;j<=listOfRows.size();j++)
															{
																k=j+1;
																System.out.println("start k value" +k+" value of j : "+j);
																Thread.sleep(2000);
																driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody/tr["+j+"]/td[4]/a/img")).click();				
																new Select(driver.findElement(By.xpath("//*[@id='ctl00_uxPgCPH_grdvAssociate_ctl0"+k+"_cmbunique']"))).selectByIndex(1);//need to modify
																driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody/tr["+j+"]/td[4]/a/img")).click();
																Thread.sleep(1000);
															}
															driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_btnsave']")).click();
														}
																												
														break;
														
								
														
			// Customer having Multiple connection with one Contract
														
			case "MultiConnection_singlecontract" :		if(CustomerNumber.isEmpty())
														{
															System.out.println("\n");	
														}
														else if (CustomerNumber.length()!=0)
														{
															driver.findElement(uxSearchCtrltxtCustnbr).clear();	
															driver.findElement(uxSearchCtrltxtCustnbr).sendKeys(CustomerNumber+Keys.TAB);	
															Thread.sleep(1000);
														}
														System.out.println("entered into MultiConnection_singlecontract");
														
														getConnectionCount="select COUNT(*) as COUNT from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
														this.records=oDBConnection.fecthRecords(getConnectionCount);
														this.record=this.records.get(0);
														Connection_Count=record.get("COUNT");
														connectionCount = Integer.parseInt(Connection_Count);
														System.out.println("connectionCount is "+connectionCount);
			
														for(int a =0;a<=connectionCount-1;a++)
														{																		
															
															new Select(driver.findElement(ddlConnection)).selectByIndex(a);
																		
															Select select = new Select(driver.findElement(ddlConnection));
															WebElement option = select.getFirstSelectedOption();
															String defaultItem = option.getText();
															System.out.println("defaultItem : "+defaultItem);
																		
															getContractCount="select COUNT(*) as COUNT from contract_connections where connection_id = '"+defaultItem+"'";
															this.records=oDBConnection.fecthRecords(getContractCount);
															this.record=this.records.get(0);
															Contract_count=record.get("COUNT");
															ContractCount = Integer.parseInt(Contract_count);
															System.out.println("ContractCount is "+ContractCount);																																
																		
															for(int c =1;c<=ContractCount;c++)
															{
																System.out.println("ENTERED IN TO LOOP");
																new Select(driver.findElement(cmbContract)).selectByIndex(c);
																System.out.println("SELECTED");
																WebElement table1 = driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody"));	
																listOfRows = table1.findElements(By.tagName("img"));
																System.out.println("Rows: "+listOfRows.size());	
																for(int j =1;j<=listOfRows.size();j++)
																{
																	k=j+1;
																	System.out.println("start k value" +k+" value of j : "+j);
																	Thread.sleep(2000);
																	driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody/tr["+j+"]/td[4]/a/img")).click();				
																	new Select(driver.findElement(By.xpath("//*[@id='ctl00_uxPgCPH_grdvAssociate_ctl0"+k+"_cmbunique']"))).selectByIndex(1);
																	driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody/tr["+j+"]/td[4]/a/img")).click();
																	Thread.sleep(1000);
																}
																driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_btnsave']")).click();
															}
															
														}
																		
														
											
														break;
														
			}
		}
	
	
	
	// For Association Change to the customer  with  new SC and STB
	
	
	public void changeAssociation(String CustomerNumber,String Case,String connectionNumber,String item_Name) throws InterruptedException
	{			
			
		int k ;     	
		switch (Case)
		{
			

			case "New" :		// If you want to add the hardware for the     		
				
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
								System.out.println("entered into MultiConnection_singlecontract");
				
								getConnectionCount="select COUNT(*) as COUNT from connection_header where customer_id in (select customer_id from customer_tbl where customer_nbr = '"+CustomerNumber+"')";
								this.records=oDBConnection.fecthRecords(getConnectionCount);
								this.record=this.records.get(0);
								Connection_Count=record.get("COUNT");
								connectionCount = Integer.parseInt(Connection_Count);
								System.out.println("connectionCount is "+connectionCount);
														
								for(int a =0;a<=connectionCount-1;a++)
								{																		
									
									new Select(driver.findElement(ddlConnection)).selectByIndex(a);
									
									Select select = new Select(driver.findElement(ddlConnection));
									WebElement option = select.getFirstSelectedOption();
									String defaultItem = option.getText();
									System.out.println("defaultItem : "+defaultItem);
									
									getContractCount="select COUNT(*) as COUNT from contract_connections where connection_id = '"+defaultItem+"'";
									this.records=oDBConnection.fecthRecords(getContractCount);
									this.record=this.records.get(0);
									Contract_count=record.get("COUNT");
									ContractCount = Integer.parseInt(Contract_count);
									System.out.println("ContractCount is "+ContractCount);																																
									
									String uniqueAdderess = driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody/tr[1]/td[3]")).getText();
									Thread.sleep(3000);
									System.out.println("uniqueAdderess : "+uniqueAdderess);
									
									for(int c =1;c<=ContractCount;c++)
									{
										System.out.println("ENTERED IN TO LOOP");
										new Select(driver.findElement(cmbContract)).selectByIndex(c);
										System.out.println("SELECTED");
										
										if(uniqueAdderess.length()==0)
										{
											
											WebElement table1 = driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody"));	
											listOfRows = table1.findElements(By.tagName("img"));
											System.out.println("Rows: "+listOfRows.size());	
											for(int j =1;j<=listOfRows.size();j++)
											{
												k=j+1;
												System.out.println("start k value" +k+" value of j : "+j);
												Thread.sleep(2000);
												driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody/tr["+j+"]/td[4]/a/img")).click();				
												new Select(driver.findElement(By.xpath("//*[@id='ctl00_uxPgCPH_grdvAssociate_ctl0"+k+"_cmbunique']"))).selectByIndex(1);
												driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody/tr["+j+"]/td[4]/a/img")).click();
												Thread.sleep(1000);
											}
											driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_btnsave']")).click();
										}
													
									}
								}
								
											
								break;
											
								
					
			case "Change" :		if(CustomerNumber.isEmpty())
								{
									System.out.println("\n");	
								}
								else if (CustomerNumber.length()!=0)
								{		
									driver.findElement(uxSearchCtrltxtCustnbr).clear();	
									driver.findElement(uxSearchCtrltxtCustnbr).sendKeys(CustomerNumber+Keys.TAB);	
									Thread.sleep(1000);
								}

			                    String Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
			                    this.records=oDBConnection.fecthRecords(Customer_id);
			                    this.record=this.records.get(0);
			                    String Party_id=record.get("CUSTOMER_ID");
			                    System.out.println(Party_id);


			                    getUnassociatedSmartCard="select SERIAL_NUMBER from item_serial_dtl where party_id='"+Party_id+"'  and ITEM_ID=16 and   item_detail_id not in ( select item_detail_id from association  where  customer_id='"+Party_id+"') order by ITEM_DETAIL_ID desc ";
			                    this.records=oDBConnection.fecthRecords(getUnassociatedSmartCard);
			                    this.record=this.records.get(0);
			                    getUnassociatedSmartCardValue=record.get("SERIAL_NUMBER");
			                    System.out.println("getUnassociatedSmartCardValue is "+getUnassociatedSmartCardValue);

			                    getUnassociatedSetupbox="select SERIAL_NUMBER from item_serial_dtl where party_id='"+Party_id+"'  and ITEM_ID=19 and   item_detail_id not in ( select item_detail_id from association  where  customer_id='"+Party_id+"') order by ITEM_DETAIL_ID desc ";
			                    this.records=oDBConnection.fecthRecords(getUnassociatedSetupbox);
			                    this.record=this.records.get(0);
			                    getUnassociatedSetupboxValue=record.get("SERIAL_NUMBER");
			                    System.out.println("getUnassociatedSetupboxValue is "+getUnassociatedSetupboxValue);
			
								System.out.println("entered into MultiConnection_singlecontract");
												
								new Select(driver.findElement(ddlConnection)).selectByVisibleText(connectionNumber);																
								new Select(driver.findElement(cmbContract)).selectByIndex(1);
								System.out.println("SELECTED");																			
								Thread.sleep(1000);
																
								WebElement table1 = driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody"));	
								listOfRows = table1.findElements(By.tagName("img"));
								System.out.println("Rows: "+listOfRows.size());	
								for(int j =1;j<=listOfRows.size();j++)
								{
								
									k=j+1;
									System.out.println("start k value" +k+" value of j : "+j);
									Thread.sleep(2000);
									
									driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody/tr["+j+"]/td[4]/a/img")).click();																		
	
									String ItemName = driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody/tr["+j+"]/td[2]")).getText();
									Thread.sleep(3000);
									System.out.println("ItemName is : "+ItemName);	
									
									if(ItemName.equalsIgnoreCase("Set-Top-Box"))
									{
										System.out.println("Entered into Set-Top-Box"+getUnassociatedSetupboxValue);
										Thread.sleep(3000);
										new Select(driver.findElement(By.xpath("//*[@id='ctl00_uxPgCPH_grdvAssociate_ctl0"+k+"_cmbunique']"))).selectByVisibleText(getUnassociatedSetupboxValue);
										Thread.sleep(3000);
										driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody/tr["+j+"]/td[4]/a/img")).click();
										Thread.sleep(1000);									
									}
									else
									{
										System.out.println("Entered into Smart Card");
										new Select(driver.findElement(By.xpath("//*[@id='ctl00_uxPgCPH_grdvAssociate_ctl0"+k+"_cmbunique']"))).selectByVisibleText(getUnassociatedSmartCardValue);
										driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody/tr["+j+"]/td[4]/a/img")).click();
										Thread.sleep(1000);
										
									}
									
																		
								}
								
								driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_btnsave']")).click();
								break;
								
	case "ChangeWithSingle":
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
				                   
				                   Customer_id = "Select CUSTOMER_ID from Customer_tbl where customer_nbr='"+CustomerNumber+"' ";
				                   this.records=oDBConnection.fecthRecords(Customer_id);
				                   this.record=this.records.get(0);
				                   Party_id=record.get("CUSTOMER_ID");
				                   System.out.println(Party_id);


				                   getUnassociatedSmartCard="select SERIAL_NUMBER from item_serial_dtl where party_id='"+Party_id+"'  and ITEM_ID=16 and   item_detail_id not in ( select item_detail_id from association  where  customer_id='"+Party_id+"') order by ITEM_DETAIL_ID desc ";
				                   this.records=oDBConnection.fecthRecords(getUnassociatedSmartCard);
				                   this.record=this.records.get(0);
				                   getUnassociatedSmartCardValue=record.get("SERIAL_NUMBER");
				                   System.out.println("getUnassociatedSmartCardValue is "+getUnassociatedSmartCardValue);

				                   getUnassociatedSetupbox="select SERIAL_NUMBER from item_serial_dtl where party_id='"+Party_id+"'  and ITEM_ID=19 and   item_detail_id not in ( select item_detail_id from association  where  customer_id='"+Party_id+"') order by ITEM_DETAIL_ID desc ";
				                   this.records=oDBConnection.fecthRecords(getUnassociatedSetupbox);
				                   this.record=this.records.get(0);
				                   getUnassociatedSetupboxValue=record.get("SERIAL_NUMBER");
				                   System.out.println("getUnassociatedSetupboxValue is "+getUnassociatedSetupboxValue);

				                   System.out.println("entered into MultiConnection_singlecontract");

				                   new Select(driver.findElement(ddlConnection)).selectByVisibleText(connectionNumber);																
				                   new Select(driver.findElement(cmbContract)).selectByIndex(1);
				                   System.out.println("SELECTED");																			
				                   Thread.sleep(1000);

				                   table1 = driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody"));	
				                   listOfRows = table1.findElements(By.tagName("img"));
				                   System.out.println("Rows: "+listOfRows.size());	
				                   for(int j =1;j<=listOfRows.size();j++)
				                   {

				                	   k=j+1;
				                	   System.out.println("start k value" +k+" value of j : "+j);
				                	   Thread.sleep(2000);

				                	   driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody/tr["+j+"]/td[4]/a/img")).click();																		

				                	   String ItemName = driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody/tr["+j+"]/td[2]")).getText();
				                	   Thread.sleep(3000);
				                	   System.out.println("ItemName is : "+ItemName);	

				                	   if(ItemName.equals(item_Name))
				                	   {
				                		   System.out.println("ItemName is : "+ItemName+" item_Name  is : "+item_Name);
				                		   Thread.sleep(3000);
				                		   if(ItemName.equals("Smart Card"))
				                		   {
				                			   System.out.println("smart card selection");
				                			   new Select(driver.findElement(By.xpath("//*[@id='ctl00_uxPgCPH_grdvAssociate_ctl0"+k+"_cmbunique']"))).selectByVisibleText(getUnassociatedSmartCardValue);
				                			   System.out.println("smart card selected");
				                		   }
				                		   else
				                		   {
				                			   System.out.println("Set Top Box selection");
				                			   new Select(driver.findElement(By.xpath("//*[@id='ctl00_uxPgCPH_grdvAssociate_ctl0"+k+"_cmbunique']"))).selectByVisibleText(getUnassociatedSetupboxValue);
				                			   System.out.println("Set Top Box selected");
				                		   }
				                			   Thread.sleep(3000);
				                		   driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvAssociate']/tbody/tr["+j+"]/td[4]/a/img")).click();
				                		   Thread.sleep(1000);									
				                	   }
				         


				                   }

				                   driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_btnsave']")).click();
				                   break;

				                

		}
		
		   							System.out.println("end of hardware association screen ");
	}
	

	
	
	



}
