package Helpers;

import org.json.JSONObject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class is a wrapper that implements http communication with the server
 * No server calls should happen outside of this class
 */
public class HttpClient {
    /**
     * This function is responsible for HTTP Post methods
     * @param url: host url
     * @param token: auth token
     * @param body: content of the request as a JSONObject
     */
    public static String post(String url, String token, JSONObject body) {
        try {
            return sendPost(url, token, body.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * This function is responsible for HTTP Post methods
     * @param url: host url
     * @param token: auth token
     * @param body: content of the request as a String in Json format
     */
    public static String post(String url, String token, String body) {
        try {
            return sendPost(url, token, body);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * This function is responsible for HTTP Get methods
     * @param url: host url
     * @param token: auth token
     */
    public static String get(String url, String token) {
        try {
            return sendGet(url, token);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String sendGet(String getUrl, String token) throws IOException {
        URL url = new URL(getUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("authorization", "Bearer " + token);

        return read(con.getInputStream());
    }

    private static String sendPost(String postUrl, String token, String data) throws IOException {
        URL url = new URL(postUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        con.setRequestProperty("Accept", "application/json");
        if(token != null) {
            con.setRequestProperty("authorization", "Bearer " + token);
        }
        con.setDoOutput(true);
        con.setDoInput(true);

        sendData(con, data);

        return read(con.getInputStream());
    }

    private static void sendData(HttpURLConnection con, String data) throws IOException {
        DataOutputStream wr = null;
        try {
            wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(data);
            wr.flush();
            wr.close();
        } catch(IOException exception) {
            throw exception;
        } finally {
            closeQuietly(wr);
        }
    }

    private static String read(InputStream is) throws IOException {
        BufferedReader in = null;
        String inputLine;
        StringBuilder body;
        try {
            in = new BufferedReader(new InputStreamReader(is));

            body = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                body.append(inputLine);
            }
            in.close();

            return body.toString();
        } catch(IOException ioe) {
            throw ioe;
        } finally {
            closeQuietly(in);
        }
    }

    private static void closeQuietly(Closeable closeable) {
        try {
            if( closeable != null ) {
                closeable.close();
            }
        } catch(IOException ex) {

        }
    }
}
