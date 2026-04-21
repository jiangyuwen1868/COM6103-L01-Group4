package com.ruoyi;

import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;



public class SM4UtilTest {

	static {
        Security.addProvider(new BouncyCastleProvider());
    }
	
	private static final String ALGORITHM = "SM4";
	
	@Test
	public void testDecrypt() throws Exception {
		String str = "REVEQgEEARBOCRoEa3eX1p9TAPuU4LB7BAAAABDkYWZgkbF6Rk3CjPBs1Mlo";
		String strkey = "DJGJNNK1N6uHCl/vokSNbA==";
		String strIv = "TgkaBGt3l9afUwD7lOCwew==";
		
		SM4UtilTest.decrypt(str, strkey, strIv, "SM4/CBC/PKCS7Padding");
	}
	
	
	@Test
	public void encryptValue() {
    	try {
    		String plainValue = "姜郁文";
	    	String AES_KEY = "hkiqAXU6Ur5fixGHaO4Lb2V2ggausYwW";
	    	byte[] key = Arrays.copyOf(DigestUtils.sha1(AES_KEY), 16);
	    	
	    	Cipher cipher = Cipher.getInstance("AES");
	    	cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"));
	    	byte[] result = cipher.doFinal(StringUtils.getBytesUtf8(String.valueOf(plainValue)));
	    	System.out.println(Base64.encodeBase64String(result));
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }


    // CBC模式加密(需16字节IV)
    public static String encrypt(String str, String strkey, String strIv, String algorithm) {
        try {
            byte[] key = Base64.decodeBase64(strkey);
            byte[] iv = handleIVBytes(strIv);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE,
                    new SecretKeySpec(key, ALGORITHM),
                    new IvParameterSpec(iv));
            return Base64.encodeBase64String(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String str, String strkey, String strIv, String algorithm) {
        try {
            byte[] key = Base64.decodeBase64(strkey);
            byte[] iv = handleIVBytes(strIv);
            byte[] bytes = Base64.decodeBase64(str.getBytes());
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE,
                    new SecretKeySpec(key, ALGORITHM),
                    new IvParameterSpec(iv));
            return new String(cipher.doFinal(bytes), StandardCharsets.UTF_8);
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
    }


    private static byte[] handleIVBytes(String iv) {
        final int IV_SIZE_BYTES = 16; // 固定 16 字节 (128 位)

        // 1. 尝试作为 Base64 解码
        try {
            byte[] decoded = Base64.decodeBase64(iv);
            if (decoded.length == IV_SIZE_BYTES) {
                return decoded;
            }
        } catch (Exception ignored) {
            // 非 Base64 格式，继续尝试 Hex
        }

        // 2. 尝试作为 Hex 解码
        try {
            byte[] decoded = Hex.decode(iv);
            if (decoded.length == IV_SIZE_BYTES) {
                return decoded;
            }
        } catch (Exception ignored) {
            // 非 Hex 格式，继续下一步
        }

        // 3. 检查原始字符串字节长度
        byte[] rawBytes = iv.getBytes(StandardCharsets.UTF_8);
        if (rawBytes.length == IV_SIZE_BYTES) {
            return rawBytes;
        }

        // 4. 所有情况均不匹配，抛出异常
        throw new IllegalArgumentException(
                "IV 长度必须为 16 字节，但实际为: " + rawBytes.length);
    }
}
