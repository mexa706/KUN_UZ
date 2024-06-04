package org.example.kun_uzz.util;


import org.example.kun_uzz.DTO.JwtDTO;
import org.example.kun_uzz.Entity.ProfileEntity;
import org.example.kun_uzz.Enums.ProfileRole;
import org.example.kun_uzz.config.CustomUserDetail;
import org.example.kun_uzz.exp.AppForbiddenException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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

    public static Integer getProfileId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail user = (CustomUserDetail) authentication.getPrincipal();
        return user.getProfile().getId();
    }

    public static ProfileEntity getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail user = (CustomUserDetail) authentication.getPrincipal();
        return user.getProfile();
    }


}
