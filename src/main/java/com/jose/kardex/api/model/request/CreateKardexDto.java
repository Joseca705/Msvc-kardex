package com.jose.kardex.api.model.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateKardexDto {

  @NotNull(message = "El campo movementType no debe ser nulo.")
  private String movementType;

  @NotNull(message = "El campo amount no debe ser nulo.")
  @Min(value = 1, message = "El valor de amount debe ser mayor a 1.")
  private Integer amount;

  @NotNull(message = "El campo balanceAmount no debe ser nulo.")
  @Min(value = 1, message = "El valor de balanceAmount debe ser mayor a 1.")
  private Integer balanceAmount;

  @NotNull(message = "El campo unitPrice no debe ser nulo.")
  @Min(value = 1, message = "El valor de unitPrice debe ser mayor a 1.")
  @Digits(
    integer = 38,
    fraction = 4,
    message = "El valor de purchasePrice puede contener hasta 4 decimales."
  )
  private BigDecimal unitPrice;

  @NotNull(message = "El campo reference no debe ser nulo.")
  private String reference;

  @NotNull(message = "El campo referenceId no debe ser nulo.")
  @Min(value = 1, message = "El valor de referenceId debe ser mayor a 1.")
  private Integer referenceId;

  @NotNull(message = "El campo productId no debe ser nulo.")
  @Min(value = 1, message = "El valor de productId debe ser mayor a 1.")
  private Integer productId;

  @NotNull(message = "El campo batchId no debe ser nulo.")
  @Min(value = 1, message = "El valor de batchId debe ser mayor a 1.")
  private Integer batchId;
}
