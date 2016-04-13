package com.felix.raspi.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by fsoewito on 4/7/2016.
 */
public class JsonUtil {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String toJson(Object o) throws IOException{
        return OBJECT_MAPPER.writeValueAsString(o);
    }
}
