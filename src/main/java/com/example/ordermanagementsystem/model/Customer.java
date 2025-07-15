package com.example.ordermanagementsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;

@Entity
@Table(name="customers")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(generator="UUID")
    private UUID id;

    @Column(name="name" , nullable=false)
    private String name;
    
    @Column(name="email", nullable=false, unique=true)
    private String email;


}

