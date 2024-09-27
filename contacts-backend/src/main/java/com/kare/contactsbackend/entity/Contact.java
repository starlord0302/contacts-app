package com.kare.contactsbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "full_name", columnDefinition = "VARCHAR(50) NOT NULL")
  private String fullName;

  @Column(name = "phone_number", columnDefinition = "VARCHAR(25) NOT NULL")
  private String phoneNumber;

  @Column(name = "email", columnDefinition = "VARCHAR(50) NOT NULL")
  private String email;

  public Contact() {
  }

  public Contact(String fullName, String phoneNumber, String email) {
    this.fullName = fullName;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
