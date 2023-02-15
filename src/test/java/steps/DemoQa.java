package steps;

import cucumberHooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pageActions.DemoQaActions;


public class DemoQa {

    WebDriver driver;
    private DemoQaActions demoQaActions;

    public DemoQa() {
        this.driver = Hooks.openAndQuitBrowser();
        demoQaActions = new DemoQaActions(driver);
    }

    @Given("Go to demoqa url")
    public void goToDemoqaUrl() {
        driver.get("https://demoqa.com/radio-button");
    }

    @When("check {string} radio button")
    public void checkArgRadioButton(String arg0) {
        demoQaActions.clickToRadioButton(arg0.toLowerCase());
    }

    @Then("See result contain {string}")
    public void seeResultContainArg(String arg0) {
        demoQaActions.resultMessageContainText(arg0);
    }

}
