package net.codingcatlady.util.http;

import java.util.HashMap;

public class SimpleHttpJsonPut extends SimpleHttp {

    /**
     * Constructor
     * @param urlText
     */
    public SimpleHttpJsonPut(String urlText) {
        super(urlText);
    }



    /**
     * Constructor
     * @param urlText
     * @param body
     */
    public SimpleHttpJsonPut(String urlText, HashMap<Object, Object> body) {
        super(urlText, body);
    }





    /**
     * Returns the HTTP method
     * @return
     */
    @Override
    public String getMethod() {
        return "PUT";
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
