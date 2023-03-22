package steps;


import com.google.gson.JsonObject;
import constants.FrameworkConstants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.asserts.SoftAssert;

import utils.ExcelUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class API {
    SoftAssert softAssert = new SoftAssert();

    List<String> getListResponse;
    Response getResponse;
    List<String> postListResponse;
    Response postResponse;

    static ExcelUtils excelUtils = new ExcelUtils();
    private static final Logger logger = LogManager.getLogger(API.class);

    public API() {
        softAssert.assertAll();
    }

    @Given("send get method with id={string}")
    public void sendGetMethodWithId(String arg0) {
        RestAssured.baseURI = FrameworkConstants.BASE_FAKERESAPI_URL + FrameworkConstants.FAKER_ACTIVITY_MODULE;
        RequestSpecification httpRequest = RestAssured.given();

        getResponse = httpRequest.request(Method.GET, arg0);
//        getResponse.prettyPrint();
        String responseBody = getResponse.getBody().asString();
        getListResponse = new ArrayList<String>(Arrays.asList(responseBody.split(",")));
    }

    @When("show get response body")
    public void showGetResponseBody() {
        logger.info("==================Response==================");
        logger.info("Status code:" + getResponse.getStatusCode());
        for (int i = 0; i < getListResponse.size(); i++) {
            logger.info(getListResponse.get(i));
        }
        logger.info("==================End Response==================");
    }

    @Then("Status code is {string}")
    public void statusCodeIs(String arg0) {
        softAssert.assertEquals(getResponse.getStatusCode(), Integer.parseInt(arg0));
    }


    @Given("send post method with {string} module")
    public void sendPostMethodWithModule(String arg0) throws IOException {
        RestAssured.baseURI = FrameworkConstants.BASE_REQRES_URL;
        RequestSpecification httpRequest = RestAssured.given();
        JsonObject requestParams = new JsonObject();

        excelUtils.setExcelFile(FrameworkConstants.EXCEL_DATAAPI_FILE_PATH, "Sheet1");
        for (int i = 1; i <= excelUtils.getRowCountInSheet(); i++) {
            logger.info(excelUtils.getCellData(i, 0));
            logger.info(excelUtils.getCellData(i, 1));
            logger.info(excelUtils.getCellData(i, 2));
            requestParams.addProperty("name", excelUtils.getCellData(i, 1));
            requestParams.addProperty("job", excelUtils.getCellData(i, 2));
            httpRequest.header("Content-Type", excelUtils.getCellData(i, 0));
        }

        httpRequest.body(requestParams.toString());
        postResponse = httpRequest.request(Method.POST, arg0);
//        postResponse.prettyPrint();
        String responseBody = postResponse.getBody().asString();
        postListResponse = new ArrayList<String>(Arrays.asList(responseBody.split(",")));
    }

    @When("show post response body")
    public void showPostResponseBody() {
        logger.info("==================Response==================");
        logger.info("Status code:" + postResponse.getStatusCode());
        for (int i = 0; i < postListResponse.size(); i++) {
            logger.info(postListResponse.get(i));
        }
        logger.info("==================End Response==================");
    }

    @Then("Status code post is {string}")
    public void statusCodePostIs(String arg0) {
        softAssert.assertEquals(postResponse.getStatusCode(), Integer.parseInt(arg0));
    }

    @Given("test datadriven fakeresapi")
    public void testDatadrivenFakeresapi() throws IOException {
        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1/";
        RequestSpecification httpRequest = RestAssured.given();
        JsonObject requestParams = new JsonObject();

        excelUtils.setExcelFile(FrameworkConstants.EXCEL_DATAAPI_FILE_PATH, "Sheet2");
        for (int i = 1; i <= excelUtils.getRowCountInSheet(); i++) {
            logger.info(excelUtils.getCellData(i, 0));
            logger.info(excelUtils.getCellData(i, 1));
            logger.info(excelUtils.getCellData(i, 2));
            logger.info(excelUtils.getCellData(i, 3));
            requestParams.addProperty("id", excelUtils.getCellData(i, 0));
            requestParams.addProperty("title", excelUtils.getCellData(i, 1));
            requestParams.addProperty("dueDate", excelUtils.getCellData(i, 2));
            boolean completed;
            if (excelUtils.getCellData(i, 3) == "TRUE") {
                completed = true;
                requestParams.addProperty("completed", completed);
            } else {
                completed = false;
                requestParams.addProperty("completed", completed);
            }
            httpRequest.header("Content-Type", "application/json");
            httpRequest.body(requestParams.toString());
            postResponse = httpRequest.request(Method.POST, "Activities");
            String responseBody = postResponse.getBody().asString();
            postListResponse = new ArrayList<String>(Arrays.asList(responseBody.split(",")));

            logger.info("==================Response==================");
            logger.info("Status code:" + postResponse.getStatusCode());
            for (int y = 0; y < postListResponse.size(); y++) {
                logger.info(postListResponse.get(y));
            }
            logger.info("==================End Response==================");
        }
    }

    @Then("test datadriven reqres")
    public void testDatadrivenReqres() throws IOException {
        excelUtils.setExcelFile(FrameworkConstants.EXCEL_DATAAPI_FILE_PATH, "Sheet3");
        logger.info("row:" + excelUtils.getRowCountInSheet());
        RestAssured.baseURI = "https://reqres.in/api/";
        RequestSpecification httpRequest = RestAssured.given();
        JsonObject requestParams = new JsonObject();
            for (int i = 1; i <= excelUtils.getRowCountInSheet(); i++) {
                logger.info("count:" + excelUtils.getCellData(i, 2));
                logger.info(excelUtils.getCellData(i, 0));
                logger.info(excelUtils.getCellData(i, 1));
                requestParams.addProperty("name", excelUtils.getCellData(i, 0));
                requestParams.addProperty("job", excelUtils.getCellData(i, 1));

                httpRequest.header("Content-Type", "application/json");
                httpRequest.body(requestParams.toString());
                postResponse = httpRequest.request(Method.POST, "users");
                String responseBody = postResponse.getBody().asString();
                postListResponse = new ArrayList<String>(Arrays.asList(responseBody.split(",")));

                logger.info("==================Response==================");
                logger.info("Status code:" + postResponse.getStatusCode());
                for (int y = 0; y < postListResponse.size(); y++) {
                    logger.info(postListResponse.get(y));
                }
                logger.info("==================End Response==================");
            }
        }

}

