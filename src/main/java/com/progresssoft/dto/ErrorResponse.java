package com.progresssoft.dto;

public record ErrorResponse(
        int status,
        String error,
        String message
) {}