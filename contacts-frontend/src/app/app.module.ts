import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ContactListComponent } from './components/contact-list/contact-list.component';
import { ContactComponent } from './components/contact/contact.component';
import { ContactFormComponent } from './components/contact-form/contact-form.component';
import { ContactPageComponent } from './components/contact-page/contact-page.component';
import {ReactiveFormsModule} from "@angular/forms";
import {provideHttpClient} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    ContactListComponent,
    ContactComponent,
    ContactFormComponent,
    ContactPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
  ],
  providers: [provideHttpClient()],
  bootstrap: [AppComponent]
})
export class AppModule { }
