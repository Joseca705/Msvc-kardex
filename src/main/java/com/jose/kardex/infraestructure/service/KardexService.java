package com.jose.kardex.infraestructure.service;

import com.jose.kardex.api.model.request.CreateKardexDto;
import com.jose.kardex.api.model.response.CreatedKardexResponse;
import com.jose.kardex.domain.entity.Kardex;
import com.jose.kardex.domain.repository.KardexRepository;
import com.jose.kardex.infraestructure.abstract_service.IKardexService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KardexService implements IKardexService {

  private final KardexRepository kardexRepository;

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
