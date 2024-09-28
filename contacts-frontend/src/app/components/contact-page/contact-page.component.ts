import {Component, OnInit} from '@angular/core';
import {ContactService} from "../../services/contact.service";
import {Contact} from "../../interfaces/contact";

@Component({
  selector: 'ca-contact-page',
  templateUrl: './contact-page.component.html',
  styleUrl: './contact-page.component.scss'
})
export class ContactPageComponent implements OnInit {
  contacts: Contact[] = [];

  constructor(private contactService: ContactService) {
  }

  ngOnInit(): void {
    this.fetchAllContacts();
  }

  fetchAllContacts(): void {
    this.contactService.getContacts().subscribe( {
      next: (data) => {
        this.contacts = data.contacts;
        console.log(this.contacts);
      },
      error: (error) => {
        console.error(error);
      }
    });
  }
}
