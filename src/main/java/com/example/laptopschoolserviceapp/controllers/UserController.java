package com.example.laptopschoolserviceapp.controllers;

import com.example.laptopschoolserviceapp.handler.CustomAccessDeniedHandler;
import com.example.laptopschoolserviceapp.models.*;
import com.example.laptopschoolserviceapp.security.AuthenticationRequest;
import com.example.laptopschoolserviceapp.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    // Logger
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    // Constructor Injection
    private UserService userService;
    private LaptopService laptopService;
    private LaptopPartService laptopPartService;
    private TicketService ticketService;
    private JwtService jwtService;

    public UserController(UserService userService, LaptopService laptopService, LaptopPartService laptopPartService, TicketService ticketService, JwtService jwtService) {
        this.userService = userService;
        this.laptopService = laptopService;
        this.laptopPartService = laptopPartService;
        this.ticketService = ticketService;
        this.jwtService = jwtService;
    }


    @GetMapping("/log")
    public String index(@ModelAttribute("newUser") User newUser,
                        @ModelAttribute("newLogin")User newLogin,
                        Model model){
        model.addAttribute("newUser", newUser);
        model.addAttribute("newLogin", newLogin);
        return "index";
    }

  /*  @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser,
                           @NotNull BindingResult result,
                           Model model,
                           HttpSession session){
        userService.register(newUser, result);
        if (result.hasErrors()){
            model.addAttribute("newLogin", new LoginUser());
            return "index";
        }
        session.setAttribute("loogedInUserId", newUser.getId());
        return "redirect:/dashboard";
    }*/

/*
    @PostMapping("/loginuser")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin,
                        @NotNull BindingResult result,
                        Model model,
                        HttpSession session){
        User user = userService.login(newLogin,result);
        if (result.hasErrors()){
            model.addAttribute("newUser", new User());
            return "index";
        }
        session.setAttribute("loggedInUserId", user.getId());
        return "redirect:/dashboard";
    }*/

    @GetMapping("/dashboard")
    public String dashboard(@ModelAttribute("newLogin") AuthenticationRequest authenticationRequest, HttpSession session, Model model){

        Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");
        User loggedInUser = userService.findOneUser(loggedInUserId);

        String token = (String) session.getAttribute("token");
        if (jwtService.isTokenValid(token, loggedInUser )) {
            List<Laptop> userLaptops = laptopService.findLaptopsByUserId(loggedInUserId);
            List<LaptopPart> parts = laptopPartService.getAll();
            List<Ticket> tickets = ticketService.getAll();

            model.addAttribute("user",loggedInUser);
            model.addAttribute("userLaptops",userLaptops);
            model.addAttribute("parts",parts);
            model.addAttribute("tickets",tickets);

            return "welcome";
        }

        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

}
