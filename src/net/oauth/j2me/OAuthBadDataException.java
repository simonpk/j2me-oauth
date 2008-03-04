/*
 * OAuthBadDataException.java
 *
 * Created on February 15, 2008, 4:31 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.oauth.j2me;

/**
 *
 * @author Administrator
 */
public class OAuthBadDataException extends Exception {
    
    public OAuthBadDataException() {
        super();
    }

    public OAuthBadDataException(String message) {
        super(message);
    }

    public OAuthBadDataException(String message, Throwable cause) {
        super(message); //, cause);
    }
    
}
