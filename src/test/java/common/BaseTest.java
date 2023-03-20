package common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.service.ExtentService;
import constants.FrameworkConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {
    private Duration longTimeout = FrameworkConstants.WAIT_DEFAULT;
    public WebElement getElementByXpath(WebDriver driver,String xpath){
        return driver.findElement(By.xpath(xpath));
    }

    public static void addExtentReportEnvironment() {
        ExtentReports extentReports = ExtentService.getInstance();
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
        extentReports.setSystemInfo("Env", FrameworkConstants.ENVIRONMENT);
        extentReports.setSystemInfo("Base URL", FrameworkConstants.BASE_FAKERESAPI_URL);
    }
    public void waitForElementClickable(WebDriver driver,String xpath){
        WebDriverWait explicitWait = new WebDriverWait(driver,longTimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    public void waitForElementVisible(WebDriver driver, String xpath){
        WebDriverWait explicitWait = new WebDriverWait(driver,longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public Boolean isElementDisplayed(WebDriver driver, String locatorType){
        return getElementByXpath(driver,locatorType).isDisplayed();
    }
    public void clickElement(WebDriver driver,String xpath){
        waitForElementClickable(driver,xpath);
        getElementByXpath(driver,xpath).click();
    }

    public String getTextElement(WebDriver driver,String xpath){
        waitForElementVisible(driver,xpath);
        return getElementByXpath(driver,xpath).getText();
    }
}
