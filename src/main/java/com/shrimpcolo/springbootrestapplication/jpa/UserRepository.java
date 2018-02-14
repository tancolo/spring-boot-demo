package com.shrimpcolo.springbootrestapplication.jpa;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long>, JpaSpecificationExecutor<User> {

    Iterable<User> findByName(String name);

    Iterable<User> findByNameContainingIgnoreCase(String name);

    List<User> findByRole(String role);

}
