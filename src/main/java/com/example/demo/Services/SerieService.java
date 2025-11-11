package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repositories.SerieRepository;
import com.example.demo.models.Serie;
import java.util.List;

@Service
public class SerieService {
    @Autowired
    private SerieRepository serieRepository;

    //listar
    public List<Serie> listarTodas() {
        return serieRepository.findAll();
    }

    //guardar
    public Serie guardar(Serie serie) {
        return serieRepository.save(serie);
    }

    //obtener por id
    public Serie obtenerSeriePorId(Long id) {
        return serieRepository.findById(id).orElse(null);
    }

    //eliminar
    public void eliminarSerie(Long id) {
        serieRepository.deleteById(id);
    }
}
