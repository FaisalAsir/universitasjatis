package com.universitasjatis.universitasjatis.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
//@Builder
public class ResponseDto<T> {
    private String httpStatus;
    private List<String> errors;
    private T data;

    public ResponseDto(HttpStatus httpStatus, List<String> errors, T data) {
        this.httpStatus = httpStatus.getReasonPhrase();
        this.errors = errors;
        this.data = data;
    }
}
