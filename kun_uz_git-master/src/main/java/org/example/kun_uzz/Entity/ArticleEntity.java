package org.example.kun_uzz.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.kun_uzz.Enums.ArticleStatus;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "article")
@Getter
@Setter

public class ArticleEntity {
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;


    @Column(name = "title")
    private String title;


    @Column(name = "description")
    private String description;


    @Column(name = "content")
    private String content;


    @Column(name = "image_id")
    private Integer imageId;


    @Column(name = "moderator_id")
    private Integer moderatorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moderator_id", insertable = false, updatable = false)
    private ProfileEntity moderator;

    @Column(name = "region_id")
    private Integer regionId;
    @ManyToOne
    @JoinColumn(name = "region_id", insertable = false, updatable = false)
    private RegionEntity region;


    @Column(name = "category_id")
    private Integer categoryId;
    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity category;


    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ArticleStatus status;

}
