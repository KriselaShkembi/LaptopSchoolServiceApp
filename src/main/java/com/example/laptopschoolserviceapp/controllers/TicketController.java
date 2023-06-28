package com.example.laptopschoolserviceapp.controllers;

import com.example.laptopschoolserviceapp.models.Laptop;
import com.example.laptopschoolserviceapp.models.LaptopPart;
import com.example.laptopschoolserviceapp.models.Ticket;
import com.example.laptopschoolserviceapp.models.User;
import com.example.laptopschoolserviceapp.services.LaptopPartService;
import com.example.laptopschoolserviceapp.services.LaptopService;
import com.example.laptopschoolserviceapp.services.TicketService;
import com.example.laptopschoolserviceapp.services.UserService;
import org.apache.catalina.LifecycleState;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private TicketService ticketService;
    private LaptopPartService laptopPartService;
    private UserService userService;
    private LaptopService laptopService;

    public TicketController(TicketService ticketService, LaptopPartService laptopPartService, UserService userService, LaptopService laptopService) {
        this.ticketService = ticketService;
        this.laptopPartService = laptopPartService;
        this.userService = userService;
        this.laptopService = laptopService;
    }

    @RequestMapping("/new")
    @PreAuthorize("hasAnyAuthority(@Role.ADMIN)")
    public String newTicket(@ModelAttribute("ticket") Ticket ticket,
                            Model model) {
        List<Laptop> laptops = laptopService.getAll();
        List<LaptopPart> parts = laptopPartService.getAll();
        model.addAttribute("tickets", ticketService.getAll());
        model.addAttribute("laptops",laptops);
        model.addAttribute("parts",parts);

        return "newTicket";
    }

    @PostMapping("/new")
    @PreAuthorize("hasAnyAuthority(@Role.ADMIN)")
    public String addNew(@Valid @ModelAttribute("ticket") Ticket ticket,
                         @NotNull BindingResult result, Model model,
                         HttpSession session) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        User userLogged = userService.findOneUser(userId);

        if (result.hasErrors()) {
            return "newTicket";
        } else {
            ticket.setUser(userLogged);
            List<Laptop> laptops = laptopService.getAll();
            List<LaptopPart> parts = laptopPartService.getAll();
            model.addAttribute("laptops",laptops);
            model.addAttribute("parts",parts);

            ticketService.create(ticket);
            return "redirect:/dashboard";
        }
    }

    @RequestMapping("/{id}")
    @PreAuthorize("hasAnyAuthority(@Role.ADMIN)")
    public String ticketDetail(@ModelAttribute("ticket") Ticket ticket, BindingResult result, Model model,
                             @PathVariable("id") Long id,
                             HttpSession session) {
        if(session.getAttribute("loggedInUserId") == null){
            return "redirect:/dashboard";
        }
        if (result.hasErrors()){
            return "redirect:/ticket/{id}";
        }
        Ticket selectedTicket = ticketService.findById(id);
        model.addAttribute("ticket", selectedTicket);

        return "viewTicket";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasAnyAuthority(@Role.ADMIN)")
    public String getEdit(@PathVariable("id") Long id, Model model){
        Ticket ticket = ticketService.findById(id);
        model.addAttribute("ticket", ticket);
        List<Laptop> laptops = laptopService.getAll();
        List<LaptopPart> parts = laptopPartService.getAll();
        model.addAttribute("laptops",laptops);
        model.addAttribute("parts",parts);
        return "editTicket";
    }

    @PutMapping("/{id}/edit")
    @PreAuthorize("hasAnyAuthority(@Role.ADMIN)")
    public String edit(@Valid @ModelAttribute("ticket") Ticket ticket,
                       @NotNull BindingResult result, Model model,
                       @PathVariable("id") Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        User userLogged = userService.findOneUser(userId);
        List<Laptop> laptops = laptopService.getAll();
        List<LaptopPart> parts = laptopPartService.getAll();
        model.addAttribute("laptops",laptops);
        model.addAttribute("parts",parts);
        if (result.hasErrors()) {
            return "editTicket";
        } else {
            ticket.setUser(userLogged);
            ticketService.update(ticket);
            return "redirect:/dashboard";
        }
    }


    @PostMapping("/{id}/generatePdf")
    public String generatePdf(@PathVariable("id") Long id){
        Ticket ticket = ticketService.findById(id);
        if (ticket != null) {
            ticketService.generateTicketPdf(ticket);
        }
        return "redirect:/dashboard";
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasAnyAuthority(@Role.ADMIN)")
    public String delete(@PathVariable("id") Long id){
        ticketService.delete(id);
        return "redirect:/dashboard";
    }

}
