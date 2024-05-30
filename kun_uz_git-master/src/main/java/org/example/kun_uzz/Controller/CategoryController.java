package org.example.kun_uzz.Controller;

import jakarta.validation.Valid;
import org.example.kun_uzz.DTO.category.CategoryCreateDTO;
import org.example.kun_uzz.DTO.category.CategoryDTO;
import org.example.kun_uzz.Enums.Language;
import org.example.kun_uzz.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryCreateDTO categoryCreateDTO) {
        CategoryDTO response = categoryService.create(categoryCreateDTO);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<CategoryDTO> response = categoryService.getAll();
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/lang")
    public ResponseEntity<List<CategoryDTO>> getAllByLang(
            @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
        List <CategoryDTO> response = categoryService.getAllByLang(lang);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateRegion(@PathVariable("id") Integer id,
                                                @Valid  @RequestBody CategoryDTO dto) {
        Boolean result = categoryService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }


  /*  1. Create  (ADMIN)
        (order_number,name_uz, name_ru, name_en)
            2. Update by id (ADMIN)
        (order_number,name_uz, name_ru, name_en)
            3. Delete by id (ADMIN)
    4. Get List (ADMIN) - order by order_number
            (id,order_number,name_uz, name_ru, name_en,visible,created_date)
    5. Get By Lang (Language keladi shu language dagi name larini berib yuboramiz)
        (id,order_number,name) (name ga tegishli name_... dagi qiymat qo'yiladi.)*/
}
