package com.ProvisioningPageObject;

import java.util.ArrayList;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


import Utility.Navigate;
import Utility.WaitTool;
import mq.seleniumFW.connection.MQDBConnection;

public class ProvisioningSystemCreation 
{

	
	
			private WebDriver driver;
			public Navigate oNavigate;
			WaitTool oWaitTool;
			private MQDBConnection oDBConnection;

			public By sbattr_PROV_SYSTEM_CODE = By.name("ctl00$uxPgCPH$sbattr_PROV_SYSTEM_CODE");
			public By sbattr_PROV_SYSTEM_CODE_search = By.name("ctl00$uxPgCPH$sbattr_PROV_SYSTEM_CODE_search");
			public By sbattr_PROV_SYSTEM_NAME = By.name("ctl00$uxPgCPH$sbattr_PROV_SYSTEM_NAME");
			public By sbattrcb_IS_RENEWAL = By.name("ctl00$uxPgCPH$sbattrcb_IS_RENEWAL");
			public By sbattr_RENEWAL_PERIOD = By.name("ctl00$uxPgCPH$sbattr_RENEWAL_PERIOD");
			public By sbattrcb_ALLOW_TOPUP = By.name("ctl00$uxPgCPH$sbattrcb_ALLOW_TOPUP");
			public By sbattrcb_IS_DATEDRIVEN = By.name("ctl00$uxPgCPH$sbattrcb_IS_DATEDRIVEN");
			public By sbattrcb_PURGE_FOR_REUSE = By.name("ctl00$uxPgCPH$sbattrcb_PURGE_FOR_REUSE");
			public By sbattrcb_IS_MULTIFAMILY_REQ_REQUIRED = By.name("ctl00$uxPgCPH$sbattrcb_IS_MULTIFAMILY_REQ_REQUIRED");
			public By sbattr_MAX_SERVICES_IN_REQUEST = By.name("ctl00$uxPgCPH$sbattr_MAX_SERVICES_IN_REQUEST");
			public By sbattrcb_REQ_REQUIRED_ONTERMINATION = By.name("ctl00$uxPgCPH$sbattrcb_REQ_REQUIRED_ONTERMINATION");
			public By sbattrcb_SUPPORT_REQUEST_PRIORITY = By.name("ctl00$uxPgCPH$sbattrcb_SUPPORT_REQUEST_PRIORITY");
			public By sbattrcb_IS_OTT = By.name("ctl00$uxPgCPH$sbattrcb_IS_OTT");
			public By sbattrcb_IS_OSDCMD_SUPPORTS_REPEATION = By.name("ctl00$uxPgCPH$sbattrcb_IS_OSDCMD_SUPPORTS_REPEATION");
			public By btnsave = By.name("ctl00$uxPgCPH$btnsave");
			public By btncancel = By.name("ctl00$uxPgCPH$btncancel");
			public By ValidationMessage=By.id("ctl00__infoSpan");

			public By sbattr_RENEWAL_PERIOD_TYPE = By.name("ctl00$uxPgCPH$sbattr_RENEWAL_PERIOD_TYPE");
			public By sbattr_TIME_ZONE = By.name("ctl00$uxPgCPH$sbattr_TIME_ZONE");

			String ScreenValidation;
			public ArrayList<Hashtable<String, String>> records;
			public Hashtable<String,String> record;
			int ProvisioningSystem_Count;

			public ProvisioningSystemCreation(WebDriver driver,MQDBConnection dbConnection)
			{

				this.driver=driver;
				this.oDBConnection=dbConnection;

				this.oNavigate=new Navigate(driver,oDBConnection); 
				PageFactory.initElements(driver, this);

			}

			public  String ProvisioningSystem(String scode,String sDescription,String sRenewal,String sRenewalPeriod,String srenewalperiodtype,String snotifyontopup,String sdatedriven,String spurge_for_reuse,String sis_multifamily_req_required,String sProvisioningSystemTime_Zone,String sMax_services_in_request,String srequest_required_ontermination,String ssupp_req_priority,String sIs_OTT,String sIs_OSDCommandSupports_Repeation,String Save_Cancel) throws Exception

