package com.example.backend.Playload.Request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthentificationRequest {
    String password;
    private String email;
    private Boolean passwordneedschange;

}
