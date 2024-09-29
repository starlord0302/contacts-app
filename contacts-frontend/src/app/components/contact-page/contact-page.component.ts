import {Component, OnInit} from '@angular/core';
import {ContactService} from "../../services/contact.service";
import {Contact} from "../../interfaces/contact";
import {Observable} from "rxjs";

@Component({
  selector: 'ca-contact-page',
  templateUrl: './contact-page.component.html',
  styleUrl: './contact-page.component.scss'
})
export class ContactPageComponent implements OnInit {
  contacts: Contact[] = [];
  newContact: Contact | null = null;
  editMode$!: Observable<boolean>;

  constructor(private contactService: ContactService) {
  }

  ngOnInit(): void {
    this.fetchAllContacts();
    this.editMode$ = this.contactService.getEditMode();
  }

  fetchAllContacts(): void {
    this.contactService.getContacts().subscribe({
      next: (data) => {
        this.contacts = data.contacts;
      },
      error: (error) => {
        console.error(error);
      }
    });
  }

  updateNewContact(contact: Contact | null): void {
    if (!contact) {
      return;
    }

    const existingContactIndex = this.contacts.findIndex((c) => c.id === contact.id);

    if (existingContactIndex >= 0) {
      this.contacts = this.contacts.map((c) => {
        if (c.id === contact.id) {
          return contact;
        }
        return c;
      });
    } else {
      this.contacts = [...this.contacts, contact];
    }

    this.newContact = contact;
  }
}
