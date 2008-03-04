/*
 * FireEagleConsumer.java
 *
 * Created on February 15, 2008, 3:01 PM
 *
 */

package net.yahoo.fireeagle.j2me;

import java.io.IOException;
import java.util.Hashtable;
import net.oauth.j2me.BadTokenStateException;
import net.oauth.j2me.Consumer;
import net.oauth.j2me.OAuthServiceProviderException;
import net.oauth.j2me.token.AccessToken;
import net.oauth.j2me.token.RequestToken;
/**
 *
 * @author Administrator
 */
public class FireEagleConsumer {
    public static final String OAUTH_HOST="https://fireeagle.yahooapis.com";
    public static final String REQUEST_TOKEN_URL="/oauth/request_token";
    public static final String ACCESS_TOKEN_URL="/oauth/access_token";
    public static final String MOBILE_AUTH_URL="http://fireeagle.yahoo.net/oauth/mobile_auth/31"; //31
    public static final String UPDATE_API_URL="/api/0.1/update";
    public static final String QUERY_API_URL="/api/0.1/user";
    
    private Consumer oauthConsumer;
    private RequestToken requestToken;
    private AccessToken accessToken;
    
    /** Creates a new instance of FireEagleConsumer */
    public FireEagleConsumer(String key, String secret) {
        oauthConsumer = new Consumer(key, secret);
        oauthConsumer.setSignatureMethod("HMAC-SHA1");
    }
    
    public RequestToken fetchNewRequestToken() throws OAuthServiceProviderException {
        requestToken = oauthConsumer.getRequestToken(OAUTH_HOST+REQUEST_TOKEN_URL);
        return requestToken;
    }
    
    public AccessToken fetchNewAccessToken() throws OAuthServiceProviderException, BadTokenStateException {
        if (requestToken==null) {
            throw new BadTokenStateException("No request token set");
        }
        accessToken = oauthConsumer.getAccessToken(OAUTH_HOST+ACCESS_TOKEN_URL, requestToken);
        requestToken = null; // it's no good after being used
        return accessToken;
    }
    
    public String updateLocation(Hashtable queryParams) throws OAuthServiceProviderException, BadTokenStateException, IOException {
        if (accessToken==null) {
            throw new BadTokenStateException("No access token set");
        }
        return oauthConsumer.accessProtectedResource(OAUTH_HOST+UPDATE_API_URL, accessToken, queryParams, "POST");
    }
    
    public String queryUserLocation() throws OAuthServiceProviderException, BadTokenStateException, IOException {
        if (accessToken==null) {
            throw new BadTokenStateException("No access token set");
        }
        return oauthConsumer.accessProtectedResource(OAUTH_HOST+QUERY_API_URL, accessToken, null);
    }
    
    // accessors and stuff
    public RequestToken getRequestToken() {
        return requestToken;
    }
    
    public void setAccessToken(AccessToken accessToken) {
        this.accessToken=accessToken;
    }
    
    // no real need to get Access Token or set Request Token (I think)
    
    public String naiveParseErrorResponse(String someXML) {
        int s=someXML.indexOf("msg=");
        if (s<0) {
            return "unknown";
        }
        s=s+5; // move to end of msg=" tag
        int e=someXML.indexOf("\"", s);
        if (e<0) {
            return "unknown";
        }
        return someXML.substring(s, e);
    }
    
    public String naiveParseQueryResponse(String someXML) {
        int s=someXML.indexOf("<name>");
        if (s<0) {
            return "unknown";
        }
        s=s+6; // move to end of <name> tag
        int e=someXML.indexOf("</name>", s);
        if (e<0) {
            return "unknown";
        }
        return someXML.substring(s, e);
    }
    
}
