package com.qiqv.music.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * 使用jackon封装的一个工具类
 */
public class JSONUtils {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 将对象转为JSON字符串
     * @param object
     * @return
     */
    public static String objectToJson(Object object){
        String result;
        try {
            result =OBJECT_MAPPER.writeValueAsString(object);
            return result;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static<T> Object jsonToObject(String json, Class<T> beanType){
        T t;
        try {
          t =  OBJECT_MAPPER.readValue(json,beanType);
          return t;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static<T> List<T> jsonToList(String json, Class<T> beanType){
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(List.class,beanType);
        try {
            List<T> list = OBJECT_MAPPER.readValue(json,javaType);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
