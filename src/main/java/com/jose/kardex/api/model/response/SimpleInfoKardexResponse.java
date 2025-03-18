package com.jose.kardex.api.model.response;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleInfoKardexResponse {

  private Integer batchId;

  private Integer balanceAmount;

  private BigDecimal unitPrice;

  private Integer productId;
}
