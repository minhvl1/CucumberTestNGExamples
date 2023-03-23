package steps;

import cucumberHooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pageActions.DemoQaActions;
import runners.TestRunnerAllFeatures;


public class DemoQa {

    WebDriver driver;
    private DemoQaActions demoQaActions;
    private static final Logger logger = LogManager.getLogger(DemoQa.class);
    final Level NOTICE = Level.forName("NOTICE", 450);

    public DemoQa() {
        this.driver = Hooks.openAndQuitBrowser();
        demoQaActions = new DemoQaActions(driver);
    }

    @Given("Go to demoqa url")
    public void goToDemoqaUrl() {
        logger.log(NOTICE, "Go to QA url");
        driver.get("https://demoqa.com/radio-button");
    }

    @When("check {string} radio button")
    public void checkArgRadioButton(String arg0) {
        logger.log(NOTICE, "Click to " + arg0 + " button");
        demoQaActions.clickToRadioButton(arg0.toLowerCase());
    }

    @Then("See result contain {string}")
    public void seeResultContainArg(String arg0) {
        logger.log(NOTICE, "Verify result should contain " + arg0);
        demoQaActions.resultMessageContainText(arg0);
    }

}
