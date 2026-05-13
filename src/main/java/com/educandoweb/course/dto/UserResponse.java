package com.educandoweb.course.dto;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.User;
import org.apache.coyote.http11.filters.SavedRequestInputFilter;

import java.util.List;

public record UserResponse(
        String name,
        String email,
        String phone,
        List<Order> orders
) {
    public UserResponse(User user){
        this(
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getOrders()
        );
    }
}
