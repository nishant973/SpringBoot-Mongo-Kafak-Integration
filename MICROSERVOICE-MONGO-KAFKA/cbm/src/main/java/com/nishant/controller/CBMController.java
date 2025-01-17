package com.nishant.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/cbm")
public class CBMController {
	
	@Value("${rmm.app.url.get}")
	private String url;

	@GetMapping("/getcar/{cartype}")
	public String getCar(@PathVariable("cartype") String carType) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		header.set("Content-Type", "application/json");
		ResponseEntity<String> response = restTemplate.exchange(url + "/" + carType, HttpMethod.GET, 
				new HttpEntity<String>(header), String.class);
		String message =  response.getBody();
		return  message;
	}
}
