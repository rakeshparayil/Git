
package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
//MONOCHROME WILL give results of cucumber in readable format
//helper atrributed in bracket
@CucumberOptions(features="src/test/java/cucumber", glue= "Rakesh.stepDefinition",
monochrome=true,tags= "@Errorvalidation", plugin= {"html:target/cucumber.html"})
//target folder will be created where html report get generated
//cucumber has not inbuilt capabilities to run testng files
//but a class 'AbstractTestNgCucucmberTests' created 
//by testNG HAS WRAPPER THAT CAN WRAP TESTNG
public class TestngTestrunner extends AbstractTestNGCucumberTests {
	//cucumber feature file running in testng cucumber runner

}
