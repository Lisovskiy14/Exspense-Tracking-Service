package com.example.expense_tracking_service.service.impl;

import com.example.expense_tracking_service.common.Role;
import com.example.expense_tracking_service.domain.User;
import com.example.expense_tracking_service.dto.auth.SignInRequestDto;
import com.example.expense_tracking_service.dto.auth.SignUpRequestDto;
import com.example.expense_tracking_service.security.service.JwtService;
import com.example.expense_tracking_service.service.AuthService;
import com.example.expense_tracking_service.service.UserService;
import com.example.expense_tracking_service.service.exception.UsernameAlreadyExistsException;
import com.example.expense_tracking_service.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Override
    public String signUp(SignUpRequestDto signUpRequestDto) {
        if (userRepository.existsByUsername(signUpRequestDto.getUsername())) {
            throw new UsernameAlreadyExistsException(signUpRequestDto.getUsername());
        }

        User user = User.builder()
                .username(signUpRequestDto.getUsername())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                .roles(Set.of(Role.ROLE_USER))
                .build();

        userService.createUser(user);

        log.info("New user {} has been created", user.getUsername());
        return jwtService.generateToken(user);
    }

    @Override
    public String signIn(SignInRequestDto signInRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequestDto.getUsername(),
                signInRequestDto.getPassword()
        ));

        UserDetails userDetails = userDetailsService.loadUserByUsername(signInRequestDto.getUsername());

        log.info("User {} has been authenticated", userDetails.getUsername());
        return jwtService.generateToken(userDetails);
    }
}
