package net.codingcatlady.util.http;

import net.codingcatlady.util.URLHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SimpleHttpGet extends SimpleHttp {



    /**
     * Constructor
     * @param urlText
     */
    public SimpleHttpGet(String urlText) {
        super(urlText);
    }


    /**
     * Returns the method
     * @return
     */
    @Override
    public String getMethod() {
        return "GET";
    }
}
