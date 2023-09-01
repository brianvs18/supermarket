package com.softlond.jpa.supermarketdb.category;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface JpaCategoryRepository extends CrudRepository<CategoryEntity, String>, QueryByExampleExecutor<CategoryEntity> {
    @Query(value = "SELECT * FROM categories", nativeQuery = true)
    List<CategoryEntity> findAllCategories();
    @Query(value = "DELETE FROM categories c WHERE c.id = (:id)", nativeQuery = true)
    Void deleteByCategoryId(@Param("id") final String id);

}
