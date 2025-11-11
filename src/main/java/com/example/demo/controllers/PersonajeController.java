package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo.models.Personaje;



import com.example.demo.Services.PersonajeService;

@Controller
@RequestMapping("/personajes")



public class PersonajeController {

    @Autowired
    private PersonajeService personajeService;

    @GetMapping
    public String listarPersonajes(Model model) {
        model.addAttribute("personajes", personajeService.listarPersonajes());
        return "personajes-lista";
    }

    @GetMapping("/{id}")
    public String obtenerPersonajePorId(@PathVariable Long id, Model model) {
        model.addAttribute("personaje", personajeService.obtenerPersonajePorId(id));
        return "personajes/obtener";
    }   

    @PostMapping("/guardar")
    public String guardarPersonaje(Personaje personaje) {
        personajeService.guardarPersonaje(personaje);
        return "redirect:/personajes";

    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPersonaje(@PathVariable Long id) {
        personajeService.eliminarPersonaje(id);
        return "redirect:/personajes";
    }

}
