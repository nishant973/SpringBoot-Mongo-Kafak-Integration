package com.nishant.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.nishant.model.Car;

@Service
public class Consumer {

	@KafkaListener(topics = "test" , containerFactory = "kafkaListenerContainerFactory")
	public void consumeMessage(Car car) {
		System.out.println("car receiver at show room :" + car);
	}
}
