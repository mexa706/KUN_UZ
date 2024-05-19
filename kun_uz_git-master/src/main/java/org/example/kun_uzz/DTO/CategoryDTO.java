package org.example.kun_uzz.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class CategoryDTO {
    private Integer id;
    private String name_uz;
    private String name_ru;
    private String name_en;
    private boolean visible;
    private LocalDateTime createdDate;
}
