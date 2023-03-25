package steps;

import common.BaseTest;
import cucumberHooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;


public class Exam extends BaseTest {
    WebDriver driver;
    Assert anAssert;
    public Exam() {
        this.driver = Hooks.openAndQuitBrowser();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    private static final Logger logger = LogManager.getLogger(Exam.class);

    @Given("Calculator between {string} and {string}")
    public void calculatorBetweenAnd(String arg0, String arg1) throws ParseException {
        logger.info(verifyStringIsValidPage(arg0));
        logger.info(verifyStringIsValidPage(arg1));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
        Date firstDate = sdf.parse(arg0);
        Date secondDate = sdf.parse(arg1);
        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        logger.info("Day between:" + diff);
    }

    @Then("Calculator close date when add {int} month into {string}")
    public void calculatorCloseDateWhenAddMonthInto(int arg0, String arg1) throws ParseException {
        logger.info(verifyStringIsValidPage(arg1));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date incrementedDate = DateUtils.addMonths(sdf.parse(arg1), arg0);
        logger.info("Close date:" + sdf.format(incrementedDate));

    }

    @Given("Go to {string}")
    public void goTo(String arg0) {
        driver.get(arg0);
    }

    @Then("Verify placeholder is display")
    public void verifyPlaceholderIsDisplay() {
        logger.info("Email holder is display:" + isElementDisplayed(driver, "//input[@name='email' and @placeholder=\" \"]"));
        logger.info("Password holder is display:" + isElementDisplayed(driver, "//input[@name='password' and @placeholder=\" \"]"));

//            String script = "return document.querySelector('input[name=\"email\"][placeholder=\" \"').getAttribute('name')";
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            String content = (String) js.executeScript(script);
//            logger.info(content);
    }

    @And("Email placeholder have text should equal {string}")
    public void emailPlaceholderHaveTextShouldEqual(String arg0) {
        String emailText = getTextElement(driver, "//input[@name='email' and @placeholder=\" \"]//parent::label/span");
        logger.info(emailText);
        verifyEqualText(emailText,arg0);

    }

    @And("Password placeholder have text should equal {string}")
    public void passwordPlaceholderHaveTextShouldEqual(String arg0) {
        String passwordText = getTextElement(driver, "//input[@name='password' and @placeholder=\" \"]//parent::label/span");
        logger.info(passwordText);
        verifyEqualText(passwordText,arg0);
    }
}
