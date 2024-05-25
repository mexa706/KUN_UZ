package org.example.kun_uzz.Controller;

import jakarta.validation.Valid;
import org.example.kun_uzz.DTO.auth.RegistrationDTO;
import org.example.kun_uzz.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<String> registrationByPhone(@Valid @RequestBody RegistrationDTO dto) {
        String body = authService.registrationByPhone(dto);
        return ResponseEntity.ok().body(body);
    }
    @GetMapping("/verification/{userId}")
    public ResponseEntity<String> verificationByPhone(@PathVariable("userId") Integer userId) {
        String body = authService.authorizationVerificationByPhone(userId);
        return ResponseEntity.ok().body(body);
    }

    @GetMapping("/registration/resend/{email}")
    public ResponseEntity<String> registrationResend(@PathVariable("email") String email) {
        String body = authService.registrationResendByEmail(email);
        return ResponseEntity.ok().body(body);
    }






}
