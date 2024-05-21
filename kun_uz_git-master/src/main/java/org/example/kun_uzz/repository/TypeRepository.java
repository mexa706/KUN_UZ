package org.example.kun_uzz.repository;


import org.example.kun_uzz.Entity.TypeEntity;
import org.springframework.data.repository.CrudRepository;

public interface TypeRepository extends CrudRepository<TypeEntity, Integer> {
    Iterable<TypeEntity> findAllByVisibleTrue();
}
