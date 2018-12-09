package com.starriddle.starter.springboot.feign.client.util;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.Util;
import feign.jackson.JacksonDecoder;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static feign.Util.ensureClosed;

public class MyJacksonDecoder extends JacksonDecoder {

    private static List<Type> types = Arrays.asList(String.class, LocalDate.class);

    public MyJacksonDecoder() {
        super();
    }

    public MyJacksonDecoder(Iterable<Module> modules) {
        super(modules);
    }

    public MyJacksonDecoder(ObjectMapper mapper) {
        super(mapper);
    }

    @Override
    public Object decode(Response response, Type type) throws IOException {
        if (response.status() == 404) {
            return Util.emptyValueOf(type);
        }
        if (response.body() == null) {
            return null;
        }
        if (types.contains(type)){
            Reader reader = response.body().asReader();
            try {
                if (String.class.equals(type)) {
                    // 处理服务端直接返回的未json化的String
                    return Util.toString(reader);
                }
                if (LocalDate.class.equals(type)) {
                    // 处理服务端返回的json化的LocalDate
                    String value = Util.toString(reader);
                    System.out.println(value);
                    return LocalDate.parse(value.substring(1,value.length()-1));
                }
            } finally {
                ensureClosed(reader);
            }
        }
        return super.decode(response, type);
    }

}