package com.jose.kardex.api.model.response;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfitDetailResponse {

  private Integer productId;

  private String name;

  private Integer kardexId;

  private BigDecimal subtotal;

  private String movementType;
}
