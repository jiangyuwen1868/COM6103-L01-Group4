package com.ruoyi.common.utils.chiper;

public class SM4Context {
    public int mode;
    public long[] sk;
    public Padding padding;

    public SM4Context() {
        this.mode = 1;
        this.padding = Padding.PKCS7PADDING;
        this.sk = new long[32];
    }
}
