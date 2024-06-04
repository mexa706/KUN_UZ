package org.example.kun_uzz.repository;

import org.example.kun_uzz.Entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ArticleRepository extends JpaRepository<ArticleEntity, String> {

    List<ArticleEntity> findTop5ByIdAndVisibleTrueOrderByCreateDateDesc(UUID id);

}
