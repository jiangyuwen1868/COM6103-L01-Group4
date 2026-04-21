package com.ruoyi.common.utils.chiper;

import java.math.BigInteger;
import java.security.Key;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ruoyi.common.utils.Base64;
import com.ruoyi.common.utils.Utils;


public class SM4Util {
	public String secretKey = "";
	public String iv = "";
	public boolean hexString = false;
	public Padding padding = Padding.PKCS7PADDING;

	private final static Logger logger = LoggerFactory.getLogger(SM4Util.class);

	public SM4Util() {
	}

	/**
	 * SM4_ECB模式加密数据
	 * @param plainText 明文数据
	 * @return Base64编码格式密文
	 */
	public String encryptDataToB4_ECB(byte[] plainText) {
		try {
			SM4Context ctx = new SM4Context();
			ctx.mode = SM4.SM4_ENCRYPT;
			ctx.padding = padding;

			byte[] keyBytes;
			if (hexString) {
				keyBytes = Utils.hex2Byte(secretKey);
			} else {
				keyBytes = secretKey.getBytes();
			}
			SM4 sm4 = new SM4();
			sm4.sm4_setkey_enc(ctx, keyBytes);
			byte[] encrypted = sm4.sm4_crypt_ecb(ctx,
					plainText);
			String cipherText = new String(Base64.encode(encrypted), "UTF-8");
			if (cipherText != null && cipherText.trim().length() > 0) {
				Pattern p = Pattern.compile("\\s*|\t|\r|\n");
				Matcher m = p.matcher(cipherText);
				cipherText = m.replaceAll("");
			}
			return cipherText;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * SM4_ECB解密Base64编码格式密文
	 * @param cipherText Base64编码格式密文
	 * @return 明文
	 */
	public byte[] decryptDataB4_ECB(String cipherText) {
		try {
			SM4Context ctx = new SM4Context();
			ctx.mode = SM4.SM4_DECRYPT;
			ctx.padding = padding;

			byte[] keyBytes;
			if (hexString) {
				keyBytes = Utils.hex2Byte(secretKey);
			} else {
				keyBytes = secretKey.getBytes();
			}
			SM4 sm4 = new SM4();
			sm4.sm4_setkey_dec(ctx, keyBytes);
			byte[] decrypted = sm4.sm4_crypt_ecb(ctx,
					Base64.decode(cipherText));
			return decrypted;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * SM4_ECB模式加密数据
	 * @param plainText 明文
	 * @return 十六进制格式编码数据
	 */
	public String encryptDataToHex_ECB(byte[] plainText) {
		try {
			SM4Context ctx = new SM4Context();
			ctx.mode = SM4.SM4_ENCRYPT;
			ctx.padding = padding;

			byte[] keyBytes;
			if (hexString) {
				keyBytes = Utils.hex2Byte(secretKey);
			} else {
				keyBytes = secretKey.getBytes();
			}
			SM4 sm4 = new SM4();
			sm4.sm4_setkey_enc(ctx, keyBytes);
			byte[] encrypted = sm4.sm4_crypt_ecb(ctx,
					plainText);
			
			return Utils.bytes2Hex(encrypted);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * SM4_ECB模式解密十六进制字符串密文数据
	 * @param cipherText 十六进制字符串密文数据
	 * @return 明文
	 */
	public byte[] decryptDataHex_ECB(String cipherText) {
		try {
			SM4Context ctx = new SM4Context();
			ctx.mode = SM4.SM4_DECRYPT;
			ctx.padding = padding;

			byte[] keyBytes;
			if (hexString) {
				keyBytes = Utils.hex2Byte(secretKey);
			} else {
				keyBytes = secretKey.getBytes();
			}
			SM4 sm4 = new SM4();
			sm4.sm4_setkey_dec(ctx, keyBytes);
			byte[] decrypted = sm4.sm4_crypt_ecb(ctx,
					Utils.hex2Byte(cipherText));
			return decrypted;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * SM4_CBC加密数据
	 * @param plainText 明文
	 * @return Base64格式编码密文数据
	 */
	public String encryptDataToB4_CBC(byte[] plainText) {
		try {
			SM4Context ctx = new SM4Context();
			ctx.mode = SM4.SM4_ENCRYPT;
			ctx.padding = padding;

			byte[] keyBytes;
			byte[] ivBytes;

			if (hexString) {
				keyBytes = Utils.hex2Byte(secretKey);
				ivBytes = Utils.hex2Byte(iv);
			} else {
				keyBytes = secretKey.getBytes();
				ivBytes = iv.getBytes();
			}

			SM4 sm4 = new SM4();
			sm4.sm4_setkey_enc(ctx, keyBytes);
			byte[] encrypted = sm4.sm4_crypt_cbc(ctx, ivBytes,
					plainText);
			String cipherText = new String(Base64.encode(encrypted), "UTF-8");
			if (cipherText != null && cipherText.trim().length() > 0) {
				Pattern p = Pattern.compile("\\s*|\t|\r|\n");
				Matcher m = p.matcher(cipherText);
				cipherText = m.replaceAll("");
			}
			return cipherText;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * SM4_CBC解密Base64密文数据
	 * @param cipherText Base64密文数据
	 * @return 明文
	 */
	public byte[] decryptDataB4_CBC(String cipherText) {
		try {
			SM4Context ctx = new SM4Context();
			ctx.mode = SM4.SM4_DECRYPT;
			ctx.padding = padding;

			byte[] keyBytes;
			byte[] ivBytes;
			if (hexString) {
				keyBytes = Utils.hex2Byte(secretKey);
				ivBytes = Utils.hex2Byte(iv);
			} else {
				keyBytes = secretKey.getBytes();
				ivBytes = iv.getBytes();
			}

			SM4 sm4 = new SM4();
			sm4.sm4_setkey_dec(ctx, keyBytes);
			byte[] decrypted = sm4.sm4_crypt_cbc(ctx, ivBytes,
					Base64.decode(cipherText));
			return decrypted;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * SM4_CBC加密数据
	 * @param plainText 明文
	 * @return 十六进制字符串密文
	 */
	public String encryptDataToHex_CBC(byte[] plainText) {
		try {
			SM4Context ctx = new SM4Context();
			ctx.mode = SM4.SM4_ENCRYPT;
			ctx.padding = padding;

			byte[] keyBytes;
			byte[] ivBytes;

			if (hexString) {
				keyBytes = Utils.hex2Byte(secretKey);
				ivBytes = Utils.hex2Byte(iv);
			} else {
				keyBytes = secretKey.getBytes();
				ivBytes = iv.getBytes();
			}

			SM4 sm4 = new SM4();
			sm4.sm4_setkey_enc(ctx, keyBytes);
			byte[] encrypted = sm4.sm4_crypt_cbc(ctx, ivBytes,
					plainText);
			
			return Utils.bytes2Hex(encrypted);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * SM4_CBC解密十六进制密文数据
	 * @param cipherText 十六进制密文数据
	 * @return 明文
	 */
	public byte[] decryptDataHex_CBC(String cipherText) {
		try {
			SM4Context ctx = new SM4Context();
			ctx.mode = SM4.SM4_DECRYPT;
			ctx.padding = padding;

			byte[] keyBytes;
			byte[] ivBytes;
			if (hexString) {
				keyBytes = Utils.hex2Byte(secretKey);
				ivBytes = Utils.hex2Byte(iv);
			} else {
				keyBytes = secretKey.getBytes();
				ivBytes = iv.getBytes();
			}

			SM4 sm4 = new SM4();
			sm4.sm4_setkey_dec(ctx, keyBytes);
			byte[] decrypted = sm4.sm4_crypt_cbc(ctx, ivBytes,
					Utils.hex2Byte(cipherText));
			return decrypted;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 生成SM4密钥（HexString）
	 * @return 十六进制字符串SM4密钥
	 */
	public String generateKey() {
		try {
			SecureRandom rng = new SecureRandom();
			BigInteger keyBit = new BigInteger(128, rng);
			while(keyBit.bitLength() < 128){
				keyBit = new BigInteger(128, rng);
			}
			String keyR = "0000000000" + keyBit.toString(16);
			String key = keyR.substring(keyR.length()-32, keyR.length());
			return key.toUpperCase();
		} catch(Exception e) {
			return randomKey(32/2, true);
		}
	}
	
	/**
	 * 生成SM4密钥（AsciiString）
	 * @return ASCII字符串SM4密钥
	 */
	public String randomKey() {
		return randomKey(32/2, false);
	}
	
	protected String randomKey(int size, boolean hexString) {
		String[] seed = new String[] { "A", "B", "C", "D", "E", "F", "G", "H",
				"I", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U",
				"V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g",
				"h", "i", "j", "k", "m", "n", "p", "Q", "r", "s", "t", "u",
				"v", "w", "x", "y", "z", "2", "3", "4", "5", "6", "7", "8", "9" };// 数组
		int seedlength = seed.length;// 数组长度
		String createPassword = "";
		for (int i = 0; i < size; i++) {
			int j = (int) Math.floor(Math.random() * seedlength);
			createPassword += seed[j];
		}
		if (hexString) {
			return Utils.bytes2Hex(createPassword.getBytes());
		} else {
			return createPassword;
		}
	}

	/**
	 * 加密
	 *
	 * @param key       密钥明文字节
	 * @param keyMode   模式
	 * @param padding   填充
	 * @param plainData 明文
	 * @return
	 * @throws Exception
	 */
	static public byte[] encrypt(byte[] key, String keyMode, Padding padding, byte[] plainData) throws Exception {
		return encrypt(key,keyMode,padding,plainData,null);
	}

	/**
	 * 加密
	 *
	 * @param key       密钥明文字节
	 * @param keyMode   模式
	 * @param padding   填充
	 * @param plainData 明文
	 * @return
	 * @throws Exception
	 */
	static public byte[] encrypt(byte[] key, String keyMode, Padding padding, byte[] plainData, byte[] iv) throws Exception {

		Key sm4Key = new SecretKeySpec(key, "SM4");
		StringBuilder sb = new StringBuilder("SM4/");
		sb.append(keyMode).append("/").append(padding.getValue());
		Cipher cipher = Cipher.getInstance(sb.toString(), "BC");

		if (iv != null) {
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
			cipher.init(Cipher.ENCRYPT_MODE, sm4Key, ivParameterSpec);
		} else {
			cipher.init(Cipher.ENCRYPT_MODE, sm4Key);
		}

		byte[] cipherData = cipher.doFinal(plainData);

		logger.info("SM4 Encrypt result:" + Utils.bytes2Hex(cipherData));

		return cipherData;
	}
}
