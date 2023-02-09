/*
 * Copyright (c) 2022 Anh Tester
 * Automation Framework Selenium
 */

package constants;


import java.io.File;
import java.time.Duration;

//final -> We do not want any class to extend this class
public final class FrameworkConstants {

    //private -> We do not want anyone to create the object of this class
    private FrameworkConstants() {
    }
    public static final Duration LONG_TIMEOUT = Duration.ofSeconds(30);
    public static final String REPORT_TITLE="DEMO Cucumber Report";
    public static final String AUTHOR ="VLMinh";
    public static final String SEND_EMAIL_TO_USERS="NO";
}

