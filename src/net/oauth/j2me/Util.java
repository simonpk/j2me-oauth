/*
 * Util.java
 *
 * Created on November 20, 2007, 2:31 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.oauth.j2me;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.io.HttpsConnection;

/**
 *
 * @author Administrator
 */
public class Util {
    
    /** Creates a new instance of Util */
    public Util() {
    }
    
    // if the same key occurs in h1 and h2, use the value from h1
    public static final Hashtable hashtableMerge(Hashtable h1, Hashtable h2) {
        System.out.println("in hastableMerge");
        Hashtable h=new Hashtable();
        Enumeration keys=h1.keys();
        while (keys.hasMoreElements()) {
            Object k=keys.nextElement();
            if (h1.get(k)!=null) {
                h.put(k, h1.get(k));
            }
        }
        keys=h2.keys();
        while (keys.hasMoreElements()) {
            Object k=keys.nextElement();
            if (!h.containsKey(k) && h2.get(k)!=null) {
                h.put(k, h2.get(k));
            }
        }
        return h;
    }
    
    // sorts String values
    public static final Enumeration sort(Enumeration e){
        Vector v=new Vector();
        while (e.hasMoreElements()) {
            String s=(String)e.nextElement();
            int i=0;
            for (i=0; i<v.size(); i++) {
                int c=s.compareTo((String)v.elementAt(i));
                if (c<0) { // s should go before i
                    v.insertElementAt(s, i);
                    break;
                } else if (c==0) { // s already there
                    break;
                }
            }
            if (i>=v.size()) { // add s at end
                v.addElement(s);
            }
        }
        return v.elements();
    }
    
    
    
    /** Split string into multiple strings
     * @param original      Original string
     * @param separator     Separator string in original string
     * @return              Splitted string array
     */
    // TODO -- add in a max split param
    public static final String[] split(String original, String separator) {
        Vector nodes = new Vector();
        
        // Parse nodes into vector
        int index = original.indexOf(separator);
        while(index>=0) {
            nodes.addElement( original.substring(0, index) );
            original = original.substring(index+separator.length());
            index = original.indexOf(separator);
        }
        // Get the last node
        nodes.addElement( original );
        
        // Create splitted string array
        String[] result = new String[ nodes.size() ];
        if( nodes.size()>0 ) {
            for(int loop=0; loop<nodes.size(); loop++)
                result[loop] = (String)nodes.elementAt(loop);
        }
        return result;
    }
    
    // this is an OAuth-friendly url encode -- should work fine for ordniary encoding also
    public static final String urlEncode(String s) {
        if (s == null)
            return s;
        StringBuffer sb = new StringBuffer(s.length() * 3);
        try {
            char c;
            for (int i = 0; i < s.length(); i++) {
                c = s.charAt(i);
                if (c == '&') {
                    //sb.append("&amp;"); // don't do this
                    sb.append("%26");
                    //} else if (c == ' ') {
                    //    sb.append('+'); // maybe don't do this either
                } else if (
                        // safe characters
                        c == '-'
                        || c == '_'
                        || c == '.'
                        || c == '!'
                        || c == '~'
                        || c == '*'
                        || c == '\''
                        || c == '('
                        || c == ')'
                        || (c >= 'A' && c <= 'Z')
                        || (c >= 'a' && c <= 'z') ) {
                    sb.append(c);
                } else {
                    sb.append('%');
                    if (c > 15) { // is it a non-control char, ie. >x0F so 2 chars
                        sb.append(Integer.toHexString((int)c).toUpperCase()); // just add % and the string
                    } else {
                        sb.append("0" + Integer.toHexString((int)c).toUpperCase());
                        // otherwise need to add a leading 0
                    }
                }
            }
            
        } catch (Exception ex) {
            return (null);
        }
        return (sb.toString());
    }
    
