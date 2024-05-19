package org.example.kun_uzz.DTO;

import lombok.Getter;
import lombok.Setter;
import org.example.kun_uzz.Enums.ProfileRole;
import org.example.kun_uzz.Enums.ProfileStatus;

import java.time.LocalDate;
@Getter
@Setter
public class ProfileDTO {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;
    private ProfileStatus status;
    private ProfileRole role;
    private Boolean visible;
    private LocalDate created_date;
    private Integer photo_id;
}
