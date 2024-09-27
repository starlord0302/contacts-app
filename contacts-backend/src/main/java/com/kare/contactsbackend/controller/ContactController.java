package com.kare.contactsbackend.controller;

import com.kare.contactsbackend.dto.ContactListResponse;
import com.kare.contactsbackend.dto.ContactRequest;
import com.kare.contactsbackend.dto.ContactResponse;
import com.kare.contactsbackend.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

  private final ContactService contactService;

  public ContactController(ContactService contactService) {
    this.contactService = contactService;
  }

  @GetMapping
  public ResponseEntity<ContactListResponse> getAllContacts() {
    return new ResponseEntity<>(this.contactService.getAllContacts(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<ContactResponse> createContact(@RequestBody ContactRequest contactRequest) {
    ContactResponse contactResponse = this.contactService.saveContact(contactRequest);
    return new ResponseEntity<>(contactResponse, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ContactResponse> getSingleContact(@PathVariable Long id) {
    return new ResponseEntity<>(this.contactService.getContact(id), HttpStatus.OK);
  }
}
