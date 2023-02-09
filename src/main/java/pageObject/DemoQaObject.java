package pageObject;

import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import pageUIs.DemoQaUI;

public class DemoQaObject extends BaseTest {
    WebDriver driver;
    SoftAssert softAssert = new SoftAssert();
    public DemoQaObject(WebDriver driver){
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
