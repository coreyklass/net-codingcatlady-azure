package net.codingcatlady.azure.auth;

import net.codingcatlady.util.json.JsonCreatorUnimplementedException;
import net.codingcatlady.util.json.JsonParser;
import net.codingcatlady.util.json.JsonParserUnimplementedException;
import net.codingcatlady.util.JavaCast;
import net.codingcatlady.util.http.SimpleHttpFormPost;
import net.codingcatlady.util.URLHelper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationProvider {

    /**
     * Stores the auth URL
     */
    static final String authUrl = "https://login.microsoftonline.com/{tenantID}/oauth2/token";


    /**
     * Stores the auth scope
     */
    static final String defaultScope = "https://graph.microsoft.com/.default";


    /**
     * Stores the grant type
     */
    static final String grantType = "client_credentials";


    /**
     * Stores the TenantID
     */
    final public String tenantID;


    /**
     * Stores the ClientID
     */
    final public String clientID;


    /**
     * Stores the Client Secret
     */
    final private String _clientSecret;




    /**
     * Constructor
     * @param clientID
     */
    public AuthenticationProvider(String tenantID, String clientID, String clientSecret) {
        this.tenantID = tenantID;
        this.clientID = clientID;
        this._clientSecret = clientSecret;
    }






    /**
     * Builds and returns the auth URL
     * @return
     */
    private String _buildAuthUrl() {
        String tenantID;

        try {
            // URL-encode the value
            tenantID = URLHelper.urlEncode(this.tenantID);

            // build a URL text
            String urlText = AuthenticationProvider.authUrl.replace("{tenantID}", tenantID);

            // return the URL
            return urlText;

        } catch (UnsupportedEncodingException exception) {
            // if there was an error encoding the text, return NULL
            String errorText = String.format("Error encoding text: %s", exception.getMessage());
            System.out.println(errorText);
            return null;
        }
    }


    /**
     * Builds and returns the auth body
     * @return
     */
    private HashMap<Object, Object> _buildAuthBody(String resource) {
        HashMap<Object, Object> params = new HashMap<>();

        params.put("client_id", this.clientID);
        params.put("client_secret", this._clientSecret);
        params.put("grant_type", AuthenticationProvider.grantType);
        params.put("scope", AuthenticationProvider.defaultScope);

        if (resource != null) {
            params.put("resource", resource);
        }

        return params;
    }


    /**
     * Requests an auth token
     * @return
     */
    public AuthenticationToken requestToken() {
        return this.requestToken(null);
    }



    /**
     * Requests an auth token
     * @return
     */
    public AuthenticationToken requestToken(String resource) {
        // pull URL and body
        String urlText = this._buildAuthUrl();
        HashMap<Object, Object> body = this._buildAuthBody(resource);

        // if either is NULL, return NULL
        if ((urlText == null) || (body == null)) {
            return null;
        }

        // perform a simple post
        SimpleHttpFormPost post = new SimpleHttpFormPost(urlText, body);

        byte[] responseBytes = null;

        try {
            responseBytes = post.execute();

        } catch (IOException | JsonCreatorUnimplementedException e) {
            throw new RuntimeException(e);
        }

        // convert response bytes to text
        String jsonText = new String(responseBytes, StandardCharsets.UTF_8);


        try {
            // parse responded JSON into a map
            Object json = JsonParser.getParser().parse(jsonText);

            if (json instanceof Map) {
                // convert response to auth token
                HashMap<Object, Object> jsonMap = JavaCast.toHashMap(json);
                AuthenticationToken token = new AuthenticationToken(jsonMap);

                return token;
            }

        } catch (JsonParserUnimplementedException e) {
            throw new RuntimeException(e);
        }

        return null;
    }





}
