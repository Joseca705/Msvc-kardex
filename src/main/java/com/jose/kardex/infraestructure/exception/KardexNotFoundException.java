package com.jose.kardex.infraestructure.exception;

public class KardexNotFoundException extends RuntimeException {

  public KardexNotFoundException() {
    super("El registro del kardex no fue encontrado.");
  }
}
