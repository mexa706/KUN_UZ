package org.example.kun_uzz.DTO;

import lombok.Getter;
import lombok.Setter;
import org.example.kun_uzz.Enums.ProfileRole;
@Getter
@Setter
public class JwtDTO {
    private Integer id;
    private String username;
    private ProfileRole role;

    public JwtDTO(Integer id) {
        this.id = id;
    }

    public JwtDTO(Integer id, ProfileRole role, String username) {
        this.id = id;
        this.username=username;
        this.role = role;
    }


}
