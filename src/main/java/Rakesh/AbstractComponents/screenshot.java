package Rakesh.AbstractComponents;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;

public class screenshot {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/login");
		
		TakesScreenshot sh = (TakesScreenshot)driver;
		File source = sh.getScreenshotAs(OutputType.FILE);
		
		//destination path in local system.
		File dest = new File("C:\\Users\\USER\\Desktop\\New folder\\New folder 1\\demoqa.jpg");//destination of file
		FileHandler.copy(source, dest);
		driver.quit();
		
		

	}

}
