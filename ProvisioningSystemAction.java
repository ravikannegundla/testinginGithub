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

public class ProvisioningSystemAction 

{
	
	private WebDriver driver;
	public Navigate oNavigate;
	WaitTool oWaitTool;
	
	private MQDBConnection oDBConnection;
	public ArrayList<Hashtable<String, String>> records;
    public Hashtable<String,String> record;
    

	public By LinkAdd = By.name("ctl00$uxPgCPH$LinkAdd");
	public By BtnSave = By.name("ctl00$uxPgCPH$BtnSave");
	public By BtnCancel = By.name("ctl00$uxPgCPH$BtnCancel");
	public By PROV_SYSTEM = By.name("ctl00$uxPgCPH$PROV_SYSTEM");
	public By ValidationMessage=By.id("ctl00__infoSpan");
	
	String ScreenValidation;
    int ProvService_Count,record1;
    
    
    public ProvisioningSystemAction(WebDriver driver,MQDBConnection dbConnection)
    
	{
		this.driver=driver;
		this.oDBConnection=dbConnection;
		this.oNavigate=new Navigate(driver,oDBConnection); 
		PageFactory.initElements(driver, this);
	}
    
    
    
    public  String ProvisioningActionMapping(String sProvisioningSystem, ArrayList<String> abc, String Save_Cancel) throws Exception
    
	 {
	
    	String InactiveTheOldActions = "update provsion_system_action set STATUS='I' WHERE SYSTEM_ID IN (select PROV_SYSTEM_ID from PROVISIONING_SYSTEM  WHERE PROV_SYSTEM_NAME='"+sProvisioningSystem+"') and STATUS='A'";
    	this.record1=oDBConnection.updateRecords(InactiveTheOldActions);
    	System.out.println(" Records are updated and commited succefully");

    	
    	if(sProvisioningSystem.length()!=0)
		{
			new Select(driver.findElement(PROV_SYSTEM)).selectByVisibleText(sProvisioningSystem);
		}
		Thread.sleep(3000);

		int j=2;
		for(int i=0;i<abc.size();i++)
		{

			driver.findElement(LinkAdd).click();
			System.out.println(" Add button clicked @ "+i);
			String getActionDesc="select ACTION_DESC from PROVSION_ACTION where ACTION_ID='"+abc.get(i)+"'";
			
			
			this.records=oDBConnection.fecthRecords(getActionDesc);
			this.record=this.records.get(0);
			String getActionDesc1=record.get("ACTION_DESC");
			System.out.println("Action desc  is "+getActionDesc1);
			new Select(driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_DetailsGrid_ctl0"+j+"_ACTION_DESC']"))).selectByVisibleText(getActionDesc1);

			j=j+1;
		}
		
		
		if(Save_Cancel.contains("Save"))	
		{
			driver.findElement(BtnSave).click();
		}
		else
		{
			driver.findElement(BtnCancel).click();
		}
		ScreenValidation=driver.findElement(ValidationMessage).getText();
		Thread.sleep(2000);
		System.out.println(ScreenValidation);
		
		
		return ScreenValidation;
		
    		
	 }
    
    
    

}
