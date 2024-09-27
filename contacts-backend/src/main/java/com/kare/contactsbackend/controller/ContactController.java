package com.kare.contactsbackend.controller;

import com.kare.contactsbackend.dto.ContactListResponse;
import com.kare.contactsbackend.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

  private final ContactService contactService;

  public ContactController(ContactService contactService) {
    this.contactService = contactService;
  }

  @GetMapping
  public ResponseEntity<ContactListResponse> getAllContacts() {
    return new ResponseEntity<>(contactService.getAllContacts(), HttpStatus.OK);
  }
}
