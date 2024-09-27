package com.kare.contactsbackend.dto;

import java.util.List;

public class ContactListResponse {
  private List<ContactResponse> contacts;

  public ContactListResponse() {
  }

  public List<ContactResponse> getContacts() {
    return contacts;
  }

  public void setContacts(List<ContactResponse> contacts) {
    this.contacts = contacts;
  }
}
