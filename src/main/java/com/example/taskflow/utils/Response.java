package com.example.taskflow.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Response<T> {
    private String error;
    private String message;
    private T result;
    private Map<String, String> errorValidation;
}
