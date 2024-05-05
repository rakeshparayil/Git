package Rakesh.Testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Rakesh.resources.extendReporterNg;

public class Listener extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports extent = extendReporterNg.getReportObject();
	//test,test(error handling and submit order test run parallel)
	//concurency issue, like which test it refers to can create problem
	//multiple tests trying to access one variable'test'.
	//Java there is a class called threadlocal can fix this
	ThreadLocal<ExtentTest> extendTest = new ThreadLocal<ExtentTest>(); //thread safe 
	
	//Before testrun happens these methods are implemented
    //add listners to xml file for beacuse test has no clue where listeners are
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
				//returning extent report(classname.method)
		
		test =extent.createTest(result.getMethod().getMethodName());
		//the above step create an entry for methods in test reports
		extendTest.set(test);//thread local-assign unique thread id
		//lets say errorvalidation came here to the method created
		//an object test, pushed object to threadlocal,also picks
		//unique thread id of test case
		//creating a map
	}
	//on success the below method is executed
	//ans instruction given in method is executed
	

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//result has some idea about the test going to get executed
		extendTest.get().log(Status.PASS, "Test is pass");
		
		
	}
	//on failure the below method is executed

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		//test.log(Status.FAIL, "Test failed");
		//test result will also get stored in result call it
		extendTest.get().fail(result.getThrowable());//get to extract thread id stored
		//above step wil automatically fails test when it fails
		//and add screenhsot in step below 
		//will give all error message in html report
		//screenshot only test fails, attach it to the report
		
		//getting driver information into the method, driver life is catched 
		//here and send to basetend screenshot method
		try {
			driver= (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
			//cant get driver from method because it is associated in
			//class level and no in method level
		
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			//not matter whichever exception it fails(there are a lot)
			//above is the parent of all exception
			e1.printStackTrace();
		}
		//to get driver information from result which has information of driver from
		//beforemethod of basetest class
		String filePath=null;//initially its null
		try {
			 filePath = getScreenshot(result.getMethod().getMethodName(),driver);
			 //above step it gets information about filepath
			 //once the filepath is taken it send info to
			 //take screenshot
		} catch (IOException e) { 
			// TODO Auto-generated catch block
			//IOexception
			e.printStackTrace();//if screenshot does not exist
			//it prints in output
		}
		
			// TODO Auto-generated catch block
			
		extendTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
		//taken screenshot and attach to the test/extent report
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		// TODO Auto-generated method stub
		extent.flush();
	}
	//ITestListeners is an ''interface''

}
