package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.models.Personaje;
import com.example.demo.Services.PersonajeService;

@Controller
@RequestMapping("/personajes")
public class PersonajeController {

    @Autowired
    private PersonajeService personajeService;
    
    // Listar todos los personajes
    @GetMapping
    public String listarPersonajes(Model model) {
        model.addAttribute("personajes", personajeService.listarTodos());
        model.addAttribute("total", personajeService.contarPersonajes());
        return "layout/personajes-lista";
    }

    // Mostrar formulario para crear un nuevo personaje
    @GetMapping("/nuevo")
    public String nuevoPersonaje(Model model) {
        model.addAttribute("personaje", new Personaje());
        return "layout/personajes-crear";
    }

    // Ver detalles de un personaje
    @GetMapping("/{id}")
    public String verPersonaje(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Personaje personaje = personajeService.obtenerPersonajePorId(id);
            if (personaje == null) {
                redirectAttributes.addFlashAttribute("error", "Personaje no encontrado");
                return "redirect:/personajes";
            }
            model.addAttribute("personaje", personaje);
            return "layout/personajes-detalle";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al obtener el personaje: " + e.getMessage());
            return "redirect:/personajes";
        }
    }

    // Mostrar formulario para editar un personaje
    @GetMapping("/editar/{id}")
    public String editarPersonaje(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Personaje personaje = personajeService.obtenerPersonajePorId(id);
            if (personaje == null) {
                redirectAttributes.addFlashAttribute("error", "Personaje no encontrado");
                return "redirect:/personajes";
            }
            model.addAttribute("personaje", personaje);
            return "layout/personajes-editar";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al cargar el personaje: " + e.getMessage());
            return "redirect:/personajes";
        }
    }

    // Guardar un personaje (crear o actualizar)
    @PostMapping("/guardar")
    public String guardarPersonaje(@ModelAttribute Personaje personaje, RedirectAttributes redirectAttributes) {
        try {
            if (personaje.getNombre() == null || personaje.getNombre().trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "El nombre es obligatorio");
                return "redirect:/personajes/nuevo";
            }
            if (personaje.getBiografia() == null || personaje.getBiografia().trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "La biografía es obligatoria");
                return "redirect:/personajes/nuevo";
            }
            
            personajeService.guardar(personaje);
            
            if (personaje.getId() != null) {
                redirectAttributes.addFlashAttribute("mensaje", "Personaje actualizado exitosamente");
            } else {
                redirectAttributes.addFlashAttribute("mensaje", "Personaje creado exitosamente");
            }
            return "redirect:/personajes";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el personaje: " + e.getMessage());
            return "redirect:/personajes";
        }
    }

    // Eliminar un personaje
    @GetMapping("/eliminar/{id}")
    public String eliminarPersonaje(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            if (!personajeService.existePersonaje(id)) {
                redirectAttributes.addFlashAttribute("error", "Personaje no encontrado");
                return "redirect:/personajes";
            }
            
            personajeService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Personaje eliminado exitosamente");
            return "redirect:/personajes";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el personaje: " + e.getMessage());
            return "redirect:/personajes";
        }
    }
}
