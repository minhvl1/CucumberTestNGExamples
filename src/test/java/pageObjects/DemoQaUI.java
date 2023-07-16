package pageObjects;

public class DemoQaUI {
    public static String getElementByLabel(String label) {
        return "//label[@for=" + "'" + label + "Radio']";
    }

    public static String getResultText = "//p[@class='mt-3']";
    public static String getYesButton = "//label[@for='yesRadio']";
}
