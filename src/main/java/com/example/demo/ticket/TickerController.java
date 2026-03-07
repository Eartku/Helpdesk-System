package com.example.demo.ticket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ticket")
public class TickerController {
    @GetMapping
    public String ticketPage(){
        return "ticket";
    }
}
