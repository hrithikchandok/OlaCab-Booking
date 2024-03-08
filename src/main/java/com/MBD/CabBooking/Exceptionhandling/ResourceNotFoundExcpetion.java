package com.MBD.CabBooking.Exceptionhandling;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundExcpetion extends RuntimeException {
	  String resourceName;
	   String fieldName;
	   long fieldValue;
	public  ResourceNotFoundExcpetion(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s :%s",resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	public  ResourceNotFoundExcpetion(String message) {
		super(message);
//		this.resourceName = resourceName;
//		this.fieldName = fieldName;
//		this.fieldValue = fieldValue;
	}
}
