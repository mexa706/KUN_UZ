package org.example.kun_uzz.DTO.type;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class TypeDTO {
    private Integer id;
    private String nameUz;
    private String nameRu;
    private String nameEn;
    private Integer orderNumber;
    private Boolean visible;
    private LocalDateTime createdDate;
}
