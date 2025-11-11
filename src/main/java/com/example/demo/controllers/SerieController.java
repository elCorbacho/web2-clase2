package com.example.demo.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.Services.SerieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.models.Serie;   
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequestMapping("/series")          
public class SerieController {

    @Autowired
    private SerieService serieService;
    @GetMapping
    public String listarSeries(Model model) {
        model.addAttribute("titulo", "listado de series");
        model.addAttribute("series", serieService.listarTodas());
        return "series-lista";
    }

    // Método para mostrar el formulario de creación de una nueva serie
    @GetMapping("/nuevo")
    public String nuevaSerie(Model model) {
        model.addAttribute("serie", new Serie());
        return "series-crear";
    }

    @PostMapping("/guardar")
    public String guardarSerie(Serie serie) {
        serieService.guardar(serie);
        return "redirect:/series";
    }


    // Método para eliminar una serie por su ID
    @GetMapping("/editar/{id}")
    public String editarSerie(@PathVariable Long id, Model model) {
        model.addAttribute("serie", serieService.obtenerSeriePorId(id));
        return "series-editar";
    }

    // Método para eliminar una serie por su ID
    @GetMapping("/eliminar/{id}")
    public String eliminarSerie(@PathVariable Long id) {
        serieService.eliminarSerie(id);
        return "redirect:/series";
    }

}