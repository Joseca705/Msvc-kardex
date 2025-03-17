package com.jose.kardex.domain.entity;

import com.jose.kardex.domain.constant.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "kardex")
public class Kardex extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "movement_type", nullable = false)
  private String movementType;

  @Column(nullable = false)
  private Integer amount;

  @Column(name = "balance_amount")
  private Integer balanceAmount;

  @Column(name = "unit_price", nullable = false)
  private BigDecimal unitPrice;

  @Column(name = "total_price")
  private BigDecimal totalPrice;

  @Column(name = "reference")
  private String reference;

  @Column(name = "reference_id", nullable = false)
  private Integer referenceId;

  @Column(name = "product_id", nullable = false)
  private Integer productId;

  @Column(name = "batch_id", nullable = false)
  private Integer batchId;

  @PrePersist
  public void onPrePersist() {
    this.setStatus(Status.ACTIVE);
    this.totalPrice = this.unitPrice.multiply(new BigDecimal(this.amount));
  }
}
