package com.nishant.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.nishant.model.Car;

@Service
public class Producer {

	@Value("${kafka-topic}")
	private String topic;
	
	@Autowired
	private KafkaTemplate<String, Car> kafkaTemplate;
	
	public void send(Car car) {
		kafkaTemplate.send(topic , car);
	}
}
