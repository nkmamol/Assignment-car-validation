package com.application;

import com.utility.Constants;
//import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    WebDriver driver;
    public String driverpath;


    public WebDriver startBrowser(String browser) {
        if (browser.trim().equalsIgnoreCase("Chrome")) {

            ChromeOptions options = new ChromeOptions();
            options.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
            options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
            options.addArguments("--start-maximized");
            options.addArguments("--disable-default-apps");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable.dev.shm.usage");
            options.setExperimentalOption("excludeSwithes", new String[]{"enable-automation"});
            options.setExperimentalOption("useAutomationExtension", false);
            // WebDriverManager.chromedriver().setup();
            driverpath = Constants.driverPath + "/chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", driverpath);
            driver = new ChromeDriver();
            tlDriver.set(driver);
            setImplicitlyWait(Constants.Implicit_Wait);

        } else {
            System.out.println("No Browser is selected for opening");
        }
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        return getDriver();
    }

    /*
    Implicit waits
     */
    public void setImplicitlyWait(int seconds) {
        getDriver().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    /*
     * returns the driver instance
     */
    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }
}
