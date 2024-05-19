package org.example.kun_uzz.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.kun_uzz.Enums.ProfileRole;
import org.example.kun_uzz.Enums.ProfileStatus;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "profile_entity")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(name = "name_uz")
    private String name;
    @Column(name = "surname_uz")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "password")
    private String password;
    @Column(name = "status")
    private ProfileStatus status=ProfileStatus.ACTIVE;
    @Column(name = "role")
    private ProfileRole role;
    @Column(name = "photo_id")
    private Integer photo_id;
    @Column(name = "visible")
    private Boolean visible = Boolean.TRUE;;
    @Column(name = "created_date")
    private LocalDate created_date=LocalDate.now();
}

