package Rakesh.Testcomponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

     int count=0;
     int maxTry=1;
	//class has to implement the unimplimented methods exposed by interface IRetryAnalyzer
	//Whenever test fails it also has to come to this block reporting failure
	//to inform do i need to rerun again
	//how many times rerun
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		
		//as long as method return true it will rerun again and again
		if(count<maxTry)//0<1 means rerun
		{
			count ++;
			return true;//stop retrying
		}
		return false;
	}
	

}
