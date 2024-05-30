package org.example.kun_uzz.Service;

import org.example.kun_uzz.DTO.type.TypeCreateDTO;
import org.example.kun_uzz.DTO.type.TypeDTO;
import org.example.kun_uzz.Entity.TypeEntity;
import org.example.kun_uzz.Enums.Language;
import org.example.kun_uzz.exp.AppBadException;
import org.example.kun_uzz.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class TypeService {
    @Autowired
    private TypeRepository typeRepository;

    public TypeDTO create(TypeCreateDTO typeCreateDTO) {

        TypeEntity entity = new TypeEntity();

        entity.setOrderNumber(typeCreateDTO.getOrderNumber());
        entity.setName_uz(typeCreateDTO.getNameUz());
        entity.setName_ru(typeCreateDTO.getNameRu());
        entity.setName_en(typeCreateDTO.getNameEn());

        return toDTO(entity);
    }

    public List<TypeDTO> getAll() {
        Iterable<TypeEntity> entities = typeRepository.findAll();
        List<TypeDTO> dtos = new ArrayList<>();
        for (TypeEntity typeEntity : entities) {
            dtos.add(toDTO(typeEntity));
        }
        return dtos;
    }

    public List<TypeDTO> getAllByLang(Language lang) {
        Iterable<TypeEntity> entities=typeRepository.findAllByVisibleTrue();

        List<TypeDTO> dtoList = new LinkedList<>();
        for (TypeEntity entity : entities) {
            TypeDTO dto = new TypeDTO();
            dto.setId(entity.getId());
            dto.setOrderNumber(entity.getOrderNumber());
            switch (lang) {
                case en -> dto.setNameEn(entity.getName_en());
                case uz -> dto.setNameUz(entity.getName_uz());
                case ru -> dto.setNameRu(entity.getName_ru());
            }
            dtoList.add(dto);
        }
        return dtoList;

    }

    public TypeEntity get(Integer id) {
        return typeRepository.findById(id).orElseThrow(() -> {
            throw new AppBadException("Category not found");
        });
    }


    public Boolean update(Integer id, TypeDTO dto) {

        TypeEntity entity = get(id);

        entity.setId(dto.getId());
        entity.setVisible(dto.getVisible());
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setName_uz(dto.getNameUz());
        entity.setName_ru(dto.getNameRu());
        entity.setName_en(dto.getNameEn());
        entity.setCreatedDate(dto.getCreatedDate());
        typeRepository.save(entity);
        return true;


    }














    public TypeDTO toDTO(TypeEntity entity){
        TypeDTO dto = new TypeDTO();
        dto.setId(entity.getId());
        dto.setNameUz(entity.getName_uz());
        dto.setNameEn(entity.getName_en());
        dto.setNameRu(entity.getName_ru());
        dto.setVisible(entity.getVisible());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
