package Helpers;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * This class contains the application settings
 */
public class Settings {
    private static String azureBaseUrl = "https://taxeeappservice.azurewebsites.net";
    //private static String azureBaseUrl = "https://localhost:5001";

    /**
     * Returns the backend api url
     */
    public static String getAzureBaseUrl() {
        return azureBaseUrl;
    };

    private static String dateFormat = "MM/dd/yyyy HH:mm:ss";

    /**
     * Returns the common date format used in the application
     */
    public static String getDsteFormat() {
        return dateFormat;
    };

    /**
     * Returns users auth token stored in the taxee.properties file
     * if it exists
     */
    public static String getAuthToken () {
        try (InputStream input = new FileInputStream("taxee.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            return prop.getProperty("token");
        } catch (IOException ex) {
            System.out.println("properties doesn't exist yet");
            return null;
        }
    }

    /**
     * Saves the users auth token to the taxee.properties file
     * It creates the file if it not yet exists
     * @param token - jwt token
     */
    public static void setAuthProperty(String token) {
        try (OutputStream output = new FileOutputStream("taxee.properties")) {
            Properties authProperty = new Properties();

            authProperty.setProperty("token", token);

            authProperty.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    /**
     * Deletes the taxee.properties file
     */
    public static void clearAuthProperty() {
        try
        {
            Files.deleteIfExists(Paths.get("taxee.properties"));
        }
        catch(IOException e)
        {
            System.out.println("Auth property file does not exist");
        }
    }
}
