package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pageUIs.GoogleUI;

public class GooglePageObject {
    private WebDriver driver;

    public GooglePageObject(WebDriver driver){
        this.driver = driver;
    }

    public void sendKeySearchTextbox(String sendKey){
            switch (sendKey.toLowerCase()){
                case "enter":
                    driver.findElement(By.xpath(GoogleUI.getGoogleSearchTextbox)).sendKeys(Keys.RETURN);
                break;
                default:
                    driver.findElement(By.xpath(GoogleUI.getGoogleSearchTextbox)).sendKeys(sendKey);
            }


    }

}
