package org.example.kun_uzz.Controller;

import jakarta.validation.Valid;
import org.example.kun_uzz.DTO.profile.ProfileCreateDTO;
import org.example.kun_uzz.DTO.profile.ProfileDTO;
import org.example.kun_uzz.DTO.profile.ProfileUpdateDTO;
import org.example.kun_uzz.Enums.ProfileRole;
import org.example.kun_uzz.Service.ProfileService;
import org.example.kun_uzz.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/create")
    public ResponseEntity<ProfileDTO> create(@Valid @RequestBody ProfileCreateDTO profile) {
        ProfileDTO response = profileService.create(profile);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProfileDTO>> getAll(@RequestHeader("Authorization") String token) {

        SecurityUtil.getJwtDTO(token, ProfileRole.ROLE_ADMIN);

        List<ProfileDTO> response = profileService.getAll();
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(value = "/current")
    public ResponseEntity<ProfileDTO> update(@Valid @RequestBody ProfileUpdateDTO profileUpdateDTO,
                                             @RequestHeader("Authorization") String token) {

        ProfileDTO response = profileService.update(SecurityUtil.getJwtDTO(token).getId(), profileUpdateDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestHeader("Authorization") String token) {

        Boolean response = profileService.delete(SecurityUtil.getJwtDTO(token).getId());
        return ResponseEntity.ok(response);
    }

 /*   @GetMapping("/all_with_pagination")
    public ResponseEntity<PageImpl<ProfileDTO>> getAllWithPagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                     @RequestParam(value = "size", defaultValue = "10") int size,
                                                                     @RequestHeader("Authorization") String token) {
        SecurityUtil.getJwtDTO(token, ProfileRole.ROLE_ADMIN);
        PageImpl<ProfileDTO> pageList = profileService.getAllWithPagination(page - 1, size);
        return ResponseEntity.ok().body(pageList);
    }*/

}
