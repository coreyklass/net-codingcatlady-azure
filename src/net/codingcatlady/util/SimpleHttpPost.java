package net.codingcatlady.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;

public class SimpleHttpPost {

    /**
     * Stores the URL text
     */
    public final String urlText;


    /**
     * Stores the body
     */
    private HashMap<Object, Object> _body;



    /**
     * Constructor
     * @param urlText
     */
    public SimpleHttpPost(String urlText) {
        this.urlText = urlText;
    }


    /**
     * Constructor
     * @param urlText
     * @param body
     */
    public SimpleHttpPost(String urlText, HashMap<Object, Object> body) {
        this(urlText);
        this._body = JavaCast.toHashMap(body);
    }



    /**
     * Returns the body
     * @return
     */
    public HashMap<Object, Object> body() {
        return JavaCast.toHashMap(this._body);
    }


    /**
     * Executes the HTTP post
     */
    public byte[] execute() throws IOException {
        // build the URL
        URL url = new URL(this.urlText);

        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

        // set the POST method
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);

        // pull the body
        String bodyText = URLHelper.urlEncodedMap(this.body());
        byte[] bodyBytes = bodyText.getBytes(StandardCharsets.UTF_8);

        // data length
        urlConnection.setFixedLengthStreamingMode(bodyBytes.length);

        // form content type
        urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

        // open a connection
        urlConnection.connect();

        // pull the output stream
        OutputStream postStream = urlConnection.getOutputStream();

        // write to the output stream
        postStream.write(bodyBytes);

        // get the response stream
        InputStream responseStream = urlConnection.getInputStream();

        // convert to a string
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String resultText = s.hasNext() ? s.next() : "";

        // return bytes
        return resultText.getBytes(StandardCharsets.UTF_8);
    }

}
