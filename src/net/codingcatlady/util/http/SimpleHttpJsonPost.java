package net.codingcatlady.util.http;

import java.util.HashMap;

public class SimpleHttpJsonPost extends SimpleHttp {

    /**
     * Constructor
     * @param urlText
     */
    public SimpleHttpJsonPost(String urlText) {
        super(urlText);
    }



    /**
     * Constructor
     * @param urlText
     * @param body
     */
    public SimpleHttpJsonPost(String urlText, HashMap<Object, Object> body) {
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
        return "application/json";
    }

    
}
