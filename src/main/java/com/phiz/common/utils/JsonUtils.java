package com.phiz.common.utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonUtils {

    // 定义jackson对象
	// 设置在反序列化时忽略在JSON字符串中存在，而在Java中不存在的属性
    private static final ObjectMapper MAPPER = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);;

    public static String objectToJson(Object data) {
    	try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    @SuppressWarnings("unchecked")
	public static Map<String, String> jsonToMap(String data){
    	try {
			return MAPPER.readValue(data, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		} 
    	return null;
    }
    
    /**
     * 将json结果集转化为对象
     * 
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 将json数据转换成pojo对象list
     */
    public static <T>List<T> jsonToList(String jsonData, Class<T> beanType) {
    	JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
    	try {
    		List<T> list = MAPPER.readValue(jsonData, javaType);
    		return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return null;
    }
    
    public static JsonNode jsonToNode(String jsonData){
    	try {
			return MAPPER.readTree(jsonData);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
    }
}
