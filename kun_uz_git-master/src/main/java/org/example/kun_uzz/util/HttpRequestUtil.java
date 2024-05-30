package org.example.kun_uzz.util;

import jakarta.servlet.http.HttpServletRequest;
import org.example.kun_uzz.DTO.JwtDTO;
import org.example.kun_uzz.Enums.ProfileRole;
import org.example.kun_uzz.exp.AppForbiddenException;

public class HttpRequestUtil {


    public static JwtDTO getJwtDTO(HttpServletRequest request) {
        Integer id = (Integer) request.getAttribute("id");
        ProfileRole role = (ProfileRole) request.getAttribute("role");
        JwtDTO dto = new JwtDTO(id,role);
        return dto;
    }
    public static JwtDTO getJwtDTO(HttpServletRequest request, ProfileRole requiredRole) {
        Integer id = (Integer) request.getAttribute("id");
        ProfileRole role = (ProfileRole) request.getAttribute("role");
        JwtDTO dto = new JwtDTO(id, role);

        if (!dto.getRole().equals(requiredRole)) {
            throw new AppForbiddenException("Method not allowed");
        }
        return dto;
    }


}
