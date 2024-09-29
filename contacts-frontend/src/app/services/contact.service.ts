import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {BehaviorSubject, Observable, Subject} from "rxjs";
import {ContactList} from "../interfaces/contact-list";
import {Contact} from "../interfaces/contact";

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  private apiUrl: string;

  contactSubject: BehaviorSubject<Contact | null> = new BehaviorSubject<Contact | null>(null);
  editMode: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

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

  updateContact(id: number, contact: Contact, file: File | null): Observable<Contact> {
    const formData = new FormData();
    formData.append('contactRequest', new Blob([JSON.stringify(contact)], {
      type: "application/json"
    }));
    if (file) {
      formData.append('file', file);
    }
    return this.http.put<Contact>(`${this.apiUrl}/${id}`, formData);
  }

  emitContact(contact: Contact) {
    this.contactSubject.next(contact);
  }

  getEditMode(): Observable<boolean> {
    return this.editMode.asObservable();
  }

}
