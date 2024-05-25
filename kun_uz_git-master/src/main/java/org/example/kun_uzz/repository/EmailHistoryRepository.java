package org.example.kun_uzz.repository;

import org.example.kun_uzz.Entity.CategoryEntity;
import org.example.kun_uzz.Entity.EmailHistoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EmailHistoryRepository extends CrudRepository<EmailHistoryEntity, Integer> {

    long countByEmailAndCreatedDateBetween(String email, LocalDateTime from, LocalDateTime to);

    Optional<EmailHistoryEntity> findByEmail(String email);
}
