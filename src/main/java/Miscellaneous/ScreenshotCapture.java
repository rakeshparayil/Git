package Miscellaneous;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class ScreenshotCapture {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//There is a public interface TakesScreenshot
		//WebDriver interface does not directly implement TakesScreenshot interface
		//Need to cast the driver to implement the TakesScreenshot interface
		//Need library apache common.io to be downloaded from maven repository
		
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//To take screenshot of full page
		driver.manage().window().maximize();
		
		//open url
		driver.get("https://selenium.vansah.io/");
		
		TakesScreenshot screenshot = ((TakesScreenshot)driver);
		String base64Screenshot = screenshot.getScreenshotAs(OutputType.BASE64);
		System.out.println("base64Screenshot:" + " " + base64Screenshot);

	}

}
