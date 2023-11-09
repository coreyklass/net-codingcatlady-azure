package net.codingcatlady.util.http;

import net.codingcatlady.util.JavaCast;
import net.codingcatlady.util.URLHelper;
import net.codingcatlady.util.json.JsonCreator;
import net.codingcatlady.util.json.JsonCreatorUnimplementedException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;

public class SimpleHttp {


    /**
     * Stores the URL text
     */
    public final String urlText;



    /**
     * Stores the auth header value
     */
    private String _authHeader = null;



    /**
     * Stores the body
     */
    private HashMap<Object, Object> _body;





    /**
     * Constructor
     * @param urlText
     */
    protected SimpleHttp(String urlText) {
        this.urlText = urlText;
    }



    /**
     * Constructor
     * @param urlText
     * @param body
     */
    protected SimpleHttp(String urlText, HashMap<Object, Object> body) {
        this(urlText);
        this._body = JavaCast.toHashMap(body);
    }




    /**
     * Sets the authorization header
     * @param text
     */
    public void setAuthHeader(String text) {
        this._authHeader = text;
    }




    /**
     * Returns the HTTP method
     * @return
     */
    public String getMethod() {
        return null;
    }



    /**
     * Returns the content type
     * @return
     */
    public String getContentType() {
        return null;
    }






    /**
     * Executes the HTTP method
     */
    public byte[] execute() throws IOException, JsonCreatorUnimplementedException {
        // build the URL
        URL url = new URL(this.urlText);

        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

        // set the method
        urlConnection.setRequestMethod(this.getMethod());

        // does this have a body?
        boolean hasBody = (this.getMethod().equals("POST") || this.getMethod().equals("PUT"));

        // declare a variable for body bytes
        String bodyText = null;
        byte[] bodyBytes = null;

        // if the method has a body
        if (hasBody) {
            urlConnection.setDoOutput(true);

            switch (this.getContentType()) {
                case "application/x-www-form-urlencoded":
                    bodyText = URLHelper.urlEncodedMap(this._body);
                    bodyBytes = bodyText.getBytes(StandardCharsets.UTF_8);
                    break;

                case "application/json":
                    bodyText = JsonCreator.getCreator().create(this._body);
                    bodyBytes = bodyText.getBytes(StandardCharsets.UTF_8);
                    break;

                default:
                    bodyBytes = new byte[0];
                    break;
            }

            // data length
            urlConnection.setFixedLengthStreamingMode(bodyBytes.length);

            // form content type
            urlConnection.setRequestProperty("Content-Type", this.getContentType());
        }

        // set the auth header
        if (this._authHeader != null) {
            urlConnection.setRequestProperty("Authorization", this._authHeader);
        }

        // open a connection
        urlConnection.connect();

        if (hasBody) {
            // pull the output stream
            OutputStream outputStream = urlConnection.getOutputStream();

            // write to the output stream
            outputStream.write(bodyBytes);
        }

        // get the response stream
        InputStream responseStream = urlConnection.getInputStream();

        // convert to a string
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        int readCount;
        byte[] data = new byte[10];

        while ((readCount = responseStream.read(data, 0, data.length)) != -1) {
            bytes.write(data, 0, readCount);
        }

        bytes.flush();

        return bytes.toByteArray();
    }



}
