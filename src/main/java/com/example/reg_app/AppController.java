package com.example.reg_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AppController {
    @Autowired//automatically create new instant of user repository
    private UserRepository repository;

    @GetMapping("")
    public String viewHomePage(){
        return "index";
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }
    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        repository.save(user);
        return "register_success";
    }
    @GetMapping("/users")
    public String viewUserList(Model model){
        List<User> listUsers = repository.findAll();
        model.addAttribute("listUsers",listUsers);
        return "users";
    }
}
