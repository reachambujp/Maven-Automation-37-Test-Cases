package salesforceautomation.utility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestEventListernerUtility implements ITestListener{
	private static ExtentReportUtility extentReport;

	@Override
	public void onTestStart(ITestResult result) {
		extentReport.startSingleTestReport(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentReport.logTestPassed(result.getMethod().getMethodName()+" is passed");
	}

	
	@Override
	public void onTestFailure(ITestResult result) {
		extentReport.logTestFailed(result.getMethod().getMethodName()+" is failed");
		extentReport.logTestFailedWithException(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		extentReport=ExtentReportUtility.getInstance();
		extentReport.startReport();
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.endReport();
	}

	
	
	
	
	

}
