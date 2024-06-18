package org.example.kun_uzz.Controller;

import jakarta.validation.Valid;
import org.example.kun_uzz.DTO.article.ArticleCreateDto;
import org.example.kun_uzz.DTO.article.ArticleResponseDto;
import org.example.kun_uzz.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    // 1. CREATE (Moderator) status(NotPublished)
    @PreAuthorize("hasRole('MODERATOR')")
    @PostMapping("/moderator")
    public ResponseEntity<ArticleResponseDto> create(@Valid @RequestBody ArticleCreateDto createDto) {
        return ResponseEntity.ok(articleService.createArticle(createDto));
    }

    @PreAuthorize("hasAnyRole('MODERATOR','PUBLISHER')")
    @PutMapping("/moderator/{id}")
    public ResponseEntity<ArticleResponseDto> update(@Valid @RequestBody ArticleCreateDto createDto,
                                                     @PathVariable("id") String id) {
        return ResponseEntity.ok(articleService.update(id, createDto));
    }

    @GetMapping("/types/{id}")
    public ResponseEntity<List<ArticleResponseDto>> last5ByTypesId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(articleService.getLast5ByTypes(id));
    }

    @PostMapping("/last/eight")
    public ResponseEntity<List<ArticleResponseDto>> last8Article(@RequestBody List<String> idList) {
        return ResponseEntity.ok(articleService.getLast8Article(idList));
    }

    //  GET /publish/{articleId} - PUBLISHER
    //  DELETE /adm/{artileId} - ADMIN


}
