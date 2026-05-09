package com.api.user_api.controller;

import com.api.user_api.dto.UserRequest;
import com.api.user_api.model.User;
import com.api.user_api.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    /*
    @GetMapping
    public String status(){
        return "The API is online!";
    }
    */

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest request){
        if(userRepository.existsByEmail(request.email())){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Email already exists");
        }

        User user = new User();
        BeanUtils.copyProperties(request, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,
                                        @Valid @RequestBody UserRequest request){
        return userRepository.findById(id)
                .map(user -> {
                    if(!user.getEmail().equals(request.email()) && userRepository.existsByEmail(request.email())){
                        return ResponseEntity.status(HttpStatus.CONFLICT)
                                .body("Email already exists in another account");
                    }

                    BeanUtils.copyProperties(request, user);

                    user.setId(id);

                    User updatedUser = userRepository.save(user);
                    return ResponseEntity.ok(updatedUser);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<User> listAll(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id){
        return userRepository.findById(id)
                .map(ResponseEntity:: ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
