package com.sai.exception;

import java.util.ArrayList;
import java.util.List;

public class DuplicateExistException extends RuntimeException {

  private static final long serialVersionUID = 3963375862531423949L;

  private final transient List<Object> duplicates;

  public List<Object> getDuplicates() {
    return duplicates != null ? new ArrayList<>(duplicates) : null;
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public DuplicateExistException(String message, List duplicates) {
    super(message);
    this.duplicates = duplicates;
  }

}
