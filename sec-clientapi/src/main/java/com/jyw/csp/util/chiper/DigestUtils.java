package com.jyw.csp.util.chiper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SM3Digest;

public class DigestUtils {
	
	private static final String ALGORITHM_SHA1 = "SHA-1";
    private static final String ALGORITHM_SHA256 = "SHA-256";
    private static final String ALGORITHM_SHA512 = "SHA-512";
    
    static MessageDigest getDigest(String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static byte[] sha1(byte[] data) {
        return getDigest(ALGORITHM_SHA1).digest(data);
    }

    public static byte[] sha256(byte[] data) {
        return getDigest(ALGORITHM_SHA256).digest(data);
    }

    public static byte[] sha512(byte[] data) {
        return getDigest(ALGORITHM_SHA512).digest(data);
    }

	/* HASH1 */
    public static byte[] calcHashDigest(String data) {    	
    	SHA1Digest digest = new SHA1Digest();
		digest.reset();
		digest.update(data.getBytes(), 0, data.getBytes().length);
		byte[] dresult = new byte[(digest.getDigestSize())];
		digest.doFinal(dresult,0);
		return dresult;
    }
    
    public static byte[] calcHashDigest(byte[] data) {    	
    	SHA1Digest digest = new SHA1Digest();
		digest.reset();
		digest.update(data, 0, data.length);
		byte[] dresult = new byte[(digest.getDigestSize())];
		digest.doFinal(dresult,0);
		return dresult;
    }
    
    /* SM3 */
    public static  byte[] calcSM3Digest(String data) {
    	SM3Digest digest = new SM3Digest();
		digest.reset();
		digest.update(data.getBytes(), 0, data.getBytes().length);
		byte[] dresult = new byte[(digest.getDigestSize())];
		digest.doFinal(dresult,0);
		return dresult;
    }
    
    public static byte[] calcSM3Digest(byte[] data) {
    	SM3Digest digest = new SM3Digest();
		digest.reset();
		digest.update(data, 0, data.length);
		byte[] dresult = new byte[(digest.getDigestSize())];
		digest.doFinal(dresult,0);
		return dresult;
    }
}
