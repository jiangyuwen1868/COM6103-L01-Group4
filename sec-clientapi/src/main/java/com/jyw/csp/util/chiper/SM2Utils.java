package com.jyw.csp.util.chiper;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.math.ec.ECPoint;

import com.jyw.csp.util.Utils;

/**
 * @author yzw
 *
 */
public class SM2Utils {
    /**
     * infinity
     */
    public static final String PC_00 = "00";
    /**
     * compressed
     */
    public static final String PC_02 = "02";
    /**
     * compressed
     */
    public static final String PC_03 = "03";
    /**
     * uncompressed
     */
    public static final String PC_04 = "04";
    /**
     * hybrid
     */
    public static final String PC_06 = "06";
    /**
     * hybrid
     */
    public static final String PC_07 = "07";

    /**
     * 大数字转换字节数组
     * 
     * @param n
     * @return
     */
    private static byte[] bigInteger2Bytes(BigInteger n) {
        if (n == null) {
            return null;
        }

        int byteCount = 32;
        byte[] result = new byte[byteCount];
        byte[] tmp = n.toByteArray();
        if (tmp.length == byteCount + 1) {
            System.arraycopy(tmp, 1, result, 0, result.length);
        } else if (n.toByteArray().length == byteCount) {
            System.arraycopy(tmp, 0, result, 0, result.length);
        } else {
            for (int i = 0; i < byteCount - tmp.length; i++) {
                result[i] = 0;
            }
            System.arraycopy(tmp, 0, result, byteCount - tmp.length, tmp.length);
        }
        return tmp;
    }

    private static byte[] asUnsignedByteArray(BigInteger value) {
        byte bytes[] = value.toByteArray();
        if (bytes[0] == 0) {
            byte tmp[] = new byte[bytes.length - 1];
            System.arraycopy(bytes, 1, tmp, 0, tmp.length);
            return tmp;
        } else {
            return bytes;
        }
    }

    private static byte[] getByteArray(String hexString) {
        byte[] hexbyte = hexString.getBytes();
        byte[] bitmap = new byte[hexbyte.length / 2];
        for (int i = 0; i < bitmap.length; i++) {
            hexbyte[i * 2] -= hexbyte[i * 2] > '9' ? 7 : 0;
            hexbyte[i * 2 + 1] -= hexbyte[i * 2 + 1] > '9' ? 7 : 0;
            bitmap[i] = (byte) ((hexbyte[i * 2] << 4 & 0xf0) | (hexbyte[i * 2 + 1] & 0x0f));
        }
        return bitmap;
    }

    private static byte[] getT1(SM2 sm2, String publicKey) {
        String id = "1234567812345678";
        // byte[] idByte = id.getBytes();
        byte[] idByte = Utils.hex2Byte(id);
        byte[] entlByte = getENTL(idByte);

        byte[] aByte = asUnsignedByteArray(sm2.eccA);
        byte[] bByte = asUnsignedByteArray(sm2.eccB);
        byte[] gxByte = asUnsignedByteArray(sm2.eccGx);
        byte[] gyByte = asUnsignedByteArray(sm2.eccGy);
        byte[] axyByte = getByteArray(publicKey);

        byte[] t1Bytes = new byte[entlByte.length + idByte.length + aByte.length + bByte.length + gxByte.length + gyByte.length + axyByte.length];
        System.arraycopy(entlByte, 0, t1Bytes, 0, entlByte.length);
        System.arraycopy(idByte, 0, t1Bytes, entlByte.length, idByte.length);
        System.arraycopy(aByte, 0, t1Bytes, entlByte.length + idByte.length, aByte.length);
        System.arraycopy(bByte, 0, t1Bytes, entlByte.length + idByte.length + aByte.length, bByte.length);
        System.arraycopy(gxByte, 0, t1Bytes, entlByte.length + idByte.length + aByte.length + bByte.length, gxByte.length);
        System.arraycopy(gyByte, 0, t1Bytes, entlByte.length + idByte.length + aByte.length + bByte.length + gxByte.length, gyByte.length);
        System.arraycopy(axyByte, 0, t1Bytes, entlByte.length + idByte.length + aByte.length + bByte.length + gxByte.length + gyByte.length, axyByte.length);

        return t1Bytes;
    }

    private static byte[] getENTL(byte[] bytes) {
        byte[] entlByte = new byte[2];
        int i = bytes.length * 8;
        entlByte[1] = ((byte) (i & 0xFF));
        entlByte[0] = ((byte) (i >> 8 & 0xFF));
        return entlByte;
    }

    /**
     * 生成随机密钥对
     * 
     * @return 密钥对：prik || pubkx || pubky
     */
    public static byte[] generateKeyPair() {
        SM2 sm2 = new SM2();
        AsymmetricCipherKeyPair key = sm2.eccKeyPairGenerator.generateKeyPair();
        ECPrivateKeyParameters ecpriv = (ECPrivateKeyParameters) key.getPrivate();
        ECPublicKeyParameters ecpub = (ECPublicKeyParameters) key.getPublic();

        byte[] priv = bigInteger2Bytes(ecpriv.getD());
        byte[] pub = ecpub.getQ().getEncoded(false);

        byte[] result = new byte[priv.length + pub.length];
        System.arraycopy(priv, 0, result, 0, priv.length);
        System.arraycopy(pub, 0, result, priv.length, pub.length);

        return result;
    }

