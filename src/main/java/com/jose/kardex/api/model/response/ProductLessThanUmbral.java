package com.jose.kardex.api.model.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductLessThanUmbral {

  private Integer id;

  private String name;

  private List<BatchCurrentAmount> batchs;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class BatchCurrentAmount {

  private Integer batchId;

  private Integer currentAmount;
}
