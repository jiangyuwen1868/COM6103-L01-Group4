package com.ruoyi.system.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TxFilterConfig {
    @Bean
    public FilterRegistrationBean<TxAccessFilter> tradeLogFilter() {
        FilterRegistrationBean<TxAccessFilter> filterRegistrationBean = new FilterRegistrationBean<TxAccessFilter>();
        filterRegistrationBean.setFilter(new TxAccessFilter());
        filterRegistrationBean.setName("txAccessFilter");
        filterRegistrationBean.addUrlPatterns("/manager/tx");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}
