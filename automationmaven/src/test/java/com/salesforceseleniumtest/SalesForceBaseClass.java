
package com.salesforceseleniumtest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import salesforceautomation.utility.AutomationConstants;
import salesforceautomation.utility.ExtentReportUtility;

//launch browser
//enter url
//enter fields value
//click action
//clear field data
//close browser
//refresh browser

public class SalesForceBaseClass {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Logger log;
	protected ExtentReportUtility report = ExtentReportUtility.getInstance();

	@BeforeTest
	public void setParamsBeforeTest() {
		log = LogManager.getLogger(SalesForceBaseClass.class.getName());
	}

	@AfterMethod
	public void tearDownAfterMethod() {
//		driver.close();
		driver.quit();
		//report.logTestinfo("close the browser");
	}

	@BeforeMethod
	@Parameters("browserName")
	public void setBeforeTestMethod(@Optional("chrome") String browName) {
		launchBrowser(browName);
	}

	public void launchBrowser(String browserName) {
		browserName = browserName.toLowerCase();
		switch (browserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();

			log.info("Chrome broswer opened");
			// report.logTestinfo("Broswer launched");
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			log.info("Edge broswer opened");
			// report.logTestinfo("Broswer launched");

			break;
		}
	}

	public void getURL(String url) {
		driver.get(url);
		log.info("URL launched");
		// report.logTestinfo("URL launched");
	}

	public void enterTextValue(WebElement element, String data, String objName) {

		if (element.isDisplayed()) {
			element.clear();
			element.sendKeys(data);
			log.info(objName + " is entered in the textField");
           // report.logTestinfo(objName+" is entered in the textField");
		} else {
			log.info(objName + " is not entered in the textField");
		  //  report.logTestinfo(objName+" is not entered in the textField");
		}

	}

	public void clickAction(WebElement element, String objName) {
		if (element.isDisplayed()) {
			element.click();
			log.info(objName + " is clicked ");
			// report.logTestinfo(objName+" is clicked ");

		} else {
			log.info(objName + " is not clicked");
			// report.logTestinfo(objName+" is not clicked ");
		}
	}

	public void clearFieldData(WebElement element) {
		if (element.isDisplayed()) {
			element.clear();
			log.info("fieldData is clear");
			// report.logTestinfo("clear field data");
		} else {
			System.out.println("fieldData is displayed");
			// report.logTestinfo("fieldData is displayed");
		}
	}

	/*
	 * public static void refreshBrowser(WebDriver driver) { this.driver=driver;
	 * driver.navigate().refresh(); System.out.println("Refresh sucess");
	 * 
	 * }
	 */

	public void closeBrowser() {
		driver.close();
	}

	public void quitBrowser() {
		driver.quit();
	}

	public String getPageTile() {
		return driver.getTitle();

	}

	public void moveToElementAction(WebElement element, String objname) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		

	}

	public void contextClicktAction(WebElement element, String objname) {
		Actions action = new Actions(driver);
		action.clickAndHold(element).build().perform();
		

	}

	public void fluentWaitForVisibility(WebElement element, String objname) {
		Wait<WebDriver> fluentwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(2)).ignoring(ElementNotInteractableException.class);
		fluentwait.until(ExpectedConditions.visibilityOf(element));

	}

	public void waitElementToBeClickable(WebElement element, String objName) {
		log.info("waiting for element " + objName + " to be clickable");
		wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public void waitUntilAlertIsPresent() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.alertIsPresent());
		log.info("waiting for alert to be present");

	}

	public File getScreenShotOfThePage() {

		String date = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
		TakesScreenshot screenshort = (TakesScreenshot) driver;
		File imgFile = screenshort.getScreenshotAs(OutputType.FILE);
		File destFile = new File(AutomationConstants.SCREENSHOTS_DIRECTORY_PATH + date + ".png");
		try {
			FileUtils.copyFile(imgFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return destFile;
	}

}
