package com.ecommerce.userservice.mapper;

import com.ecommerce.userservice.dto.LoginRequestDto;
import com.ecommerce.userservice.dto.LoginResponseDto;
import com.ecommerce.userservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {
    public LoginResponseDto toLoginResponse(User user, String token)
    {
        return new LoginResponseDto(token,"Bearer",user.getId(),user.getEmail(),user.getName());
    }
}
