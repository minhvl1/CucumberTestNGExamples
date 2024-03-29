package common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.service.ExtentService;
import constants.FrameworkConstants;
import io.restassured.response.Response;
import org.apache.commons.validator.GenericValidator;
import org.apache.logging.log4j.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.InputMismatchException;


public class BaseTest {
    private Duration longTimeout = FrameworkConstants.WAIT_DEFAULT;

    public WebElement getElementByXpath(WebDriver driver, String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public static final Level NOTICE = Level.forName("NOTICE", 450);

    public static void addExtentReportEnvironment(WebDriver driver) {
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        ExtentReports extentReports = ExtentService.getInstance();

        extentReports.setSystemInfo("Author", FrameworkConstants.AUTHOR);
        extentReports.setSystemInfo("Report Title", FrameworkConstants.REPORT_TITLE);
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
        extentReports.setSystemInfo("Environment", FrameworkConstants.ENVIRONMENT);
        extentReports.setSystemInfo("Browser", cap.getBrowserName() + " " + cap.getBrowserVersion());

    }

    public void waitForElementClickable(WebDriver driver, String xpath) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    public void waitForElementVisible(WebDriver driver, String xpath) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public Boolean isElementDisplayed(WebDriver driver, String locatorType) {
        waitForElementVisible(driver, locatorType);
        return getElementByXpath(driver, locatorType).isDisplayed();
    }

    public void clickElement(WebDriver driver, String xpath) {
        waitForElementClickable(driver, xpath);
        getElementByXpath(driver, xpath).click();
    }

    public String getTextElement(WebDriver driver, String xpath) {
        waitForElementVisible(driver, xpath);
        return getElementByXpath(driver, xpath).getText();
    }

    public void verifyEqualText(String actualString, String expectedString) {
        SoftAssert assertEqual = new SoftAssert();
        assertEqual.assertEquals(actualString, expectedString);
        assertEqual.assertAll();
    }

    public String verifyStringIsValidPage(String date) {
        Boolean valid = GenericValidator.isDate(date, "yyyyMMdd", true);
        if (valid == true) {
            return date + " is valid date";
        } else
            throw new InputMismatchException(date + " is not valid date");
    }

    public static void logIntoExtentReport(Response response, String type){
        switch (type.toLowerCase()){
            case "statuscode":
                ExtentCucumberAdapter.getCurrentStep().info(MarkupHelper.createLabel("Status code:" + response.getStatusCode(), ExtentColor.ORANGE));
                break;
            case "responsebody":
                ExtentCucumberAdapter.getCurrentStep().info(MarkupHelper.createLabel("Response:" + response.prettyPrint(),ExtentColor.GREY));
                break;
        }
    }
}
