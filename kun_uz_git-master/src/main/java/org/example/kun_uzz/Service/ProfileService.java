package org.example.kun_uzz.Service;

import org.example.kun_uzz.DTO.profile.ProfileCreateDTO;
import org.example.kun_uzz.DTO.profile.ProfileDTO;
import org.example.kun_uzz.DTO.profile.ProfileUpdateDTO;
import org.example.kun_uzz.Entity.ProfileEntity;
import org.example.kun_uzz.exp.AppBadException;
import org.example.kun_uzz.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        entity.setPassword(dto.getPassword());
        entity.setPhoto_id(dto.getPhoto_id());

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
        dto.setCreated_date(entity.getCreated_date());
        dto.setPhoto_id(entity.getPhoto_id());
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
