import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {ContactList} from "../interfaces/contact-list";

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
}
