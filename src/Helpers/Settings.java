package Helpers;

public class Settings {
    private static String azureBaseUrl = "https://taxeeappservice.azurewebsites.net";
    //private static String azureBaseUrl = "https://localhost:5001";
    public static String getAzureBaseUrl() {
        return azureBaseUrl;
    };

    private static String dateFormat = "MM/dd/yyyy HH:mm:ss";
    public static String getDsteFormat() {
        return dateFormat;
    };
}
