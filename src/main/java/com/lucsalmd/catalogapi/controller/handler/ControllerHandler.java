package com.lucsalmd.catalogapi.controller.handler;

import com.lucsalmd.catalogapi.exception.BusinessException;
import com.lucsalmd.catalogapi.exception.dto.BusinessError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BusinessError> handleBusinessException(final BusinessException exception){
        return ResponseEntity.status(exception.getStatus()).body(exception.getError());
    }

}
