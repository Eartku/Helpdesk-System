package com.example.demo.auth;

import com.example.demo.role.Role;
import com.example.demo.role.RoleName;
import com.example.demo.role.RoleRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import lombok.Getter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Getter
    private final UserRepository userRepository;
    @Getter
    private final RoleRepository roleRepository;
    @Getter
    private final PasswordEncoder passwordEncoder;
    @Getter
    private final JwtService jwtService;
    @Getter
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())){
            throw new RuntimeException("Email đã tồn tại!");
        }
        String password  = passwordEncoder.encode(request.password());

        User new_user = new User(request.fullname(), request.dob(), request.email());
        new_user.setPassword(password);

        Role role = roleRepository.findByName(RoleName.USER).orElseThrow(()->new RuntimeException("Role không tồn tại!"));

        new_user.addRole(role);

        userRepository.save(new_user);
        String token = jwtService.generateToken(new_user);
        return new AuthResponse(token, new_user.getEmail(), new_user.getFullname());
    }

}
