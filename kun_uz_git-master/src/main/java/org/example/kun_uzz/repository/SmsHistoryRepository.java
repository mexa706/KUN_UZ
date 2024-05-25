package org.example.kun_uzz.repository;

import org.example.kun_uzz.Entity.EmailHistoryEntity;
import org.example.kun_uzz.Entity.SmsHistoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface SmsHistoryRepository extends CrudRepository<SmsHistoryEntity,Integer> {
    Long countByPhoneAndCreatedDateBetween(String phone, LocalDateTime from, LocalDateTime to);

    // select count(*) from email_history createdDate between :from and :to
    @Query("from SmsHistoryEntity order by createdDate desc limit 1")
    Optional<SmsHistoryEntity> findLastByPhone(String phone);
}
