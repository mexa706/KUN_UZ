package org.example.kun_uzz.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.kun_uzz.Enums.EmotionStatus;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "article_like")
public class ArticleLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "profile_id")
    private Integer profileId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;

    @Column(name = "article_id")
    private String articleId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", insertable = false, updatable = false)
    private ArticleEntity article;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EmotionStatus status;

}
