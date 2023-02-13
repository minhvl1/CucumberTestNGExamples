package constants;


import utils.Helpers;
import utils.PropertiesHelpers;

import java.time.Duration;

//final -> We do not want any class to extend this class
public final class FrameworkConstants {

    //private -> We do not want anyone to create the object of this class
    private FrameworkConstants() {
    }
    public static final String PROJECT_PATH = Helpers.getCurrentDir();
    public static final Duration WAIT_DEFAULT = Duration.ofSeconds(Long.parseLong(PropertiesHelpers.getValue("WAIT_DEFAULT")));
    public static final String REPORT_TITLE = PropertiesHelpers.getValue("REPORT_TITLE");
    public static final String AUTHOR =PropertiesHelpers.getValue("AUTHOR");
    public static final String BROWSER =PropertiesHelpers.getValue("BROWSER");
    public static final String SEND_EMAIL_TO_USERS = PropertiesHelpers.getValue("SEND_EMAIL_TO_USERS");
    public static final String EXCEL_DATA_FILE_PATH = PropertiesHelpers.getValue("EXCEL_DATA_FILE_PATH");
    public static final String EXCEL2_DATA_FILE_PATH = PropertiesHelpers.getValue("EXCEL2_DATA_FILE_PATH");
    public static final String BASE_FAKERESAPI_URL = PropertiesHelpers.getValue("BASE_FAKERESAPI_URL");
    public static final String FAKER_ACTIVITY_MODULE = PropertiesHelpers.getValue("FAKER_ACTIVITY_MODULE");
    public static final String BASE_REQRES_URL = PropertiesHelpers.getValue("BASE_REQRES_URL");
    public static final String EXCEL_DATAAPI_FILE_PATH = PropertiesHelpers.getValue("EXCEL_DATAAPI_FILE_PATH");
    public static final String YES = "yes";
    public static final String NO = "no";
}