    /**
     * 数据加密
     * 
     * @param publicKey
     * @param data
     * @return C1 || C2 || C3
     * @throws IOException
     */
    public static byte[] encrypt0(byte[] publicKey, byte[] data) throws IOException {
        if (publicKey == null || publicKey.length == 0) {
            return null;
        }

        if (data == null || data.length == 0) {
            return null;
        }

        byte[] c2 = new byte[data.length];
        System.arraycopy(data, 0, c2, 0, data.length);

        SM2 sm2 = new SM2();
        SM2Cipher cipher = new SM2Cipher();
        ECPoint userKey = sm2.eccCurve.decodePoint(publicKey);

        ECPoint ecPoint = cipher.initEncrypt(sm2, userKey);
        cipher.encrypt(c2);
        byte[] c3 = new byte[32];
        cipher.doFinal(c3);

        byte[] c1 = ecPoint.getEncoded(false);

        byte[] result = new byte[c1.length + c2.length + c3.length];
        System.arraycopy(c1, 0, result, 0, c1.length);
        System.arraycopy(c2, 0, result, c1.length, c2.length);
        System.arraycopy(c3, 0, result, c1.length + c2.length, c3.length);

        return result;
    }

    /**
     * 数据加密
     * 
     * @param publicKey
     * @param data
     * @return C1 || C3 || C2
     * @throws IOException
     */
    public static String encrypt(byte[] publicKey, byte[] data) throws IOException {
        if (publicKey == null || publicKey.length == 0) {
            return null;
        }

        if (data == null || data.length == 0) {
            return null;
        }

        byte[] source = new byte[data.length];
        System.arraycopy(data, 0, source, 0, data.length);

        SM2 sm2 = new SM2();
        SM2Cipher cipher = new SM2Cipher();
        ECPoint userKey = sm2.eccCurve.decodePoint(publicKey);

        ECPoint c1 = cipher.initEncrypt(sm2, userKey);
        cipher.encrypt(source);
        byte[] c3 = new byte[32];
        cipher.doFinal(c3);

        // 国密规范变更，拼装成加密字符串：C1 || C3 || C2
        return Utils.bytes2Hex(c1.getEncoded(false)) + Utils.bytes2Hex(c3) + Utils.bytes2Hex(source);
    }

    /**
     * 数据解密
     * 
     * @param privateKey
     * @param encryptedData C1 || C2 || C3
     * @return
     * @throws IOException
     */
    public static byte[] decrypt0(byte[] privateKey, byte[] encryptedData) throws IOException {
        if (privateKey == null || privateKey.length == 0) {
            return null;
        }

        if (encryptedData == null || encryptedData.length == 0) {
            return null;
        }

        // 拆分加密字符串
        // C1 = C1标志位2位 + C1实体部分128位
        // C2 = encryptedData.length - C1长度 - C3长度
        // C3 = C3实体部分64位
        byte[] c1 = new byte[1 + 64];
        byte[] c2 = new byte[encryptedData.length - 1 - 64 - 32];
        byte[] c3 = new byte[32];
        System.arraycopy(encryptedData, 0, c1, 0, c1.length);
        System.arraycopy(encryptedData, c1.length, c2, 0, c2.length);
        System.arraycopy(encryptedData, c1.length + c2.length, c3, 0, c3.length);

        SM2 sm2 = new SM2();
        BigInteger userD = new BigInteger(1, privateKey);

        // 通过C1实体字节来生成ECPoint
        ECPoint ecPoint = sm2.eccCurve.decodePoint(c1);
        SM2Cipher cipher = new SM2Cipher();
        cipher.initDecrypt(userD, ecPoint);
        cipher.decrypt(c2);
        cipher.doFinal(c3);

        return c2;
    }

    /**
     * 数据解密
     * 
     * @param privateKey
     * @param encryptedData C1 || C3 || C2
     * @return
     * @throws IOException
     */
    public static byte[] decrypt(byte[] privateKey, byte[] encryptedData) throws IOException {
        if (privateKey == null || privateKey.length == 0) {
            return null;
        }

        if (encryptedData == null || encryptedData.length == 0) {
            return null;
        }
        // 加密字节数组转换为十六进制的字符串 长度变为encryptedData.length * 2
        String data = Utils.bytes2Hex(encryptedData);

        // 国密规范变更
        // 拆分加密字符串
        // C1 = C1标志位2位 + C1实体部分128位
        // C3 = C3实体部分64位
        // C2 = encryptedData.length * 2 - C1长度 - C2长度
        byte[] c1Bytes = Utils.hex2Byte(data.substring(0, 130));
        byte[] c3 = Utils.hex2Byte(data.substring(130, 130 + 64));
        byte[] c2 = Utils.hex2Byte(data.substring(130 + 64));

        SM2 sm2 = new SM2();

        BigInteger userD = new BigInteger(1, privateKey);
        // 通过C1实体字节来生成ECPoint
        ECPoint c1 = sm2.eccCurve.decodePoint(c1Bytes);

        SM2Cipher cipher = new SM2Cipher();
        cipher.initDecrypt(userD, c1);
        cipher.decrypt(c2);
        cipher.doFinal(c3);

        return c2;
    }

