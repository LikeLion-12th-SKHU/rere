package net.skhu.likelion12thteam03be.global.error.dto;

public record ErrorResponse(
        int statusCode,
        String message
) {
}