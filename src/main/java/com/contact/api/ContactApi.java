package com.contact.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.contact.dao.ContactRepository;
import com.contact.entities.Contact;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin("https://contact-angular-frontend.herokuapp.com")
public class ContactApi {
	@Autowired
	private ContactRepository service;

	@GetMapping()
	public ResponseEntity<Page<Contact>> getContacts(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		return ResponseEntity.ok(service.findAll(PageRequest.of(page, size, Sort.by("lastUpdated").descending())));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Contact> getContactById(@PathVariable("id") Long id) {
		Optional<Contact> findedContact = service.findById(id);
		if (findedContact.isPresent()) {
			return ResponseEntity.ok(findedContact.get());
		}
		return ResponseEntity.notFound().build();
	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	public ResponseEntity<Contact> saveContact(@RequestBody Contact contact) {
		System.out.println("Contact info: " + contact);
		service.save(contact);
		// Tạo URI lưu thông tin cho todo
		// URI location =
		// ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(contact.getId()).toUri();
		return new ResponseEntity<Contact>(contact, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Contact> deleteContact(@PathVariable("id") Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
