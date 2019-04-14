package Helpers;

/**
 * This class contains the current users main data
 */
public class User {
    private static String accessToken;
    public static String getAccessToken(){
        return accessToken;
    }
    public static void setAccessToken(String token){
        accessToken = token;
    }
}
