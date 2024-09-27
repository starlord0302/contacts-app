package com.kare.contactsbackend.service;

import com.kare.contactsbackend.dto.ContactListResponse;
import com.kare.contactsbackend.entity.Contact;
import com.kare.contactsbackend.mapper.ContactMapper;
import com.kare.contactsbackend.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

  private final ContactRepository repository;
  private final ContactMapper mapper;

  public ContactService(ContactRepository repository, ContactMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public ContactListResponse getAllContacts() {
    List<Contact> contacts = repository.findAll();
    return mapper.toContactListResponse(contacts);
  }
}
