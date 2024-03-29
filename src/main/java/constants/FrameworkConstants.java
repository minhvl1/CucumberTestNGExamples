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
    public static final String ENVIRONMENT = PropertiesHelpers.getEnvironment("ENV");
    public static final Duration WAIT_DEFAULT = Duration.ofSeconds(Long.parseLong(PropertiesHelpers.getValue("WAIT_DEFAULT")));
    public static final String REPORT_TITLE = PropertiesHelpers.getEnvironment("REPORT_TITLE");
    public static final String AUTHOR =PropertiesHelpers.getEnvironment("AUTHOR");
    public static final String BROWSER =PropertiesHelpers.getEnvironment("BROWSER");
    public static final String SEND_EMAIL_TO_USERS = PropertiesHelpers.getEnvironment("SEND_EMAIL_TO_USERS");
    public static final String EXCEL_DATA_FILE_PATH = PropertiesHelpers.getEnvironment("EXCEL_DATA_FILE_PATH");
    public static final String EXCEL2_DATA_FILE_PATH = PropertiesHelpers.getEnvironment("EXCEL2_DATA_FILE_PATH");
    public static final String BASE_FAKERESAPI_URL = PropertiesHelpers.getEnvironment("BASE_FAKERESAPI_URL");
    public static final String FAKER_ACTIVITY_MODULE = PropertiesHelpers.getEnvironment("FAKER_ACTIVITY_MODULE");
    public static final String BASE_REQRES_URL = PropertiesHelpers.getEnvironment("BASE_REQRES_URL");
    public static final String EXCEL_DATAAPI_FILE_PATH = PropertiesHelpers.getEnvironment("EXCEL_DATAAPI_FILE_PATH");
    public static final String SEND_MSG_TO_TEAMS = PropertiesHelpers.getEnvironment("SEND_TO_TEAMS");
    public static final String SEND_MSG_TO_SLACK = PropertiesHelpers.getEnvironment("SEND_TO_SLACK");
    public static final String YES = "yes";
    public static final String NO = "no";
    public static final String EXPORT_VIDEO_PATH="ExportData/Videos";
    public static final String VIDEO_RECORD ="no"; //available with browser headed mode
}

