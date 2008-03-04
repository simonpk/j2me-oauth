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

import java.util.Hashtable;

public class ServiceProviderConfig {
    private String oauthVersion = "1.0";
    private String authEndpoint;
    private String requestTokenEndpoint;
    private String accessTokenEndpoint;
    private String apiEndpoint;
    private Hashtable additionalParams;

    public ServiceProviderConfig() {
    }

    public String getOauthVersion() {
        return oauthVersion;
    }

    public void setOauthVersion(String oauthVersion) {
        this.oauthVersion = oauthVersion;
    }

    public String getAuthEndpoint() {
        return authEndpoint;
    }

    public void setAuthEndpoint(String authEndpoint) {
        this.authEndpoint = authEndpoint;
    }

    public String getRequestTokenEndpoint() {
        return requestTokenEndpoint;
    }

    public void setRequestTokenEndpoint(String requestTokenEndpoint) {
        this.requestTokenEndpoint = requestTokenEndpoint;
    }

    public String getAccessTokenEndpoint() {
        return accessTokenEndpoint;
    }

    public void setAccessTokenEndpoint(String accessTokenEndpoint) {
        this.accessTokenEndpoint = accessTokenEndpoint;
    }

    public String getApiEndpoint() {
        return apiEndpoint;
    }

    public void setApiEndpoint(String apiEndpoint) {
        this.apiEndpoint = apiEndpoint;
    }

    public Hashtable getAdditionalParams() {
        return additionalParams;
    }

    public void setAdditionalParams(Hashtable additionalParams) {
        this.additionalParams = additionalParams;
    }

    public String getAdditionalParam(String key) {
        return (String) additionalParams.get(key);
    }

    public void setAdditionalParam(String key, String value) {
        additionalParams.put(key, value);
    }
}
