package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.example.demo.repositories.PersonajeRepository;
import com.example.demo.models.Personaje;

@Service
@Transactional
public class PersonajeService {
    
    @Autowired
    private PersonajeRepository personajeRepository;
    
    // Listar todos
    public List<Personaje> listarTodos() {
        return personajeRepository.findAll();
    }
    
    // Guardar
    public Personaje guardar(Personaje personaje) {
        return personajeRepository.save(personaje);
    }
    
    // Obtener por id
    public Personaje obtenerPersonajePorId(Long id) {
        return personajeRepository.findById(id).orElse(null);
    }
    
    // Eliminar
    public void eliminar(Long id) {
        personajeRepository.deleteById(id);
    }
    
    // Verificar si existe
    public boolean existePersonaje(Long id) {
        return personajeRepository.existsById(id);
    }
    
    // Contar total
    public long contarPersonajes() {
        return personajeRepository.count();
    }
}
