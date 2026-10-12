package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    public static String takeScreenshot(WebDriver driver, String testName){

        //generate timestamp so that each screenshot have different name
        String timestamp=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        //Define where screenshots will be saved
        String screenshotPath = "screenshots/"+testName+"_"+timestamp+".png";

        try{
            TakesScreenshot camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot,new File(screenshotPath));
            System.out.println("screenshot saved: "+ screenshotPath);
        }
        catch(IOException e){
            System.out.println("Failed to save screenshot: "+ e.getMessage());
        }

        return screenshotPath;
    }
}
