package org.example.kun_uzz.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "category_entity")

public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(name = "name_uz")
    private String name_uz;
    @Column(name = "name_ru")
    private String name_ru;
    @Column(name = "name_en")
    private String name_en;
    @Column(name = "visible")
    private Boolean visible = Boolean.TRUE;
    @Column(name="orderNumber")
    private Integer orderNumber;
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
}
