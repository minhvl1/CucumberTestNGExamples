package runners;


import common.BaseTest;
import constants.FrameworkConstants;
import cucumberHooks.CucumberListener;
import cucumberHooks.Hooks;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import steps.API;
import utils.*;


@Test
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"steps", "cucumberHooks"},
        plugin = {"cucumberHooks.CucumberListener",
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "html:target/cucumber-reports/cucumber-reports.html",
                "json:target/cucumber-reports/cucumber-reports.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome = true
//        , tags = "not @Jira and not @FeatureApi"
        , tags = "not @Jira and not @EXAM"
)

public class TestRunnerAllFeatures extends AbstractTestNGCucumberTests {
    static FileHelpers fileHelpers = new FileHelpers();
    private static final Logger logger = LogManager.getLogger(TestRunnerAllFeatures.class);

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

//    @Override
//    public void tearDownClass(){
//    }

    @AfterSuite
    public void afterSuite() {
        logger.info("================ AFTER SUITE ================");
        EmailSendUtils.sendEmail(CucumberListener.count_totalTCs
                , CucumberListener.count_passedTCs
                , CucumberListener.count_failedTCs
                , CucumberListener.count_skippedTCs);

        SlackIntergration.sendResultRunnerToSlack(CucumberListener.count_totalTCs
                , CucumberListener.count_passedTCs
                , CucumberListener.count_failedTCs
                , CucumberListener.count_skippedTCs);

        TeamsIntegration.sendMessageToTeams(CucumberListener.count_totalTCs
                , CucumberListener.count_passedTCs
                , CucumberListener.count_failedTCs
                , CucumberListener.count_skippedTCs);

        logger.log(BaseTest.NOTICE,"Total:" + CucumberListener.count_totalTCs);
        logger.log(BaseTest.NOTICE,"Pass:" + CucumberListener.count_passedTCs);
        logger.log(BaseTest.NOTICE,"Failed:" + CucumberListener.count_failedTCs);
        logger.log(BaseTest.NOTICE,"Skipped:" + CucumberListener.count_skippedTCs);
    }

    WebDriver driver;

    @BeforeSuite
    public void cleanReport() {
        logger.info("================ BEFORE SUITE ================");
        logger.info("Environment:" + FrameworkConstants.ENVIRONMENT);
        fileHelpers.cleanAllureReportFiles();
        fileHelpers.cleanExtentReportFiles();
    }

    @BeforeSuite
    public void generateExtentReport() {
        this.driver = Hooks.openAndQuitBrowser();
        BaseTest.addExtentReportEnvironment(driver);
    }

}
