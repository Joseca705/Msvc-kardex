package com.jose.kardex.api.model.request;

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

  @NotNull
  private String movementType;

  @NotNull
  @Min(1)
  private Integer amount;

  @NotNull
  @Min(1)
  private Integer balanceAmount;

  @NotNull
  @Min(1)
  private BigDecimal unitPrice;

  @NotNull
  private String reference;

  @NotNull
  @Min(1)
  private Integer referenceId;

  @NotNull
  @Min(1)
  private Integer productId;

  @NotNull
  @Min(1)
  private Integer batchId;
}
