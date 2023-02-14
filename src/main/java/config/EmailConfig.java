/*
 * Copyright (c) 2022.
 * Automation Framework Selenium - Anh Tester
 */

package config;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * Data for Sending email after execution
 */
public class EmailConfig {

    //Nhớ tạo mật khẩu app (App Password) cho Gmail mới gửi được nhen
    //Nếu dùng config của Hosting thì bình thường
    //Enable Override Report and Send config in config file => src/test/resources/config/config.properties
    //OVERRIDE_REPORTS=yes
    //send_email_to_users=yes

    public static final String SERVER = "smtp.gmail.com";
    public static final String PORT = "587";

    public static final String FROM = "minhvu1234567yahoo@gmail.com";
    public static final String PASSWORD = "bwkuznvfgzlalnsu";

    public static final String[] TO = {"minhvu890yahoo@gmail.com",
            "minhvu12344yahoo@gmail.com"};

    static LocalDateTime localNow = LocalDateTime.now(TimeZone.getTimeZone("Asia/Ho_Chi_Minh").toZoneId());
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-YY HH:mm:ss");
    static String formatDateTime = localNow.format(formatter);
    public static final String SUBJECT = "Report Automation Test "+ formatDateTime;
}