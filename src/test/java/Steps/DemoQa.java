package Steps;

import cucumberHooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import pageObject.DemoQaObject;


public class DemoQa {

    WebDriver driver;
    private DemoQaObject demoQaObject;

    public DemoQa() {
        this.driver = Hooks.openAndQuitBrowser();
        demoQaObject = new DemoQaObject(driver);
    }

    @Given("Go to demoqa url")
    public void goToDemoqaUrl() {
        driver.get("https://demoqa.com/radio-button");
    }

    @When("check {string} radio button")
    public void checkArgRadioButton(String arg0) {
        demoQaObject.clickToRadioButton(arg0.toLowerCase());
    }

    @Then("See result contain {string}")
    public void seeResultContainArg(String arg0) {
        demoQaObject.resultMessageContainText(arg0);
    }


}
