package org.example.kun_uzz.Controller;

import jakarta.validation.Valid;
import org.example.kun_uzz.DTO.RegionCreateDTO;
import org.example.kun_uzz.DTO.RegionDTO;
import org.example.kun_uzz.Enums.Language;
import org.example.kun_uzz.Service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @PostMapping("/create")
    public ResponseEntity<RegionDTO> create(@Valid @RequestBody RegionCreateDTO region) {
        RegionDTO response = regionService.create(region);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/all")
    public ResponseEntity<List<RegionDTO>> getAll() {
        List<RegionDTO> response = regionService.getAll();
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/lang")
    public ResponseEntity<List<RegionDTO>> getAllByLang(
            @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
       List <RegionDTO> response = regionService.getAllByLang(lang);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateRegion(@PathVariable("id") Integer id,
                                                @Valid  @RequestBody RegionCreateDTO dto) {
        Boolean result = regionService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteRegion(@PathVariable("id") Integer id) {
        Boolean result = regionService.delete(id);
        return ResponseEntity.ok().body(result);
    }




}
