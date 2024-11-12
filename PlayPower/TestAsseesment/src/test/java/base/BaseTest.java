package base;

import java.io.FileReader;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.Reports;

public class BaseTest extends Reports {
	public static WebDriver driver;
	public static Properties prop = new Properties();
	public static FileReader fr;
	
	
	@BeforeMethod
	public void setUp() throws Exception {
		if(driver==null) {
			FileReader fr = new FileReader("C:\\Users\\Admin\\eclipse-workspace\\PlayPower\\TestAsseesment\\src\\test\\resources\\configFiles\\config.properties");
			prop.load(fr);;
		}
		if(prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver();
			 driver.get(prop.getProperty("url"));
			 driver.manage().window().maximize();
		}
		else if(prop.getProperty("browser").equalsIgnoreCase("fireforx")) {
			WebDriverManager.firefoxdriver().setup();
			 driver = new FirefoxDriver();
			 driver.get(prop.getProperty("url"));
			 driver.manage().window().maximize();
		}
		else if(prop.getProperty("browser").equalsIgnoreCase("edgeBrowser")) {
			WebDriverManager.edgedriver().setup();
			 driver = new EdgeDriver();
			 driver.get(prop.getProperty("url"));
			 driver.manage().window().maximize();
		}
		
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.close();
		System.out.println("Browser closed Successfully");
		
		
	}
	
	
	

}
