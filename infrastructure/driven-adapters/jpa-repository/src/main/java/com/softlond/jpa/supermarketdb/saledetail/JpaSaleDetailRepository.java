package com.softlond.jpa.supermarketdb.saledetail;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaSaleDetailRepository extends CrudRepository<SaleDetailEntity, String>, QueryByExampleExecutor<SaleDetailEntity> {

    List<SaleDetailEntity> findAllBySaleId(String saleId);
}
