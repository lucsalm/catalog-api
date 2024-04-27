package com.lucsalmd.catalogapi.repository;

import com.lucsalmd.catalogapi.model.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {

    List<Category> findAllCategoryByOwnerId(String ownerId);
}
