package com.example.backend.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdResponse;
    Long responseCode;
    int score;
    @ManyToOne
    Quiz quiz;
    @ManyToOne
    User user;
}
