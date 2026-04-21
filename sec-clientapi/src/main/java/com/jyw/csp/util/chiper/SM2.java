package com.jyw.csp.util.chiper;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;

public class SM2 {
    // 测试参数
    /*
    private static final String[] eccParam = {
            "8542D69E4C044F18E8B92435BF6FF7DE457283915C45517D722EDB8B08F1DFC3",
            "787968B4FA32C3FD2417842E73BBFEFF2F3C848B6831D7E0EC65228B3937E498",
            "63E4C6D3B23B0C849CF84241484BFE48F61D59A5B16BA06E6E12D1DA27C5249A",
            "8542D69E4C044F18E8B92435BF6FF7DD297720630485628D5AE74EE7C32E79B7",
            "421DEBD61B62EAB6746434EBC3CC315E32220B3BADD50BDC4C4E6C147FEDD43D",
            "0680512BCBB42C07D47349D2153B70C4E5D7FDFCBFA36EA1A85841B9E46E09A2"
    };
    */

    // 正式参数
    private static String[] eccParams = {
            "FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFF",
            "FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFC",
            "28E9FA9E9D9F5E344D5A9E4BCF6509A7F39789F515AB8F92DDBCBD414D940E93",
            "FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFF7203DF6B21C6052B53BBF40939D54123",
            "32C4AE2C1F1981195F9904466A39C9948FE30BBFF2660BE1715A4589334C74C7",
            "BC3736A2F4F6779C59BDCEE36B692153D0A9877CC62A474002DF32E52139F0A0"
    };

    public final BigInteger eccP;
    public final BigInteger eccA;
    public final BigInteger eccB;
    public final BigInteger eccGx;
    public final BigInteger eccGy;
    public final BigInteger eccN;
    public final ECPoint eccPointG;
    public final ECCurve eccCurve;
    public final ECDomainParameters eccBcSpec;
    public final ECKeyPairGenerator eccKeyPairGenerator;
    public final ECFieldElement eccGxFieldElement;
    public final ECFieldElement eccGyFieldElement;

    public SM2() {
        this.eccP = new BigInteger(eccParams[0], 16);
        this.eccA = new BigInteger(eccParams[1], 16);
        this.eccB = new BigInteger(eccParams[2], 16);
        this.eccGx = new BigInteger(eccParams[4], 16);
        this.eccGy = new BigInteger(eccParams[5], 16);
        this.eccN = new BigInteger(eccParams[3], 16);

        this.eccCurve = new ECCurve.Fp(this.eccP, this.eccA, this.eccB, null, null);

        this.eccGxFieldElement = this.eccCurve.fromBigInteger(this.eccGx);
        this.eccGyFieldElement = this.eccCurve.fromBigInteger(this.eccGy);

        this.eccPointG = this.eccCurve.createPoint(this.eccGx, this.eccGy);

        this.eccBcSpec = new ECDomainParameters(this.eccCurve, this.eccPointG, this.eccN);

        ECKeyGenerationParameters ecc_ecgenparam = new ECKeyGenerationParameters(this.eccBcSpec, new SecureRandom());

        this.eccKeyPairGenerator = new ECKeyPairGenerator();
        this.eccKeyPairGenerator.init(ecc_ecgenparam);
    }
}
