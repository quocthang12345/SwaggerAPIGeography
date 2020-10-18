package com.Example.config;

import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

@Configuration
public class MongoConfig {
	
	 	@Bean
	    public MongoDbFactory mongoDbFactory() throws UnknownHostException {
	        return new SimpleMongoDbFactory(new MongoClient("localhost", 27017), "example");
	    }

	    @Bean
	    public MongoTemplate mongoTemplate() throws UnknownHostException {
	        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
	        return mongoTemplate;
	    }
}
 