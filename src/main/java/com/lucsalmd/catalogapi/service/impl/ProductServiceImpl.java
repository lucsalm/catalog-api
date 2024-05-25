package com.lucsalmd.catalogapi.service.impl;

import com.lucsalmd.catalogapi.exception.BusinessException;
import com.lucsalmd.catalogapi.exception.enums.BusinessErrorEnum;
import com.lucsalmd.catalogapi.model.dto.ProductRequestDTO;
import com.lucsalmd.catalogapi.model.entity.Category;
import com.lucsalmd.catalogapi.model.entity.Product;
import com.lucsalmd.catalogapi.publisher.SNSPublisher;
import com.lucsalmd.catalogapi.repository.CategoryRepository;
import com.lucsalmd.catalogapi.repository.ProductRepository;
import com.lucsalmd.catalogapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    
    private final  ProductRepository productRepository;
    
    private final  CategoryRepository categoryRepository;
    
    private final  SNSPublisher publisher;

    public List<Product> getProductsByOwner(String ownerId){
        List<Product> products = productRepository.findAllProductsByOwnerId(ownerId);
        if(products.isEmpty()) throw new BusinessException(BusinessErrorEnum.REQUEST_OWNER_NOT_FOUND);
        return products;
    }

    public Product createProduct(String ownerId, ProductRequestDTO productRequestDTO) {
        String categoryId = productRequestDTO.categoryId();
        if(Objects.nonNull(categoryId)){
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new BusinessException(BusinessErrorEnum.CATEGORY_NOT_FOUND));
            if(!category.getOwnerId().equals(ownerId)) throw new BusinessException(BusinessErrorEnum.INCOMPATIBLE_OWNER);
        }

        final Product product = new Product(ownerId, productRequestDTO);
        productRepository.save(product);

        publisher.publishMessage(product.getOwnerId());

        return product;
    }

    public Product updateProduct(String productId, ProductRequestDTO productRequestDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException(BusinessErrorEnum.PRODUCT_NOT_FOUND));


        String categoryId = productRequestDTO.categoryId();
        if(Objects.nonNull(categoryId)){
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new BusinessException(BusinessErrorEnum.CATEGORY_NOT_FOUND));
            if(!category.getOwnerId().equals(product.getOwnerId())) throw new BusinessException(BusinessErrorEnum.INCOMPATIBLE_OWNER);
        }

        if(Objects.nonNull(productRequestDTO.title())) product.setTitle(productRequestDTO.title());
        if(Objects.nonNull(productRequestDTO.description())) product.setDescription(productRequestDTO.description());
        if(Objects.nonNull(productRequestDTO.price())) product.setPrice(productRequestDTO.price());
        if(Objects.nonNull(categoryId)) product.setCategoryId(categoryId);

        productRepository.save(product);

        publisher.publishMessage(product.getOwnerId());

        return product;
    }

    public void deleteProduct(String productId) {
        final Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException(BusinessErrorEnum.PRODUCT_NOT_FOUND));

        productRepository.delete(product);

        publisher.publishMessage(product.getOwnerId());
    }







}
