package runners;

import cucumberHooks.CucumberListener;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import steps.API;
import utils.*;

import java.io.IOException;


@Test
@CucumberOptions(
        features = "src/test/resources/features/TC01-API.feature",
        glue = {"steps","cucumberHooks"},
        plugin = {"cucumberHooks.CucumberListener",
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "html:target/cucumber-reports/cucumber-reports.html",
                "json:target/cucumber-reports/cucumber-reports.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
         monochrome = true,
        tags = "@Feature4"
)

public class TestRunnerapi extends AbstractTestNGCucumberTests {
    static FileHelpers fileHelpers = new FileHelpers();
    private static final Logger logger = Logger.getLogger(API.class);
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
    }
    @BeforeSuite
    public void cleanReport() throws IOException {
        logger.info("================ BEFORE SUITE ================");
        logger.info("Environment:"+ PropertiesHelpers.getEnvironment("ENV"));
        fileHelpers.cleanAllureReportFiles();
        fileHelpers.cleanExtentReportFiles();
    }
}
