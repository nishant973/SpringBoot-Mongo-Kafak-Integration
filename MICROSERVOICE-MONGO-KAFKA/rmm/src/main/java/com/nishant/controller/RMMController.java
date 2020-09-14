package com.nishant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nishant.kafka.Producer;
import com.nishant.model.BodyInfo;
import com.nishant.model.Car;
import com.nishant.model.RawMaterialWrapper;
import com.nishant.service.RMMService;

@RestController
@RequestMapping("/rmm")
public class RMMController {
	
	@Autowired
	private RMMService rmmService;
	
	@Autowired
	private Producer producer;

	@PostMapping("/create")
	public void createTask(@RequestBody BodyInfo rawData) {
		rmmService.create(rawData);
	}
	
	@GetMapping("/get/{type}")
	public ResponseEntity<String> getTask(@PathVariable String type){
		List<BodyInfo> bodyInfos =  rmmService.get(type);
		RawMaterialWrapper materialWrapper = new RawMaterialWrapper();
		materialWrapper.setBodyInfos(bodyInfos);
		Car car = new Car();
		car.setColor("black");
		car.setMaterialWrapper(materialWrapper);
		car.setModel("2000");
		producer.send(car);
		return new ResponseEntity<String>("Car successfully delieverd to broker : ", HttpStatus.OK);
	}
	
//	@GetMapping("/produce/{item}")
//	public String produce(@PathVariable String item){
//		producerTemplate.send("test", item);
//		return "Message has been delivered";
//	}
}
