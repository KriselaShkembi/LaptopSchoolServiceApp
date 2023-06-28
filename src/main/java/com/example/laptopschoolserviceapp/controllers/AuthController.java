package com.example.laptopschoolserviceapp.controllers;

import com.example.laptopschoolserviceapp.security.AuthenticationRequest;
import com.example.laptopschoolserviceapp.security.AuthenticationResponse;
import com.example.laptopschoolserviceapp.services.AuthenticationService;
import com.example.laptopschoolserviceapp.security.RegisterRequest;
import com.example.laptopschoolserviceapp.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class AuthController {
    private AuthenticationService authenticationService;
    private UserService userService;

    public AuthController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authentication")
    public String authentication(@Valid @ModelAttribute("newLogin")AuthenticationRequest authenticationRequest, HttpSession session){
        try{
            String token = authenticationService.authenticate(authenticationRequest).getToken();
            session.setAttribute("token", token);
        }catch (Exception e){
            return "redirect:/";
        }
        return "redirect:/dashboard";
    }
}
