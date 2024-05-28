package org.example.kun_uzz.util;

import org.example.kun_uzz.DTO.JwtDTO;
import org.example.kun_uzz.Enums.ProfileRole;
import org.example.kun_uzz.exp.AppForbiddenException;

public class SecurityUtil {

    public static JwtDTO getJwtDTO(String token) {
        String jwt = token.substring(7); // Bearer eyJhb
        JwtDTO dto = JwtUtill.decode(jwt);
        return dto;
    }
    public static JwtDTO getJwtDTO(String token, ProfileRole requiredRole) {
        JwtDTO dto = getJwtDTO(token);
        if(!dto.getRole().equals(requiredRole)){
            throw new AppForbiddenException("Method not allowed");
        }
        return dto;
    }

}
