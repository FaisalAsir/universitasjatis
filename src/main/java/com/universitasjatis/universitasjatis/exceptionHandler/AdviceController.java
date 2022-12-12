package com.universitasjatis.universitasjatis.exceptionHandler;

import com.universitasjatis.universitasjatis.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class AdviceController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<Object>> fieldNotVallid(MethodArgumentNotValidException e) {
        List<String> error = e.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.toList());
        log.error(error.toString());
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.BAD_REQUEST, error, null),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDto<Object>> fieldDateNotVallid(HttpMessageNotReadableException e) {
        log.info("Error Response handler : {}",e.getMessage());
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.BAD_REQUEST, Collections.singletonList("Tanggal harus diisi dan dengan format dd-MM-yyyy"), null),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ResponseDto<Object>> internalErrorExeption(DataNotFoundException e){
        log.error("Requested Data Not Found",e);

        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.NOT_FOUND, Collections.singletonList(e.getMessage()), null),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(JatisExeption.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDto<Object>> jatisErrorExeption(JatisExeption e){
        log.error("Error BAD_REQUEST",e);

        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.BAD_REQUEST, Collections.singletonList(e.getMessage()), null),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseDto<Object>> internalErrorExeption(Exception e){
        log.error("Internal Error",e);

        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR, Collections.singletonList("Internal error please contact Admin"), null),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
