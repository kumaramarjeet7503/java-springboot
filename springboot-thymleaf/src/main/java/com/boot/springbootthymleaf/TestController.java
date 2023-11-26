package com.boot.springbootthymleaf ;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

}
