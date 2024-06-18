package org.example.kun_uzz.Service;

import org.example.kun_uzz.DTO.article.ArticleCreateDto;
import org.example.kun_uzz.DTO.article.ArticleResponseDto;
import org.example.kun_uzz.Entity.*;
import org.example.kun_uzz.Enums.ArticleStatus;
import org.example.kun_uzz.Enums.Language;
import org.example.kun_uzz.exp.AppBadException;
import org.example.kun_uzz.mapper.ArticleShortInfoMapper;
import org.example.kun_uzz.repository.*;
import org.example.kun_uzz.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ArticleTypesService articleTypesService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private ArticleLikeRepository articleLikeRepository;

    @Autowired
    private AttachService attachService;

//    @Autowired
//    private ArticleTypeRepository articleTypeRepository;

    // 1 . CREATE (Moderator) status(NotPublished)
    public ArticleResponseDto createArticle(ArticleCreateDto createDto) {
        ProfileEntity moderator = SecurityUtil.getProfile();

        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setTitle(createDto.getTitle());
        articleEntity.setDescription(createDto.getDescription());
        articleEntity.setContent(createDto.getContent());
        articleEntity.setImageId(createDto.getImageId());
        articleEntity.setRegionId(createDto.getRegionId());
        articleEntity.setCategoryId(createDto.getCategoryId());
        articleEntity.setModeratorId(moderator.getId());
        // articleEntity.setStatus(ArticleStatus.NOT_PUBLISHED);
        articleRepository.save(articleEntity);
        articleTypesService.create(articleEntity.getId(), createDto.getArticleTypeIds());

        return toDTO(articleEntity);
    }

    public ArticleResponseDto update(String articleId, ArticleCreateDto dto) {
        ArticleEntity entity = get(articleId);
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setImageId(dto.getImageId());
        entity.setRegionId(dto.getRegionId());
        entity.setCategoryId(dto.getCategoryId());
        entity.setStatus(ArticleStatus.NOT_PUBLISHED);
        articleRepository.save(entity);

        articleTypesService.merge(articleId, dto.getArticleTypeIds());
        return toDTO(entity);
    }

    public ArticleResponseDto toDTO(ArticleEntity entity) {
        ArticleResponseDto dto = new ArticleResponseDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setContent(entity.getContent());
        dto.setImageId(entity.getImageId());
        return dto;
    }


    public List<ArticleResponseDto> getLast5ByTypes(Integer typesId) {
        // id(uuid),title,description,image(id,url),published_date
        List<ArticleShortInfoMapper> mapperList = articleRepository.getByTypesId(typesId, 5);
        List<ArticleResponseDto> dtoList = new LinkedList<>();
        for (ArticleShortInfoMapper mapper : mapperList) {
            dtoList.add(toDTO(mapper));
        }
        return dtoList;
    }

    public List<ArticleResponseDto> getLast3ByTypes(Integer typesId) {
        List<ArticleShortInfoMapper> mapperList = articleRepository.getByTypesId(typesId, 3);
        return mapperList.stream()
                .map(mapper -> toDTO(mapper))
                .collect(Collectors.toList());
    }

    public List<ArticleResponseDto> getLast8Article(List<String> idList) {
        List<ArticleShortInfoMapper> mapperList = articleRepository.getLast8(idList);
        return mapperList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ArticleResponseDto getById(String id, Language lang) {
        ArticleEntity entity = get(id);
        if (!entity.getStatus().equals(ArticleStatus.PUBLISHED)) {
            throw new AppBadException("Article not found");
        }
        ArticleResponseDto dto = new ArticleResponseDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setContent(entity.getContent());
        dto.setSharedCount(entity.getSharedCount());
        dto.setRegion(regionService.getRegion(entity.getRegionId(), lang));
        // dto.setCategory(categoryService.getCategory(entity.getCategoryId(), lang));
        dto.setPublishedDate(entity.getPublishedDate());
        dto.setViewCount(entity.getViewCount());
        dto.setLikeCount(articleLikeRepository.getArticleLikeCount(id)); // TODO
        // tagList(name)
        return dto;
    }


    public ArticleResponseDto toDTO(ArticleShortInfoMapper mapper) {
        ArticleResponseDto dto = new ArticleResponseDto();
        dto.setId(mapper.getId());
        dto.setTitle(mapper.getTitle());
        dto.setDescription(mapper.getDescription());
        dto.setPublishedDate(mapper.getPublishedDate());
        dto.setImage(attachService.getDTOWithURL(mapper.getImageId()));
        return dto;
    }

    public ArticleEntity get(String id) {
        return articleRepository.findByIdAndVisibleTrue(id).orElseThrow(() -> {
            throw new AppBadException("Article not found");
        });
    }

}
