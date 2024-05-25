package org.example.kun_uzz.Service;

import org.example.kun_uzz.Entity.EmailHistoryEntity;
import org.example.kun_uzz.Entity.SmsHistoryEntity;
import org.example.kun_uzz.exp.AppBadException;
import org.example.kun_uzz.repository.EmailHistoryRepository;
import org.example.kun_uzz.repository.SmsHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class SmsHistoryService {
    @Autowired
    private SmsHistoryRepository smsHistoryRepository;


    public void crete(String toPhone, String text) {

        SmsHistoryEntity entity = new SmsHistoryEntity();

        entity.setPhone(toPhone);
        entity.setMessage(text);

        smsHistoryRepository.save(entity);
    }

    public void checkPhoneLimit(String phone) { // 1 minute -3 attempt
        // 23/05/2024 19:01:13
        // 23/05/2024 19:01:23
        // 23/05/2024 19:01:33

        // 23/05/2024 19:00:55 -- (current -1)
        // 23/05/2024 19:01:55 -- current

        LocalDateTime to = LocalDateTime.now();
        LocalDateTime from = to.minusMinutes(2);

        long count = smsHistoryRepository.countByPhoneAndCreatedDateBetween(phone, from, to);
        if (count >= 3) {
            throw new AppBadException("Sms limit reached. Please try after some time");
        }
    }

    public void isNotExpiredPhone(String phone) {
        Optional<SmsHistoryEntity> optional = smsHistoryRepository.findLastByPhone(phone);
        if (optional.isEmpty()) {
            throw new AppBadException("Sms history not found");
        }
        SmsHistoryEntity entity = optional.get();
        if (entity.getCreatedDate().plusDays(1).isBefore(LocalDateTime.now())) {
            throw new AppBadException("Confirmation time expired");
        }
    }
}
