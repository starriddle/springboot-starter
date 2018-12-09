package com.starriddle.starter.springboot.feign.client.util;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import feign.RequestTemplate;
import feign.gson.GsonEncoder;

import java.lang.reflect.Type;
import java.time.LocalDate;

/**
 * @author CYL
 * @date 2018-04-27
 */

public class MyGsonEncoder extends GsonEncoder {

    public MyGsonEncoder(Iterable<TypeAdapter<?>> adapters) {
        super(adapters);
    }

    public MyGsonEncoder() {
        super();
    }

    public MyGsonEncoder(Gson gson) {
        super(gson);
    }

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) {
        if (String.class.equals(bodyType)) {
            template.body(object.toString());
        }else if (LocalDate.class.equals(bodyType)) {
            template.body(object.toString());
        } else {
            super.encode(object, bodyType, template);
        }
    }
}
