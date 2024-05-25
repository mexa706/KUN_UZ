package org.example.kun_uzz.repository;

import org.example.kun_uzz.Entity.CategoryEntity;
import org.example.kun_uzz.Entity.EmailHistoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EmailHistoryRepository extends CrudRepository<EmailHistoryEntity, Integer> {

    Long countByEmailAndCreatedDateBetween(String email, LocalDateTime from, LocalDateTime to);

    // select count(*) from email_history createdDate between :from and :to
    @Query("from EmailHistoryEntity order by createdDate desc limit 1")
    Optional<EmailHistoryEntity> findLastByEmail(String email);
}
