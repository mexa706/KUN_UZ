package org.example.kun_uzz.Service;

import jakarta.persistence.Column;
import lombok.Setter;
import org.example.kun_uzz.DTO.ProfileCreateDTO;
import org.example.kun_uzz.DTO.ProfileDTO;
import org.example.kun_uzz.DTO.RegionCreateDTO;
import org.example.kun_uzz.DTO.RegionDTO;
import org.example.kun_uzz.Entity.ProfileEntity;
import org.example.kun_uzz.Entity.RegionEntity;
import org.example.kun_uzz.Enums.ProfileRole;
import org.example.kun_uzz.Enums.ProfileStatus;
import org.example.kun_uzz.exp.AppBadException;
import org.example.kun_uzz.repository.ProfileRepository;
import org.example.kun_uzz.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
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
        entity.setRole(dto.getRole());
        entity.setPhoto_id(dto.getPhoto_id());

        profileRepository.save(entity);
        return toDTO(entity);
    }

    public ProfileDTO update(Integer id, ProfileCreateDTO dto) {



        ProfileEntity entity = new ProfileEntity();

        entity=get(id);

        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());


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
}
