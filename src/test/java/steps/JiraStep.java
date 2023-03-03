package steps;


import models.IssueJira;
import models.RequestCapability;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.FileHelpers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class JiraStep implements RequestCapability {
    Response response;
    String baseUrlJira = "https://vlminh.atlassian.net";
    String jiraPath= "/rest/api/3/project/AB";
    String issuePath="/rest/api/3/issue";
    String email = "minhvu1234567yahoo@gmail.com";
    String apiToken = "ATATT3xFfGF0XnDA5e8iYNJFwaD7cCyEuav4Baax36Uyz9iRED5zqCWPG2f5ir-fe-i6wZfOOlRCbOqCp9C5Ypq1eY0pqSwSCdyVYzrCugTsdOzkA5D_WLWAfdHnMheyMCutUFp7uSUt__Si7BBtt_dCxO1PXvmoMtolaygqoJ4Ac3aBy_wl-As=0E5596A6";
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
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("self", createIssueResponse.get("self"));
        FileWriter file = new FileWriter("src/test/resources/testdata/Jira.json");
        file.write(jsonObject.toJSONString());
        file.close();

    }

    @Given("Delete issue")
    public void deleteIssue() throws IOException, ParseException {
        byte[] encodedCred = Base64.encodeBase64(creDential.getBytes());
        String encodedCreStr = new String(encodedCred);


        Object o = new JSONParser().parse(new FileReader("src/test/resources/testdata/Jira.json"));
        JSONObject j = (JSONObject) o;
        String Name = (String) j.get("self");
        System.out.println(Name);

        //create request
        RequestSpecification request = RestAssured.given();
        request.baseUri(baseUrlJira);
        request.header(defaultHeader);
        request.header(RequestCapability.getAuthenticatedHeader(encodedCreStr));

        response = request.delete(Name);
        System.out.println(response.statusCode());



    }
}
