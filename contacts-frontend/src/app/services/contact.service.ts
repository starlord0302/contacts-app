import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {ContactList} from "../interfaces/contact-list";
import {Contact} from "../interfaces/contact";

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  private apiUrl: string;

  constructor(private http: HttpClient) {
    this.apiUrl = environment.apiBaseUrl;
  }

  getContacts(): Observable<ContactList> {
    return this.http.get<ContactList>(this.apiUrl);
  }

  saveContact(contact: Contact, file: File | null): Observable<Contact> {
    const formData = new FormData();
    formData.append('contactRequest', new Blob([JSON.stringify(contact)], {
      type: "application/json"
    }));
    if (file) {
      formData.append('file', file);
    }
    return this.http.post<Contact>(this.apiUrl, formData);
  }
}
