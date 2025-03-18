package com.jose.kardex.domain.repository;

import com.jose.kardex.domain.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
  @Query(
    value = """
    SELECT p.id, p.name, sum(k.amount) as total_amount
    FROM products p
    LEFT JOIN kardex k ON p.id = k.product_id
    WHERE k.movement_type = 'ENTRADA'
    GROUP BY p.id
    ORDER BY total_amount DESC
    LIMIT :limit
      """,
    nativeQuery = true
  )
  List<Object[]> findTotalAmountByProduct(@Param("limit") Integer limit);
}
