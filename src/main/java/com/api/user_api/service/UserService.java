package com.api.user_api.service;

import com.api.user_api.dto.UserRequest;
import com.api.user_api.dto.UserResponse;
import com.api.user_api.model.User;
import com.api.user_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public List<UserResponse> findAll(){
        return userRepository.findAll().stream()
                .map(UserResponse::new)
                .toList();
    }
    public Optional<UserResponse> findById(long id){
        return userRepository.findById(id)
                .map(UserResponse::new);
    }

    public UserResponse save(UserRequest request){
        if(userRepository.existsByEmail(request.email())){
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        BeanUtils.copyProperties(request, user);

        String passwordEncoded = passwordEncoder.encode(request.password());
        user.setPassword(passwordEncoded);

        User savedUser = userRepository.save(user);
        return new UserResponse(savedUser);
    }

    public UserResponse update(long id, UserRequest request){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!user.getEmail().equals(request.email()) && userRepository.existsByEmail(request.email())){
            throw new RuntimeException("Email already exists in another user");
        }

        BeanUtils.copyProperties(request, user);
        user.setId(id);
        User newUser = userRepository.save(user);
        return new UserResponse(newUser);
    }

    public void delete(long id){
        if(!userRepository.existsById(id)){
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }

}
