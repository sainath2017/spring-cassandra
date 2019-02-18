package com.sai.controller.advice;

import com.sai.dto.ErrorMessage;
import com.sai.dto.ServiceResponse;
import com.sai.exception.DuplicateExistException;
import com.sai.exception.EntityNotFoundException;
import com.sai.exception.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@ResponseBody
public class DefaultExceptionHandler {

  private static final Logger LOG = LoggerFactory.getLogger(DefaultExceptionHandler.class);

  @ExceptionHandler({EntityNotFoundException.class})
  public ResponseEntity<ServiceResponse<?>> handleException(EntityNotFoundException e) {
    LOG.info(e.getMessage());

    String message = e.getMessage();
    ServiceResponse<Object> sr = new ServiceResponse<>(message, e.getErrorMessages());
    return new ResponseEntity<>(sr, HttpStatus.NOT_FOUND);
  }

    @ExceptionHandler({DuplicateExistException.class})
    public ResponseEntity<ServiceResponse<Object>> handleException(DuplicateExistException e) {

        String message = e.getMessage();
        ServiceResponse<Object> sr =
                new ServiceResponse<>(message, Arrays.asList(new ErrorMessage(message)), e.getDuplicates());

        LOG.info("Precondition failed. Message {}", message);

        return new ResponseEntity<>(sr, HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ServiceResponse<?>> handleException(HttpMessageNotReadableException e) {
        String message = "Error parsing payload, check json syntax, LocalDate format and data types";

        // Have exception message only in debug mode
        if (LOG.isDebugEnabled()) {
            message = message + "\n" + e.getMessage();
        }

        LOG.info("Error parsing payload", e);
        ErrorMessage error = ErrorMessage.of(message);
        ServiceResponse<?> sr = ServiceResponse.of("Invalid Payload", error);

        return new ResponseEntity<>(sr, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<ServiceResponse<?>> handleException(ValidationException e) {

        ErrorMessage error = ErrorMessage.of(e.getMessage());
        ServiceResponse<?> sr = ServiceResponse.of("Validation failure", error);

        return new ResponseEntity<>(sr, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ServiceResponse<?>> handleException(IllegalArgumentException e) {
        ErrorMessage error = ErrorMessage.of(e.getMessage());
        ServiceResponse<?> sr = ServiceResponse.of("Validation Failure", error);

        return new ResponseEntity<>(sr, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ServiceResponse<?>> handleException(ConstraintViolationException e) {
        String message = "Validation failure";

        List<ErrorMessage> errorMessages = e.getConstraintViolations()
                .stream()
                .map(v -> ErrorMessage.of(v.getMessage(), v.getPropertyPath()
                        .toString()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(ServiceResponse.of(message, errorMessages), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NullPointerException.class, UnsupportedOperationException.class})
    public ResponseEntity<ServiceResponse<?>> handleException(Exception e) {
        String message = "Unexpected error occurred, please contact support";

        // Have exception message only in debug mode
        if (LOG.isDebugEnabled()) {
            message = message + "\n" + e.getMessage();
        }

        LOG.info("Error processing request", e);
        ErrorMessage error = ErrorMessage.of(message);
        ServiceResponse<?> sr = ServiceResponse.of("Unexpected error", error);

        return new ResponseEntity<>(sr, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
