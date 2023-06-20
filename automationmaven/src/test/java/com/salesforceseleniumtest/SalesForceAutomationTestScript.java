
package com.salesforceseleniumtest;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import salesforceautomation.utility.AutomationConstants;
import salesforceautomation.utility.ExtentReportUtility;
import salesforceautomation.utility.PropertiesUtility;

public class SalesForceAutomationTestScript extends SalesForceBaseClass {

	 protected ExtentReportUtility report=ExtentReportUtility.getInstance();

	
	PropertiesUtility propUtil = new PropertiesUtility();
	
	
	@Test
	
	public void loginToSalesForceTestScript() {

		Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);
		getURL(appDataProperties.getProperty("app.url"));

		WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));
		enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
				AutomationConstants.USER_NAME_FIELD_TXT);

		WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
		enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

		WebElement clickRememberChkBox = driver.findElement(By.xpath("//input[@id='rememberUn']"));
		clickAction(clickRememberChkBox, "remember me");

		WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
		clickAction(clickLoginField, "login");

		WebElement userMenuDropdown = driver.findElement(By.xpath("//span[contains(@id,userNavLabel)]"));
		clickAction(userMenuDropdown, "UserMenu");

		WebElement logOutField = driver.findElement(By.xpath("//a[@title='Logout']"));
		clickAction(logOutField, "Logout");
		
		//report.logTestPassed("Test passed");
		

	}

	// Test Case 4A (forgetYourPassword) 
    @Test
	public void forgetYourPassword() throws InterruptedException {
    	
    	Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);
		getURL(appDataProperties.getProperty("app.url"));
        
		Thread.sleep(4000);
		WebElement forgetYourPasswordField = driver.findElement(By.xpath("//a[@id='forgot_password_link']"));
		clickAction(forgetYourPasswordField, "forgetPassword");
		log.info("Forget password field is clicked");
		Thread.sleep(4000);
		
		WebElement resetUserName=driver.findElement(By.id("un"));
		enterTextValue(resetUserName, appDataProperties.getProperty("reset.username"), "resetUserNameField");
		log.info("Entered UserName in resetUserName field");
		
		WebElement continueButton=driver.findElement(By.id("continue"));
		continueButton.click();
		log.info("Clicked Continue Button");
		Thread.sleep(4000);

	}
    
    
    //Test Case 4B (ValidateLoginErrorMessage)
    @Test
    public void validateLoginErrorMessage() throws InterruptedException {
    	
    	
    	
    	Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);
		getURL(appDataProperties.getProperty("app.url"));
		
		Thread.sleep(2000);
		WebElement wrongUserNameElement = driver.findElement(By.xpath("//input[@id='username']"));
		enterTextValue(wrongUserNameElement, appDataProperties.getProperty("invalid.userid"), "Wrong User Name");
		
		Thread.sleep(2000);
		WebElement wrongPasswordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
		enterTextValue(wrongPasswordNameElement,appDataProperties.getProperty("invalid.password"), "Wrong Password Name");
		
		Thread.sleep(2000);
		WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
		clickAction(clickLoginField, "login");
		log.info("Error message displayed"+ " Your login attempt has failed");
				
	}

	// Test Case 5

	@Test
	public void userMenuDropDown() {
		Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);

		getURL(appDataProperties.getProperty("app.url"));

		WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));

		enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),

				AutomationConstants.USER_NAME_FIELD_TXT);

		WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
		enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

		WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
		clickAction(clickLoginField, "login");

		WebElement userMenu = driver.findElement(By.xpath("//span[@id='userNavLabel']"));
		if (userMenu.isDisplayed()) {
			userMenu.click();
			log.info("userMenu is displayed");
		} else {
			log.info("userMenu is not displayed");
		}

		WebElement baseMenu = driver.findElement(By.id("userNav"));
		WebElement subMenuOptions = baseMenu.findElement(By.id("userNav-menuItems"));
		
		log.info(subMenuOptions.getText());
		
	}

	// Test Case 6

	@Test 
	public void myProfileFromUserMenudrpdwn() throws InterruptedException {
		Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);
		getURL(appDataProperties.getProperty("app.url"));

		WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));

		enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
				AutomationConstants.USER_NAME_FIELD_TXT);
		WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
		enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

		WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
		clickAction(clickLoginField, "login");

		WebElement userMenu = driver.findElement(By.xpath("//span[@id='userNavLabel']"));
		if (userMenu.isDisplayed()) {
			userMenu.click();
			log.info("userMenu is displayed");
		} else {
			log.info("userMenu is not displayed");
		}

		WebElement baseMenu = driver.findElement(By.id("userNav"));
		WebElement subMenuOptions = baseMenu.findElement(By.id("userNav-menuItems"));
		log.info(subMenuOptions.getText());

		WebElement myProfile = driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[1]"));
		waitElementToBeClickable(myProfile, "myprofileuserdrpdwn ");

		if (myProfile.isDisplayed()) {
			myProfile.click();
			log.info("myProfile is Displayed");
		} else {
			log.info("myProfile is not Displayed");
		}
		
		Thread.sleep(4000); // waitUntilAlertIsPresent();
		WebElement moderatorLink = driver.findElement(By.xpath("//a[@title='User Action Menu']"));
		fluentWaitForVisibility(moderatorLink, "moderatorLink");
		moderatorLink.click();
		Thread.sleep(4000); 

		WebElement editProfileLink = driver.findElement(By.xpath("//*[@id=\"chatterTab\"]/div[1]/div/div[1]/div[1]/div/ul/li[2]/a"));
		fluentWaitForVisibility(editProfileLink, "editProfileLink");
		editProfileLink.click();
		log.info("profile drpdwn");

		driver.switchTo().frame("aboutMeContentId");
		//switchFrame("aboutMeContentId");
		WebElement aboutTab = driver.findElement(By.xpath("//*[@id=\"aboutTab\"]/a"));
		aboutTab.click();
		// Thread.sleep(4000);
		log.info("Update lastname");
		WebElement lastNameEle = driver.findElement(ByIdOrName.id("lastName"));
		lastNameEle.clear();
		lastNameEle.sendKeys("mishra");
		fluentWaitForVisibility(lastNameEle, "lastNameEle");

		WebElement saveAllEle = driver.findElement(By.xpath("//input[@value='Save All']"));
		fluentWaitForVisibility(saveAllEle, "saveAllEle");
		log.info("Click saveAll button");
		saveAllEle.click();

		WebElement feedTab = driver.findElement(By.id("profileTab_sfdc.ProfilePlatformFeed"));
		feedTab.click(); // *[@id="profileTab_sfdc.ProfilePlatformFeed"]

		WebElement postLink = driver.findElement(By.xpath("//*[@id=\"publisherAttachTextPost\"]/span[1]"));
		postLink.click();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		log.info("Post is clicked");

		// driver.switchTo().frame("cke_43_contents");
		WebElement postContent = driver.findElement(By.xpath("//*[@id=\"cke_43_contents\"]/iframe"));
		postContent.click();
		Actions action = new Actions(driver);
		action.sendKeys("Hello Test String").build().perform();
		
		//performAction("Hello Test String");
		Thread.sleep(3000);
		// driver.manage().timeouts().implicitlyWait(2000,TimeUnit.SECONDS);

		WebElement shareButton = driver.findElement(By.id("publishersharebutton"));
		shareButton.click();
		log.info("Post content sucessful");
		Thread.sleep(3000);

		WebElement fileUpload = driver.findElement(By.xpath("//*[@id=\"publisherAttachContentPost\"]/span[1]"));
		fileUpload.click();

		WebElement chatterUploadFile = driver.findElement(By.id("chatterUploadFileAction"));
		chatterUploadFile.click();

		WebElement chatterFile = driver.findElement(By.id("chatterFile"));
		chatterFile.sendKeys("C:\\Users\\manis\\OneDrive\\Desktop\\heart1.png");
		Thread.sleep(3000);

		WebElement shareUploadButton = driver.findElement(By.id("publishersharebutton"));
		shareUploadButton.click();
		log.info("File upload sucessful");
		Thread.sleep(3000);

	}

	// Test Case 7
	@Test
	public void mySettingFromUserMenudrpdwn() throws InterruptedException, AWTException {
		Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);

		getURL(appDataProperties.getProperty("app.url"));

		WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));
		enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
				AutomationConstants.USER_NAME_FIELD_TXT);

		WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
		enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

		WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
		clickAction(clickLoginField, "login");

		WebElement userMenu = driver.findElement(By.xpath("//span[@id='userNavLabel']"));
		if (userMenu.isDisplayed()) {
			userMenu.click();
			log.info("userMenu is displayed");
		} else {
			log.info("userMenu is not displayed");
		}

		WebElement baseMenu = driver.findElement(By.id("userNav"));
		WebElement subMenuOptions = baseMenu.findElement(By.id("userNav-menuItems"));
		log.info(subMenuOptions.getText());
		// Thread.sleep(4000);
		// My Setting

		WebElement mySetting = driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[2]"));
		waitElementToBeClickable(mySetting, "My setting element");
		mySetting.click();
		log.info("mySetting page is displayed");

		Thread.sleep(3000);

		// Click on personal link and select Login History link.

		WebElement personalLink = driver.findElement(By.id("PersonalInfo"));
		waitElementToBeClickable(personalLink, "personal Link Element");
		personalLink.click();

		log.info("Personal link is clicked");

		Thread.sleep(3000);
		// *[@id="PersonalInfo_child"]/div[8]
		WebElement loginHist = driver.findElement(By.xpath("//*[@id=\"PersonalInfo_child\"]/div[8]"));
		waitElementToBeClickable(loginHist, "login hist Element");
		loginHist.click();
		log.info("Login hist is clicked");
		Thread.sleep(3000);

		// *[@id="RelatedUserLoginHistoryList_body"]/div/a
		WebElement downloadHist = driver.findElement(By.xpath("//*[@id=\"RelatedUserLoginHistoryList_body\"]/div/a"));
		waitElementToBeClickable(downloadHist, "downloadHist");
		downloadHist.click();
		log.info("Download hist is sucessful");
		Thread.sleep(3000);

		// Click on Display & Layout link

		WebElement dispalyLayoutLink = driver.findElement(By.xpath("//*[@id=\"DisplayAndLayout\"]"));
		waitElementToBeClickable(dispalyLayoutLink, "dispalyLayoutLink");
		dispalyLayoutLink.click();
		log.info("dispalyLayoutLink is clicked");

		WebElement customizeMyTab = driver.findElement(By.xpath("//*[@id=\"DisplayAndLayout_child\"]/div[1]"));
		waitElementToBeClickable(customizeMyTab, "customize Tab");
		customizeMyTab.click();
		log.info("customizeMyTab is clicked");

		Thread.sleep(3000);
        
		Select contentField = new Select(driver.findElement(By.xpath("//*[@id=\"p4\"]")));

		contentField.selectByVisibleText("Salesforce Chatter");

		log.info("Salesforce Chatter is selected");

		Thread.sleep(3000);
		// *[@id="duel_select_0"]
		boolean isValueSelected = true;
		try {
			Select availableTabs = new Select(driver.findElement(By.xpath("//*[@id=\"duel_select_0\"]")));
			availableTabs.selectByVisibleText("Reports");
		} catch (NoSuchElementException nse) {
			isValueSelected = false;
			log.info("The selected item is already available in the selected tab");
		}

		// *[@id="duel_select_0"]

		if (isValueSelected) {
			Thread.sleep(3000);
			WebElement rightAddArrow = driver.findElement(By.xpath("//*[@id=\"duel_select_0_right\"]/img"));
			waitElementToBeClickable(rightAddArrow, "rightAddArrow");
			rightAddArrow.click();
			log.info("Reports option is added to Selected Tabs section");
		} else {
			log.info("Reports option already present in Selected Tabs section");
		}

		Thread.sleep(3000);
		WebElement saveButton = driver.findElement(By.name("save"));
		waitElementToBeClickable(saveButton, "saveButton");
		saveButton.click();

		Thread.sleep(3000);

		// Click on Email link and click on my email settings

		// *[@id="EmailSetup"]
		// *[@id="EmailSetup_child"]/div[1]
		// input[@id='sender_name']
		// input[@id='sender_email']
		// input[@value=' Save ']

		WebElement emailLink = driver.findElement(By.xpath("//*[@id=\"EmailSetup\"]"));
		waitElementToBeClickable(emailLink, "Email Link field");
		emailLink.click();
		log.info("emailLink is clicked");

		WebElement myEmailSetting = driver.findElement(By.xpath("//*[@id=\"EmailSetup_child\"]/div[1]"));
		waitElementToBeClickable(myEmailSetting, "myEmailSetting field");
		myEmailSetting.click();
		log.info("myEmailSetting is clicked");
		Thread.sleep(3000);
		WebElement emailName = driver.findElement(By.xpath("//input[@id='sender_name']"));
		waitElementToBeClickable(emailName, "EmailName field");
		emailName.clear();
		emailName.sendKeys("Isha");
		log.info("Enter email name in emailName field");

		Thread.sleep(3000);
		WebElement emailAddress = driver.findElement(By.xpath("//input[@id='sender_email']"));
		waitElementToBeClickable(emailAddress, "EmailAddress field");
		emailAddress.clear();
		emailAddress.sendKeys("ambuj.meet@gmail.com");
		log.info("Enter email Address in emailAddress field");
		Thread.sleep(3000);

		WebElement automaticBccRadioButton = driver.findElement(By.xpath("//input[@id='auto_bcc1']"));
		waitElementToBeClickable(automaticBccRadioButton, "Automatic BccRadioButton field");
		// automaticBccRadioButton.clear();
		automaticBccRadioButton.click();
		log.info("automaticBcc RadioButton is clicked");

		Thread.sleep(3000);
		WebElement saveButtonInEmailLink = driver.findElement(By.xpath("//input[@value=' Save ']"));
		waitElementToBeClickable(saveButtonInEmailLink, "saveButton");
		log.info("before save click");
		saveButtonInEmailLink.click();

		// Click on Calendar & Remainders
		log.info("Test Case 7 :: Calendar & Reminder -- Starts");

		// *[@id="CalendarAndReminders"]
		// *[@id="CalendarAndReminders_child"]/div[2]
		// input[@onclick='ActivityReminder.testPopup();']

		Thread.sleep(3000);
		// *[@id="CalendarAndReminders"]/a/span[3]
		// *[@id="CalendarAndReminders"]
		WebElement calendarAndRemainders = driver.findElement(By.xpath("//*[@id=\"CalendarAndReminders\"]"));
		waitElementToBeClickable(calendarAndRemainders, "CalendarAndRemainders field");
		calendarAndRemainders.click();
		log.info("CalendarAndRemainders link is clicked");

		Thread.sleep(3000);
		// *[@id="CalendarAndReminders_child"]/div[2]
		WebElement activityRemainders = driver.findElement(By.xpath("//*[@id=\"CalendarAndReminders_child\"]/div[2]"));
		waitElementToBeClickable(activityRemainders, "activityRemainders field");
		activityRemainders.click();
		log.info("activityRemainders field is clicked");

		Thread.sleep(3000);
		// WebElement
		// openATestRemainder=driver.findElement(By.xpath("//*[@id=\"CalendarAndReminders\"]"));
		WebElement openATestRemainder = driver.findElement(By.id("testbtn"));
		waitElementToBeClickable(openATestRemainder, "openATestRemainder popup field");
		openATestRemainder.click();
		log.info("openATestRemainder popup field is clicked");
		Thread.sleep(3000);

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_CONTROL);

		// *[@id="bottomButtonRow"]/input[1]
		WebElement saveButtonopenATestRemainder = driver.findElement(By.xpath("//*[@id=\"bottomButtonRow\"]/input[1]"));
		waitElementToBeClickable(saveButtonopenATestRemainder, "saveButton");
		saveButtonopenATestRemainder.click();

	}
	
	// Test Case 8
	// Developer Console
	@Test
	public void developerConsole() throws InterruptedException, AWTException {
		Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);

		log.info("Test 8 starts");
		getURL(appDataProperties.getProperty("app.url"));

		WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));
		enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
				AutomationConstants.USER_NAME_FIELD_TXT);

		WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
		enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

		WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
		clickAction(clickLoginField, "login");

		WebElement userMenu = driver.findElement(By.xpath("//span[@id='userNavLabel']"));
		if (userMenu.isDisplayed()) {
			userMenu.click();
			log.info("userMenu is displayed");
		} else {
			log.info("userMenu is not displayed");
		}

		WebElement baseMenu = driver.findElement(By.id("userNav"));
		WebElement subMenuOptions = baseMenu.findElement(By.id("userNav-menuItems"));
		log.info(subMenuOptions.getText());

		// a[@title='Developer Console (New Window)']
		// *[@id="userNav-menuItems"]/a[3]

		// DeveloperConsole
		Thread.sleep(3000);
		//*[@id="userNav-menuItems"]/a[3]
		WebElement developerConsole = driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[3]"));
		waitElementToBeClickable(developerConsole, "developerConsole link");
		log.info("developerConsole link is clicked");
		developerConsole.click();
		
		Thread.sleep(3000);

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		log.info("developerConsole popup close");

	}
	//Test Case 9
	@Test
	public void logoutOptionFromUserMenu() {
		Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);

		getURL(appDataProperties.getProperty("app.url"));

		WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));
		enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
		AutomationConstants.USER_NAME_FIELD_TXT);

		WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
		enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

		WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
		clickAction(clickLoginField, "login");
		
		log.info("UserMenu Page");
		WebElement userMenu = driver.findElement(By.xpath("//span[@id='userNavLabel']"));
		if (userMenu.isDisplayed()) {
			userMenu.click();
			log.info("userMenu is displayed");
		} else {
			log.info("userMenu is not displayed");
		}

		WebElement baseMenu = driver.findElement(By.id("userNav"));
		WebElement subMenuOptions = baseMenu.findElement(By.id("userNav-menuItems"));
		log.info(subMenuOptions.getText());
		
		log.info("Logout option from user menu ");
		//*[@id="userNav-menuItems"]/a[5]
		WebElement logoutField = driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[5]"));
		clickAction(logoutField, "Logout");	
		
	}
	
	//Test Case 10[Create an Account]
	@Test
	public void createAnAccount() throws InterruptedException, AWTException {
		Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);

		getURL(appDataProperties.getProperty("app.url"));

		WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));
		enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
		AutomationConstants.USER_NAME_FIELD_TXT);

		WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
		enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

		WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
		clickAction(clickLoginField, "login");
		
		
		
		Thread.sleep(5000);
		WebElement accountLink = driver.findElement(By.xpath("//*[@id=\"Account_Tab\"]"));
		waitElementToBeClickable(accountLink, "accountLink field");
		accountLink.click();
		log.info("accountLink field is clicked");
		
		Thread.sleep(4000); //PopUp window
		WebElement popUpWindow=driver.findElement(By.id("tryLexDialogX"));
		popUpWindow.click();
		log.info("Popup window close");
		
		
		//*[@id="hotlist"]/table/tbody/tr/td[2]/input
		//*[@id="hotlist"]/table/tbody/tr/td[2]
		
		/*Thread.sleep(5000);		
		Actions act =  new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input"))).click().perform();
		log.info("new button is clicked");*/
		
		//new button
		Thread.sleep(3000);
		try {
			driver.findElement(By.xpath("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input")).click();
		  } catch (Exception e) {
		     JavascriptExecutor executor = (JavascriptExecutor) driver;
		     executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input")));
		     log.info("new button is clicked");
		  }
		Thread.sleep(3000);
		WebElement accountName = driver.findElement(By.xpath("//*[@id=\"acc2\"]"));
		waitElementToBeClickable(accountName, "accountName");
		accountName.sendKeys("Ambuj Account");
		log.info("Account name is Entered");
		
		Select typeField = new Select(driver.findElement(By.xpath("//select[@id='acc6']")));
		typeField.selectByVisibleText("Technology Partner");
		log.info("Technology Partner is selected");
		
		Select customerPriority = new Select(driver.findElement(By.xpath("//select[@id='00NHs00000Djqtn']")));
		customerPriority.selectByVisibleText("High");
		log.info("Customer Priority (High) is selected from dropdown");
		
		
		Thread.sleep(3000);
		try {
			driver.findElement(By.xpath("//input[@tabindex='34']")).click();
		  } catch (Exception e) {
		     JavascriptExecutor executor = (JavascriptExecutor) driver;
		     executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@tabindex='34']")));
		     log.info("saveButton field is clicked");
		  }
		
		//*[@id="bottomButtonRow"]/input[1]
		/*WebElement saveButton = driver.findElement(By.xpath("//*[@id=\"bottomButtonRow\"]/input[1]"));
		waitElementToBeClickable(saveButton, "saveButton field");
		saveButton.click();
		log.info("saveButton field is clicked");*/
		
	}

	
	
	//Test Case 11 [Create new view]
	@Test
	public void createNewView() throws InterruptedException, AWTException {
		Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);

		getURL(appDataProperties.getProperty("app.url"));

		WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));
		enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
		AutomationConstants.USER_NAME_FIELD_TXT);

		WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
		enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

		WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
		clickAction(clickLoginField, "login");
		
		Thread.sleep(3000);
		WebElement accountLink = driver.findElement(By.xpath("//*[@id=\"Account_Tab\"]"));
		waitElementToBeClickable(accountLink, "accountLink field");
		accountLink.click();
		log.info("accountLink field is clicked");
		
		getScreenShotOfThePage();
		Thread.sleep(4000); //PopUp window
		WebElement popUpWindow=driver.findElement(By.id("tryLexDialogX"));
		popUpWindow.click();
		log.info("Popup window close");

		
		//*[@id="filter_element"]/div/span/span[2]/a[2]
		//a[text()='Create New View']
		
		//Create Account view
		Thread.sleep(3000);
		try {
			driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]")).click();
		  } catch (Exception e) {
		     JavascriptExecutor executor = (JavascriptExecutor) driver;
		     executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]")));
		     log.info("createNewViewLink field is clicked");
		  }
		
		//input[@id='fname']
		WebElement viewName = driver.findElement(By.id("fname"));
		waitElementToBeClickable(viewName, "viewName field");	
		viewName.sendKeys("Pishu");
		log.info("Entered Name in viewName field");
		//Thread.sleep(3000);
		
		//*[@id="devname"]
		//input[@id='devname']
		WebElement viewUniqueName = driver.findElement(By.id("devname"));
		waitElementToBeClickable(viewUniqueName, "viewUniqueName field");
		viewUniqueName.clear();
		String previousText = viewUniqueName.getAttribute("value");
		log.info("Before adding text, the value is= "+previousText);
		viewUniqueName.sendKeys("Baby_28");
		log.info("Entered Unique Name in viewUniqueName field");
		Thread.sleep(3000);
		
	
		//input[@name='save']
		WebElement saveButtoncreateAcc = driver.findElement(By.xpath("//input[@name='save']"));
		waitElementToBeClickable(saveButtoncreateAcc, "saveButtoncreateAcc field");
		saveButtoncreateAcc.click();
		log.info("saveButton field is clicked");
		Thread.sleep(3000);
		
		getScreenShotOfThePage();
		
	}
	
	
	//Test case 12 [Edit view]
	@Test
	public void editView() throws InterruptedException, AWTException {
		Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);

		getURL(appDataProperties.getProperty("app.url"));

		WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));
		enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
		AutomationConstants.USER_NAME_FIELD_TXT);

		WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
		enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

		WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
		clickAction(clickLoginField, "login");
		
		Thread.sleep(3000);
		WebElement accountLink = driver.findElement(By.xpath("//*[@id=\"Account_Tab\"]"));
		waitElementToBeClickable(accountLink, "accountLink field");
		accountLink.click();
		log.info("accountLink field is clicked");
		
		
		Thread.sleep(4000); //PopUp window
		WebElement popUpWindow=driver.findElement(By.id("tryLexDialogX"));
		popUpWindow.click();
		
		log.info("Popup window close");
		
		
		//*[@id="fcf"]
		//select[@id='fcf']
		//view
		Thread.sleep(4000);
		Select viewField= new Select(driver.findElement(By.name("fcf")));
		viewField.selectByVisibleText("Ishu");
		log.info("In ViewField dropdown Ishu is selected");

		
		//*[@id="filter_element"]/div/span/span[2]/a[1]
		//a[text()='Edit']
		WebElement editField = driver.findElement(By.xpath("//a[text()='Edit']"));
		waitElementToBeClickable(editField, "edit field");
		editField.click();
		log.info("In editField is Clicked");
		
		WebElement viewName = driver.findElement(By.id("fname"));
		waitElementToBeClickable(viewName, "viewName field");
		viewName.clear();
		viewName.sendKeys("Anamika");
		log.info("Entered Name in viewName field");
		
		//select[@id='fcol1']
		Select filterFieldAccountNme=new Select(driver.findElement(By.xpath("//select[@id='fcol1']"))) ;
		filterFieldAccountNme.selectByVisibleText("Account Name");
		log.info("In filterField dropdown AccountNme is selected");
		
		Thread.sleep(4000);
		Select filterOperatorField=new Select(driver.findElement(By.id("fop1"))) ;
		filterOperatorField.selectByVisibleText("contains");
		log.info("In filterOperator dropdown Contains is selected");
		
		
		WebElement filtervalueField = driver.findElement(By.id("fval1"));
		waitElementToBeClickable(filtervalueField, "Value field");
		filtervalueField.clear();
		filtervalueField.sendKeys("1234");
		log.info("Entered Value 1234 in  Valuefield");
		Thread.sleep(4000);
		
		//input[@name='save']
		WebElement saveButton = driver.findElement(By.xpath("//*[@id=\"editPage\"]/div[3]/table/tbody/tr/td[2]/input[1]"));
		waitElementToBeClickable(saveButton, "saveButton field");
		saveButton.click();
		log.info("saveButton field is clicked");
		Thread.sleep(3000);
		
	}
	
	
	//Test case 13 [MergeAccounts]
	@Test
	public void mergeAccounts() throws InterruptedException, AWTException {
		Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);

		getURL(appDataProperties.getProperty("app.url"));

		WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));
		enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
		AutomationConstants.USER_NAME_FIELD_TXT);

		WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
		enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

		WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
		clickAction(clickLoginField, "login");
		
		Thread.sleep(3000);
		WebElement accountLink = driver.findElement(By.xpath("//*[@id=\"Account_Tab\"]"));
		waitElementToBeClickable(accountLink, "accountLink field");
		accountLink.click();
		log.info("accountLink field is clicked");
		
		Thread.sleep(4000); //PopUp window
		WebElement popUpWindow=driver.findElement(By.id("tryLexDialogX"));
		popUpWindow.click();
		log.info("Popup window close");
		
		
		Thread.sleep(3000);
		WebElement mergeAccount = driver.findElement(By.xpath("//a[text()='Merge Accounts']"));
		waitElementToBeClickable(mergeAccount, "Merge Account field");
		mergeAccount.click();
		log.info("mergeAccount field is clicked");
		
		//input[@id='srch']
		Thread.sleep(4000);
		WebElement findAccountBlankField = driver.findElement(By.xpath("//input[@id='srch']"));
		waitElementToBeClickable(findAccountBlankField, "Value field");
		findAccountBlankField.clear();
		findAccountBlankField.sendKeys("Ambuj Account");
		log.info("Entered AccountName--- Ambuj Account");
		Thread.sleep(4000);
		
		Thread.sleep(3000);
		WebElement findAccounts = driver.findElement(By.xpath("//input[@value='Find Accounts']"));
		waitElementToBeClickable(findAccounts, "Find Account field");
		findAccounts.click();
		log.info("findAccounts field is clicked and 2 Account is created");
		
		//input[@name='goNext']
		Thread.sleep(3000);
		WebElement nextButton = driver.findElement(By.xpath("//input[@name='goNext']"));
		waitElementToBeClickable(nextButton, "nextButton field");
		nextButton.click();
		log.info("nextButton field is clicked");
		Thread.sleep(3000);
		
		
	}
	
	
	//Test case 14 [Create account report]
	@Test
	public void createAccountReportField() throws InterruptedException, AWTException {
		
		Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);

		getURL(appDataProperties.getProperty("app.url"));

		WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));
		enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
		AutomationConstants.USER_NAME_FIELD_TXT);

		WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
		enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

		WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
		clickAction(clickLoginField, "login");
		
		Thread.sleep(3000);
		WebElement accountLink = driver.findElement(By.xpath("//*[@id=\"Account_Tab\"]"));
		waitElementToBeClickable(accountLink, "accountLink field");
		accountLink.click();
		log.info("accountLink field is clicked");
		
		
		Thread.sleep(4000);
		WebElement popUpWindow=driver.findElement(By.id("tryLexDialogX"));
		popUpWindow.click();
		log.info("Popup window close");
		
		//AccountWithLastActivity30Days
		Thread.sleep(3000);
		try {
			driver.findElement(By.xpath("//a[text()='Accounts with last activity > 30 days']")).click();
		  } catch (Exception e) {
		     JavascriptExecutor executor = (JavascriptExecutor) driver;
		     executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Accounts with last activity > 30 days']")));
		     log.info("accountWithLastActivity30Days field is clicked");
		  }
		
	
		//*[@id="ext-gen20"]
		//Date field drop down
		Thread.sleep(4000);
		
		WebElement dateFieldDropDown=driver.findElement(By.xpath("//img[@id='ext-gen148']"));
		dateFieldDropDown.click();
		log.info("dateFieldDropDown is clicked");
		WebElement createdDate=driver.findElement(By.xpath("//div[text()='Created Date']"));
		createdDate.click();
		log.info("createdDate is clicked");
	
		WebElement fromField=driver.findElement(By.xpath("//img[@id='ext-gen152']"));//FromField
		fromField.click();
		log.info("fromField is clicked");
		
		Thread.sleep(4000);
		WebElement todaysDatefromField=driver.findElement(By.xpath("//td[@title='Today']"));
		todaysDatefromField.click();
		log.info("Today's Date is updated in Fromfield");
		
		/*LocalDate today= LocalDate.now();
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate= today.format(formatter);
		log.info("Today's Date is updated in Fromfield");
		Thread.sleep(4000);*/
		
		//td[@class='x-date-active x-date-today x-date-selected']
		WebElement toField=driver.findElement(By.xpath("//img[@id='ext-gen154']"));//ToField
		fromField.click();
		log.info("toField is clicked");
		
		Thread.sleep(4000);
		WebElement todaysDatetoField=driver.findElement(By.xpath("//td[@class='x-date-active x-date-today x-date-selected']"));
		todaysDatetoField.click();
		log.info("Today's Date is updated in tofield");
		
		
		
		/*LocalDate today1= LocalDate.now();
		DateTimeFormatter formatter1=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate1= today1.format(formatter1);
		log.info("Today's Date is updated in Tofield");
        Thread.sleep(4000); */
         
		WebElement saveButtonFromUnsavedReport = driver.findElement(By.xpath("//button[@id='ext-gen49']"));		
		waitElementToBeClickable(saveButtonFromUnsavedReport, "saveButton field");
		saveButtonFromUnsavedReport.click();
		log.info("saveButton field is clicked on UnsavedReport");
		Thread.sleep(3000);
		
		//input[@id='saveReportDlg_reportNameField'] saveReportDlg_reportNameField
		Thread.sleep(4000);
        WebElement reportName= driver.findElement(By.xpath("//input[@id='saveReportDlg_reportNameField']"));
        fluentWaitForVisibility(reportName,"ReportName field");
        reportName.sendKeys("Project Report");
        log.info("Enter Project Report in reportName field");
        
        Thread.sleep(4000);
        WebElement reportUniqueName= driver.findElement(By.id("saveReportDlg_DeveloperName"));
        fluentWaitForVisibility(reportUniqueName,"ReportUniqueName field");
        reportUniqueName.clear();
        reportUniqueName.sendKeys("SalesForce Report");
        log.info("Enter SalesForce Report in reportUniqueName field");
        
        WebElement saveAndRunRoport= driver.findElement(By.xpath("//button[text()='Save and Run Report']"));
        fluentWaitForVisibility(saveAndRunRoport,"saveAndRunRoport field");
        saveAndRunRoport.click();
        log.info("saveAndRunRoport is clicked");
			
	}
	
	
	//Test case 15 [opportunities drop down]  ask Question to Geeta
	@Test
	public void opportunitiesDropDown() {

		
		Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);

		getURL(appDataProperties.getProperty("app.url"));

		WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));
		enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
		AutomationConstants.USER_NAME_FIELD_TXT);

		WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
		enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

		WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
		clickAction(clickLoginField, "login");
		
		
		
		WebElement opportunitiesLink = driver.findElement(By.id("Opportunity_Tab"));
		if(opportunitiesLink.isDisplayed()) {
			waitElementToBeClickable(opportunitiesLink, "Opportunities field");
			opportunitiesLink.click();
			log.info("opportunities link is clicked");
		}else {
			log.info("opportunities link is not clicked");
		}
		//*[@id="fcf"]
		List<WebElement> viewList= driver.findElements(By.xpath("//*[@id=\"fcf\"]"));
		log.info(viewList.size()+" items in the list");
		String itemExpected[]= {"All Opportunities", "Closing Next Month", "Closing This Month", "My Opportunities","New Last Week", 
				                 "New This Week", "Opportunity Pipeline","Private","Recently Viewed Opportunities","Won"};
		for(int i=0; i<viewList.size(); i++) {
			if(viewList.get(i).getText().equals(itemExpected[i])) {
			log.info(viewList.get(i).getText()+" is present");	
			}else {
				log.info(viewList.get(i).getText()+" is not present");
			}			
		}
		
		
		
	}

	
	
	//Test case 16 [Create a new Opty] this test case is not working...look into it again
    @Test
	public void createNewOpty() throws InterruptedException, AWTException {
			Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);

			getURL(appDataProperties.getProperty("app.url"));

			WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));
			enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
			AutomationConstants.USER_NAME_FIELD_TXT);

			WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
			enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

			WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
			clickAction(clickLoginField, "login");
			
			
			
			WebElement opportunitiesLink = driver.findElement(By.id("Opportunity_Tab"));
			if(opportunitiesLink.isDisplayed()) {
				waitElementToBeClickable(opportunitiesLink, "Opportunities field");
				opportunitiesLink.click();
				log.info("opportunities link is clicked");
			}else {
				log.info("opportunities link is not clicked");
			}
			
			
			//input[@value='No Thanks']
			//a[@id='tryLexDialogX']
			Thread.sleep(4000);
			WebElement popupWindow=driver.findElement(By.xpath("//a[@id='tryLexDialogX']"));
			popupWindow.click();
			
			Thread.sleep(4000);
			WebElement newField = driver.findElement(By.xpath("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input"));
			waitElementToBeClickable(newField, "New field");
			newField.click();

			
			//input[@id='opp3']   //*[@id="opp3"]
			Thread.sleep(4000);
			WebElement opportunityName = driver.findElement(By.xpath("//*[@id=\"opp3\"]"));
			fluentWaitForVisibility(opportunityName, "Opportunity Name field ");
			opportunityName.sendKeys("Testing");
			log.info("Entered Testing in Opportunity Name field ");
			
			Thread.sleep(4000);
			WebElement accountName = driver.findElement(By.xpath("//input[@id='opp4']"));
			fluentWaitForVisibility(accountName, "account Name field ");
			accountName.sendKeys("Ambuj Account");
			log.info("Entered Ambuj Account in AccountName field ");
			
			//select[@id='opp6']
			Thread.sleep(4000);
			WebElement leadSource = driver.findElement(By.xpath("//select[@id='opp6']"));
			Select select=new Select(leadSource);
			fluentWaitForVisibility(leadSource, "lead Source field ");
			select.selectByVisibleText("Web");
			log.info("Web element is selected in leadSource field ");
			
			Thread.sleep(4000);
			LocalDate today=LocalDate.now();
			DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String formatterDate= today.format(formatter);
			
			WebElement closeDate=driver.findElement(By.id("opp9"));
			closeDate.sendKeys("formatterDate");
			log.info("Date is selected in closeDate field");
			
			//select[@id='opp11']
			WebElement stageName=driver.findElement(By.xpath("//select[@id='opp11']"));
			Select select1=new Select(stageName);
			select1.selectByVisibleText("Needs Analysis");
			
			
			Thread.sleep(4000);
			WebElement saveButton=driver.findElement(By.xpath("//*[@id=\"bottomButtonRow\"]/input[1]"));
			saveButton.click();
		}
	    
	    
	    
	    
	    //Test case 17(Test Opportunity Pipeline Report)
	    @Test
	   public void testOpportunityPipelineReport() throws InterruptedException, AWTException {
	    	Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);

			getURL(appDataProperties.getProperty("app.url"));

			WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));
			enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
			AutomationConstants.USER_NAME_FIELD_TXT);

			WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
			enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

			WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
			clickAction(clickLoginField, "login");
			
			
			
			WebElement opportunitiesLink = driver.findElement(By.id("Opportunity_Tab"));
			if(opportunitiesLink.isDisplayed()) {
				waitElementToBeClickable(opportunitiesLink, "Opportunities field");
				opportunitiesLink.click();
				log.info("opportunities link is clicked");
			}else {
				log.info("opportunities link is not clicked");
			}
			
			//tryLexDialogX
			//driver.switchTo().activeElement();
			Thread.sleep(4000);
			WebElement popUpWindow=driver.findElement(By.id("tryLexDialogX"));
			popUpWindow.click();
			
			Thread.sleep(4000);
			WebElement opportunityPipelinelink=driver.findElement(By.xpath("//a[text()='Opportunity Pipeline']"));
			opportunityPipelinelink.click();
			log.info("opportunity Pipe line link field open");
			
		}
	    
	    
	
       //Test case-- 18
	    @Test
	    public void TeststuckOpportunitiesReport() throws InterruptedException {
	    	Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);

			getURL(appDataProperties.getProperty("app.url"));

			WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));
			enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
			AutomationConstants.USER_NAME_FIELD_TXT);

			WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
			enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

			WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
			clickAction(clickLoginField, "login");
			
			
			
			WebElement opportunitiesLink = driver.findElement(By.id("Opportunity_Tab"));
			if(opportunitiesLink.isDisplayed()) {
				waitElementToBeClickable(opportunitiesLink, "Opportunities field");
				opportunitiesLink.click();
				log.info("opportunities link is clicked");
			}else {
				log.info("opportunities link is not clicked");
			}
			
			Thread.sleep(4000);
			WebElement popUpWindow=driver.findElement(By.id("tryLexDialogX"));
			popUpWindow.click();
			
			
			//a[text()='Stuck Opportunities']
			
			Thread.sleep(4000);
			WebElement stuckOpportunity=driver.findElement(By.xpath("//a[text()='Opportunity Pipeline']"));
			stuckOpportunity.click();
			log.info("opportunity Pipe line link field open");
		}
	    
	    
	    
	    //Test Case 19 [Test quarterly summary report]
	    
	    @Test
	    public void Testquarterlysummaryreport() throws InterruptedException {
	    	Properties appDataProperties = propUtil.loadPropFile(AutomationConstants.APP_DATA_PROPERTIES_FILE_PATH);

			getURL(appDataProperties.getProperty("app.url"));

			WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));
			enterTextValue(userNameElement, appDataProperties.getProperty("valid.userid"),
			AutomationConstants.USER_NAME_FIELD_TXT);

			WebElement passwordNameElement = driver.findElement(By.xpath("//input[@id='password']"));
			enterTextValue(passwordNameElement, appDataProperties.getProperty("valid.password"), "password");

			WebElement clickLoginField = driver.findElement(By.xpath("//input[@id='Login']"));
			clickAction(clickLoginField, "login");
			
			
			
			WebElement opportunitiesLink = driver.findElement(By.id("Opportunity_Tab"));
			if(opportunitiesLink.isDisplayed()) {
				waitElementToBeClickable(opportunitiesLink, "Opportunities field");
				opportunitiesLink.click();
				log.info("opportunities link is clicked");
			}else {
				log.info("opportunities link is not clicked");
			}
			
			Thread.sleep(2000);
			WebElement popUpWindow=driver.findElement(By.id("tryLexDialogX"));
			popUpWindow.click();
			
						
			Thread.sleep(4000);
			WebElement  intervalLink  = driver.findElement(By.xpath("//select[@id='quarter_q']"));
			Select selectinterval= new Select(intervalLink);
			selectinterval.selectByVisibleText("Next FQ");
			log.info("Current and NextFQ is displayed in the intervalLink");
			
			
			WebElement  includeLink  = driver.findElement(By.xpath("//select[@id='open']"));
			Select includeselect= new Select(includeLink);
			includeselect.selectByVisibleText("Closed/Won Opportunities");
			log.info("Current and NextFQ is displayed in the intervalLink");
			
			//input[@value='Run Report']
			Thread.sleep(4000);
			WebElement runReport=driver.findElement(By.xpath("//input[@value='Run Report']"));
			runReport.click();
			
		}
	    
	    
	    
}
