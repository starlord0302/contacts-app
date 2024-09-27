package com.kare.contactsbackend.mapper;

import com.kare.contactsbackend.dto.ContactListResponse;
import com.kare.contactsbackend.dto.ContactRequest;
import com.kare.contactsbackend.dto.ContactResponse;
import com.kare.contactsbackend.entity.Contact;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContactMapper {

    public ContactResponse toContactResponse(Contact contact) {
        ContactResponse response = new ContactResponse();
        response.setId(contact.getId());
        response.setFullName(contact.getFullName());
        response.setPhoneNumber(contact.getPhoneNumber());
        response.setEmail(contact.getEmail());
        return response;
    }

    public ContactListResponse toContactListResponse(List<Contact> contacts) {
        ContactListResponse response = new ContactListResponse();
        List<ContactResponse> contactResponses = contacts.stream()
                .map(this::toContactResponse)
                .collect(Collectors.toList());
        response.setContacts(contactResponses);
        return response;
    }

    public Contact toContact(ContactRequest contactRequest) {
        Contact contact = new Contact();
        contact.setFullName(contactRequest.getFullName());
        contact.setPhoneNumber(contactRequest.getPhoneNumber());
        contact.setEmail(contactRequest.getEmail());
        return contact;
    }

}
