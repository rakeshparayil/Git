package Rakesh.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extendReporterNg {
//static will allow to acces without creation of object
	//classname.method
	public static  ExtentReports getReportObject()
	{
		String path = System.getProperty("user.dir") + "//reports/index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		 ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester","Jadu");
		return extent;
		//to impliment this to every test introduce tesntng listeners in 
		//create a class testcomponent
	}
}
