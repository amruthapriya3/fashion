package net.javaguides.springboot.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.*;

@Controller
public class AdminController {
    private final UserRepository userRepository;

    @Autowired
    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/admin")
    public String adminPage() {
        
        return "admin-login";
    }
    @GetMapping("/admindashboard")
    public String adminDashboard(Model model) {
    	long userCount = userRepository.count();
        model.addAttribute("userCount", userCount);
        return "admindashboard";
    }
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = userRepository.findAll();
        int userCount = users.size();
        
        // Set attributes in the model for the Thymeleaf template
        model.addAttribute("activeTab", "users"); // Set the active tab to "users"
        model.addAttribute("userCount", userCount);
        model.addAttribute("users", users);

        return "users"; // Return the name of the Thymeleaf template (admin.html)
    }

}
