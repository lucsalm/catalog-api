package com.lucsalmd.catalogapi.exception;

import com.lucsalmd.catalogapi.exception.dto.BusinessError;
import com.lucsalmd.catalogapi.exception.enums.BusinessErrorEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException {

    private final transient BusinessError error;
    private final transient HttpStatus status;

    public BusinessException(BusinessErrorEnum errorEnum) {
        this.error = new BusinessError(errorEnum);
        this.status = errorEnum.getStatus();
    }
}
