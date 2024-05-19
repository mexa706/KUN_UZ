package org.example.kun_uzz.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
public class RegionDTO {
    private Integer id;
    private Integer orderNumber;
    private String name_uz;
    private String name_ru;
    private String name_en;
    private Boolean visible;
    private String name;
    private LocalDateTime createdDate;
}
