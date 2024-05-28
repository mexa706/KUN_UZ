package org.example.kun_uzz.Controller;

import jakarta.validation.Valid;
import org.example.kun_uzz.DTO.*;
import org.example.kun_uzz.Entity.ProfileEntity;
import org.example.kun_uzz.Enums.ProfileRole;
import org.example.kun_uzz.Service.ProfileService;
import org.example.kun_uzz.Service.RegionService;
import org.example.kun_uzz.util.SecurityUtil;
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
    public ResponseEntity<ProfileDTO> create(@Valid @RequestBody ProfileCreateDTO profile,
                                             @RequestHeader("Authorization") String token) {

        SecurityUtil.getJwtDTO(token, ProfileRole.ROLE_ADMIN);

        ProfileDTO response = profileService.create(profile);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProfileDTO>> getAll() {
        List<ProfileDTO> response = profileService.getAll();
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(value = "/current")
    public ResponseEntity<ProfileDTO> update(@Valid @RequestBody ProfileUpdateDTO profileUpdateDTO,
                                             @RequestHeader("Authorization") String token) {

        ProfileDTO response = profileService.update(SecurityUtil.getJwtDTO(token).getId(),profileUpdateDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping (value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
       Boolean response =profileService.delete(id);
        return ResponseEntity.ok(response);
    }


}
