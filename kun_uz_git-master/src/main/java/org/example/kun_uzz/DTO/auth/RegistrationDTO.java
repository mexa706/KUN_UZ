package org.example.kun_uzz.DTO.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDTO {
    @NotBlank(message = "name not found")
    private String name;
    @NotBlank(message = "surname not found")
    private String surname;
    @NotBlank(message = "email not found")
    private String email;
    @NotBlank
    private String phone;
    @NotBlank(message = "passord not found")
    private String password;

}
