package net.atos.khayma.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UtilisateurController {

    @RequestMapping("/test")
    public String test() {
        return "HELLO WORLD !!!";
    }

}
