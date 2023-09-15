package com.softlond.jpa.supermarketdb.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaUserRepository extends CrudRepository<UserEntity, String>, QueryByExampleExecutor<UserEntity> {

    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<UserEntity> findAllUsers();
}
