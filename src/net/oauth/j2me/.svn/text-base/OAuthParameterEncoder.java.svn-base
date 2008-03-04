/*
 * Copyright 2007 Sxip Identity Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.oauth.j2me;

//import java.nio.charset.CharsetEncoder;
//import java.lang.CharSequence;
//import java.lang.StringBuilder;
//import java.net.URLEncoder;
import net.oauth.j2me.Util;
//import java.lang.StringBuffer;
public class OAuthParameterEncoder {
    private String unreservedCharactersPattern = "[a-zA-Z0-9\\-\\._~]";
    private String unreservedCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-._~";

    public OAuthParameterEncoder() {
    }

    public String encode(String s) {
        if (s == null || "".equals(s)) {
            return "";
        }

        StringBuffer sb = new StringBuffer(s.length()*2);

        for (int i = 0; i < s.length(); i++) {
            if (unreservedCharacters.indexOf(s.charAt(i)) == -1) {
                // get byte values of the character
                // and turn them into percent encoding
                String t = String.valueOf(s.charAt(i));

               // try {
                    sb.append(Util.urlEncode(t));
                    //byte[] charBytes = t.getBytes("UTF-8");
                //} catch (java.io.UnsupportedEncodingException e) {
                //    ;
                //}
            } else {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }
}
