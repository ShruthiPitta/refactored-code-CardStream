package CardstreamExample;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.sql.Connection;
import java.util.Iterator;
import java.util.Set;

public class HttpService {
    private final static String GATEWAY_URL = "https://gateway.cardstream.com/direct/";
    private static HttpURLConnection getConnection() throws IOException {
        // Create the request
        URL reqUrl = new URL(GATEWAY_URL);
        HttpURLConnection reqConn = (HttpURLConnection) reqUrl.openConnection();
        reqConn.setDoInput(true);
        reqConn.setDoOutput(true);
        reqConn.setUseCaches(false);
        reqConn.setRequestMethod("POST");
        reqConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        reqConn.setRequestProperty("Connection", "Close");
        reqConn.setRequestProperty("charset", "utf-8");

        return reqConn;
    }

    public static  String postRequest(String request)  {
        String response = null ;
        try {

        HttpURLConnection connection = getConnection();
        connection.setRequestProperty("Content-Length", "" + Integer.toString(request.getBytes().length));
            try (DataOutputStream reqStream = new DataOutputStream(connection.getOutputStream())) {
                reqStream.writeBytes(request);
                reqStream.flush();
            }
             response = buildResponse(connection);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return response;

    }

    public static String buildResponse(HttpURLConnection connection) throws IOException {
        String response =  new String();
        try (BufferedReader resBuf = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line = resBuf.readLine();
            while (line != null) {
                response += line;
                line = resBuf.readLine();
            }
        }

        return response;
    }

}
