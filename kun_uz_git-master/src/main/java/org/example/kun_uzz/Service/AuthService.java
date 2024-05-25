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

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private MailSenderService mailSenderService;
    @Autowired
    private EmailHistoryService emailHistoryService;
    @Autowired
    private SmsHistoryService smsHistoryService;
    @Autowired
    private SmsService smsService;

    /*  public String registration(RegistrationDTO dto) {

          Optional<ProfileEntity> optional = profileRepository.findByEmailAndVisibleTrue(dto.getEmail());
          if (optional.isPresent()) {
              throw new AppBadException("Email already exists");
          }

          ProfileEntity entity = new ProfileEntity();
          entity.setName(dto.getName());
          entity.setSurname(dto.getSurname());
          entity.setEmail(dto.getEmail());
          entity.setPassword(MD5.getMD5(dto.getPassword()));

          entity.setCreated_date(LocalDateTime.now());
          entity.setRole(ProfileRole.ROLE_USER);
          entity.setStatus(ProfileStatus.REGISTRATION);

          profileRepository.save(entity);


          // send email

          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Click to the link to complete registration \n");
          stringBuilder.append("http://localhost:8081/auth/verification/");
          stringBuilder.append(entity.getId());
          stringBuilder.append("\n Mazgi.");

          emailHistoryService.checkEmailLimit(dto.getEmail());
          emailHistoryService.isNotExpiredEmail(dto.getEmail());

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
          if (entity.getCreated_date().plusMinutes(1).isBefore(LocalDateTime.now())) {
              entity.setCreated_date(LocalDateTime.now());

              mailSenderService.send(entity.getId().toString(), "Your account has expired", entity.getEmail());

              return "Registration expired, please try again";
          }
          profileRepository.updateStatus(userId, ProfileStatus.ACTIVE);
          return "Success";
      }
  */
    public String registrationByPhone(RegistrationDTO dto) {

//        Optional<ProfileEntity> optional = profileRepository.findByEmailAndVisibleTrue(dto.getEmail());
        Optional<ProfileEntity> optional = profileRepository.findByPhoneAndVisibleTrue(dto.getPhone());
        if (optional.isPresent()) {
            throw new AppBadException("Phone already exists");
        }

        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setPassword(MD5.getMD5(dto.getPassword()));

        entity.setCreated_date(LocalDateTime.now());
        entity.setRole(ProfileRole.ROLE_USER);
        entity.setStatus(ProfileStatus.REGISTRATION);

        profileRepository.save(entity);

        smsService.sendSms(dto.getPhone());
        smsHistoryService.crete(entity.getEmail(), entity.getPhone());
        return "To complete your registration please enter the code.";
    }

    public String authorizationVerificationByPhone(Integer userId) {
        Optional<ProfileEntity> optional = profileRepository.findById(userId);
        if (optional.isEmpty()) {
            throw new AppBadException("User not found");
        }

        ProfileEntity entity = optional.get();

        smsHistoryService.isNotExpiredPhone(entity.getPhone());// check for expireation date

        if (!entity.getVisible() || !entity.getStatus().equals(ProfileStatus.REGISTRATION)) {
            throw new AppBadException("Registration not completed");
        }

        profileRepository.updateStatus(userId, ProfileStatus.ACTIVE);
        return "Success";
    }

    public String registrationResendByEmail(String email) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndVisibleTrue(email);
        if (optional.isEmpty()) {
            throw new AppBadException("Email not exists");
        }
        ProfileEntity entity = optional.get();

        if (!entity.getVisible() || !entity.getStatus().equals(ProfileStatus.REGISTRATION)) {
            throw new AppBadException("Registration not completed");
        }
        emailHistoryService.checkEmailLimit(email);
        sendRegistrationEmail(entity.getId(), email);
        return "To complete your registration please verify your email.";
    }

    public void sendRegistrationEmail(Integer profileId, String email) {
        // send email
        String url = "http://localhost:8081/auth/verification/" + profileId;
        String formatText = "<style>\n" +
                "    a:link, a:visited {\n" +
                "        background-color: #f44336;\n" +
                "        color: white;\n" +
                "        padding: 14px 25px;\n" +
                "        text-align: center;\n" +
                "        text-decoration: none;\n" +
                "        display: inline-block;\n" +
                "    }\n" +
                "\n" +
                "    a:hover, a:active {\n" +
                "        background-color: red;\n" +
                "    }\n" +
                "</style>\n" +
                "<div style=\"text-align: center\">\n" +
                "    <h1>Welcome to kun.uz web portal</h1>\n" +
                "    <br>\n" +
                "    <p>Please button lick below to complete registration</p>\n" +
                "    <div style=\"text-align: center\">\n" +
                "        <a href=\"%s\" target=\"_blank\">This is a link</a>\n" +
                "    </div>";
        String text = String.format(formatText, url);
        mailSenderService.send(email, "Complete registration", text);
        emailHistoryService.crete(email, text); // create history
    }

    public String registrationResendByPhone(String phone) {
        Optional<ProfileEntity> optional = profileRepository.findByPhoneAndVisibleTrue(phone);
        if (optional.isEmpty()) {
            throw new AppBadException("Email not exists");
        }
        ProfileEntity entity = optional.get();

        if (!entity.getVisible() || !entity.getStatus().equals(ProfileStatus.REGISTRATION)) {
            throw new AppBadException("Registration not completed");
        }
        smsHistoryService.checkPhoneLimit(phone);
        smsService.sendSms(phone);
       // smsService.s(email, text); // create history        ????????????????????????????????????????
        return "To complete your registration please enter the code.";
    }

}


