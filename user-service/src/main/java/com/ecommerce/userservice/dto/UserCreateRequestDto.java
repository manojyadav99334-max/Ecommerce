package com.ecommerce.userservice.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequestDto {

    @NotNull(message = "name is required")
    private String name;
    @Email(message = "valid email required")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 6,message = "password must be atleast 6 characters")
    private String password;
    private String phone;
}
