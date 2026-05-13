package com.educandoweb.course.dto;

public record UserPutRequest(
        Long id,
        String name,
        String email,
        String phone,
        String password
) {
}
