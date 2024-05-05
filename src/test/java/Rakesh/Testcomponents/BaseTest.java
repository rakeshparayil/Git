package Rakesh.Testcomponents;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Rakesh.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseTest {
	public WebDriver driver;
	public LandingPage landingpage;
	public WebDriver initializeDriver() throws IOException
	{
		//properties class - can read global properties from the file created in resources
		Properties pro = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//Rakesh//resources//Globaldata.properties");
		pro.load(fis);//fis is the inputstream for the file 
		//loads all key value pairs-here(browser =chrome) keyevalue pair
		//FileInputStream is a class
		//Inputstream is used to read data from source(file here)
		String browsername = System.getProperty("browser")!=null ? System.getProperty("browser") : pro.getProperty("browser");
		//not equal to null, Java ternary operator
		//if condition is true second argument get executed
		//if condition is false third argument get executed
		//arguments are separated with q mark and syntax
		//In terminal let say browser is given as edge(not null)
		//String browsername= pro.getProperty("browser");
		if(browsername.contains("chrome"))
		{ 
			ChromeOptions options =new ChromeOptions();
		      WebDriverManager.chromedriver().setup();
		      options.addArguments("headless");
		      if(browsername.contains("headless"))
		      {
		       driver = new ChromeDriver(options);
		       driver.manage().window().setSize(new Dimension(1440,900));
		       //fullscreen run
		      }
		       
			
		}
		else if (browsername.equalsIgnoreCase("firefox"))
		{
			
		}
		
		else if(browsername.equalsIgnoreCase("chrome"))
		{
			driver = new EdgeDriver();
			
			
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	       driver.manage().window().maximize();
	       return driver;
	}
	       
	       
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
	
	String jsonContent =FileUtils.readFileToString(new File(filePath),
			StandardCharsets.UTF_8);
		ObjectMapper maper = new ObjectMapper();
	List<HashMap<String,String>> data = maper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
	});
	return data;
	
	}
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty(("user.dir")+"//reports//"+ testCaseName +".png"));
		FileUtils.copyFile(source, file);
		return System.getProperty(("user.dir")+"//reports//"+ testCaseName +".png");
	}
		
	   	
   @BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
	 landingpage = new LandingPage(driver);
	       landingpage.goTo();
	       return landingpage;
	}
     
   @AfterMethod(alwaysRun=true)
  public void closebrowser()
  {
	   driver.close();
   }
	
}
