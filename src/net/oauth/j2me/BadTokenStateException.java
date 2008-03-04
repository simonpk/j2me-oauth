/*
 * BadTokenStateException.java
 *
 * Created on February 15, 2008, 3:28 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.oauth.j2me;

/**
 *
 * @author Administrator
 */
public class BadTokenStateException extends Exception {
    
    /** Creates a new instance of BadTokenStateException */
    public BadTokenStateException() {
        super();
    }

    public BadTokenStateException(String message) {
        super(message);
    }

    public BadTokenStateException(String message, Throwable cause) {
        super(message); //, cause);
    }
}
