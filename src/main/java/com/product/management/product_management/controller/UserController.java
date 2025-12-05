package com.product.management.product_management.controller;


import com.product.management.product_management.entity.User;
import com.product.management.product_management.entity.UserRequest;
import com.product.management.product_management.repository.UserRepository;
import com.product.management.product_management.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final JwtUtil jwtUtil;

    private final UserRepository userRepository;

    @PostMapping("/api/auth/login")
    public ResponseEntity<String> generateToken(@RequestBody UserRequest request) {

        Integer id = 2;
        User user = userRepository.findByUserName(request.getUserName());

        if(user.getUserName().equals(request.getUserName())){
            String token = jwtUtil.generateToken(request.getUserName() , List.of("Admin"));

            return  ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
