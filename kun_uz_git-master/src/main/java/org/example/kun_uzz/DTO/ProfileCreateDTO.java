package org.example.kun_uzz.DTO;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.example.kun_uzz.Enums.ProfileRole;
import org.example.kun_uzz.Enums.ProfileStatus;
@Getter
@Setter
public class ProfileCreateDTO {

    @NotBlank(message = "Имя не должно быть пустым")
    private String name;

    @NotBlank(message = "Фамилия не должна быть пустой")
    private String surname;

    @NotBlank(message = "Email не должен быть пустым")
    @Email(message = "Некорректный формат email")
    private String email;

    @NotBlank(message = "Телефон не должен быть пустым")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Некорректный формат телефона")
    private String phone;

    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 6, message = "Пароль должен быть не менее 6 символов")
    private String password;


    @NotNull(message = "ID фото не должен быть null")
    private Integer photo_id;
}
