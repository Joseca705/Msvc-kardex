package com.jose.kardex.api.controller;

import com.jose.kardex.api.model.request.CreateKardexDto;
import com.jose.kardex.api.model.response.CreatedKardexResponse;
import com.jose.kardex.api.model.response.CurrentAmountBatchResponse;
import com.jose.kardex.api.model.response.ProductLessThanUmbral;
import com.jose.kardex.api.model.response.ProfitResponse;
import com.jose.kardex.api.model.response.TopSellingProductsResponse;
import com.jose.kardex.infraestructure.abstract_service.IKardexService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/kardex")
@Validated
public class KardexController {

  private final IKardexService kardexService;

  @PostMapping(path = "")
  public ResponseEntity<CreatedKardexResponse> createRecordKardex(
    @RequestBody @Valid List<CreateKardexDto> dtos
  ) {
    CreatedKardexResponse response = this.kardexService.create(dtos);
    return ResponseEntity.ok(response);
  }

  @GetMapping(path = "/current-amount-batch")
  public ResponseEntity<
    List<CurrentAmountBatchResponse>
  > getCurrentAmountBatchs(
    @NotEmpty(message = "El campo no debe estar vacio.") @RequestParam(
      name = "ids",
      required = true
    ) List<@Positive Integer> ids
  ) {
    List<CurrentAmountBatchResponse> response =
      this.kardexService.getCurrentAmountBatchs(ids);
    return ResponseEntity.ok(response);
  }

  @GetMapping(path = "/less-than-umbral")
  public ResponseEntity<
    List<ProductLessThanUmbral>
  > getProductsLessThanUmbralStock() {
    return ResponseEntity.ok(this.kardexService.findProductsLessThanUmbral());
  }

  @GetMapping(path = "/top-selling-products")
  public ResponseEntity<List<TopSellingProductsResponse>> getTopSellingProducts(
    @RequestParam(
      name = "limit",
      defaultValue = "5",
      required = false
    ) @Positive Integer limit
  ) {
    List<TopSellingProductsResponse> topProducts =
      this.kardexService.findTopSellingProducts(limit);
    return ResponseEntity.ok(topProducts);
  }

  @GetMapping(path = "/profit")
  public ResponseEntity<ProfitResponse> getGananciaObtenidaEnRangoDeFechas(
    @RequestParam(required = true, name = "start") @DateTimeFormat(
      pattern = "yyyy-MM-dd"
    ) LocalDate start,
    @RequestParam(required = true, name = "end") @DateTimeFormat(
      pattern = "yyyy-MM-dd"
    ) LocalDate end
  ) {
    ProfitResponse profit = this.kardexService.getProfitBetweenDate(start, end);
    return ResponseEntity.ok(profit);
  }
}
