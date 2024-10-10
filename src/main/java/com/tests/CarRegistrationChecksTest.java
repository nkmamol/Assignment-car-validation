package com.tests;

import com.pageObjectModel.CarRegistrationCheckPageClass;
import com.utility.Constants;
import com.application.DriverFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.utility.ReadTextDataFile;
import com.utility.VehicleDetails;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class CarRegistrationChecksTest {
    public String destFile;
    public ExtentTest test;
    public ExtentHtmlReporter htmlReports;   //to generate an html file
    private static ExtentReports extent;
    private WebDriver driver;
    private CarRegistrationCheckPageClass carRegistrationCheckPageClass;
    private ReadTextDataFile readTextDataFile;

    @BeforeSuite
    public void extentReportConfig() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        destFile = System.getProperty("user.dir") + Constants.reportsFilePath + "CarRegistrationCheck" + dateFormat.format(new Date());
        File newFolder = new File(destFile);
        boolean created = newFolder.mkdir();
        if (created)
            System.out.println("Folder is created !");
        else
            System.out.println("Unable to create folder");

        String destDir = "Car_Registration_Assignment_Report" + dateFormat.format(new Date()) + ".html";
        htmlReports = new ExtentHtmlReporter(destFile + "\\" + destDir);
        extent = new ExtentReports();
        extent.attachReporter(htmlReports);
        setExtent(extent);
        htmlReports.config().setReportName("Car Registration Check");
        htmlReports.config().setTheme(Theme.STANDARD);
        htmlReports.config().setDocumentTitle("Car Registration Check Result");
    }

    @BeforeMethod
    public void initializeBrowser() {
        DriverFactory driverFactory = new DriverFactory();
        driver = driverFactory.startBrowser("Chrome");
        carRegistrationCheckPageClass = new CarRegistrationCheckPageClass(driver);
        readTextDataFile = new ReadTextDataFile();
        driver.get(Constants.URL);
    }

    @Test
    public void VehicleDetails() throws IOException {
        Random random = new Random();
        List<String> registrationNumbers = readTextDataFile.readTextDataFile(Constants.TestDataPath + "car_input.txt");
        Map<String, VehicleDetails> expectedVehicleDetails = readTextDataFile.readOutputTextDataFile(Constants.TestDataPath + "car_output.txt");
        for (String registrationNumber : registrationNumbers) {
            String formattedRegNumber = registrationNumber.replaceAll(" ", "");
            String randomMilage = String.format("%14d", random.nextInt(10000));
            carRegistrationCheckPageClass.enterStepOneDetails(registrationNumber, randomMilage);
            VehicleDetails detailsFromWeb = carRegistrationCheckPageClass.getVehicleDetails(registrationNumber);

            if (detailsFromWeb == null) {
                System.out.println("Car Valuation Details mismatch for registration Number:" + registrationNumber);
                //Assert.assertFalse(readTextDataFile.compareDetailsVehicle(expectedVehicleDetails.get(formattedRegNumber), detailsFromWeb));
            } else {
                System.out.println("Car Valuation Details matched for registration Number:" + registrationNumber);
                Assert.assertTrue(readTextDataFile.compareDetailsVehicle(expectedVehicleDetails.get(formattedRegNumber), detailsFromWeb));
            }
            carRegistrationCheckPageClass.getBackToMainPage();

        }


    }

    @AfterMethod
    public void fnCloseBrowser(ITestResult testResult) {
        System.out.println("testResult========" + testResult.getStatus());
        if (testResult.getStatus() == ITestResult.FAILURE) {
            String name = "Test_Fail_ScreenShot";
        } else if (testResult.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, testResult.getThrowable());
        }
    }

    @AfterSuite
    public void End_SetUp() {
        extent.flush();
        if (driver != null)
            driver.quit();
    }

    public ExtentReports getExtent() {
        return extent;
    }

    public void setExtent(ExtentReports extent) {
        this.extent = extent;
    }
}
