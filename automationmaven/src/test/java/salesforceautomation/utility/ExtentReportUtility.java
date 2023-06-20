package salesforceautomation.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.netty.util.Constant;

public class ExtentReportUtility {
	public static ExtentReports report;
	public static ExtentSparkReporter spark;
	public static ExtentTest testLogger;
	private static ExtentReportUtility extentObject;
	
    private ExtentReportUtility() {
		
	}
	public static ExtentReportUtility getInstance() {
		if(extentObject==null) {
			extentObject=new ExtentReportUtility();
		}
		return extentObject;
		
	}
	
	public void startReport() {
		
		 report = new ExtentReports();
		 spark = new ExtentSparkReporter(AutomationConstants.SPARKS_HTML_REPORT_PATH);
		 report.attachReporter(spark);
		 
		 report.setSystemInfo("Application Name", "Salesforce");
		 report.setSystemInfo("Environment", "QA");
		 report.setSystemInfo("User Name", "Ambuj");
		 
		 spark.config().setDocumentTitle("Test Execution Report");
		 spark.config().setReportName("SalesForce regression tests");
		 spark.config().setTheme(Theme.DARK);	
	}
	
	
	
	public void startSingleTestReport(String methodName) {
		testLogger=report.createTest(methodName);	
		
	}
	
	public void endReport() {
		report.flush();
	}
	
	public void logTestinfo(String text) {
		testLogger.log(Status.INFO, text);
	}
	
	public void logTestPassed(String text) {
		testLogger.pass(MarkupHelper.createLabel(text, ExtentColor.GREEN));
	}
	
	public void logTestFailed(String text) {
		testLogger.fail(MarkupHelper.createLabel(text, ExtentColor.RED));
		
		
	}
	
	public void logTestFailedWithException(Throwable e) {
		testLogger.log(Status.FAIL, e);
		
	}

}
