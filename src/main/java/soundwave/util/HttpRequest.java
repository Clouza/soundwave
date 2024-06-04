package soundwave.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {
    String url, method, contentType, message;

    public HttpRequest() {}

    public HttpRequest setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public HttpRequest setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public HttpRequest setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public HttpRequest setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getMessage() {
        return message;
    }

    /**
     * The send method allows the Logger class to send a report
     * to discord via webhook. But, you can actually
     * send anything with this class. I guess.
     */
    public void send() {
        try {
            URL url = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(this.method);
            connection.setRequestProperty("Content-type", this.contentType);
            connection.setDoOutput(true);
            String jsonPayload = "{\"content\": \"" + this.message + "\"}";

            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.writeBytes(jsonPayload);
                wr.flush();
            }

            int responseCode = connection.getResponseCode();
            System.out.println("HttpRequest Response code: " + responseCode);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
