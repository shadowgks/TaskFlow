package com.example.taskflow.exception;

import com.example.taskflow.utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Response<?>> handelIllegalArgumentException(Exception ex){
        Response<?> response = new Response<>();
        String getErrorMessage = ex.getMessage();
        response.setError(getErrorMessage);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<?>> handelMethodArgumentNotValidException(MethodArgumentNotValidException ev){
        Response<?> response = new Response<>();
        Map<String, String> setFieldsValidation = new HashMap<>();
        ev.getBindingResult().getFieldErrors().forEach(
                e -> {
                    setFieldsValidation.put(e.getField(), e.getDefaultMessage());
                }
        );
        response.setErrorValidation(setFieldsValidation);
        return ResponseEntity.badRequest().body(response);
    }
}
