package models;

import org.json.simple.JSONObject;

public class IssueJira {

    public static JSONObject CreateIssueJiraBody(){
        JSONObject issueType = new JSONObject();
        issueType.put("id","10001");

        JSONObject project = new JSONObject();
        project.put("id","10000");

        JSONObject fields = new JSONObject();
        fields.put("summary","test from selenium");
        fields.put("issuetype",issueType);
        fields.put("project",project);

        JSONObject createIssue = new JSONObject();
        createIssue.put("fields",fields);
        return createIssue;
    }
}
