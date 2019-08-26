package com.ProvisioningPageObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Utility.Navigate;
import Utility.WaitTool;
import mq.seleniumFW.connection.MQDBConnection;

public class ProvisioningService 

{
	private WebDriver driver;
	public Navigate oNavigate;
	WaitTool oWaitTool;
	
	private MQDBConnection oDBConnection;

	public By btnsave = By.name("ctl00$uxPgCPH$btnsave");
	public By btncancel = By.name("ctl00$uxPgCPH$btncancel");
	public By ddlProvSystem = By.name("ctl00$uxPgCPH$ddlProvSystem");
	public By ValidationMessage=By.id("ctl00__infoSpan");
	
	public ArrayList<Hashtable<String, String>> records;
    public Hashtable<String,String> record;
    
    String ScreenValidation;
    int ProvService_Count,i,j,Service_CountWithProvsystem;
    public List<WebElement> listOfRows;
	
	
	public ProvisioningService(WebDriver driver,MQDBConnection dbConnection)
	{
		this.driver=driver;
		this.oDBConnection=dbConnection;
		this.oNavigate=new Navigate(driver,oDBConnection); 
		PageFactory.initElements(driver, this);
	}

	
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

	
	
	public  String ProvisioningServiceMapping(String sProvisioningSystem,String sCategory,String sService, String Save_Cancel) throws Exception
    
	 {
	
		String ProvServiceCount = "select COUNT(PROVSION_SERVICE_ID) from PROVSION_SERVICE where SYSTEM_ID IN (select PROV_SYSTEM_ID from PROVISIONING_SYSTEM  WHERE PROV_SYSTEM_NAME='"+sProvisioningSystem+"') and STATUS='A' and SERVICE_ID in (select SERVICE_ID from service where SERVICE_NAME='"+sService+"')";
		this.records=oDBConnection.fecthRecords(ProvServiceCount);
		this.record=this.records.get(0);
		ProvServiceCount=record.get("COUNT(PROVSION_SERVICE_ID)");
		ProvService_Count = Integer.parseInt(ProvServiceCount);
		System.out.println("ProvService_Count is "+ProvService_Count);
		
		
		// ProvService_Count is the count to check the given service is allready with the given provisioning system ProvService_Count is zero then the service is added to the provisiong system   
		if(ProvService_Count ==0)       
		{

			if(sProvisioningSystem.length()!=0)
			{
				new Select(driver.findElement(ddlProvSystem)).selectByVisibleText(sProvisioningSystem);
			}
			Thread.sleep(3000);

			// ServiceCountWithProvsystem is the count to check how many active services are present with the given provisioning system
			String ServiceCountWithProvsystem = "select COUNT(PROVSION_SERVICE_ID) from PROVSION_SERVICE where SYSTEM_ID IN (select PROV_SYSTEM_ID from PROVISIONING_SYSTEM  WHERE PROV_SYSTEM_NAME='"+sProvisioningSystem+"') and STATUS='A'";
			this.records=oDBConnection.fecthRecords(ServiceCountWithProvsystem);
			this.record=this.records.get(0);
			ServiceCountWithProvsystem=record.get("COUNT(PROVSION_SERVICE_ID)");
			Service_CountWithProvsystem= Integer.parseInt(ServiceCountWithProvsystem);
			System.out.println("ServiceCountWithProvsystem is "+Service_CountWithProvsystem);

			
			// if the ServiceCountWithProvsystem is Greater than  zero then the row count is taken from code i.e (with tr's) 
			if(Service_CountWithProvsystem>0)
			{

				WebElement table = driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_gvProvService']/tbody"));
				listOfRows = table.findElements(By.tagName("tr"));
				i=listOfRows.size();
				System.out.println("Rows: "+listOfRows.size());
				System.out.println("test@2");
				j=i+1;
				System.out.println("start j value" +j);
			}

			// if the ServiceCountWithProvsystem is equal to zero then J value is 2 which is first service.
			else 
			{
				j=2;
			}

			driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_ancAdd']")).click();
			Thread.sleep(2000);

			// if the J value is greater than 10 then the xpath is changeing 
			if (j>=10)
			{
				new Select(driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_gvProvService_ctl"+j+"_ddlCategory']"))).selectByVisibleText(sCategory);
				Thread.sleep(2000);
				new Select(driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_gvProvService_ctl"+j+"_ddlServiceEvent']"))).selectByVisibleText(sService);
				Thread.sleep(3000);
				String ServiceCasCode = randomAlphaNumeric(3);
				driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_gvProvService_ctl"+j+"_txtCode']")).sendKeys(ServiceCasCode);
				driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_ancUpdate']")).click();
				Thread.sleep(2000);
			}

			else 
			{
				new Select(driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_gvProvService_ctl0"+j+"_ddlCategory']"))).selectByVisibleText(sCategory);
				Thread.sleep(2000);
				new Select(driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_gvProvService_ctl0"+j+"_ddlServiceEvent']"))).selectByVisibleText(sService);
				Thread.sleep(3000);
				String ServiceCasCode = randomAlphaNumeric(4);
				driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_gvProvService_ctl0"+j+"_txtCode']")).sendKeys(ServiceCasCode);
				driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_ancUpdate']")).click();
				Thread.sleep(2000);
			}

			if(Save_Cancel.contains("Save"))	
			{
				driver.findElement(btnsave).click();
			}
			else
			{
				driver.findElement(btncancel).click();
			}
			ScreenValidation=driver.findElement(ValidationMessage).getText();
			Thread.sleep(2000);
			System.out.println(ScreenValidation);
		}

		else 
		{
			System.out.println("The Given service is already mapped with the given ProvisioningSystem in the Provisioning Service Screen");
			String output="The Given service is already mapped with the given ProvisioningSystem in the Provisioning Service Screen";
			return output;
		}

		return ScreenValidation;
	 }

	
	
	
	
	
}
