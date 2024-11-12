package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class Reports  {
    protected static ExtentReports extentReports;
    protected static ExtentTest test;
    

    @BeforeClass
    public void initializeReports() throws Exception {
    	
      Properties prop = new Properties();
      FileReader fr = new FileReader("C:\\Users\\Admin\\eclipse-workspace\\PlayPower\\TestAsseesment\\src\\test\\resources\\configFiles\\config.properties");
	  prop.load(fr);
		
        extentReports = new ExtentReports();
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("C:\\Users\\Admin\\eclipse-workspace\\PlayPower\\TestAsseesment\\test-output\\Reports\\testReports.html");
        htmlReporter.config().setDocumentTitle("Automation Test Report");
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setReportName("PlowerPlay Assesement");
        htmlReporter.config().setTimeStampFormat("dd-mm-yyy hh:mm:ss");
        htmlReporter.viewConfigurer().viewOrder().as(new ViewName[] {
        		ViewName.DASHBOARD,
        		ViewName.TEST,
        		ViewName.AUTHOR,
        		ViewName.DEVICE,
        		ViewName.CATEGORY,
        		
        }).apply();

        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("OS", "Windows");
        extentReports.setSystemInfo("Tester", "Sarath Kaipu");
        extentReports.setSystemInfo("App URL",prop.getProperty("url"));
        extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
        extentReports.setSystemInfo("Browser", System.getProperty("browser", "Chrome"));
    }

    @AfterClass
    public void flushReports() throws Exception {
        if (extentReports != null) {
            extentReports.flush();
            System.out.println("Extent Report flushed and saved");
            Desktop.getDesktop().browse(new File("C:\\Users\\Admin\\eclipse-workspace\\PlayPower\\TestAsseesment\\test-output\\Reports\\testReports.html").toURI());
        }
    }
}
