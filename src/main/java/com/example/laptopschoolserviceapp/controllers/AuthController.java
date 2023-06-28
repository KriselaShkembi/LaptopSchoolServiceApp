package com.example.laptopschoolserviceapp.security.config;

import com.example.laptopschoolserviceapp.models.Laptop;
import com.example.laptopschoolserviceapp.models.User;
import com.example.laptopschoolserviceapp.services.UserService;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
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




    /* @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") RegisterRequest request, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "index"; // Return to the registration form if there are errors
        }

        // Handle registration logic using the AuthenticationService
        authenticationService.register(request);

        return "redirect:/"; // Redirect to the dashboard page after successful registration
    }*/

   /* @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("newUser") User newUser,
            @NotNull BindingResult result,
            Model model,
            HttpSession session,
            @RequestBody RegisterRequest request){
        ResponseEntity.ok(authenticationService.register(request));
        if (result.hasErrors()){
            model.addAttribute("newLogin", new User());
            return "index";
        }
        session.setAttribute("loogedInUserId", newUser.getId());
        return "redirect:/dashboard";
    }*/

    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> authentication(
            @RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));

    }
}