			{
				String ProvisioningSystemCount = "select COUNT(PROV_SYSTEM_CODE) as COUNT from PROVISIONING_SYSTEM where PROV_SYSTEM_CODE='"+scode+"'";
				this.records=oDBConnection.fecthRecords(ProvisioningSystemCount);
				this.record=this.records.get(0);
				ProvisioningSystemCount=record.get("COUNT");
				System.out.println("ProvisioningSystem Count is "+ProvisioningSystemCount);
				ProvisioningSystem_Count = Integer.parseInt(ProvisioningSystemCount);
				System.out.println("ProvisioningSystem Count is "+ProvisioningSystem_Count);

				// if the  Provisioning System code is not exist's in db then the New Provisioning System will be created.
				if(ProvisioningSystem_Count==0)       
				{

					if(scode.length()!=0)
					{ 
						Thread.sleep(1000);
						driver.findElement(sbattr_PROV_SYSTEM_CODE).clear();
						driver.findElement(sbattr_PROV_SYSTEM_CODE).sendKeys(scode+Keys.TAB);
					}
					Thread.sleep(1000);

					if(sDescription.length()!=0)
					{
						driver.findElement(sbattr_PROV_SYSTEM_NAME).clear();
						driver.findElement(sbattr_PROV_SYSTEM_NAME).sendKeys(sDescription+Keys.TAB);
					}
					Thread.sleep(1000);

					if(sRenewal.length()!=0)
					{
						driver.findElement(sbattrcb_IS_RENEWAL).click();
					}
					Thread.sleep(1000);

					if(sRenewalPeriod.length()!=0)
					{
						driver.findElement(sbattr_RENEWAL_PERIOD).clear();
						driver.findElement(sbattr_RENEWAL_PERIOD).sendKeys(sRenewalPeriod+Keys.TAB);
					}
					Thread.sleep(1000);

					if(srenewalperiodtype.length()!=0)
					{
						new Select(driver.findElement(sbattr_RENEWAL_PERIOD_TYPE)).selectByVisibleText(srenewalperiodtype);
					}
					Thread.sleep(3000);

					if(snotifyontopup.length()!=0)
					{
						driver.findElement(sbattrcb_ALLOW_TOPUP).click();
					}
					Thread.sleep(1000);

					if(sdatedriven.length()!=0)
					{
						driver.findElement(sbattrcb_IS_DATEDRIVEN).click();
					}
					Thread.sleep(1000);

					if(spurge_for_reuse.length()!=0)
					{
						driver.findElement(sbattrcb_PURGE_FOR_REUSE).click();
					}
					Thread.sleep(1000);

					if(sis_multifamily_req_required.length()!=0)
					{
						driver.findElement(sbattrcb_IS_MULTIFAMILY_REQ_REQUIRED).click();
					}
					Thread.sleep(1000);

					if(sProvisioningSystemTime_Zone.length()!=0)
					{
						new Select(driver.findElement(sbattr_TIME_ZONE)).selectByVisibleText(sProvisioningSystemTime_Zone);
					}
					Thread.sleep(3000);

					if(sMax_services_in_request.length()!=0)
					{ 
						driver.findElement(sbattr_MAX_SERVICES_IN_REQUEST).sendKeys(sMax_services_in_request+Keys.TAB);
					}
					Thread.sleep(1000);

					if(srequest_required_ontermination.length()!=0)
					{
						driver.findElement(sbattrcb_REQ_REQUIRED_ONTERMINATION).click();
					}
					Thread.sleep(1000);

					if(ssupp_req_priority.length()!=0)
					{
						driver.findElement(sbattrcb_SUPPORT_REQUEST_PRIORITY).click();
					}
					Thread.sleep(1000);

					if(sIs_OTT.length()!=0)
					{
						driver.findElement(sbattrcb_IS_OTT).click();
					}
					Thread.sleep(1000);

					if(sIs_OSDCommandSupports_Repeation.length()!=0)
					{
						driver.findElement(sbattrcb_IS_OSDCMD_SUPPORTS_REPEATION).click();
					}
					Thread.sleep(1000);
					if(Save_Cancel.contains("Save"))	
					{
						driver.findElement(btnsave).click();
					}
					else
					{
						driver.findElement(btncancel).click();
					}

					ScreenValidation=driver.findElement(ValidationMessage).getText();
					Thread.sleep(4000);
					System.out.println(ScreenValidation);


				}

				else 
				{

					
					if(scode.length()!=0)
					{ 
						Thread.sleep(1000);
						driver.findElement(sbattr_PROV_SYSTEM_CODE).clear();
						driver.findElement(sbattr_PROV_SYSTEM_CODE).sendKeys(scode+Keys.TAB);
					}
					Thread.sleep(5000);


					if(sRenewal.length()!=0)
					{
						if(sRenewal=="Y")
						{
							if(driver.findElement(sbattrcb_IS_RENEWAL).isSelected())
							{
								System.out.println("Renewal check box is already selected");
							}
							else 
							{
								driver.findElement(sbattrcb_IS_RENEWAL).click();
							}
						}
						else 
						{
							if(driver.findElement(sbattrcb_IS_RENEWAL).isSelected())
							{
								driver.findElement(sbattrcb_IS_RENEWAL).click();
							}
							else 
							{
								System.out.println("Renewal check box is not  selected");
							}
						}
					}
					Thread.sleep(1000);


					if(sRenewalPeriod.length()!=0)
					{
						driver.findElement(sbattr_RENEWAL_PERIOD).clear();
						driver.findElement(sbattr_RENEWAL_PERIOD).sendKeys(sRenewalPeriod+Keys.TAB);
					}
					Thread.sleep(1000);

					if(srenewalperiodtype.length()!=0)
					{
						new Select(driver.findElement(sbattr_RENEWAL_PERIOD_TYPE)).selectByVisibleText(srenewalperiodtype);
					}
					Thread.sleep(3000);


					if(snotifyontopup.length()!=0)
					{
						if(snotifyontopup=="Y")
						{
							if(driver.findElement(sbattrcb_ALLOW_TOPUP).isSelected())
							{
								System.out.println("notifyontopup check box is already selected");
							}
							else 
							{
								driver.findElement(sbattrcb_ALLOW_TOPUP).click();
							}
						}
						else 
						{
							if(driver.findElement(sbattrcb_ALLOW_TOPUP).isSelected())
							{
								driver.findElement(sbattrcb_ALLOW_TOPUP).click();
							}
							else 
							{
								System.out.println("notifyontopup check box is not selected");
							}
						}
					}
					Thread.sleep(1000);


					if(sdatedriven.length()!=0)
					{
						if(sdatedriven=="Y")
						{
							if(driver.findElement(sbattrcb_IS_DATEDRIVEN).isSelected())
							{
								System.out.println("datedriven check box is already selected");
							}
							else 
							{
								driver.findElement(sbattrcb_IS_DATEDRIVEN).click();
							}
						}
						else 
						{
							if(driver.findElement(sbattrcb_IS_DATEDRIVEN).isSelected())
							{
								driver.findElement(sbattrcb_IS_DATEDRIVEN).click();
							}
							else 
							{
								System.out.println("datedriven check box is not selected");
							}
						}
					}
					Thread.sleep(1000);


					if(spurge_for_reuse.length()!=0)
					{
						if(spurge_for_reuse=="Y")
						{
							if(driver.findElement(sbattrcb_PURGE_FOR_REUSE).isSelected())
							{
								System.out.println("purge_for_reuse check box is already selected");
							}
							else 
							{
								driver.findElement(sbattrcb_PURGE_FOR_REUSE).click();
							}
						}
						else 
						{
							if(driver.findElement(sbattrcb_PURGE_FOR_REUSE).isSelected())
							{
								driver.findElement(sbattrcb_PURGE_FOR_REUSE).click();
							}
							else 
							{
								System.out.println("purge_for_reuse check box is not selected");
							}
						}
					}
					Thread.sleep(1000);

					if(sis_multifamily_req_required.length()!=0)
					{
						if(sis_multifamily_req_required=="Y")
						{
							if(driver.findElement(sbattrcb_IS_MULTIFAMILY_REQ_REQUIRED).isSelected())
							{
								System.out.println("sis_multifamily_req_required check box is already selected");
							}
							else 
							{
								driver.findElement(sbattrcb_IS_MULTIFAMILY_REQ_REQUIRED).click();
							}
						}
						else 
						{
							if(driver.findElement(sbattrcb_IS_MULTIFAMILY_REQ_REQUIRED).isSelected())
							{
								driver.findElement(sbattrcb_IS_MULTIFAMILY_REQ_REQUIRED).click();
							}
							else 
							{
								System.out.println("sis_multifamily_req_required check box is not selected");
							}
						}
					}
					Thread.sleep(1000);





					if(sProvisioningSystemTime_Zone.length()!=0)
					{
						new Select(driver.findElement(sbattr_TIME_ZONE)).selectByVisibleText(sProvisioningSystemTime_Zone);
					}
					Thread.sleep(3000);

					if(sMax_services_in_request.length()!=0)
					{ 
						driver.findElement(sbattr_MAX_SERVICES_IN_REQUEST).sendKeys(sMax_services_in_request+Keys.TAB);
					}
					Thread.sleep(1000);


					if(srequest_required_ontermination.length()!=0)
					{
						if(srequest_required_ontermination=="Y")
						{
							if(driver.findElement(sbattrcb_REQ_REQUIRED_ONTERMINATION).isSelected())
							{
								System.out.println("srequest_required_ontermination check box is already selected");
							}
							else 
							{
								driver.findElement(sbattrcb_REQ_REQUIRED_ONTERMINATION).click();
							}
						}
						else 
						{
							if(driver.findElement(sbattrcb_REQ_REQUIRED_ONTERMINATION).isSelected())
							{
								driver.findElement(sbattrcb_REQ_REQUIRED_ONTERMINATION).click();
							}
							else 
							{
								System.out.println("srequest_required_ontermination check box is not selected");
							}
						}
					}
					Thread.sleep(1000);


					if(ssupp_req_priority.length()!=0)
					{
						if(ssupp_req_priority=="Y")
						{
							if(driver.findElement(sbattrcb_SUPPORT_REQUEST_PRIORITY).isSelected())
							{
								System.out.println("ssupp_req_priority check box is already selected");
							}
							else 
							{
								driver.findElement(sbattrcb_SUPPORT_REQUEST_PRIORITY).click();
							}
						}
						else 
						{
							if(driver.findElement(sbattrcb_SUPPORT_REQUEST_PRIORITY).isSelected())
							{
								driver.findElement(sbattrcb_SUPPORT_REQUEST_PRIORITY).click();
							}
							else 
							{
								System.out.println("ssupp_req_priority check box is not selected");
							}
						}
					}
					Thread.sleep(1000);

					if(sIs_OTT.length()!=0)
					{
						if(sIs_OTT=="Y")
						{
							if(driver.findElement(sbattrcb_IS_OTT).isSelected())
							{
								System.out.println("sIs_OTT check box is already selected");
							}
							else 
							{
								driver.findElement(sbattrcb_IS_OTT).click();
							}
						}
						else 
						{
							if(driver.findElement(sbattrcb_IS_OTT).isSelected())
							{
								driver.findElement(sbattrcb_IS_OTT).click();
							}
							else 
							{
								System.out.println("sIs_OTT check box is not selected");
							}
						}
					}
					Thread.sleep(1000);


					if(sIs_OSDCommandSupports_Repeation.length()!=0)
					{
						if(sIs_OSDCommandSupports_Repeation=="Y")
						{
							if(driver.findElement(sbattrcb_IS_OSDCMD_SUPPORTS_REPEATION).isSelected())
							{
								System.out.println("sIs_OSDCommandSupports_Repeation check box is already selected");
							}
							else 
							{
								driver.findElement(sbattrcb_IS_OSDCMD_SUPPORTS_REPEATION).click();
							}
						}
						else 
						{
							if(driver.findElement(sbattrcb_IS_OSDCMD_SUPPORTS_REPEATION).isSelected())
							{
								driver.findElement(sbattrcb_IS_OSDCMD_SUPPORTS_REPEATION).click();
							}
							else 
							{
								System.out.println("sIs_OSDCommandSupports_Repeation check box is not selected");
							}
						}
					}
					Thread.sleep(1000);


					if(Save_Cancel.contains("Save"))	
					{
						driver.findElement(btnsave).click();
					}
					else
					{
						driver.findElement(btncancel).click();
					}

					ScreenValidation=driver.findElement(ValidationMessage).getText();
					Thread.sleep(4000);

				}

				return ScreenValidation;
			}

	
}
