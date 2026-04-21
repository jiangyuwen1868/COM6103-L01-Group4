package com.ruoyi.system.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import com.ruoyi.system.context.SessionContext;

public class TxAccessFilter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(getClass());


    

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.debug("==========TxAccessFilter->doFilter begin");

        SessionContext context = SessionContext.getCurrentContext();
        context.setSysRecvTime(System.currentTimeMillis());
        context.setStopWatch(new StopWatch("costTimeInfo"));

        CspHttpServletRequestWrapper requestWrapper = new CspHttpServletRequestWrapper((HttpServletRequest) request);
        CspHttpServletResponseWrapper responseWrapper = new CspHttpServletResponseWrapper((HttpServletResponse) response);

        try {

            chain.doFilter(requestWrapper, responseWrapper);


            ServletOutputStream out = response.getOutputStream();
            out.write(responseWrapper.getContent());
            out.flush();
        } finally {
            SessionContext.getCurrentContext().release();

            logger.debug("==========TxAccessFilter->doFilter finish");
        }
    }
}
