import {Component, Input} from '@angular/core';
import {Contact} from "../../interfaces/contact";

@Component({
  selector: 'ca-contact',
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.scss'
})
export class ContactComponent {
  @Input() contact!: Contact;
}
