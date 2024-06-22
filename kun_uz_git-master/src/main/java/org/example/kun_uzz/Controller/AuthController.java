package org.example.kun_uzz.Controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.kun_uzz.DTO.profile.ProfileDTO;
import org.example.kun_uzz.DTO.auth.LoginDTO;
import org.example.kun_uzz.DTO.auth.RegistrationDTO;
import org.example.kun_uzz.Service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;



    @PostMapping("/registrationByPhone")
    public ResponseEntity<String> registrationByPhone(@Valid @RequestBody RegistrationDTO dto) {
        String body = authService.registrationByPhone(dto);
        log.info("Registration name = {}  phone = {} ",dto.getName(),dto.getPhone());
        return ResponseEntity.ok().body(body);
    }
    @GetMapping("/verificationByPhone/{userId}")
    public ResponseEntity<String> verificationByPhone(@PathVariable("userId") Integer userId) {
        String body = authService.authorizationVerificationByPhone(userId);
        return ResponseEntity.ok().body(body);
    }

    @GetMapping("/registrationByEmail/resend/{email}")
    public ResponseEntity<String> registrationResendByEmail(@PathVariable("email") String email) {
        String body = authService.registrationResendByEmail(email);

        return ResponseEntity.ok().body(body);
    }

    @GetMapping("/registrationByPhone/resend/{phone}")
    public ResponseEntity<String> registrationResendByPhone(@PathVariable("phone") String phone) {
        String body = authService.registrationResendByPhone(phone);
        return ResponseEntity.ok().body(body);
    }

    @PostMapping("/registrationByEmail")
    public ResponseEntity<String> registrationByEmail(@Valid @RequestBody RegistrationDTO dto) {
        String body = authService.registrationByEmail(dto);
        return ResponseEntity.ok().body(body);
    }
    @GetMapping("/verificationByEmail/{userId}")
    public ResponseEntity<String> verificationByEmail(@PathVariable("userId") Integer userId) {
        String body = authService.authorizationVerificationByEmail(userId);
        return ResponseEntity.ok().body(body);
    }

    @PostMapping("/login")
    public ResponseEntity<ProfileDTO> registrationByEmail(@Valid @RequestBody LoginDTO dto) {
        ProfileDTO body = authService.login(dto);
        return ResponseEntity.ok().body(body);
    }



}
