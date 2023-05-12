package listener;


import com.aventstack.extentreports.Status;

import static constants.FrameworkConstants.*;

import io.qameta.allure.Attachment;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.*;
import utils.ScreenRecoderHelpers;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;


public class TestListener implements ITestListener, ISuiteListener, IInvokedMethodListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);
    private ScreenRecoderHelpers screenRecorder;
    private ByteArrayOutputStream request = new ByteArrayOutputStream();
    private ByteArrayOutputStream response = new ByteArrayOutputStream();
    private PrintStream requestVar = new PrintStream(request,true);
    private PrintStream responseVar = new PrintStream(response,true);

    public TestListener() {
        try {
            screenRecorder = new ScreenRecoderHelpers();
        } catch (IOException | AWTException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName()
                : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        // Before every method in the Test Class
        //System.out.println(method.getTestMethod().getMethodName());
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // After every method in the Test Class
        //System.out.println(method.getTestMethod().getMethodName());
    }

    @Override
    public void onStart(ISuite iSuite) {
        RestAssured.filters(new ResponseLoggingFilter(LogDetail.ALL,responseVar),
        new RequestLoggingFilter(LogDetail.ALL, requestVar));
    }

    @Override
    public void onFinish(ISuite iSuite) {

    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        if (VIDEO_RECORD.toLowerCase().trim().equals(YES)) {
            screenRecorder.stopRecording(true);
        }
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        screenRecorder.stopRecording(true);
        logRequest(request);
        logResponse(response);
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        if (VIDEO_RECORD.toLowerCase().trim().equals(YES)) {
            screenRecorder.stopRecording(true);
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        if (VIDEO_RECORD.toLowerCase().trim().equals(YES)) {
            screenRecorder.stopRecording(true);
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        logger.error("Test failed but it is in defined success ratio " + getTestName(iTestResult));
    }

    @Attachment(value ="request")
    public byte[] logRequest(final ByteArrayOutputStream stream){
        return attach(stream) ;
    }

    @Attachment(value ="response")
    public byte[] logResponse(final ByteArrayOutputStream stream){
        return attach(stream) ;
    }

    public byte[] attach(final ByteArrayOutputStream log){
        final byte[] array = log.toByteArray();
        log.reset();
        return array;
    }
}
