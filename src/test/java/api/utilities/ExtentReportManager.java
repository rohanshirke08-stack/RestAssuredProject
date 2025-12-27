package api.utilities;

import org.testng.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // Create Extent Report
    public void onStart(ITestContext context) {
        ExtentSparkReporter spark =
                new ExtentSparkReporter("test-output/ExtentReport.html");

        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("API Automation Report");
        spark.config().setReportName("User API Test Results");

        extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("Project", "API Automation");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester", "QA Team");
    }

    // Test Start
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest =
                extent.createTest(result.getMethod().getMethodName());

        test.set(extentTest);
    }

    // Test Pass
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }

    // Test Failure
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());
    }

    // Test Skipped
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test Skipped");
    }

    // Suite Finish
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}