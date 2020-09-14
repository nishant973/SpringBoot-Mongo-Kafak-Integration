package com.nishant.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.nishant.repository" , 
						mongoTemplateRef = CarMongoConfiguration.MONGO_TEMPLATE_REF)
public class CarMongoConfiguration {

	public static final String MONGO_TEMPLATE_REF = "myMongoTemplate";
}
