package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.dto.LoginRequestDto;
import com.ecommerce.userservice.dto.LoginResponseDto;
import com.ecommerce.userservice.dto.UserCreateRequestDto;
import com.ecommerce.userservice.dto.UserResponseDto;
import com.ecommerce.userservice.service.impl.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserCreateRequestDto dto)
    {
        UserResponseDto created=userService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto dto)
    {
        LoginResponseDto loginResponseDto=userService.login(dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(loginResponseDto);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable long id)
    {
        UserResponseDto userResponseDto=userService.getById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(userResponseDto);
    }

}
