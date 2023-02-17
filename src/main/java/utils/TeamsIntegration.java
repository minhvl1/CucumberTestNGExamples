package utils;

import constants.FrameworkConstants;

import java.io.*;
import java.net.*;


/*
Step:
1. Creat a channel in a group
2. Add Webhook app for above channel and get the URL
3. Use HTTP request to send message to channel

 */
public class TeamsIntegration {
    private static final String BASE_URL = PropertiesHelpers.getEnvironment("TEAMS_WEBHOOK_URL");
    private static final String FLAG_SEND_TO_TEAMS = PropertiesHelpers.getEnvironment("SEND_TO_TEAMS");
    public static void sendMessageToTeams(int count_totalTCs, int count_passedTCs, int count_failedTCs, int count_skippedTCs) {
        String msg = "The Runner's result: "+"<br>count_totalTCs: " + count_totalTCs + "<br>count_passedTCs: "
                + count_passedTCs + "<br>count_failedTCs: " + count_failedTCs + "<br>count_skippedTCs: " + count_skippedTCs
                + "<br>View report at link: <https://www.google.com/>";

        try {
            if (FLAG_SEND_TO_TEAMS.trim().equalsIgnoreCase(FrameworkConstants.YES)) {
                sendPOST(msg);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void sendPOST(String message) throws IOException {
        URL url = new URL (BASE_URL);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        String jsonInputString =  "{\"text\":\"" +message + "\"}";
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
    }

}
