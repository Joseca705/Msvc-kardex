package com.jose.kardex.domain.repository;

import com.jose.kardex.domain.entity.Kardex;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface KardexRepository
  extends JpaRepositoryImplementation<Kardex, Integer> {}
