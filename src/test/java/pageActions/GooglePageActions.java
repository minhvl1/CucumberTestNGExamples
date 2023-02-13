package pageActions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pageObjects.GoogleUI;

public class GooglePageActions {
    private WebDriver driver;

    public GooglePageActions(WebDriver driver){
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
