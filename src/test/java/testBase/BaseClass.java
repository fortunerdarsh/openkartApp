package testBase;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.nio.file.Files;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {
    protected WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass(groups={"sanity","master","regression"})
    @Parameters({"os","browser"})
    public void setup(@Optional("windows") String os, @Optional("chrome") String br) throws IOException {
        try {
            p = new Properties();
            p.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to load config.properties: " + e.getMessage());
        }

        logger = LogManager.getLogger(this.getClass());

        String env = p.getProperty("execution_env", "local");

        try {
            if (env.equalsIgnoreCase("remote")) {
                if (br.equalsIgnoreCase("chrome")) {
                    ChromeOptions options = new ChromeOptions();
                    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
                } else if (br.equalsIgnoreCase("edge")) {
                    EdgeOptions options = new EdgeOptions();
                    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
                } else {
                    Assert.fail("Unsupported browser for remote: " + br);
                }
            } else { // local
                if (br.equalsIgnoreCase("chrome")) {
                    driver = new ChromeDriver();
                } else if (br.equalsIgnoreCase("edge")) {
                    driver = new EdgeDriver();
                } else {
                    Assert.fail("Unsupported browser for local: " + br);
                }
            }

            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get(p.getProperty("web"));
            driver.manage().window().maximize();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Driver setup failed: " + e.getMessage());
        }
    }

    @AfterClass(groups={"sanity","master","regression"})
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public String randomeString() {
        return RandomStringUtils.randomAlphabetic(3);
    }

    public String randomeNum() {
        return RandomStringUtils.randomNumeric(7);
    }

    public String randomeAlphaNum() {
        return RandomStringUtils.randomAlphabetic(3) + "@" + RandomStringUtils.randomNumeric(7);
    }

    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);

        Files.copy(sourceFile.toPath(), targetFile.toPath());

        return targetFilePath;
    }
}
