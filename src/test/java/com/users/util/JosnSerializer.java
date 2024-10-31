package com.users.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public  class JosnSerializer {

    public static ObjectMapper mapper = new ObjectMapper();

    public static String serializeObjectToJson(Object object){

        String jsonInString;
        try {
            jsonInString = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);

        }

        return jsonInString;
    }
}
