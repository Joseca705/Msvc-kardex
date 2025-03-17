package com.jose.kardex.api.controller;

import com.jose.kardex.api.model.request.CreateKardexDto;
import com.jose.kardex.api.model.response.CreatedKardexResponse;
import com.jose.kardex.infraestructure.abstract_service.IKardexService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/kardex")
public class KardexController {

  private final IKardexService kardexService;

  @PostMapping(path = "")
  public ResponseEntity<CreatedKardexResponse> createRecordKardex(
    @RequestBody @Valid List<CreateKardexDto> dtos
  ) {
    CreatedKardexResponse response = this.kardexService.create(dtos);
    return ResponseEntity.ok(response);
  }
}
