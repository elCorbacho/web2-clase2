package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.Services.SerieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.models.Serie;   
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/series")          
public class SerieController {

    @Autowired
    private SerieService serieService;
    
    // Listar todas las series
    @GetMapping
    public String listarSeries(Model model) {
        model.addAttribute("series", serieService.listarTodas());
        model.addAttribute("total", serieService.contarSeries());
        return "layout/series-lista";
    }

    // Mostrar formulario para crear una nueva serie
    @GetMapping("/nuevo")
    public String nuevaSerie(Model model) {
        model.addAttribute("serie", new Serie());
        return "layout/series-crear";
    }

    // Ver detalles de una serie
    @GetMapping("/{id}")
    public String verSerie(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Serie serie = serieService.obtenerSeriePorId(id);
            if (serie == null) {
                redirectAttributes.addFlashAttribute("error", "Serie no encontrada");
                return "redirect:/series";
            }
            model.addAttribute("serie", serie);
            return "layout/series-detalle";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al obtener la serie: " + e.getMessage());
            return "redirect:/series";
        }
    }

    // Guardar una serie (crear o actualizar)
    @PostMapping("/guardar")
    public String guardarSerie(@ModelAttribute Serie serie, RedirectAttributes redirectAttributes) {
        try {
            if (serie.getTitulo() == null || serie.getTitulo().trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "El título es obligatorio");
                return "redirect:/series/nuevo";
            }
            if (serie.getYear() == 0) {
                redirectAttributes.addFlashAttribute("error", "El año es obligatorio");
                return "redirect:/series/nuevo";
            }
            
            serieService.guardar(serie);
            
            if (serie.getId() != null) {
                redirectAttributes.addFlashAttribute("mensaje", "Serie actualizada exitosamente");
            } else {
                redirectAttributes.addFlashAttribute("mensaje", "Serie creada exitosamente");
            }
            return "redirect:/series";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar la serie: " + e.getMessage());
            return "redirect:/series";
        }
    }

    // Mostrar formulario para editar una serie
    @GetMapping("/editar/{id}")
    public String editarSerie(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Serie serie = serieService.obtenerSeriePorId(id);
            if (serie == null) {
                redirectAttributes.addFlashAttribute("error", "Serie no encontrada");
                return "redirect:/series";
            }
            model.addAttribute("serie", serie);
            return "layout/series-editar";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al cargar la serie: " + e.getMessage());
            return "redirect:/series";
        }
    }

    // Eliminar una serie
    @GetMapping("/eliminar/{id}")
    public String eliminarSerie(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            if (!serieService.existeSerie(id)) {
                redirectAttributes.addFlashAttribute("error", "Serie no encontrada");
                return "redirect:/series";
            }
            
            serieService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Serie eliminada exitosamente");
            return "redirect:/series";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar la serie: " + e.getMessage());
            return "redirect:/series";
        }
    }
}