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

package net.oauth.j2me.token;

import java.util.Hashtable;

public class AccessToken implements Token {
    private String token;
    private String secret;
    private Hashtable additionalParams;

    public AccessToken() {
    }

    public AccessToken(String token, String secret) {
        this.token = token;
        this.secret = secret;
    }

    public String getType() {
        return "AccessToken";
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Hashtable getAdditionalParams() {
        return additionalParams;
    }

    public void setAdditionalParams(Hashtable additionalParams) {
        this.additionalParams = additionalParams;
    }

    // Authorized is really only for Request Tokens
    public boolean getAuthorized() {
        return false;
    }

    public void setAuthorized(boolean authorized) {
        return;
    }

    // Exchanged is also really only for Request Tokens
    public boolean getExchanged() {
        return false;
    }

    public void setExchanged(boolean exchanged) {
        return;
    }
}
