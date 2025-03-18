package com.jose.kardex.domain.repository;

import com.jose.kardex.domain.entity.Kardex;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface KardexRepository
  extends JpaRepositoryImplementation<Kardex, Integer> {
  @Query(
    value = """
    SELECT
    	json_build_object(
    	'id', p.id,
    	'name', p.name,
    	'batchs', coalesce(json_agg(json_build_object(
    		'batchId', z.batch_id,
    		'currentAmount', z.current_amount
    	)))
    ):: TEXT
    FROM products p
    INNER JOIN (
    	SELECT
    		k.batch_id ,
    		k.product_id,
    		min(k.balance_amount) as current_amount
    	FROM kardex k
    	WHERE k.movement_type = 'ENTRADA'
    	GROUP BY k.batch_id, k.product_id) as z
    	ON p.id = z.product_id
    WHERE z.current_amount < p.umbral_stock
    GROUP BY p.id;
          """,
    nativeQuery = true
  )
  List<String> findProductsLessThanUmbral();
}
