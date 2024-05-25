package com.lucsalmd.catalogapi.model.entity;

import com.lucsalmd.catalogapi.model.dto.CategoryRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("category")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    private String categoryId;
    @Indexed
    private String ownerId;
    private String title;
    private String description;

    public Category(String ownerId, CategoryRequestDTO category) {
        this.ownerId = ownerId;
        this.title = category.title();
        this.description = category.description();
    }

}
