package org.example.kun_uzz.DTO.profile;

import lombok.Getter;
import lombok.Setter;
import org.example.kun_uzz.Enums.ProfileRole;

@Getter
@Setter

public class ProfileCreateDTO {

    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;
    private Integer photo_id;
    private ProfileRole role;
}
