package com.jose.kardex.infraestructure.exception;

public class ProductNotFoundException extends RuntimeException {

  public ProductNotFoundException() {
    super("El producto no fue encontrado.");
  }
}
