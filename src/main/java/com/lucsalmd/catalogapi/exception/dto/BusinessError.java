package com.lucsalmd.catalogapi.exception.dto;

import com.lucsalmd.catalogapi.exception.enums.BusinessErrorEnum;
import lombok.Getter;

@Getter
public class BusinessError {

    private final String code;
    private final String message;

    public BusinessError(BusinessErrorEnum errorEnum) {
        this.code = errorEnum.name();
        this.message = errorEnum.getMessage();
    }
}
