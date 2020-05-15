package com.yadav.aop.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Passenger {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private Boolean boookingStaatus;
	
	@Override
	public String toString() {
		ObjectMapper objmaper = new ObjectMapper();
		try {
			return objmaper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		return "error in objectMapper" ;
		
	}

}
