package com.pageObjectModel;

import com.utility.VehicleDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CarRegistrationCheckPageClass {
    @FindBy(className = "loader")
    WebElement loader;
    @FindBy(xpath = "//a[contains(@class, 'logo-link')]")
    WebElement logoLink;
    @FindBy(id = "onetrust-accept-btn-handler")
    WebElement onetrustBtn;
    @FindBy(id = "vehicleReg")
    WebElement vehicleRegistartionInput;
    @FindBy(id = "Mileage")
    WebElement vehicleMilageInput;
    @FindBy(id = "btn-go")
    WebElement vehicleSubmitBtn;
    @FindBy(id = "btn-back")
    WebElement backBtn;
    @FindBy(xpath = "(//div[contains(text(), 'Manufacturer:')])[2]/following-sibling::*")
    WebElement carManufacturer;
    @FindBy(xpath = "(//div[contains(text(), 'Model:')])[2]/following-sibling::*")
    WebElement carModelElement;
    @FindBy(xpath = "(//div[contains(text(), 'Year:')])[2]/following-sibling::*")
    WebElement carYearElement;
    @FindBy(xpath = "//div[contains(@class, 'primary-section')]//div[contains(@class, 'amount')][1]")
    WebElement valuationContainer;
    private WebDriver driver;
    private WebDriverWait wait;

    public CarRegistrationCheckPageClass(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 2);
        PageFactory.initElements(driver, this);
    }

    public void enterStepOneDetails(String regnumber, String milage) {
        if (onetrustBtn.isDisplayed()) {
            onetrustBtn.click();
        }
        vehicleRegistartionInput.clear();
        vehicleRegistartionInput.sendKeys(regnumber);

        vehicleMilageInput.clear();
        vehicleMilageInput.sendKeys(milage);

        vehicleSubmitBtn.click();
    }

    public boolean isVehicleDetailsFound() {
        try {
            wait.until(ExpectedConditions.visibilityOf(carManufacturer));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void getBackToMainPage() {
        driver.navigate().back();
//        if (isVehicleDetailsFound()) {
//            backBtn.click();
//        } else {
//            System.out.println("djkhdjsfhsdkjfhsdjkfhsd");
//            driver.navigate().back();
//        }
    }

    public VehicleDetails getVehicleDetails(String registrationNumber) {
        String formattedRegNumber = registrationNumber.replaceAll(" ", "");
        if (isVehicleDetailsFound()) {
            String carBrand = carManufacturer.getText();
            String carModel = carModelElement.getText();
            String carYear = carYearElement.getText();
            return new VehicleDetails(formattedRegNumber, carBrand, carModel, carYear);
        } else {
            return null;
        }
    }

    public void checkForInvisibilityOfLoader() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
    }
}
