package com.softlond.jpa.supermarketdb.sale;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaSaleRepository extends CrudRepository<SaleEntity, String>, QueryByExampleExecutor<SaleEntity> {

    @Query(value = "SELECT * FROM sales", nativeQuery = true)
    List<SaleEntity> findAllSales();

    @Query(value = "SELECT * FROM sales WHERE ((:clientId IS NULL OR :clientId = '') AND creation_date = :initialDate) " +
            "OR (client_id = :clientId AND :initialDate IS NULL) " +
            "OR (client_id = :clientId AND creation_date = :initialDate AND :finalDate IS NULL)" +
            "OR (client_id = :clientId AND creation_date BETWEEN :initialDate AND :finalDate)", nativeQuery = true)
    List<SaleEntity> findAllByFilters(@Param("clientId") final String clientId,
                                      @Param("initialDate") final Long initialDate,
                                      @Param("finalDate") final Long finalDate);
}
