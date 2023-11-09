package net.codingcatlady.azure.keyVault;

import net.codingcatlady.azure.auth.AuthenticationToken;
import net.codingcatlady.util.http.SimpleHttpJsonPost;
import net.codingcatlady.util.http.SimpleHttpJsonPut;
import net.codingcatlady.util.json.JsonCreatorUnimplementedException;
import net.codingcatlady.util.json.JsonParser;
import net.codingcatlady.util.json.JsonParserUnimplementedException;
import net.codingcatlady.util.JavaCast;
import net.codingcatlady.util.URLHelper;
import net.codingcatlady.util.http.SimpleHttpGet;
import net.codingcatlady.util.http.SimpleHttpFormPut;

import java.io.IOException;
import java.util.HashMap;

public class KeyVault {

    /**
     * Stores the key vault URL
     */
    final public String keyVaultUrl;


    /**
     * Stores the auth token
     */
    final public AuthenticationToken authToken;


    /**
     * Constructor
     * @param keyVaultUrl
     * @param authToken
     */
    public KeyVault(String keyVaultUrl, AuthenticationToken authToken) {
        this.keyVaultUrl = keyVaultUrl;
        this.authToken = authToken;
    }



    /**
     * Returns the secret value
     * @param name
     * @return
     */
    public String getSecret(String name) throws IOException, JsonParserUnimplementedException, JsonCreatorUnimplementedException {
        // URL-encode the secret name
        String urlEncodedName = URLHelper.urlEncode(name);

        // build the get-secret URL
        String secretUrlText = this.keyVaultUrl + "/secrets/" + urlEncodedName + "?api-version=7.4";

        // build the GET object
        SimpleHttpGet get = new SimpleHttpGet(secretUrlText);
        this.authToken.writeAuthHeader(get);

        // run and return results
        String jsonText = new String(get.execute());
        Object json = JsonParser.getParser().parse(jsonText);
        HashMap<Object, Object> jsonMap = JavaCast.toHashMap(json);

        if (jsonMap != null) {
            return JavaCast.toString(jsonMap.get("value"));
        }

        return null;
    }


    /**
     * Stores a secret in a key vault
     * @param name
     * @param value
     * @return
     */
    public Boolean putSecret(String name, String value) throws IOException, JsonCreatorUnimplementedException {
        // URL-encode the secret name
        String urlEncodedName = URLHelper.urlEncode(name);

        // build the put-secret URL
        String secretUrlText = this.keyVaultUrl + "/secrets/" + urlEncodedName + "?api-version=7.4";

        // build the values Map
        HashMap<Object, Object> body = new HashMap<>();
        body.put("value", value);

        // build the PUT object
        SimpleHttpJsonPut put = new SimpleHttpJsonPut(secretUrlText, body);
        this.authToken.writeAuthHeader(put);

        // run and return results
        put.execute();

        return true;
    }



}
