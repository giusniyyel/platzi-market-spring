package com.giusniyyel.platzimarket.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludar")
public class HolaMundoController {
    @GetMapping("/hola")
    public String saludar() {
        return "Never stop learning! 🚀";
    }
}
