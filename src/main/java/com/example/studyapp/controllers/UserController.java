package com.example.studyapp.controllers;

import com.example.studyapp.dtos.UserDto;
import com.example.studyapp.dtos.UserLoginDto;
import com.example.studyapp.entities.User;
import com.example.studyapp.security.JpaUserDetailsService;
import com.example.studyapp.security.JwtUtils;
import com.example.studyapp.services.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JpaUserDetailsService jpaUserDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto, BindingResult result){

        if(result.hasErrors()){
            return infoValidation(result);
        }

        userService.saveUser(userDto);
        return ResponseEntity.ok("Ok");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUsername(@Valid @RequestBody UserLoginDto userLoginDto, BindingResult result){
        if(result.hasErrors()){
            return infoValidation(result);
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword())
            );

            UserDetails userDetails = jpaUserDetailsService.loadUserByUsername(userLoginDto.getUsername());

            String token = jwtUtils.generateToken(userDetails);

            Map<String,Object> response = new HashMap<>();
            response.put("token", "Bearer " + token);
            response.put("user", userDetails.getUsername());
            response.put("message","Login existosoooo");

            return ResponseEntity.ok(response);
        }catch (AuthenticationException e){
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Credenciales inválidas");
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }




    private ResponseEntity<?> infoValidation(BindingResult bindingResult) {
        Map<String,String> errors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
