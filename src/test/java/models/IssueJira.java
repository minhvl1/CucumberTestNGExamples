package models;


import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class IssueJira {

    public static String CreateIssueJiraBody(){
        JsonObject issueType = new JsonObject();
        issueType.addProperty("id","10001");

        JsonObject project = new JsonObject();
        project.addProperty("id","10000");

        JsonObject fields = new JsonObject();
        fields.addProperty("summary","test from selenium");
        fields.add("issuetype",issueType);
        fields.add("project",project);

        JsonObject createIssue = new JsonObject();
        createIssue.add("fields",fields);
        return new Gson().toJson(createIssue);
    }
}
