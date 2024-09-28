import {Component, Input} from '@angular/core';
import {Contact} from "../../interfaces/contact";

@Component({
  selector: 'ca-contact-list',
  templateUrl: './contact-list.component.html',
  styleUrl: './contact-list.component.scss'
})
export class ContactListComponent {
  @Input() contacts!: Contact[];

}
