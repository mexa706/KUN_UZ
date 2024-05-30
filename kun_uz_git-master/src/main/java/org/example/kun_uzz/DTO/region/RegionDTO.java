package org.example.kun_uzz.DTO.region;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class RegionDTO {
    private Integer id;
    private Integer orderNumber;
    private String nameUz;
    private String nameRu;
    private String nameEn;
    private Boolean visible;
    private String name;
    private LocalDateTime createdDate;
}
