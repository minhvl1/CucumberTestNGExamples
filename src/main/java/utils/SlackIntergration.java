package utils;

import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.model.Message;
import constants.FrameworkConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SlackIntergration {
    private static String oAuthToken = PropertiesHelpers.getEnvironment("SLACK_O_AUTH_TOKEN");
    private static String slackChannel = PropertiesHelpers.getEnvironment("SLACK_CHANNEL");
    private static String reportLink = PropertiesHelpers.getEnvironment("REPORT_LINK");

    public static void sendMessageToSlack(String message) throws SlackApiException, IOException {

        if (FrameworkConstants.SEND_MSG_TO_SLACK.trim().equalsIgnoreCase(FrameworkConstants.YES))  {
            // Initialize an API Methods client with the given token
            Slack slack = Slack.getInstance();

            try {
                ChatPostMessageResponse response = slack.methods(oAuthToken).chatPostMessage(ChatPostMessageRequest.builder()
                        .channel(slackChannel)
                        .text(message)
                        .build());
                if (response.isOk()) {
                    Message postedMessage = response.getMessage();
                } else {
                    String errorCode = response.getError(); // e.g., "invalid_auth", "channel_not_found"
                    System.out.printf("Error slack: " + errorCode);
                }

            } catch (SlackApiException requestFailure) {
                // Slack API responded with unsuccessful status code (= not 20x)
            } catch (IOException connectivityIssue) {

            }
        }
    }
    public static void sendResultRunnerToSlack(int count_totalTCs, int count_passedTCs, int count_failedTCs, int count_skippedTCs)  {
        if (FrameworkConstants.SEND_MSG_TO_SLACK.trim().equalsIgnoreCase(FrameworkConstants.YES))  {
            // Initialize an API Methods client with the given token
            Slack slack = Slack.getInstance();

            String msg = "The Runner's result: "+"\ncount_totalTCs: " + count_totalTCs + "\ncount_passedTCs: "
                    + count_passedTCs + "\ncount_failedTCs: " + count_failedTCs + "\ncount_skippedTCs: " + count_skippedTCs
                    + "\nView report at link: <"+reportLink+">";

            try {
                ChatPostMessageResponse response = slack.methods(oAuthToken).chatPostMessage(ChatPostMessageRequest.builder()
                        .channel(slackChannel)
                        .text(msg)
                        .build());
                if (response.isOk()) {
                    Message postedMessage = response.getMessage();
                } else {
                    String errorCode = response.getError(); // e.g., "invalid_auth", "channel_not_found"
                    System.out.printf("Error slack: " + errorCode);
                }
            } catch (SlackApiException requestFailure) {
                // Slack API responded with unsuccessful status code (= not 20x)
            } catch (IOException connectivityIssue) {

            }
        }
    }
}
