package org.example.kun_uzz.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.kun_uzz.Enums.ArticleStatus;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "article")
@Getter
@Setter

public class ArticleEntity {
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "title")      //   :)  davayyyyyyyyyy
    private String title;       // Yangilikning nomi

    @Column(name = "description")
    private String description; //  Yangilik haqida qisqacha malumot

    @Column(columnDefinition = "text")
    private String content;       // Malumotni to'liq qismi

    @Column(name = "shared_count")
    private Integer sharedCount;  // Yangilikni ulashilganlar soni

    @Column(name = "view_count")
    private Integer viewCount;    // Yangilikni ko'rilganlar soni

    @Column(name = "like_count")
    private Long likeCount;    //
    @Column(name = "dislike_count")
    private Long dislikeCount;    //

    @Column(name = "image_id")
    private String imageId;      // Yangilikni rasmining Id si
    @ManyToOne
    @JoinColumn(name = "image_id", insertable = false, updatable = false)
    private AttachEntity attach;

    @Column(name = "create_date")
    private LocalDateTime createDate = LocalDateTime.now();  // Yangilikni yozilgan vaqti

    @Column(name = "published_date")
    private LocalDateTime publishedDate;        // Yangilik tahrir(tekshiruv)dan o'tgan va hammaga ko'rsatilgan vaqti

    @Column(name = "visible")
    private Boolean visible = Boolean.TRUE;

    @Column(name = "region_id")
    private Integer regionId;
    @ManyToOne
    @JoinColumn(name = "region_id", insertable = false, updatable = false)
    private RegionEntity region;                  // BU yangilik qayer(region) da sodir bo'ldi

    @Column(name = "category_id")
    private Integer categoryId;
    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity category;               // BU yangilik qanday category ga tegishli  bo'ladi

    @Column(name = "moderator_id")
    private Integer moderatorId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moderator_id", insertable = false, updatable = false)
    private ProfileEntity moderator;                // Yangilikni yozgan odam

    @Column(name = "publisher_id")
    private Integer publisherId;
    @ManyToOne
    @JoinColumn(name = "publisher_id", insertable = false, updatable = false)
    private ProfileEntity publisher;                // Yangilikni tahrir(tekshirgan) odam

    @OneToMany(mappedBy = "article")
    private List<ArticleTypesEntity> articleTypes;

    @Enumerated(EnumType.STRING)
    private ArticleStatus status = ArticleStatus.NOT_PUBLISHED;

}
