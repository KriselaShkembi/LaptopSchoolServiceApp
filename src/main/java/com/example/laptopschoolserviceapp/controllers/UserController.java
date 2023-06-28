package com.betaplan.krisela.loginandregistration.controllers;

import com.betaplan.krisela.loginandregistration.models.Book;
import com.betaplan.krisela.loginandregistration.models.LoginUser;
import com.betaplan.krisela.loginandregistration.models.User;
import com.betaplan.krisela.loginandregistration.services.BookService;
import com.betaplan.krisela.loginandregistration.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;
    @GetMapping("/")
    public String index(@ModelAttribute("newUser") User newUser,
                        @ModelAttribute("newLogin")User newLogin,
                        Model model){
        model.addAttribute("newUser", newUser);
        model.addAttribute("newLogin", newLogin);
        return "index";
    }

    @PostMapping("/register")
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
        return "redirect:/books";
    }

    @PostMapping("/login")
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
        return "redirect:/books";
    }

    @RequestMapping("/books")
    public String dashboard(HttpSession session,
                            Model model){
        Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");
        if(loggedInUserId==null){
            return "redirect:/";
        }
        User loggedInUser = userService.findOneUser(loggedInUserId);
        model.addAttribute("user",loggedInUser);
        model.addAttribute("books",bookService.getAllBooks());
        return "welcome";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping("/  books/new")
    public String newBook(@ModelAttribute("book") Book book,
                             Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "newBook";
    }

    @PostMapping("/books/new")
    public String addNewBook(@Valid @ModelAttribute("book") Book book,
                                @NotNull BindingResult result, Model model,
                                HttpSession session) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        User userLogged = userService.findOneUser(userId);
        if (result.hasErrors()) {
            return "newBook";
        } else {
            book.setUser(userLogged);
            bookService.createBooks(book);
            return "redirect:/books";
        }
    }

    @RequestMapping("/books/{id}")
    public String bookDetail(Model model,
                          @PathVariable("id") Long id,
                             HttpSession session) {
        if(session.getAttribute("loggedInUserId") == null){
            return "redirect:/books";
        }
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        model.addAttribute("user", userService.findOneUser((Long)session.getAttribute("loggedInUserId")));
        return "bookDetails";
    }

    @GetMapping("/books/{id}/edit")
    public String getEditBook(@PathVariable("id") Long id, Model model){
        Book bookEdit = bookService.bookDetails(id);
        model.addAttribute("bookEdit",bookEdit);
        return "editBook";
    }

    @PutMapping("/books/{id}/edit")
    public String editBook(@Valid @ModelAttribute("bookEdit") Book bookEdit,
                             @NotNull BindingResult result, Model model,
                           @PathVariable("id") Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        User userLogged = userService.findOneUser(userId);
        if (result.hasErrors()) {
            return "editBook";
        } else {
            bookEdit.setUser(userLogged);
            bookService.saveBooks(bookEdit);
            return "redirect:/books";
        }
    }
    @RequestMapping(value = "/books/{id}/delete", method = RequestMethod.DELETE)
    public String deleteBook(@PathVariable("id") Long id, HttpSession session){
        bookService.deleteBook(id);
        return "redirect:/books";
    }


}
