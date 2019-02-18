package com.sai.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import com.sai.util.JsonUtil;

/**
 * This class will be the generic response for all controller methods.
 * <p>
 * Plan is to return a consistent payload and populate errors object whenever there is an error.
 * Also, in future, the service response may have some instrumentation and debug information for
 * internal troubleshooting.
 * </p>
 *
 * @param <T>
 * @author sainath
 */
@Getter
@Setter
public class ServiceResponse<T> {

  private String message;

  private List<ErrorMessage> errors = new ArrayList<>();

  private T data;

  public ServiceResponse() {}

  public ServiceResponse(String message) {
    this.message = message;
  }

  public ServiceResponse(T data) {
    this.data = data;
  }

  public ServiceResponse(List<ErrorMessage> errors) {
    this.errors = errors != null ? new ArrayList<>(errors) : null;
  }

  public ServiceResponse(String message, T data) {
    this.message = message;
    this.data = data;
  }

  public ServiceResponse(String message, List<ErrorMessage> errors) {
    this.message = message;
    this.errors = errors != null ? new ArrayList<>(errors) : null;
  }

  public ServiceResponse(List<ErrorMessage> errors, T data) {
    this.errors = errors != null ? new ArrayList<>(errors) : null;
    this.data = data;
  }

  public static <T> ServiceResponse<T> of(List<ErrorMessage> errors) {
    return new ServiceResponse<>(errors);
  }

  public static <T> ServiceResponse<T> of(ErrorMessage error) {
    ServiceResponse<T> sr = new ServiceResponse<>();
    sr.getErrors()
        .add(error);
    return sr;
  }

  public static <T> ServiceResponse<T> of(List<ErrorMessage> errors, T data) {
    return new ServiceResponse<>(errors, data);
  }

  public ServiceResponse(String message, List<ErrorMessage> errors, T data) {
    this(message, data);
    this.errors = errors != null ? new ArrayList<>(errors) : null;
  }

  public static <T> ServiceResponse<T> of(String message) {
    return new ServiceResponse<>(message);
  }

  public static <T> ServiceResponse<T> of(T data) {
    return new ServiceResponse<>(data);
  }

  public static <T> ServiceResponse<T> of(String message, T data) {
    return new ServiceResponse<>(message, data);
  }

  public static <T> ServiceResponse<T> of(String message, ErrorMessage error) {
    ServiceResponse<T> sr = new ServiceResponse<>(message);
    sr.getErrors()
            .add(error);
    return sr;
  }

  @Override
  public String toString() {
    return JsonUtil.toJsonString(this);
  }
}
