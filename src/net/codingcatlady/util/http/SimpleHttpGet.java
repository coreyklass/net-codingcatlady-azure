package net.codingcatlady.util.http;

import net.codingcatlady.util.URLHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SimpleHttpGet {

    /**
     * Stores the URL text
     */
    public final String urlText;


    /**
     * Stores the auth header value
     */
    protected String _authorizationHeader = null;




    /**
     * Constructor
     * @param urlText
     */
    public SimpleHttpGet(String urlText) {
        this.urlText = urlText;
    }



    /**
     * Sets the authorization header
     * @param text
     */
    public void setAuthorizationHeader(String text) {
        this._authorizationHeader = text;
    }




    /**
     * Executes the HTTP post
     */
    public byte[] execute() throws IOException {
        // build the URL
        URL url = new URL(this.urlText);

        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

        // set the GET method
        urlConnection.setRequestMethod("GET");

        // set the auth header
        if (this._authorizationHeader != null) {
            urlConnection.setRequestProperty("Authorization", this._authorizationHeader);
        }

        // open a connection
        urlConnection.connect();

        // get the response stream
        InputStream responseStream = urlConnection.getInputStream();

        // convert to a string
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String resultText = s.hasNext() ? s.next() : "";

        // return bytes
        return resultText.getBytes(StandardCharsets.UTF_8);
    }

}
