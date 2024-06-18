package org.example.kun_uzz.mapper;

import java.time.LocalDateTime;

public interface ArticleShortInfoMapper {
    String getId();

    String getTitle();

    String getDescription();

    String getImageId();

    LocalDateTime getPublishedDate();
}
