package net.codingcatlady.azure.keyVault;

import net.codingcatlady.azure.auth.AuthenticationToken;
import net.codingcatlady.azure.util.AzureJsonParser;
import net.codingcatlady.azure.util.AzureJsonParserUnimplementedException;
import net.codingcatlady.util.JavaCast;
import net.codingcatlady.util.URLHelper;
import net.codingcatlady.util.http.SimpleHttpGet;

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
    public String getSecret(String name) throws IOException, AzureJsonParserUnimplementedException {
        // URL-encode the secret name
        String urlEncodedName = URLHelper.urlEncode(name);

        // build the get-secret URL
        String secretUrlText = this.keyVaultUrl + "/secrets/" + urlEncodedName + "?api-version=7.4";

        // build the GET object
        SimpleHttpGet get = new SimpleHttpGet(secretUrlText);
        this.authToken.writeAuthHeader(get);

        // run and return results
        String jsonText = new String(get.execute());
        Object json = AzureJsonParser.getParser().parse(jsonText);
        HashMap<Object, Object> jsonMap = JavaCast.toHashMap(json);

        if (jsonMap != null) {
            return JavaCast.toString(jsonMap.get("value"));
        }

        return null;
    }


}
