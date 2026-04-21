package com.ruoyi.system.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class CspHttpServletResponseWrapper extends HttpServletResponseWrapper {
    private ByteArrayOutputStream buffer;
    private ServletOutputStream out;

    public CspHttpServletResponseWrapper(HttpServletResponse response) {
        super(response);

        this.buffer = new ByteArrayOutputStream();
        this.out = new ServletOutputStream() {
            @Override
            public void write(int b) throws IOException {
                buffer.write(b);
            }

            @Override
            public void setWriteListener(WriteListener listener) {
            }

            @Override
            public boolean isReady() {
                return false;
            }
        };
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return out;
    }

    @Override
    public void flushBuffer() throws IOException {
        if (out != null) {
            out.flush();
        }
    }

    public byte[] getContent() throws IOException {
        flushBuffer();
        return buffer.toByteArray();
    }
}
