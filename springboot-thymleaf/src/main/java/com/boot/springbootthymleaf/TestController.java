package com.boot.springbootthymleaf ;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.boot.springbootthymleaf.entity.UserData;

import jakarta.validation.Valid;

@Controller
public class TestController {
    
    @GetMapping("/test")
    public String index(){
        System.out.println("hello world");
        return "test" ;
    }

    @GetMapping("/about")
    public String about(Model m)
    {
        m.addAttribute("name", "Amarjeet") ;
        m.addAttribute("course", "Java") ;
        return "about" ;
    }

    @GetMapping("/base")
    public String base()
    {
        return "extend" ;
    }

    @GetMapping("/form")
    public String form(Model model)
    {
        model.addAttribute("userData",new UserData()) ;
        return "form" ;
    }

    @PostMapping("/process")
    public String formSubmit(@Valid @ModelAttribute("userData") UserData userData)
    {
        
        return "form" ;
    }

}
