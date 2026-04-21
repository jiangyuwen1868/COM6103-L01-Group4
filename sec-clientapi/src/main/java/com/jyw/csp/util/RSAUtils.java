package com.jyw.csp.util;

public final class RSAUtils {
    public final static String PUBKEY_MODULUS = "modulus";
    public final static String PUBKEY_EXPONENT = "exponent";
    public final static String PUBKEY_LENGTH = "pubKeyLen";

    public static class RSAKeyInfo {
        /**
         * 版本
         */
        private String version;
        /**
         * 公钥模n
         */
        private String modulus;
        /**
         * 公钥指数e
         */
        private String publicExponent;
        /**
         * 私钥指数d
         */
        private String privateExponent;
        
        /**
         * 公钥 + 指数 offset长度
         */
        private int offsetLength;
        /**
         * 参数p
         */
        private String prime1;
        /**
         * 参数q
         */
        private String prime2;
        /**
         * d mod (p - 1)
         */
        private String exponent1;
        /**
         * d mod (q - 1)
         */
        private String exponent2;
        /**
         * (inverse of q) mod p
         */
        private String coefficient;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getModulus() {
            return modulus;
        }

        public void setModulus(String modulus) {
            this.modulus = modulus;
        }

        public String getPublicExponent() {
            return publicExponent;
        }

        public void setPublicExponent(String publicExponent) {
            this.publicExponent = publicExponent;
        }

        public String getPrivateExponent() {
            return privateExponent;
        }

        public void setPrivateExponent(String privateExponent) {
            this.privateExponent = privateExponent;
        }


		public int getOffsetLength() {
			return offsetLength;
		}

		public void setOffsetLength(int offsetLength) {
			this.offsetLength = offsetLength;
		}

		public String getPrime1() {
            return prime1;
        }

        public void setPrime1(String prime1) {
            this.prime1 = prime1;
        }

        public String getPrime2() {
            return prime2;
        }

        public void setPrime2(String prime2) {
            this.prime2 = prime2;
        }

        public String getExponent1() {
            return exponent1;
        }

        public void setExponent1(String exponent1) {
            this.exponent1 = exponent1;
        }

        public String getExponent2() {
            return exponent2;
        }

        public void setExponent2(String exponent2) {
            this.exponent2 = exponent2;
        }

        public String getCoefficient() {
            return coefficient;
        }

        public void setCoefficient(String coefficient) {
            this.coefficient = coefficient;
        }

        public int getModulusLength() {
            return modulus.length() / 2;
        }
    }

    /**
     * 获取公钥信息
     * 
     * @param publicKeyData DER编码公钥信息
     * @return
     */
    public static RSAKeyInfo getPublicKeyInfo(byte[] publicKeyData) {
        // 获取公钥模和公钥指数
        int offset = 0;
        // 1字节公钥信息记录头，十六进制'30'
        offset += 1;
        // 数据长度
        // i = (公钥模长 / 8) + 1
        // 如果i<128，使用1字节记录公钥信息除记录头外剩余信息字节长度
        // 如果128<i<256，使用2字节记录公钥信息除记录头外剩余信息字节长度，1字节为十六进制'81'，1字节为长度
        // 如果256<i<65536，使用3字节记录公钥信息除记录头外剩余信息字节长度，1字节为十六进制'82'，2字节为长度
        // 如果65536<i<16777216，使用4字节记录公钥信息除记录头外剩余信息字节长度，1字节为十六进制'83'，3字节为长度
        // 如果i>16777216，使用5字节记录公钥信息除记录头外剩余信息字节长度，1字节为十六进制'84'，4字节为长度
        // 当前公钥长度支持1024，1152，1408，1984，取128<i<256，即2字节
        byte[] temp = new byte[1];
        System.arraycopy(publicKeyData, offset, temp, 0, temp.length);
        int i = Utils.byteArrayToInt(temp);
        if (i < 128) {
            offset += 1;
        } else if (i == 0x81) {
            offset += 2;
        } else if (i == 0x82) {
            offset += 3;
        } else if (i == 0x83) {
            offset += 4;
        } else {
            offset += 5;
        }
        // 1字节信息分隔，十六进制'02'
        offset += 1;
        // 公钥长度信息，参考以上注释
        temp = new byte[1];
        System.arraycopy(publicKeyData, offset, temp, 0, temp.length);
        i = Utils.byteArrayToInt(temp);
        int mLength = i;
        if (i < 128) {
            offset += 1;
        } else if (i == 0x81) {
            offset += 1;
            temp = new byte[1];
            System.arraycopy(publicKeyData, offset, temp, 0, temp.length);
            mLength = Utils.byteArrayToInt(temp);
            offset += 1;
        } else if (i == 0x82) {
            offset += 1;
            temp = new byte[2];
            System.arraycopy(publicKeyData, offset, temp, 0, temp.length);
            mLength = Utils.byteArrayToInt(temp);
            offset += 2;
        } else if (i == 0x83) {
            offset += 1;
            temp = new byte[3];
            System.arraycopy(publicKeyData, offset, temp, 0, temp.length);
            mLength = Utils.byteArrayToInt(temp);
            offset += 3;
        } else {
            offset += 1;
            temp = new byte[4];
            System.arraycopy(publicKeyData, offset, temp, 0, temp.length);
            mLength = Utils.byteArrayToInt(temp);
            offset += 4;
        }
        // 由于公钥模记录时使用长度为(公钥模长 / 8) + 1的byte数组存储，并且从后往前写，因此第一个字节为0，即十六进制'00'
        offset += 1;
        mLength -= 1;
        temp = new byte[mLength];
        System.arraycopy(publicKeyData, offset, temp, 0, temp.length);
        String m = Utils.bytes2Hex(temp);
        offset += mLength;
        // 1字节信息分隔，十六进制'02'
        offset += 1;
        // 1字节记录公钥指数长度
        temp = new byte[1];
        System.arraycopy(publicKeyData, offset, temp, 0, temp.length);
        int eLength = Utils.byteArrayToInt(temp);
        offset += 1;
        // 公钥指数
        temp = new byte[eLength];
        System.arraycopy(publicKeyData, offset, temp, 0, temp.length);
        String e = Utils.bytes2Hex(temp);

        RSAKeyInfo rsaKeyInfo = new RSAKeyInfo();
        rsaKeyInfo.setModulus(m);
        rsaKeyInfo.setPublicExponent(e);
        rsaKeyInfo.setOffsetLength(offset + eLength);
        return rsaKeyInfo;
    }

    /**
     * @param publicKeyData DER编码公钥信息
     * @return
     */
    public static RSAKeyInfo getPublicKeyInfo(String publicKeyData) {
        return getPublicKeyInfo(Utils.hex2Byte(publicKeyData));
    }
}
