package com.kare.contactsbackend.exception.custom;

public class ContactNotFoundException extends RuntimeException {
  public ContactNotFoundException(String message) {
    super(message);
  }
}
