package com.jose.kardex.api.controller.error_handler;

import com.jose.kardex.api.model.response.ErrorResponse;
import com.jose.kardex.api.model.response.ErrorsResponse;
import com.jose.kardex.api.model.response.abstract_response.BaseErrorResponse;
import com.jose.kardex.infraestructure.exception.ProductNotFoundException;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestController {

  @ExceptionHandler(exception = ProductNotFoundException.class)
  public BaseErrorResponse handleProductNotFoundException(
    ProductNotFoundException exception
  ) {
    return ErrorResponse.builder()
      .error(exception.getMessage())
      .status(HttpStatus.BAD_REQUEST.name())
      .code(HttpStatus.BAD_REQUEST.value())
      .build();
  }

  @ExceptionHandler(exception = MethodArgumentNotValidException.class)
  public BaseErrorResponse handleFailedValidation(
    MethodArgumentNotValidException exception
  ) {
    var errors = new ArrayList<String>();
    exception
      .getAllErrors()
      .forEach(error -> errors.add(error.getDefaultMessage()));
    return ErrorsResponse.builder()
      .errors(errors)
      .status(HttpStatus.BAD_REQUEST.name())
      .code(HttpStatus.BAD_REQUEST.value())
      .build();
  }
}
