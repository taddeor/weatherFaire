package com.weather.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtils {

    public static <T> T create(String resource , Class<T> valueType) throws Exception{
        final ObjectMapper mapper = new ObjectMapper();
        T t = mapper.readValue(JacksonUtils.class.getResourceAsStream(resource), valueType);
        return t;
    }
}
