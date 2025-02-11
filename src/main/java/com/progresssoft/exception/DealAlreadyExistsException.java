package com.progresssoft.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DealAlreadyExistsException extends RuntimeException {

    public DealAlreadyExistsException(String message) {
        super(message);
    }


}
