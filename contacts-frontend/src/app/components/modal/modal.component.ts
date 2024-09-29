import {Component, ContentChild, Input, OnInit} from '@angular/core';
import {ContactFormComponent} from "../contact-form/contact-form.component";
import {ContactService} from "../../services/contact.service";

@Component({
  selector: 'ca-modal',
  templateUrl: './modal.component.html',
  styleUrl: './modal.component.scss'
})
export class ModalComponent {
  @Input() title!: string;
  @ContentChild(ContactFormComponent) contactForm!: ContactFormComponent;

  constructor(private contactService: ContactService) {
  }

  saveChanges() {
    this.contactForm.onSubmit();
    this.contactForm.reset();
  }

  close() {
    this.contactForm.reset();
    this.contactService.editMode.next(false);
  }
}
