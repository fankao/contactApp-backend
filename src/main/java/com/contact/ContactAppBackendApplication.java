package com.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.contact.dao.ContactRepository;
import com.contact.entities.Contact;

@SpringBootApplication
public class ContactAppBackendApplication implements CommandLineRunner {
	@Autowired
	private ContactRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(ContactAppBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 200; i++) {
			repository.save(new Contact("Unknow", "Mr. "+i, i+"@email.com", "000000000"));
		}
		
	}

}
