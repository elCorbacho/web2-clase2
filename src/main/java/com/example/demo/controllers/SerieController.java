package com.example.demo.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.Services.SerieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.demo.models.Serie;   





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

    @GetMapping("/nuevo")
    public String nuevaSerie(Model model) {
        model.addAttribute("serie", new Serie());
        return "series-crear";
    }

    @PostMapping("/guardar")
    public String postMethodName(Serie serie) {
        serieService.guardar(serie);
        return "redirect:/series";
    }
    
    
    
    
}

