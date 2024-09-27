package com.kare.contactsbackend.service;

import com.kare.contactsbackend.dto.ContactListResponse;
import com.kare.contactsbackend.dto.ContactRequest;
import com.kare.contactsbackend.dto.ContactResponse;
import com.kare.contactsbackend.entity.Contact;
import com.kare.contactsbackend.exception.custom.ContactNotFoundException;
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

  public ContactResponse saveContact(ContactRequest contactRequest) {
    Contact entity = mapper.toContact(contactRequest);
    return this.mapper.toContactResponse(repository.save(entity));
  }

  public ContactResponse getContact(Long id) {
    Contact contact = repository.findById(id).orElseThrow(() -> new ContactNotFoundException("Contact not found with id: " + id));
    return mapper.toContactResponse(contact);
  }

  public ContactResponse updateContact(Long id, ContactRequest contactRequest) {
    Contact contact = repository.findById(id).orElseThrow(() -> new ContactNotFoundException("Contact not found with id: " + id));
    contact.setFullName(contactRequest.getFullName());
    contact.setPhoneNumber(contactRequest.getPhoneNumber());
    contact.setEmail(contactRequest.getEmail());
    return mapper.toContactResponse(repository.save(contact));
  }

  public void deleteContact(Long id) {
    if (!repository.existsById(id)) {
      throw new ContactNotFoundException("Contact not found with id: " + id);
    }
    repository.deleteById(id);
  }
}
