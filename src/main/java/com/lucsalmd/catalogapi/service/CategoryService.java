package com.lucsalmd.catalogapi.service;

import com.lucsalmd.catalogapi.model.dto.CategoryRequestDTO;
import com.lucsalmd.catalogapi.model.entity.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(String ownerId, CategoryRequestDTO categoryRequestDTO);

    List<Category> getCategoriesByOwner(String ownerId);

    Category updateCategory(String categoryId, CategoryRequestDTO categoryRequestDTO);

    void deleteCategory(String categoryId);


}
