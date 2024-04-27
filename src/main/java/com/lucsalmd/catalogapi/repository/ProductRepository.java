package com.lucsalmd.catalogapi.repository;

import com.lucsalmd.catalogapi.model.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product,String> {

    List<Product> findAllProductsByOwnerId(String categoryId);
}
