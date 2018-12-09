package com.starriddle.starter.springboot.feign.client.util;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestTemplate;
import feign.jackson.JacksonEncoder;

import java.lang.reflect.Type;
import java.time.LocalDate;

/**
 * @author CYL
 * @date 2018-04-27
 */

public class MyJacksonEncoder extends JacksonEncoder {
    public MyJacksonEncoder() {
        super();
    }

    public MyJacksonEncoder(Iterable<Module> modules) {
        super(modules);
    }

    public MyJacksonEncoder(ObjectMapper mapper) {
        super(mapper);
    }

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) {
        if (String.class.equals(bodyType)) {
            // String不进行json化，直接传输，因服务端默认直接读取
            template.body(object.toString());
        } else if (LocalDate.class.equals(bodyType)) {
            // localDate直接传输String
            // TODO 服务端解码需要设置，默认解码不支持
            template.body(object.toString());
        } else {
            super.encode(object, bodyType, template);
        }
    }
}
