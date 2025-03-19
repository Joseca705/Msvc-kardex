package com.jose.kardex.api.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentAmountBatchResponse {

  private Integer batchId;

  private Integer balanceAmount;
}
