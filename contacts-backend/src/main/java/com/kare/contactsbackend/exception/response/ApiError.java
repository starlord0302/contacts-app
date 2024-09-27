package com.kare.contactsbackend.exception.response;

public class ApiError {

  public int status;
  public String message;
  public long timestamp;

  public ApiError() {
  }

  public ApiError(int status, String message, long timestamp) {
    this.status = status;
    this.message = message;
    this.timestamp = timestamp;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }
}
