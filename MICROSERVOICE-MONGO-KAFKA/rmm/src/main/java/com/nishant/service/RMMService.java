package com.nishant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nishant.model.BodyInfo;
import com.nishant.repository.BodyRepository;

@Service
public class RMMService {

	@Autowired
	private BodyRepository bodyRepository;
	
	public void create(BodyInfo rawData) {
		bodyRepository.save(rawData);
	}
	
	public List<BodyInfo> get(String type) {
		return bodyRepository.findByType(type);
	}
}
