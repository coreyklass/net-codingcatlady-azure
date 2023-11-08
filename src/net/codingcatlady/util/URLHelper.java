package net.codingcatlady.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class URLHelper {


    /**
     * URL-Encodes the specified text
     * @param text
     * @return
     */
    public static String urlEncode(String text) throws UnsupportedEncodingException {
        // attempt to URL-encode the text
        return URLEncoder.encode(text, StandardCharsets.UTF_8.toString());
    }


    /**
     * URL-encodes the Map
     * @param map
     * @return
     */
    public static String urlEncodedMap(Map<?, ?> map) throws UnsupportedEncodingException {
        StringBuilder text = new StringBuilder();

        for (Object ixKey : map.keySet()) {
            String ixEncodedKey = urlEncode(ixKey.toString());
            String ixEncodedValue = urlEncode(map.get(ixKey.toString()).toString());
            String ixPair = String.format("%s=%s", ixEncodedKey, ixEncodedValue);

            if (!text.toString().isEmpty()) {
                text.append("&");
            }

            text.append(ixPair);
        }

        return text.toString();
    }




}
