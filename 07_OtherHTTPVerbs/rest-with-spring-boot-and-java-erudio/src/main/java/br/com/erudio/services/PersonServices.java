package br.com.erudio.services;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.erudio.model.Person;

@Service
public class PersonServices {

	private static AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	// aqui utilizando o Logger em vez de Log4J pq na epoce do curso ele estava com problemas
	
	
	public Person findById(String id) {
		
		logger.info("Finding one person!");
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Leonard");
		person.setLastName("Silva");
		person.setAddress("Test 123");
		person.setGender("Male");
		return person;
	}
}
