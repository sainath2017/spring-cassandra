package com.sai.exception;

import java.util.ArrayList;
import java.util.List;

import com.sai.dto.ErrorMessage;

public class EntityNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 159869075340370295L;

  private final transient  List<ErrorMessage> errorMessages = new ArrayList<>();

  public EntityNotFoundException(String message) {
    super(message);
    ErrorMessage errorMessage = new ErrorMessage(message);
    this.errorMessages.add(errorMessage);
  }

  public List<ErrorMessage> getErrorMessages() {
    return new ArrayList<>(errorMessages);
  }

}
