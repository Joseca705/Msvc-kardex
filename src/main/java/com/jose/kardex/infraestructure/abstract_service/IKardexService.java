package com.jose.kardex.infraestructure.abstract_service;

import com.jose.kardex.api.model.request.CreateKardexDto;
import com.jose.kardex.api.model.response.CreatedKardexResponse;
import com.jose.kardex.api.model.response.CurrentAmountBatchResponse;
import com.jose.kardex.api.model.response.ProductLessThanUmbral;
import com.jose.kardex.api.model.response.ProfitResponse;
import com.jose.kardex.api.model.response.TopSellingProductsResponse;
import java.time.LocalDate;
import java.util.List;

public interface IKardexService
  extends CrudService<List<CreateKardexDto>, CreatedKardexResponse, Integer> {
  List<ProductLessThanUmbral> findProductsLessThanUmbral();

  List<TopSellingProductsResponse> findTopSellingProducts(Integer limit);

  ProfitResponse getProfitBetweenDate(LocalDate start, LocalDate end);

  List<CurrentAmountBatchResponse> getCurrentAmountBatchs(List<Integer> ids);
}
