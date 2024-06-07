package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src\\test\\java\\cucumber\\SubmitOrder.feature", 
monochrome=true, plugin= {"html: target/cucumber.html"})
public class TestNgCucumberRunner extends AbstractTestNGCucumberTests{

}