    public static final String postViaHttpsConnection(String fullUrl) throws IOException, OAuthServiceProviderException {
        String[] urlPieces=split(fullUrl, "?");
        HttpsConnection c = null;
        DataInputStream dis = null;
        OutputStream os = null;
        int rc;
        String respBody = new String(""); // return empty string on bad things
        // TODO -- better way to handle unexpected responses
        try {
            System.out.println("UTIL -- posting to "+urlPieces[0]);
            c = (HttpsConnection)Connector.open(urlPieces[0], Connector.READ_WRITE); // hack for emulator?
            
            // Set the request method and headers
            c.setRequestMethod(HttpConnection.POST);
            c.setRequestProperty("User-Agent", "Profile/MIDP-2.0 Configuration/CLDC-1.0");
            c.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            c.setRequestProperty("Content-Length", ""+urlPieces[1].length());
            // TODO -- add'l headers for t-mobile?
            
            // Getting the output stream may flush the headers
            os = c.openOutputStream();
            System.out.println("UTIL -- writing POST data: "+urlPieces[1]);
            os.write(urlPieces[1].getBytes());
            os.flush();           // Optional, getResponseCode will flush
            
            // Getting the response code will open the connection,
            // send the request, and read the HTTP response headers.
            // The headers are stored until requested.
            rc = c.getResponseCode();
            
            int len = c.getHeaderFieldInt("Content-Length", 0);
            //int len = (int)c.getLength();
            System.out.println("content-length="+len);
            dis = c.openDataInputStream();
            
            byte[] data = new byte[len];
            if (len == 0) {
                System.out.println("UTIL -- no length, reading individual characters...");
                ByteArrayOutputStream tmp = new ByteArrayOutputStream();
                int ch;
                while( ( ch = dis.read() ) != -1 ) {
                    tmp.write( ch );
                }
                data = tmp.toByteArray();
            } else {
                System.out.println("UTIL -- got a length, reading...");
                dis.readFully(data);
            }
            respBody=new String(data);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Not an HTTP URL");
        } finally {
            if (dis != null)
                dis.close();
            if (os != null)
                os.close();
            if (c != null)
                c.close();
        }
        if (rc != HttpConnection.HTTP_OK) {
            throw new OAuthServiceProviderException("HTTP response code: " + rc, rc, respBody);
        }
        return respBody;
    }
    
    public static final String getViaHttpsConnection(String url) throws IOException, OAuthServiceProviderException {
        HttpsConnection c = null;
        DataInputStream dis = null;
        OutputStream os = null;
        int rc;
        String respBody = new String(""); // return empty string on bad things
        // TODO -- better way to handle unexpected responses
        
        try {
            System.out.println("UTIL -- opening connection");
            c= (HttpsConnection) Connector.open(url, Connector.READ);
            c.setRequestMethod(HttpConnection.GET);
            c.setRequestProperty("User-Agent", "Profile/MIDP-2.0 Configuration/CLDC-1.0");
            c.setRequestProperty("Cache-Control", "no-store");
            c.setRequestProperty("Connection", "close"); // not sure this is a good idea, but HTTP/1.0 might be less error-prone, some clients have trouble with chunked responses
            System.out.println("UTIL -- connection open");
            
            // Getting the response code will open the connection,
            // send the request, and read the HTTP response headers.
            // The headers are stored until requested.
            rc = c.getResponseCode();
            System.out.println("UTIL -- got response code" + rc);
            
            // Get the length and process the data
            int len = c.getHeaderFieldInt("Content-Length", 0);

            System.out.println("content-length="+len);
            dis = c.openDataInputStream();
            
            byte[] data = null; 
            if (len == -1L) {
                System.out.println("UTIL -- no length, reading individual characters...");
                ByteArrayOutputStream tmp = new ByteArrayOutputStream();
                int ch;
                while( ( ch = dis.read() ) != -1 ) {
                    tmp.write( ch );
                }
                data = tmp.toByteArray();
            } else {
                System.out.println("UTIL -- got a length, reading...");
                data = new byte[len];
                dis.read(data);
            }
            respBody=new String(data);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Not an HTTP URL");
        } finally {
            if (dis != null)
                dis.close();
            if (c != null)
                c.close();
        }
        if (rc != HttpConnection.HTTP_OK) {
            throw new OAuthServiceProviderException("HTTP response code: " + rc, rc, respBody);
        }
        return respBody;
    }
    
    private static char[]  map1  = new char[64];
    static {
        int  i  = 0;
        for ( char c = 'A'; c <= 'Z'; c++ ) {
            map1[i++] = c;
        }
        for ( char c = 'a'; c <= 'z'; c++ ) {
            map1[i++] = c;
        }
        for ( char c = '0'; c <= '9'; c++ ) {
            map1[i++] = c;
        }
        map1[i++] = '+';
        map1[i++] = '/';
    }
    
    public static final String base64Encode( byte[] in ) {
        int     iLen      = in.length;
        int     oDataLen  = ( iLen * 4 + 2 ) / 3;// output length without padding
        int     oLen      = ( ( iLen + 2 ) / 3 ) * 4;// output length including padding
        char[]  out       = new char[oLen];
        int     ip        = 0;
        int     op        = 0;
        int     i0;
        int     i1;
        int     i2;
        int     o0;
        int     o1;
        int     o2;
        int     o3;
        while ( ip < iLen ) {
            i0 = in[ip++] & 0xff;
            i1 = ip < iLen ? in[ip++] & 0xff : 0;
            i2 = ip < iLen ? in[ip++] & 0xff : 0;
            o0 = i0 >>> 2;
            o1 = ( ( i0 & 3 ) << 4 ) | ( i1 >>> 4 );
            o2 = ( ( i1 & 0xf ) << 2 ) | ( i2 >>> 6 );
            o3 = i2 & 0x3F;
            out[op++] = map1[o0];
            out[op++] = map1[o1];
            out[op] = op < oDataLen ? map1[o2] : '=';
            op++;
            out[op] = op < oDataLen ? map1[o3] : '=';
            op++;
        }
        return new String( out );
    }
    
}
