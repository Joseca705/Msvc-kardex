package com.jose.kardex.api.model.response;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfitResponse {

  private BigDecimal profit;

  private List<ProfitDetailResponse> details;
}
