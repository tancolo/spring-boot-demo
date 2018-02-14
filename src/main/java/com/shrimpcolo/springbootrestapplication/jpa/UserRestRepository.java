package com.shrimpcolo.springbootrestapplication.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "users", collectionResourceRel = "users")
public interface UserRestRepository extends PagingAndSortingRepository<User, Long> {

    Iterable<User> findByNameContainingIgnoreCase(String name);

    List<User> findByRole(@Param("role") String role);
}
