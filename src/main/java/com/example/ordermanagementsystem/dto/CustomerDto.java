package com.example.ordermanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Getter
@Setter
public class CustomerDto {
    private UUID id;

    @NotBlank(message = "Customer name must not be blank")
    private String name;

    @NotBlank(message = "Customer email must not be blank")
    private String email;
}
