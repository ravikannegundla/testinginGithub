package com.ProvisioningPageObject;

import java.util.ArrayList;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Utility.Navigate;
import Utility.WaitTool;
import mq.seleniumFW.connection.MQDBConnection;

public class HardwareForProvisioning 
{
	private WebDriver driver;
	public Navigate oNavigate;
	WaitTool oWaitTool;
	
	private MQDBConnection oDBConnection;
	
	public By btnsave = By.name("ctl00$uxPgCPH$btnsave");
	public By btncancel = By.name("ctl00$uxPgCPH$btncancel");
	public By cmbProv = By.name("ctl00$uxPgCPH$cmbProv");

	public By ValidationMessage=By.id("ctl00__infoSpan");
	public ArrayList<Hashtable<String, String>> records;
    public Hashtable<String,String> record;
    int Hardware_Count;
    String ScreenValidation;
    int record1;
    
	public HardwareForProvisioning(WebDriver driver,MQDBConnection dbConnection)
	{
		this.driver=driver;
		this.oDBConnection=dbConnection;
		this.oNavigate=new Navigate(driver,oDBConnection); 
		PageFactory.initElements(driver, this);
	}


	
	
	
	
	public  String HardwareMappingForProvisioningSystem(String sProvisioningSystem,String sPrimaryItem,String sSecondaryItem,String Save_Cancel) throws Exception
    
	 {
		String InactiveTheOldHardwaredetails = "update prov_system_hw_map set STATUS='I' where PROV_SYSTEM_ID IN (select PROV_SYSTEM_ID from PROVISIONING_SYSTEM  WHERE PROV_SYSTEM_NAME='"+sProvisioningSystem+"') and STATUS='A'";
		this.record1=oDBConnection.updateRecords(InactiveTheOldHardwaredetails);
		System.out.println(" Records are updated and commited succefully");
		Thread.sleep(2000);

		if(sProvisioningSystem.length()!=0)
		{
			new Select(driver.findElement(cmbProv)).selectByVisibleText(sProvisioningSystem);
		}
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_btnadd']")).click();
		Thread.sleep(2000);
		new Select(driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvitem_ctl02_ddlClassCode']"))).selectByVisibleText("TV Channel");
		Thread.sleep(2000);
		new Select(driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvitem_ctl02_dhwitem']"))).selectByVisibleText(sPrimaryItem);
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvitem_ctl02_Isprimary']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_btnadd']")).click();

		System.out.println("adding the primary hardware is completed");

		Thread.sleep(2000);
		new Select(driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvitem_ctl03_ddlClassCode']"))).selectByVisibleText("TV Channel");
		Thread.sleep(2000);
		new Select(driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_grdvitem_ctl03_dhwitem']"))).selectByVisibleText(sSecondaryItem);
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_btnupdate']")).click();

		System.out.println("adding the SecondaryItem hardware is completed");

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

		return ScreenValidation;
		
	 }
	
	
	
	
	
}
