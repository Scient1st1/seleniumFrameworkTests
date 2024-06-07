package Levan.SeleniumFramework.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Levan.SeleniumFramework.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LoginPage loginpage;

//	properties class
	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		String userDir = System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(
				userDir + "\\src\\test\\java\\Levan\\SeleniumFramework\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : 
			prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApp() throws IOException {
//		handle startup and login
		WebDriver driver = initializeDriver();
		loginpage = new LoginPage(driver);
		loginpage.goTo();
		return loginpage;

	}

	@AfterMethod(alwaysRun = true)
	public void shoutDown() {
		driver.close();
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

//		get json as string
		String JsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
//		convert to hashmap - Jackson databind in dependencies
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(JsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
//		data = {map,map}
		return data;

	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "\\reports" + testCaseName + ".png");
		FileUtils.copyFile(src, file);
		return System.getProperty("user.dir") + "\\reports" + testCaseName + ".png";
	}
}
