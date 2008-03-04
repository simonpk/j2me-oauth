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
/*
import java.lang.StringBuilder;
import java.net.URLDecoder;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
*/
public class OAuthParameterDecoder {
    private String unreservedCharactersPattern = "[a-zA-Z0-9\\-\\._~]";
    private String unreservedCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-._~";
    private String escapeSequence = "%\\d\\d";

    public OAuthParameterDecoder() {
    }

    public String decode(String s) {
        //StringBuilder sb = new StringBuilder();
        String decoded = new String(s);
  /*      Pattern p = Pattern.compile(escapeSequence);
        Matcher m = p.matcher(decoded);

        while (m.find()) {
            String escapedChar = decoded.substring(m.start(), m.end());
            try {
                m.replaceAll(URLDecoder.decode(escapedChar, "UTF-8"));
            } catch (java.io.UnsupportedEncodingException e) {
                ;
            }
        }
*/
        return decoded;
    }
}
