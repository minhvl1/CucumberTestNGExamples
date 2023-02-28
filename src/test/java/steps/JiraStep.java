package steps;

import common.RequestCapability;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.codec.binary.Base64;

import java.util.List;
import java.util.Map;
import java.util.Objects;


public class JiraStep implements RequestCapability {
    @Given("Authen jira")
    public void authenJira() {
        String baseUrlJira = "https://vlminh.atlassian.net";
        String jiraPath= "/rest/api/3/project/AB";

        String email = "minhvu1234567yahoo@gmail.com";
        String apiToken = "ATATT3xFfGF0-H9V5hhaPdYS-1JaZQ91duxYk2P5UaTW4weBviUsObKEE8dh-1g1hoJLzVyg_SLTaVgq0dEdbNDg07kugF1gjHdhVkNRx5ws2R46Y6ueIPb43YeVd_hIW2dmbQctwVYh36VR_34x_wlyziFymsvmnQBKH8OH9gFFSOsPRm-OuVo=E95E665E";
        String creDential = email+":"+apiToken;

        byte[] encodedCred = Base64.encodeBase64(creDential.getBytes());
        String encodedCreStr = new String(encodedCred);

        RequestSpecification request = RestAssured.given();
        request.baseUri(baseUrlJira);
        request.header(defaultHeader);
        request.header(RequestCapability.getAuthenticatedHeader(encodedCreStr));

        Response response = request.get(jiraPath);
//        response.prettyPrint();

        Map<String, List<Map<String,String>>> projectInfo = JsonPath.from(response.asString()).get();
        List<Map<String,String>> issueTypes = projectInfo.get("issueTypes");
        for (Map<String, String> issueType: issueTypes) {
            System.out.println(issueType.get("id"));
            System.out.println(issueType.get("name"));
            System.out.println("=====================");
        }
    }
}
