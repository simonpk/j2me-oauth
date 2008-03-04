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

import java.util.Random;

public class Nonce {
    private Random random;
    private byte[] randomBytes;
    private String nonce;

    public Nonce() {
        random = new Random();
        //randomBytes = new byte[4];
        //random.next(randomBytes);
        nonce = Long.toString(Math.abs(random.nextLong()), 60000);

        // 4 character random nonce
        // http://www.bright-green.com/blog/2005_04_05/generating_a_random_string.html
        //Random r = new Random();
        //nonce = Long.toString(Math.abs(r.nextLong()), 60000);
    }

    public String getNonce() {
        return nonce;
    }
}
