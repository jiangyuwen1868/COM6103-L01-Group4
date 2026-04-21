package com.ruoyi.common.utils.chiper;

import java.security.Security;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.ruoyi.common.utils.Utils;

public final class SM3Utils {
	public static final String ALG_HMAC_SM3 = "HMAC-SM3";
	static {
		if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
			// No such provider: BC
			Security.addProvider(new BouncyCastleProvider());
		}
	}
	
    public static String digest(String data) {
        byte[] md = new byte[32];
        byte[] bytes = data.getBytes();

        SM3Digest sm3 = new SM3Digest();
        sm3.update(bytes, 0, bytes.length);
        sm3.doFinal(md, 0);

        return Utils.bytes2Hex(md);
    }
    
    public static String hmac(String key, String data) {
    	byte[] srcData = data.getBytes();
    	KeyParameter keyParameter = new KeyParameter(Utils.hex2Byte(key));
    	
    	org.bouncycastle.crypto.digests.SM3Digest digest = new org.bouncycastle.crypto.digests.SM3Digest();

    	HMac mac = new HMac(digest);

    	mac.init(keyParameter);

    	mac.update(srcData, 0, srcData.length);

    	byte[] result = new byte[mac.getMacSize()];

    	mac.doFinal(result, 0);

    	return Utils.bytes2Hex(result);
    }
    
    public static String HMac(String key, String data) {
    	try {
	    	byte[] srcData = data.getBytes();
	    	SecretKey secretKey = new SecretKeySpec(Utils.hex2Byte(key), ALG_HMAC_SM3);
	    	
	    	Mac mac = Mac.getInstance(ALG_HMAC_SM3, "BC");
	        mac.init(secretKey);
	        mac.reset();
	        mac.update(srcData, 0, srcData.length);
	        byte[] result = mac.doFinal();
	        return Utils.bytes2Hex(result);
    	} catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
}
