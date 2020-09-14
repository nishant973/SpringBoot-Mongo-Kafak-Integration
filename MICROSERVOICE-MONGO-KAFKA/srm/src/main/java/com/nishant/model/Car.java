package com.nishant.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Car {

	private String color;
		
	private String model;
		
	private RawMaterialWrapper materialWrapper;
		
}
