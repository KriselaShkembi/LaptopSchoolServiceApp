package com.example.laptopschoolserviceapp.controllers;

import com.example.laptopschoolserviceapp.models.Laptop;
import com.example.laptopschoolserviceapp.models.User;
import com.example.laptopschoolserviceapp.services.LaptopService;
import com.example.laptopschoolserviceapp.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/laptop")
public class LaptopController {
    // Logger
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    // Constructor Injection
    private LaptopService laptopService;
    private UserService userService;

    public LaptopController(LaptopService laptopService, UserService userService) {
        this.laptopService = laptopService;
        this.userService = userService;
    }

    @GetMapping("/new")
    @PreAuthorize("hasAnyAuthority(@Role.USER)")
    public String Laptop(@ModelAttribute("laptop") Laptop laptop,
                             Model model) {
        model.addAttribute("laptops", laptopService.getAll());
        return "newLaptop";
    }

    @PostMapping("/new")
    @PreAuthorize("hasAnyAuthority(@Role.USER)")
    public String addNew(@Valid @ModelAttribute("laptop") Laptop laptop,
                         @NotNull BindingResult result, Model model,
                         HttpSession session) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        User userLogged = userService.findOneUser(userId);

        if (result.hasErrors()) {
            return "newLaptop";
        } else {
            laptop.setUser(userLogged);
            laptopService.create(laptop);
            logger.info("Laptop created successfully.");
            return "redirect:/dashboard";
        }
    }


    @GetMapping("/{id}/edit")
    @PreAuthorize("hasAnyAuthority(@Role.USER)")
    public String getEdit(@PathVariable("id") Long id, Model model){
        Laptop laptop = laptopService.findById(id);
        model.addAttribute("laptop", laptop);
        return "editLaptop";
    }

    @PutMapping("/laptop/{id}/edit")
    @PreAuthorize("hasAnyAuthority(@Role.USER)")
    public String edit(@Valid @ModelAttribute("laptop") Laptop laptop,
                       @NotNull BindingResult result, Model model,
                       @PathVariable("id") Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        User userLogged = userService.findOneUser(userId);

        if (result.hasErrors()) {
            return "editLaptop";
        } else {
            laptop.setUser(userLogged);
            laptopService.save(laptop);
            return "redirect:/dashboard";
        }
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasAnyAuthority(@Role.USER)")
    public String delete(@PathVariable("id") Long id){
        laptopService.delete(id);
        return "redirect:/dashboard";
    }

}
