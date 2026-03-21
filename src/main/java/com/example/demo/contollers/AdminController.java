package com.example.demo.contollers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.DTO.custDetailDTO;
import com.example.demo.service.CustomerDetailsService;

@Controller
public class AdminController {

    @Autowired
    private CustomerDetailsService customerDetailsService;

    @GetMapping("/admin")
    public String adminPage(Model model) {
        List<custDetailDTO> customers = customerDetailsService.getAllCustomers().getBody();
        model.addAttribute("customers", customers);
        return "admin";
    }

    @GetMapping({ "/user", "/" })
    public String forward() {
        return "forward:/index.html";
    }
}
