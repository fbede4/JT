package Helpers;

public class Settings {
    private static String azureBaseUrl = "http://taxeeappservice.azurewebsites.net";
    public static String getAzureBaseUrl() {
        return azureBaseUrl;
    };

    private static String dateFormat = "MM/dd/yyyy HH:mm:ss";
    public static String getDsteFormat() {
        return dateFormat;
    };
}
