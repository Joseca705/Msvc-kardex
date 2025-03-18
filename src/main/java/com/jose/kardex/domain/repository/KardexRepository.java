package com.jose.kardex.domain.repository;

import com.jose.kardex.api.model.projection.SimpleInfoKardex;
import com.jose.kardex.domain.entity.Kardex;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
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

  @Query(
    value = """
    SELECT
    	json_build_object(
    	'productId', p.id,
    	'name', p.name,
    	'kardexId', k.id,
    	'subtotal', k.total_price,
    	'movementType', k.movement_type
    	):: TEXT
    FROM products p
    LEFT JOIN kardex k on
    	 p.id = k.product_id
    WHERE
    	k.created_at >= :startDate
    	and k.created_at < :endDate
      """,
    nativeQuery = true
  )
  List<String> findDetailsProduct(
    @Param("startDate") LocalDate startDate,
    @Param("endDate") LocalDate endDate
  );

  @Query(
    value = """
    SELECT
    	distinct on
    	(k.batch_id)
    	k.batch_id,
    	k.balance_amount,
    	k.unit_price,
    	k.product_id
    from kardex k
    WHERE k.batch_id in (:ids)
    ORDER BY k.batch_id,
    	k.created_at DESC
      """,
    nativeQuery = true
  )
  List<Object[]> getKardexSimpleInfoByIds(@Param("ids") List<Integer> ids);
}
