package common;

import constants.FrameworkConstants;
import helpers.ExcelHelpers;
import helpers.Helpers;
import org.testng.annotations.DataProvider;

public class DataProviderManager {
    @DataProvider(name = "test-data")
    public Object[][] dataProvFunc(){
        return new Object[][]{
                {"cmc global"},{"Automation"}
        };
    }

    @DataProvider(name = "test-data2")
    public Object[][] dataProvFunc2(){
        return new Object[][]{
                {"Selenium","Delhi"},{"QTP","Bangalore"},{"LoadRunner","Chennai"}
        };
    }

    @DataProvider(name = "getSignInDataHashTable")
    public static Object[][] getSignInData() {
        ExcelHelpers excelHelpers = new ExcelHelpers();
        Object[][] data = excelHelpers.getDataHashTable(Helpers.getCurrentDir() + FrameworkConstants.EXCEL_DATA_FILE_PATH, "SignIn", 5, 9);
        System.out.println("getSignInData: " + data);
        return data;
    }

}
