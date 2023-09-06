package com.system.jpa.supermarketdb.sale;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaSaleRepository extends CrudRepository<SaleEntity, String>, QueryByExampleExecutor<SaleEntity> {

    @Query(value = "SELECT * FROM sales", nativeQuery = true)
    List<SaleEntity> findAllSales();
}
