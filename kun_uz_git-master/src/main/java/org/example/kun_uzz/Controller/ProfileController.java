package org.example.kun_uzz.Controller;

import jakarta.validation.Valid;
import org.example.kun_uzz.DTO.ProfileCreateDTO;
import org.example.kun_uzz.DTO.ProfileDTO;
import org.example.kun_uzz.DTO.RegionCreateDTO;
import org.example.kun_uzz.DTO.RegionDTO;
import org.example.kun_uzz.Entity.ProfileEntity;
import org.example.kun_uzz.Service.ProfileService;
import org.example.kun_uzz.Service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.StyledEditorKit;
import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService ProfileService;
    @Autowired
    private ProfileService profileService;

    @PostMapping("/create")
    public ResponseEntity<ProfileDTO> create(@Valid @RequestBody ProfileCreateDTO profile) {
        ProfileDTO response = profileService.create(profile);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProfileDTO>> getAll() {
        List<ProfileDTO> response = profileService.getAll();
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ProfileDTO> update(@PathVariable Integer id, @RequestBody ProfileCreateDTO profileCreateDTO) {
        ProfileDTO response = profileService.update(id, profileCreateDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping (value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
       Boolean response =profileService.delete(id);
        return ResponseEntity.ok(response);
    }


}
