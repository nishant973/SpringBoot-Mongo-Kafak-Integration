package com.nishant.events;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.util.ReflectionUtils;

public class CascadeCallBack implements ReflectionUtils.FieldCallback {

	private Object source;
	private MongoOperations mongoOperations;
	
	CascadeCallBack(final Object source , final MongoOperations mongoOperations){
		this.source = source;
		this.mongoOperations = mongoOperations;
	}
	
	@Override
	public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
		ReflectionUtils.makeAccessible(field);
		if(field.isAnnotationPresent(DBRef.class) && field.isAnnotationPresent(CascadeSave.class)) {
			final Object fieldValue = field.get(source);
			if(null != fieldValue) {
				FieldCallBack callBack = new FieldCallBack();
				ReflectionUtils.doWithFields(fieldValue.getClass(), callBack);
				if(fieldValue instanceof List<?>) {
					for(Object object : (List<?>) fieldValue) {
						mongoOperations.save(object);
					}
				}else {
					mongoOperations.save(fieldValue);
				}
			}
		}
	}

}
