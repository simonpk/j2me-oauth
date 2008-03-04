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

// TODO -- note about how to get bouncycastle stuff
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.params.KeyParameter;
//import javax.crypto.spec.SecretKeySpec;

import net.oauth.j2me.OAuthParameterEncoder;
import net.oauth.j2me.OAuthParameterDecoder;

import net.oauth.j2me.Util;

public class HMACSHA1Signature implements OAuthSignature {
    private String method;
    private String message;
    private String key;
    private String signature;

    public HMACSHA1Signature() {
        method = "HMAC-SHA1";
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
        try {
            HMac m=new HMac(new SHA1Digest());
            m.init(new KeyParameter(key.getBytes("UTF-8")));
            byte[] bytes=message.getBytes("UTF-8");
            m.update(bytes, 0, bytes.length);
            byte[] mac = new byte[m.getMacSize()];
            m.doFinal(mac, 0);
            signature = new String(Util.base64Encode(mac));
            
            // debug
            System.out.println("mac alg: "+m.getAlgorithmName());
            System.out.println("dig alg: "+m.getUnderlyingDigest().getAlgorithmName());
            System.out.println("key: "+key);
            System.out.println("message: "+message);
            System.out.println("unencoded: "+new String(mac));
            System.out.println("sig: "+signature);
        } catch (java.io.UnsupportedEncodingException e) {
            ;
        } catch (Exception e) {
            ;
        }

        return signature;
    }
}
