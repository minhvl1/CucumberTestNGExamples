package Steps;

import common.DataProviderManager;
import cucumberHooks.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class ProviderStep {
    WebDriver driver;
    public ProviderStep() {
        this.driver = Hooks.openAndQuitBrowser();
    }


@Test(dataProvider ="test-data2",dataProviderClass = DataProviderManager.class)
public void runProvider(String keyWord1, String keyWord2) {
    driver.get("https://www.google.com");
    WebElement txtBox = driver.findElement(By.xpath("//input[@class='gLFyf']"));
    txtBox.sendKeys(keyWord1," ",keyWord2);
    txtBox.sendKeys(Keys.ENTER);
}

@Test(dataProvider = "getSignInDataHashTable",dataProviderClass = DataProviderManager.class)
public void testGetSignInData(Hashtable<String, String> data) {
    System.out.println("signInData.testCaseName = " + data.get("TESTCASENAME"));
    System.out.println("signInData.username = " + data.get("EMAIL"));
    System.out.println("signInData.password = " + data.get("PASSWORD"));
//    System.out.println("signInData.expectedTitle = " + data.get(SignInModel.getExpectedTitle()));
//    System.out.println("signInData.expectedError = " + data.get(SignInModel.getExpectedError()));

}

}
