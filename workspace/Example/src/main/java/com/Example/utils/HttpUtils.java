package com.Example.utils;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtils {
	private String value;
	public HttpUtils(String value) {
		this.value = value;
	}	
	public <T> T toModel(Class<T> tname) {
		try {
			 ObjectMapper objectMapper = new ObjectMapper();
			 objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			 objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			 return objectMapper.readValue(value, tname);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;		
	}
	public static HttpUtils of(BufferedReader br) { // convert json from client give data to server to String json to mapping model
		StringBuilder sb = new StringBuilder();
		try {
			String line;
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
		}catch(IOException e) {
			System.out.print(e.getMessage());
		}
		return new HttpUtils(sb.toString());
	}

}