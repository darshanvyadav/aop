package com.yadav.aop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yadav.aop.domain.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long>{
	

}
