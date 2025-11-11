package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.repositories.SerieRepository;
import com.example.demo.models.Serie;
import java.util.List;

@Service
@Transactional
public class SerieService {
    @Autowired
    private SerieRepository serieRepository;

    // Listar todas
    public List<Serie> listarTodas() {
        return serieRepository.findAll();
    }

    // Guardar
    public Serie guardar(Serie serie) {
        return serieRepository.save(serie);
    }

    // Obtener por id
    public Serie obtenerSeriePorId(Long id) {
        return serieRepository.findById(id).orElse(null);
    }

    // Eliminar
    public void eliminar(Long id) {
        serieRepository.deleteById(id);
    }
    
    // Verificar si existe
    public boolean existeSerie(Long id) {
        return serieRepository.existsById(id);
    }
    
    // Contar total
    public long contarSeries() {
        return serieRepository.count();
    }
}
