package utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if(extent == null){
            String timestamp= new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            ExtentSparkReporter reporter= new ExtentSparkReporter("reports/TestReport"+ timestamp+".html");
            reporter.config().setReportName("Automation Test Report");
            reporter.config().setDocumentTitle("Test Results");
            reporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester","Shweta");
            extent.setSystemInfo("Environment","QA");
            extent.setSystemInfo("Browser","Chrome");
        }
        return extent;
    }
}
