package utils;

import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.model.Message;

import java.io.IOException;

public class SlackIntergration {
    private static String webHookUrl = "https://hooks.slack.com/services/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";
    private static String oAuthToken = "xoxb-4814438859152-4803415643169-AsxhDN3ddmEbQa020wPpyll6";
    private static String slackChannel = "#myproject";


    public static void sendMessageToSlack(String message) throws SlackApiException, IOException {


    // Initialize an API Methods client with the given token
        Slack slack = Slack.getInstance();
//        MethodsClient methods;
//        methods = slack.methods(oAuthToken);

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
    public static void sendResultRunnerToSlack(int count_totalTCs, int count_passedTCs, int count_failedTCs, int count_skippedTCs)  {


        // Initialize an API Methods client with the given token
        Slack slack = Slack.getInstance();
//        MethodsClient methods;
//        methods = slack.methods(oAuthToken);
        String msg = "The Runner's result: "+"\ncount_totalTCs: " + count_totalTCs + "\ncount_passedTCs: "
                + count_passedTCs + "\ncount_failedTCs: " + count_failedTCs + "\ncount_skippedTCs: " + count_skippedTCs;

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
