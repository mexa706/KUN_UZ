/*
package org.example.kun_uzz.config;

import org.springframework.beans.factory.annotation.Autowired;
import  jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecuredFilterConfig {



    @Autowired
    private TokenFilter tokenFilter;

    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(tokenFilter);
        bean.addUrlPatterns("/profile/create");
        bean.addUrlPatterns("/profile/update/*");
        return bean;
    }



}
*/
