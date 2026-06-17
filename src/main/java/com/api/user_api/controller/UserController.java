package com.api.user_api.controller;

import com.api.user_api.config.CustomHealthCheck;
import com.api.user_api.dto.UserRequest;
import com.api.user_api.dto.UserResponse;
import com.api.user_api.model.User;
import com.api.user_api.repository.UserRepository;
import com.api.user_api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.health.contributor.Health;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CustomHealthCheck customHealthCheck;

    @GetMapping("/status-javaconfig")
    public Health status(){
        return customHealthCheck.health();
    }


    /*
    @GetMapping
    public List<UserResponse> listAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id){
        return userService.findById(id)
                .map(ResponseEntity:: ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,
                                        @Valid @RequestBody UserRequest request){
        UserResponse updatedUser = userService.update(id, request);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
*/

}
