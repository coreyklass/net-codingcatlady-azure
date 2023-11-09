package net.codingcatlady.util.http;

import net.codingcatlady.util.JavaCast;
import net.codingcatlady.util.URLHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;

public class SimpleHttpFormPost extends SimpleHttp {

    /**
     * Constructor
     * @param urlText
     */
    public SimpleHttpFormPost(String urlText) {
        super(urlText);
    }



    /**
     * Constructor
     * @param urlText
     * @param body
     */
    public SimpleHttpFormPost(String urlText, HashMap<Object, Object> body) {
        super(urlText, body);
    }





    /**
     * Returns the HTTP method
     * @return
     */
    @Override
    public String getMethod() {
        return "POST";
    }



    /**
     * Returns the content type
     * @return
     */
    @Override
    public String getContentType() {
        return "application/x-www-form-urlencoded";
    }


}
