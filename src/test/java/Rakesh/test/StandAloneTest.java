package Rakesh.test;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       WebDriverManager.chromedriver().setup();
       WebDriver driver = new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       driver.manage().window().maximize();
       driver.get("https://rahulshettyacademy.com/client");
       driver.findElement(By.id("userEmail")).sendKeys("nsw@gmail.com");
       
     //  LandingPage landingpage = new LandingPage(driver); // when you creating argement in class
       //can catch it in constructor 
       
       driver.findElement(By.id("userPassword")).sendKeys("Rs@12345");
       driver.findElement(By.id("login")).click();
       //part-of class unically identifies all the products, we save them onto
       //the list anfter login
       //find out generic "webelements" so that we can add them to list and itearte to find the
       //desired one
       //common locator applicable to all these
       //class name is too long  and classes are separated by spaces take one generic
       // ".classname"
       //find all "elements" and grab them into list
       List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
       //iterate and find out which element ZARA COAT 3 is displayed
       //search inside the section of zara coat only not entire webelement like the driver.
       //so we write product.findelement
       //only one b here which is tagname css selector is just b
       //limiting scope of driver
       WebElement prod = products.stream().filter(s->s.findElement(By.cssSelector(".card-body"))
    		   .getText().equals("ZARA COAT 3")).findFirst().orElse(null);
       //store webelemen of zara
       //control inside the zarra coat 3
       //first  will return first element if multiple entries are there for zara coat
       //by default
       //now add to cart
       
       //css selector classname space last-of type as two buttons are present, first-of-type will
       //giveview, we move locally with prod web element
       prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
       //add to cart message disappers fastly, need to gran the element quickly
       //explicitly wait till message add to cart is displayed
       //locator #id for css selector
       WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
       //when it is displayed we can confirm product is added and proceed further
       //there is a shadow in page apperaring, need that to be gone as well as to proceed
       //ask developer to get it as its too fast to capture
       //explicitly wait till its invisibility or the shadow is gone
       //class name given by developer- 'ng-animating'
       //'.classname' is the css locator
       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
       //after all these process happened then only add to cart will appear for us to click
       //which is a button on top right leading to another page.
       driver.findElement(By.cssSelector("[routerlink*='cart']")).click();//partial text selector in css
       List<WebElement> cartproducts=    driver.findElements(By.xpath("//li//h3"));
       //just want to confirm if text matches
       //anymatch instead of filter(this gives webelement) in lambda expression
       Boolean match= cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase("ZARA COAT 3"));
       Assert.assertTrue(match);
       //traverse from parent to child .classname space tagname
       driver.findElement(By.cssSelector(".totalRow button")).click();
       Actions a = new Actions(driver);
       //first argument is webelement, second text to send
       a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"India").build().perform();
       //when you type india all the options pop up
       //wait until the block of option shown up
       wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
       //India comes in second place, choose to select second option
       driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
       //As the element is not clickable the below code was used
       WebElement submit = driver.findElement(By.cssSelector(".action__submit"));
       JavascriptExecutor js = (JavascriptExecutor) driver;
     //Perform Click on submit button using JavascriptExecutor
       js.executeScript("arguments[0].click();", submit);
      String confirmationmessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
      Assert.assertTrue(confirmationmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER"));

	}

}
