package com.zl.property.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonObjectUtils {
	
	private JsonObjectUtils(){
	}
	
	
	/**
	 * 将json 转化为对象
	 * 
	 * @param json
	 * @param obj
	 * @return
	 */
	public static Object jsonToObject(String json, Object obj) {
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			return objectMapper.readValue(json, obj.getClass());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将对象转化为json
	 * 
	 * @param obj
	 * @return
	 * @author YixinCapital -- chenyuan1 2016年3月2日 上午10:33:02
	 */
	public static String objectToJson(Object obj) {
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * json转Map
	 * 
	 * @param json
	 * @return
	 * @author YixinCapital -- chenyuan1 2016年5月23日 下午8:44:59
	 */
	public static Map<String, String> jsonToMap(String json) {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> map = new HashMap<String, String>();

		try {
			map = objectMapper.readValue(json, new TypeReference<Map<String, String>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * json数组转化为对象数组
	 * 
	 * @param json
	 * @return 
	 * @author YixinCapital -- chenyuan1
	 *	       2016年5月24日 上午10:08:52
	 */
	public static List<Object> jsonArrayToObjects(String json, Object obj) {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Object> result = new ArrayList<>();
		com.fasterxml.jackson.databind.JavaType javaType = getCollectionType(ArrayList.class, obj.getClass());
		try {
			result = (List<Object>) objectMapper.readValue(json, javaType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@SuppressWarnings("deprecation")
	public static com.fasterxml.jackson.databind.JavaType getCollectionType(Class<?> collectionClass,
			Class<?>... elementClasses) {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}

}
