package com.yadav.aop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yadav.aop.annotation.LogInput;
import com.yadav.aop.annotation.LogOutput;
import com.yadav.aop.domain.Passenger;
import com.yadav.aop.repository.PassengerRepository;

@RestController
public class AopController {

	
	@Autowired
	PassengerRepository passengerRepo;
	
	
	
	@GetMapping(value = "/getPassenger/{id}")
	public Passenger getPassenger(@PathVariable long id) {
		Optional<Passenger> passenger = passengerRepo.findById(id);
		if (passenger.isPresent()) {
			serviceCallToCheckLogging(passenger.get());
		}
		return passenger.get();
	}
	
	@LogInput
	@LogOutput
	@PostMapping(value = "/savePassenger")
	public Passenger savePassenger(@RequestBody Passenger pasger) {
		Passenger save = passengerRepo.save(pasger);
        return save;
	}
	
	@GetMapping("/getAllPAssenger")
	public List<Passenger> getAllPassengers() {
		return passengerRepo.findAll();		
	}
	
	@LogInput
	@LogOutput
	public Passenger serviceCallToCheckLogging(Passenger passenger) {
		passenger.setBoookingStaatus(false);
		passenger.setFirstName("Test");
		passenger.setId(Long.valueOf(3333));
		passenger.setLastName("Test");
		return passenger;
	}
	
	
}
