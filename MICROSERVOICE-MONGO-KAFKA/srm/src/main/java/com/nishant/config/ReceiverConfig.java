package com.nishant.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.nishant.model.Car;

@Configuration
@EnableKafka
public class ReceiverConfig {
	
	@Value("${kafka.bootstrap.server}")
	private String bootStrapServer;

	public Map<String , Object> consumerConfig(){
		Map<String , Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG , "json");
		return props;
	}
	
	//@Bean
	@ConditionalOnMissingBean(ConsumerFactory.class)
	public ConsumerFactory<String, Car> consumerFactory(){
		return new DefaultKafkaConsumerFactory<>(
				consumerConfig() , new StringDeserializer() , new JsonDeserializer<>(Car.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Car> kafkaListenerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<String, Car> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	} 
}
