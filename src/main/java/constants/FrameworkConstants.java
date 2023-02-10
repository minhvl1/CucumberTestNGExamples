/*
 * Copyright (c) 2022 Anh Tester
 * Automation Framework Selenium
 */

package constants;


import helpers.Helpers;
import helpers.PropertiesHelpers;

import java.io.File;
import java.time.Duration;

//final -> We do not want any class to extend this class
public final class FrameworkConstants {

    //private -> We do not want anyone to create the object of this class
    private FrameworkConstants() {
    }
    public static final String PROJECT_PATH = Helpers.getCurrentDir();
    public static final Duration LONG_TIMEOUT = Duration.ofSeconds(30);
    public static final String REPORT_TITLE = PropertiesHelpers.getValue("REPORT_TITLE");
    public static final String AUTHOR =PropertiesHelpers.getValue("AUTHOR");
    public static final String SEND_EMAIL_TO_USERS = PropertiesHelpers.getValue("SEND_EMAIL_TO_USERS");
    public static final String PROJECT_NAME = PropertiesHelpers.getValue("PROJECT_NAME");
    public static final String EXTENT_REPORT_NAME = PropertiesHelpers.getValue("EXTENT_REPORT_NAME");
    public static final String EXTENT_REPORT_PDF = PropertiesHelpers.getValue("EXTENT_REPORT_PDF");
    public static final String EXTENT_REPORT_FOLDER = PropertiesHelpers.getValue("EXTENT_REPORT_FOLDER");
    public static final String EXPORT_VIDEO_PATH = PropertiesHelpers.getValue("EXPORT_VIDEO_PATH");
    public static final String EXPORT_CAPTURE_PATH = PropertiesHelpers.getValue("EXPORT_CAPTURE_PATH");
    public static final String EXTENT_REPORT_FILE_NAME = EXTENT_REPORT_NAME + ".html";
    public static final String EXTENT_REPORT_FOLDER_PATH = PROJECT_PATH + EXTENT_REPORT_FOLDER;
    public static final String OVERRIDE_REPORTS = PropertiesHelpers.getValue("OVERRIDE_REPORTS");
    public static final String OPEN_REPORTS_AFTER_EXECUTION = PropertiesHelpers.getValue("OPEN_REPORTS_AFTER_EXECUTION");
    public static final String EXCEL_DATA_FILE_PATH = PropertiesHelpers.getValue("EXCEL_DATA_FILE_PATH");
    public static final String EXCEL2_DATA_FILE_PATH = PropertiesHelpers.getValue("EXCEL2_DATA_FILE_PATH");
    public static final String BASE_FAKERESAPI_URL = PropertiesHelpers.getValue("BASE_FAKERESAPI_URL");
    public static final String FAKER_ACTIVITY_MODULE = PropertiesHelpers.getValue("FAKER_ACTIVITY_MODULE");
    public static final String BASE_REQRES_URL = PropertiesHelpers.getValue("BASE_REQRES_URL");
    public static final String EXCEL_DATAAPI_FILE_PATH = PropertiesHelpers.getValue("EXCEL_DATAAPI_FILE_PATH");
}

