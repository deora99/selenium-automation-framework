package base;

import config.ConfigReader;
import utilities.ScreenshotUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.testng.ITestResult;
import java.time.Duration;
import java.lang.reflect.Method;
import utilities.ExtentReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @BeforeSuite
    public void setUpReport(){
        extent=ExtentReportManager.getInstance();
        System.out.println("Extent Report initialized");
    }

    @AfterSuite
    public void tearDownReport(){
        if(extent != null){
            extent.flush();
            System.out.println("Report saved to reports/TestReport.html");
        }
    }

    @BeforeMethod
    public void setup(Method method){
        String browser=ConfigReader.getBrowser();

        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            driver=new ChromeDriver(options);
        }

        wait =new WebDriverWait(driver,
                Duration.ofSeconds(ConfigReader.getWaitTimeout())
        );

        driver.manage().window().maximize();
        driver.get(ConfigReader.getBaseUrl());

        System.out.println("Browser opened: "+browser);
        System.out.println("Navigated to URL: "+ConfigReader.getBaseUrl());

        ExtentTest extentTest = extent.createTest(method.getName());
        test.set(extentTest);
    }

    @AfterMethod
    public void teardown(ITestResult result){
        //if test fails, take screenshot
        ExtentTest extentTest =test.get();

        if(result.getStatus() == ITestResult.FAILURE){
            extentTest.fail(result.getThrowable());   // log failure in report
           String screenShotPath= ScreenshotUtil.takeScreenshot(driver, result.getName());
           extentTest.addScreenCaptureFromPath("../"+ screenShotPath, result.getName());
            System.out.println("Test FAILED: " + result.getName());
        }
        else if(result.getStatus() == ITestResult.SUCCESS){
            extentTest.pass("Test passed successfully");    // log pass in report
            System.out.println("Test PASSED: " + result.getName());
        }

        else{
            extentTest.skip("Test skipped");
            System.out.println("Test SKIPPED: " + result.getName());
        }
        if(driver!=null){
            driver.quit();
            System.out.println("Browser closed");
        }
    }

    public static ExtentTest getTest(){
        return test.get();
    }
}