    /**
     * @param publicKey
     * @param privateKey
     * @param data
     * @return
     */
    public static String sign(String publicKey, String privateKey, String data) {
        SM2 sm2 = new SM2();
        byte[] t1Bytes = getT1(sm2, publicKey);

        SM3Digest sm3Digest = new SM3Digest();
        sm3Digest.update(t1Bytes, 0, t1Bytes.length);
        byte[] zBytes = new byte[32];
        sm3Digest.doFinal(zBytes, 0);

        // byte[] dataBytes = getByteArray(data);
        byte[] dataBytes = data.getBytes();

        byte[] t2Bytes = new byte[zBytes.length + dataBytes.length];
        System.arraycopy(zBytes, 0, t2Bytes, 0, zBytes.length);
        System.arraycopy(dataBytes, 0, t2Bytes, zBytes.length, dataBytes.length);

        sm3Digest.update(t2Bytes, 0, t2Bytes.length);
        byte[] hashBytes = new byte[32];
        sm3Digest.doFinal(hashBytes, 0);

        BigInteger e = new BigInteger(1, hashBytes);
        BigInteger k = null;
        ECPoint pointK = null;
        BigInteger r = null;
        BigInteger s = null;
        BigInteger d = null;

        ECPrivateKeyParameters ecPriv = new ECPrivateKeyParameters(new BigInteger(privateKey, 16), sm2.eccBcSpec);
        SecureRandom random = new SecureRandom();
        do {
            do {
                do {
                    k = new BigInteger(sm2.eccN.bitLength(), random);
                } while (k.equals(BigInteger.ZERO) || k.compareTo(sm2.eccN) >= 0);

                pointK = sm2.eccPointG.multiply(k);

                d = ecPriv.getD();

                r = e.add(pointK.normalize().getXCoord().toBigInteger());
                r = r.mod(sm2.eccN);
            } while (r.equals(BigInteger.ZERO) || r.add(k).equals(sm2.eccN));

            BigInteger da1 = d.add(BigInteger.ONE);
            da1 = da1.modInverse(sm2.eccN);

            s = r.multiply(d);
            s = k.subtract(s).mod(sm2.eccN);
            s = da1.multiply(s).mod(sm2.eccN);
        } while (s.equals(BigInteger.ZERO));

        String signR = Utils.bytes2Hex(asUnsignedByteArray(r));
        String signS = Utils.bytes2Hex(asUnsignedByteArray(s));

        return signR + signS;
    }

    /**
     * @param publicKey
     * @param data
     * @param signature
     * @return
     */
    public static boolean verify(String publicKey, String data, String signature) {
        SM2 sm2 = new SM2();

        byte[] t1Bytes = getT1(sm2, publicKey);

        SM3Digest sm3Digest = new SM3Digest();
        sm3Digest.update(t1Bytes, 0, t1Bytes.length);
        byte[] zBytes = new byte[32];
        sm3Digest.doFinal(zBytes, 0);

        // byte[] dataBytes = getByteArray(data);
        byte[] dataBytes = data.getBytes();

        byte[] t2Bytes = new byte[zBytes.length + dataBytes.length];
        System.arraycopy(zBytes, 0, t2Bytes, 0, zBytes.length);
        System.arraycopy(dataBytes, 0, t2Bytes, zBytes.length, dataBytes.length);

        sm3Digest.update(t2Bytes, 0, t2Bytes.length);
        byte[] hashBytes = new byte[32];
        sm3Digest.doFinal(hashBytes, 0);

        BigInteger ax = new BigInteger(publicKey.substring(0, publicKey.length() / 2), 16);
        BigInteger ay = new BigInteger(publicKey.substring(publicKey.length() / 2), 16);

        ECPoint pointA = sm2.eccCurve.createPoint(ax, ay);

        BigInteger r = new BigInteger(signature.substring(0, signature.length() / 2), 16);
        BigInteger s = new BigInteger(signature.substring(signature.length() / 2), 16);

        if (r.equals(BigInteger.ZERO) || r.compareTo(sm2.eccN) >= 0) {
            return false;
        }
        if (s.equals(BigInteger.ZERO) || s.compareTo(sm2.eccN) >= 0) {
            return false;
        }

        BigInteger e = new BigInteger(1, hashBytes);

        BigInteger t = r.add(s).mod(sm2.eccN);

        if (t.equals(BigInteger.ZERO)) {
            return false;
        }

        ECPoint x1y1 = sm2.eccPointG.multiply(s);
        x1y1 = x1y1.add(pointA.multiply(t));

        BigInteger rr = e.add(x1y1.normalize().getXCoord().toBigInteger()).mod(sm2.eccN);

        return r.equals(rr);
    }
}
