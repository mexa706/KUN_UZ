package org.example.kun_uzz.Service;

import org.example.kun_uzz.DTO.CategoryCreateDTO;
import org.example.kun_uzz.DTO.CategoryDTO;
import org.example.kun_uzz.Entity.CategoryEntity;
import org.example.kun_uzz.Enums.Language;
import org.example.kun_uzz.exp.AppBadException;
import org.example.kun_uzz.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDTO create(CategoryCreateDTO dto) {

        CategoryEntity entity = new CategoryEntity();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setName_uz(dto.getNameUz());
        entity.setName_ru(dto.getNameRu());
        entity.setName_en(dto.getNameEn());

        categoryRepository.save(entity);
        return toDTO(entity);
    }



    public CategoryDTO toDTO(CategoryEntity entity){
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setName_uz(entity.getName_uz());
        dto.setName_en(entity.getName_en());
        dto.setName_ru(entity.getName_ru());
        dto.setVisible(entity.getVisible());
        dto.setOrder_number(entity.getOrderNumber());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public List<CategoryDTO> getAll() {
        Iterable<CategoryEntity> categoryEntities = categoryRepository.findAll();
        List<CategoryDTO> dtos = new ArrayList<>();
        for (CategoryEntity categoryEntity : categoryEntities) {
            dtos.add(toDTO(categoryEntity));
        }
        return dtos;
    }

    public List<CategoryDTO> getAllByLang(Language lang) {
        Iterable<CategoryEntity> entities=categoryRepository.findAllByVisibleTrue();

        List<CategoryDTO> dtoList = new LinkedList<>();
        for (CategoryEntity entity : entities) {
            CategoryDTO dto = new CategoryDTO();
            dto.setId(entity.getId());
            dto.setOrder_number(entity.getOrderNumber());
            switch (lang) {
                case en -> dto.setName_en(entity.getName_en());
                case uz -> dto.setName_uz(entity.getName_uz());
                case ru -> dto.setName_ru(entity.getName_ru());
            }
            dtoList.add(dto);
        }
        return dtoList;

    }

    public CategoryEntity get(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> {
            throw new AppBadException("Category not found");
        });
    }


    public Boolean update(Integer id, CategoryDTO dto) {

        CategoryEntity entity = get(id);

        entity.setId(dto.getId());
        entity.setVisible(dto.getVisible());
        entity.setOrderNumber(dto.getOrder_number());
        entity.setName_uz(dto.getName_uz());
        entity.setName_ru(dto.getName_ru());
        entity.setName_en(dto.getName_en());
        entity.setCreatedDate(dto.getCreatedDate());
        categoryRepository.save(entity);
        return true;


    }
}
