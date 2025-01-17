package com.nishant.events;

import java.lang.reflect.Field;

import org.springframework.data.annotation.Id;
import org.springframework.util.ReflectionUtils;

public class FieldCallBack implements ReflectionUtils.FieldCallback{
	
	private boolean idFound;

	@Override
	public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
		ReflectionUtils.makeAccessible(field);
		if(field.isAnnotationPresent(Id.class)) {
			idFound = true;
		}
	}
	
	public boolean isIdFound() {
		return idFound;
	}

}
