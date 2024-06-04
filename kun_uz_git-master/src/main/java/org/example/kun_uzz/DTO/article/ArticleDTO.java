package org.example.kun_uzz.DTO.article;

import lombok.Getter;
import lombok.Setter;
import org.example.kun_uzz.Enums.ArticleStatus;

@Getter
@Setter
public class ArticleDTO {
    //title,description,content,image_id, region_id,category_id, articleType(array)
    private String  id;
    private String title;
    private String description;
    private String content;
    private Integer imageId;
    private Integer regionId;
    private Integer categoryId;
    private Integer[] articleTypes;
    private ArticleStatus status;
}
