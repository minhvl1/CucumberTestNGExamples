package steps;

import common.RequestCapability;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.it.Ma;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.json.Json;

import java.util.List;
import java.util.Map;
import java.util.Objects;


public class JiraStep implements RequestCapability {
    Response response;
    @Given("Authen jira")
    public void authenJira() {
        String baseUrlJira = "https://vlminh.atlassian.net";
        String jiraPath= "/rest/api/3/project/AB";

        String email = "minhvu1234567yahoo@gmail.com";
        String apiToken = "ATATT3xFfGF0XnDA5e8iYNJFwaD7cCyEuav4Baax36Uyz9iRED5zqCWPG2f5ir-fe-i6wZfOOlRCbOqCp9C5Ypq1eY0pqSwSCdyVYzrCugTsdOzkA5D_WLWAfdHnMheyMCutUFp7uSUt__Si7BBtt_dCxO1PXvmoMtolaygqoJ4Ac3aBy_wl-As=0E5596A6";
        String creDential = email+":"+apiToken;

        byte[] encodedCred = Base64.encodeBase64(creDential.getBytes());
        String encodedCreStr = new String(encodedCred);

        RequestSpecification request = RestAssured.given();
        request.baseUri(baseUrlJira);
        request.header(defaultHeader);
        request.header(RequestCapability.getAuthenticatedHeader(encodedCreStr));

        response = request.get(jiraPath);
    }

    @Then("Show reponse")
    public void showReponse() {
    //        response.prettyPrint();

            //{} Map
            // [] List

            Map<String, List<Map<String,String>>> projectInfo = JsonPath.from(response.asString()).get();
            List<Map<String,String>> issueTypes = projectInfo.get("issueTypes");
    //        for (Map<String, String> issueType: issueTypes) {
    //            System.out.println(issueType.get("id"));
    //            System.out.println(issueType.get("name"));
    //            System.out.println("=====================");
    //        }

            Map<String, Map<String, Map<String,String>>>projectTypeKe = JsonPath.from(response.asString()).get();
            Map<String, Map<String,String>> lead = projectTypeKe.get("lead");
            Map<String,String> avatarUrls = lead.get("avatarUrls");
            System.out.println(avatarUrls.get("48x48"));
    }
}
