package net.javaguides.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    @GetMapping("/custhome")
    public String custhome() {
        return "custhome";
    }
  
    @GetMapping("/dresses")
    public String adminPage() {
        
        return "Dresses";
    }
    @GetMapping("/dressproduct")
    public String dressPro() {
        
        return "dressproduct";
    }
    @GetMapping("/beauty")
    public String beauty() {
        
        return "beautypro";
    }
    
    @GetMapping("/about")
    public String about() {
        
        return "about";
    }
    @GetMapping("/basic")
    public String basic() {
        
        return "page1";
    }
    @GetMapping("/party")
    public String party() {
        
        return "page2";
    }
    @GetMapping("/traditional")
    public String traditional() {
        
        return "page3";
    }
    @GetMapping("/error")
    public String error() {
        
        return "error";
    }
    @GetMapping("/payment")
    public String payment() {
        
        return "payment";
    }
   
    }



