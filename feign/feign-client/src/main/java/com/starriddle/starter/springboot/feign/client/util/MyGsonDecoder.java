package com.starriddle.starter.springboot.feign.client.util;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import feign.Response;
import feign.Util;
import feign.gson.GsonDecoder;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static feign.Util.ensureClosed;

public class MyGsonDecoder extends GsonDecoder {

    private static List<Type> types = Arrays.asList(String.class, LocalDate.class);

    public MyGsonDecoder(Iterable<TypeAdapter<?>> adapters) {
        super(adapters);
    }

    public MyGsonDecoder() {
        super();
    }

    public MyGsonDecoder(Gson gson) {
        super(gson);
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
                    return Util.toString(reader);
                }
                if (LocalDate.class.equals(type)) {
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