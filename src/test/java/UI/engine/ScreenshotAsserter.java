package UI.engine;

import com.groupdocs.comparison.Comparer;
import configurations.ScreenshotConfigs;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenshotAsserter {
    ScreenshotConfigs scrConf = new ScreenshotConfigs();

    public void getScreenshot(WebDriver driver, String screenName) {
        String fullPathToScreenArchive = scrConf.getScreenPathForScreenArchive() + screenName + ".png";
        String fullPathToScreenCurrent = scrConf.getScreenPathForCurrentTest() + screenName + ".png";
        Path path = Paths.get(fullPathToScreenArchive);
        if (Files.exists(path)){
            getScreenAndCopyToFolder(driver, scrConf.getScreenPathForCurrentTest(), screenName);
            Comparer comparer = new Comparer(fullPathToScreenArchive);
            comparer.add(fullPathToScreenCurrent);
            comparer.compare(scrConf.getScreenPathForResult() + screenName + ".png");
        } else {
            System.out.println("\u001B[34m" + "Внимание, скриншот не был создан заранее и создался во время теста, в результате чего не была выполнена проверка. Проверьте кейс вручную или перезапустите");
            getScreenAndCopyToFolder(driver, scrConf.getScreenPathForScreenArchive(), screenName);
        }

    }

    void getScreenAndCopyToFolder(WebDriver driver, String pathForCopy, String screenName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(pathForCopy + screenName + ".png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
