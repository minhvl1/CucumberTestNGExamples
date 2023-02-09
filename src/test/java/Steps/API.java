package Steps;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class API {
    SoftAssert softAssert = new SoftAssert();
    List<String> getListResponse;
    Response getResponse;
    List<String> postListResponse;
    Response postResponse;
    @Given("send get method with id={string}")
    public void sendGetMethodWithId(String arg0) {
        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1/Activities";
        RequestSpecification httpRequest = RestAssured.given();

//        https://fakerestapi.azurewebsites.net/api/v1/Activities/1
        getResponse = httpRequest.request(Method.GET, arg0);
        String responseBody = getResponse.getBody().asString();
        getListResponse = new ArrayList<String>(Arrays.asList(responseBody.split(",")));
    }

    @When("show get response body")
    public void showGetResponseBody() {
        System.out.println("==================Response==================");
        System.out.println("Status code:"+getResponse.getStatusCode());
        for(int i =0; i<getListResponse.size();i++){
            System.out.println(getListResponse.get(i));
        }
        System.out.println("==================End Response==================");
    }

    @Then("Status code is {string}")
    public void statusCodeIs(String arg0) {
        softAssert.assertEquals(getResponse.getStatusCode(),Integer.parseInt(arg0));
        softAssert.assertAll();
    }



    @Given("send post method")
    public void sendPostMethod() {
        RestAssured.baseURI ="https://reqres.in/api";
        RequestSpecification httpRequest = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "morpheus");
        requestParams.put("job", "leader");
        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParams.toJSONString());
        postResponse = httpRequest.request(Method.POST,"/users");
        String responseBody = postResponse.getBody().asString();
        postListResponse = new ArrayList<String>(Arrays.asList(responseBody.split(",")));
    }

    @When("show post response body")
    public void showPostResponseBody() {
        System.out.println("==================Response==================");
        System.out.println("Status code:"+postResponse.getStatusCode());
        for(int i =0; i<postListResponse.size();i++){
            System.out.println(postListResponse.get(i));
        }

        System.out.println("==================End Response==================");
    }

    @Then("Status code post is {string}")
    public void statusCodePostIs(String arg0) {
        softAssert.assertEquals(postResponse.getStatusCode(),Integer.parseInt(arg0));
        softAssert.assertAll();
    }
}
