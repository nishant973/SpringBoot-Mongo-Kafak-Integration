package com.nishant.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.nishant.events.CascadeSaveEventListener;

@Configuration
@EnableConfigurationProperties(MongoProperties.class)
public class MongoConfiguration {	
	
	private MongoProperties mongoProperties;
	
	public MongoConfiguration(MongoProperties mongoProperties) {
		this.mongoProperties = mongoProperties;
	}
	
	
	@Bean(name = CarMongoConfiguration.MONGO_TEMPLATE_REF)
	@Primary
	public MongoTemplate carMongoTemplate() {
		
		return new MongoTemplate(mongoClient(), mongoProperties.getDatabase());
	}
	
	
	
	@Bean
	public MongoClient mongoClient() {
//		StringBuilder connectionString = new StringBuilder("mongodb");
//		connectionString.append("://");
//		connectionString.append(mongoProperties.getHost());
//		connectionString.append(":");
//		connectionString.append(mongoProperties.getPort());
		return MongoClients.create(mongoProperties.getConnectionString());
	}
	
	@Bean
	public CascadeSaveEventListener cascadeSaveEventListener() {
		return new CascadeSaveEventListener();
	}
}
