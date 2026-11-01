package com.ecommerce.userservice.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String roles;
    private Instant createdAt;
    private Instant updatedAt;

}
