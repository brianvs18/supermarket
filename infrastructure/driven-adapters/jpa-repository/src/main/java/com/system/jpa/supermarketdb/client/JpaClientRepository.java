package com.system.jpa.supermarketdb.client;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaClientRepository extends CrudRepository<ClientEntity, String>, QueryByExampleExecutor<ClientEntity> {

    @Query(value = "SELECT * FROM clients", nativeQuery = true)
    List<ClientEntity> findAllClients();

    Optional<ClientEntity> findByDocument(String document);
}
