package com.starriddle.starter.springboot.feign.server.config;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Iterator;
import java.util.List;

/**
 * @author CYL
 * @date 2018-04-27
 */
//@Configuration
//@EnableWebMvc
public class MessageConverterConfig implements WebMvcConfigurer {
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        Iterator<HttpMessageConverter<?>> iterator = converters.iterator();
        while (iterator.hasNext()){
            HttpMessageConverter<?> httpMessageConverter = iterator.next();
            if (httpMessageConverter instanceof MappingJackson2HttpMessageConverter){
                iterator.remove();
            }else if (httpMessageConverter instanceof GsonHttpMessageConverter){
                iterator.remove();
            }
        }
        converters.add(new GsonHttpMessageConverter());
    }
}
