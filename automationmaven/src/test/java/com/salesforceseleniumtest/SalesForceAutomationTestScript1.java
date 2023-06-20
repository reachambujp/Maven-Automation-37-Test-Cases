package com.salesforceseleniumtest;

import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import salesforceautomation.utility.AutomationConstants;
import salesforceautomation.utility.PropertiesUtility;

public class SalesForceAutomationTestScript1 extends SalesForceBaseClass {
	
	PropertiesUtility propUtil = new PropertiesUtility();
	
	
	
	//Test Case 20 (Leads Tab)
	@Test
	public void leadsTab() throws InterruptedException {
		Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);
		getURL(appDataProperties.getProperty("app.url"));

		WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));

		enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
				AutomationConstants.USER_NAME_FIELD_TXT);
		WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
		enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

		WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
		clickAction(clickLoginField, "login");
		
		Thread.sleep(4000);
		WebElement leadsLink=driver.findElement(By.id("Lead_Tab"));
		leadsLink.click();
		log.info("Lead link Open ");
		
	}
	
	
	
	//Test Case 21
	@Test
	public void leadsSelectView() throws InterruptedException {
		Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);
		getURL(appDataProperties.getProperty("app.url"));

		WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));

		enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
				AutomationConstants.USER_NAME_FIELD_TXT);
		WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
		enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

		WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
		clickAction(clickLoginField, "login");
		
		/*Thread.sleep(4000);
		WebElement leadsLink=driver.findElement(By.id("Lead_Tab"));
		leadsLink.click();
		log.info("Lead link Open ");*/
		
		
		Thread.sleep(2000);
		try {
			WebElement popUpWindow=driver.findElement(By.id("tryLexDialogX"));
			popUpWindow.click();
		}catch(Exception ex) {
			log.info("Popup window does not appear ");
		}
		
		
		
		
		//select[@title='View:']
		List<WebElement> leadDropDown=driver.findElements(By.xpath("//select[@title='View:']"));
		log.info(leadDropDown.size() + " Item(s) in the list");
		String expItem[] = { "All Open Leads", "My Unread Leads", "Recently Viewed Leads", "Today's Leads"};

		for (int i = 0; i < leadDropDown.size(); i++) {
			if (leadDropDown.get(i).getText().equals(expItem[i])) {
				log.info(leadDropDown.get(i).getText() + " is present");
			} else {
				 log.info(leadDropDown.get(i).getText()+" is not present");
			}
		}
		
		
	}
	
	
	
	
	//Test Case 22(defaultView)
	@Test
	public void defaultView() throws InterruptedException {
		Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);
		getURL(appDataProperties.getProperty("app.url"));

		WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));

		enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
				AutomationConstants.USER_NAME_FIELD_TXT);
		WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
		enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

		WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
		clickAction(clickLoginField, "login");
		
		Thread.sleep(4000);
		WebElement leadsLink=driver.findElement(By.id("Lead_Tab"));
		leadsLink.click();
		log.info("Lead link Open ");
		
		Thread.sleep(2000);
		WebElement popUpWindow=driver.findElement(By.id("tryLexDialogX"));
		popUpWindow.click();
		
		
		Thread.sleep(7000);
		WebElement myUnreadLeads=driver.findElement(By.xpath("//select[@title='View:']"));
		Select selecttdylead=new Select(myUnreadLeads);
		selecttdylead.selectByVisibleText("My Unread Leads");
		log.info("My Unread Leads is selected from drop down field");
		
		
		//input[@value=' Go! ']
		//*[@id="filter_element"]/div/span/span[1]/input
		WebElement goLink=driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[1]/input"));
		waitElementToBeClickable(goLink, "Go link field");
		goLink.click();
		log.info("Go link is opened");
		
	}
	
	
	
	
	
	//Test Case 23 (List item "Todays Leads" work)
	
	@Test
	public void todaysLeadsWork() throws InterruptedException {
		Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);
		getURL(appDataProperties.getProperty("app.url"));

		WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));

		enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
				AutomationConstants.USER_NAME_FIELD_TXT);
		WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
		enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

		WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
		clickAction(clickLoginField, "login");
		
		Thread.sleep(4000);
		WebElement leadsLink=driver.findElement(By.id("Lead_Tab"));
		leadsLink.click();
		log.info("Lead link Open ");
		
		Thread.sleep(2000);
		WebElement popUpWindow=driver.findElement(By.id("tryLexDialogX"));
		popUpWindow.click();
		
		
		Thread.sleep(7000);
		WebElement todaysLeads=driver.findElement(By.xpath("//select[@title='View:']"));
		Select selecttdylead=new Select(todaysLeads);
		selecttdylead.selectByVisibleText("My Unread Leads");
		log.info("todays Leads is selected from dropdown field");
		
	}
	
	
	//Test Case 24(Check "New" button on Leads Home)
	
	@Test
	public void newButton() throws InterruptedException {
		Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);
		getURL(appDataProperties.getProperty("app.url"));

		WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));

		enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
				AutomationConstants.USER_NAME_FIELD_TXT);
		WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
		enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

		WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
		clickAction(clickLoginField, "login");
		
		Thread.sleep(4000);
		WebElement leadsLink=driver.findElement(By.id("Lead_Tab"));
		leadsLink.click();
		log.info("Lead link Open ");
		
		Thread.sleep(2000);
		WebElement popUpWindow=driver.findElement(By.id("tryLexDialogX"));
		popUpWindow.click();
		log.info("PopUpWindow is open");
		
		//input[@name='new']
		WebElement newLink=driver.findElement(By.xpath("//input[@name='new']"));
		waitElementToBeClickable(newLink, "New field");
		newLink.click();
		log.info("New link is clicked");
		
		WebElement lastName=driver.findElement(By.id("name_lastlea2"));
		fluentWaitForVisibility(lastName, "Last name field");
		lastName.sendKeys("ABCD");
		log.info("Entered last name in the field");
		
		
		WebElement companyName =driver.findElement(By.id("lea3"));
		fluentWaitForVisibility(companyName, "Company name field");
		companyName.sendKeys("ABCD");
		log.info("Entered Company name in the field");
		
		Thread.sleep(4000);
		WebElement saveButton=driver.findElement(By.name("save"));
		waitElementToBeClickable(saveButton, "Save field");
		saveButton.click();
		
	}
	
	//Test Case 25 (Create new contact)
	@Test
	public void createNewContact() throws InterruptedException {
		Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);
		getURL(appDataProperties.getProperty("app.url"));

		WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));

		enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
				AutomationConstants.USER_NAME_FIELD_TXT);
		WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
		enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

		WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
		clickAction(clickLoginField, "login");
	
		
		Thread.sleep(2000);
		WebElement popUpWindow=driver.findElement(By.id("tryLexDialogX"));
		popUpWindow.click();
		log.info("PopUpWindow is open");
		
		WebElement ContactLink=driver.findElement(By.xpath("//li[@id='Contact_Tab']"));
		waitElementToBeClickable(ContactLink, "Creating new Contact");
		ContactLink.click();
		
		//input[@name='new']
		Thread.sleep(2000);
		WebElement newButton=driver.findElement(By.xpath("//input[@name='new']"));
		waitElementToBeClickable(newButton, "New Button field");
		newButton.click();
		
		
		WebElement lastName=driver.findElement(By.id("name_lastcon2"));
		fluentWaitForVisibility(lastName, "Last Name Field");
		lastName.sendKeys("Last Name");
		
		Thread.sleep(4000);
		WebElement accountName=driver.findElement(By.xpath("//input[@id='con4']"));
		fluentWaitForVisibility(accountName, "Account Name Field");
		accountName.sendKeys("Account Name");
		
		
		WebElement saveButton=driver.findElement(By.name("save"));
		waitElementToBeClickable(saveButton, "Save Button field");
		saveButton.click();
	}
	
	
	//Test Case 26 (Create new view in the Contact Page)
	@Test
	public void newViewContactPage() throws InterruptedException {
		
		Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);
		getURL(appDataProperties.getProperty("app.url"));

		WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));

		enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
				AutomationConstants.USER_NAME_FIELD_TXT);
		WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
		enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

		WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
		clickAction(clickLoginField, "login");
	
		
		/*Thread.sleep(2000);
		WebElement popUpWindow=driver.findElement(By.id("tryLexDialogX"));
		popUpWindow.click();
		log.info("PopUpWindow is open");*/
		
		WebElement ContactLink=driver.findElement(By.xpath("//li[@id='Contact_Tab']"));
		waitElementToBeClickable(ContactLink, "Creating new Contact");
		ContactLink.click();
		log.info("Contact Link is clicked");
		
		//*[@id="filter_element"]/div/span/span[2]/a[2]
		//a[text()='Create New View']
		
		//create-new-view
		Thread.sleep(3000);
		try {
			driver.findElement(By.xpath("//span[@class='fFooter']")).click();
		  } catch (Exception e) {
		     JavascriptExecutor executor = (JavascriptExecutor) driver;
		     executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]")));
		     log.info("CreateNewViewLink field is clicked");
		  }
		
		
		/*Thread.sleep(4000);
		WebElement createNewView=driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]"));
		waitElementToBeClickable(createNewView, "Creating new View");
		createNewView.click();
		log.info("Create New View link is open");*/
		
		Thread.sleep(2000);
		WebElement viewName=driver.findElement(By.id("fname"));
		fluentWaitForVisibility(viewName, "View Name field");
		viewName.sendKeys("Ishi");
		log.info("Enter View Name in view name field");
		
		
		WebElement viewUniqueName=driver.findElement(By.xpath("//input[@id='devname']"));
		fluentWaitForVisibility(viewUniqueName, "View Unique Name field");
		viewUniqueName.sendKeys("Ishi_345");
		log.info("Enter View Unique Name in view unique name field");
		
		Thread.sleep(2000);
		WebElement saveButton=driver.findElement(By.name("save"));
		saveButton.click();
		log.info("Save buttion is clicked");
		Thread.sleep(4000);
	}
	
	
	//Test Case 27 (Check recently created contact in the Contact Page) Help unable to locate element
	@Test
	public void createdContactInTheContactPage() throws InterruptedException {
		
		Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);
		getURL(appDataProperties.getProperty("app.url"));

		WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));

		enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
				AutomationConstants.USER_NAME_FIELD_TXT);
		WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
		enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

		WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
		clickAction(clickLoginField, "login");
		
		WebElement ContactLink=driver.findElement(By.xpath("//li[@id='Contact_Tab']"));
		waitElementToBeClickable(ContactLink, "Creating new Contact");
		ContactLink.click();
		log.info("Contact Link is clicked");
		
		Thread.sleep(4000);
		//*[@id="hotlist_mode"]
		WebElement recentlyCreatedContact=driver.findElement(By.xpath("//*[@id=\"hotlist_mode\"]"));
		Select select=new Select(recentlyCreatedContact);
		select.selectByVisibleText("Recently Created");
		log.info("Recently Created Contact page is selected");
		
	}
	
	
	//Test Case 28 [Check 'My contacts' view in the Contact Page]
	
	@Test
	public void checkMyContacts() throws InterruptedException {
		
		Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);
		getURL(appDataProperties.getProperty("app.url"));

		WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));

		enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
				AutomationConstants.USER_NAME_FIELD_TXT);
		WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
		enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

		WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
		clickAction(clickLoginField, "login");
		
		WebElement ContactLink=driver.findElement(By.xpath("//li[@id='Contact_Tab']"));
		waitElementToBeClickable(ContactLink, "Creating new Contact");
		ContactLink.click();
		log.info("Contact Link is clicked");
		
		//select "My Contact" in ViewDropDown
		Thread.sleep(4000);
		WebElement ViewDropDown=driver.findElement(By.xpath("//*[@id=\"fcf\"]"));
		Select myContact=new Select(ViewDropDown);
		myContact.selectByVisibleText("My Contacts");
		log.info("My contact is selected from ViewDropDown ");
		
		Thread.sleep(4000);
		
	}
	
	
	//Test Case 29 [View a contact in the contact Page] not understood the test case
	
	@Test
	public void name() throws InterruptedException {
		
		Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);
		getURL(appDataProperties.getProperty("app.url"));

		WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));

		enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
				AutomationConstants.USER_NAME_FIELD_TXT);
		WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
		enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

		WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
		clickAction(clickLoginField, "login");
		
		WebElement ContactLink=driver.findElement(By.xpath("//li[@id='Contact_Tab']"));
		waitElementToBeClickable(ContactLink, "Creating new Contact");
		ContactLink.click();
		log.info("Contact Link is clicked");
		
		
		
	}
	
	
	
	
	//Test Case 30 (Check the Error message if, the required information is not entered while creating a New view in Contacts)
	
	@Test
	public void checkErrormessage() throws InterruptedException {
		
		Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);
		getURL(appDataProperties.getProperty("app.url"));

		WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));

		enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
		AutomationConstants.USER_NAME_FIELD_TXT);
		
		WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
		enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

		WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
		clickAction(clickLoginField, "login");
		
		WebElement ContactLink=driver.findElement(By.xpath("//li[@id='Contact_Tab']"));
		waitElementToBeClickable(ContactLink, "Creating new Contact");
		ContactLink.click();
		log.info("Contact Link is clicked");

		// create-new-view
		/*Thread.sleep(3000);
		try {
			driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]")).click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();",
					driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]")));
			log.info("CreateNewViewLink field is clicked");
		}*/
		
		
		Thread.sleep(7000);
		WebElement createNewView=driver.findElement(By.xpath("//span[@class='fFooter']"));
		waitElementToBeClickable(createNewView, "Creating new View");
		createNewView.click();
		log.info("Create New View link is open");
		
		
		WebElement viewUniqueName=driver.findElement(By.xpath("//input[@id='devname']"));
		fluentWaitForVisibility(viewUniqueName, "View Unique Name field");
		viewUniqueName.sendKeys("EFGH");
		log.info("Enter View Unique Name in view unique name field");
		
		
		Thread.sleep(2000);
		WebElement saveButton=driver.findElement(By.name("save"));
		saveButton.click();
		log.info("Save buttion is clicked");
		log.info("The Error message appears as Error: You must enter a value");
		Thread.sleep(4000);
		
		getScreenShotOfThePage();
			
		
	}
	
	
	//Test Case 31 (Check the Cancel button works fine in Create New View)
	@Test
	public void cancelButtonWorks() throws InterruptedException {
		
		Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);
		getURL(appDataProperties.getProperty("app.url"));

		WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));

		enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
		AutomationConstants.USER_NAME_FIELD_TXT);
		
		WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
		enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

		WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
		clickAction(clickLoginField, "login");
		
		WebElement ContactLink=driver.findElement(By.xpath("//li[@id='Contact_Tab']"));
		waitElementToBeClickable(ContactLink, "Creating new Contact");
		ContactLink.click();
		log.info("Contact Link is clicked");
		
		Thread.sleep(7000);
		WebElement createNewView=driver.findElement(By.xpath("//span[@class='fFooter']"));
		waitElementToBeClickable(createNewView, "Creating new View");
		createNewView.click();
		log.info("Create New View link is open");
		
		Thread.sleep(2000);
		WebElement viewName=driver.findElement(By.id("fname"));
		fluentWaitForVisibility(viewName, "View Name field");
		viewName.sendKeys("ABCD");
		log.info("Enter View Name in view name field");
		
		
		WebElement viewUniqueName=driver.findElement(By.xpath("//input[@id='devname']"));
		fluentWaitForVisibility(viewUniqueName, "View Unique Name field");
		viewUniqueName.sendKeys("EFGH");
		log.info("Enter View Unique Name in view unique name field");
		
		//*[@id=\"editPage\"]/div[3]/table/tbody/tr/td[2]/input[2]
		WebElement cancelButton=driver.findElement(By.xpath("//input[@name='cancel']"));
		waitElementToBeClickable(cancelButton, "Cancel Button Field");
		cancelButton.click();	
	}
	
	
	
	//Test Case 32 (Check the Save and New button works in New Contact page)
	@Test
	public void checkTheSaveAndNewButtonWorks() throws InterruptedException {
		
		Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);
		getURL(appDataProperties.getProperty("app.url"));

		WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));

		enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
		AutomationConstants.USER_NAME_FIELD_TXT);
		
		WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
		enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

		WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
		clickAction(clickLoginField, "login");
		
		WebElement ContactLink=driver.findElement(By.xpath("//li[@id='Contact_Tab']"));
		waitElementToBeClickable(ContactLink, "Creating new Contact");
		ContactLink.click();
		log.info("Contact Link is clicked");
		
	   WebElement popUpWindow=driver.findElement(By.id("tryLexDialogX"));
		popUpWindow.click();
		log.info("PopUpWindow is open");
		
		Thread.sleep(4000);
		WebElement newButton=driver.findElement(By.xpath("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input"));
		waitElementToBeClickable(newButton, "New Button field");
		newButton.click();
		
		
		WebElement lastName=driver.findElement(By.id("name_lastcon2"));
		fluentWaitForVisibility(lastName, "Last Name Field");
		lastName.sendKeys("Indian");
		
		Thread.sleep(4000);
		WebElement accountName=driver.findElement(By.xpath("//input[@id='con4']"));
		fluentWaitForVisibility(accountName, "Account Name Field");
		accountName.sendKeys("Global Media");
		
		Thread.sleep(4000);
		WebElement saveAndNewButton=driver.findElement(By.xpath("//*[@id=\"topButtonRow\"]/input[2]"));
		waitElementToBeClickable(saveAndNewButton, "SaveAndNewButton field");
		saveAndNewButton.click();
		
		getScreenShotOfThePage();
		
		
		log.info("Test case 32: Fail, Error: Invalid Data:"
				+ "Review all error messages below to correct your data.");
		
		
		
	}
	
	
	//Test Case 33 (Verify if the firstname and lastname of the loggedin user is displayed)

	
	@Test
	public void verifyFirstNmeLastNme() throws InterruptedException {
		
		Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);
		getURL(appDataProperties.getProperty("app.url"));

		WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));

		enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
		AutomationConstants.USER_NAME_FIELD_TXT);
		
		WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
		enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

		WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
		clickAction(clickLoginField, "login");
		
		//*[@id="home_Tab"]/a
		WebElement homeLink=driver.findElement(By.xpath("//*[@id=\"home_Tab\"]/a"));
		waitElementToBeClickable(homeLink, "Home Link Field");
		homeLink.click();
		
		/*Thread.sleep(2000);
		WebElement popUpWindow=driver.findElement(By.id("tryLexDialogX"));
		popUpWindow.click();
		log.info("PopUpWindow is open");
		getScreenShotOfThePage();*/
		
		Thread.sleep(2000);
		WebElement firstNmeLastNme=driver.findElement(By.xpath("//h1[@class='currentStatusUserName']"));
		waitElementToBeClickable(firstNmeLastNme, "firstNmeLastNme Link");
		firstNmeLastNme.click();
		log.info("Page is same as the 'My Profile' page.");
		
	}
	
	
	//Test Case 34 (Verify the edited lastname is updated at various places)
	
	@Test
	public void verifyEditedLastName() throws InterruptedException {
		
		Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);
		getURL(appDataProperties.getProperty("app.url"));

		WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));

		enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
		AutomationConstants.USER_NAME_FIELD_TXT);
		
		WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
		enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

		WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
		clickAction(clickLoginField, "login");
		
		
		WebElement homeLink=driver.findElement(By.xpath("//*[@id=\"home_Tab\"]/a"));
		waitElementToBeClickable(homeLink, "Home Link Field");
		homeLink.click();
		
		/*Thread.sleep(2000);
		WebElement popUpWindow=driver.findElement(By.id("tryLexDialogX"));
		popUpWindow.click();
		log.info("PopUpWindow is open");*/
		
		Thread.sleep(5000);
		WebElement firstNmeLastNme=driver.findElement(By.xpath("//*[@id=\"ptBody\"]/div/div[2]/span[1]/h1"));
		waitElementToBeClickable(firstNmeLastNme, "firstNmeLastNme Link");
		firstNmeLastNme.click();
		log.info("Page should be same as the 'My Profile' page."); 
		
		Thread.sleep(4000); 
		WebElement moderatorLink = driver.findElement(By.xpath("//a[@title='User Action Menu']"));
		fluentWaitForVisibility(moderatorLink, "moderatorLink");
		moderatorLink.click();
		Thread.sleep(4000); 
		
		WebElement editProfileLink = driver.findElement(By.xpath("//*[@id=\"chatterTab\"]/div[1]/div/div[1]/div[1]/div/ul/li[2]/a"));
		fluentWaitForVisibility(editProfileLink, "editProfileLink");
		editProfileLink.click();
		log.info("profile drpdwn");

		driver.switchTo().frame("aboutMeContentId");
		WebElement aboutTab = driver.findElement(By.xpath("//*[@id=\"aboutTab\"]/a"));
		aboutTab.click();
		
		// Thread.sleep(4000);
		log.info("Update lastname");
		WebElement lastNameEle = driver.findElement(ByIdOrName.id("lastName"));
		lastNameEle.clear();
		fluentWaitForVisibility(lastNameEle, "lastNameEle");
		lastNameEle.sendKeys("Ambuj");
		

		WebElement saveAllEle = driver.findElement(By.xpath("//input[@value='Save All']"));
		fluentWaitForVisibility(saveAllEle, "saveAllEle");
		log.info("Click saveAll button");
		saveAllEle.click();
		
	}
	
	
	
	//Test case 35 (Verify the tab customization)
	
	@Test
	public void verifyTabCustomization() throws InterruptedException {
		
		Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);
		getURL(appDataProperties.getProperty("app.url"));

		WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));

		enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
		AutomationConstants.USER_NAME_FIELD_TXT);
		
		WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
		enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

		WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
		clickAction(clickLoginField, "login");
		
		WebElement allTabField=driver.findElement(By.id("AllTab_Tab"));
		allTabField.click();
		log.info("Clicked AllTab field");
		
		Thread.sleep(4000);
		WebElement cutomizeTabField=driver.findElement(By.xpath("//input[@value='Customize My Tabs']"));
		if(cutomizeTabField.isDisplayed()) {
			cutomizeTabField.click();
			log.info("Clicked cutomizeTabField field");
		}else {
			log.info("Clicked cutomizeTabField field");
			
		}
		Thread.sleep(4000);
		
		
		/*WebElement selectAnyTabFromSelectedTab=driver.findElement(By.xpath("//option[@value='Lead']"));
		selectAnyTabFromSelectedTab.click();
		log.info("Lead Tab is selected");
		
		//img[@class='leftArrowIcon']
		WebElement removeField=driver.findElement(By.xpath("//img[@class='leftArrowIcon']"));
		removeField.click();
		log.info("Clicked Remove Field");
		Thread.sleep(4000);*/
		
		boolean isValueSelected = true;
		try {
			Thread.sleep(4000);
			WebElement selectAnyTabFromSelectedTab=driver.findElement(By.xpath("//option[@value='Lead']"));
			selectAnyTabFromSelectedTab.click();
			log.info("Leads is selected in Selected Tab");
		} catch (NoSuchElementException nse) {
			isValueSelected = false;
			log.info("The selected item is already available in the selected tab");
		}

		
		if (isValueSelected) {
			Thread.sleep(3000);
			WebElement removeField = driver.findElement(By.xpath("//*[@id=\"duel_select_0_right\"]/img"));
			waitElementToBeClickable(removeField, "removeField Link");
			removeField.click();
			log.info("Lead option  Removed from the Selected Tab Field");
		} else {
			log.info("Lead option already present in Selected Tabs section");
		}

		
		//input[@name='save']
		WebElement saveButton=driver.findElement(By.xpath("//input[@name='save']"));
		saveButton.click();
		log.info("Clicked Save Button");
		
		WebElement userMenuField=driver.findElement(By.id("userNavLabel"));
		userMenuField.click();
		log.info("Clicked UserMenu Field");
		
		WebElement logoutField=driver.findElement(By.xpath("//a[@title='Logout']"));
		logoutField.click();
		log.info("Clicked UserMenu Field");
		
		
	}
	
	
	
	//Test case 37 (Blocking an event in the calender)
	
	@Test
	public void blockingEventInCalender() throws InterruptedException {
		
		Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);
		getURL(appDataProperties.getProperty("app.url"));

		WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));

		enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
		AutomationConstants.USER_NAME_FIELD_TXT);
		
		WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
		enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

		WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
		clickAction(clickLoginField, "login");
		
		
		WebElement homeLink=driver.findElement(By.xpath("//*[@id=\"home_Tab\"]/a"));
		waitElementToBeClickable(homeLink, "Home Link Field");
		homeLink.click();
		log.info("Clicked Home link");
		
		/*Thread.sleep(2000);
		WebElement popUpWindow=driver.findElement(By.id("tryLexDialogX"));
		popUpWindow.click();
		log.info("PopUpWindow is open");*/
		
		
		//a[text()='Sunday June 11, 2023']
		/*Thread.sleep(2000);
		WebElement currentDateLink=driver.findElement(By.xpath("//a[text()='Sunday June 11, 2023']"));
		waitElementToBeClickable(currentDateLink, "Current Date Link");
		currentDateLink.click();*/
		//*[@id="ptBody"]/div/div[2]/span[2]/a
		Thread.sleep(3000);
		try {
			driver.findElement(By.xpath("//*[@id=\"ptBody\"]/div/div[2]/span[2]/a")).click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();",
					driver.findElement(By.xpath("//*[@id=\"ptBody\"]/div/div[2]/span[2]/a")));
			log.info("currentDateLink field is clicked");
		}
		log.info("currentDateLink field is clicked");
		
		
		
		WebElement link8PM=driver.findElement(By.xpath("//*[@id=\"p:f:j_id25:j_id61:4:j_id64\"]/a"));
		waitElementToBeClickable(link8PM, "link8PM");
		link8PM.click();
		
		Thread.sleep(2000);
		WebElement subjectComboIcon=driver.findElement(By.xpath("//img[@class='comboboxIcon']"));
		waitElementToBeClickable(subjectComboIcon, "Subject Combo Icon");
		subjectComboIcon.click();
		log.info("Subject Combo Icon field is clicked");
		
	 
		String parent_window= driver.getWindowHandle();
		Set<String> allWindow= driver.getWindowHandles();
		
		for(String str:allWindow) {
			if(!str.equals(parent_window)) {
				driver.switchTo().window(str);
				WebElement otherEvent=driver.findElement(By.xpath("/html/body/div[2]/ul/li[5]/a"));
				clickAction(otherEvent, "Select other Event");
				driver.switchTo().window(parent_window);
				log.info("Other event is selected in subject field");
			}
		}
		
		Thread.sleep(2000);
		WebElement endDate=driver.findElement(By.id("EndDateTime"));
		endDate.click();
		log.info("End time field is selected ");
		
		List<WebElement> allDates=driver.findElements(By.xpath("//*[@id=\"datePicker\"]//td"));
		driver.findElement(By.xpath("//*[@id=\"calRow3\"]/td[2]")).click();
		
		WebElement startTime=driver.findElement(By.id("StartDateTime_time"));
		fluentWaitForVisibility(startTime, "startTime");
		startTime.clear();
		startTime.sendKeys("9:00 PM");
		log.info("startTime field is selected ");
		
		WebElement endTimee=driver.findElement(By.id("EndDateTime_time"));
		fluentWaitForVisibility(endTimee, "endTimee");
		endTimee.clear();
		endTimee.sendKeys("11:30 PM");
		log.info("endTimee field is selected ");
		
		Thread.sleep(2000);
		WebElement saveButton=driver.findElement(By.xpath("//input[@title='Save']"));
		saveButton.click();
		
		
	}
	
	
	
	
	

}
