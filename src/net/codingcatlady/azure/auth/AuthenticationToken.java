package net.codingcatlady.azure.auth;

import net.codingcatlady.util.JavaCast;
import net.codingcatlady.util.http.SimpleHttpGet;

import java.util.Date;
import java.util.HashMap;

public class AuthenticationToken {


    /**
     * Stores the creation date
     */
    final public Date creationDate;


    /**
     * Stores the token type
     */
    final public String tokenType;


    /**
     * Stores when the token expires
     */
    final public Long expiresIn;


    /**
     * Stores the access token
     */
    final private String _accessToken;


    /**
     * Stores the expiry date
     */
    final public Date expiryDate;




    /**
     * Constructor
     * @param json
     */
    protected AuthenticationToken(HashMap<Object, Object> json) {
        this.creationDate = new Date();
        this.tokenType = (String)json.get("token_type");
        this._accessToken = (String)json.get("access_token");

        Integer expiresIn = JavaCast.toInteger(json.get("expires_in"));
        this.expiresIn = expiresIn.longValue();

        long expiresInMS = this.expiresIn * 1000;
        this.expiryDate = new Date(this.creationDate.getTime() + expiresInMS);
    }


    /**
     * Returns the token as a string
     * @return
     */
    @Override
    public String toString() {
        HashMap<String, Object> values = new HashMap<>();

        values.put("tokenType", this.tokenType);
        values.put("expiresIn", this.expiresIn);
        values.put("expiryDate", this.expiryDate);
        values.put("creationDate", this.creationDate);
        values.put("accessToken", this._accessToken.substring(0, 6));

        return values.toString();
    }



    /**
     * Writes the auth header
     * @param http
     */
    public void writeAuthHeader(SimpleHttpGet http) {
        http.setAuthorizationHeader(this.tokenType + " " + this._accessToken);
    }




}
