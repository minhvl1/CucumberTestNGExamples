package steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.PropertiesHelpers;

import io.cucumber.java.en.Given;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class ProviderStep {
    //    WebDriver driver;
    private static final Logger logger = LogManager.getLogger(ProviderStep.class);
//    public ProviderStep() {
//        this.driver = Hooks.openAndQuitBrowser();

//    }


//@Test(dataProvider ="test-data2",dataProviderClass = DataProviderManager.class)
//public void runProvider(String keyWord1, String keyWord2) {
//    driver.get("https://www.google.com");
//    WebElement txtBox = driver.findElement(By.xpath("//input[@class='gLFyf']"));
//    txtBox.sendKeys(keyWord1," ",keyWord2);
//    txtBox.sendKeys(Keys.ENTER);
//}
//
//@Test(dataProvider = "getSignInDataHashTable",dataProviderClass = DataProviderManager.class)
//public void testGetSignInData(Hashtable<String, String> data) {
//    System.out.println("signInData.testCaseName = " + data.get("TESTCASENAME"));
//    System.out.println("signInData.username = " + data.get("EMAIL"));
//    System.out.println("signInData.password = " + data.get("PASSWORD"));
////    System.out.println("signInData.expectedTitle = " + data.get(SignInModel.getExpectedTitle()));
////    System.out.println("signInData.expectedError = " + data.get(SignInModel.getExpectedError()));
//
//}

    //    @Test(dataProvider = "getDataProviderTable",dataProviderClass = DataProviderManager.class)
//    public void testDataProvider(Hashtable<String, String> data) {
//        System.out.println(data.get("1"));
//        System.out.println(data.get("2"));
//        System.out.println(data.get("3"));
//        System.out.println(data.get("4"));
//        System.out.println(data.get("5"));
//
//    }
//    static ExcelUtils excelUtils = new ExcelUtils();
//    static String excelFilePath = FrameworkConstants.EXCEL2_DATA_FILE_PATH;
//
//    @Given("Test")
//    public void test() throws Exception {
//        excelUtils.setExcelFile(excelFilePath, "Sheet1");
//        for (int i = 1; i <= excelUtils.getRowCountInSheet(); i++) {
//            System.out.println(excelUtils.getCellData(i, 0));
//            System.out.println(excelUtils.getCellData(i, 1));
//            System.out.println(excelUtils.getCellData(i, 2));
//            System.out.println(excelUtils.getCellData(i, 3));
//            System.out.println(excelUtils.getCellData(i, 4));
//        }
//
//
//    }
    @Given("Test")
    public void test() {
        logger.info("Enviroment:" + PropertiesHelpers.getEnvironment("ENV"));
    }
}


