
// Generating excellent client html reports from test execution:
// Extent Report we need to integrate with the framework so it will take care of generating html reports after we run the test cases.
// Extent Reports are very famous in automation frameworks in Selenium, Appium and REST API Automation

package resources;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
 
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

// ExtentHtmlReporter generates the html reports, we need to give the path where reports needs to be generated
// TestNG has a method 'getResults()' and it will store all the results in Map collection and then we need to iterate through each result and if the result is Passed then change status to Passed and then it builds a new node using 'buildTestNodes' method & update that specific test as Passed and if it is failed, update as status.failed in the html report.
// context.getFailedTests(), Status.FAIL - TestNG returns all failed tests using getFailedTests() and then update status as status.failed in the html report.

public class ExtentReporterNG implements IReporter {
    private ExtentReports extent;
    ExtentHtmlReporter htmlReporter;
 
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
    	
    	// System.getProperty("user.dir") - This gets the current project path, so we can replace the local machine project path "C:\\Users\\SACHINJIBHAKATE\\eclipse-workspace\\AppiumFramework" with System.getProperty("user.dir") - This helps if this test needs to be run in different machines.
    	// The below will create report in 'htmlreport.html' file under 'Reports' folder within the project 'AppiumFramework'
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Reports\\htmlreport.html");
        extent = new ExtentReports();
        
        extent.attachReporter(htmlReporter);
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();
 
            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
 
                buildTestNodes(context.getPassedTests(), Status.PASS);
                buildTestNodes(context.getFailedTests(), Status.FAIL);
                buildTestNodes(context.getSkippedTests(), Status.SKIP);
            }
        }
 
        extent.flush();
        
    }
 
    private void buildTestNodes(IResultMap tests, Status status) {
        ExtentTest test;
 
        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test = extent.createTest(result.getMethod().getMethodName());
 
                /*test.getTest(). = getTime(result.getStartMillis());
                test.getTest().endedTime = getTime(result.getEndMillis());*/
 
                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);
 
                String message = "Test " + status.toString().toLowerCase() + "ed";
 
                if (result.getThrowable() != null)
                    message = result.getThrowable().getMessage();
 
                test.log(status, message);
 
          //      extent.endTest(test);
            }
        }
    }
 
    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();        
    }
}