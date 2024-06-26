package org.example.kun_uzz.Service;


import org.example.kun_uzz.DTO.region.RegionCreateDTO;
import org.example.kun_uzz.DTO.region.RegionDTO;
import org.example.kun_uzz.Entity.RegionEntity;
import org.example.kun_uzz.Enums.Language;
import org.example.kun_uzz.exp.AppBadException;
import org.example.kun_uzz.mapper.RegionMapper;
import org.example.kun_uzz.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public RegionDTO create(RegionCreateDTO dto) {
        RegionEntity entity = new RegionEntity();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setName_uz(dto.getNameUz());
        entity.setName_ru(dto.getNameRu());
        entity.setName_en(dto.getNameEn());

        regionRepository.save(entity);
        return toDTO(entity);
    }

    public Boolean update(Integer id, RegionCreateDTO dto) {
        RegionEntity entity = get(id);
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setName_uz(dto.getNameUz());
        entity.setName_ru(dto.getNameRu());
        entity.setName_en(dto.getNameEn());
        regionRepository.save(entity);
        return true;
    }

    public Boolean delete(Integer id) {
        RegionEntity entity = get(id);
        regionRepository.delete(entity);
//        regionRepository.deleteById(id);
        return true;
    }

    public RegionEntity get(Integer id) {
        return regionRepository.findById(id).orElseThrow(() -> {
            throw new AppBadException("Region not found");
        });
    }

    public RegionDTO getRegion(Integer id, Language lang) {
        RegionEntity region = get(id);
        RegionDTO dto = new RegionDTO();
        dto.setId(region.getId());
        switch (lang) {
            case Uz -> dto.setName(region.getName_uz());
            case Ru -> dto.setName(region.getName_ru());
            default -> dto.setName(region.getName_en());
        }
        return dto;
    }

    public List<RegionDTO> getAll() {
        Iterable<RegionEntity> iterable = regionRepository.findAll();
        List<RegionDTO> dtoList = new LinkedList<>();
        for (RegionEntity entity : iterable) {
            dtoList.add(toDTO(entity));
        }
        return dtoList;
    }

    public List<RegionDTO> getAllByLang(Language lang) {
        Iterable<RegionEntity> iterable = regionRepository.findAllByVisibleTrueOrderByOrderNumberDesc();
        List<RegionDTO> dtoList = new LinkedList<>();
        for (RegionEntity entity : iterable) {
            RegionDTO dto = new RegionDTO();
            dto.setId(entity.getId());
            switch (lang) {
                case En -> dto.setName(entity.getName_en());
                case Uz -> dto.setName(entity.getName_uz());
                case Ru -> dto.setName(entity.getName_ru());
            }
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<RegionDTO> getAllByLang2(Language lang) {
        List<RegionMapper> mapperList = regionRepository.findAll(lang.name());
        List<RegionDTO> dtoList = new LinkedList<>();
        for (RegionMapper entity : mapperList) {
            RegionDTO dto = new RegionDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public RegionDTO toDTO(RegionEntity entity) {
        RegionDTO dto = new RegionDTO();
        dto.setId(entity.getId());
        dto.setNameUz(entity.getName_uz());
        dto.setNameEn(entity.getName_en());
        dto.setNameRu(entity.getName_ru());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
