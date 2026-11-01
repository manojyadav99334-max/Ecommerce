package com.ecommerce.userservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    private String phone;
    private String roles;
    private Instant createdAt;
    private Instant updatedAt;

    @PrePersist
    public void prePersist()
    {
        createdAt=Instant.now();
        updatedAt=createdAt;
    }
    @PreUpdate
    public void preUpdate()
    {
        updatedAt=Instant.now();
    }

}
