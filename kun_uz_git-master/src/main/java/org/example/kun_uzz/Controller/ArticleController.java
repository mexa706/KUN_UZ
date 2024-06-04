package org.example.kun_uzz.Controller;


import jakarta.validation.Valid;
import org.example.kun_uzz.DTO.JwtDTO;
import org.example.kun_uzz.DTO.article.ArticleCreateDTO;
import org.example.kun_uzz.DTO.article.ArticleResponseDto;
import org.example.kun_uzz.Enums.ProfileRole;
import org.example.kun_uzz.Service.ArticleService;
import org.example.kun_uzz.exp.AppForbiddenException;
import org.example.kun_uzz.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.xml.transform.Result;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    // 1. CREATE (Moderator) status(NotPublished)
    @PostMapping("/moderator")
    public ResponseEntity<ArticleResponseDto> create(@Valid @RequestBody ArticleCreateDTO createDto) {
        return ResponseEntity.ok(articleService.createArticle(createDto));
    }

    // 2. Update (Moderator (status to not publish)) (remove old image) (title,description,content,shared_count,image_id, region_id,category_id)
  /*  @PostMapping("/adm/update/{id}")
    public ResponseEntity<Result> updateArticle(@RequestBody ArticleCreateDTO createDto,
                                                @RequestHeader("Authorization") String token,
                                                @PathVariable(value = "id") UUID articleId) {

        JwtDTO dto = SecurityUtil.getJwtDTO(token);
        if (!dto.getRole().equals(ProfileRole.ROLE_MODERATOR)) {
            throw new AppForbiddenException("Kechirasiz sizda bunday huquq yo'q");
        }

        Result result = articleService.updateArticle(createDto, dto.getId(),articleId);
        return ResponseEntity.status(result.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(result);
    }

    // 3. Delete Article (MODERATOR)
    @PostMapping("/delete/{id}")
    public ResponseEntity<Result> deleteArticle(@RequestHeader("Authorization") String token,
                                                @PathVariable(value = "id") UUID articleId) {

        JwtDTO dto = SecurityUtil.getJwtDTO(token);
        if (!dto.getRole().equals(ProfileRole.ROLE_MODERATOR)) {
            throw new AppForbiddenException("Kechirasiz sizda bunday huquq yo'q");
        }

        Result result = articleService.deleteArticle( dto.getId(),articleId);
        return ResponseEntity.status(result.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(result);
    }

    // 4. Change status by id (PUBLISHER) (publish,not_publish) bitta aqilliy tekshirib ruxsat berdi
    @PostMapping("/publisher/{id}")
    public ResponseEntity<Result> publisherArticle(@RequestHeader("Authorization") String token,
                                                   @PathVariable(value = "id") UUID articleId) {

        JwtDTO dto = SecurityUtil.getJwtDTO(token);
        if (!dto.getRole().equals(ProfileRole.ROLE_PUBLISHER)) {
            throw new AppForbiddenException("Kechirasiz sizda bunday huquq yo'q");
        }

        Result result = articleService.publisherArticle( dto.getId(),articleId);
        return ResponseEntity.status(result.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(result);
    }

    // 5. Get Last 5 Article By Types  ordered_by_created_date
    @GetMapping("/get-list-top5")
    public ResponseEntity<List<ArticleResponseDto>> getLast5ArticleByTypes(@RequestParam UUID types, @RequestHeader("Accept-Language")LanguageEnum languageEnum){
        List<ArticleResponseDto> responseDtoList=articleService.getLast5ArticleByTypes(types,languageEnum);
        return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
    }
*/
}
