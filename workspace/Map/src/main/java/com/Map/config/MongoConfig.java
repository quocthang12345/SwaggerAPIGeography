package com.Map.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoTypeMapper;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.Map.model.Model;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
@EnableMongoRepositories(basePackages = "com.Map.repository")
public class MongoConfig {
	

	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
		MongoCredential credential = MongoCredential.createCredential("map", "map", "quocthang123".toCharArray());

		ServerAddress serverAddress = new ServerAddress("localhost", 27017);

		MongoClient mongoClient = new MongoClient(serverAddress, Arrays.asList(credential));

		// Mongo DB Factory
		SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(mongoClient, "map");

		return simpleMongoDbFactory;
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {

		return new MongoTemplate(mongoDbFactory(), mappingMongoConverter());
	}

	@Bean
	public MappingMongoConverter mappingMongoConverter() throws Exception {
		MongoTypeMapper typeMapper = new DefaultMongoTypeMapper(null);
		MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory()),
				new MongoMappingContext());
		converter.setTypeMapper(typeMapper);
		List<Converter<Model, BasicDBObject>> convert = new ArrayList<>();
		converter.setCustomConversions(new CustomConversions(convert));
		return converter;
	}

}
