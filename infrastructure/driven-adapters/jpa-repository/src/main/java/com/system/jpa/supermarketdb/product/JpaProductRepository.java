package com.system.jpa.supermarketdb.product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface JpaProductRepository extends CrudRepository<ProductEntity, String>, QueryByExampleExecutor<ProductEntity> {
    @Query(value = "SELECT * FROM products", nativeQuery = true)
    List<ProductEntity> findAllProducts();
}
