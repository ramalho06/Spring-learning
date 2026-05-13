package com.educandoweb.course.dto;

public record UserRequest(
        String name,
        String email,
        String phone,
        String password
) {
}
