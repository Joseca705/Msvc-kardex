package com.jose.kardex.infraestructure.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jose.kardex.api.model.request.CreateKardexDto;
import com.jose.kardex.api.model.response.CreatedKardexResponse;
import com.jose.kardex.api.model.response.ProductLessThanUmbral;
import com.jose.kardex.api.model.response.TopSellingProductsResponse;
import com.jose.kardex.domain.entity.Kardex;
import com.jose.kardex.domain.repository.KardexRepository;
import com.jose.kardex.domain.repository.ProductRepository;
import com.jose.kardex.infraestructure.abstract_service.IKardexService;
import com.jose.kardex.infraestructure.exception.ProductNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KardexService implements IKardexService {

  private final KardexRepository kardexRepository;
  private final ProductRepository productRepository;
  private final ObjectMapper mapper;

  @Override
  public CreatedKardexResponse create(List<CreateKardexDto> request) {
    List<Kardex> kardexs = request
      .stream()
      .map(req -> dtoToEntity(req))
      .toList();
    this.kardexRepository.saveAll(kardexs);

    return new CreatedKardexResponse("Record kardex created successfully.");
  }

  @Override
  public List<ProductLessThanUmbral> findProductsLessThanUmbral() {
    List<String> rawProducts =
      this.kardexRepository.findProductsLessThanUmbral();

    if (rawProducts.isEmpty()) throw new ProductNotFoundException();

    List<ProductLessThanUmbral> products = rawProducts
      .stream()
      .map(raw -> {
        ProductLessThanUmbral product;
        try {
          product = mapper.readValue(raw, ProductLessThanUmbral.class);
          return product;
        } catch (JsonMappingException e) {
          throw new RuntimeException();
        } catch (JsonProcessingException e) {
          throw new RuntimeException();
        }
      })
      .toList();

    return products;
  }

  @Override
  public List<TopSellingProductsResponse> findTopSellingProducts(
    Integer limit
  ) {
    List<Object[]> rawProducts =
      this.productRepository.findTotalAmountByProduct(limit);

    if (rawProducts.isEmpty()) throw new ProductNotFoundException();

    return rawProducts
      .stream()
      .map(rawProduct ->
        new TopSellingProductsResponse(
          ((Number) rawProduct[0]).intValue(),
          (String) rawProduct[1],
          ((Number) rawProduct[2]).intValue()
        )
      )
      .toList();
  }

  @Override
  public CreatedKardexResponse read(Integer id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'read'");
  }

  @Override
  public CreatedKardexResponse update(
    List<CreateKardexDto> request,
    Integer id
  ) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void delete(Integer id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  private Kardex dtoToEntity(CreateKardexDto dto) {
    Kardex kardex = new Kardex();
    BeanUtils.copyProperties(dto, kardex);
    return kardex;
  }
}
