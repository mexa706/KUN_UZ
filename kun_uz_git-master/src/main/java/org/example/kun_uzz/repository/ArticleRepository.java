package org.example.kun_uzz.repository;

import org.example.kun_uzz.Entity.ArticleEntity;
import org.example.kun_uzz.mapper.ArticleShortInfoMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ArticleRepository extends JpaRepository<ArticleEntity, String> {

    Optional<ArticleEntity> findByIdAndVisibleTrue(String id);

    // 5.
    @Query(value = "select * from article as a\n" +
            "inner join article_types as ats  on ats.article_id = a.id\n" +
            "where types_id = ?1 and visible = true and status = 'PUBLISHED'\n" +
            "order by created_date desc\n" +
            "limit 5", nativeQuery = true)
    List<ArticleEntity> getLast5ByTypesIdNative(Integer typesId);

    // 5.6
//            "inner join ArticleTypesEntity as ats  on ats.articleId = a.id " +

    @Query(value = "" +
            " SELECT a.id,a.title,a.description,a.imageId,a.publishedDate " +
            " from ArticleEntity as a " +
            "inner join a.articleTypes as ats " +
            "where ats.typesId= ?1 and a.visible = true and a.status = 'PUBLISHED' " +
            "order by a.createDate desc " +
            " limit ?2 ")
    List<ArticleShortInfoMapper> getByTypesId(Integer typesId, int limit);

    // 7. Get Last 8  Articles witch id not included in given list.
    @Query(value = " SELECT a.id,a.title,a.description,a.imageId,a.publishedDate " +
            " from ArticleEntity as a " +
            " where a.visible = true and a.status = 'PUBLISHED' " +
            " and a.id not in ?1 " +
            " order by a.createDate desc " +
            " limit 8 ")
    List<ArticleShortInfoMapper> getLast8(List<String> ids);
    // select *
    // from article ae1_0 join article_types at1_0 on ae1_0.id=at1_0.article_id
    // where at1_0.types_id=? and
    // ae1_0.visible=true and
    // ae1_0.status='PUBLISHED'
    // order by ae1_0.create_date desc
    // fetch first 5 rows only


}
