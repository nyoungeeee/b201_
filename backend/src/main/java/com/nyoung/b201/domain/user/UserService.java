package com.nyoung.b201.domain.user;

import com.nyoung.b201.domain.user.dto.UserRequest;
import com.nyoung.b201.domain.user.dto.UserResponse;
import com.nyoung.b201.domain.user.entity.User;
import com.nyoung.b201.domain.user.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse create(UserRequest req) {
        String encodedPassword = passwordEncoder.encode(req.getPassword());
        return UserResponse.from(userRepository.save(new User(req)));
    }

    public UserResponse find(Long userId) {
        User user = userRepository.findByIdAndDeletedFalse(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return UserResponse.from(user);
    }

    public List<UserResponse> findAll() {
        List<User> users = userRepository.findAllByDeletedFalse();
        return UserResponse.from(users);
    }

    @Transactional
    public void update(UserRequest req) {
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new UserNotFoundException(req.getUserId()));
        user.updateUser(req.getEmail(), req.getName(), req.getTeam());
//        if (req.getPassword() != null) {
//            changePassword(req.getUserId(), req.getPassword());
//        }
    }

//    public void changePassword(Long userId, String rawPassword) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));
//        String encodedPassword = passwordEncoder.encode(rawPassword);
//        user.changePassword(encodedPassword);
//    }
//
//    public void delete(Long userId) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
//        user.updateDeletedStatus(true);
//    }
//
//    public boolean checkAdmin(Long userId) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
//        return user.isAdmin();
//    }

}
