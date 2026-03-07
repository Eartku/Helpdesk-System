package com.example.demo.user;

import com.example.demo.role.Role;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatus status;                    // ← thêm mới

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private final Set<Role> roles = new HashSet<>();

    protected User() {}

    public User(String fullname, LocalDate dob, String email) {
        this.fullname = fullname;
        this.dob = dob;
        this.email = email;
        this.status = UserStatus.ACTIVE;          // ← mặc định ACTIVE khi tạo mới
    }

    public void addRole(Role role) { this.roles.add(role); }

    public String getFullname() { return fullname; }
    public LocalDate getDob() { return dob; }
    public String getEmail() { return email; }
    public Set<Role> getRoles() { return roles; }
    public UserStatus getStatus() { return status; }

    public void setPassword(String password) { this.password = password; }
    public void setStatus(UserStatus status) { this.status = status; }

    // ↓ UserDetails methods

    @Override
    public String getPassword() { return password; }   // ← thêm mới

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()                           // ← sửa lại
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName()))
                .collect(Collectors.toList());
    }

    @Override public String getUsername() { return email; }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return status != UserStatus.BANNED; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return status == UserStatus.ACTIVE; }
}