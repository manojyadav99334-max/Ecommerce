package com.ecommerce.userservice.service.impl;

import com.ecommerce.userservice.dto.LoginRequestDto;
import com.ecommerce.userservice.dto.LoginResponseDto;
import com.ecommerce.userservice.dto.UserCreateRequestDto;
import com.ecommerce.userservice.dto.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserCreateRequestDto dto);
    LoginResponseDto login(LoginRequestDto dto);
    UserResponseDto getById(Long id);

}
