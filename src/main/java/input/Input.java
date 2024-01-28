package input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Input {

    private final HttpURLConnection connection;
    private final URL targetUrl;
    private int responseCode;

    public Input(String targetURL) throws IOException {
        this.targetUrl = new URL(targetURL);
        this.connection = (HttpURLConnection) targetUrl.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Cookie", "session=" + System.getenv("AOC_SESSION_COOKIE"));
        connection.setRequestProperty("Accept", "text/plain");
        responseCode = connection.getResponseCode();
    }

    public String getResponse() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String lineInput;
        StringBuilder response = new StringBuilder();

        while ((lineInput = in.readLine()) != null) {
            response.append(lineInput);
            response.append("\n");
        }
        in.close();
        return response.toString();
    }
}
