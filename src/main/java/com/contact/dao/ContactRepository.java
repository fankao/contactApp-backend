package com.contact.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contact.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
