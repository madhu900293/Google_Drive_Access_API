//package com.haridwar.university.btech.controller;
//
//import com.haridwar.university.btech.dto.UserDto;
//import com.haridwar.university.btech.dto.UserDtoResponse;
//import com.haridwar.university.btech.models.User;
//import com.haridwar.university.btech.repository.UserRepository;
//import com.haridwar.university.btech.service.UserService;
//import com.haridwar.university.btech.util.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@RequestMapping("/users")
//@RestController
//public class UserController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private UserService userService;
//
//
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    // POST method to save a new user
//    @PostMapping
//    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
//        User user = new User();
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        // Hash the password before saving
//        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        userRepository.save(user);
//        return ResponseEntity.ok("User created successfully");
//    }
//    //    // PUT method to update an existing user
////    @PutMapping("/{id}")
////    public UserDto updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest request) {
////        Optional<User> optionalUser = userRepository.findById(id);
////        if (optionalUser.isPresent()) {
////            User user = optionalUser.get();
////            user.setName(request.getName());
////            user.setEmail(request.getEmail());
////
////            if (request.getPassword() != null && !request.getPassword().isEmpty()) {
////                user.setPassword(passwordEncoder.encode(request.getPassword()));
////            }
////
////            User updatedUser = userRepository.save(user);
////            return new UserDto(updatedUser);
////        } else {
////            throw new RuntimeException("User not found with id " + id);
////        }
////    }
//    @GetMapping("/{id}")  // GET request to get a user by ID
//    public UserDtoResponse getUser(@PathVariable Long id) {
//        Optional<User> user = userRepository.findById(id);
//        if (user.isPresent()) {
//            return new UserDtoResponse(user.get());
//        } else {
//            throw new RuntimeException("User not found with id " + id);
//        }
//
//    }
//
//    @DeleteMapping("/{id}")  // DELETE request to delete a user by ID
//    public void deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);  // Delete the user
//    }
//
//
//    @GetMapping("/all")
//    public List<UserDtoResponse> getAllUsers() {
//        return userRepository.findAll()
//                .stream()
//                .map(UserDtoResponse::new)
//                .collect(Collectors.toList());
//    }
//
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody User loginRequest) {
//        Optional<User> userOptional = userRepository.findAll().stream()
//                .filter(u -> u.getEmail().equals(loginRequest.getEmail()))
//                .findFirst();
//
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
//                String token = jwtUtil.generateToken(user.getEmail());
//                return ResponseEntity.ok(Collections.singletonMap("token", token));
//            }
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//    }
//
//}
package com.haridwar.university.btech.controller;


import com.haridwar.university.btech.UerDtoResponse.LoginRequestDto;
import com.haridwar.university.btech.repository.UserRepository;
import com.haridwar.university.btech.service.UserService;
import com.haridwar.university.btech.models.User;
import com.haridwar.university.btech.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/users")
@RestController
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    //
//    @PostMapping
//    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto) {
//        User savedUser = userService.saveUser(userDto);
//        return ResponseEntity.ok(Map.of("message", "User created successfully", "id", savedUser.getId()));
//    }
//
//
//
//    @GetMapping("/{id}")  // GET request to get a user by ID
//    public UserDtoResponse getUser(@PathVariable Long id) {
//            Optional<User> user = userRepository.findById(id);
//            if (user.isPresent()) {
//                return new UserDtoResponse(user.get());
//            } else {
//                throw new RuntimeException("User not found with id " + id);
//            }
//
//        }
//
//    @DeleteMapping("/{id}")  // DELETE request to delete a user by ID
//    public void deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);  // Delete the user
//    }
//
//
//    @GetMapping("/all")
//    public List<UserDtoResponse> getAllUsers() {
//        return userRepository.findAll()
//                .stream()
//                .map(UserDtoResponse::new)
//                .collect(Collectors.toList());
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        Optional<User> userOptional = userRepository.findAll().stream()
                .filter(u -> u.getEmail().equals(loginRequest.getEmail()))
                .findFirst();

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                String token = jwtUtil.generateToken(user.getEmail());
                return ResponseEntity.ok(Collections.singletonMap("token", token));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");

    }
}