package Steps;

import cucumberHooks.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ProviderStep {
    WebDriver driver;
    public ProviderStep() {
        this.driver = Hooks.openAndQuitBrowser();
    }

    @DataProvider(name = "test-data")
    public Object[][] dataProvFunc(){
        return new Object[][]{
                {"CMC global"},{"Automation"}
        };
    }
    @Test(dataProvider ="test-data")
    public void runProvider(String keyWord) {
        driver.get("https://www.google.com");
        WebElement txtBox = driver.findElement(By.xpath("//input[@class='gLFyf']"));
        txtBox.sendKeys(keyWord);
        Reporter.log("Keyword entered is : " +keyWord);
        txtBox.sendKeys(Keys.ENTER);
        Reporter.log("Search results are displayed.");
    }
}
