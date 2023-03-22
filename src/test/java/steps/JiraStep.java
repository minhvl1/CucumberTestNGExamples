package steps;


import com.google.gson.*;
import models.IssueJira;
import models.RequestCapability;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.codec.binary.Base64;
import utils.FileHelpers;
import java.io.*;
import java.util.List;
import java.util.Map;


public class JiraStep implements RequestCapability {
    Response response;
    String baseUrlJira = "https://vlminh.atlassian.net";
    String jiraPath= "/rest/api/3/project/AB";
    String issuePath="/rest/api/3/issue";
    String email = "";
    String apiToken = "";
    String creDential = email+":"+apiToken;



    @Given("Authen jira")
    public void authenJira() {
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
//            response.prettyPrint();

            //{} Map
            // [] List

            Map<String, List<Map<String,String>>> projectInfo = JsonPath.from(response.asString()).get();
            List<Map<String,String>> issueTypes = projectInfo.get("issueTypes");
//            for (Map<String, String> issueType: issueTypes) {
//                System.out.println(issueType.get("id"));
//                System.out.println(issueType.get("name"));
//                System.out.println("=====================");
//            }

            Map<String, Map<String, Map<String,String>>>projectTypeKe = JsonPath.from(response.asString()).get();
            Map<String, Map<String,String>> lead = projectTypeKe.get("lead");
            Map<String,String> avatarUrls = lead.get("avatarUrls");
            System.out.println(avatarUrls.get("48x48"));
    }


    @Given("Create issue")
    public void createIssue() {

        byte[] encodedCred = Base64.encodeBase64(creDential.getBytes());
        String encodedCreStr = new String(encodedCred);

        //create request
        RequestSpecification request = RestAssured.given();
        request.baseUri(baseUrlJira);
        request.header(defaultHeader);
        request.header(RequestCapability.getAuthenticatedHeader(encodedCreStr));

        response = request.body(IssueJira.CreateIssueJiraBody()).post(issuePath);
        response.prettyPrint();

    }

    @Then("Give id issue")
    public void giveIdIssue() throws IOException {
        Map<String,String> createIssueResponse = JsonPath.from(response.asString()).get();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("self", createIssueResponse.get("self"));
        FileWriter file = new FileWriter("src/test/resources/testdata/Jira.json");
        file.write(jsonObject.toString());
        file.close();

    }

    @Given("Delete issue")
    public void deleteIssue() throws IOException {
        byte[] encodedCred = Base64.encodeBase64(creDential.getBytes());
        String encodedCreStr = new String(encodedCred);



        //create request
        RequestSpecification request = RestAssured.given();
        request.baseUri(baseUrlJira);
        request.header(defaultHeader);
        request.header(RequestCapability.getAuthenticatedHeader(encodedCreStr));

        String Name = FileHelpers.readJsonFile("self","src/test/resources/testdata/Jira.json");

        response = request.delete(Name);
        System.out.println(response.statusCode());

    }


}
