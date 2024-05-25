package com.lucsalmd.catalogapi.service;

import com.lucsalmd.catalogapi.model.dto.ProductRequestDTO;
import com.lucsalmd.catalogapi.model.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(String ownerId, ProductRequestDTO productRequestDTO);
    List<Product> getProductsByOwner(String ownerId);
    Product updateProduct(String productId, ProductRequestDTO productRequestDTO);
    void deleteProduct(String productId);
}
