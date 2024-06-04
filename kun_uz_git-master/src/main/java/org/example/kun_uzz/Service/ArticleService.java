package org.example.kun_uzz.Service;

import org.example.kun_uzz.DTO.article.ArticleCreateDTO;
import org.example.kun_uzz.DTO.article.ArticleDTO;
import org.example.kun_uzz.DTO.article.ArticleResponseDto;
import org.example.kun_uzz.Entity.*;
import org.example.kun_uzz.Enums.ArticleStatus;
import org.example.kun_uzz.exp.AppBadException;
import org.example.kun_uzz.exp.AppForbiddenException;
import org.example.kun_uzz.repository.*;
import org.example.kun_uzz.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.xml.transform.Result;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ArticleTypesService articleTypesService;

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private RegionService regionService;


    // 1 . CREATE (Moderator) status(NotPublished)
    public ArticleResponseDto createArticle(ArticleCreateDTO createDto) {

        RegionEntity regionEntity = regionService.get(createDto.getRegionId());

        Integer moderatorId = SecurityUtil.getProfileId();


        Optional<CategoryEntity> categoryEntityOptional = categoryRepository.findById(createDto.getCategoryId());
        if (categoryEntityOptional.isEmpty()) {
            throw new AppBadException("Bunday category mavjud emas");
        }
        //
      /*  Optional<ProfileEntity> profileEntityOptional = profileRepository.findById(profileId);
        if (profileEntityOptional.isEmpty()) {
            throw new AppBadException("Bunday hodim mavjud emas");
        }*/

        //  List<ArticleTypeEntity> allById = articleTypeRepository.findAllById(createDto.get());


        ArticleEntity articleEntity = new ArticleEntity();

        articleEntity.setId(UUID.randomUUID().toString());
        articleEntity.setTitle(createDto.getTitle());
        articleEntity.setDescription(createDto.getDescription());
        articleEntity.setContent(createDto.getContent());
        articleEntity.setImageId(createDto.getImageId());
        articleEntity.setRegionId(createDto.getRegionId());
        articleEntity.setCategoryId(createDto.getCategoryId());
      //  articleEntity.setModerator(profileEntityOptional.get());
        articleEntity.setStatus(ArticleStatus.NOT_PUBLISHED);


        articleRepository.save(articleEntity);
articleTypesService.create(articleEntity.getId(),createDto.getArticleTypeIds());


        return toDTO(articleEntity);
    }

    public ArticleResponseDto toDTO(ArticleEntity articleEntity) {
        ArticleResponseDto dto = new ArticleResponseDto();
        dto.setId(articleEntity.getId());
        dto.setTitle(articleEntity.getTitle());
        dto.setDescription(articleEntity.getDescription());
        dto.setContent(articleEntity.getContent());
      //  dto.setCategoryId(articleEntity.getCategoryId());
       // dto.setRegionId(articleEntity.getRegionId());
        dto.setImageId(articleEntity.getImageId());
        return dto;

    }



    //   // 2. Update (Moderator (status to not publish)) (remove old image) (title,description,content,shared_count,image_id, region_id,category_id)
  /*  public Result updateArticle(ArticleCreateDto createDto, Integer profileId, UUID articleId) {
        Optional<ArticleEntity> articleEntityOptional = articleRepository.findById(articleId);
        if (articleEntityOptional.isEmpty()) {
            return new Result("Bunday article mavjud emas", false);
        }

        Optional<RegionEntity> regionEntityOptional = regionRepository.findById(createDto.getRegionId());
        if (regionEntityOptional.isEmpty()) {
            return new Result("Bunday ragion mavjud emas", false);
        }

        //
        Optional<CategoryEntity> categoryEntityOptional = categoryRepository.findById(createDto.getCategoryId());
        if (categoryEntityOptional.isEmpty()) {
            return new Result("Bunday category mavjud emas", false);
        }
        //
        Optional<ProfileEntity> profileEntityOptional = profileRepository.findById(profileId);
        if (profileEntityOptional.isEmpty()) {
            return new Result("Bunday hodim mavjud emas", false);
        }

        List<ArticleTypeEntity> allById = articleTypeRepository.findAllByIdIn(createDto.getArticleTypeIds());

        ArticleEntity articleEntity = articleEntityOptional.get();

        articleEntity.setTitle(createDto.getTitle());
        articleEntity.setDescription(createDto.getDescription());
        articleEntity.setContent(createDto.getContent());
        articleEntity.setImageId(createDto.getImageId());

        articleEntity.setRegion(regionEntityOptional.get());
        articleEntity.setCategory(categoryEntityOptional.get());
        articleEntity.setModerator(profileEntityOptional.get());
        articleEntity.setArticleType(allById);
        return new Result("Maqola o'zgartirildi", true);
    }

    // 3. Delete Article (MODERATOR)
    public Result deleteArticle(Integer id, UUID articleId) {
        Optional<ArticleEntity> articleEntityOptional = articleRepository.findById(articleId);
        if (articleEntityOptional.isEmpty()) {
            return new Result("Bunday article mavjud emas", false);
        }
        Optional<ProfileEntity> profileEntityOptional = profileRepository.findById(id);
        if (profileEntityOptional.isEmpty()) {
            return new Result("Bunday hodim mavjud emas", false);
        }
        ArticleEntity articleEntity = articleEntityOptional.get();
//        articleRepository.delete(articleEntity);
        articleEntity.setVisible(false);
        articleRepository.save(articleEntity);
        return new Result("Maqola o'chirildi", true);
    }

    // 4. Change status by id (PUBLISHER) (publish,not_publish)
    public Result publisherArticle(Integer id, UUID articleId) {

        Optional<ArticleEntity> articleEntityOptional = articleRepository.findById(articleId);
        if (articleEntityOptional.isEmpty()) {
            return new Result("Bunday article mavjud emas", false);
        }
        Optional<ProfileEntity> profileEntityOptional = profileRepository.findById(id);
        if (profileEntityOptional.isEmpty()) {
            return new Result("Bunday hodim mavjud emas", false);
        }
        ArticleEntity articleEntity = articleEntityOptional.get();
        articleEntity.setStatus(ArticleStatus.PUBLISHED);
        articleEntity.setPublishedDate(LocalDateTime.now());
        articleEntity.setPublisher(profileEntityOptional.get());
        articleRepository.save(articleEntity);
        return new Result("Maqolaga ruxsad berildi", true);
    }

    // 5. Get Last 5 Article By Types  ordered_by_created_date
    public List<ArticleResponseDto> getLast5ArticleByTypes(UUID type, LanguageEnum lang) {

        List<ArticleResponseDto> articleResponseDtoList = new ArrayList<>();

        for (ArticleEntity articleEntity : articleRepository.findTop5ByIdAndVisibleTrueOrderByCreateDateDesc(type)) {
            ArticleResponseDto articleResponseDto = new ArticleResponseDto();
            articleResponseDto.setTitle(articleEntity.getTitle());
            articleResponseDto.setDescription(articleEntity.getDescription());
            articleResponseDto.setContent(articleEntity.getContent());
            articleResponseDto.setImageId(articleEntity.getImageId());
            articleResponseDto.setViewCount(articleEntity.getViewCount());
            articleResponseDto.setCreateDate(articleEntity.getCreateDate());
            articleResponseDto.setPublished_date(articleEntity.getPublishedDate());

            RegionResponseDTO regionDTO = new RegionResponseDTO();
            regionDTO.setId(articleEntity.getRegion().getId());

            switch (lang) {
                case UZ -> regionDTO.setName(articleEntity.getRegion().getNameUz());
                case RU -> regionDTO.setName(articleEntity.getRegion().getNameRu());
                case EN -> regionDTO.setName(articleEntity.getRegion().getNameEn());
            }

            articleResponseDto.setRegion(regionDTO);
            articleResponseDtoList.add(articleResponseDto);

        }

        return articleResponseDtoList;
    }*/
}
