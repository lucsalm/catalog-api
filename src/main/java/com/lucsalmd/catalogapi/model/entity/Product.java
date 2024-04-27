package com.lucsalmd.catalogapi.model.entity;

import com.lucsalmd.catalogapi.model.dto.ProductRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("product")
public class Product {
    @Id
    private String productId;
    @Indexed
    private String ownerId;
    private String categoryId;
    private String title;
    private Integer price;
    private String description;

    public Product(String ownerId, ProductRequestDTO productRequestDTO) {
        this.ownerId = ownerId;
        this.title = productRequestDTO.title();
        this.price = productRequestDTO.price();
        this.categoryId = productRequestDTO.categoryId();
        this.description = productRequestDTO.description();
    }
}
