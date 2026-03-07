package com.example.demo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    // Spring Security gọi cái này để load user khi xác thực
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Không tìm thấy user: " + email)
                );
    }

    // Tìm user theo id
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy user: " + id)
                );
    }

    // Kiểm tra email đã tồn tại chưa (dùng khi register)
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}