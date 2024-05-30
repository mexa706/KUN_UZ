package org.example.kun_uzz.Controller;

import jakarta.validation.Valid;
import org.example.kun_uzz.DTO.region.RegionCreateDTO;
import org.example.kun_uzz.DTO.region.RegionDTO;
import org.example.kun_uzz.Enums.Language;
import org.example.kun_uzz.Enums.ProfileRole;
import org.example.kun_uzz.Service.RegionService;
import org.example.kun_uzz.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @PostMapping("/adm/create")
    public ResponseEntity<RegionDTO> create(@Valid @RequestBody RegionCreateDTO region,  @RequestHeader("Authorization") String token) {

        SecurityUtil.getJwtDTO(token, ProfileRole.ROLE_ADMIN);

        RegionDTO response = regionService.create(region);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/adm/all")
    public ResponseEntity<List<RegionDTO>> getAll(  @RequestHeader("Authorization") String token) {

        SecurityUtil.getJwtDTO(token, ProfileRole.ROLE_ADMIN);
        List<RegionDTO> response = regionService.getAll();
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/lang")
    public ResponseEntity<List<RegionDTO>> getAllByLang(
            @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang,  @RequestHeader("Authorization") String token) {

        SecurityUtil.getJwtDTO(token, ProfileRole.ROLE_ADMIN);
       List <RegionDTO> response = regionService.getAllByLang(lang);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/adm/update/{id}")
    public ResponseEntity<Boolean> updateRegion(@PathVariable("id") Integer id,
                                                @Valid  @RequestBody RegionCreateDTO dto,  @RequestHeader("Authorization") String token) {

        SecurityUtil.getJwtDTO(token, ProfileRole.ROLE_ADMIN);
        Boolean result = regionService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }
    @DeleteMapping("/adm/delete/{id}")
    public ResponseEntity<Boolean> deleteRegion(@PathVariable("id") Integer id,  @RequestHeader("Authorization") String token) {

        SecurityUtil.getJwtDTO(token, ProfileRole.ROLE_ADMIN);
        Boolean result = regionService.delete(id);
        return ResponseEntity.ok().body(result);
    }




}
