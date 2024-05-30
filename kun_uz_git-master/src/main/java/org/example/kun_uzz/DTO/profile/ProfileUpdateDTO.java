package org.example.kun_uzz.DTO.profile;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileUpdateDTO {
    @NotBlank(message = "name not found")
    private String name;
    @NotBlank(message = "name not found")
    private String surname;
}
