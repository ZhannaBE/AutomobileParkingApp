
package com.example.parking.service;

import com.example.parking.model.Role;
import com.example.parking.model.User;
import com.example.parking.repository.RoleRepository;
import com.example.parking.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String username, String rawPassword) {
        if (userRepository.findByUsername(username).isPresent()) throw new RuntimeException("User exists");
        User u = new User();
        u.setUsername(username);
        u.setPassword(passwordEncoder.encode(rawPassword));
        u.setEnabled(true);
        Role defaultRole = roleRepository.findByName("READ_ONLY").orElseThrow();
        u.getRoles().add(defaultRole);
        return userRepository.save(u);
    }

    public List<User> all() { return userRepository.findAll(); }

    public void addRoleToUser(Long userId, String roleName) {
        User user = userRepository.findById(userId).orElseThrow();
        Role r = roleRepository.findByName(roleName).orElseThrow();
        user.getRoles().add(r);
        userRepository.save(user);
    }
}
