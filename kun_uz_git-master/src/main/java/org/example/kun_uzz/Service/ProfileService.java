package org.example.kun_uzz.Service;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.example.kun_uzz.DTO.profile.ProfileCreateDTO;
import org.example.kun_uzz.DTO.profile.ProfileDTO;
import org.example.kun_uzz.DTO.profile.ProfileUpdateDTO;
import org.example.kun_uzz.Entity.ProfileEntity;
import org.example.kun_uzz.Enums.ProfileRole;
import org.example.kun_uzz.exp.AppBadException;
import org.example.kun_uzz.repository.ProfileRepository;
import org.example.kun_uzz.util.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public ProfileDTO create(ProfileCreateDTO dto) {
        ProfileEntity entity = new ProfileEntity();


        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setRole(dto.getRole());
        entity.setRole(dto.getRole());
        entity.setPassword(MD5.getMD5(dto.getPassword()));
        entity.setPhotoId(dto.getPhoto_id());

        profileRepository.save(entity);
        return toDTO(entity);
    }

    public ProfileDTO update(Integer id , ProfileUpdateDTO dto) {



        ProfileEntity entity = new ProfileEntity();

        entity=get(id);

        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());


        profileRepository.save(entity);

        return toDTO(entity);
    }

    public Boolean delete(Integer id) {

        profileRepository.deleteById(id);

        return true;
    }

    public ProfileEntity get(Integer id) {
        return profileRepository.findById(id).orElseThrow(() -> {
            log.error("Profile not found id = {}", id);
            throw new AppBadException("Profile not found");
        });
    }


    public List<ProfileDTO> getAll() {
        Iterable<ProfileEntity> regions = profileRepository.findAll();
        List<ProfileDTO> list = new ArrayList<>();
        for (ProfileEntity region : regions) {
            list.add(toDTO(region));
        }
        return list;
    }


    public ProfileDTO toDTO(ProfileEntity entity) {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setVisible(entity.getVisible());
        dto.setPassword(entity.getPassword());
        dto.setRole(entity.getRole());
        dto.setCreated_date(entity.getCreatedDate());
        dto.setPhoto_id(entity.getPhotoId());
        dto.setStatus(entity.getStatus());
        return dto;
    }
  /*  public PageImpl<ProfileDTO> getAllWithPagination(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<ProfileEntity> profileEntities = profileRepository.findAll(pageable);

        List<ProfileDTO> profileList = new ArrayList<>();

        profileEntities.getContent().forEach(profileEntity -> profileList.add(toDTO(profileEntity)));

        return new PageImpl<>(profileList, pageable, profileEntities.getTotalElements());
    }*/
}
