package org.example.kun_uzz.Service;

import org.example.kun_uzz.DTO.auth.RegistrationDTO;
import org.example.kun_uzz.Entity.ProfileEntity;
import org.example.kun_uzz.Enums.ProfileRole;
import org.example.kun_uzz.Enums.ProfileStatus;
import org.example.kun_uzz.exp.AppBadException;
import org.example.kun_uzz.repository.ProfileRepository;
import org.example.kun_uzz.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;


@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private MailSenderService mailSenderService;

    public String registration(RegistrationDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndVisibleTrue(dto.getEmail());
        if (optional.isPresent()) {
            throw new AppBadException("Email already exists");
        }

        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(MD5.getMD5(dto.getPassword()));

        entity.setCreated_date(LocalDate.now());
        entity.setRole(ProfileRole.ROLE_USER);
        entity.setStatus(ProfileStatus.REGISTRATION);

        profileRepository.save(entity);


        // send email

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Click to the link to complete registration \n");
        stringBuilder.append("http://localhost:8081/auth/verification/");
        stringBuilder.append(entity.getId());
        stringBuilder.append("\n Mazgi.");

        mailSenderService.send(dto.getEmail(), "Complete registration", stringBuilder.toString());
        return "To complete your registration please verify your email.";

    }
    public String authorizationVerification(Integer userId) {
        Optional<ProfileEntity> optional = profileRepository.findById(userId);
        if (optional.isEmpty()) {
            throw new AppBadException("User not found");
        }
        ProfileEntity entity = optional.get();
        if (!entity.getVisible() || !entity.getStatus().equals(ProfileStatus.REGISTRATION)) {
            throw new AppBadException("Registration not completed");
        }
        profileRepository.updateStatus(userId, ProfileStatus.ACTIVE);
        return "Success";
    }

}
