package com.ssafy.offistellink.mail.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

    // ResponseStatusException 예외가 발생했을 때의 응답 처리
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResult> handleResponseStatusException(ResponseStatusException e) {
        log.error("ResponseStatusException occurred: ", e);
        ErrorResult errorResult = new ErrorResult("RESP_ERROR", e.getReason());
        return new ResponseEntity<>(errorResult, HttpStatus.UNAUTHORIZED);
    }

    // NullPointerException 예외가 발생했을 때의 응답 처리
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResult> handleNullPointerException(NullPointerException e) {
        log.error("NullPointerException occurred: ", e);
        ErrorResult errorResult = new ErrorResult("NULL_POINTER", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    // 그 외의 예외에 대해 공통적인 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResult> handleGeneralException(Exception e) {
        log.error("An unexpected error occurred: ", e);
        ErrorResult errorResult = new ErrorResult("GENERAL_ERROR", "An unexpected error occurred");
        return new ResponseEntity<>(errorResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}