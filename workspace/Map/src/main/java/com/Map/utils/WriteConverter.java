package com.Map.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import com.Map.model.Model;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@WritingConverter
public class WriteConverter implements Converter<Model, DBObject> {
	@Override
	public DBObject convert(Model source) {
		DBObject dbo = new BasicDBObject();
	    dbo.put("_id", source.get_id());
	    return dbo;
	}
}