import {Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {ContactService} from "../../services/contact.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Contact} from "../../interfaces/contact";

@Component({
  selector: 'ca-contact-form',
  templateUrl: './contact-form.component.html',
  styleUrl: './contact-form.component.scss'
})
export class ContactFormComponent implements OnInit {

  @Input() newContact: Contact | null = null;
  @Output() contactSaved = new EventEmitter<Contact | null>();
  @ViewChild('fileInput', {static: false}) fileInput!: ElementRef;
  form: FormGroup;
  selectedImageUrl: string | null = null;
  selectedImageFile: File | null = null;
  editContact: Contact | null = null;

  constructor(
    private formBuilder: FormBuilder,
    private contactService: ContactService
  ) {
    this.form = this.formBuilder.group({
      fullName: [''],
      email: [''],
      phoneNumber: [''],
    });
  }

  ngOnInit(): void {
    this.contactService.contactSubject.subscribe({
      next: (contact) => {
          this.editContact = contact;
          if (this.editContact) {
            this.form.patchValue(this.editContact);
            this.selectedImageUrl = this.editContact.imageUrl;
          }
        }
    });
  }

  onSubmit(): void {
    if (this.editContact) {
      this.updateContact();
    } else {
      this.saveNewContact();
    }
  }

  saveNewContact(): void {
    this.contactService.saveContact(this.form.value, this.selectedImageFile).subscribe({
      next: (data) => {
        console.log(data);
        this.newContact = data;
        this.contactSaved.emit(this.newContact);
      },
      error: (error) => {
        console.error(error);
      }
    });
  }

  updateContact(): void {
    this.contactService.updateContact(this.editContact!.id, this.form.value, this.selectedImageFile).subscribe({
      next: (data) => {
        console.log(data);
        this.contactSaved.emit(data);
      }
    })
  }

  reset(): void {
    this.form.reset();
    this.selectedImageFile = null;
    this.selectedImageUrl = null;
  }

  showFileSelector(): void {
    this.fileInput.nativeElement.click();
  }

  onFileSelected(event: Event) {
    const inputElement = event.target as HTMLInputElement;
    if (inputElement.files && inputElement.files.length > 0) {
      this.selectedImageFile = inputElement.files[0];
      const reader = new FileReader();
      reader.onload = () => {
        this.selectedImageUrl = reader.result as string;
      };
      reader.readAsDataURL(this.selectedImageFile);
    } else {
      console.log('No file selected');
    }
    inputElement.value = '';
  }

  removeImage(): void {
    this.selectedImageUrl = null;
  }
}
