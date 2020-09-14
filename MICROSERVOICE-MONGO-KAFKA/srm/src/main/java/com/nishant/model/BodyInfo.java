package com.nishant.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BodyInfo {

	private String id;
	
	private String type;
	
	private String thickness;
	
	// A pointer to a documnet , like foreign key in relation databases
	private List<EngineInfo> engine;
}