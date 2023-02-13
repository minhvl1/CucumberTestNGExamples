package pageActions;

import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import pageObjects.DemoQaUI;

public class DemoQaActions extends BaseTest {
    WebDriver driver;
    SoftAssert softAssert = new SoftAssert();
    public DemoQaActions(WebDriver driver){
        this.driver = driver;
    }
    public void clickToRadioButton(String label){
        clickElement(driver,DemoQaUI.getElementByLabel(label));
    }

    public void resultMessageContainText(String text){
      softAssert.assertTrue(getTextElement(driver,DemoQaUI.getResultText).contains(text));
      softAssert.assertAll();
    }

}
