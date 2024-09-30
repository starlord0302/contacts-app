import {Component, Input} from '@angular/core';
import {Contact} from "../../interfaces/contact";
import {ContactService} from "../../services/contact.service";

@Component({
  selector: 'ca-contact',
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.scss'
})
export class ContactComponent {
  @Input() contact!: Contact;
  show: boolean = false;

  constructor(private contactService: ContactService) {
  }

  openEditMode() {
    this.contactService.emitContact(this.contact);
    this.contactService.editMode.next(true);
  }

  removeContact() {
    this.contactService.deleteContact(this.contact.id).subscribe({
      next: () => {
        this.contactService.emitContactIdToDelete(this.contact.id);
      },
      error: (error) => {
        console.error(error);
      }
    });
  }

  showButtons() {
    this.show = true;
  }

  hideButtons() {
    this.show = false;
  }
}
