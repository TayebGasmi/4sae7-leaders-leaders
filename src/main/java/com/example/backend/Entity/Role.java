package com.example.backend.Entity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRole", nullable = false)
    private Integer id;


    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleEnum role;


}