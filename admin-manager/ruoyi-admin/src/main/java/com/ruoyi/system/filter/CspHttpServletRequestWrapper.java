package com.ruoyi.system.filter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ruoyi.system.exception.CspException;

public class CspHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ByteArrayInputStream buffer;

    public CspHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream in = null;
        try {
            in = request.getInputStream();
            byte[] bytes = new byte[1024];
            int length = 0;
            while ((length = in.read(bytes)) != -1) {
                baos.write(bytes, 0, length);
            }
        } catch (IOException e) {
            logger.error("", e);
            throw new CspException(1006, "请求报文读取错误", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("", e);
                } finally {
                    in = null;
                }
            }
        }

        this.buffer = new ByteArrayInputStream(baos.toByteArray());
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        buffer.reset();

        return new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return buffer.read();
            }

            @Override
            public void setReadListener(ReadListener listener) {
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public boolean isFinished() {
                return false;
            }
        };
    }

    public byte[] getContent() {
        buffer.reset();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] bytes = new byte[1024];
        int length = 0;
        while ((length = buffer.read(bytes, 0, bytes.length)) != -1) {
            baos.write(bytes, 0, length);
        }

        return baos.toByteArray();
    }
}
