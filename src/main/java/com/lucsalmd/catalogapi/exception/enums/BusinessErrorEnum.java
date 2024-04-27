package com.lucsalmd.catalogapi.exception.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BusinessErrorEnum {
    PRODUCT_NOT_FOUND("Product not found", HttpStatus.NOT_FOUND),
    CATEGORY_NOT_FOUND("Category not found", HttpStatus.NOT_FOUND),
    REQUEST_OWNER_NOT_FOUND("Not found for request owner", HttpStatus.NOT_FOUND),
    INCOMPATIBLE_OWNER("Owner not compatible", HttpStatus.UNPROCESSABLE_ENTITY),
    SERVER_ERROR("Server error occurred ", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus status;

    BusinessErrorEnum(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
