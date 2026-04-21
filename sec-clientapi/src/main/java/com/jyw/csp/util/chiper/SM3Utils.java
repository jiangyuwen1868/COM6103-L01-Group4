package com.jyw.csp.util.chiper;

import com.jyw.csp.util.Utils;

public final class SM3Utils {
    public static String digest(String data) {
        byte[] md = new byte[32];
        byte[] bytes = data.getBytes();

        SM3Digest sm3 = new SM3Digest();
        sm3.update(bytes, 0, bytes.length);
        sm3.doFinal(md, 0);

        return Utils.bytes2Hex(md);
    }

    public static String hmac(byte[] keyBytes, byte[] dataBytes) {
        byte[] ipad = new byte[64];
        byte[] opad = new byte[64];
        for (int i = 0; i < 64; i++) {
            ipad[i] = 0x36;
            opad[i] = 0x5C;
        }

        for (int i = 0, len = keyBytes.length; i < len; i++) {
            ipad[i] = (byte) (ipad[i] ^ keyBytes[i]);
            opad[i] = (byte) (opad[i] ^ keyBytes[i]);
        }

        SM3Digest sm3 = new SM3Digest();
        sm3.reset();
        sm3.update(ipad, 0, ipad.length);
        sm3.update(dataBytes, 0, dataBytes.length);
        byte[] temp = new byte[sm3.getDigestSize()];
        sm3.doFinal(temp, 0);

        sm3.reset();
        sm3.update(opad, 0, opad.length);
        sm3.update(temp, 0, temp.length);
        sm3.doFinal(temp, 0);

        return Utils.bytes2Hex(temp);
    }
}
