package com.jobportal.service;
import com.jobportal.dto.request.UserRequest;
import com.jobportal.dto.response.UserResponse;
import com.jobportal.entity.User;
import com.jobportal.exception.ResourceNotFoundException;
import com.jobportal.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserService {
    private final UserRepository userRepository;
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", id));
        return toResponse(user);
    }
    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        return toResponse(user);
    }
    public UserResponse createUser(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already registered: " + request.getEmail());
        }
        User user =  // In production: hash with BCrypt
        User.builder().email(request.getEmail()).password(request.getPassword()).fullName(request.getFullName()).role(request.getRole() != null ? request.getRole() : "applicant").phone(request.getPhone()).build();
        User saved = userRepository.save(user);
        return toResponse(saved);
    }
    public UserResponse updateUser(Long id, UserRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", id));
        if (request.getFullName() != null) user.setFullName(request.getFullName());
        if (request.getPhone() != null) user.setPhone(request.getPhone());
        if (request.getRole() != null) user.setRole(request.getRole());
        User saved = userRepository.save(user);
        return toResponse(saved);
    }
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User", id);
        }
        userRepository.deleteById(id);
    }
    // Simple login (auth-ready placeholder)
    public UserResponse login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        if (!user.getPassword().equals(password)) {
            // In production: BCrypt.matches()
            throw new IllegalArgumentException("Invalid email or password");
        }
        return toResponse(user);
    }
    private UserResponse toResponse(User user) {
        return UserResponse.builder().id(user.getId()).email(user.getEmail()).fullName(user.getFullName()).role(user.getRole()).phone(user.getPhone()).createdAt(user.getCreatedAt()).build();
    }
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
