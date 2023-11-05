package net.javaguides.springboot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.javaguides.springboot.service.UserService;
import net.javaguides.springboot.web.dto.UserRegistrationDto;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private UserService userService;
    private JavaMailSender emailSender;

    @Autowired
    public UserRegistrationController(UserService userService, JavaMailSender emailSender) {
        this.userService = userService;
        this.emailSender = emailSender;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto, HttpSession session) {
        int i = 0;

        try {
            String email = registrationDto.getEmail();

            if (userService.getUserByEmail(email) != null) {
                i++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        if (i >= 1) {
            session.setAttribute("msg", "Registration Failed, Please try a different email id");
            return "redirect:/registration";
        }

        // Create and save the user
        userService.save(registrationDto);

        // Send an email with the user's credentials
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(registrationDto.getEmail());
        message.setSubject("Successfully Registered");
        message.setText("Thank you for registering! You have been successfully registered.\n\n"
                + "Your login credentials:\nEmail: " + registrationDto.getEmail() + "\nPassword: " + registrationDto.getPassword()
                + "\n\nYou can now log in to your account.");

        try {
            emailSender.send(message);
            session.setAttribute("msg", "Registration Successful...");
        } catch (Exception ex) {
            // Handle email sending errors
            System.out.println("Error sending email: " + ex.getMessage());
            session.setAttribute("msg", "Registration Successful, but there was an issue sending the email.");
        }

        return "redirect:/registration";
    }
}
