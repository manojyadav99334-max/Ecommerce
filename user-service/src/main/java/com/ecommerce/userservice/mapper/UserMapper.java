package com.ecommerce.userservice.mapper;

import com.ecommerce.userservice.dto.UserCreateRequestDto;
import com.ecommerce.userservice.dto.UserResponseDto;
import com.ecommerce.userservice.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder)
    {
        this.passwordEncoder=passwordEncoder;
    }
    public User toEntity(UserCreateRequestDto dto)
    {
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles("ROLE_USER").phone(dto.getPhone())
                .build();

    }
    public UserResponseDto dto(User user)
    {
        return UserResponseDto
                .builder()
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .roles(user.getRoles())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();

    }
}
