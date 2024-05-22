package org.example.kun_uzz.Controller;

import jakarta.validation.Valid;
import org.example.kun_uzz.DTO.CategoryCreateDTO;
import org.example.kun_uzz.DTO.CategoryDTO;
import org.example.kun_uzz.DTO.TypeCreateDTO;
import org.example.kun_uzz.DTO.TypeDTO;
import org.example.kun_uzz.Enums.Language;
import org.example.kun_uzz.Service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @PostMapping("/create")
    public ResponseEntity<TypeDTO> create(@Valid @RequestBody TypeCreateDTO typeCreateDTO) {
        TypeDTO response = typeService.create(typeCreateDTO);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TypeDTO>> getAll() {
        List<TypeDTO> response = typeService.getAll();
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/lang")
    public ResponseEntity<List<TypeDTO>> getAllByLang(
            @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
        List <TypeDTO> response = typeService.getAllByLang(lang);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateRegion(@PathVariable("id") Integer id,
                                                @Valid  @RequestBody TypeDTO dto) {
        Boolean result = typeService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }


  /*  1. Create  (ADMIN)
        (order_number,name_uz, name_ru, name_en)
            2. Update by id (ADMIN)
        (order_number,name_uz, name_ru, name_en)
            3. Delete by id (ADMIN)
    4. Get List (ADMIN) (Pagination)
            (id,key,name_uz, name_ru, name_en,visible,created_date)
            5. Get By Lang (Language keladi shu language dagi name larini berib yuboramiz)
        (id,key,name) (name ga tegishli name_.. dagi qiymat qo'yiladi.)*/
}


