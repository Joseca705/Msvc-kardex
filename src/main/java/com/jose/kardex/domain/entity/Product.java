package com.jose.kardex.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "products")
public class Product extends BaseEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 100, nullable = false)
  private String code;

  @Column(length = 100, nullable = false)
  private String name;

  @Column(name = "sale_price", nullable = false, precision = 38, scale = 4)
  private BigDecimal salePrice;

  @Column(name = "umbral_stock", nullable = false)
  private Integer umbralStock;
}
