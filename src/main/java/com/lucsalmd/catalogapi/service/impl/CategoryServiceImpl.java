package com.lucsalmd.catalogapi.service.impl;

import com.lucsalmd.catalogapi.exception.BusinessException;
import com.lucsalmd.catalogapi.exception.enums.BusinessErrorEnum;
import com.lucsalmd.catalogapi.model.dto.CategoryRequestDTO;
import com.lucsalmd.catalogapi.model.entity.Category;
import com.lucsalmd.catalogapi.publisher.SNSPublisher;
import com.lucsalmd.catalogapi.repository.CategoryRepository;
import com.lucsalmd.catalogapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SNSPublisher publisher;

    public List<Category> getCategoriesByOwner(String ownerId) {
        List<Category> categories = categoryRepository.findAllCategoryByOwnerId(ownerId);
        if (categories.isEmpty()) throw new BusinessException(BusinessErrorEnum.REQUEST_OWNER_NOT_FOUND);
        return categories;
    }

    public Category createCategory(String ownerId, CategoryRequestDTO categoryRequestDTO) {
        final Category category = new Category(ownerId, categoryRequestDTO);

        categoryRepository.save(category);

        publisher.publishMessage(category.getOwnerId());

        return category;
    }

    public Category updateCategory(String categoryId, CategoryRequestDTO categoryRequestDTO) {
        final Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BusinessException(BusinessErrorEnum.CATEGORY_NOT_FOUND));

        if (Objects.nonNull(categoryRequestDTO.description())) category.setDescription(categoryRequestDTO.description());
        if (Objects.nonNull(categoryRequestDTO.title())) category.setTitle(categoryRequestDTO.title());

        categoryRepository.save(category);

        publisher.publishMessage(category.getOwnerId());

        return category;
    }

    public void deleteCategory(String categoryId) {
        final Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BusinessException(BusinessErrorEnum.CATEGORY_NOT_FOUND));

        categoryRepository.delete(category);
        publisher.publishMessage(category.getOwnerId());
    }

}
