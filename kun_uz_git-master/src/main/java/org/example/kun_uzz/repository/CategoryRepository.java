package org.example.kun_uzz.repository;


import org.example.kun_uzz.Entity.CategoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {

   //  List<CategoryEntity> findAllOrderByOrderNumber();



    Iterable<CategoryEntity> findAllByVisibleTrue();
}
