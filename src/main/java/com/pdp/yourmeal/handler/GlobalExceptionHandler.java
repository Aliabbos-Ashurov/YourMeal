package com.pdp.yourmeal.handler;

import com.pdp.yourmeal.dto.response.ErrorMessageDTO;
import com.pdp.yourmeal.handler.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Aliabbos Ashurov
 * @since 19/September/2024  19:43
 **/
@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Value("${app.log-mode}")
    private boolean LOG_MODE;

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageDTO handleUserNotFoundException(UserNotFoundException e, HttpServletRequest request) {
        logException(e, request);
        return ErrorMessageDTO.of("USER_NOT_FOUND", e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageDTO handleResourceNotFoundException(ResourceNotFoundException e, HttpServletRequest request) {
        logException(e, request);
        return ErrorMessageDTO.of("NOT_FOUND", e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(InvalidInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageDTO handleInvalidInputException(InvalidInputException e, HttpServletRequest request) {
        logException(e, request);
        return ErrorMessageDTO.of("INVALID_INPUT", e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessageDTO handleUserAlreadyExistException(UserAlreadyExistException e, HttpServletRequest request) {
        logException(e, request);
        return ErrorMessageDTO.of("USER_ALREADY_EXIST", e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorMessageDTO handleUnauthorizedAccessException(UnauthorizedAccessException e, HttpServletRequest request) {
        logException(e, request);
        return ErrorMessageDTO.of("UNAUTHORIZED", e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageDTO handleCategoryNotFoundException(CategoryNotFoundException e, HttpServletRequest request) {
        logException(e, request);
        return ErrorMessageDTO.of("CATEGORY_NOT_FOUND", e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageDTO handleOrderNotFoundException(OrderNotFoundException e, HttpServletRequest request) {
        logException(e, request);
        return ErrorMessageDTO.of("ORDER_NOT_FOUND", e.getMessage(), request.getRequestURI());
    }

    private void logException(Exception ex, HttpServletRequest request) {
        if (LOG_MODE) {
            log.error("Exception occurred at URI: [{}] MESSAGE: {}", request.getRequestURI(), ex.getMessage());
        }
    }
}
