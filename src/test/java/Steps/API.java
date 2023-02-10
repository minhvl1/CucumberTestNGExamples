package Steps;


import constants.FrameworkConstants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
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
    private static final Logger logger = Logger.getLogger(API.class);
    public API(){
        softAssert.assertAll();
    }
    @Given("send get method with id={string}")
    public void sendGetMethodWithId(String arg0) {
        RestAssured.baseURI = FrameworkConstants.BASE_FAKERESAPI_URL + FrameworkConstants.FAKER_ACTIVITY_MODULE;
        RequestSpecification httpRequest = RestAssured.given();

        getResponse = httpRequest.request(Method.GET, arg0);
        String responseBody = getResponse.getBody().asString();
        getListResponse = new ArrayList<String>(Arrays.asList(responseBody.split(",")));
    }

    @When("show get response body")
    public void showGetResponseBody() {
        logger.info("==================Response==================");
        logger.info("Status code:"+getResponse.getStatusCode());
        for(int i =0; i<getListResponse.size();i++){
            logger.info(getListResponse.get(i));
        }
        logger.info("==================End Response==================");
    }

    @Then("Status code is {string}")
    public void statusCodeIs(String arg0) {
        softAssert.assertEquals(getResponse.getStatusCode(),Integer.parseInt(arg0));
    }


    @Given("send post method with {string} module")
    public void sendPostMethodWithModule(String arg0) throws IOException {
        RestAssured.baseURI =FrameworkConstants.BASE_REQRES_URL;
        RequestSpecification httpRequest = RestAssured.given();
        JSONObject requestParams = new JSONObject();

        excelUtils.setExcelFile(FrameworkConstants.EXCEL_DATAAPI_FILE_PATH,"Sheet1");
        for(int i=1;i<=excelUtils.getRowCountInSheet();i++) {
            logger.info(excelUtils.getCellData(i,0));
            logger.info(excelUtils.getCellData(i,1));
            logger.info(excelUtils.getCellData(i,2));
            requestParams.put("name",excelUtils.getCellData(i,1));
            requestParams.put("job", excelUtils.getCellData(i,2));
            httpRequest.header("Content-Type", excelUtils.getCellData(i,0));
        }

        httpRequest.body(requestParams.toJSONString());
        postResponse = httpRequest.request(Method.POST,arg0);
        String responseBody = postResponse.getBody().asString();
        postListResponse = new ArrayList<String>(Arrays.asList(responseBody.split(",")));
    }

    @When("show post response body")
    public void showPostResponseBody() {
        logger.info("==================Response==================");
        logger.info("Status code:"+postResponse.getStatusCode());
        for(int i =0; i<postListResponse.size();i++){
            logger.info(postListResponse.get(i));
        }
        logger.info("==================End Response==================");
    }

    @Then("Status code post is {string}")
    public void statusCodePostIs(String arg0) {
        softAssert.assertEquals(postResponse.getStatusCode(),Integer.parseInt(arg0));
    }

}
