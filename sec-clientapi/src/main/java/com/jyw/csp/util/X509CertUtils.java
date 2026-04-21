package com.jyw.csp.util;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.pkcs.CertificationRequest;
import org.bouncycastle.asn1.pkcs.CertificationRequestInfo;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jyw.csp.util.chiper.DigestUtils;

public final class X509CertUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(X509CertUtils.class);

    public static final int KEY_ALGORITHM_RSA1024 = 1;
    public static final int KEY_ALGORITHM_RSA2048 = 2;
    public static final int KEY_ALGORITHM_SM2 = 3;

    public static final int ALGORITHM_RSA = 1;
    public static final int ALGORITHM_SM2 = 2;

    public static final int HSAH_ALGORITHM_SHA1 = 1;
    public static final int HASH_ALGORITHM_SHA256 = 2;
    public static final int HASH_ALGORITHM_SHA512 = 3;
    public static final int HASH_ALGORITHM_SM3 = 4;

    public static final String SIGNATURE_ALGORITHM_SHA1WITHRSA = "SHA1WITHRSA";
    public static final String SIGNATURE_ALGORITHM_SHA256WITHRSA = "SHA256WITHRSA";
    public static final String SIGNATURE_ALGORITHM_SHA512WITHRSA = "SHA512WITHRSA";
    public static final String SIGNATURE_ALGORITHM_SM3WITHSM2 = "SM3WITHSM2";

    public static final ASN1ObjectIdentifier OID_SHA1 = new ASN1ObjectIdentifier("1.3.14.3.2.26");
    public static final ASN1ObjectIdentifier OID_SHA256 = new ASN1ObjectIdentifier("2.16.840.1.101.3.4.2.1");
    public static final ASN1ObjectIdentifier OID_SHA512 = new ASN1ObjectIdentifier("2.16.840.1.101.3.4.2.3");

    // gm
    public static final ASN1ObjectIdentifier OID_ECPUBLICKEY = new ASN1ObjectIdentifier("1.2.840.10045.2.1");
    public static final ASN1ObjectIdentifier SM2P256V1 = new ASN1ObjectIdentifier("1.2.156.10197.1.301");
    public static final ASN1ObjectIdentifier SM2SIGN_WITH_SM3 = new ASN1ObjectIdentifier("1.2.156.10197.1.501");

    public static RSAPublicKey getRSAPublicKey(String modulusHex, BigInteger publicExponent) {
    	RSAPublicKey publicKey = null;

        BigInteger modulus = new BigInteger(modulusHex, 16);

        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(modulus, publicExponent);
            publicKey = (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("", e);
        } catch (InvalidKeySpecException e) {
            LOGGER.error("", e);
        }

        return publicKey;
    }

    private static PrivateKey getRSAPrivateKey(String modulusHex, String privateExponentHex) {
        PrivateKey privateKey = null;

        BigInteger modulus = new BigInteger(modulusHex, 16);
        BigInteger privateExponent = new BigInteger(privateExponentHex, 16);

        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(modulus, privateExponent);
            privateKey = keyFactory.generatePrivate(privateKeySpec);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("", e);
        } catch (InvalidKeySpecException e) {
            LOGGER.error("", e);
        }

        return privateKey;
    }

    static byte[] encryptByPrivateKey(PrivateKey privateKey, byte[] dataBytes) {
        byte[] encryptedBytes = null;

        int modulusLength = ((RSAPrivateKey) privateKey).getModulus().bitLength();
        /*
        if (privateKey instanceof RSAPrivateKey) {
            modulusLength = ((RSAPrivateKey) privateKey).getModulus().bitLength();
        } else if (privateKey instanceof RSAPrivateCrtKey) {
            modulusLength = ((RSAPrivateCrtKey) privateKey).getModulus().bitLength();
        }
        */
        byte[] bytes = rsaPKCS1padding(modulusLength, dataBytes);

        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            encryptedBytes = cipher.doFinal(bytes);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("", e);
        } catch (NoSuchPaddingException e) {
            LOGGER.error("", e);
        } catch (InvalidKeyException e) {
            LOGGER.error("", e);
        } catch (IllegalBlockSizeException e) {
            LOGGER.error("", e);
        } catch (BadPaddingException e) {
            LOGGER.error("", e);
        }

        return encryptedBytes;
    }

    private static byte[] rsaSign(Provider provider, PrivateKey privateKey, String algorithm, byte[] dataBytes) {
        byte[] signatureBytes = null;

        ASN1ObjectIdentifier oid = null;
        byte[] digestBytes = null;
        if (SIGNATURE_ALGORITHM_SHA1WITHRSA.equals(algorithm)) {
            oid = OID_SHA1;
            digestBytes = DigestUtils.sha1(dataBytes);
        } else if (SIGNATURE_ALGORITHM_SHA256WITHRSA.equals(algorithm)) {
            oid = OID_SHA256;
            digestBytes = DigestUtils.sha256(dataBytes);
        } else if (SIGNATURE_ALGORITHM_SHA512WITHRSA.equals(algorithm)) {
            oid = OID_SHA512;
            digestBytes = DigestUtils.sha512(dataBytes);
        }
        byte[] unsignedData = getUnsignedData(oid, digestBytes);

        signatureBytes = encryptByPrivateKey(privateKey, unsignedData);

        return signatureBytes;
    }

    public static byte[] getUnsignedData(ASN1ObjectIdentifier oid, byte[] dataBytes) {
        byte[] digestBytes = new byte[0];
        if (OID_SHA1.equals(oid)) {
            digestBytes = DigestUtils.sha1(dataBytes);
        } else if (OID_SHA256.equals(oid)) {
            digestBytes = DigestUtils.sha256(dataBytes);
        } else if (OID_SHA512.equals(oid)) {
            digestBytes = DigestUtils.sha512(dataBytes);
        }

        ASN1EncodableVector algVector = new ASN1EncodableVector();
        algVector.add(oid);
        algVector.add(DERNull.INSTANCE);
        DERSequence algSequence = new DERSequence(algVector);

        DEROctetString derOctetString = new DEROctetString(digestBytes);
        ASN1EncodableVector vector = new ASN1EncodableVector();
        vector.add(algSequence);
        vector.add(derOctetString);

        DERSequence sequence = new DERSequence(vector);
        byte[] unsignedData = new byte[0];
        try {
            unsignedData = sequence.getEncoded(ASN1Encoding.DER);
        } catch (IOException e) {
            LOGGER.error("", e);
        }

        return unsignedData;
    }

    /**
     * RSA PKCS1padding 填充模式
     * 
     * @param modulusLength 公钥模长
     * @param dataBytes     数据
     * @return
     */
    public static byte[] rsaPKCS1padding(int modulusLength, byte[] dataBytes) {
        int byteLength = modulusLength / 8;
        int index = 0;
        byte[] bytes = new byte[byteLength];
        bytes[index++] = 0x00;
        // 私钥操作0x00或0x01，公钥操作0x02
        bytes[index++] = 0x01;
        for (int length = byteLength - 1 - dataBytes.length; index < length; index++) {
            bytes[index] = (byte) 0xFF;
        }
        bytes[index++] = 0x00;
        System.arraycopy(dataBytes, 0, bytes, index, dataBytes.length);

        return bytes;
    }

    private static X500Name getSubject(String cn) {
        X500NameBuilder x500NameBuilder = new X500NameBuilder();
        x500NameBuilder.addRDN(BCStyle.C, "CN");
        x500NameBuilder.addRDN(BCStyle.CN, cn);
        X500Name subject = x500NameBuilder.build();
        return subject;
    }

    public static String generateRSACSR(String cn, String modulusHex, BigInteger publicExponent, String privateExponentHex) {
        String csrBase64 = "";
        boolean useHsm = true;

        X500Name subject = getSubject(cn);
        PublicKey publicKey = getRSAPublicKey(modulusHex, publicExponent);
        PrivateKey privateKey = getRSAPrivateKey(modulusHex, privateExponentHex);

        SubjectPublicKeyInfo publicKeyInfo = SubjectPublicKeyInfo.getInstance(publicKey.getEncoded());
        try {
            LOGGER.debug(Utils.bytes2Hex(publicKeyInfo.getEncoded(ASN1Encoding.DER)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (useHsm) {
            CertificationRequestInfo requestInfo = new CertificationRequestInfo(subject, publicKeyInfo, new DERSet());
            AlgorithmIdentifier algorithm = new AlgorithmIdentifier(PKCSObjectIdentifiers.sha1WithRSAEncryption, DERNull.INSTANCE);
            try {
                byte[] signature = rsaSign(new BouncyCastleProvider(), privateKey, SIGNATURE_ALGORITHM_SHA1WITHRSA, requestInfo.getEncoded(ASN1Encoding.DER));

                CertificationRequest certificationRequest = new CertificationRequest(requestInfo, algorithm, new DERBitString(signature));
                PKCS10CertificationRequest pkcs10CertificationRequest = new PKCS10CertificationRequest(certificationRequest);

                StringWriter out = new StringWriter();
                JcaPEMWriter pemWriter = new JcaPEMWriter(out);
                pemWriter.writeObject(pkcs10CertificationRequest);
                pemWriter.close();

                csrBase64 = out.toString();
            } catch (IOException e) {
                LOGGER.error("", e);
            }
        } else {
            PKCS10CertificationRequestBuilder builder = new PKCS10CertificationRequestBuilder(subject, publicKeyInfo);

            JcaContentSignerBuilder jcaContentSignerBuilder = new JcaContentSignerBuilder(SIGNATURE_ALGORITHM_SHA1WITHRSA);
            try {
                ContentSigner contentSigner = jcaContentSignerBuilder.build(privateKey);
                PKCS10CertificationRequest pkcs10CertificationRequest = builder.build(contentSigner);

                StringWriter out = new StringWriter();
                JcaPEMWriter pemWriter = new JcaPEMWriter(out);
                pemWriter.writeObject(pkcs10CertificationRequest);
                pemWriter.close();

                csrBase64 = out.toString();
            } catch (OperatorCreationException e) {
                LOGGER.error("", e);
            } catch (IOException e) {
                LOGGER.error("", e);
            }
        }

        return csrBase64;
    }

    public static SubjectPublicKeyInfo getSubjectPublicKeyInfo4SM2(String publicKeyHex) {
        byte[] publicKeyBytes = Utils.hex2Byte(publicKeyHex);

        ASN1EncodableVector algVector = new ASN1EncodableVector();
        algVector.add(X509CertUtils.OID_ECPUBLICKEY);
        algVector.add(X509CertUtils.SM2P256V1);
        DERSequence algSequence = new DERSequence(algVector);

        DERBitString derBitString = new DERBitString(publicKeyBytes);

        ASN1EncodableVector publicKeyInfoVector = new ASN1EncodableVector();
        publicKeyInfoVector.add(algSequence);
        publicKeyInfoVector.add(derBitString);

        DERSequence publicKeyInfoSequence = new DERSequence(publicKeyInfoVector);

        SubjectPublicKeyInfo publicKeyInfo = SubjectPublicKeyInfo.getInstance(publicKeyInfoSequence);

        return publicKeyInfo;
    }

//    private static byte[] sm2Sign(String publicKeyHex, String privateKeyHex, byte[] dataBytes) {
//        String signatureHex = SM2Util.signByJava(publicKeyHex, privateKeyHex, Utils.bytes2Hex(dataBytes));
//        LOGGER.debug("signature: {}", signatureHex);
//        String signRHex = signatureHex.substring(0, 64);
//        String signSHex = signatureHex.substring(64);
//
//        ASN1Integer signR = new ASN1Integer(new BigInteger(signRHex, 16));
//        ASN1Integer signS = new ASN1Integer(new BigInteger(signSHex, 16));
//        ASN1EncodableVector signatureVector = new ASN1EncodableVector();
//        signatureVector.add(signR);
//        signatureVector.add(signS);
//
//        DERSequence signatureSequence = new DERSequence(signatureVector);
//        byte[] signatureBytes = new byte[0];
//        try {
//            signatureBytes = signatureSequence.getEncoded(ASN1Encoding.DER);
//        } catch (IOException e) {
//            LOGGER.error("", e);
//        }
//
//        return signatureBytes;
//    }

//    public static String generateSM2CSR(String cn, String publicKeyHex, String privateKeyHex) {
//        String csrBase64 = "";
//
//        X500Name subject = getSubject(cn);
//        SubjectPublicKeyInfo publicKeyInfo = getSubjectPublicKeyInfo4SM2("04" + publicKeyHex);
//
//        CertificationRequestInfo requestInfo = new CertificationRequestInfo(subject, publicKeyInfo, new DERSet());
//
//        AlgorithmIdentifier algorithm = new AlgorithmIdentifier(SM2SIGN_WITH_SM3, DERNull.INSTANCE);
//
//        try {
//            byte[] signature = sm2Sign(publicKeyHex, privateKeyHex, requestInfo.getEncoded(ASN1Encoding.DER));
//
//            CertificationRequest certificationRequest = new CertificationRequest(requestInfo, algorithm, new DERBitString(signature));
//            PKCS10CertificationRequest pkcs10CertificationRequest = new PKCS10CertificationRequest(certificationRequest);
//
//            StringWriter out = new StringWriter();
//            JcaPEMWriter pemWriter = new JcaPEMWriter(out);
//            pemWriter.writeObject(pkcs10CertificationRequest);
//            pemWriter.close();
//
//            csrBase64 = out.toString();
//        } catch (IOException e) {
//            LOGGER.error("", e);
//        }
//
//        return csrBase64;
//    }
}
