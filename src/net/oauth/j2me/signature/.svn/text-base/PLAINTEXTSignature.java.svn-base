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

package net.oauth.j2me.signature;

import net.oauth.j2me.OAuthParameterEncoder;
import net.oauth.j2me.OAuthParameterDecoder;

public class PLAINTEXTSignature implements OAuthSignature {
    private String method;
    private String message;
    private String key;
    private String signature;

    public PLAINTEXTSignature() {
        method = "PLAINTEXT";
        message = "";
        signature = "";
    }

    public String getMethod() {
        return method;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSignature() {
        OAuthParameterEncoder encoder = new OAuthParameterEncoder();
        signature = encoder.encode(encoder.encode(message)
            + "&" + encoder.encode(key));
        return signature;
    }
}
