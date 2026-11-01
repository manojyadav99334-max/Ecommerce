package com.ecommerce.userservice.service.impl.impl;

import com.ecommerce.userservice.dto.LoginRequestDto;
import com.ecommerce.userservice.dto.LoginResponseDto;
import com.ecommerce.userservice.dto.UserCreateRequestDto;
import com.ecommerce.userservice.dto.UserResponseDto;
import com.ecommerce.userservice.entity.User;
import com.ecommerce.userservice.exception.ResourceAlreadyExistsException;
import com.ecommerce.userservice.exception.ResourceNotFoundException;
import com.ecommerce.userservice.mapper.AuthMapper;
import com.ecommerce.userservice.mapper.UserMapper;
import com.ecommerce.userservice.repository.UserRepository;
import com.ecommerce.userservice.service.impl.UserService;
import com.ecommerce.userservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private  final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthMapper authMapper;


    @Override
    public UserResponseDto register(UserCreateRequestDto dto) {
       if(userRepo.existsByEmail(dto.getEmail()))
       {
           throw new ResourceAlreadyExistsException("email is already registered");
       }

       User user= userMapper.toEntity(dto);
       User savedUser=userRepo.save(user);
       return userMapper.dto(savedUser);
    }

    @Override
    public LoginResponseDto login(LoginRequestDto dto) {
       User user=userRepo.findByEmail(dto.getEmail())
               .orElseThrow(()->new ResourceNotFoundException("Invalid creatdentials"));
        boolean matches=passwordEncoder.matches(dto.getPassword(),user.getPassword());
        if(!matches)
        {
            throw new ResourceNotFoundException("Invalid Credentials");

        }
        String token= jwtUtil.generateToken(user.getId(),user.getEmail(),user.getRoles());
        return authMapper.toLoginResponse(user,token);

    }

    @Override
    public UserResponseDto getById(Long id) {
        User user=userRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User Id not found"));
        return userMapper.dto(user);
    }
}
