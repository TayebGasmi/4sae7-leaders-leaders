package com.example.backend.Services;

import com.example.backend.Entity.RoleEnum;
import com.example.backend.Entity.Token;
import com.example.backend.Entity.TokenType;
import com.example.backend.Entity.User;
import com.example.backend.Playload.Request.AuthentificationRequest;
import com.example.backend.Playload.Request.RegisterRequest;
import com.example.backend.Playload.Response.AuthentificationResponse;
import com.example.backend.Repository.RoleRepository;
import com.example.backend.Repository.TokenRepository;
import com.example.backend.Repository.UserRepository;
import com.example.backend.Security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
    private static final int MAX_FAILED_LOGIN_ATTEMPTS = 2;
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Autowired
    RoleRepository roleRepository;

    public AuthentificationResponse register(RegisterRequest request) {
        logger.debug("RegisterRequest object: {}", request.toString());
//        Set<Role> roles = new LinkedHashSet<>();
//        Role r = new Role(1,RoleEnum.ROLE_USER);


        var user = User.builder().firstname(request.getFirstName()).lastname(request.getLastName()).email(request.getEmail()).username(request.getUsername()).birthdate(request.getBirthdate()).password(passwordEncoder.encode(request.getPassword())).roles(roleRepository.findByRole(RoleEnum.ROLE_USER)).build();
        logger.debug("RegisterRequest object: {}", request.toString());

        logger.debug("User object before saving to the database: {}", user);

        var savedUser = repository.save(user);
        logger.debug("User object after saving to the database: {}", savedUser);

        var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthentificationResponse.builder().token(jwtToken).build();
    }

//    public AuthentificationResponse authenticate(AuthentificationRequest request) throws Exception {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//
//                )
//        );
//        var user = repository.findByEmail(request.getEmail()).orElse(null);
//
//
//         var jwtToken = jwtService.generateToken(user);
//        revokeAllUserTokens(user);
//        saveUserToken(user, jwtToken);
//         return AuthentificationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }

    public AuthentificationResponse authenticate(AuthentificationRequest request) throws Exception {

        var user = repository.findByEmail(request.getEmail()).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        if (user.getPasswordneedschange()) {
            throw new javax.naming.AuthenticationException("password needs to be changed");
        }


        if (user.isAccountLocked()) {
            int lockoutDurationMinutes = 1;
            if (user.getLastLockTime().plusMinutes(lockoutDurationMinutes).isBefore(LocalDateTime.now())) {
                user.setAccountLocked(false);
                user.setFailedLoginAttempts(0);
                user.setLastLockTime(null);
                repository.save(user);
            } else {
                throw new LockedException("User account is locked");
            }
        }

        if (user.getFailedLoginAttempts() >= MAX_FAILED_LOGIN_ATTEMPTS) {
            user.setAccountLocked(true);
            user.setLastLockTime(LocalDateTime.now());
            repository.save(user);
            throw new LockedException("User account is locked");
        }

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (AuthenticationException e) {
            // Increment the number of failed login attempts
            user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);
            repository.save(user);
            throw e;
        }

        user.setFailedLoginAttempts(0);
        repository.save(user);

        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthentificationResponse.builder().token(jwtToken).build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder().user(user).token(jwtToken).tokenType(TokenType.BEARER).expired(false).revoked(false).build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser((user.getId()));
        if (validUserTokens.isEmpty()) return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
