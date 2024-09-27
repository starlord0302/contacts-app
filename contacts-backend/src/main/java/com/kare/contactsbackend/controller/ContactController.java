package com.kare.contactsbackend.controller;

import com.kare.contactsbackend.dto.ContactListResponse;
import com.kare.contactsbackend.dto.ContactRequest;
import com.kare.contactsbackend.dto.ContactResponse;
import com.kare.contactsbackend.service.ContactService;
import com.kare.contactsbackend.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

  private final ContactService contactService;
  private final FileService fileService;

  public ContactController(ContactService contactService, FileService fileService) {
    this.contactService = contactService;
    this.fileService = fileService;
  }

  @GetMapping
  public ResponseEntity<ContactListResponse> getAllContacts() {
    return new ResponseEntity<>(this.contactService.getAllContacts(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<ContactResponse> createContact(@RequestPart ContactRequest contactRequest, @RequestPart(value = "file", required = false) MultipartFile file) {
    String imageUrl = "";
    if (file != null) {
      imageUrl = this.fileService.uploadFile(file);
    }
    ContactResponse contactResponse = this.contactService.saveContact(contactRequest, imageUrl);
    return new ResponseEntity<>(contactResponse, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ContactResponse> getSingleContact(@PathVariable Long id) {
    return new ResponseEntity<>(this.contactService.getContact(id), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ContactResponse> updateContact(@PathVariable Long id, @RequestPart ContactRequest contactRequest, @RequestPart(value = "file", required = false) MultipartFile file) {
    String imageUrl = "";
    if (file != null) {
      imageUrl = this.fileService.uploadFile(file);
    }
    return new ResponseEntity<>(this.contactService.updateContact(id, contactRequest, imageUrl), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
    this.contactService.deleteContact(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
