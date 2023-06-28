package com.example.laptopschoolserviceapp.controllers;

import com.example.laptopschoolserviceapp.models.LaptopPart;
import com.example.laptopschoolserviceapp.models.User;
import com.example.laptopschoolserviceapp.services.LaptopPartService;
import com.example.laptopschoolserviceapp.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/part")
public class LaptopPartController {

    private LaptopPartService laptopPartService;
    private UserService userService;

    public LaptopPartController(LaptopPartService laptopPartService, UserService userService) {
        this.laptopPartService = laptopPartService;
        this.userService = userService;
    }

    @RequestMapping("/new")
    @PreAuthorize("hasAnyAuthority(@Role.ADMIN)")
    public String newLaptopPart(@ModelAttribute("part") LaptopPart part,
                             Model model) {
        model.addAttribute("parts", laptopPartService.getAll());
        return "newPart";
    }

    @PostMapping("/new")
    @PreAuthorize("hasAnyAuthority(@Role.ADMIN)")
    public String addNew(@Valid @ModelAttribute("part") LaptopPart part,
                         @NotNull BindingResult result, Model model,
                         HttpSession session) {
        if (result.hasErrors()) {
            return "newPart";
        } else {
            laptopPartService.create(part);
            return "redirect:/dashboard";
        }
    }

    @RequestMapping("/{id}")
    @PreAuthorize("hasAnyAuthority(@Role.ADMIN)")
    public String partDetail(@ModelAttribute("part") LaptopPart part, BindingResult result, Model model,
                             @PathVariable("id") Long id,
                             HttpSession session) {
        if(session.getAttribute("loggedInUserId") == null){
            return "redirect:/dashboard";
        }
        if (result.hasErrors()){
            return "redirect:/part/{id}";
        }
        LaptopPart laptopPart = laptopPartService.findById(id);
        model.addAttribute("part", laptopPart);

        return "viewPart";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasAnyAuthority(@Role.ADMIN)")
    public String getEdit(@PathVariable("id") Long id, Model model){
        LaptopPart part = laptopPartService.findById(id);
        model.addAttribute("part", part);
        return "editPart";
    }

    @PutMapping("/{id}/edit")
    @PreAuthorize("hasAnyAuthority(@Role.ADMIN)")
    public String edit(@Valid @ModelAttribute("part") LaptopPart part,
                       @NotNull BindingResult result, Model model,
                       @PathVariable("id") Long id, HttpSession session) {
        if (result.hasErrors()) {
            return "editPart";
        } else {
            laptopPartService.save(part);
            return "redirect:/dashboard";
        }
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasAnyAuthority(@Role.ADMIN)")
    public String delete(@PathVariable("id") Long id){
        laptopPartService.delete(id);
        return "redirect:/dashboard";
    }


}
