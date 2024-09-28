import {Component, ContentChild, Input} from '@angular/core';
import {ContactFormComponent} from "../contact-form/contact-form.component";

@Component({
  selector: 'ca-modal',
  templateUrl: './modal.component.html',
  styleUrl: './modal.component.scss'
})
export class ModalComponent {
  @Input() title!: string;
  @ContentChild(ContactFormComponent) contactForm!: ContactFormComponent;

  saveChanges() {
    this.contactForm.onSubmit();
    this.contactForm.reset();
  }

  close() {
    this.contactForm.reset();
  }
}
