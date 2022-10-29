package com.project.IIITBuyNSell.exceptions;

import com.project.IIITBuyNSell.dto.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<BaseResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        BaseResponse baseResponse = BaseResponse.builder().message(ex.getMessage()).build();
        return new ResponseEntity<>(baseResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserDTONotFoundException.class)
    public ResponseEntity<BaseResponse> handleUserDTONotFoundException(UserDTONotFoundException ex) {
        BaseResponse baseResponse = BaseResponse.builder().message("Please provide user details").build();
        return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
    }

    public static class UserDTONotFoundException extends RuntimeException {
        private static final long serialVersionUID = -879483505518044393L;
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<BaseResponse> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        BaseResponse baseResponse = BaseResponse.builder().message("Invalid Credentials").build();
        return new ResponseEntity<>(baseResponse, HttpStatus.NOT_FOUND);
    }

    public static class InvalidCredentialsException extends RuntimeException {
        private static final long serialVersionUID = -879483505518044383L;
    }

    @ExceptionHandler(UnknownException.class)
    public ResponseEntity<BaseResponse> handleUnknownException(UnknownException ex) {
        BaseResponse baseResponse = BaseResponse.builder().message("Internal Server Error").build();
        return new ResponseEntity<>(baseResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static class UnknownException extends RuntimeException {
        private static final long serialVersionUID = -879483505518044383L;
    }

}
